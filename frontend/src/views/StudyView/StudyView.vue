<template>
    <v-app>
        <v-main class="bg-grey-lighten-5">
            <div id="study-layout" class="mobile-container border-red">
                <StudyHeader :unitId="$route.params.id" />

                <StudyMain />
                <StudyFooter/>
            </div>
        </v-main>
    </v-app>
</template>

<script setup>
import StudyHeader from '@/views/Component/Header/StudyHeader.vue'
import StudyMain from '@/views/Component/Main/StudyMain.vue'
import StudyFooter from '../Component/Footer/StudyFooter.vue';
</script>

<style scoped>
.mobile-container {
    max-width: 500px;
    margin: 0 auto;
    height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: white;
}

.border-red {
    border: 1px solid red !important;
}
</style>