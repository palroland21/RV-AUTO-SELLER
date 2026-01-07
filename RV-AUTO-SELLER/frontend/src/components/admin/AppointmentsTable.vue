<template>
  <div class="wrap">
    <div class="toolbar">
      <input v-model="q" class="inp" placeholder="Search by location/details/client/seller/date..." />
      <button class="btn" @click="load">Reload</button>
    </div>

    <div v-if="loading" class="muted">Loading...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <table v-if="!loading" class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Location</th>
        <th>Date</th>
        <th>Client ID</th>
        <th>Seller ID</th>
        <th>Details</th>
        <th class="right">Actions</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="a in filtered" :key="a.id">
        <td>{{ a.id }}</td>
        <td>{{ a.location }}</td>
        <td>{{ formatDateTime(a.date) }}</td>
        <td>{{ a.client }}</td>
        <td>{{ a.seller }}</td>
        <td class="details">{{ a.details }}</td>
        <td class="right">
          <button class="btn danger" @click="remove(a.id)">Delete</button>
        </td>
      </tr>

      <tr v-if="filtered.length === 0">
        <td colspan="7" class="muted">No appointments</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from "vue";
import { adminApi } from "@/api/adminService";
import type { Appointment } from "@/types/appointment";

const items = ref<Appointment[]>([]);
const loading = ref(false);
const error = ref("");
const q = ref("");

const filtered = computed(() => {
  const s = q.value.trim().toLowerCase();
  if (!s) return items.value;

  return items.value.filter((a) => {
    const text = `${a.id} ${a.location} ${a.date} ${a.details} ${a.client} ${a.seller}`.toLowerCase();
    return text.includes(s);
  });
});

function formatDateTime(iso: string) {
  // backend LocalDateTime -> ex: "2026-01-07T12:30:00"
  if (!iso) return "-";
  return iso.replace("T", " ");
}

async function load() {
  loading.value = true;
  error.value = "";
  try {
    items.value = await adminApi.getAppointments();
  } catch (e: any) {
    error.value = e?.response?.data?.message ?? e?.message ?? "Failed to load appointments";
  } finally {
    loading.value = false;
  }
}

async function remove(id: number) {
  const ok = confirm(`Delete appointment #${id}?`);
  if (!ok) return;

  try {
    await adminApi.deleteAppointment(id);
    items.value = items.value.filter((x) => x.id !== id);
  } catch (e: any) {
    alert(e?.response?.data?.message ?? e?.message ?? "Failed to delete appointment");
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
.details { max-width: 520px; white-space: pre-wrap; word-break: break-word; }
.muted { color: #777; }
.error { margin-top: 10px; color: #b00020; }
</style>
