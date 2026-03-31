<template>
    <header class="study-header">
        <v-btn icon="mdi-chevron-left" variant="text" color="#7c3aed" @click="$router.go(-1)" />

        <div class="header-center">
            <span class="header-title">Day {{ unitId }}</span>
            <div class="header-progress-track">
                <div class="header-progress-fill" :style="{ width: progressPercentage + '%' }" />
            </div>
        </div>

        <div class="header-count">
            <span class="count-done">{{ learnedWords }}</span>
            <span class="count-sep"> / </span>
            <span class="count-total">{{ total }}</span>
        </div>
    </header>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
    unitId: { type: String, default: '1' },
    total: { type: Number, default: 0 },
    remaining: { type: Number, default: 0 }
})

const learnedWords = computed(() => props.total - props.remaining)

const progressPercentage = computed(() => {
    if (props.total === 0) return 0
    return (learnedWords.value / props.total) * 100
})
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nunito:wght@400;700;900&display=swap');

.study-header {
    width: 100%;
    height: 64px;
    display: flex;
    align-items: center;
    padding: 0 8px 0 4px;
    background: linear-gradient(160deg, #f0e6ff 0%, #e6f0ff 100%);
    border-bottom: 1.5px solid rgba(167, 139, 250, 0.2);
    font-family: 'Nunito', sans-serif;
    flex-shrink: 0; /* 높이 고정 */
}

.header-center {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 6px;
    padding: 0 8px;
}

.header-title {
    font-size: 13px;
    font-weight: 700;
    color: #7c6aaa;
    letter-spacing: 0.5px;
}

.header-progress-track {
    width: 100%;
    height: 6px;
    background: rgba(255, 255, 255, 0.7);
    border-radius: 99px;
    overflow: hidden;
}

.header-progress-fill {
    height: 100%;
    background: linear-gradient(90deg, #a78bfa, #60a5fa);
    border-radius: 99px;
    transition: width 0.5s cubic-bezier(0.4, 0, 0.2, 1);
}

.header-count {
    font-family: 'Nunito', sans-serif;
    font-size: 14px;
    font-weight: 700;
    padding-right: 16px;
    white-space: nowrap;
}

.count-done {
    color: #7c3aed;
}

.count-sep {
    color: #c4b5fd;
}

.count-total {
    color: #9ca3af;
}
</style>