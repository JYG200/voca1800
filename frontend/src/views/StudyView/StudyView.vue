<template>
  <v-app>
    <v-main class="bg-grey-lighten-5">
      <div id="study-layout" class="mobile-container">

        <StudyHeader :unitId="$route.params.id" :total="totalWords" :remaining="remainingWords" />

        <StudyMain class="study-main-area" @update-progress="handleProgress" />

      </div>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import StudyHeader from '@/views/Component/Header/StudyHeader.vue'
import StudyMain from '@/views/Component/Main/StudyMain.vue'

// 진행 상황을 담을 변수
const totalWords = ref(0)
const remainingWords = ref(0)

// Main에서 데이터가 올라오면 변수 업데이트
const handleProgress = (data) => {
  totalWords.value = data.total
  remainingWords.value = data.remaining
}
</script>

<style scoped>
.study-main-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  /* overflow: hidden; 을 잠시 지우거나 visible로 바꿔보세요 */
  height: calc(100vh - 80px);
  /* 헤더 높이를 제외한 명확한 높이 부여 */
}

.mobile-container {
  max-width: 500px;
  margin: 0 auto;
  height: 100vh;
  display: flex;
  flex-direction: column;
  /* 세로로 차곡차곡 쌓임 */
  background-color: white;
  position: relative;
  /* 나중에 화면 위에 애니메이션 띄우기 좋게 설정 */
}

/* 🌟 핵심: 헤더가 차지하고 남은 아래쪽 화면을 꽉 채우게 만듭니다 */
.study-main-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  /* 카드가 화면 밖으로 삐져나가지 않게 꽉 잡아줌 */
}
</style>