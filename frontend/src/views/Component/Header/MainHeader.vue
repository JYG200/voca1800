<template>
    <v-app-bar id="main-header" color="white" elevation="1" height="80" :scroll-behavior="isMobile ? 'hide' : undefined"  scroll-target="#main-scroll" scroll-threshold="50">
      <div class="header-content-container">
        <div class="header-wrapper">
          <div class="header-side border-red">
            <BaseButton icon prepend-icon="mdi-menu" variant="text" color="grey-darken-2" />
          </div>

          <div class="header-center border-red">
            <span class="progress-label text-caption grey--text">나의 진도</span>
            <span class="progress-status font-weight-black">Day {{ currentProgress }} 🔥</span>
          </div>

          <div class="header-side text-right border-red">
            <BaseButton icon prepend-icon="mdi-cog" variant="text" color="grey-darken-2"
              @click="isSettingsOpen = true" />
          </div>
        </div>
      </div>

      <v-dialog v-model="isSettingsOpen" max-width="500" location="bottom" transition="dialog-bottom-transition">
        <SettingsContent @close="isSettingsOpen = false" />
      </v-dialog>
    </v-app-bar>
</template>

<script setup>
import { ref } from 'vue'
import { useDisplay } from 'vuetify'
import BaseButton from '@/views/Component/Common/BaseButton.vue'
import SettingsContent from './SettingsContent.vue'

const { mobile: isMobile } = useDisplay()
const isSettingsOpen = ref(false)

// 🌟 현재 진도를 나타내는 반응형 변수 추가 (기본값 15로 설정)
const currentProgress = ref(2)

</script>

<style scoped>
.header-content-container {
  width: 100%;
  max-width: 500px;
  margin: 0 auto;
  height: 100%;
}

.header-wrapper {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 100%;
  padding: 0 16px;
}

.header-side {
  flex: 1;
  display: flex;
  align-items: center;
}

.header-side.text-right {
  justify-content: flex-end;
}

.header-center {
  flex: 2;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.border-red {
  border: 1px solid red !important;
}
</style>