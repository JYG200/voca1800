<template>
    <header id="study-header" class="border-red">
        <div class="header-wrapper">

            <div class="header-side border-red">
                <BaseButton icon prepend-icon="mdi-arrow-left" variant="text" color="grey-darken-3" @click="goBack" />
            </div>

            <div class="header-center border-red">
                <span class="unit-title">단원 {{ unitId }}</span>
            </div>

            <div class="header-side border-red"></div>

        </div>
    </header>
</template>

<script setup>
import { useRouter } from 'vue-router'
import BaseButton from '@/views/Component/Common/BaseButton.vue'

defineProps({
    unitId: String
})

const router = useRouter()
const goBack = () => {
    window.history.length > 1 ? router.back() : router.push('/')
}
</script>

<style scoped>
.border-red {
    border: 1px solid red !important;
}

#study-header {
    width: 100%;
    height: 64px;
    background-color: white;
}

.header-wrapper {
    display: flex;
    height: 100%;
    align-items: center;
    /* 수직 중앙 */
}

.header-side {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
}

.header-center {
    flex: 2;
    /* 중앙을 넓게 하여 글자 공간 확보 */
    display: flex;
    justify-content: center;
    align-items: center;
}

.unit-title {
    font-size: 1.2rem;
    font-weight: 900;
    color: #333;
}
</style>