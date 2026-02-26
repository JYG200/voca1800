<template>
  <v-app>
    <v-main class="study-scroll-area">
      <!-- 헤더만 500px 제한 -->
      <div class="header-wrapper">
        <StudyHeader :unitId="$route.params.id" :total="totalWords" :remaining="remainingWords" />
      </div>
      <!-- StudyMain은 전체 너비 -->
      <StudyMain @update-progress="handleProgress" />
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import StudyHeader from '@/views/Component/Header/StudyHeader.vue'
import StudyMain from '@/views/Component/Main/StudyMain.vue'

const totalWords = ref(0)
const remainingWords = ref(0)

const handleProgress = (data) => {
  totalWords.value = data.total
  remainingWords.value = data.remaining
}
</script>

<style scoped>
.study-scroll-area {
  height: 100vh;
  overflow: hidden;
  background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 50%, #e6fff6 100%);
  display: flex;
  flex-direction: column;
}

.header-wrapper {
  width: 100%;
  flex-shrink: 0;
}
</style>