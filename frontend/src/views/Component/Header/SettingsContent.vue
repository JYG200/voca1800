<template>
  <div class="settings-card">

    <div class="settings-header">
      <span class="settings-title">학습 설정</span>
      <button class="close-btn" @click="$emit('close')">
        <v-icon size="20" color="#7c3aed">mdi-close</v-icon>
      </button>
    </div>

    <div class="settings-divider"></div>

    <div class="settings-body">

      <!-- 학습 언어 -->
      <div class="settings-row">
        <div class="row-left">
          <div class="row-icon-wrap">
            <v-icon size="18" color="#7c3aed">mdi-translate</v-icon>
          </div>
          <span class="row-label">학습 언어</span>
        </div>

        <!-- ✅ 커스텀 드롭다운 -->
        <div class="custom-dropdown" @click="langOpen = !langOpen">
          <span>{{ language }}</span>
          <v-icon size="14" color="#7c3aed">{{ langOpen ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          <div v-if="langOpen" class="dropdown-menu">
            <div v-for="item in ['한국어', '日本語', 'English']" :key="item" class="dropdown-item"
              :class="{ selected: language === item }" @click.stop="language = item; langOpen = false">
              {{ item }}
            </div>
          </div>
        </div>
      </div>

      <!-- 화면 모드 -->
      <div class="settings-row">
        <div class="row-left">
          <div class="row-icon-wrap">
            <v-icon size="18" color="#7c3aed">mdi-theme-light-dark</v-icon>
          </div>
          <span class="row-label">화면 모드</span>
        </div>
        <div class="toggle-group">
          <button class="toggle-btn" :class="{ active: themeMode === 'light' }" @click="themeMode = 'light'">
            <v-icon size="16">mdi-white-balance-sunny</v-icon>
          </button>
          <button class="toggle-btn" :class="{ active: themeMode === 'dark' }" @click="themeMode = 'dark'">
            <v-icon size="16">mdi-moon-waning-crescent</v-icon>
          </button>
        </div>
      </div>

      <!-- 소리 크기 -->
      <div class="settings-row-col">
        <div class="row-left">
          <div class="row-icon-wrap">
            <v-icon size="18" color="#7c3aed" style="cursor: pointer" @click="volume = volume === 0 ? 70 : 0">
              {{ volume === 0 ? 'mdi-volume-off' : 'mdi-volume-high' }}
            </v-icon>
          </div>
          <span class="row-label">소리 크기</span>
          <span class="volume-value">{{ volume }}%</span>
        </div>
        <v-slider v-model="volume" min="0" max="100" step="10" color="#7c3aed" hide-details class="mt-2" />
      </div>

      <div class="settings-divider"></div>

      <!-- 초기화 -->
      <div class="settings-row">
        <div class="row-left">
          <div class="row-icon-wrap danger">
            <v-icon size="18" color="#ef4444">mdi-alert-circle-outline</v-icon>
          </div>
          <span class="row-label danger-label">학습 초기화</span>
        </div>
        <button class="danger-btn" @click="confirmReset = true">초기화</button>
      </div>

    </div>

    <!-- 초기화 확인 다이얼로그 -->
    <div v-if="confirmReset" class="confirm-overlay">
      <div class="confirm-card">
        <p class="confirm-title">정말 초기화할까요?</p>
        <p class="confirm-sub">모든 학습 진도가 사라져요</p>
        <div class="confirm-actions">
          <button class="confirm-cancel" @click="confirmReset = false">취소</button>
          <button class="confirm-ok" @click="handleReset">초기화</button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue'

defineEmits(['close'])

const language = ref('한국어')
const langOpen = ref(false)
const themeMode = ref('light')
const volume = ref(70)
const confirmReset = ref(false)

const handleReset = () => {
  language.value = '한국어'
  themeMode.value = 'light'
  volume.value = 70
  confirmReset.value = false
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

.confirm-overlay {
  position: absolute;
  inset: 0;
  background: rgba(255, 255, 255, 0.92);
  backdrop-filter: blur(4px);
  border-radius: 28px;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
  padding: 24px;
}

.settings-card {
  position: relative;
  background: white;
  border-radius: 28px;
  padding: 24px 20px 36px;
  font-family: 'Nunito', sans-serif;
}

.settings-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.settings-title {
  font-size: 18px;
  font-weight: 900;
  color: #1e1b4b;
}

.close-btn {
  width: 32px;
  height: 32px;
  border: none;
  background: rgba(167, 139, 250, 0.12);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.settings-divider {
  height: 1.5px;
  background: rgba(167, 139, 250, 0.15);
  margin: 12px 0;
}

.settings-body {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.settings-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px 4px;
}

.settings-row-col {
  padding: 12px 4px;
}

.row-left {
  display: flex;
  align-items: center;
  gap: 10px;
}

.row-icon-wrap {
  width: 34px;
  height: 34px;
  background: rgba(167, 139, 250, 0.12);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.row-icon-wrap.danger {
  background: rgba(239, 68, 68, 0.08);
}

.row-label {
  font-size: 15px;
  font-weight: 700;
  color: #1e1b4b;
}

.danger-label {
  color: #ef4444;
}

.volume-value {
  font-size: 13px;
  font-weight: 700;
  color: #a78bfa;
}

/* ✅ 커스텀 드롭다운 */
.custom-dropdown {
  position: relative;
  width: 110px;
  padding: 8px 12px;
  font-family: 'Nunito', sans-serif;
  font-size: 13px;
  font-weight: 700;
  color: #1e1b4b;
  background: rgba(167, 139, 250, 0.1);
  border: 1.5px solid rgba(167, 139, 250, 0.3);
  border-radius: 12px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 4px;
  user-select: none;
}

.dropdown-menu {
  position: absolute;
  top: calc(100% + 6px);
  right: 0;
  width: 100%;
  background: white;
  border: 1.5px solid rgba(167, 139, 250, 0.3);
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 8px 24px rgba(124, 58, 237, 0.15);
  z-index: 100;
}

.dropdown-item {
  padding: 10px 12px;
  font-size: 13px;
  font-weight: 700;
  color: #6b7280;
  cursor: pointer;
  transition: background 0.15s ease;
}

.dropdown-item:hover {
  background: rgba(167, 139, 250, 0.08);
  color: #1e1b4b;
}

.dropdown-item.selected {
  color: #7c3aed;
  background: rgba(167, 139, 250, 0.1);
}

/* 토글 버튼 */
.toggle-group {
  display: flex;
  gap: 6px;
  background: rgba(167, 139, 250, 0.1);
  border-radius: 12px;
  padding: 4px;
}

.toggle-btn {
  width: 36px;
  height: 32px;
  border: none;
  background: transparent;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #9ca3af;
  transition: all 0.2s ease;
}

.toggle-btn.active {
  background: linear-gradient(135deg, #7c3aed, #4f46e5);
  color: white;
  box-shadow: 0 2px 8px rgba(124, 58, 237, 0.35);
}

/* 초기화 버튼 */
.danger-btn {
  padding: 8px 18px;
  background: rgba(239, 68, 68, 0.08);
  border: 1.5px solid rgba(239, 68, 68, 0.25);
  border-radius: 99px;
  color: #ef4444;
  font-size: 13px;
  font-weight: 700;
  font-family: 'Nunito', sans-serif;
  cursor: pointer;
  transition: all 0.2s ease;
}

.danger-btn:active {
  background: rgba(239, 68, 68, 0.15);
}

/* 확인 다이얼로그 */
.confirm-card {
  background: white;
  border-radius: 24px;
  padding: 28px 24px;
  text-align: center;
  font-family: 'Nunito', sans-serif;
}

.confirm-title {
  font-size: 17px;
  font-weight: 900;
  color: #1e1b4b;
  margin-bottom: 6px;
}

.confirm-sub {
  font-size: 13px;
  color: #9ca3af;
  margin-bottom: 24px;
}

.confirm-actions {
  display: flex;
  gap: 10px;
  justify-content: center;
}

.confirm-cancel {
  flex: 1;
  padding: 12px;
  border: 1.5px solid rgba(167, 139, 250, 0.3);
  background: transparent;
  border-radius: 14px;
  font-size: 14px;
  font-weight: 700;
  color: #7c6aaa;
  font-family: 'Nunito', sans-serif;
  cursor: pointer;
}

.confirm-ok {
  flex: 1;
  padding: 12px;
  border: none;
  background: linear-gradient(135deg, #ef4444, #dc2626);
  border-radius: 14px;
  font-size: 14px;
  font-weight: 700;
  color: white;
  font-family: 'Nunito', sans-serif;
  cursor: pointer;
  box-shadow: 0 4px 12px rgba(239, 68, 68, 0.35);
}
</style>