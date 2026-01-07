<template>
  <div class="admin">
    <aside class="sidebar">
      <div class="brand">Admin</div>

      <nav class="nav">
        <RouterLink class="link" to="/admin">Dashboard</RouterLink>
        <RouterLink class="link" to="/admin/users">Users</RouterLink>
        <RouterLink class="link" to="/admin/listings">Listings</RouterLink>
        <RouterLink class="link" to="/admin/appointments">Appointments</RouterLink>
        <RouterLink class="link" to="/admin/feedbacks">Feedbacks</RouterLink>
      </nav>

      <div class="hint">RV Auto Seller</div>
    </aside>

    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.admin { display: grid; grid-template-columns: 260px 1fr; min-height: 100vh; }
.sidebar { padding: 18px; border-right: 1px solid #e5e5e5; background: #fff; position: sticky; top: 0; height: 100vh; }
.brand { font-weight: 800; font-size: 20px; margin-bottom: 14px; }
.nav { display: flex; flex-direction: column; gap: 8px; }
.link { padding: 10px 12px; border-radius: 10px; text-decoration: none; color: #111; }
.link.router-link-active { background: #f2f2f2; }
.content { padding: 22px; background: #fafafa; }
.hint { margin-top: 18px; font-size: 12px; color: #666; }
</style>
