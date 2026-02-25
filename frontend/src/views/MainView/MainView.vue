<template>
  <v-app shadow>
    <MainHeader />

    <v-main id="main-scroll" class="main-scroll-area">
      <div class="content-limit-wrapper">
        <DayBoard />
      </div>
    </v-main>

    <MainFooter />
  </v-app>
</template>

<script setup>
import MainHeader from '@/views/Component/Header/MainHeader.vue'
import DayBoard from '@/views/Component/Main/DayBoard.vue'
import MainFooter from '@/views/Component/Footer/MainFooter.vue'
</script>

<style scoped>
/* 🌟 2. calc 없이 깔끔하게 100vh로 가둡니다 */
.main-scroll-area {
  height: 100vh; 
  overflow-y: auto; /* 내부 스크롤 부활! */
  background-color: #f8f9fa;
  -webkit-overflow-scrolling: touch;
}

.content-limit-wrapper {
  max-width: 500px;
  margin: 0 auto;
  padding-bottom: 20px; /* 푸터 여백은 Vuetify가 알아서 하니 살짝만 줍니다 */
}

/* 내부 스크롤바 커스텀 부활 */
.main-scroll-area::-webkit-scrollbar {
  width: 4px;
}
.main-scroll-area::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}
</style>