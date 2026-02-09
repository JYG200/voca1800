<template>
<div class="home">
    <h1> API 분리 테스트</h1>
    <div class="card">
      <p>결과:</p>
      <h2>{{ message }}</h2>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchTestWords } from '@/api/testApi'; 

const message = ref('데이터 로딩 중...');

onMounted(async () => {
  message.value = await fetchTestWords();
});
</script>

<style scoped>
/* 스타일은 그대로 */
.home { text-align: center; margin-top: 50px; }
.card { 
  border: 1px solid #ddd; 
  padding: 20px; 
  margin: 20px auto; 
  max-width: 400px;
  border-radius: 8px;
  background: #f9f9f9;
}
h2 { color: #42b883; }
</style>