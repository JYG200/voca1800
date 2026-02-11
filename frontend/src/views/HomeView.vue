<template>
<div class="home">
    <h1> API 테스트</h1>
    <div class="card">
      <p>결과:</p>
      
      <h2 v-if="words.length === 0">데이터 로딩 중...</h2>

      <ul v-else>
        <li v-for="(word, index) in words" :key="index">
          {{ word.wordEnglish }}
        </li>
      </ul>
    </div>
</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchTestWords } from '@/api/testApi'; 

const message = ref('데이터 로딩 중...');
const words = ref([]);
onMounted(async () => {
  const response = await fetchTestWords();
  words.value = response;
});
</script>

<style scoped>
/* 리스트 스타일 살짝 다듬기 */
ul {
  list-style-type: none; /* 점 없애기 */
  padding: 0;
}
li {
  font-size: 1.5rem;
  color: #42b883;
  margin: 10px 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 5px;
}
.home { text-align: center; margin-top: 50px; }
.card { 
  border: 1px solid #ddd; 
  padding: 20px; 
  margin: 20px auto; 
  max-width: 400px;
  border-radius: 8px;
  background: #f9f9f9;
}
</style>