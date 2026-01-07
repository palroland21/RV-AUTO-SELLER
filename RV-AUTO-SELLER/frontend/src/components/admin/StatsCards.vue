<template>
  <div>
    <div class="grid">
      <div class="card">
        <div class="k">Users</div>
        <div class="v">{{ stats?.totalUsers ?? "-" }}</div>
      </div>
      <div class="card">
        <div class="k">Listings</div>
        <div class="v">{{ stats?.totalListings ?? "-" }}</div>
      </div>
      <div class="card">
        <div class="k">Appointments</div>
        <div class="v">{{ stats?.totalAppointments ?? "-" }}</div>
      </div>
      <div class="card">
        <div class="k">Feedbacks</div>
        <div class="v">{{ stats?.totalFeedbacks ?? "-" }}</div>
      </div>
    </div>

    <div class="row">
      <div class="panel">
        <div class="ptitle">Listings by brand</div>
        <table class="mini">
          <tbody>
          <tr v-for="(val, key) in stats?.listingsByBrand" :key="key">
            <td>{{ key }}</td>
            <td class="num">{{ val }}</td>
          </tr>
          <tr v-if="!hasMap(stats?.listingsByBrand)">
            <td colspan="2" class="muted">No data</td>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="panel">
        <div class="ptitle">Listings by type</div>
        <table class="mini">
          <tbody>
          <tr v-for="(val, key) in stats?.listingsByType" :key="key">
            <td>{{ key }}</td>
            <td class="num">{{ val }}</td>
          </tr>
          <tr v-if="!hasMap(stats?.listingsByType)">
            <td colspan="2" class="muted">No data</td>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="panel">
        <div class="ptitle">Listings by fuel</div>
        <table class="mini">
          <tbody>
          <tr v-for="(val, key) in stats?.listingsByFuel" :key="key">
            <td>{{ key }}</td>
            <td class="num">{{ val }}</td>
          </tr>
          <tr v-if="!hasMap(stats?.listingsByFuel)">
            <td colspan="2" class="muted">No data</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div v-if="error" class="error">{{ error }}</div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { adminApi } from "@/api/adminService";
import type { AdminStatsResponse } from "@/types/admin";

const stats = ref<AdminStatsResponse | null>(null);
const error = ref("");

function hasMap(map: Record<string, number> | undefined) {
  return map && Object.keys(map).length > 0;
}

onMounted(async () => {
  try {
    stats.value = await adminApi.getStats();
  } catch (e: any) {
    error.value = e?.response?.data?.message ?? e?.message ?? "Failed to load stats";
  }
});
</script>

<style scoped>
.grid { display: grid; grid-template-columns: repeat(4, minmax(0, 1fr)); gap: 12px; }
.card { background: #fff; border: 1px solid #eee; border-radius: 14px; padding: 16px; }
.k { color: #666; font-size: 12px; }
.v { font-size: 28px; font-weight: 800; margin-top: 6px; }

.row { margin-top: 14px; display: grid; grid-template-columns: repeat(3, minmax(0, 1fr)); gap: 12px; }
.panel { background: #fff; border: 1px solid #eee; border-radius: 14px; padding: 16px; }
.ptitle { font-weight: 800; margin-bottom: 10px; }
.mini { width: 100%; border-collapse: collapse; }
.mini td { padding: 8px 0; border-bottom: 1px solid #f2f2f2; }
.num { text-align: right; font-weight: 700; }
.muted { color: #777; }
.error { margin-top: 12px; color: #b00020; }
@media (max-width: 1000px) {
  .grid { grid-template-columns: repeat(2, 1fr); }
  .row { grid-template-columns: 1fr; }
}
</style>
