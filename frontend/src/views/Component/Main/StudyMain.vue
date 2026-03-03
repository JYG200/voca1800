<template>
    <main id="study-main">
        <div class="card-container">
            <div v-if="words.length > 1" class="word-card next-card" :style="nextCardStyle">
                <div class="card-inner card-inner-static">
                    <div class="card-face card-front">
                        <div class="card-tag">Day 1</div>
                        <div class="card-content-wrapper">
                            <div class="word-group">
                                <h1 class="word-text">{{ nextPreview?.word }}</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div v-if="words.length > 0" class="word-card top-card" :class="cardClass" :style="cardStyle"
                @pointerdown="onPointerDown" @pointermove="onPointerMove" @pointerup="onPointerUp"
                @pointercancel="onPointerUp">

                <div class="card-inner" :key="frontPreview?.id" :class="{ 'is-flipped': isFlipped }">
                    <div class="card-face card-front">
                        <div class="card-tag">Day 1</div>
                        <div class="card-content-wrapper">
                            <div class="word-group">
                                <h1 class="word-text">{{ frontPreview?.word }}</h1>
                            </div>
                        </div>
                    </div>

                    <div class="card-face card-back">
                        <div class="card-tag tag-back">Day 1</div>
                        <div class="card-content-wrapper">
                            <div class="word-group">
                                <h1 class="word-text word-text-back">{{ frontPreview?.word }}</h1>
                                <p class="phonetic-text phonetic-text-back">{{ frontPreview?.phonetic }}</p>
                            </div>
                            <div class="meaning-group">
                                <span class="meaning-text">{{ frontPreview?.meaning }}</span>
                            </div>
                        </div>
                        <div class="guide-text guide-text-back">← 외움 &nbsp;|&nbsp; 미흡 →</div>
                    </div>
                </div>
            </div>

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
import { ref, computed, watch, watchEffect, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const emit = defineEmits(['update-progress'])

const initialWords = [
    { id: 1, word: 'Apple', meaning: '사과', phonetic: '[æpl]' },
    { id: 2, word: 'Banana', meaning: '바나나', phonetic: '[bəˈnænə]' },
    { id: 3, word: 'Cherry', meaning: '체리', phonetic: '[ˈtʃeri]' },
]

const words = ref([...initialWords])

/** ===================== 상태 ===================== */
const isFlipped = ref(false)
const animState = ref('idle')

const pointerStart = ref({ x: 0, y: 0 })
const dragX = ref(0)
const isDragging = ref(false)
const capturedTarget = ref(null)

/** ===================== Preview(깜빡임 방지) ===================== */
const frontPreview = ref(words.value[0] ?? null)
const nextPreview = ref(words.value[1] ?? null)

watchEffect(() => {
    if (animState.value !== 'idle') return
    frontPreview.value = words.value[0] ?? null
    nextPreview.value = words.value[1] ?? null
})

watch(
    () => words.value.length,
    (len) => emit('update-progress', { total: initialWords.length, remaining: len }),
    { immediate: true }
)

/** ===================== returning 감쇠 ===================== */
const returningProgress = ref(0)
let returningRaf = null

function startReturningDecay(from) {
    cancelReturningDecay()
    returningProgress.value = from

    const duration = 350
    const start = performance.now()
    const easeOutCubic = (t) => 1 - Math.pow(1 - t, 3)

    const tick = (now) => {
        const t = Math.min(1, (now - start) / duration)
        const eased = easeOutCubic(t)
        returningProgress.value = from * (1 - eased)
        if (t < 1) returningRaf = requestAnimationFrame(tick)
    }

    returningRaf = requestAnimationFrame(tick)
}

function cancelReturningDecay() {
    if (returningRaf) cancelAnimationFrame(returningRaf)
    returningRaf = null
}

onBeforeUnmount(() => cancelReturningDecay())

/** ===================== 포인터 이벤트 ===================== */
const onPointerDown = (e) => {
    if (animState.value !== 'idle') return

    capturedTarget.value = e.currentTarget
    capturedTarget.value?.setPointerCapture?.(e.pointerId)

    pointerStart.value = { x: e.clientX, y: e.clientY }
    dragX.value = 0
    isDragging.value = true
}

function onPointerMove(e) {
    if (!isDragging.value) return

    const dx = e.clientX - pointerStart.value.x
    if (Math.abs(dx) > 5) {
        animState.value = 'dragging'
        dragX.value = dx
    }
}

function onPointerUp(e) {
    capturedTarget.value?.releasePointerCapture?.(e.pointerId)
    capturedTarget.value = null

    if (!isDragging.value) return
    isDragging.value = false

    if (Math.abs(dragX.value) < 8) {
        animState.value = 'idle'
        dragX.value = 0
        isFlipped.value = !isFlipped.value
        return
    }

    if (dragX.value < -130) handleSwipeOut('left')
    else if (dragX.value > 130) handleSwipeOut('right')
    else {
        const from = Math.min(1, Math.abs(dragX.value) / 130)
        animState.value = 'returning'
        dragX.value = 0
        startReturningDecay(from)

        setTimeout(() => {
            animState.value = 'idle'
            returningProgress.value = 0
            cancelReturningDecay()
        }, 500)
    }
}

/** ===================== 스와이프 처리 ===================== */
const handleSwipeOut = (dir) => {
    isFlipped.value = false
    cancelReturningDecay()
    returningProgress.value = 0

    frontPreview.value = words.value[0] ?? null
    animState.value = dir === 'left' ? 'flying-left' : 'flying-right'

    setTimeout(() => {
        const current = words.value.shift()
        if (dir === 'right' && current) words.value.push({ ...current, id: Date.now() })

        dragX.value = 0
        animState.value = 'entering'
        frontPreview.value = words.value[0] ?? null

        requestAnimationFrame(() => {
            requestAnimationFrame(() => {
                animState.value = 'idle'
            })
        })
    }, 380)
}

/** ===================== 스타일 바인딩 ===================== */
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
        const p = Math.min(1, Math.abs(x) / 130)

        const glowColor = x < 0
            ? `rgba(34, 197, 94, ${p * 0.7})`
            : `rgba(239, 68, 68, ${p * 0.7})`

        return {
            transform: `translateX(${x}px) rotate(${rotate}deg)`,
            transition: 'none',
            opacity: 1,
            filter: `drop-shadow(0 0 ${p * 40}px ${glowColor})`,
        }
    }
    if (animState.value === 'returning') return { transform: `translateX(0px) rotate(0deg)`, opacity: 1 }
    if (animState.value === 'flying-left') return { transform: `translateX(-700px) rotate(-30deg)`, opacity: 0 }
    if (animState.value === 'flying-right') return { transform: `translateX(700px) rotate(30deg)`, opacity: 0 }

    return {
        transform: `translateX(0px) rotate(0deg)`,
        opacity: animState.value === 'entering' ? 0 : 1,
    }
})

const nextCardStyle = computed(() => {
    if (words.value.length <= 1) {
        return { opacity: 0, transform: 'translateY(0px) scale(1)', transition: 'opacity 0.2s ease' }
    }

    let p = 0
    if (animState.value === 'dragging') p = Math.min(1, Math.abs(dragX.value) / 130)
    else if (animState.value === 'returning') p = returningProgress.value
    else if (animState.value === 'flying-left' || animState.value === 'flying-right') p = 1

    const scale = 0.96 + 0.04 * p
    const translateY = 12 - 12 * p

    return {
        transform: `translateY(${translateY}px) scale(${scale})`,
        opacity: 1,
        transition: animState.value === 'dragging' ? 'none' : 'transform 0.38s ease',
    }
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
}

/* ================= 카드 공통 ================= */
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

/* ================= 앞 카드 ================= */
.top-card {
    z-index: 5;
    background: transparent !important;
    box-shadow: none !important;
    transform-style: preserve-3d;
    will-change: transform, opacity;
}

.top-card.is-flying-left,
.top-card.is-flying-right {
    transition: transform 0.38s cubic-bezier(0.55, 0, 1, 0.45), opacity 0.25s ease;
}

.top-card.is-returning {
    transition: transform 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.4), opacity 0.3s ease;
}

.top-card.is-entering {
    transition: opacity 0.35s ease !important;
}

/* ================= flip ================= */
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

.card-inner-static {
    transition: none !important;
    transform: none !important;
}

.card-face {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 32px;
    backface-visibility: hidden;
    -webkit-backface-visibility: hidden;
    overflow: hidden;
}

/* ================= 앞면 ================= */
.card-front {
    background: linear-gradient(145deg, #ffffff 0%, #f5f0ff 60%, #ede8ff 100%);
    box-shadow: 0 20px 60px rgba(167, 139, 250, 0.25), 0 4px 16px rgba(0, 0, 0, 0.06);
}


/* ================= 뒷면 ================= */
.card-back {
    background: linear-gradient(145deg, #7c3aed 0%, #4f46e5 50%, #2563eb 100%);
    transform: rotateY(180deg);
    box-shadow: 0 20px 60px rgba(79, 70, 229, 0.4), 0 4px 16px rgba(0, 0, 0, 0.1);
}

.card-front::after {
    content: "";
    position: absolute;
    inset: 0;
    border-radius: 32px;
    box-shadow: 0 10px 0 rgba(167, 139, 250, 0.06);
    pointer-events: none;
}

/* ================= 태그 ================= */
.card-tag {
    position: absolute;
    top: 24px;
    left: 24px;
    padding: 5px 14px;
    border-radius: 99px;
    font-size: 12px;
    font-weight: 700;
    background: rgba(167, 139, 250, 0.15);
    border: 1.5px solid rgba(167, 139, 250, 0.3);
    color: #7c3aed;
    z-index: 2;
}

.tag-back {
    background: rgba(255, 255, 255, 0.15);
    border-color: rgba(255, 255, 255, 0.25);
    color: rgba(255, 255, 255, 0.9);
}

/* ================= 내용 ================= */
.card-content-wrapper {
    width: 100%;
    height: 100%;
    padding: 32px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 16px;
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
}

.word-text-back {
    color: #ffffff;
}

.phonetic-text-back {
    color: rgba(255, 255, 255, 0.6);
}

.meaning-text {
    font-size: 36px;
    font-weight: 900;
    color: #ffffff;
    margin-top: 12px;
}

.guide-text-back {
    color: rgba(255, 255, 255, 0.35);
}

/* ================= 완료 화면 ================= */
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
    cursor: pointer;
    box-shadow: 0 8px 24px rgba(124, 58, 237, 0.4);
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.reset-btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 12px 32px rgba(124, 58, 237, 0.5);
}

.next-card {
    filter: drop-shadow(0 10px 24px rgba(0, 0, 0, 0.06));
    transition: opacity 0.2s ease;
}
</style>