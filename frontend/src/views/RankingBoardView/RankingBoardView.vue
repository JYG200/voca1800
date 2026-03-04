<template>
  <v-app class="app-shell">
    <MainHeader />

    <main class="ranking-main">
      <div class="content-limit-wrapper">
        <RankingBoard />
      </div>
    </main>

    <MainFooter />
  </v-app>
</template>

<script setup>
import MainHeader from '@/views/Component/Header/MainHeader.vue'
import MainFooter from '@/views/Component/Footer/MainFooter.vue'
import RankingBoard from '@/views/Component/Main/RankingBoard.vue'
</script>

<style scoped>
.app-shell {
  height: 100vh !important;      /* ✅ min-height → height 로 변경 */
  max-height: 100vh !important;  /* ✅ 추가 */
  display: flex;
  flex-direction: column;
  overflow: hidden;              /* ✅ 추가 — 앱 전체가 넘치지 않게 */
  background: linear-gradient(160deg, #ede9fe 0%, #dbeafe 55%, #eff6ff 100%);
}

.ranking-main {
  flex: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  min-height: 0;                 /* ✅ 추가 — flex 자식이 줄어들 수 있게 */
}

.content-limit-wrapper {
  max-width: 500px;
  width: 100%;
  margin: 0 auto;
  padding: 20px 20px 16px;
  flex: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  min-height: 0;                 /* ✅ 추가 */
}
</style>