<template>
  <header class="main-header">
    <div class="header-wrapper">

      <div class="header-side">
        <BaseButton :icon="true" prepend-icon="mdi-cog-outline" color="#7c3aed" variant="text"
          @click="$emit('open-settings')" />
      </div>

      <div class="header-center">
        <span class="header-sub">나의 진도</span>
        <span class="header-title">Day {{ currentProgress }} 🔥</span>
      </div>

      <div class="header-side text-right">
        <BaseButton :icon="true" prepend-icon="mdi-bell-outline" color="#7c3aed" variant="text" />
      </div>

    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'
import BaseButton from '@/views/Component/Common/BaseButton.vue'

defineEmits(['open-settings'])  
const currentProgress = ref(2)
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

.main-header {
  width: 100%;
  height: 68px;
  flex-shrink: 0;
  background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 100%);
  border-bottom: 1.5px solid rgba(167, 139, 250, 0.2);
  font-family: 'Nunito', sans-serif;
  position: sticky;
  top: 0;
  z-index: 100;
}

.header-wrapper {
  max-width: 500px;
  margin: 0 auto;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
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
  gap: 1px;
}

.header-sub {
  font-size: 11px;
  font-weight: 700;
  color: #a78bfa;
  letter-spacing: 0.5px;
}

.header-title {
  font-size: 18px;
  font-weight: 900;
  color: #1e1b4b;
  letter-spacing: -0.3px;
}

/* ✅ icon-btn 관련 CSS 전부 삭제 - BaseButton이 담당 */
</style>