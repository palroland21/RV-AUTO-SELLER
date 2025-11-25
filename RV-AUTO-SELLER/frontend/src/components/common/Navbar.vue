<template>
  <nav
      class="navbar"
      :class="{
      'navbar-transparent': isTransparent && !isScrolled,
          'scrolled': isScrolled
    }"
  >
    <div class="navbar-container">
      <div class="logo">
        <router-link to="/">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="logo-icon"><path d="M19 17h2c.6 0 1-.4 1-1v-3c0-.9-.7-1.7-1.5-1.9C18.7 10.6 16 10 16 10s-1.3-1.4-2.2-2.3c-.5-.4-1.1-.7-1.8-.7H5c-.6 0-1.1.4-1.4.9l-1.4 2.9A3.7 3.7 0 0 0 2 12v4c0 .6.4 1 1 1h2"/><circle cx="7" cy="17" r="2"/><circle cx="17" cy="17" r="2"/></svg>
          RV Auto Seller
        </router-link>
      </div>

      <div class="nav-links">
        <router-link to="/">Home</router-link>
        <router-link to="/listings">Vehicles</router-link>
      </div>

      <div class="user-actions">
        <router-link to="/account" class="btn-account">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
          Account
        </router-link>

        <router-link to="/sell-form" class="btn-add-listing">Sell your car</router-link>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

defineProps({
  isTransparent: {
    type: Boolean,
    default: false
  }
});

const isScrolled = ref(false);

const handleScroll = () => {
  isScrolled.value = window.scrollY > 50;
};

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});

</script>

<style scoped>
.navbar {
  width: 100%;
  padding: 1rem 2rem;
  box-sizing: border-box;
  transition: all 0.3s ease;
  z-index: 1000;

  position: sticky;
  top: 0;

  background-color: var(--color-background);
  border-bottom: 1px solid var(--color-border);
}

.navbar-container {
  max-width: 1280px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.logo a {
  font-size: 1.5rem;
  font-weight: 700;
  color: var(--brand-primary-dark);
  text-decoration: none;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}
.logo-icon { stroke: var(--brand-primary-dark); }
.nav-links { display: flex; gap: 2rem; }
.nav-links a {
  text-decoration: none;
  color: var(--color-text);
  font-weight: 500;
  font-size: 1.2rem;
  transition: color 0.2s ease;
}
.nav-links a:hover { color: var(--brand-primary); }
.user-actions { display: flex; align-items: center; gap: 1rem; }
.btn-account {
  text-decoration: none;
  color: var(--color-text);
  font-weight: 500;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem 1rem;
  border: 1px solid var(--color-border);
  border-radius: 8px;
  transition: all 0.2s ease;
}
.btn-account:hover {
  border-color: var(--brand-text-light);
  color: var(--brand-text-light);
  background: var(--brand-accent-orange);
}
.btn-add-listing {
  text-decoration: none;
  background-color: var(--brand-primary-dark);
  color: var(--vt-c-white);
  border: none;
  padding: 0.7rem 1.5rem;
  border-radius: 8px;
  font-weight: 600;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.btn-add-listing:hover {
  text-decoration: none;
  background-color: var(--brand-primary);
}

.navbar.navbar-transparent {
  background-color: transparent;
  border-bottom: 1px solid transparent;
}
.navbar.navbar-transparent .logo a,
.navbar.navbar-transparent .nav-links a,
.navbar.navbar-transparent .btn-account {
  color: var(--vt-c-white);
  border-color: rgba(255, 255, 255, 0.5);
}
.navbar.navbar-transparent .logo-icon { stroke: var(--vt-c-white); }
.navbar.navbar-transparent .nav-links a:hover,
.navbar.navbar-transparent .btn-account:hover {
  color: var(--vt-c-white-mute);
  border-color: var(--vt-c-white-mute);
}

.navbar.scrolled {
  background-color: rgba(255, 255, 255, 0.85);

  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);

  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
  border-bottom: 1px solid rgba(0,0,0,0.05);

  padding: 0.8rem 2rem;
}


.navbar.scrolled .logo a,
.navbar.scrolled .nav-links a,
.navbar.scrolled .btn-account {
  color: var(--color-text);
  border-color: var(--color-border);
}
.navbar.scrolled .logo-icon { stroke: var(--brand-primary-dark); }
.navbar.scrolled .btn-add-listing {
  background-color: var(--brand-primary-dark);
  color: white;
}
</style>