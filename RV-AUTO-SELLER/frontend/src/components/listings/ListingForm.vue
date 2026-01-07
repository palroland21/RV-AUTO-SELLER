<template>
  <Navbar />
  <div class="listing-form-container">

    <div class="header-section">
      <h1>Add New Listing</h1>
      <p>Complete the details about your car as shown in the registration documents.</p>
    </div>

    <div v-if="!isAuthenticated" class="auth-wall">
      <div class="auth-content">
        <div class="icon-circle">
          <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="8.5" cy="7" r="4"></circle><line x1="20" y1="8" x2="20" y2="14"></line><line x1="23" y1="11" x2="17" y2="11"></line></svg>
        </div>
        <h2>Authentication Required</h2>
        <p>You must be logged in to post a new car listing. Please login to your account or register if you don't have one yet.</p>

        <div class="auth-buttons">
          <button @click="router.push('/login')" class="btn-primary">Login</button>
          <button @click="router.push('/register')" class="btn-secondary">Create Account</button>
        </div>
      </div>
    </div>

    <div v-else>
      <div v-if="successMessage" class="alert alert-success">
        <div class="alert-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11.01"></polyline></svg>
        </div>
        <span>{{ successMessage }}</span>
      </div>

      <div v-if="errorMessage" class="alert alert-error">
        <div class="alert-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>
        </div>
        <span>{{ errorMessage }}</span>
      </div>

      <form @submit.prevent="submitListing" class="form-content">
        <section class="form-card">
          <h2>General Information</h2>
          <div class="form-grid">
            <div class="form-group full-width">
              <label>Listing Title *</label>
              <input type="text" v-model="formData.title" placeholder="e.g. Volkswagen Golf 7 2.0 TDI" required />
            </div>

            <div class="form-group">
              <label>Brand *</label>
              <div class="select-wrapper">
                <select v-model="formData.brand" required>
                  <option value="" disabled>Select Brand</option>
                  <option v-for="brand in enumOptions.brands" :key="brand" :value="brand">{{ brand }}</option>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label>Model *</label>
              <input type="text" v-model="formData.model" placeholder="e.g. Golf 7" required />
            </div>

            <div class="form-group">
              <label>Body Type *</label>
              <div class="select-wrapper">
                <select v-model="formData.type" required>
                  <option value="" disabled>Select Type</option>
                  <option v-for="type in enumOptions.carTypes" :key="type" :value="type">{{ type }}</option>
                </select>
              </div>
            </div>
          </div>
        </section>

        <section class="form-card">
          <h2>Technical Specifications</h2>
          <div class="form-grid">
            <div class="form-group">
              <label>Year of Manufacture *</label>
              <input type="number" v-model="formData.yearOfManufacture" placeholder="2017" min="1900" :max="new Date().getFullYear()" required />
            </div>

            <div class="form-group">
              <label>Power (HP) *</label>
              <input type="number" v-model="formData.horsePower" placeholder="150" min="1" required />
            </div>

            <div class="form-group">
              <label>Price (€) *</label>
              <input type="number" v-model="formData.price" placeholder="11500" min="0" required />
            </div>

            <div class="form-group">
              <label>Fuel Type *</label>
              <div class="select-wrapper">
                <select v-model="formData.fuelType" required>
                  <option value="" disabled>Select Fuel</option>
                  <option v-for="fuel in enumOptions.fuelTypes" :key="fuel" :value="fuel">{{ fuel }}</option>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label>Transmission *</label>
              <div class="select-wrapper">
                <select v-model="formData.transmissionType" required>
                  <option value="" disabled>Select Transmission</option>
                  <option v-for="trans in enumOptions.transmissionTypes" :key="trans" :value="trans">{{ trans }}</option>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label>VIN (Vehicle Identification Number)</label>
              <input type="text" v-model="formData.vin" placeholder="e.g. WVWZZZ..." maxlength="17" style="text-transform: uppercase;" />
            </div>

            <div class="form-group full-width">
              <label>Location *</label>
              <input type="text" v-model="formData.location" placeholder="e.g. Cluj-Napoca" required />
            </div>
          </div>
        </section>

        <section class="form-card">
          <h2>Description</h2>
          <div class="form-group full-width">
            <textarea
                v-model="formData.description"
                placeholder="Describe the car's condition, features, service history, upgrades..."
                rows="6"
                required
                maxlength="1000"
            ></textarea>
            <div class="char-count">{{ formData.description.length }}/1000 characters</div>
          </div>
        </section>

        <section class="form-card">
          <h2>Photos</h2>
          <p class="subtitle">Add quality photos to increase your chances of selling (Max 10).</p>
          <ImageUploader @update:images="handleImagesUpdate" />
        </section>

        <div class="form-actions">
          <button type="button" class="btn-secondary" @click="router.go(-1)">Cancel</button>

          <button type="submit" class="btn-primary" :disabled="isSubmitting">
            <span v-if="isSubmitting" class="spinner"></span>
            <span>{{ isSubmitting ? 'Publishing...' : 'Publish Listing' }}</span>
          </button>
        </div>

      </form>
    </div>
  </div>
  <Footer />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import type { ListingFormData, ListingResponse } from '@/types/listing';
import ImageUploader from './ImageUploader.vue';
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";

const isSubmitting = ref(false);
const listingImages = ref<File[]>([]);
const errorMessage = ref('');
const successMessage = ref('');
// NOU: Stare pentru autentificare
const isAuthenticated = ref(false);

const router = useRouter();
const route = useRoute();

const formData = reactive<ListingFormData>({
  title: '',
  brand: '',
  model: '',
  type: '',
  yearOfManufacture: null,
  horsePower: null,
  price: null,
  fuelType: '',
  transmissionType: '',
  vin: '',
  location: '',
  description: '',
  username: ''
});

const enumOptions = reactive({
  brands: [] as string[],
  carTypes: [] as string[],
  fuelTypes: [] as string[],
  transmissionTypes: [] as string[]
});

const loadEnums = async () => {
  try {
    const response = await fetch('http://localhost:9090/api/utils/enums');
    if (!response.ok) throw new Error("Failed to fetch enums");

    const data = await response.json();
    enumOptions.brands = data.brands;
    enumOptions.carTypes = data.carTypes;
    enumOptions.fuelTypes = data.fuelTypes;
    enumOptions.transmissionTypes = data.transmissionTypes;
  } catch (error) {
    console.error("Enum Error:", error);
    errorMessage.value = "Failed to load form options. Check backend connection.";
  }
};

onMounted(() => {
  // NOU: Verificăm token-ul la intrarea în pagină
  const token = localStorage.getItem('token');
  isAuthenticated.value = !!token; // Devine true dacă există token, false altfel

  // Încărcăm datele DOAR dacă e logat
  if (isAuthenticated.value) {
    loadEnums();
  }
});

const handleImagesUpdate = (files: File[]) => {
  listingImages.value = files;
};

function parseJwt(token: string) {
  try {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
  } catch (e) {
    return null;
  }
}

const submitListing = async () => {
  isSubmitting.value = true;
  errorMessage.value = '';
  successMessage.value = '';

  try {
    const submitData = new FormData();
    const token = localStorage.getItem('token');

    // Verificare extra și la submit
    if (!token) {
      // Dacă cumva a expirat tokenul cât stătea pe pagină
      isAuthenticated.value = false;
      throw new Error("You must be logged in to publish a listing!");
    }

    const decodedToken = parseJwt(token);
    const usernameFromToken = decodedToken ? (decodedToken.username || decodedToken.sub) : null;

    if(usernameFromToken) {
      submitData.append('username', usernameFromToken);
    } else {
      throw new Error("Invalid Token: Could not extract username.");
    }

    (Object.keys(formData) as Array<keyof ListingFormData>).forEach(key => {
      const value = formData[key];
      if (value !== null && value !== undefined && value !== '') {
        submitData.append(key, value.toString());
      }
    });

    listingImages.value.forEach(file => {
      submitData.append('images', file);
    });

    const response = await fetch('http://localhost:9090/listing/new_listing', {
      method: 'POST',
      headers: { 'Authorization': `Bearer ${token}` },
      body: submitData
    });

    if (!response.ok) {
      const errorText = await response.text();
      // Dacă primim eroare 401/403 de la backend, forțăm logout
      if (response.status === 401 || response.status === 403) {
        localStorage.removeItem('token');
        isAuthenticated.value = false;
        throw new Error("Session expired. Please login again.");
      }
      throw new Error(errorText || 'Server Error');
    }

    const responseData: ListingResponse = await response.json();
    successMessage.value = `Listing "${responseData.title}" published successfully!`;
    window.scrollTo({ top: 0, behavior: 'smooth' });
    setTimeout(() => {
      router.push('/listings');
    }, 4000);

  } catch (error: any) {
    console.error('Submission Error:', error);
    errorMessage.value = error.message || 'An unexpected error occurred.';
    window.scrollTo({ top: 0, behavior: 'smooth' });
  } finally {
    isSubmitting.value = false;
  }
};

const goToLogin = () => {
  router.push({
    path: '/login',
    query: { redirect: route.fullPath } // Trimitem calea curentă ca parametru
  });
};
</script>

<style scoped>
/* AICI ESTE CSS-UL NOU PENTRU AUTH WALL */
.auth-wall {
  background: var(--vt-c-white);
  border: 1px solid var(--color-border);
  border-radius: 16px;
  padding: 4rem 2rem;
  text-align: center;
  margin: 2rem 0;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.03);
}

.auth-content {
  max-width: 450px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1.5rem;
}

.auth-content h2 {
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--vt-c-black);
  margin: 0;
}

.auth-content p {
  color: var(--vt-c-text-light-2);
  line-height: 1.6;
  font-size: 1.05rem;
}

.icon-circle {
  background-color: #eff6ff;
  color: var(--brand-primary);
  width: 64px;
  height: 64px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 0.5rem;
}

.auth-buttons {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
  width: 100%;
  justify-content: center;
}

/* ... (CSS-UL ORIGINAL RĂMÂNE MAI JOS, FĂRĂ MODIFICĂRI) ... */
.listing-form-container {
  max-width: 900px;
  margin: 3rem auto;
  padding: 0 1.5rem;
}

.header-section {
  margin-bottom: 2.5rem;
  text-align: center;
}

.header-section h1 {
  font-size: 2.2rem;
  font-weight: 800;
  color: var(--brand-primary-dark);
  margin-bottom: 0.5rem;
}

.header-section p {
  color: var(--vt-c-text-light-2);
  font-size: 1.1rem;
}

.alert {
  padding: 1rem 1.5rem;
  border-radius: 8px;
  margin-bottom: 2rem;
  display: flex;
  align-items: center;
  gap: 1rem;
  font-weight: 500;
  animation: slideDown 0.3s ease-out;
}

@keyframes slideDown {
  from { opacity: 0; transform: translateY(-10px); }
  to { opacity: 1; transform: translateY(0); }
}

.alert-success {
  background-color: #ecfdf5;
  color: #065f46;
  border: 1px solid #a7f3d0;
}

.alert-error {
  background-color: #fef2f2;
  color: #991b1b;
  border: 1px solid #fecaca;
}

.form-card {
  background: var(--vt-c-white);
  border: 1px solid var(--color-border);
  border-radius: 16px;
  padding: 2.5rem;
  margin-bottom: 2rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.03);
}

.form-card h2 {
  font-size: 1.4rem;
  font-weight: 700;
  color: var(--vt-c-black);
  margin-bottom: 1.5rem;
  border-bottom: 1px solid var(--vt-c-divider-light-2);
  padding-bottom: 1rem;
}

.subtitle {
  font-size: 0.95rem;
  color: var(--vt-c-text-light-2);
  margin-bottom: 1.5rem;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1.5rem;
  align-items: start;
}

.full-width {
  grid-column: 1 / -1;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.6rem;
}

label {
  font-size: 0.95rem;
  font-weight: 600;
  color: var(--vt-c-black-mute);
  margin-left: 2px;
}

input, select, textarea {
  width: 100%;
  box-sizing: border-box;
  padding: 0.9rem 1rem;
  border: 1px solid var(--color-border);
  border-radius: 8px;
  font-size: 1rem;
  background-color: var(--vt-c-white);
  color: var(--vt-c-black);
  outline: none;
  transition: all 0.3s ease;
  font-family: inherit;
  appearance: none;
}

input:focus, select:focus, textarea:focus {
  border-color: var(--brand-primary);
  box-shadow: 0 0 0 3px rgba(26, 74, 156, 0.1);
}

.select-wrapper {
  position: relative;
}

.select-wrapper::after {
  content: "▼";
  font-size: 0.8rem;
  position: absolute;
  right: 1rem;
  top: 50%;
  transform: translateY(-50%);
  color: var(--vt-c-text-light-2);
  pointer-events: none;
}

textarea {
  resize: vertical;
  min-height: 120px;
}

.char-count {
  text-align: right;
  font-size: 0.8rem;
  color: var(--vt-c-text-light-2);
  margin-top: 0.2rem;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 3rem;
}

.btn-primary, .btn-secondary {
  padding: 0.9rem 2.5rem;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  font-size: 1rem;
  transition: all 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}

.btn-primary {
  background-color: var(--brand-primary-dark);
  color: white;
  border: none;
  min-width: 160px;
}

.btn-primary:hover:not(:disabled) {
  background-color: var(--brand-primary);
  transform: translateY(-1px);
}

.btn-primary:disabled {
  background-color: #9ca3af;
  cursor: not-allowed;
}

.btn-secondary {
  background-color: white;
  color: var(--vt-c-text-light-1);
  border: 1px solid var(--color-border);
}

.btn-secondary:hover {
  background-color: var(--vt-c-white-soft);
}

.spinner {
  width: 16px;
  height: 16px;
  border: 2px solid rgba(255,255,255, 0.3);
  border-radius: 50%;
  border-top-color: white;
  animation: spin 0.8s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

@media (max-width: 900px) {
  .form-grid {
    grid-template-columns: 1fr 1fr;
  }
}

@media (max-width: 600px) {
  .form-grid {
    grid-template-columns: 1fr;
  }
  .form-card {
    padding: 1.5rem;
  }
  .auth-buttons {
    flex-direction: column;
  }
}
</style>