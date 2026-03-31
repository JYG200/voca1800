<template>
  <v-app>
    <MainHeader />
    <div class="follow-scroll-area">
      <div class="content-limit-wrapper">
        <FollowPage />
      </div>
    </div>
    <MainFooter />
  </v-app>
</template>

<script setup>
import MainHeader from '@/component/header/MainHeader.vue'
import MainFooter from '@/component/footer/MainFooter.vue'
import FollowPage from '@/component/main/FollowPage.vue'
</script>

<style scoped>
.follow-scroll-area {
  height: calc(100vh - 56px - 56px);
  overflow-y: auto;
  background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 50%, #f0f7ff 100%);
}

.content-limit-wrapper {
  max-width: 500px;
  margin: 0 auto;
  padding: 24px;
}
</style>