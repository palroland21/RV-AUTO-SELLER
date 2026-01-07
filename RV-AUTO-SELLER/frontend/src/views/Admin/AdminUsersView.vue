<template>
  <div>
    <div class="head">
      <h1 class="title">Users</h1>
      <p class="sub">Gestionează utilizatori: edit, role, delete.</p>
    </div>
    <UsersTable />
  </div>
</template>

<script setup lang="ts">
import UsersTable from "@/components/admin/UsersTable.vue";
</script>

<style scoped>
.head { margin-bottom: 14px; }
.title { font-size: 28px; font-weight: 800; }
.sub { color: #666; margin-top: 4px; }
</style>
