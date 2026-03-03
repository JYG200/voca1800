<template>
  <div>
    <MainHeader />
    <div class="ranking-scroll-area">
      <div class="content-limit-wrapper">
        <h2>랭킹</h2>
      </div>
    </div>
    <MainFooter />
  </div>
</template>

<script setup>
import MainHeader from '@/views/Component/Header/MainHeader.vue'
import MainFooter from '@/views/Component/Footer/MainFooter.vue'
</script>

<style scoped>
.ranking-scroll-area {
  margin-top: 64px;
  height: calc(100vh - 64px - 68px);
  overflow-y: auto;
  background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 50%, #f0f7ff 100%);
}

.content-limit-wrapper {
  max-width: 500px;
  margin: 0 auto;
  padding: 24px;
}
</style>