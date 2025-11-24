<template>
  <Navbar />

  <div class="home-view">

    <section class="hero-section">
      <div class="hero-overlay"></div>
      <div class="hero-content">
        <h1>Explore and find your ideal car</h1>
        <p>Discover thousands of new and used car offers from verified and trusted dealers.</p>

        <div class="search-container">
          <input type="text" placeholder="Search by brand, model, or keywords..." />
          <button class="search-button">Search</button>
        </div>

        <div class="popular-searches">
          <span>Popular searches:</span>
          <a href="#">BMW</a>
          <a href="#">Mercedes</a>
          <a href="#">Audi</a>
          <a href="#">SUV</a>
        </div>
      </div>
    </section>

    <section class="features-section">
      <div class="feature-card">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path></svg>
        </div>
        <h3>Verified Dealers</h3>
        <p>All sellers are verified and evaluated by our community</p>
      </div>

      <div class="feature-card">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
        </div>
        <h3>Advanced filtering</h3>
        <p>Find the perfect car using our detailed filters</p>
      </div>

      <div class="feature-card">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path></svg>
        </div>
        <h3>Direct contact</h3>
        <p>Communicate directly with sellers through the platform</p>
      </div>

      <div class="feature-card">
        <div class="feature-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon></svg>
        </div>
        <h3>Real feedback</h3>
        <p>Read other buyers experiences before deciding</p>
      </div>
    </section>

    <section class="recommended-section">
      <div class="section-header">
        <h2>Recommended offers</h2>
        <p>Discover the best deals verified by our team</p>
      </div>

      <div class="listings-grid">
        <CarCard
            v-for="n in 4"
            :key="n"
            title=""
            :price="15000"
            :year="2023"
            :km="10000"
            fuel="Petrol"
            location="Bucharest"
            image=""
            :isRecommended="true"
        />
      </div>
    </section>

    <Footer />
  </div>
</template>

<script setup>
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
import CarCard from "@/components/common/CarCard.vue";
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
  background-color: rgba(11, 45, 106, 0.7);
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
  color: white;
}

.hero-content p {
  font-size: 1.1rem;
  margin-bottom: 2rem;
  color: rgba(255, 255, 255, 0.9);
}

.search-container {
  display: flex;
  background-color: var(--vt-c-white);
  border-radius: 50px;
  padding: 0.4rem;
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
  padding: 0.7rem 2rem;
  border-radius: 50px;
  cursor: pointer;
  font-weight: bold;
}

.popular-searches {
  margin-top: 1rem;
  font-size: 0.9rem;
}
.popular-searches span { color: rgba(255,255,255,0.8); margin-right: 0.5rem; }
.popular-searches a {
  color: white;
  text-decoration: none;
  font-weight: bold;
  margin: 0 0.25rem;
  padding: 0.2rem 0.6rem;
  border: 1px solid rgba(255,255,255,0.4);
  border-radius: 20px;
}

.features-section {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
  max-width: 1280px;
  margin: 4rem auto;
  padding: 0 2rem;
}


.feature-card {
  background-color: var(--vt-c-white);
  border: 1px solid var(--color-border);
  border-radius: 16px;
  padding: 2rem;
  text-align: center;
  transition: transform 0.3s ease, box-shadow 0.3s ease;

  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
  border-color: var(--brand-primary);
}

.feature-icon {
  color: var(--brand-primary);
  background-color: var(--color-background-soft);
  width: 64px;
  height: 64px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto 1rem auto;
}

.feature-card h3 {
  font-size: 1.25rem;
  font-weight: bold;
  color: var(--vt-c-black);
  margin-bottom: 0.5rem;
}

.feature-card p {
  font-size: 0.95rem;
  color: var(--vt-c-text-light-2);
  line-height: 1.5;
}

.recommended-section {
  max-width: 1280px;
  margin: 4rem auto 6rem;
  padding: 0 2rem;
}

.section-header {
  text-align: center;
  margin-bottom: 2.5rem;
}

.section-header h2 {
  font-size: 2rem;
  font-weight: 800;
  color: var(--brand-primary-dark);
  margin-bottom: 0.5rem;
}

.section-header p {
  font-size: 1.1rem;
  color: var(--vt-c-text-light-2);
}

.listings-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2rem;
}
</style>