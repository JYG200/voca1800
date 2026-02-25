<template>
  <v-bottom-navigation v-model="activeTab" grow mandatory color="primary" height="70"
    :scroll-behavior="isMobile ? 'hide' : undefined" scroll-target="#main-scroll" scroll-threshold="10"
    class="custom-footer">
    <div class="footer-content-container">

      <v-btn value="ranking" class="footer-btn">
        <v-icon :color="activeTab === 'ranking' ? 'primary' : 'grey-lighten-1'">
          {{ activeTab === 'ranking' ? 'mdi-trophy' : 'mdi-trophy-outline' }}
        </v-icon>
        <span :class="activeTab === 'ranking' ? 'text-primary font-weight-bold' : 'text-grey'">랭킹</span>
      </v-btn>

      <v-btn value="study" class="footer-btn">
        <v-icon size="30" :color="activeTab === 'study' ? 'primary' : 'grey-lighten-1'">
          {{ activeTab === 'study' ? 'mdi-book-open-page-variant' : 'mdi-book-open-page-variant-outline' }}
        </v-icon>
        <span :class="activeTab === 'study' ? 'text-primary font-weight-bold' : 'text-grey'">학습</span>
      </v-btn>

      <v-btn value="mypage" class="footer-btn">
        <v-icon :color="activeTab === 'mypage' ? 'primary' : 'grey-lighten-1'">
          {{ activeTab === 'mypage' ? 'mdi-account-circle' : 'mdi-account-circle-outline' }}
        </v-icon>
        <span :class="activeTab === 'mypage' ? 'text-primary font-weight-bold' : 'text-grey'">마이</span>
      </v-btn>

    </div>
  </v-bottom-navigation>
</template>

<script setup>
import { ref } from 'vue'
import { useDisplay } from 'vuetify'

const { mobile: isMobile } = useDisplay()
const activeTab = ref('study') // 기본값 학습
</script>

<style scoped>
.custom-footer {
  background-color: white !important;
  /* 배경은 항상 깔끔한 흰색 */
  border-top: 1px solid #eeeeee !important;
}

.footer-content-container {
  display: flex;
  width: 100%;
  max-width: 500px;
  margin: 0 auto;
  height: 100%;
}

.footer-btn {
  flex: 1; /* 🌟 핵심 1: 세 개의 버튼이 가로 공간을 똑같은 비율로 꽉 채워 나눠가짐 */
  max-width: none !important; /* 🌟 핵심 2: Vuetify의 기본 버튼 너비 제한을 풀어줌 */
  height: 100% !important;
  background: transparent !important;
  box-shadow: none !important;
  
  /* 아이콘과 글자가 중앙에 예쁘게 정렬되도록 보장 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

/* 텍스트 스타일 */
span {
  font-size: 0.75rem;
  margin-top: 4px;
}

/* 활성화된 텍스트 강조 */
.text-primary {
  color: #1976D2 !important;
}

.text-grey {
  color: #BDBDBD !important;
  /* 비활성화된 아이콘은 연한 회색 */
}

/* 클릭 시 터치 피드백만 살짝 */
.footer-btn:active {
  opacity: 0.7;
}
</style>