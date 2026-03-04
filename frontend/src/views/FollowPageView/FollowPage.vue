<template>
  <section class="follow-page">

    <!-- ───── 헤더 ───── -->
    <div class="header">

      <!-- 검색 모드 -->
      <template v-if="isSearchMode">
        <div class="search-bar">
          <span class="search-icon">🔍</span>
          <input
            ref="searchInputEl"
            class="search-input"
            placeholder="이름으로 검색"
            v-model="searchQuery"
          />
          <button class="search-close" @click="exitSearch">✕</button>
        </div>
      </template>

      <!-- 일반 모드 -->
      <template v-else>
        <div class="tab-row">
          <button
            class="tab"
            :class="{ active: activeTab === 'followers' }"
            @click="activeTab = 'followers'"
          >
            팔로워 <span class="tab-count">{{ followers.length }}</span>
          </button>
          <button
            class="tab"
            :class="{ active: activeTab === 'following' }"
            @click="activeTab = 'following'"
          >
            팔로잉 <span class="tab-count">{{ following.length }}</span>
          </button>
        </div>
        <button class="search-btn" @click="enterSearch">🔍</button>
      </template>

    </div>

    <!-- ───── 리스트 ───── -->
    <div class="list">

      <!-- 검색 결과 -->
      <template v-if="isSearchMode">
        <div v-if="!searchQuery.trim()" class="empty">
          <div class="empty-icon">🔍</div>
          <div class="empty-text">검색어를 입력해주세요</div>
        </div>
        <div v-else-if="searchResults.length === 0" class="empty">
          <div class="empty-icon">😅</div>
          <div class="empty-text">검색 결과가 없어요</div>
        </div>
        <div
          v-for="u in searchResults"
          :key="u.id"
          class="user-row"
        >
          <div class="avatar-wrap">
            <div class="avatar-fallback" :style="{ background: u.avatarBg }">
              {{ u.name.slice(0, 1).toUpperCase() }}
            </div>
          </div>
          <div class="user-info">
            <div class="user-name">{{ u.name }}</div>
            <div class="user-sub">{{ u.bio }}</div>
          </div>
          <button
            class="action-btn"
            :class="isFollowing(u.id) ? 'following' : 'follow'"
            @click="toggleFollow(u)"
          >
            {{ isFollowing(u.id) ? '팔로잉' : '팔로우' }}
          </button>
        </div>
      </template>

      <!-- 팔로워/팔로잉 탭 -->
      <template v-else>
        <div v-if="activeList.length === 0" class="empty">
          <div class="empty-icon">👥</div>
          <div class="empty-text">
            {{ activeTab === 'followers' ? '아직 팔로워가 없어요' : '아직 팔로잉이 없어요' }}
          </div>
        </div>
        <div
          v-for="u in activeList"
          :key="u.id"
          class="user-row"
        >
          <div class="avatar-wrap">
            <div class="avatar-fallback" :style="{ background: u.avatarBg }">
              {{ u.name.slice(0, 1).toUpperCase() }}
            </div>
          </div>
          <div class="user-info">
            <div class="user-name">{{ u.name }}</div>
            <div class="user-sub">{{ u.bio }}</div>
          </div>
          <button class="action-btn unfollow" @click="onUnfollow(u.id)">
            취소
          </button>
        </div>
      </template>

    </div>
  </section>
</template>

<script setup>
import { ref, computed, nextTick } from 'vue'

// ───── 탭 / 검색 상태 ─────
const activeTab    = ref('followers')
const isSearchMode = ref(false)
const searchQuery  = ref('')
const searchInputEl = ref(null)

async function enterSearch() {
  isSearchMode.value = true
  searchQuery.value  = ''
  await nextTick()
  searchInputEl.value?.focus()
}
function exitSearch() {
  isSearchMode.value = false
  searchQuery.value  = ''
}

// ───── 데이터 ─────
const followers = ref([
  { id: 'u1', name: 'Mina',  bio: '영어 공부 중 🌸', avatarBg: 'linear-gradient(135deg, #ec4899, #f97316)' },
  { id: 'u2', name: 'Ken',   bio: '매일 단어 10개!', avatarBg: 'linear-gradient(135deg, #10b981, #22c55e)' },
  { id: 'u3', name: 'Sora',  bio: '토익 목표 900점', avatarBg: 'linear-gradient(135deg, #0ea5e9, #6366f1)' },
  { id: 'u4', name: 'Jake',  bio: '🔥 연속 30일',   avatarBg: 'linear-gradient(135deg, #f59e0b, #ef4444)' },
  { id: 'u5', name: 'Hana',  bio: '단어왕 도전 중', avatarBg: 'linear-gradient(135deg, #a855f7, #ec4899)' },
])

const following = ref([
  { id: 'u1', name: 'Mina',  bio: '영어 공부 중 🌸', avatarBg: 'linear-gradient(135deg, #ec4899, #f97316)' },
  { id: 'u2', name: 'Ken',   bio: '매일 단어 10개!', avatarBg: 'linear-gradient(135deg, #10b981, #22c55e)' },
  { id: 'u6', name: 'Taro',  bio: '오늘도 화이팅',  avatarBg: 'linear-gradient(135deg, #14b8a6, #0ea5e9)' },
])

const allUsers = ref([
  { id: 'u1', name: 'Mina',  bio: '영어 공부 중 🌸', avatarBg: 'linear-gradient(135deg, #ec4899, #f97316)' },
  { id: 'u2', name: 'Ken',   bio: '매일 단어 10개!', avatarBg: 'linear-gradient(135deg, #10b981, #22c55e)' },
  { id: 'u3', name: 'Sora',  bio: '토익 목표 900점', avatarBg: 'linear-gradient(135deg, #0ea5e9, #6366f1)' },
  { id: 'u4', name: 'Jake',  bio: '🔥 연속 30일',   avatarBg: 'linear-gradient(135deg, #f59e0b, #ef4444)' },
  { id: 'u5', name: 'Hana',  bio: '단어왕 도전 중', avatarBg: 'linear-gradient(135deg, #a855f7, #ec4899)' },
  { id: 'u6', name: 'Taro',  bio: '오늘도 화이팅',  avatarBg: 'linear-gradient(135deg, #14b8a6, #0ea5e9)' },
  { id: 'u7', name: 'Rina',  bio: '꾸준함이 실력',  avatarBg: 'linear-gradient(135deg, #f43f5e, #a855f7)' },
  { id: 'u8', name: 'David', bio: '영어는 즐겁게',  avatarBg: 'linear-gradient(135deg, #64748b, #334155)' },
])

// ───── computed ─────
const activeList = computed(() =>
  activeTab.value === 'followers' ? followers.value : following.value
)

const searchResults = computed(() => {
  const q = searchQuery.value.trim().toLowerCase()
  if (!q) return []
  return allUsers.value.filter(u => u.name.toLowerCase().includes(q))
})

function isFollowing(id) {
  return following.value.some(u => u.id === id)
}

// ───── 팔로 취소 ─────
function onUnfollow(id) {
  following.value = following.value.filter(u => u.id !== id)
  followers.value = followers.value.filter(u => u.id !== id)
}

// ───── 검색 결과에서 팔로우/취소 토글 ─────
function toggleFollow(user) {
  if (isFollowing(user.id)) {
    following.value = following.value.filter(u => u.id !== user.id)
  } else {
    following.value.push({ ...user })
  }
}
</script>

<style scoped>
/* ───── Layout ───── */
.follow-page {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

/* ───── 헤더 ───── */
.header {
  display: flex;
  align-items: center;
  gap: 8px;
}

/* 탭 */
.tab-row {
  flex: 1;
  display: flex;
  background: rgba(255, 255, 255, 0.65);
  border: 1px solid rgba(30, 27, 75, 0.08);
  border-radius: 16px;
  padding: 4px;
  gap: 4px;
  backdrop-filter: blur(10px);
}

.tab {
  flex: 1;
  background: none;
  border: none;
  border-radius: 12px;
  padding: 9px 0;
  font-size: 14px;
  font-weight: 700;
  color: rgba(30, 27, 75, 0.45);
  cursor: pointer;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
}
.tab.active {
  background: linear-gradient(135deg, #7c3aed, #2563eb);
  color: #fff;
  box-shadow: 0 2px 8px rgba(124, 58, 237, 0.28);
}

.tab-count {
  font-size: 11px;
  font-weight: 900;
  opacity: 0.80;
}

/* 검색 버튼 */
.search-btn {
  width: 46px;
  height: 46px;
  border: 1px solid rgba(30, 27, 75, 0.08);
  border-radius: 14px;
  background: rgba(255, 255, 255, 0.75);
  font-size: 18px;
  cursor: pointer;
  display: grid;
  place-items: center;
  backdrop-filter: blur(10px);
  transition: background 0.15s;
  flex-shrink: 0;
}
.search-btn:active { background: rgba(124, 58, 237, 0.08); }

/* 검색창 */
.search-bar {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 10px;
  background: rgba(255, 255, 255, 0.90);
  border: 1.5px solid rgba(124, 58, 237, 0.35);
  border-radius: 16px;
  padding: 0 14px;
  height: 48px;
  backdrop-filter: blur(10px);
}

.search-icon { font-size: 15px; flex-shrink: 0; }

.search-input {
  flex: 1;
  border: none;
  background: none;
  outline: none;
  font-size: 15px;
  font-weight: 600;
  color: #1e1b4b;
}
.search-input::placeholder { color: rgba(30, 27, 75, 0.35); }

.search-close {
  background: none;
  border: none;
  font-size: 16px;
  color: rgba(30, 27, 75, 0.40);
  cursor: pointer;
  padding: 4px;
  flex-shrink: 0;
  line-height: 1;
}

/* ───── 리스트 ───── */
.list {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.user-row {
  background: rgba(255, 255, 255, 0.75);
  border: 1px solid rgba(30, 27, 75, 0.08);
  border-radius: 20px;
  padding: 12px 14px;
  display: flex;
  align-items: center;
  gap: 12px;
  backdrop-filter: blur(12px);
}

/* 아바타 */
.avatar-wrap { flex-shrink: 0; }

.avatar-fallback {
  width: 46px;
  height: 46px;
  border-radius: 14px;
  display: grid;
  place-items: center;
  font-size: 18px;
  font-weight: 900;
  color: rgba(255, 255, 255, 0.95);
}

/* 유저 정보 */
.user-info {
  flex: 1;
  min-width: 0;
}

.user-name {
  font-size: 15px;
  font-weight: 800;
  color: #1e1b4b;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.user-sub {
  font-size: 12px;
  color: rgba(30, 27, 75, 0.45);
  margin-top: 2px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 액션 버튼 */
.action-btn {
  border: none;
  border-radius: 12px;
  padding: 7px 14px;
  font-size: 13px;
  font-weight: 800;
  cursor: pointer;
  flex-shrink: 0;
  transition: opacity 0.15s;
}
.action-btn:active { opacity: 0.75; }

.action-btn.follow {
  background: linear-gradient(135deg, #7c3aed, #2563eb);
  color: #fff;
  box-shadow: 0 2px 8px rgba(124, 58, 237, 0.25);
}

.action-btn.following {
  background: rgba(30, 27, 75, 0.06);
  color: rgba(30, 27, 75, 0.50);
}

.action-btn.unfollow {
  background: rgba(239, 68, 68, 0.08);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.20);
}

/* ───── Empty ───── */
.empty {
  text-align: center;
  padding: 52px 0;
}
.empty-icon { font-size: 40px; margin-bottom: 10px; }
.empty-text {
  font-size: 14px;
  font-weight: 700;
  color: rgba(30, 27, 75, 0.40);
}
</style>