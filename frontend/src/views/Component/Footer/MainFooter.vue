<template>
  <nav class="main-footer">
    <div class="footer-wrapper">

      <button v-for="tab in tabs" :key="tab.value" class="footer-btn" :class="{ active: activeTab === tab.value }"
        @click="activeTab = tab.value">
        <v-icon size="24">{{ activeTab === tab.value ? tab.iconActive : tab.icon }}</v-icon>
        <span>{{ tab.label }}</span>
      </button>

    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue'

const activeTab = ref('study')

const tabs = [
  { value: 'ranking', label: '랭킹', icon: 'mdi-trophy-outline', iconActive: 'mdi-trophy' },
  { value: 'study', label: '학습', icon: 'mdi-book-open-page-variant-outline', iconActive: 'mdi-book-open-page-variant' },
  { value: 'mypage', label: '마이', icon: 'mdi-account-circle-outline', iconActive: 'mdi-account-circle' },
]
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

.main-footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 68px;
  background: white;
  border-top: 1.5px solid rgba(167, 139, 250, 0.15);
  box-shadow: 0 -4px 20px rgba(124, 58, 237, 0.08);
  z-index: 100;
  font-family: 'Nunito', sans-serif;
}

.footer-wrapper {
  max-width: 500px;
  margin: 0 auto;
  height: 100%;
  display: flex;
  align-items: center;
}

.footer-btn {
  flex: 1;
  height: 100%;
  border: none;
  background: transparent;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 3px;
  cursor: pointer;
  transition: transform 0.15s ease;
  color: #d1d5db;
}

.footer-btn span {
  font-size: 11px;
  font-weight: 700;
}

.footer-btn.active {
  color: #7c3aed;
}

.footer-btn:active {
  transform: scale(0.9);
}
</style>