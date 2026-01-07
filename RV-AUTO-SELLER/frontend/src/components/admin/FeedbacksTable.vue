<template>
  <div class="wrap">
    <div class="toolbar">
      <input v-model="q" class="inp" placeholder="Search by fromUserName/description/rating..." />
      <button class="btn" @click="load">Reload</button>
    </div>

    <div v-if="loading" class="muted">Loading...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <table v-if="!loading" class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>From</th>
        <th>To (ID)</th>
        <th>Rating</th>
        <th>Description</th>
        <th>Created</th>
        <th class="right">Actions</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="f in filtered" :key="f.id">
        <td>{{ f.id }}</td>
        <td>
          <div class="from">
            <div class="name">{{ f.fromUserName }}</div>
            <div class="mutedSmall">id: {{ f.fromUser }}</div>
          </div>
        </td>
        <td>{{ f.toUser }}</td>
        <td>{{ f.rating }}</td>
        <td class="desc">{{ f.description }}</td>
        <td>{{ formatDateTime(f.createdAt) }}</td>
        <td class="right">
          <button class="btn danger" @click="remove(f.id)">Delete</button>
        </td>
      </tr>

      <tr v-if="filtered.length === 0">
        <td colspan="7" class="muted">No feedbacks</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from "vue";
import { adminApi } from "@/api/adminService";
import type { Feedback } from "@/types/feedback";

const items = ref<Feedback[]>([]);
const loading = ref(false);
const error = ref("");
const q = ref("");

const filtered = computed(() => {
  const s = q.value.trim().toLowerCase();
  if (!s) return items.value;

  return items.value.filter((f) => {
    const text = `${f.id} ${f.fromUserName} ${f.description} ${f.rating} ${f.fromUser} ${f.toUser}`.toLowerCase();
    return text.includes(s);
  });
});

function formatDateTime(iso: string) {
  if (!iso) return "-";
  return iso.replace("T", " ");
}

async function load() {
  loading.value = true;
  error.value = "";
  try {
    items.value = await adminApi.getFeedbacks();
  } catch (e: any) {
    error.value = e?.response?.data?.message ?? e?.message ?? "Failed to load feedbacks";
  } finally {
    loading.value = false;
  }
}

async function remove(id: number) {
  const ok = confirm(`Delete feedback #${id}?`);
  if (!ok) return;

  try {
    await adminApi.deleteFeedback(id);
    items.value = items.value.filter((x) => x.id !== id);
  } catch (e: any) {
    alert(e?.response?.data?.message ?? e?.message ?? "Failed to delete feedback");
  }
}

onMounted(load);
</script>

<style scoped>
.wrap { background: #fff; border: 1px solid #eee; border-radius: 14px; padding: 16px; }
.toolbar { display: flex; gap: 10px; margin-bottom: 12px; }
.inp { width: 100%; padding: 10px 12px; border: 1px solid #ddd; border-radius: 10px; outline: none; }
.btn { padding: 10px 12px; border: 1px solid #ddd; border-radius: 10px; background: #fff; cursor: pointer; }
.btn:hover { background: #f7f7f7; }
.danger { border-color: #f2b8b5; background: #fff5f5; }
.table { width: 100%; border-collapse: collapse; }
th, td { text-align: left; padding: 12px 10px; border-bottom: 1px solid #f2f2f2; vertical-align: top; }
.right { text-align: right; }
.desc { max-width: 520px; white-space: pre-wrap; word-break: break-word; }
.from { display: grid; gap: 2px; }
.name { font-weight: 700; }
.muted { color: #777; }
.mutedSmall { color: #777; font-size: 12px; }
.error { margin-top: 10px; color: #b00020; }
</style>
