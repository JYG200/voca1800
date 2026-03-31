<template>
  <nav class="main-footer">
    <div class="footer-wrapper">

      <button v-for="tab in tabs" :key="tab.value" class="footer-btn" :class="{ active: activeTab === tab.value }"
        @click="moveTo(tab.value)">
        <v-icon size="20">{{ activeTab === tab.value ? tab.iconActive : tab.icon }}</v-icon>
        <span>{{ tab.label }}</span>
      </button>

    </div>
  </nav>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const activeTab = computed(() => {
  if (route.name === 'study') return 'main'
  return route.name
})

const tabs = [
  { value: 'ranking', label: '랭킹', icon: 'mdi-trophy-outline', iconActive: 'mdi-trophy' },
  { value: 'main', label: '학습', icon: 'mdi-book-open-page-variant-outline', iconActive: 'mdi-book-open-page-variant' },
  { value: 'mypage', label: '마이', icon: 'mdi-account-circle-outline', iconActive: 'mdi-account-circle' },
]

const moveTo = (value) => {
  router.push({ name: value })
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

.main-footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 56px;
  padding-bottom: max(0px, env(safe-area-inset-bottom));
  background: white;
  border-top: 1.5px solid rgba(167, 139, 250, 0.15);
  box-shadow: 0 -4px 20px rgba(124, 58, 237, 0.08);
  z-index: 100;
  font-family: 'Nunito', sans-serif;
  box-sizing: border-box;
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
  gap: 2px;
  cursor: pointer;
  transition: transform 0.15s ease;
  color: #d1d5db;
}

.footer-btn span {
  font-size: 10px;
  font-weight: 700;
}

.footer-btn.active {
  color: #7c3aed;
}

.footer-btn:active {
  transform: scale(0.9);
}
</style>