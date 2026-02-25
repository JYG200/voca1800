<template>
    <main id="study-main" class="border-red pa-6 d-flex flex-column align-center justify-center">
        <v-card id="main-word-card" class="border-red d-flex flex-column align-center justify-center elevation-2">
            <div class="word-content text-center">
                <h1 class="text-h2 font-weight-black">Apple</h1>
                <p class="text-h6 text-grey-darken-1 mt-4">[æpl]</p>
            </div>
            <div class="meaning-content mt-12 border-red pa-4">
                <span class="text-h4 font-weight-bold text-primary">사과</span>
            </div>
            <div class="tap-guide text-caption text-grey mt-auto mb-6">
                화면을 터치하여 뜻을 확인하세요
            </div>
        </v-card>
    </main>
</template>

<style scoped>
.border-red {
    border: 1px solid red !important;
}

#study-main {
    flex: 1;
    width: 100%;
    display: flex;
}

#main-word-card {
    width: 100%;
    height: 80%;
    border-radius: 32px !important;
}
</style>