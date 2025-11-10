<template>
  <div class="public-layout">
    <Navbar :is-transparent="$route.name === 'Home'" />

    <main class="main-content">
      <router-view />
    </main>

    <Footer />
  </div>
</template>

<script setup>
import Navbar from '@/components/common/Navbar.vue';
import Footer from '@/components/common/Footer.vue';
</script>

<style scoped>
.public-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.main-content {
  flex-grow: 1;
}
</style>

<style>
body {
  margin: 0;
  font-family: 'Arial', sans-serif;
}
</style>