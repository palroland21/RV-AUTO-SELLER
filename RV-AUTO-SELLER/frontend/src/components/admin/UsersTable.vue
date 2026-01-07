<template>
  <div class="wrap">
    <div class="toolbar">
      <input v-model="q" class="inp" placeholder="Search by username/email/name..." />
      <button class="btn" @click="load">Reload</button>
    </div>

    <div v-if="loading" class="muted">Loading...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <table v-if="!loading" class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Username</th>
        <th>Email</th>
        <th>Telephone</th>
        <th>Role</th>
        <th class="right">Actions</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="u in filtered" :key="u.id">
        <td>{{ u.id }}</td>
        <td>{{ u.firstName }} {{ u.lastName }}</td>
        <td>{{ u.username }}</td>
        <td>{{ u.email }}</td>
        <td>{{ u.telephone }}</td>

        <td>
          <select class="sel" :value="u.role" @change="onRoleChange(u, $event)">
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
        </td>

        <td class="right">
          <button class="btn ghost" @click="openEdit(u)">Edit</button>
          <button class="btn danger" @click="remove(u)">Delete</button>
        </td>
      </tr>

      <tr v-if="filtered.length === 0">
        <td colspan="7" class="muted">No users found</td>
      </tr>
      </tbody>
    </table>

    <!-- Modal edit -->
    <div v-if="editing" class="modal">
      <div class="modal-card">
        <div class="modal-head">
          <div class="mtitle">Edit user #{{ editing.id }}</div>
          <button class="x" @click="closeEdit">✕</button>
        </div>

        <div class="grid">
          <label>
            First name
            <input class="inp" v-model="form.firstName" />
          </label>
          <label>
            Last name
            <input class="inp" v-model="form.lastName" />
          </label>
          <label>
            Username
            <input class="inp" v-model="form.username" />
          </label>
          <label>
            Email
            <input class="inp" v-model="form.email" />
          </label>
          <label>
            Telephone
            <input class="inp" v-model="form.telephone" />
          </label>
          <label>
            New password (optional)
            <input class="inp" type="password" v-model="form.newPassword" />
          </label>
        </div>

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
import type { AdminUserUpdateRequest } from "@/types/admin";
import type { User } from "@/types/User";

const users = ref<User[]>([]);
const loading = ref(false);
const error = ref("");
const q = ref("");

const editing = ref<User | null>(null);
const form = ref<AdminUserUpdateRequest>({});
const modalError = ref("");

const filtered = computed(() => {
  const s = q.value.trim().toLowerCase();
  if (!s) return users.value;
  return users.value.filter((u: any) => {
    const text = `${u.id} ${u.firstName ?? ""} ${u.lastName ?? ""} ${u.username ?? ""} ${u.email ?? ""}`.toLowerCase();
    return text.includes(s);
  });
});

async function load() {
  loading.value = true;
  error.value = "";
  try {
    users.value = await adminApi.getUsers();
  } catch (e: any) {
    error.value = e?.response?.data?.message ?? e?.message ?? "Failed to load users";
  } finally {
    loading.value = false;
  }
}

function openEdit(u: User) {
  editing.value = u;
  modalError.value = "";
  form.value = {
    firstName: (u as any).firstName ?? "",
    lastName: (u as any).lastName ?? "",
    username: (u as any).username ?? "",
    email: (u as any).email ?? "",
    telephone: (u as any).telephone ?? "",
    newPassword: "",
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
    const updated = await adminApi.updateUser((editing.value as any).id, form.value);
    const idx = users.value.findIndex((x: any) => x.id === (editing.value as any).id);
    if (idx >= 0) users.value[idx] = updated as any;
    closeEdit();
  } catch (e: any) {
    modalError.value = e?.response?.data?.message ?? e?.message ?? "Failed to update user";
  }
}

async function onRoleChange(u: any, ev: Event) {
  const role = (ev.target as HTMLSelectElement).value as "USER" | "ADMIN";
  try {
    const updated = await adminApi.updateUserRole(u.id, { role });
    const idx = users.value.findIndex((x: any) => x.id === u.id);
    if (idx >= 0) users.value[idx] = updated as any;
  } catch (e: any) {
    alert(e?.response?.data?.message ?? e?.message ?? "Failed to change role");
  }
}

async function remove(u: any) {
  const ok = confirm(`Delete user #${u.id} (${u.username})?`);
  if (!ok) return;
  try {
    await adminApi.deleteUser(u.id);
    users.value = users.value.filter((x: any) => x.id !== u.id);
  } catch (e: any) {
    alert(e?.response?.data?.message ?? e?.message ?? "Failed to delete user");
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
.sel { padding: 8px 10px; border-radius: 10px; border: 1px solid #ddd; }
.muted { color: #777; }
.error { margin-top: 10px; color: #b00020; }

.modal { position: fixed; inset: 0; background: rgba(0,0,0,.35); display: grid; place-items: center; padding: 18px; }
.modal-card { width: min(820px, 100%); background: #fff; border-radius: 14px; padding: 16px; border: 1px solid #eee; }
.modal-head { display: flex; justify-content: space-between; align-items: center; margin-bottom: 12px; }
.mtitle { font-weight: 800; font-size: 18px; }
.x { border: none; background: transparent; font-size: 18px; cursor: pointer; }
.grid { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 12px; }
label { display: grid; gap: 6px; font-size: 12px; color: #555; }
.actions { display: flex; gap: 10px; justify-content: flex-end; margin-top: 12px; }
@media (max-width: 780px) { .grid { grid-template-columns: 1fr; } }
</style>
