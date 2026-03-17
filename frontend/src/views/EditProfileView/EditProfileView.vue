<template>
  <v-app>
    <div class="edit-scroll-area">
      <div class="content-limit-wrapper">
        <EditProfilePage />
      </div>
    </div>
    <MainFooter />
  </v-app>
</template>

<script setup>
import MainFooter from '@/views/Component/Footer/MainFooter.vue'
import EditProfilePage from '@/views/Component/Main/EditProfilePage.vue'
</script>

<style scoped>
.edit-scroll-area {
  height: calc(100vh - 56px); 
  background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 50%, #f0f7ff 100%);
}

.content-limit-wrapper {
  max-width: 500px;
  margin: 0 auto;
  padding: 24px;
}
</style>