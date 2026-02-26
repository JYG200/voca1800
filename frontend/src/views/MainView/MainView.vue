<template>
  <v-app>
    <MainHeader @open-settings="isSettingsOpen = true" />
    
    <v-main id="main-scroll" class="main-scroll-area">
      <div class="content-limit-wrapper">
        <DayBoard />
      </div>
    </v-main>

    <MainFooter />

    <v-dialog v-model="isSettingsOpen" max-width="420">
      <SettingsContent @close="isSettingsOpen = false" />
    </v-dialog>

  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import MainHeader from '@/views/Component/Header/MainHeader.vue'
import DayBoard from '@/views/Component/Main/DayBoard.vue'
import MainFooter from '@/views/Component/Footer/MainFooter.vue'
import SettingsContent from '@/views/Component/Header/SettingsContent.vue' 

const isSettingsOpen = ref(false) 
</script>

<style scoped>
.main-scroll-area {
  height: calc(100vh - 64px - 56px); /* 헤더 높이 - 푸터 높이 */
  overflow-y: auto;
  background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 50%, #f0f7ff 100%);
  -webkit-overflow-scrolling: touch;
}

.content-limit-wrapper {
  max-width: 500px;
  margin: 0 auto;
  padding-bottom: 32px;
}

/* 스크롤바 완전히 숨기기 */
.main-scroll-area::-webkit-scrollbar {
  display: none;
}

.main-scroll-area {
  -ms-overflow-style: none;  /* IE, Edge */
  scrollbar-width: none;     /* Firefox */
}
</style>