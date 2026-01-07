<template>
  <div>
    <div class="head">
      <h1 class="title">Feedbacks</h1>
      <p class="sub">Vezi toate feedback-urile și poți șterge.</p>
    </div>
    <FeedbacksTable />
  </div>
</template>

<script setup lang="ts">
import FeedbacksTable from "@/components/admin/FeedbacksTable.vue";
</script>

<style scoped>
.head { margin-bottom: 14px; }
.title { font-size: 28px; font-weight: 800; }
.sub { color: #666; margin-top: 4px; }
</style>
