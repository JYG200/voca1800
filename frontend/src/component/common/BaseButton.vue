<template>
  <v-btn :color="color" :variant="variant" :size="size" :icon="icon ? true : undefined" class="custom-base-btn">
    <!-- 아이콘 전용 버튼 -->
    <v-icon v-if="icon">{{ prependIcon }}</v-icon>

    <!-- 일반 버튼 -->
    <template v-else>
      <v-icon v-if="prependIcon" start>{{ prependIcon }}</v-icon>
      <slot>{{ label }}</slot>
      <v-icon v-if="appendIcon" end>{{ appendIcon }}</v-icon>
    </template>
  </v-btn>
</template>

<script setup>
defineProps({
  label: { type: String, default: '' },
  color: { type: String, default: 'primary' },
  variant: { type: String, default: 'flat' },
  prependIcon: { type: String, default: '' },
  appendIcon: { type: String, default: '' },
  icon: { type: Boolean, default: false },
  size: { type: String, default: 'default' }
})
</script>

<style scoped>
.custom-base-btn {
  text-transform: none;
  /* 소문자 유지 */
  font-family: 'Nunito', sans-serif;
  font-weight: 700;
}
</style>