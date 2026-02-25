<template>
  <div class="day-grid-wrapper pa-4">
    <div v-for="chapter in 4" :key="chapter" class="chapter-section mb-8">
      <h3 class="chapter-title mb-4">
        {{ (chapter - 1) * DAYS_PER_CHAPTER + 1 }} ~ {{ chapter * DAYS_PER_CHAPTER }} 단계
      </h3>

      <div class="day-grid-container">
        <div v-for="n in DAYS_PER_CHAPTER" :key="getDay(chapter, n)" class="day-box" :class="{
          'active': getDay(chapter, n) === currentProgress,
          'locked': getDay(chapter, n) > currentProgress,
          'completed': getDay(chapter, n) < currentProgress
        }" @click="clickDay(getDay(chapter, n))">
          
          <div class="day-number">{{ getDay(chapter, n) }}</div>

          <v-icon v-if="getDay(chapter, n) < currentProgress" size="18" color="success">mdi-check-bold</v-icon>
          <div v-else-if="getDay(chapter, n) === currentProgress" class="active-dot"></div>
          <v-icon v-else size="16" color="grey-darken-1">mdi-lock</v-icon>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const router = useRouter()

// 1. 상태 (나중에 DB나 Pinia에서 받아올 유저의 현재 진도)
const currentProgress = 2; 

// 2. 레이아웃 설정값 (한 챕터당 며칠씩 보여줄 것인가?)
const DAYS_PER_CHAPTER = 15; 

// 3. 날짜 계산 함수
const getDay = (chapter, n) => n + (chapter - 1) * DAYS_PER_CHAPTER;

const clickDay = (targetDay) => {
  if (targetDay > currentProgress) {
    alert(`🔒 ${currentProgress}단계를 먼저 완료해주세요!`);
  } else {
    // 정상 이동 (1~15)
    router.push({ name: 'study', params: { id: targetDay } });
  }
}

onMounted(() => {
  // 화면이 켜지면 현재 학습 중인 곳으로 부드럽게 스크롤
  const activeElement = document.querySelector('.day-box.active');
  if (activeElement) {
    activeElement.scrollIntoView({ behavior: 'smooth', block: 'center' });
  }
})
</script>

<style scoped>

.day-grid-wrapper {
  max-width: 500px; /* 화면이 아무리 넓어도 600px 이상 늘어나지 않음 */
  margin: 0 auto;   /* 넓은 화면에서 가운데 정렬 */
}

.day-grid-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
}

@media (min-width: 600px) {
  .day-grid-container {
    grid-template-columns: repeat(5, 1fr);
    gap: 16px;
  }
}

@media (max-width: 350px) {
  .day-grid-container {
    grid-template-columns: repeat(3, 1fr);
    gap: 8px;
  }
  .day-box {
    min-height: 80px;
  }
}

.chapter-title {
  font-size: 1rem;
  color: #546E7A;
  font-weight: 700;
  border-left: 4px solid #1976D2;
  padding-left: 10px;
}

.day-box {
  background-color: #ffffff;
  border-radius: 16px;
  min-height: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border: 1px solid #e0e0e0;
  cursor: pointer;
  transition: all 0.2s ease;
  /* 💡 중복되던 color 속성 제거됨 */
}

.day-box:active {
  transform: scale(0.95);
  background-color: #eeeeee;
}

.day-number {
  font-weight: 900;
  font-size: 1.3rem;
  margin-bottom: 2px;
  color: #263238 !important;
}

/* 🎯 상태별 스타일 모음 (활성화/잠금/완료) */
.day-box.active {
  border: 2px solid #1976D2;
  background-color: #E3F2FD;
  color: #1976D2 !important; 
  animation: pulse 2s infinite;
}

/* active 상태의 숫자 색상도 파란색으로 통일 */
.day-box.active .day-number {
  color: #1976D2 !important;
}

.day-box.locked .day-number {
  color: #9e9e9e !important; /* 잠긴 숫자는 조금 더 연한 회색으로 */
}

.day-box.completed .day-number {
  color: #2E7D32 !important;
}

.active-dot {
  width: 8px;
  height: 8px;
  background-color: #1976D2;
  border-radius: 50%;
}

@keyframes pulse {
  0% { box-shadow: 0 0 0 0 rgba(25, 118, 210, 0.4); }
  70% { box-shadow: 0 0 0 10px rgba(25, 118, 210, 0); }
  100% { box-shadow: 0 0 0 0 rgba(25, 118, 210, 0); }
}
</style>