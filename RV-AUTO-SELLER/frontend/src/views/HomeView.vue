
<template>
  <Navbar />

  <div class="home-view">
    <section class="hero-section">
      <div class="hero-overlay"></div>
      <div class="hero-content">
        <h1>Găsește mașina perfectă</h1>
        <p>Descoperă mii de oferte de mașini noi și second-hand de la dealeri verificați</p>

        <div class="search-container">
          <input type="text" placeholder="Caută după marcă, model sau cuvinte cheie..." />
          <button class="search-button">Caută</button>
        </div>

        <div class="popular-searches">
          <span>Căutări populare:</span>
          <a href="#">BMW</a>
          <a href="#">Mercedes</a>
          <a href="#">Audi</a>
          <a href="#">SUV</a>
        </div>
      </div>
    </section>

    <section class="features-section">
      <div class="feature-card">
        <h3>Dealeri verificați</h3>
        <p>Toți vânzătorii sunt verificați și evaluați de comunitatea noastră</p>
      </div>
      <div class="feature-card">
        <h3>Filtrare avansată</h3>
        <p>Găsește mașina perfectă folosind filtrele noastre detaliate</p>
      </div>
      <div class="feature-card">
        <h3>Contact direct</h3>
        <p>Comunică direct cu vânzătorii prin platformă</p>
      </div>
      <div class="feature-card">
        <h3>Recenzii reale</h3>
        <p>Citește experiențele altor cumpărători înainte de a decide</p>
      </div>
    </section>

    <section class="recommended-section">
      <h2>Oferte recomandate</h2>
      <p>Descoperă cele mai bune oferte verificate de echipa noastră</p>

      <div class="listings-grid">
        <div class="car-card-placeholder">Card Mașină 1 (SUV Modern)</div>
        <div class="car-card-placeholder">Card Mașină 2 (Sedan Luxury)</div>
        <div class="car-card-placeholder">Card Mașină 3 (Minivan Familial)</div>
        <div class="car-card-placeholder">Card Mașină 4 (Hatchback)</div>
      </div>
    </section>
    <Footer />
  </div>
</template>

<script setup lang="ts">
  import Navbar from "@/components/common/Navbar.vue";
  import Footer from "@/components/common/Footer.vue";
</script>

<style scoped>
.hero-section {
  position: relative;
  height: 70vh;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  color: var(--brand-text-light);
  background-image: url('/images/hero-car.jpg');
  background-size: cover;
  background-position: center;
}

.hero-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(11, 45, 106, 0.85);
  z-index: 1;
}

.hero-content {
  position: relative;
  z-index: 2;
  max-width: 800px;
  padding: 1rem;
}

.hero-content h1 {
  font-size: 3rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.hero-content p {
  font-size: 1.1rem;
  margin-bottom: 2rem;
  color: var(--vt-c-text-dark-2);
}

.search-container {
  display: flex;
  background-color: var(--vt-c-white);
  border-radius: 50px;
  padding: 0.5rem;
  max-width: 600px;
  margin: 0 auto;
}

.search-container input {
  border: none;
  outline: none;
  flex-grow: 1;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  background: transparent;
  color: var(--vt-c-black);
}

.search-button {
  background-color: var(--brand-primary);
  color: var(--brand-text-light);
  border: none;
  padding: 0.7rem 1.5rem;
  border-radius: 50px;
  cursor: pointer;
  font-weight: 500;
}

.popular-searches {
  margin-top: 1rem;
  font-size: 0.9rem;
}

.popular-searches span {
  margin-right: 0.5rem;
  color: var(--vt-c-text-dark-2);
}

.popular-searches a {
  color: var(--brand-text-light);
  text-decoration: none;
  font-weight: bold;
  margin: 0 0.25rem;
  padding: 0.2rem 0.5rem;
  border: 1px solid var(--brand-text-light);
  border-radius: 20px;
  transition: background-color 0.3s;
}

.popular-searches a:hover {
  background-color: rgba(255, 255, 255, 0.2);
}

.features-section {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
  max-width: 1200px;
  margin: 4rem auto;
  padding: 0 2rem;
  text-align: center;
}

.feature-card h3 {
  font-size: 1.25rem;
  font-weight: bold;
  color: var(--brand-primary-dark);
  margin-bottom: 0.5rem;
}

.feature-card p {
  font-size: 0.95rem;
  color: var(--vt-c-text-light-2);
  line-height: 1.5;
}

.recommended-section {
  max-width: 1200px;
  margin: 4rem auto;
  padding: 0 2rem;
  text-align: center;
}

.recommended-section h2 {
  font-size: 2rem;
  font-weight: bold;
  color: var(--brand-primary-dark);
  margin-bottom: 0.5rem;
}

.recommended-section p {
  font-size: 1.1rem;
  color: var(--vt-c-text-light-2);
  margin-bottom: 2rem;
}

.listings-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 1.5rem;
}

.car-card-placeholder {
  border: 1px solid var(--color-border);
  border-radius: 8px;
  padding: 1rem;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--color-background-soft);
}
</style>

