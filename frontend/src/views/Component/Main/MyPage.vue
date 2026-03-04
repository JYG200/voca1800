<template>
  <section class="mypage">

    <!-- ───── 프로필 카드 ───── -->
    <div class="profile-card">
      <div class="profile-top">
        <div class="avatar-wrap">
          <img v-if="user.avatarUrl" class="avatar-img" :src="user.avatarUrl" :alt="user.name" />
          <div v-else class="avatar-fallback" :style="{ background: user.avatarBg }">
            {{ user.name.slice(0, 1).toUpperCase() }}
          </div>
          <div class="avatar-ring" />
        </div>

        <div class="profile-info">
          <div class="profile-name">{{ user.name }}</div>
          <div class="profile-bio">{{ user.bio }}</div>
        </div>
      </div>

      <!-- 팔로워/팔로잉 -->
      <div class="social-row">
        <button class="social-btn" @click="onGoFollowers">
          <span class="social-num">{{ user.followers }}</span>
          <span class="social-label">팔로워</span>
        </button>
        <div class="social-divider" />
        <button class="social-btn" @click="onGoFollowing">
          <span class="social-num">{{ user.following }}</span>
          <span class="social-label">팔로잉</span>
        </button>
      </div>
    </div>

    <!-- ───── 학습 통계 ───── -->
    <div class="section-card">
      <div class="section-title">📊 학습 통계</div>
      <div class="stat-progress">
        <div class="stat-header">
          <span class="stat-label">외운 단어</span>
          <span class="word-count">
            <span class="word-num">{{ user.memorizedWords }}</span>
            <span class="word-total"> / {{ user.totalWords }}</span>
          </span>
        </div>
        <div class="bar-track">
          <div class="bar-fill" :style="{ width: (user.memorizedWords / user.totalWords * 100) + '%' }" />
        </div>
      </div>
    </div>

    <!-- ───── 프로필 수정 / 문의 / 로그아웃 ───── -->
    <div class="section-card nav-card">
      <button class="nav-row" @click="onEditProfile">
        <span class="nav-icon">✏️</span>
        <span class="nav-text">프로필 수정</span>
        <span class="nav-arrow">›</span>
      </button>
      <div class="nav-divider" />
      <button class="nav-row" @click="onContact">
        <span class="nav-icon">💬</span>
        <span class="nav-text">문의하기</span>
        <span class="nav-arrow">›</span>
      </button>
      <div class="nav-divider" />
      <button class="nav-row logout" @click="onLogout">
        <span class="nav-icon">🚪</span>
        <span class="nav-text">로그아웃</span>
        <span class="nav-arrow">›</span>
      </button>
    </div>

    <!-- ───── 앱 버전 ───── -->
    <div class="version-row">v1.0.0</div>

  </section>
</template>

<script setup>
import { ref } from 'vue'

const user = ref({
  name: 'Yonggyu',
  bio: '매일 조금씩, 꾸준히 🌱',
  avatarUrl: '',
  avatarBg: 'linear-gradient(135deg, #7c3aed, #2563eb)',
  followers: 24,
  following: 12,
  memorizedWords: 364,
  totalWords: 1800,
})

function onGoFollowers() { alert('팔로워 목록으로 이동') }
function onGoFollowing() { alert('팔로잉 목록으로 이동') }
function onEditProfile() { alert('프로필 수정으로 이동') }
function onContact()     { alert('문의하기로 이동') }
function onLogout()      { alert('로그아웃') }
</script>

<style scoped>
/* ───── Layout ───── */
.mypage {
  display: flex;
  flex-direction: column;
  gap: 14px;
  padding-bottom: 24px;
}

/* ───── 공통 카드 ───── */
.section-card {
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.75);
  border: 1px solid rgba(30, 27, 75, 0.08);
  backdrop-filter: blur(12px);
  padding: 20px;
}

.section-title {
  font-size: 14px;
  font-weight: 800;
  color: rgba(30, 27, 75, 0.55);
  margin-bottom: 16px;
  letter-spacing: 0.3px;
}

/* ───── 프로필 카드 ───── */
.profile-card {
  border-radius: 24px;
  background: linear-gradient(135deg, rgba(124, 58, 237, 0.10), rgba(37, 99, 235, 0.08));
  border: 1.5px solid rgba(124, 58, 237, 0.22);
  backdrop-filter: blur(16px);
  padding: 22px 20px 0;
  overflow: hidden;
}

.profile-top {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 20px;
}

.avatar-wrap {
  position: relative;
  flex-shrink: 0;
}

.avatar-img,
.avatar-fallback {
  width: 68px;
  height: 68px;
  border-radius: 20px;
  display: grid;
  place-items: center;
  font-size: 26px;
  font-weight: 900;
  color: rgba(255, 255, 255, 0.95);
  object-fit: cover;
}

.avatar-ring {
  position: absolute;
  inset: -3px;
  border-radius: 23px;
  border: 2.5px solid transparent;
  background: linear-gradient(135deg, #7c3aed, #2563eb) border-box;
  -webkit-mask: linear-gradient(#fff 0 0) padding-box, linear-gradient(#fff 0 0);
  -webkit-mask-composite: destination-out;
  mask-composite: exclude;
  pointer-events: none;
}

.profile-info { min-width: 0; }

.profile-name {
  font-size: 20px;
  font-weight: 900;
  color: #1e1b4b;
  letter-spacing: -0.3px;
}

.profile-bio {
  font-size: 13px;
  color: rgba(30, 27, 75, 0.55);
  margin-top: 4px;
}

.social-row {
  display: flex;
  border-top: 1px solid rgba(30, 27, 75, 0.08);
}

.social-btn {
  flex: 1;
  background: none;
  border: none;
  padding: 14px 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3px;
  cursor: pointer;
  transition: background 0.15s;
  border-radius: 0 0 0 24px;
}
.social-btn:last-child { border-radius: 0 0 24px 0; }
.social-btn:active { background: rgba(124, 58, 237, 0.06); }

.social-num {
  font-size: 20px;
  font-weight: 900;
  color: #1e1b4b;
  line-height: 1;
}

.social-label {
  font-size: 11px;
  color: rgba(30, 27, 75, 0.50);
  font-weight: 600;
}

.social-divider {
  width: 1px;
  background: rgba(30, 27, 75, 0.08);
  margin: 10px 0;
}

/* ───── 학습 통계 ───── */
.stat-progress { margin-bottom: 0; }

.stat-header {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  margin-bottom: 8px;
}

.stat-label {
  font-size: 13px;
  font-weight: 700;
  color: rgba(30, 27, 75, 0.60);
}

.word-count { line-height: 1; }

.word-num {
  font-size: 28px;
  font-weight: 900;
  color: #1e1b4b;
}

.word-total {
  font-size: 14px;
  font-weight: 700;
  color: rgba(30, 27, 75, 0.40);
}

.bar-track {
  height: 8px;
  background: rgba(30, 27, 75, 0.07);
  border-radius: 999px;
  overflow: hidden;
}

.bar-fill {
  height: 100%;
  border-radius: 999px;
  background: linear-gradient(90deg, #7c3aed, #2563eb);
  transition: width 0.6s cubic-bezier(0.34, 1.56, 0.64, 1);
}

/* ───── 네비 카드 ───── */
.nav-card { padding: 6px 8px; }

.nav-row {
  width: 100%;
  background: none;
  border: none;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 12px;
  border-radius: 16px;
  cursor: pointer;
  transition: background 0.15s;
  text-align: left;
}
.nav-row:active { background: rgba(30, 27, 75, 0.05); }
.nav-row.logout .nav-text { color: #ef4444; }

.nav-icon { font-size: 20px; flex-shrink: 0; }

.nav-text {
  flex: 1;
  font-size: 15px;
  font-weight: 700;
  color: #1e1b4b;
}

.nav-arrow {
  font-size: 20px;
  color: rgba(30, 27, 75, 0.25);
  font-weight: 300;
  line-height: 1;
}

.nav-divider {
  height: 1px;
  background: rgba(30, 27, 75, 0.06);
  margin: 0 12px;
}

/* ───── 앱 버전 ───── */
.version-row {
  text-align: center;
  font-size: 12px;
  font-weight: 600;
  color: rgba(30, 27, 75, 0.30);
  padding-bottom: 8px;
}
</style>