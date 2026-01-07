<template>
  <div class="wrap">
    <div class="toolbar">
      <input v-model="q" class="inp" placeholder="Search by title/brand/model/location..." />
      <button class="btn" @click="load">Reload</button>
    </div>

    <div v-if="loading" class="muted">Loading...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <table v-if="!loading" class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Price</th>
        <th>Location</th>
        <th>Sold</th>
        <th class="right">Actions</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="l in filtered" :key="(l as any).id">
        <td>{{ (l as any).id }}</td>
        <td>{{ (l as any).title }}</td>
        <td>{{ (l as any).brand }}</td>
        <td>{{ (l as any).model }}</td>
        <td>{{ (l as any).price }}</td>
        <td>{{ (l as any).location }}</td>

        <td>
          <input type="checkbox" :checked="Boolean((l as any).sold)" @change="toggleSold(l as any, $event)" />
        </td>

        <td class="right">
          <button class="btn ghost" @click="openEdit(l as any)">Edit</button>
          <button class="btn danger" @click="remove(l as any)">Delete</button>
        </td>
      </tr>

      <tr v-if="filtered.length === 0">
        <td colspan="8" class="muted">No listings found</td>
      </tr>
      </tbody>
    </table>

    <!-- Modal edit listing -->
    <div v-if="editing" class="modal">
      <div class="modal-card">
        <div class="modal-head">
          <div class="mtitle">Edit listing #{{ (editing as any).id }}</div>
          <button class="x" @click="closeEdit">✕</button>
        </div>

        <div class="grid">
          <label>Title <input class="inp" v-model="form.title" /></label>
          <label>Price <input class="inp" type="number" v-model.number="form.price" /></label>
          <label>Brand <input class="inp" v-model="form.brand" /></label>
          <label>Model <input class="inp" v-model="form.model" /></label>
          <label>Location <input class="inp" v-model="form.location" /></label>
          <label>VIN <input class="inp" v-model="form.vin" /></label>
          <label>Year <input class="inp" type="number" v-model.number="form.yearOfManufacture" /></label>
          <label>Horse Power <input class="inp" type="number" v-model.number="form.horsePower" /></label>
          <label>Transmission <input class="inp" v-model="form.transmissionType" /></label>
          <label>Fuel <input class="inp" v-model="form.fuelType" /></label>
          <label>Type <input class="inp" v-model="form.type" /></label>
        </div>

        <label class="desc">
          Description
          <textarea class="inp ta" v-model="form.description" rows="5"></textarea>
        </label>

        <div class="actions">
          <button class="btn" @click="saveEdit">Save</button>
          <button class="btn ghost" @click="closeEdit">Cancel</button>
        </div>

        <div v-if="modalError" class="error">{{ modalError }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from "vue";
import { adminApi } from "@/api/adminService";
import type { Listing } from "@/types/listing";

const listings = ref<Listing[]>([]);
const loading = ref(false);
const error = ref("");
const q = ref("");

const editing = ref<any>(null);
const form = ref<any>({});
const modalError = ref("");

const filtered = computed(() => {
  const s = q.value.trim().toLowerCase();
  if (!s) return listings.value as any[];
  return (listings.value as any[]).filter((l: any) => {
    const text = `${l.id} ${l.title ?? ""} ${l.brand ?? ""} ${l.model ?? ""} ${l.location ?? ""}`.toLowerCase();
    return text.includes(s);
  });
});

async function load() {
  loading.value = true;
  error.value = "";
  try {
    listings.value = await adminApi.getListings();
  } catch (e: any) {
    error.value = e?.response?.data?.message ?? e?.message ?? "Failed to load listings";
  } finally {
    loading.value = false;
  }
}

function openEdit(l: any) {
  editing.value = l;
  modalError.value = "";
  form.value = {
    title: l.title ?? "",
    price: l.price ?? 0,
    brand: l.brand ?? "",
    model: l.model ?? "",
    location: l.location ?? "",
    vin: l.vin ?? l.VIN ?? "",
    yearOfManufacture: l.yearOfManufacture ?? 0,
    horsePower: l.horsePower ?? 0,
    transmissionType: l.transmissionType ?? "",
    fuelType: l.fuelType ?? "",
    type: l.type ?? "",
    description: l.description ?? "",
    sold: Boolean(l.sold),
    images: l.images ?? [],
  };
}

function closeEdit() {
  editing.value = null;
  form.value = {};
  modalError.value = "";
}

async function saveEdit() {
  if (!editing.value) return;
  modalError.value = "";
  try {
    // IMPORTANT: backend-ul tău updateListing așteaptă Listing entity.
    // Aici trimitem cât mai “compatibil”.
    const payload: any = {
      ...editing.value,
      ...form.value,
      VIN: form.value.vin, // dacă backend folosește VIN
      vin: form.value.vin,
    };

    const updated = await adminApi.updateListing(editing.value.id, payload);
    const idx = (listings.value as any[]).findIndex((x: any) => x.id === editing.value.id);
    if (idx >= 0) (listings.value as any[])[idx] = updated;
    closeEdit();
  } catch (e: any) {
    modalError.value = e?.response?.data?.message ?? e?.message ?? "Failed to update listing";
  }
}

async function toggleSold(l: any, ev: Event) {
  const sold = (ev.target as HTMLInputElement).checked;
  try {
    const updated = await adminApi.markListingSold(l.id, sold);
    const idx = (listings.value as any[]).findIndex((x: any) => x.id === l.id);
    if (idx >= 0) (listings.value as any[])[idx] = updated;
  } catch (e: any) {
    alert(
        e?.response?.data?.message ??
        e?.message ??
        "Failed to update sold. Verifică dacă ai câmpul 'sold' în backend."
    );
  }
}

async function remove(l: any) {
  const ok = confirm(`Delete listing #${l.id} (${l.title})?`);
  if (!ok) return;
  try {
    await adminApi.deleteListing(l.id);
    listings.value = (listings.value as any[]).filter((x: any) => x.id !== l.id) as any;
  } catch (e: any) {
    alert(e?.response?.data?.message ?? e?.message ?? "Failed to delete listing");
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
.ghost { background: #fafafa; }
.danger { border-color: #f2b8b5; background: #fff5f5; }
.table { width: 100%; border-collapse: collapse; }
th, td { text-align: left; padding: 12px 10px; border-bottom: 1px solid #f2f2f2; }
.right { text-align: right; }
.muted { color: #777; }
.error { margin-top: 10px; color: #b00020; }

.modal { position: fixed; inset: 0; background: rgba(0,0,0,.35); display: grid; place-items: center; padding: 18px; }
.modal-card { width: min(920px, 100%); background: #fff; border-radius: 14px; padding: 16px; border: 1px solid #eee; }
.modal-head { display: flex; justify-content: space-between; align-items: center; margin-bottom: 12px; }
.mtitle { font-weight: 800; font-size: 18px; }
.x { border: none; background: transparent; font-size: 18px; cursor: pointer; }
.grid { display: grid; grid-template-columns: repeat(3, minmax(0, 1fr)); gap: 12px; }
label { display: grid; gap: 6px; font-size: 12px; color: #555; }
.desc { margin-top: 10px; display: grid; gap: 6px; font-size: 12px; color: #555; }
.ta { resize: vertical; }
.actions { display: flex; gap: 10px; justify-content: flex-end; margin-top: 12px; }
@media (max-width: 980px) { .grid { grid-template-columns: 1fr; } }
</style>
