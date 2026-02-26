<template>
  <div class="day-grid-wrapper pa-4">
    <div v-for="chapter in 4" :key="chapter" class="chapter-section mb-8">
      <div class="chapter-header mb-4">
        <span class="chapter-badge">Chapter {{ chapter }}</span>
        <span class="chapter-range">Day {{ (chapter - 1) * DAYS_PER_CHAPTER + 1 }} ~ {{ chapter * DAYS_PER_CHAPTER
          }}</span>
      </div>

      <div class="day-grid-container">
        <div v-for="n in DAYS_PER_CHAPTER" :key="getDay(chapter, n)" class="day-box" :class="{
                                                                                                'active': getDay(chapter, n) === currentProgress,
                                                                                                'locked': getDay(chapter, n) > currentProgress,
                                                                                                'completed': getDay(chapter, n) < currentProgress
                                                                                              }"
          @click="clickDay(getDay(chapter, n))">
          <div class="day-number">{{ getDay(chapter, n) }}</div>

          <v-icon v-if="getDay(chapter, n) < currentProgress" size="16" color="white">mdi-check-bold</v-icon>
          <div v-else-if="getDay(chapter, n) === currentProgress" class="active-dot"></div>
          <v-icon v-else size="14" color="#c4b5fd">mdi-lock</v-icon>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const router = useRouter()
const currentProgress = 2
const DAYS_PER_CHAPTER = 15

const getDay = (chapter, n) => n + (chapter - 1) * DAYS_PER_CHAPTER

const clickDay = (targetDay) => {
  if (targetDay > currentProgress) {
    alert(`🔒 ${currentProgress}단계를 먼저 완료해주세요!`)
  } else {
    router.push({ name: 'study', params: { id: targetDay } })
  }
}

onMounted(() => {
  const activeElement = document.querySelector('.day-box.active')
  if (activeElement) {
    activeElement.scrollIntoView({ behavior: 'smooth', block: 'center' })
  }
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

.day-grid-wrapper {
  max-width: 500px;
  margin: 0 auto;
  font-family: 'Nunito', sans-serif;
}

.chapter-header {
  display: flex;
  align-items: center;
  gap: 10px;
}

.chapter-badge {
  background: linear-gradient(135deg, #7c3aed, #4f46e5);
  color: white;
  font-size: 11px;
  font-weight: 900;
  padding: 4px 12px;
  border-radius: 99px;
  letter-spacing: 0.5px;
}

.chapter-range {
  font-size: 13px;
  font-weight: 700;
  color: #9ca3af;
}

.day-grid-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px;
}

@media (min-width: 600px) {
  .day-grid-container {
    grid-template-columns: repeat(5, 1fr);
    gap: 12px;
  }
}

/* 기본 박스 */
.day-box {
  background: white;
  border-radius: 20px;
  min-height: 88px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 6px;
  border: 1.5px solid rgba(167, 139, 250, 0.15);
  cursor: pointer;
  transition: transform 0.15s ease, box-shadow 0.15s ease;
  box-shadow: 0 2px 8px rgba(167, 139, 250, 0.08);
}

.day-box:active {
  transform: scale(0.93);
}

.day-number {
  font-weight: 900;
  font-size: 1.2rem;
  color: #1e1b4b;
  line-height: 1;
}

/* 완료 */
.day-box.completed {
  background: linear-gradient(135deg, #7c3aed 0%, #4f46e5 100%);
  border-color: transparent;
  box-shadow: 0 4px 16px rgba(124, 58, 237, 0.3);
}

.day-box.completed .day-number {
  color: rgba(255, 255, 255, 0.95);
}

/* 현재 진행 중 */
.day-box.active {
  background: linear-gradient(135deg, #f0e6ff 0%, #e6f0ff 100%);
  border: 2px solid #a78bfa;
  box-shadow: 0 0 0 0 rgba(167, 139, 250, 0.4);
  animation: pulse 2s infinite;
}

.day-box.active .day-number {
  color: #7c3aed;
}

.active-dot {
  width: 7px;
  height: 7px;
  background: linear-gradient(135deg, #a78bfa, #60a5fa);
  border-radius: 50%;
}

/* 잠금 */
.day-box.locked {
  background: #fafafa;
  border-color: rgba(0, 0, 0, 0.06);
  box-shadow: none;
  cursor: not-allowed;
}

.day-box.locked .day-number {
  color: #d1d5db;
}

@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 rgba(167, 139, 250, 0.45);
  }

  70% {
    box-shadow: 0 0 0 10px rgba(167, 139, 250, 0);
  }

  100% {
    box-shadow: 0 0 0 0 rgba(167, 139, 250, 0);
  }
}
</style>