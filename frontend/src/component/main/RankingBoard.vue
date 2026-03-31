<template>
  <section class="board">
    <header class="board-header">
      <h1 class="title">Following Ranking</h1>
    </header>

    <div v-if="isEmpty" class="empty">
      <div class="empty-icon">👥</div>
      <h2 class="empty-title">팔로우가 없어서 랭킹을 집계할 수 없어요</h2>
      <p class="empty-desc">팔로우를 추가하면 랭킹이 표시돼요.</p>
      <button class="cta" type="button" @click="onGoFollow">팔로우 찾으러 가기</button>
    </div>

    <div v-else class="list-scroll" ref="scrollEl">
      <div
        v-for="u in leaderboard"
        :key="u.id"
        class="row"
        :class="{ me: u.isMe, top1: u.rank === 1, top2: u.rank === 2, top3: u.rank === 3 }"
        :ref="(el) => setRowRef(u.id, el)"
      >
        <!-- 순위 -->
        <div class="rank">
          <span v-if="u.rank === 1" class="medal">🥇</span>
          <span v-else-if="u.rank === 2" class="medal">🥈</span>
          <span v-else-if="u.rank === 3" class="medal">🥉</span>
          <span v-else class="rank-num">#{{ u.rank }}</span>
        </div>

        <!-- 유저 -->
        <div class="user">
          <div class="avatar-wrap" :class="{ 'avatar-me': u.isMe }">
            <img
              v-if="u.avatarUrl"
              class="avatar-img"
              :src="u.avatarUrl"
              :alt="u.name"
              loading="lazy"
              referrerpolicy="no-referrer"
            />
            <div v-else class="avatar-fallback" :style="{ background: u.avatarBg }">
              {{ u.name.slice(0, 1).toUpperCase() }}
            </div>
          </div>

          <div class="meta">
            <div class="name">
              {{ u.name }}
              <span v-if="u.isMe" class="mini-me">ME</span>
            </div>
            <div class="small">달성 {{ formatAchievedAt(u.achievedAt) }}</div>
          </div>
        </div>

        <!-- 진도 -->
        <div class="right">
          <div class="progress">
            <span class="num">{{ formatProgress(u.progress) }}</span>
            <span class="unit">%</span>
          </div>
          <div class="progress-bar-wrap">
            <div class="progress-bar" :style="{ width: u.progress + '%' }"></div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { computed, nextTick, onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const me = ref({
  id: 'me',
  name: 'Yonggyu',
  progress: 72,
  achievedAt: '2026-03-04T09:10:00+09:00',
  avatarUrl: '',
  avatarBg: 'linear-gradient(135deg, #7c3aed, #2563eb)',
})

const following = ref([
  // {
  //   id: 'u1',
  //   name: 'Mina',
  //   progress: 80,
  //   achievedAt: '2026-03-04T08:40:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #ec4899, #f97316)',
  // },
  // {
  //   id: 'u2',
  //   name: 'Ken',
  //   progress: 72,
  //   achievedAt: '2026-03-04T08:55:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #10b981, #22c55e)',
  // },
  // {
  //   id: 'u3',
  //   name: 'Sora',
  //   progress: 66,
  //   achievedAt: '2026-03-03T22:10:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #0ea5e9, #6366f1)',
  // },
  // {
  //   id: 'u4',
  //   name: 'Jake',
  //   progress: 91,
  //   achievedAt: '2026-03-04T07:15:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #f59e0b, #ef4444)',
  // },
  // {
  //   id: 'u5',
  //   name: 'Hana',
  //   progress: 88,
  //   achievedAt: '2026-03-04T07:50:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #a855f7, #ec4899)',
  // },
  // {
  //   id: 'u6',
  //   name: 'Taro',
  //   progress: 75,
  //   achievedAt: '2026-03-04T09:00:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #14b8a6, #0ea5e9)',
  // },
  // {
  //   id: 'u7',
  //   name: 'Rina',
  //   progress: 72,
  //   achievedAt: '2026-03-04T09:05:00+09:00',  // Ken보다 늦어서 Ken 다음 순위
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #f43f5e, #a855f7)',
  // },
  // {
  //   id: 'u8',
  //   name: 'David',
  //   progress: 60,
  //   achievedAt: '2026-03-03T20:00:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #64748b, #334155)',
  // },
  // {
  //   id: 'u9',
  //   name: 'Yuki',
  //   progress: 55,
  //   achievedAt: '2026-03-03T18:30:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #f97316, #eab308)',
  // },
  // {
  //   id: 'u10',
  //   name: 'Chris',
  //   progress: 48,
  //   achievedAt: '2026-03-03T15:00:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #06b6d4, #10b981)',
  // },
  // {
  //   id: 'u11',
  //   name: 'Nana',
  //   progress: 40,
  //   achievedAt: '2026-03-03T12:00:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #8b5cf6, #d946ef)',
  // },
  // {
  //   id: 'u12',
  //   name: 'Leo',
  //   progress: 33,
  //   achievedAt: '2026-03-03T10:00:00+09:00',
  //   avatarUrl: '',
  //   avatarBg: 'linear-gradient(135deg, #3b82f6, #06b6d4)',
  // },
])

const isEmpty = computed(() => (following.value?.length ?? 0) === 0)

const leaderboard = computed(() => {
  const all = [
    { ...me.value, isMe: true },
    ...following.value.map((u) => ({ ...u, isMe: u.id === me.value.id })),
  ]
  all.sort((a, b) => {
    if (b.progress !== a.progress) return b.progress - a.progress
    return new Date(a.achievedAt).getTime() - new Date(b.achievedAt).getTime()
  })
  return all.map((u, i) => ({ ...u, rank: i + 1 }))
})

const scrollEl = ref(null)
const rowRefs = ref(new Map())
function setRowRef(id, el) {
  if (!el) return
  rowRefs.value.set(id, el)
}

function scrollToMe() {
  const container = scrollEl.value
  const el = rowRefs.value.get(me.value.id)
  if (!container || !el) return
  const containerRect = container.getBoundingClientRect()
  const elRect = el.getBoundingClientRect()

  const targetScrollTop =
    elRect.top - containerRect.top + container.scrollTop - containerRect.height / 2 + elRect.height / 2

  const startScrollTop = container.scrollTop
  const distance = targetScrollTop - startScrollTop
  const duration = 1200   // ✅ 600 → 900ms 로 늘려서 바운스가 잘 보이게
  let startTime = null

  function easeOutBack(t) {
    const c1 = 4.5   // ✅ 1.70158 → 2.8 로 올려서 더 크게 와리가리
    const c3 = c1 + 1
    return 1 + c3 * Math.pow(t - 1, 3) + c1 * Math.pow(t - 1, 2)
  }

  function animate(currentTime) {
    if (!startTime) startTime = currentTime
    const elapsed = currentTime - startTime
    const t = Math.min(elapsed / duration, 1)
    container.scrollTop = startScrollTop + distance * easeOutBack(t)
    if (t < 1) requestAnimationFrame(animate)
  }

  requestAnimationFrame(animate)
}

onMounted(async () => {
  if (isEmpty.value) return
  await nextTick()
  setTimeout(scrollToMe, 100)
})
watch(
  () => leaderboard.value.map((u) => `${u.id}-${u.rank}-${u.progress}-${u.achievedAt}`).join('|'),
  async () => {
    if (isEmpty.value) return
    await nextTick()
    scrollToMe()
  }
)

function formatProgress(p) { return String(p) }
function formatAchievedAt(v) {
  const d = new Date(v)
  const mm = String(d.getMonth() + 1).padStart(2, '0')
  const dd = String(d.getDate()).padStart(2, '0')
  const hh = String(d.getHours()).padStart(2, '0')
  const mi = String(d.getMinutes()).padStart(2, '0')
  return `${mm}/${dd} ${hh}:${mi}`
}

function onGoFollow() {
  router.push({ name: 'follow', query: { search: 'true' } })
}

</script>

<style scoped>
/* ───── Board ───── */
.board {
  display: flex;
  flex-direction: column;
  gap: 16px;
  height: 100%;
}

.board-header {
  padding: 4px 4px 0;
  flex-shrink: 0;
}

.title {
  margin: 0;
  font-size: 22px;
  font-weight: 900;
  letter-spacing: -0.5px;
  background: linear-gradient(120deg, #6d28d9, #1d4ed8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

/* ───── Empty ───── */
.empty {
  border-radius: 24px;
  padding: 32px 20px;
  background: rgba(255, 255, 255, 0.80);
  border: 1px solid rgba(109, 40, 217, 0.12);
  backdrop-filter: blur(16px);
  text-align: center;
}

.empty-icon { font-size: 48px; margin-bottom: 12px; }

.empty-title {
  margin: 0;
  font-size: 17px;
  font-weight: 900;
  color: #1e1b4b;
}

.empty-desc {
  margin: 10px 0 20px;
  font-size: 13px;
  color: rgba(30, 27, 75, 0.55);
}

.cta {
  width: 100%;
  border: none;
  border-radius: 16px;
  padding: 14px;
  font-size: 15px;
  font-weight: 900;
  color: #fff;
  background: linear-gradient(135deg, #7c3aed, #2563eb);
  cursor: pointer;
  box-shadow: 0 4px 16px rgba(124, 58, 237, 0.30);
  transition: opacity 0.15s;
}
.cta:active { opacity: 0.85; }

/* ───── Scroll list ───── */
.list-scroll {
  flex: 1;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
  /* 스크롤바 숨기기 */
  scrollbar-width: none;
  -ms-overflow-style: none;
  padding-bottom: 80px;
}
.list-scroll::-webkit-scrollbar { display: none; }

/* ───── Row ───── */
.row {
  border-radius: 20px;
  padding: 14px 16px;
  background: rgba(255, 255, 255, 0.75);
  border: 1px solid rgba(30, 27, 75, 0.08);
  backdrop-filter: blur(12px);
  display: grid;
  grid-template-columns: 48px 1fr auto;
  align-items: center;
  gap: 12px;
  margin-bottom: 10px;
  transition: transform 0.15s, box-shadow 0.15s;
}
.row:last-child { margin-bottom: 0; }

/* 1~3등 강조 */
.row.top1 {
  background: linear-gradient(135deg, rgba(255, 215, 0, 0.12), rgba(255, 255, 255, 0.85));
  border-color: rgba(255, 193, 7, 0.30);
  box-shadow: 0 2px 12px rgba(255, 193, 7, 0.15);
}
.row.top2 {
  background: linear-gradient(135deg, rgba(192, 192, 192, 0.15), rgba(255, 255, 255, 0.85));
  border-color: rgba(160, 160, 160, 0.28);
}
.row.top3 {
  background: linear-gradient(135deg, rgba(205, 127, 50, 0.10), rgba(255, 255, 255, 0.85));
  border-color: rgba(180, 120, 60, 0.22);
}

/* ME 강조 */
.row.me {
  border: 2px solid rgba(124, 58, 237, 0.45);
  background: linear-gradient(135deg, rgba(124, 58, 237, 0.10), rgba(37, 99, 235, 0.08));
  box-shadow: 0 4px 20px rgba(124, 58, 237, 0.18);
}

/* ───── Rank ───── */
.rank { text-align: center; }

.medal { font-size: 26px; line-height: 1; }

.rank-num {
  font-size: 15px;
  font-weight: 900;
  color: rgba(30, 27, 75, 0.45);
}

/* ───── User ───── */
.user {
  display: flex;
  align-items: center;
  gap: 12px;
  min-width: 0;
}

.meta { min-width: 0; }

.name {
  font-size: 15px;
  font-weight: 800;
  color: #1e1b4b;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  display: flex;
  align-items: center;
  gap: 6px;
}

.small {
  font-size: 11px;
  color: rgba(30, 27, 75, 0.45);
  margin-top: 2px;
}

.mini-me {
  font-size: 9px;
  font-weight: 900;
  padding: 2px 7px;
  border-radius: 999px;
  color: #7c3aed;
  background: rgba(124, 58, 237, 0.12);
  border: 1px solid rgba(124, 58, 237, 0.28);
  letter-spacing: 0.5px;
}

/* ───── Avatar ───── */
.avatar-wrap {
  width: 44px;
  height: 44px;
  border-radius: 14px;
  overflow: hidden;
  flex: 0 0 auto;
  box-shadow: 0 2px 8px rgba(0,0,0,0.10);
}
.avatar-wrap.avatar-me {
  outline: 2.5px solid #7c3aed;
  outline-offset: 2px;
}

.avatar-img { width: 100%; height: 100%; object-fit: cover; display: block; }

.avatar-fallback {
  width: 100%;
  height: 100%;
  display: grid;
  place-items: center;
  font-size: 18px;
  font-weight: 900;
  color: rgba(255, 255, 255, 0.95);
}

/* ───── Progress ───── */
.right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 5px;
  min-width: 60px;
}

.progress {
  display: flex;
  align-items: baseline;
  gap: 1px;
  line-height: 1;
}

.num {
  font-size: 20px;
  font-weight: 900;
  color: #1e1b4b;
}

.unit {
  font-size: 12px;
  font-weight: 700;
  color: rgba(30, 27, 75, 0.50);
}

.progress-bar-wrap {
  width: 60px;
  height: 4px;
  background: rgba(30, 27, 75, 0.08);
  border-radius: 999px;
  overflow: hidden;
}

.progress-bar {
  height: 100%;
  border-radius: 999px;
  background: linear-gradient(90deg, #7c3aed, #2563eb);
  max-width: 100%;
  transition: width 0.4s ease;
}
</style>