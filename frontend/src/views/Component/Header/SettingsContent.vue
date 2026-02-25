<template>
  <v-card class="rounded-lg pa-4 border-red box-shadow-strong">
    <v-card-title class="d-flex justify-space-between align-center border-red px-2 pb-4">
      <span class="font-weight-black text-h6">학습 설정</span>
      <v-btn icon="mdi-close" variant="text" size="small" @click="$emit('close')" />
    </v-card-title>

    <v-divider></v-divider>

    <v-card-text class="pa-0 mt-4 border-red">
      <v-list lines="two" class="bg-transparent">
        
        <v-list-item class="border-red mb-2 px-2">
          <template v-slot:prepend><v-icon color="primary">mdi-translate</v-icon></template>
          <v-list-item-title class="font-weight-bold">학습 언어</v-list-item-title>
          <template v-slot:append>
            <v-select v-model="language" :items="['한국어', '日本語', 'English']" 
              variant="outlined" density="compact" hide-details style="width: 110px"></v-select>
          </template>
        </v-list-item>

        <v-list-item class="border-red mb-2 px-2">
          <template v-slot:prepend><v-icon color="primary">mdi-theme-light-dark</v-icon></template>
          <v-list-item-title class="font-weight-bold">화면 모드</v-list-item-title>
          <template v-slot:append>
            <v-btn-toggle v-model="themeMode" mandatory color="primary" density="compact" variant="outlined">
              <v-btn value="light" icon="mdi-white-balance-sunny"></v-btn>
              <v-btn value="dark" icon="mdi-moon-waning-crescent"></v-btn>
            </v-btn-toggle>
          </template>
        </v-list-item>

        <v-list-item class="border-red mb-2 px-2">
          <template v-slot:prepend>
            <v-icon color="primary">{{ volume === 0 ? 'mdi-volume-off' : 'mdi-volume-high' }}</v-icon>
          </template>
          <v-list-item-title class="font-weight-bold mb-1">소리 크기</v-list-item-title>
          <v-slider v-model="volume" min="0" max="100" step="10" color="primary" hide-details class="mt-2"></v-slider>
          <template v-slot:append>
            <span class="text-caption ml-2" style="width: 35px">{{ volume }}%</span>
          </template>
        </v-list-item>

        <v-divider class="my-4"></v-divider>

        <v-list-item class="border-red px-2">
          <template v-slot:prepend><v-icon color="error">mdi-alert-circle-outline</v-icon></template>
          <v-list-item-title class="font-weight-bold text-error">학습 초기화</v-list-item-title>
          <template v-slot:append>
            <v-btn color="error" variant="tonal" size="small" @click="confirmReset = true">초기화</v-btn>
          </template>
        </v-list-item>

      </v-list>
    </v-card-text>

    <v-dialog v-model="confirmReset" max-width="300">
      <v-card class="rounded-lg pa-4">
        <v-card-title class="text-h6 font-weight-bold text-center">정말 비울까요?</v-card-title>
        <v-card-actions class="justify-center">
          <v-btn variant="text" @click="confirmReset = false">취소</v-btn>
          <v-btn color="error" variant="flat" @click="handleReset">초기화</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script setup>
import { ref } from 'vue'

defineEmits(['close'])

const language = ref('한국어')
const themeMode = ref('light')
const volume = ref(70)
const confirmReset = ref(false)

const handleReset = () => {
  // 초기화 로직
  language.value = '한국어'
  themeMode.value = 'light'
  volume.value = 70
  confirmReset.value = false
}
</script>

<style scoped>
.border-red { border: 1px solid red !important; }
.box-shadow-strong {
  box-shadow: 0 10px 30px rgba(0,0,0,0.3) !important;
}
</style>