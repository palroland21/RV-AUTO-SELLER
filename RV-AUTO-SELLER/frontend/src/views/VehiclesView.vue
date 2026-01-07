<template>
  <Navbar />

  <main class="vehicles-page">
    <div class="page-container">

      <aside class="filters-sidebar">
        <div class="filters-header">
          <h2>Filters</h2>
          <button class="btn-reset" @click="resetFilters">Reset</button>
        </div>

        <form @submit.prevent="applyFilters" class="filters-form">
          <div class="filter-group">
            <label>Search</label>
            <input type="text" v-model="filters.keyword" placeholder="Brand, Model..." />
          </div>

          <div class="filter-group">
            <label>Brand</label>
            <select v-model="filters.brand">
              <option value="">All Brands</option>
              <option v-for="brand in enumOptions.brands" :key="brand" :value="brand">{{ brand }}</option>
            </select>
          </div>

          <div class="filter-group">
            <label>Price (€)</label>
            <div class="range-inputs">
              <input type="number" v-model="filters.minPrice" placeholder="Min" />
              <span>-</span>
              <input type="number" v-model="filters.maxPrice" placeholder="Max" />
            </div>
          </div>

          <div class="filter-group">
            <label>Year</label>
            <div class="range-inputs">
              <input type="number" v-model="filters.minYear" placeholder="From" />
              <span>-</span>
              <input type="number" v-model="filters.maxYear" placeholder="To" />
            </div>
          </div>

          <div class="filter-group">
            <label>Fuel Type</label>
            <select v-model="filters.fuelType">
              <option value="">Any</option>
              <option v-for="fuel in enumOptions.fuelTypes" :key="fuel" :value="fuel">{{ fuel }}</option>
            </select>
          </div>

          <button type="submit" class="btn-apply">Apply Filters</button>
        </form>
      </aside>

      <section class="listings-content">
        <div class="content-header">
          <h1>Available Vehicles</h1>
          <p class="results-count">{{ filteredCars.length }} results found</p>
        </div>

        <div v-if="isLoading" class="loading-container">
          <div class="spinner"></div>
          <p>Loading listings...</p>
        </div>

        <div v-else-if="filteredCars.length === 0" class="empty-state">
          <h3>No vehicles found</h3>
          <p>Try adjusting your search filters.</p>
        </div>

        <div v-else class="listings-grid">
          <CarCard
              v-for="car in filteredCars"
              :key="car.id"
              :id="car.id"       :image="getMainImage(car)"
              :title="car.title"
              :price="car.price"
              :year="car.yearOfManufacture"
              :km="car.horsePower"
              :fuel="car.fuelType"
              :location="car.location"
              :isRecommended="false"
          />
        </div>
      </section>

    </div>
  </main>

  <Footer />
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue';
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
import CarCard from "@/components/common/CarCard.vue";
import axios from 'axios';

// --- STATE ---
const allCars = ref([]); // Lista completa de la server
const isLoading = ref(true);

// State pentru filtre
const filters = reactive({
  keyword: '',
  brand: '',
  minPrice: null,
  maxPrice: null,
  minYear: null,
  maxYear: null,
  fuelType: ''
});

// Optiuni pentru dropdown-uri
const enumOptions = reactive({
  brands: [],
  fuelTypes: []
});

const BASE_IMAGE_URL = 'http://localhost:9090/uploads/';

const getMainImage = (car) => {
  const imagesList = car.images;

  if (imagesList && imagesList.length > 0) {
    // Luăm valoarea din baza de date
    let imagePath = imagesList[0].url;

    if (imagePath.includes('uploads')) {
      imagePath = imagePath.split(/uploads[\\/]/).pop();
    }
    if (imagePath.startsWith('/') || imagePath.startsWith('\\')) {
      imagePath = imagePath.substring(1);
    }

    return `${BASE_IMAGE_URL}${imagePath}`;
  }

  // Fallback
  return 'https://placehold.co/600x400?text=No+Image';
};

// --- API CALLS ---
const loadEnums = async () => {
  try {
    const response = await axios.get('http://localhost:9090/api/utils/enums');
    enumOptions.brands = response.data.brands;
    enumOptions.fuelTypes = response.data.fuelTypes;
  } catch (error) {
    console.error("Nu am putut incarca filtrele:", error);
  }
};

const fetchListings = async () => {
  isLoading.value = true;
  try {
    // Luam toate anunturile
    const response = await axios.get('http://localhost:9090/listing/listings');
    allCars.value = response.data;

    // DEBUG: Vezi in consola structura exacta a datelor pentru a verifica numele campului de imagini
    console.log("Date primite de la server:", allCars.value);

  } catch (error) {
    console.error("Eroare la incarcarea anunturilor:", error);
  } finally {
    isLoading.value = false;
  }
};

// --- FILTRARE (Computed Property) ---
// Filtram direct in frontend pentru rapiditate
const filteredCars = computed(() => {
  return allCars.value.filter(car => {
    // Filtru Keyword (titlu sau descriere)
    if (filters.keyword) {
      const k = filters.keyword.toLowerCase();
      const title = (car.title || '').toLowerCase();
      const desc = (car.description || '').toLowerCase();
      if (!title.includes(k) && !desc.includes(k)) return false;
    }

    // Filtru Brand
    if (filters.brand && car.brand !== filters.brand) return false;

    // Filtru Pret
    if (filters.minPrice && car.price < filters.minPrice) return false;
    if (filters.maxPrice && car.price > filters.maxPrice) return false;

    // Filtru An
    if (filters.minYear && car.yearOfManufacture < filters.minYear) return false;
    if (filters.maxYear && car.yearOfManufacture > filters.maxYear) return false;

    // Filtru Combustibil
    if (filters.fuelType && car.fuelType !== filters.fuelType) return false;

    return true;
  });
});

const applyFilters = () => {
  // Deoarece folosim computed property 'filteredCars', aplicarea e automata.
  // Acest buton poate fi folosit doar pentru UX sau daca muti filtrarea pe backend.
  console.log("Filtre aplicate");
};

const resetFilters = () => {
  filters.keyword = '';
  filters.brand = '';
  filters.minPrice = null;
  filters.maxPrice = null;
  filters.minYear = null;
  filters.maxYear = null;
  filters.fuelType = '';
};

onMounted(() => {
  loadEnums();
  fetchListings();
});
</script>

<style scoped>
.vehicles-page {
  background-color: #f8f9fa;
  min-height: 80vh;
  padding: 2rem 0;
}

.page-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1.5rem;
  display: grid;
  grid-template-columns: 280px 1fr;
  gap: 2rem;
  align-items: start;
}

/* SIDEBAR */
.filters-sidebar {
  background: white;
  padding: 1.5rem;
  border-radius: 12px;
  border: 1px solid #e5e7eb;
  position: sticky;
  top: 2rem;
  box-shadow: 0 2px 8px rgba(0,0,0,0.04);
}

.filters-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
  border-bottom: 1px solid #eee;
  padding-bottom: 0.8rem;
}

.filters-header h2 {
  font-size: 1.2rem;
  font-weight: 700;
  margin: 0;
}

.btn-reset {
  background: none;
  border: none;
  color: var(--brand-primary);
  font-size: 0.9rem;
  cursor: pointer;
  text-decoration: underline;
}

.filter-group {
  margin-bottom: 1.2rem;
}

.filter-group label {
  display: block;
  font-size: 0.9rem;
  font-weight: 600;
  color: #374151;
  margin-bottom: 0.4rem;
}

input, select {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  font-size: 0.95rem;
  box-sizing: border-box;
}

.range-inputs {
  display: flex;
  gap: 0.5rem;
  align-items: center;
}

.range-inputs input {
  text-align: center;
}

.btn-apply {
  width: 100%;
  background-color: #1a4a9c; /* Culoarea din poza ta */
  color: white;
  border: none;
  padding: 0.8rem;
  border-radius: 6px;
  font-weight: 600;
  cursor: pointer;
  margin-top: 0.5rem;
}

.btn-apply:hover {
  background-color: #153e85;
}

/* CONTENT */
.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.content-header h1 {
  font-size: 1.8rem;
  font-weight: 800;
  color: #111827;
}

.results-count {
  color: #6b7280;
}

.listings-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1.5rem;
}

/* Loading & Empty States */
.loading-container, .empty-state {
  text-align: center;
  padding: 4rem;
  background: white;
  border-radius: 12px;
  border: 1px solid #eee;
}

.spinner {
  margin: 0 auto 1rem;
  width: 40px;
  height: 40px;
  border: 4px solid #f3f3f3;
  border-top: 4px solid #1a4a9c;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin { to { transform: rotate(360deg); } }

/* RESPONSIVE */
@media (max-width: 900px) {
  .page-container {
    grid-template-columns: 1fr;
  }
  .filters-sidebar {
    position: static;
    margin-bottom: 2rem;
  }
}
</style>