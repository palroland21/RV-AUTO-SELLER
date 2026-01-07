<template>
  <div class="box">
    <h1>403</h1>
    <p>Nu ai acces la această pagină.</p>
    <RouterLink to="/">Înapoi acasă</RouterLink>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.box { max-width: 560px; margin: 80px auto; background: #fff; padding: 22px; border-radius: 14px; border: 1px solid #eee; }
h1 { font-size: 40px; margin: 0 0 8px; }
p { color: #666; margin: 0 0 12px; }
</style>
