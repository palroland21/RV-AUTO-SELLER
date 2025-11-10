<template>
  <nav class="navbar" :class="{ 'navbar-transparent': isTransparent }">
    <div class="navbar-container">
      <div class="logo">
        <a href="/">RV Auto Seller</a>
      </div>
      <div class="navbar-links">
        <a href="/">Acasă</a>
        <a href="/listings">Anunțuri</a>
        <a href="/sell">Vinde</a>
      </div>
      <div class="user-actions">
        <a href="/acccont" class="btn-account">Contul meu</a>
        <button class="btn-add-listing">Adaugă anunț</button>
      </div>
    </div>
  </nav>
</template>

<script setup>
defineProps({
  isTransparent: {
    type: Boolean,
    default: false
  }
});
</script>

<style scoped>
.navbar {
  width: 100%;
  background-color: var(--color-background);
  border-bottom: 1px solid var(--color-border);
  padding: 1rem 2rem;
  box-sizing: border-box;
  transition: background-color 0.3s ease;
  z-index: 1000;
}

.navbar-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo a {
  font-size: 1.5rem;
  font-weight: bold;
  color: var(--brand-primary-dark);
  text-decoration: none;
}

.nav-links {
  display: flex;
  gap: 1.5rem;
}

.nav-links a {
  text-decoration: none;
  color: var(--color-text);
  font-weight: 500;
  transition: color 0.3s ease;
}

.nav-links a:hover {
  color: var(--brand-primary);
}

.user-actions {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.btn-account {
  text-decoration: none;
  color: var(--color-text);
  font-weight: 500;
}

.btn-add-listing {
  background-color: var(--brand-primary);
  color: var(--brand-text-light);
  border: none;
  padding: 0.6rem 1.2rem;
  border-radius: 6px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-add-listing:hover {
  background-color: var(--brand-primary-dark);
}

.navbar.navbar-transparent {
  background-color: transparent;
  border-bottom: none;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
}

.navbar.navbar-transparent .logo a,
.navbar.navbar-transparent .nav-links a,
.navbar.navbar-transparent .btn-account {
  color: var(--brand-text-light);
}

.navbar.navbar-transparent .nav-links a:hover {
  color: var(--vt-c-text-dark-2);
}
</style>