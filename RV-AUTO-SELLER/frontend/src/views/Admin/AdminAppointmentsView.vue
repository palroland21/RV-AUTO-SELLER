<template>
  <div>
    <div class="head">
      <h1 class="title">Appointments</h1>
      <p class="sub">Vezi toate programările și poți șterge.</p>
    </div>
    <AppointmentsTable />
  </div>
</template>

<script setup lang="ts">
import AppointmentsTable from "@/components/admin/AppointmentsTable.vue";
</script>

<style scoped>
.head { margin-bottom: 14px; }
.title { font-size: 28px; font-weight: 800; }
.sub { color: #666; margin-top: 4px; }
</style>
