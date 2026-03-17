<template>
  <section class="edit-profile-page">

    <div class="header">
      <button class="back-btn" @click="router.back()">←</button>
      <span class="header-title">프로필 편집</span>
      <button class="save-btn" :disabled="!isDirty" @click="saveProfile">저장</button>
    </div>

    <div class="avatar-section">
      <div class="avatar-fallback" :style="{ background: form.avatarBg }">
        {{ form.name.slice(0, 1).toUpperCase() || '?' }}
      </div>
      <button class="change-avatar-btn" @click="cycleAvatar">아바타 변경</button>
    </div>

    <div class="form-list">
      <div class="form-item">
        <label class="form-label">이름</label>
        <input class="form-input" v-model="form.name" maxlength="20" placeholder="이름을 입력하세요" />
      </div>
      <div class="form-item">
        <label class="form-label">한 줄 소개</label>
        <input class="form-input" v-model="form.bio" maxlength="40" placeholder="나를 한 줄로 표현해보세요" />
      </div>
    </div>

    <transition name="toast">
      <div v-if="showToast" class="toast">✅ 저장되었어요!</div>
    </transition>

  </section>
</template>

<script setup>
import { reactive, computed, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route  = useRoute()

const avatarBgs = [
  'linear-gradient(135deg, #7c3aed, #2563eb)',
  'linear-gradient(135deg, #ec4899, #f97316)',
  'linear-gradient(135deg, #10b981, #22c55e)',
  'linear-gradient(135deg, #0ea5e9, #6366f1)',
  'linear-gradient(135deg, #f59e0b, #ef4444)',
  'linear-gradient(135deg, #a855f7, #ec4899)',
  'linear-gradient(135deg, #14b8a6, #0ea5e9)',
]

// ✅ MyPage에서 넘겨준 query로 초기값 세팅
const form = reactive({
  name:     route.query.name     ?? 'Yonggyu',
  bio:      route.query.bio      ?? '매일 조금씩, 꾸준히 🌱',
  avatarBg: route.query.avatarBg ? decodeURIComponent(route.query.avatarBg)
                                 : 'linear-gradient(135deg, #7c3aed, #2563eb)',
})

const snapshot = { name: form.name, bio: form.bio, avatarBg: form.avatarBg }

const isDirty = computed(() =>
  Object.keys(snapshot).some(k => form[k] !== snapshot[k])
)

function cycleAvatar() {
  const cur = avatarBgs.indexOf(form.avatarBg)
  form.avatarBg = avatarBgs[(cur + 1) % avatarBgs.length]
}

const showToast = ref(false)

function saveProfile() {
  if (!form.name.trim()) { alert('이름을 입력해주세요.'); return }

  showToast.value = true
  setTimeout(() => {
    showToast.value = false
    // ✅ 저장 결과를 query에 담아서 MyPage로 복귀
    router.push({
      name: 'mypage',
      query: {
        updatedName:     form.name,
        updatedBio:      form.bio,
        updatedAvatarBg: encodeURIComponent(form.avatarBg),
      }
    })
  }, 1000)
}
</script>

<style scoped>
/* FollowPage와 동일한 glassmorphism 스타일 */
.edit-profile-page {
  display: flex;
  flex-direction: column;
  gap: 16px;
  position: relative;
}

.header {
  display: flex;
  align-items: center;
  background: rgba(255, 255, 255, 0.65);
  border: 1px solid rgba(30, 27, 75, 0.08);
  border-radius: 16px;
  padding: 10px 14px;
  backdrop-filter: blur(10px);
}

.back-btn {
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #1e1b4b;
  padding: 4px 8px 4px 0;
  flex-shrink: 0;
}

.header-title {
  flex: 1;
  font-size: 16px;
  font-weight: 800;
  color: #1e1b4b;
  text-align: center;
}

.save-btn {
  background: linear-gradient(135deg, #7c3aed, #2563eb);
  color: #fff;
  border: none;
  border-radius: 12px;
  padding: 7px 16px;
  font-size: 13px;
  font-weight: 800;
  cursor: pointer;
  flex-shrink: 0;
  box-shadow: 0 2px 8px rgba(124, 58, 237, 0.25);
  transition: opacity 0.15s;
}

.save-btn:disabled {
  background: rgba(30, 27, 75, 0.10);
  color: rgba(30, 27, 75, 0.35);
  box-shadow: none;
  cursor: default;
}

.avatar-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  padding: 24px 20px;
  background: rgba(255, 255, 255, 0.65);
  border: 1px solid rgba(30, 27, 75, 0.08);
  border-radius: 24px;
  backdrop-filter: blur(10px);
}

.avatar-fallback {
  width: 80px;
  height: 80px;
  border-radius: 22px;
  display: grid;
  place-items: center;
  font-size: 32px;
  font-weight: 900;
  color: rgba(255, 255, 255, 0.95);
  box-shadow: 0 4px 16px rgba(124, 58, 237, 0.20);
}

.change-avatar-btn {
  background: rgba(124, 58, 237, 0.08);
  color: #7c3aed;
  border: 1px solid rgba(124, 58, 237, 0.20);
  border-radius: 12px;
  padding: 7px 18px;
  font-size: 13px;
  font-weight: 700;
  cursor: pointer;
}

.form-list {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-item {
  background: rgba(255, 255, 255, 0.75);
  border: 1px solid rgba(30, 27, 75, 0.08);
  border-radius: 20px;
  padding: 14px 16px;
  display: flex;
  flex-direction: column;
  gap: 6px;
  backdrop-filter: blur(12px);
}

.form-label {
  font-size: 11px;
  font-weight: 800;
  color: rgba(30, 27, 75, 0.40);
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.form-input {
  border: none;
  background: none;
  outline: none;
  font-size: 15px;
  font-weight: 600;
  color: #1e1b4b;
  width: 100%;
}

.form-input::placeholder { color: rgba(30, 27, 75, 0.30); }

.toast {
  position: fixed;
  bottom: 80px;
  left: 50%;
  transform: translateX(-50%);
  background: #1e1b4b;
  color: #fff;
  border-radius: 16px;
  padding: 12px 24px;
  font-size: 14px;
  font-weight: 700;
  box-shadow: 0 4px 20px rgba(0,0,0,0.20);
  white-space: nowrap;
  z-index: 999;
}

.toast-enter-active, .toast-leave-active { transition: opacity 0.25s, transform 0.25s; }
.toast-enter-from, .toast-leave-to { opacity: 0; transform: translateX(-50%) translateY(10px); }
</style>