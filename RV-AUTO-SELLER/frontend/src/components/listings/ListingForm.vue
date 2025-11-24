<template>
  <div class="listing-form-container">
    <div class="header-section">
      <h1>Add New Listing</h1>
      <p>Complete the details about your car below</p>
    </div>

    <form @submit.prevent="submitListing" class="form-content">

      <section class="form-card">
        <h2>General Information</h2>

        <div class="form-grid">
          <div class="form-group full-width">
            <label>Listing Title *</label>
            <input type="text" v-model="formData.title" placeholder="e.g. Audi A4 2.0 TDI S-Line" required />
          </div>

          <div class="form-group">
            <label>Brand *</label>
            <select v-model="formData.brand" required>
              <option value="" disabled>Select brand</option>
              <option v-for="brand in brands" :key="brand" :value="brand">{{ brand }}</option>
            </select>
          </div>
          <div class="form-group">
            <label>Model *</label>
            <input type="text" v-model="formData.model" placeholder="e.g. A4" required />
          </div>

          <div class="form-group">
            <label>Year of Manufacture *</label>
            <input type="number" v-model="formData.year" placeholder="2023" min="1900" :max="new Date().getFullYear()" required />
          </div>
          <div class="form-group">
            <label>Mileage (km) *</label>
            <input type="number" v-model="formData.mileage" placeholder="50000" min="0" required />
          </div>
          <div class="form-group">
            <label>Price (€) *</label>
            <input type="number" v-model="formData.price" placeholder="25000" min="0" required />
          </div>

          <div class="form-group">
            <label>Fuel Type *</label>
            <select v-model="formData.fuelType" required>
              <option value="" disabled>Select</option>
              <option value="Petrol">Petrol</option>
              <option value="Diesel">Diesel</option>
              <option value="Electric">Electric</option>
              <option value="Hybrid">Hybrid</option>
              <option value="LPG">LPG</option>
            </select>
          </div>
          <div class="form-group">
            <label>Transmission *</label>
            <select v-model="formData.transmission" required>
              <option value="" disabled>Select</option>
              <option value="Manual">Manual</option>
              <option value="Automatic">Automatic</option>
            </select>
          </div>

          <div class="form-group full-width">
            <label>Location *</label>
            <input type="text" v-model="formData.location" placeholder="e.g. Bucharest" required />
          </div>
        </div>
      </section>

      <section class="form-card">
        <h2>Description</h2>
        <p class="subtitle">Add a detailed description of the car</p>
        <div class="form-group full-width">
          <textarea
              v-model="formData.description"
              placeholder="Describe the car's condition, features, service history, etc."
              rows="6"
              required
          ></textarea>
        </div>
      </section>

      <section class="form-card">
        <h2>Photos</h2>
        <p class="subtitle">Add quality photos (maximum 10)</p>

        <ImageUploader @update:images="handleImagesUpdate" />
      </section>

      <div class="form-actions">
        <button type="button" class="btn-secondary">Save Draft</button>
        <button type="submit" class="btn-primary" :disabled="isSubmitting">
          {{ isSubmitting ? 'Publishing...' : 'Publish Listing' }}
        </button>
      </div>

    </form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import ImageUploader from './ImageUploader.vue';

// Mock Data for Brands (In a real app, fetch from API)
const brands = ['Audi', 'BMW', 'Mercedes-Benz', 'Volkswagen', 'Toyota', 'Ford', 'Porsche', 'Honda'];

const isSubmitting = ref(false);
const listingImages = ref([]);

const formData = reactive({
  title: '',
  brand: '',
  model: '',
  year: '',
  mileage: '',
  price: '',
  fuelType: '',
  transmission: '',
  location: '',
  description: ''
});

// Capture images from child component
const handleImagesUpdate = (files) => {
  listingImages.value = files;
};

// Handle Form Submission
const submitListing = async () => {
  isSubmitting.value = true;

  try {
    // 1. Create FormData object for Multipart upload
    const submitData = new FormData();

    // 2. Append text fields
    Object.keys(formData).forEach(key => {
      submitData.append(key, formData[key]);
    });

    // 3. Append images
    listingImages.value.forEach(file => {
      submitData.append('images', file);
    });

    // 4. Send to Backend (Example logic)
    console.log('Sending data to backend...');
    // const response = await axios.post('/api/listings', submitData);

    alert('Listing published successfully!');
    // router.push('/listings');

  } catch (error) {
    console.error('Error publishing listing:', error);
    alert('An error occurred.');
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
.listing-form-container {
  max-width: 900px;
  margin: 2rem auto;
  padding: 0 1rem;
}

.header-section {
  margin-bottom: 2rem;
}

.header-section h1 {
  font-size: 2rem;
  font-weight: 800;
  color: var(--brand-primary-dark);
  margin-bottom: 0.5rem;
}

.header-section p {
  color: var(--vt-c-text-light-2);
}

.form-card {
  background: var(--vt-c-white);
  border: 1px solid var(--color-border);
  border-radius: 12px;
  padding: 2rem;
  margin-bottom: 2rem;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
}

.form-card h2 {
  font-size: 1.25rem;
  font-weight: 700;
  color: var(--vt-c-black);
  margin-bottom: 0.5rem;
}

.subtitle {
  font-size: 0.9rem;
  color: var(--vt-c-text-light-2);
  margin-bottom: 1.5rem;
}

/* Grid System for Inputs */
.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr; /* 3 Columns */
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.full-width {
  grid-column: 1 / -1; /* Span all columns */
}

/* Input Styles */
label {
  font-size: 0.9rem;
  font-weight: 600;
  color: var(--vt-c-black-mute);
}

input, select, textarea {
  padding: 0.8rem 1rem;
  border: 1px solid var(--color-border);
  border-radius: 8px;
  font-size: 1rem;
  background-color: var(--vt-c-white);
  color: var(--vt-c-black);
  outline: none;
  transition: border-color 0.3s;
  font-family: inherit;
}

input:focus, select:focus, textarea:focus {
  border-color: var(--brand-primary);
}

textarea {
  resize: vertical;
}

/* Actions Footer */
.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 2rem;
}

.btn-primary {
  background-color: var(--brand-primary-dark);
  color: white;
  border: none;
  padding: 0.8rem 2rem;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s;
}

.btn-primary:hover:not(:disabled) {
  background-color: var(--brand-primary);
}

.btn-primary:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.btn-secondary {
  background-color: white;
  color: var(--vt-c-text-light-1);
  border: 1px solid var(--color-border);
  padding: 0.8rem 2rem;
  border-radius: 8px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
}

.btn-secondary:hover {
  background-color: var(--vt-c-white-soft);
  border-color: var(--vt-c-text-light-2);
}

/* Responsive */
@media (max-width: 768px) {
  .form-grid {
    grid-template-columns: 1fr; /* 1 Column on mobile */
  }
}
</style>