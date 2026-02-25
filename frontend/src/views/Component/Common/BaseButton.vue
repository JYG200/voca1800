<template>
  <v-btn :color="color" :variant="variant" :size="size" :icon="icon" class="font-weight-bold custom-base-btn">
    <v-icon v-if="icon">{{ prependIcon }}</v-icon>

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
  icon: { type: Boolean, default: false }, // 아이콘만 있는 버튼 여부
  size: { type: String, default: 'default' }
})
</script>

<style scoped>
.custom-base-btn {
  /* 영역 확인용 빨간 테두리 - 나중에 삭제하세요 */
  border: 1px solid red !important;
  text-transform: none;
  /* 소문자 유지 */
}
</style>