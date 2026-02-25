<template>
    <main id="study-main">
        <div class="card-container">

            <v-card v-if="words.length > 1" class="word-card next-card elevation-1">
                <div class="word-content text-center opacity-50">
                    <h1 class="text-h2 font-weight-black text-grey-lighten-2">{{ words[1].word }}</h1>
                </div>
            </v-card>

            <v-card v-if="words.length > 0" ref="topCardRef" class="word-card top-card elevation-4" :style="cardStyle"
                @click="isFlipped = !isFlipped">
                <div class="word-content text-center">
                    <h1 class="text-h2 font-weight-black">{{ words[0].word }}</h1>
                    <p class="text-h6 text-grey mt-2">{{ words[0].phonetic }}</p>
                </div>

                <div class="meaning-content mt-12 text-center" :style="{ opacity: isFlipped ? 1 : 0 }">
                    <span class="text-h4 font-weight-bold text-primary">{{ words[0].meaning }}</span>
                </div>

                <div class="guide mt-auto mb-10 text-caption text-grey-lighten-1">
                    ← 외움(삭제) | 미흡(뒤로) →
                </div>
            </v-card>

            <div v-if="words.length === 0" class="empty-state text-center">
                <v-icon size="80" color="success">mdi-party-popper</v-icon>
                <h2 class="mt-4">학습을 마쳤습니다!</h2>
                <v-btn class="mt-6" color="primary" variant="flat" @click="reset">다시 시작</v-btn>
            </div>

        </div>
    </main>
</template>

<script setup>
import { ref, computed, watch } from 'vue' 
import { usePointerSwipe } from '@vueuse/core'

// 2️⃣ 부모에게 신호를 보낼 emit을 정의합니다. (import 바로 아래에 작성)
const emit = defineEmits(['update-progress'])

const initialWords = [
    { id: 1, word: 'Apple', meaning: '사과', phonetic: '[æpl]' },
    { id: 2, word: 'Banana', meaning: '바나나', phonetic: '[bəˈnænə]' },
    { id: 3, word: 'Cherry', meaning: '체리', phonetic: '[ˈtʃeri]' },
]

const words = ref([...initialWords])
const isFlipped = ref(false)

// 3️⃣ 🌟 남은 단어 수가 바뀔 때마다 부모에게 데이터를 쏴줍니다!
watch(() => words.value.length, (newLength) => {
    emit('update-progress', {
        total: initialWords.length,
        remaining: newLength
    })
}, { immediate: true }) // immediate: true 덕분에 화면이 켜지자마자 바로 1번 전송합니다.

// 🌟 스와이프를 감지할 카드 Element 지정
const topCardRef = ref(null)

// 🌟 날아가는 애니메이션 상태 제어
const isAnimating = ref(false)
const animateX = ref(0)

// 🌟 VueUse 마법: 카드를 잡고 움직이는 거리를 실시간으로 계산
const { distanceX, isSwiping } = usePointerSwipe(topCardRef, {
    onSwipeEnd() {
        // 손가락을 뗐을 때, 이동한 거리가 100px 이상이면 날려버림
        if (distanceX.value > 100) {
            handleSwipeOut('left') // 왼쪽으로 (외움)
        } else if (distanceX.value < -100) {
            handleSwipeOut('right') // 오른쪽으로 (미흡)
        } else {
            // 거리가 짧으면 다시 제자리로 복귀
            animateX.value = 0
        }
    }
})

// 🌟 거리에 따라 카드의 CSS를 실시간으로 변형 (핵심 로직)
const cardStyle = computed(() => {
    // 1. 카드가 날아가고 있는 중일 때
    if (isAnimating.value) {
        return {
            transform: `translateX(${animateX.value}px) rotate(${animateX.value / 15}deg)`,
            transition: 'transform 0.4s ease-out'
        }
    }

    // 2. 사용자가 손가락으로 카드를 잡고 끌고 있을 때
    if (isSwiping.value) {
        const x = -distanceX.value // VueUse는 왼쪽이 양수이므로 부호를 반대로
        return {
            transform: `translateX(${x}px) rotate(${x / 15}deg)`,
            transition: 'none' // 끌고 있을 때는 지연 없이 손가락에 딱 붙게
        }
    }

    // 3. 기본 상태 (가운데 정렬)
    return {
        transform: 'translateX(0px) rotate(0deg)',
        transition: 'transform 0.3s cubic-bezier(0.25, 0.8, 0.25, 1)'
    }
})

// 카드를 날려버리는 함수
const handleSwipeOut = (dir) => {
    isAnimating.value = true
    // 화면 밖으로 날아갈 목적지 설정 (왼쪽 -500, 오른쪽 500)
    animateX.value = dir === 'left' ? -500 : 500

    // 애니메이션이 끝날 즈음(300ms) 데이터 처리
    setTimeout(() => {
        const current = words.value.shift()
        isFlipped.value = false

        if (dir === 'right') { // 못 외운 단어는 맨 뒤로
            words.value.push({ ...current, id: Date.now() })
        }

        // 다음 카드를 위해 상태 초기화
        isAnimating.value = false
        animateX.value = 0
    }, 300)
}

const reset = () => { words.value = [...initialWords] }
</script>

<style scoped>
#study-main {
    flex: 1;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
}

.card-container {
    width: 90%;
    height: 75%;
    max-width: 450px;
    position: relative;
    /* 카드를 넘길 때 브라우저 자체의 뒤로가기 스와이프 방지 */
    touch-action: none;
}

.word-card {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 32px !important;
    background-color: white !important;
    cursor: grab;
    user-select: none;
}

.word-card:active {
    cursor: grabbing;
}

/* 아래에 깔린 다음 카드 디자인 (살짝 작게 보여서 입체감 형성) */
.next-card {
    transform: scale(0.95) translateY(10px);
    z-index: 1;
}

/* 현재 맨 위 카드 */
.top-card {
    z-index: 5;
}

.meaning-content {
    transition: opacity 0.3s ease;
}
</style>