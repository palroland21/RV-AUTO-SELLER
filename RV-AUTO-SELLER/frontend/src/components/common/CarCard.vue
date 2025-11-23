<template>
  <div class="car-card">
    <div class="card-image-wrapper">
      <div v-if="isRecommended" class="badge-recommended">Recomandat</div>
      <img :src="image" :alt="title" class="car-image" />
    </div>

    <div class="card-content">
      <h3 class="car-title">{{ title }}</h3>
      <div class="car-price">€{{ price.toLocaleString() }}</div>

      <div class="car-details">
        <div class="detail-item">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>
          <span>{{ year }}</span>
        </div>
        <div class="detail-item">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>
          <span>{{ km.toLocaleString() }} km</span>
        </div>
        <div class="detail-item">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 22v-8a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v8"/><path d="M12 2v20"/><path d="M7 7h10"/></svg>
          <span>{{ fuel }}</span>
        </div>
        <div class="detail-item">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"></path><circle cx="12" cy="10" r="3"></circle></svg>
          <span>{{ location }}</span>
        </div>
      </div>

      <button class="btn-details">Vezi detalii</button>
    </div>
  </div>
</template>

<script setup>
defineProps({
  image: String,
  title: String,
  price: Number,
  year: Number,
  km: Number,
  fuel: String,
  location: String,
  isRecommended: Boolean
});
</script>

<style scoped>
.car-card {
  background-color: var(--vt-c-white);
  border: 1px solid var(--color-border);
  border-radius: 12px;
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  flex-direction: column;
}

.car-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0,0,0,0.1);
}

.card-image-wrapper {
  position: relative;
  height: 200px;
  overflow: hidden;
  background-color: #f3f4f6;
}

.car-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.badge-recommended {
  position: absolute;
  top: 10px;
  left: 10px;
  background-color: var(--brand-accent-orange);
  color: white;
  font-size: 0.75rem;
  font-weight: bold;
  padding: 0.3rem 0.8rem;
  border-radius: 20px;
  z-index: 2;
}

.card-content {
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.car-title {
  font-size: 1.1rem;
  font-weight: 700;
  color: var(--vt-c-black);
  margin: 0;
}

.car-price {
  font-size: 1.5rem;
  font-weight: 800;
  color: var(--brand-primary-dark);
}

.car-details {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 0.8rem;
  margin-bottom: 0.5rem;
}

.detail-item {
  display: flex;
  align-items: center;
  gap: 0.4rem;
  font-size: 0.9rem;
  color: var(--vt-c-text-light-2);
}

.detail-item svg {
  stroke: #9ca3af;
}


.btn-details {
  background-color: var(--brand-primary-dark);
  color: white;
  border: none;
  padding: 0.8rem;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s;
  width: 100%;
  margin-top: auto;
}

.btn-details:hover {
  background-color: var(--brand-primary);
}
</style>