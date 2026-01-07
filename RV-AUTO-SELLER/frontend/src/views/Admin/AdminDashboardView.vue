<template>
  <div>
    <h1 class="title">Admin Dashboard</h1>
    <StatsCards />
  </div>
</template>

<script setup lang="ts">
import StatsCards from "@/components/admin/StatsCards.vue";
</script>

<style scoped>
.title { font-size: 28px; font-weight: 800; margin-bottom: 16px; }
</style>
