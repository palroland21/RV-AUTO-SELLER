<template>
  <div>
    <div class="head">
      <h1 class="title">Listings</h1>
      <p class="sub">Editează / șterge anunțuri, marchează sold.</p>
    </div>
    <ListingsTable />
  </div>
</template>

<script setup lang="ts">
import ListingsTable from "@/components/admin/ListingsTable.vue";
</script>

<style scoped>
.head { margin-bottom: 14px; }
.title { font-size: 28px; font-weight: 800; }
.sub { color: #666; margin-top: 4px; }
</style>
