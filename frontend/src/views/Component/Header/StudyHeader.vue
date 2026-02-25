<template>
    <v-app-bar flat class="border-b bg-white" height="60">
        <v-btn icon="mdi-chevron-left" variant="text" @click="$router.go(-1)"></v-btn>

        <div class="progress-container mx-4 flex-grow-1">
            <div class="d-flex justify-space-between text-caption text-grey-darken-1 mb-1 font-weight-bold">
                <span>Day {{ unitId }} 학습 진행도</span>
                <span class="text-primary">{{ learnedWords }} / {{ total }}</span>
            </div>

            <v-progress-linear :model-value="progressPercentage" color="primary" height="8" rounded
                bg-color="grey-lighten-3"></v-progress-linear>
        </div>

        <div style="width: 48px;"></div>
    </v-app-bar>
</template>

<script setup>
import { computed } from 'vue'

// 🌟 부모(StudyView)가 내려준 데이터 받기
const props = defineProps({
    unitId: { type: String, default: '1' },
    total: { type: Number, default: 0 },
    remaining: { type: Number, default: 0 }
})

// 🌟 '외운 단어 수' 계산 (전체 - 남은 것)
const learnedWords = computed(() => {
    return props.total - props.remaining
})

// 🌟 진행률(%) 계산
const progressPercentage = computed(() => {
    if (props.total === 0) return 0
    return (learnedWords.value / props.total) * 100
})
</script>

<style scoped>
.progress-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
}
</style>