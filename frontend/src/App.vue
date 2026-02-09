<script setup>
import { RouterView } from 'vue-router'
</script>

<template>
  <header>
    <nav>
      <RouterLink to="/">Home</RouterLink> | 
      <a href="#">Login (준비중)</a>
    </nav>
  </header>

  <main>
    <RouterView />
  </main>
</template>

<style scoped>
header {
  padding: 20px;
  background: #eee;
  text-align: center;
}
nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #333;
  font-weight: bold;
}
</style>