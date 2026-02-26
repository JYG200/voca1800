<template>
    <main id="study-main">
        <div class="card-container">

            <!-- 다음 카드 -->
            <div v-show="words.length > 0" class="word-card next-card" :style="nextCardStyle">
                <div class="card-face card-front">
                    <div class="card-tag">Day 1</div>

                    <div class="card-content-wrapper" :style="{ opacity: nextContentOpacity, transition: 'none' }">
                        <div class="word-group">
                            <h1 class="word-text">{{ (words[1] ?? words[0])?.word }}</h1>
                            <p class="phonetic-text">{{ (words[1] ?? words[0])?.phonetic }}</p>
                        </div>
                        <p class="hint-text">탭하여 뜻 확인 👆</p>
                    </div>

                    <div class="guide-text">← 외움 &nbsp;|&nbsp; 미흡 →</div>
                </div>
            </div>

            <!-- 현재 카드 -->
            <div v-if="words.length > 0" ref="topCardRef" class="word-card top-card" :class="cardClass"
                :style="cardStyle" @pointerdown="onPointerDown">
                <div class="card-inner" :class="{ 'is-flipped': isFlipped }">
                    <!-- 앞면 -->
                    <div class="card-face card-front">
                        <div class="drag-overlay"></div>
                        <div class="card-tag">Day 1</div>
                        <div class="card-content-wrapper">
                            <div class="word-group">
                                <h1 class="word-text">{{ words[0].word }}</h1>
                                <p class="phonetic-text">{{ words[0].phonetic }}</p>
                            </div>
                            <p class="hint-text">탭하여 뜻 확인 👆</p>
                        </div>
                        <div class="guide-text">← 외움 &nbsp;|&nbsp; 미흡 →</div>
                    </div>

                    <!-- 뒷면 -->
                    <div class="card-face card-back">
                        <div class="drag-overlay"></div>
                        <div class="card-tag tag-back">Day 1</div>
                        <div class="card-content-wrapper">
                            <div class="word-group">
                                <h1 class="word-text word-text-back">{{ words[0].word }}</h1>
                                <p class="phonetic-text phonetic-text-back">{{ words[0].phonetic }}</p>
                            </div>
                            <div class="meaning-group">
                                <span class="meaning-text">{{ words[0].meaning }}</span>
                            </div>
                        </div>
                        <div class="guide-text guide-text-back">← 외움 &nbsp;|&nbsp; 미흡 →</div>
                    </div>

                </div>
            </div>

            <!-- 완료 화면 -->
            <div v-if="words.length === 0"
                class="empty-state position-absolute w-100 h-100 d-flex flex-column align-center justify-center">
                <div class="complete-icon">🎉</div>
                <h2 class="complete-title">학습 완료!</h2>
                <p class="complete-sub">{{ initialWords.length }}개 단어를 모두 외웠어요</p>
                <button class="reset-btn" @click="router.push({ name: 'main' })">확인</button>
            </div>

        </div>
    </main>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const emit = defineEmits(['update-progress'])

const initialWords = [
    { id: 1, word: 'Apple', meaning: '사과', phonetic: '[æpl]' },
    { id: 2, word: 'Banana', meaning: '바나나', phonetic: '[bəˈnænə]' },
    { id: 3, word: 'Cherry', meaning: '체리', phonetic: '[ˈtʃeri]' },
]

const words = ref([...initialWords])
const isFlipped = ref(false)
const topCardRef = ref(null)
const animState = ref('idle')

const pointerStart = ref({ x: 0, y: 0 })
const dragX = ref(0)
const isDragging = ref(false)
const hasMoved = ref(false)

watch(() => words.value.length, (newLength) => {
    emit('update-progress', { total: initialWords.length, remaining: newLength })
}, { immediate: true })

const onPointerDown = (e) => {
    if (animState.value !== 'idle') return
    pointerStart.value = { x: e.clientX, y: e.clientY }
    dragX.value = 0
    isDragging.value = true
    hasMoved.value = false
    window.addEventListener('pointermove', onPointerMove)
    window.addEventListener('pointerup', onPointerUp)
}

const onPointerMove = (e) => {
    if (!isDragging.value) return
    const dx = e.clientX - pointerStart.value.x
    if (Math.abs(dx) > 5) {
        hasMoved.value = true
        animState.value = 'dragging'
        dragX.value = dx
    }
}

const onPointerUp = () => {
    window.removeEventListener('pointermove', onPointerMove)
    window.removeEventListener('pointerup', onPointerUp)

    if (!isDragging.value) return
    isDragging.value = false

    if (!hasMoved.value || Math.abs(dragX.value) < 8) {
        animState.value = 'idle'
        dragX.value = 0
        isFlipped.value = !isFlipped.value
        return
    }

    if (dragX.value < -130) {
        handleSwipeOut('left')
    } else if (dragX.value > 130) {
        handleSwipeOut('right')
    } else {
        animState.value = 'returning'
        dragX.value = 0
        setTimeout(() => { animState.value = 'idle' }, 500)
    }
}

const cardClass = computed(() => ({
    'is-flying-left': animState.value === 'flying-left',
    'is-flying-right': animState.value === 'flying-right',
    'is-returning': animState.value === 'returning',
    'is-entering': animState.value === 'entering',
}))

const cardStyle = computed(() => {
    if (animState.value === 'dragging') {
        const x = dragX.value
        const rotate = Math.min(Math.max(x / 12, -25), 25)
        const opacity = Math.max(0.75, 1 - Math.abs(x) / 280)
        const progress = Math.min(1, Math.abs(x) / 130)
        const isLeft = x < 0

        // 왼쪽(외움) → 초록 glow, 오른쪽(미흡) → 빨강 glow
        const glowColor = isLeft
            ? `rgba(52, 211, 153, ${progress * 0.7})`
            : `rgba(248, 113, 113, ${progress * 0.7})`
        const overlayColor = isLeft
            ? `rgba(52, 211, 153, ${progress * 0.13})`
            : `rgba(248, 113, 113, ${progress * 0.13})`

        return {
            transform: `translateX(${x}px) rotate(${rotate}deg)`,
            opacity,
            transition: 'none',
            filter: `drop-shadow(0 12px 36px ${glowColor})`,
            '--overlay-color': overlayColor,
        }
    }
    return {
        '--overlay-color': 'transparent',
    }
})

const handleSwipeOut = (dir) => {
    isFlipped.value = false
    animState.value = dir === 'left' ? 'flying-left' : 'flying-right'

    setTimeout(() => {
        const current = words.value.shift()
        if (dir === 'right') {
            words.value.push({ ...current, id: Date.now() })
        }
        dragX.value = 0
        animState.value = 'entering'
        requestAnimationFrame(() => {
            requestAnimationFrame(() => {
                animState.value = 'idle'
            })
        })
    }, 380)
}

const nextContentOpacity = computed(() => {
    return 1
})

const nextCardStyle = computed(() => {
  if (animState.value === 'dragging') {
    const progress = Math.min(1, Math.abs(dragX.value) / 130)
    const scale = 0.97 + progress * 0.03
    const translateY = 8 - progress * 8
    return {
      transform: `scale(${scale}) translateY(${translateY}px)`,
      transition: 'none',
    }
  }
  return {}
})


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

#study-main {
    flex: 1;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: visible;
    font-family: 'Nunito', sans-serif;
}

.card-container {
    width: 90%;
    height: 72%;
    max-width: 420px;
    min-height: 400px;
    min-width: 280px;
    position: relative;
    touch-action: none;
    overflow: visible;
}

.word-card {
    position: absolute;
    width: 100%;
    height: 100%;
    cursor: grab;
    user-select: none;
}

.word-card:active {
    cursor: grabbing;
}

.next-card {
    border-radius: 32px;
    z-index: 1;
    overflow: hidden;
}

/* 앞 카드 래퍼 */
.top-card {
    z-index: 5;
    background: transparent !important;
    box-shadow: none !important;
    transform-style: preserve-3d;
}

/* 날아가기 - 초록/빨강 glow 유지 */
.top-card.is-flying-left {
    transform: translateX(-700px) rotate(-30deg) !important;
    opacity: 0 !important;
    filter: drop-shadow(0 12px 40px rgba(52, 211, 153, 0.6)) !important;
    transition: transform 0.38s cubic-bezier(0.55, 0, 1, 0.45),
        opacity 0.25s ease,
        filter 0.38s ease !important;
}

.top-card.is-flying-right {
    transform: translateX(700px) rotate(30deg) !important;
    opacity: 0 !important;
    filter: drop-shadow(0 12px 40px rgba(248, 113, 113, 0.6)) !important;
    transition: transform 0.38s cubic-bezier(0.55, 0, 1, 0.45),
        opacity 0.25s ease,
        filter 0.38s ease !important;
}

/* 제자리 복귀 */
.top-card.is-returning {
    transform: translateX(0px) rotate(0deg) !important;
    opacity: 1 !important;
    filter: drop-shadow(0 0 0 transparent) !important;
    transition: transform 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.4),
        opacity 0.3s ease,
        filter 0.4s ease !important;
}

/* 새 카드 진입 시작점 */
.top-card.is-entering {
    opacity: 0 !important;
    transition: none !important;
}

/* idle */
.top-card:not(.is-flying-left):not(.is-flying-right):not(.is-returning):not(.is-entering) {
    transform: translateX(0px) translateY(0px) rotate(0deg) scale(1);
    opacity: 1;
    filter: drop-shadow(0 0 0 transparent);
    transition: opacity 0.3s ease, filter 0.4s ease;
}

/* 3D flip */
.card-inner {
    width: 100%;
    height: 100%;
    position: relative;
    transform-style: preserve-3d;
    transition: transform 0.55s cubic-bezier(0.455, 0.03, 0.515, 0.955);
    border-radius: 32px;
}

.card-inner.is-flipped {
    transform: rotateY(180deg);
}

/* 앞/뒷면 공통 */
.card-face {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 32px;
    backface-visibility: hidden;
    -webkit-backface-visibility: hidden;
    overflow: hidden;
}

/* ✅ 드래그 오버레이 - 배경색이 물드는 효과 */
.drag-overlay {
    position: absolute;
    inset: 0;
    border-radius: 32px;
    background-color: var(--overlay-color, transparent);
    pointer-events: none;
    z-index: 3;
    transition: background-color 0.05s ease;
}

/* 앞면 */
.card-front {
    background: linear-gradient(145deg, #ffffff 0%, #f5f0ff 60%, #ede8ff 100%);
    box-shadow: 0 20px 60px rgba(167, 139, 250, 0.25), 0 4px 16px rgba(0, 0, 0, 0.06);
}

.card-front::before {
    content: '';
    position: absolute;
    top: -60px;
    right: -60px;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background: radial-gradient(circle, rgba(167, 139, 250, 0.18) 0%, transparent 70%);
    pointer-events: none;
}

.card-front::after {
    content: '';
    position: absolute;
    bottom: -40px;
    left: -40px;
    width: 160px;
    height: 160px;
    border-radius: 50%;
    background: radial-gradient(circle, rgba(96, 165, 250, 0.15) 0%, transparent 70%);
    pointer-events: none;
}

/* 뒷면 */
.card-back {
    background: linear-gradient(145deg, #7c3aed 0%, #4f46e5 50%, #2563eb 100%);
    box-shadow: 0 20px 60px rgba(79, 70, 229, 0.4), 0 4px 16px rgba(0, 0, 0, 0.1);
    transform: rotateY(180deg);
}

.card-back::before {
    content: '';
    position: absolute;
    top: -80px;
    right: -80px;
    width: 260px;
    height: 260px;
    border-radius: 50%;
    background: radial-gradient(circle, rgba(255, 255, 255, 0.12) 0%, transparent 70%);
    pointer-events: none;
}

.card-back::after {
    content: '';
    position: absolute;
    bottom: -60px;
    left: -60px;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background: radial-gradient(circle, rgba(255, 255, 255, 0.08) 0%, transparent 70%);
    pointer-events: none;
}

/* Day 태그 */
.card-tag {
    position: absolute;
    top: 24px;
    left: 24px;
    padding: 5px 14px;
    background: rgba(167, 139, 250, 0.15);
    border: 1.5px solid rgba(167, 139, 250, 0.3);
    border-radius: 99px;
    font-size: 12px;
    font-weight: 700;
    color: #7c3aed;
    letter-spacing: 0.5px;
    z-index: 2;
}

.tag-back {
    background: rgba(255, 255, 255, 0.15);
    border-color: rgba(255, 255, 255, 0.25);
    color: rgba(255, 255, 255, 0.85);
}

/* 카드 내부 레이아웃 */
.card-content-wrapper {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 16px;
    padding: 32px;
    position: relative;
    z-index: 1;
}

.word-group {
    text-align: center;
}

.word-text {
    font-size: 52px;
    font-weight: 900;
    color: #1e1b4b;
    letter-spacing: -1px;
    line-height: 1.1;
    margin-bottom: 8px;
}

.word-text-back {
    color: rgba(255, 255, 255, 0.95);
}

.phonetic-text {
    font-size: 18px;
    font-weight: 400;
    color: #9ca3af;
    letter-spacing: 1px;
}

.phonetic-text-back {
    color: rgba(255, 255, 255, 0.6);
}

.meaning-group {
    text-align: center;
}

.meaning-text {
    font-size: 36px;
    font-weight: 900;
    color: #ffffff;
    letter-spacing: -0.5px;
    text-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
}

.hint-text {
    font-size: 13px;
    font-weight: 600;
    color: #c4b5fd;
    margin-top: 4px;
}

.guide-text {
    position: absolute;
    bottom: 20px;
    width: 100%;
    text-align: center;
    font-size: 12px;
    font-weight: 600;
    color: #c4b5fd;
    letter-spacing: 0.3px;
    z-index: 2;
}

.guide-text-back {
    color: rgba(255, 255, 255, 0.35);
}

/* 완료 화면 */
.empty-state {
    text-align: center;
}

.complete-icon {
    font-size: 72px;
    margin-bottom: 16px;
    animation: bounce 1s ease infinite alternate;
}

@keyframes bounce {
    from {
        transform: translateY(0);
    }

    to {
        transform: translateY(-12px);
    }
}

.complete-title {
    font-size: 28px;
    font-weight: 900;
    color: #1e1b4b;
    margin-bottom: 8px;
}

.complete-sub {
    font-size: 15px;
    color: #7c6aaa;
    margin-bottom: 32px;
}

.reset-btn {
    padding: 14px 36px;
    background: linear-gradient(135deg, #7c3aed, #2563eb);
    color: white;
    border: none;
    border-radius: 99px;
    font-size: 16px;
    font-weight: 700;
    font-family: 'Nunito', sans-serif;
    cursor: pointer;
    box-shadow: 0 8px 24px rgba(124, 58, 237, 0.4);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.reset-btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 12px 32px rgba(124, 58, 237, 0.5);
}
</style>