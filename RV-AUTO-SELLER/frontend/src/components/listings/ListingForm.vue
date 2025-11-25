<template>
  <Navbar />
  <div class="listing-form-container">
    <div class="header-section">
      <h1>Add New Listing</h1>
      <p>Complete the details about your car as shown in the registration documents.</p>
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
        <button type="button" class="btn-secondary">Save Draft</button>
        <button type="submit" class="btn-primary" :disabled="isSubmitting">
          {{ isSubmitting ? 'Publishing...' : 'Publish Listing' }}
        </button>
      </div>

    </form>
  </div>
  <Footer />
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import ImageUploader from './ImageUploader.vue';
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
// import axios from 'axios';

const isSubmitting = ref(false);
const listingImages = ref([]);

// data structure matches Listing.java
const formData = reactive({
  title: '',
  brand: '',            // Enum CarBrand
  model: '',
  type: '',             // Enum CarType
  yearOfManufacture: '',
  horsePower: '',
  price: '',
  fuelType: '',         // Enum FuelType
  transmissionType: '', // Enum TransmissionType
  vin: '',              // VIN
  location: '',
  description: ''
});

const enumOptions = reactive({
  brands: [],
  carTypes: [],
  fuelTypes: [],
  transmissionTypes: []
});


const loadEnums = async () => {
  try {
    const response = await fetch('http://localhost:9090/api/utils/enums');
    const data = await response.json();

    enumOptions.brands = data.brands;
    enumOptions.carTypes = data.carTypes;
    enumOptions.fuelTypes = data.fuelTypes;
    enumOptions.transmissionTypes = data.transmissionTypes;
  } catch (error) {
    console.error("Can't load enums list from backend:", error);
  }
};

onMounted(() => {
  loadEnums();
});

const handleImagesUpdate = (files) => {
  listingImages.value = files;
};

const submitListing = async () => {
  isSubmitting.value = true;

  try {
    const submitData = new FormData();
    Object.keys(formData).forEach(key => {
      submitData.append(key, formData[key]);
    });
    listingImages.value.forEach(file => {
      submitData.append('images', file);
    });

    console.log("Submitting to backend:");
    for (var pair of submitData.entries()) {
      console.log(pair[0]+ ', ' + pair[1]);
    }

    alert('Listing published successfully! (Simulation)');

  } catch (error) {
    console.error('Error:', error);
    alert('An error occurred while publishing the listing.');
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
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

.form-card {
  background: var(--vt-c-white);
  border: 1px solid var(--color-border);
  border-radius: 16px;
  padding: 2.5rem;
  margin-bottom: 2rem;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.03); /* Umbră fină */
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
}

.btn-primary {
  background-color: var(--brand-primary-dark);
  color: white;
  border: none;
}

.btn-primary:hover:not(:disabled) {
  background-color: var(--brand-primary);
  transform: translateY(-1px);
}

.btn-secondary {
  background-color: white;
  color: var(--vt-c-text-light-1);
  border: 1px solid var(--color-border);
}

.btn-secondary:hover {
  background-color: var(--vt-c-white-soft);
  border-color: var(--vt-c-text-light-2);
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
}
</style>