<template>
  <Navbar />

  <div class="details-page">
    <div v-if="isLoading" class="loading-state">
      <div class="spinner"></div>
      <p>Loading vehicle details...</p>
    </div>

    <div v-else-if="errorMessage" class="error-state">
      <h3>Oops!</h3>
      <p>{{ errorMessage }}</p>
      <button @click="router.push('/listings')" class="btn-back">Back to Listings</button>
    </div>

    <div v-else class="container">

      <div class="listing-header">
        <div class="header-left">
          <div class="badges">
            <span class="badge year">{{ listing.yearOfManufacture }}</span>
            <span class="badge condition">Used Car</span>
          </div>
          <h1>{{ listing.title }}</h1>
          <div class="location-tag">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"></path><circle cx="12" cy="10" r="3"></circle></svg>
            {{ listing.location }}
          </div>
        </div>
        <div class="header-right">
          <div class="price-tag">€{{ listing.price?.toLocaleString() }}</div>
        </div>
      </div>

      <div class="grid-layout">

        <div class="main-column">

          <div class="gallery-container">
            <div class="main-image-wrapper">
              <img :src="currentMainImage" class="main-image" @error="handleImageError"/>
            </div>
            <div class="thumbnails-scroll" v-if="processedImages.length > 1">
              <div
                  v-for="(img, index) in processedImages"
                  :key="index"
                  class="thumb-item"
                  :class="{ active: currentImageIndex === index }"
                  @click="currentImageIndex = index"
              >
                <img :src="img" @error="handleImageError" />
              </div>
            </div>
          </div>

          <div class="specs-bar">
            <div class="spec-box">
              <span class="icon">📅</span>
              <span class="label">Year</span>
              <span class="val">{{ listing.yearOfManufacture }}</span>
            </div>
            <div class="spec-box">
              <span class="icon">🐎</span>
              <span class="label">Power</span>
              <span class="val">{{ listing.horsePower }} HP</span>
            </div>
            <div class="spec-box">
              <span class="icon">⛽</span>
              <span class="label">Fuel</span>
              <span class="val">{{ listing.fuelType }}</span>
            </div>
            <div class="spec-box">
              <span class="icon">⚙️</span>
              <span class="label">Gearbox</span>
              <span class="val">{{ listing.transmissionType || 'Manual' }}</span>
            </div>
          </div>

          <div class="content-card">
            <h3>Vehicle Description</h3>
            <p class="description-text">{{ listing.description }}</p>
          </div>

          <div class="content-card">
            <h3>Technical Data</h3>
            <div class="tech-grid">
              <div class="tech-row">
                <span class="tech-label">Brand</span>
                <span class="tech-val">{{ listing.brand }}</span>
              </div>
              <div class="tech-row">
                <span class="tech-label">Model</span>
                <span class="tech-val">{{ listing.model }}</span>
              </div>
              <div class="tech-row">
                <span class="tech-label">Body Type</span>
                <span class="tech-val">{{ listing.type }}</span>
              </div>
              <div class="tech-row">
                <span class="tech-label">VIN</span>
                <span class="tech-val vin-text">{{ listing.vin || listing.VIN || 'N/A' }}</span>
              </div>
            </div>
          </div>
        </div>

        <aside class="sidebar-column">
          <div class="seller-card">
            <h3>Seller Info</h3>

            <div class="seller-details">
              <div class="seller-profile" v-if="listing.username">
                <div class="avatar-small">
                  {{ listing.username.charAt(0).toUpperCase() }}
                </div>
                <div class="seller-text">
                  <p class="seller-name">{{ listing.username }}</p>
                  <span class="seller-type">Private Seller</span>
                </div>
              </div>

              <div class="location-row">
                <span class="icon">📍</span> {{ listing.location }}
              </div>
            </div>

            <div class="seller-actions">
              <button class="btn-primary full-width" @click="openAppointmentModal">
                <span class="btn-icon">📅</span> Schedule Test Drive
              </button>

              <button class="btn-outline full-width" @click="openFeedbackModal">
                <span class="btn-icon">⭐</span> Rate Seller
              </button>

              <button v-if="!isPhoneVisible" class="btn-text full-width phone-btn" @click="showPhone">
                <span class="btn-icon">📞</span> Show Phone Number
              </button>

              <a v-else :href="'tel:' + listing.telephone" class="phone-display full-width">
                <span class="btn-icon">📱</span> {{ listing.telephone || 'No number provided' }}
              </a>

            </div>

            <div class="safety-tip">
              <strong>Safety Tip:</strong> Never send money in advance. Meet the seller in a public place.
            </div>
          </div>
        </aside>

      </div>
    </div>

    <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
      <div class="modal-content">
        <h2>Schedule Appointment</h2>
        <p class="modal-subtitle">Meet the seller to inspect the car.</p>

        <form @submit.prevent="submitAppointment">
          <div class="form-group">
            <label>Date & Time *</label>
            <input type="datetime-local" v-model="apptForm.date" required :min="minDate" />
          </div>

          <div class="form-group">
            <label>Meeting Location *</label>
            <input type="text" v-model="apptForm.location" placeholder="e.g. Iulius Mall Parking" required />
          </div>

          <div class="form-group">
            <label>Message / Details</label>
            <textarea v-model="apptForm.details" rows="3"></textarea>
          </div>

          <div v-if="apptError" class="error-text">{{ apptError }}</div>
          <div v-if="apptSuccess" class="success-text">{{ apptSuccess }}</div>

          <div class="modal-actions">
            <button type="button" class="btn-secondary" @click="closeModal">Cancel</button>
            <button type="submit" class="btn-primary" :disabled="isSubmittingAppt">
              {{ isSubmittingAppt ? 'Booking...' : 'Confirm Appointment' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <div v-if="showFeedbackModalState" class="modal-overlay" @click.self="closeFeedbackModal">
      <div class="modal-content">
        <h2>Rate the Seller</h2>
        <p class="modal-subtitle">How was your experience with {{ listing.username }}?</p>

        <form @submit.prevent="submitFeedback">
          <div class="form-group">
            <label>Rating *</label>
            <div class="star-input">
              <span
                  v-for="star in 5"
                  :key="star"
                  @click="feedbackForm.rating = star"
                  :class="{ active: star <= feedbackForm.rating }"
              >★</span>
            </div>
            <p class="rating-text">You selected: {{ feedbackForm.rating }} stars</p>
          </div>

          <div class="form-group">
            <label>Your Review</label>
            <textarea v-model="feedbackForm.description" placeholder="Describe your interaction..." rows="4" required></textarea>
          </div>

          <div v-if="feedbackError" class="error-text">{{ feedbackError }}</div>
          <div v-if="feedbackSuccess" class="success-text">{{ feedbackSuccess }}</div>

          <div class="modal-actions">
            <button type="button" class="btn-secondary" @click="closeFeedbackModal">Cancel</button>
            <button type="submit" class="btn-primary" :disabled="isSubmittingFeedback">
              {{ isSubmittingFeedback ? 'Posting...' : 'Submit Review' }}
            </button>
          </div>
        </form>
      </div>
    </div>

  </div>
  <Footer />
</template>

<script setup>
import { ref, onMounted, computed, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";

const route = useRoute();
const router = useRouter();

// Listing Data
const listing = ref({});
const isLoading = ref(true);
const errorMessage = ref('');
const currentImageIndex = ref(0);

// Phone Visibility
const isPhoneVisible = ref(false);

// Appt State
const showModal = ref(false);
const isSubmittingAppt = ref(false);
const apptError = ref('');
const apptSuccess = ref('');

// Feedback State
const showFeedbackModalState = ref(false);
const isSubmittingFeedback = ref(false);
const feedbackError = ref('');
const feedbackSuccess = ref('');

// Forms
const apptForm = reactive({ date: '', location: '', details: '' });
const feedbackForm = reactive({ rating: 5, description: '' });

// Utils
const minDate = new Date().toISOString().slice(0, 16);
const BASE_UPLOAD_URL = 'http://localhost:9090/uploads/';
const PLACEHOLDER_IMG = 'https://placehold.co/800x600?text=No+Image';

// --- IMAGE LOGIC ---
const getCleanImageUrl = (imgObj) => {
  if (!imgObj) return PLACEHOLDER_IMG;
  let rawUrl = imgObj.url || imgObj;
  if (typeof rawUrl === 'string') {
    if (rawUrl.includes('uploads')) rawUrl = rawUrl.split(/uploads[\\/]/).pop();
    if (rawUrl.startsWith('/') || rawUrl.startsWith('\\')) rawUrl = rawUrl.substring(1);
    return `${BASE_UPLOAD_URL}${rawUrl}`;
  }
  return PLACEHOLDER_IMG;
};

const processedImages = computed(() => {
  const rawImages = listing.value.images || listing.value.listingImages || [];
  if (rawImages.length === 0) return [PLACEHOLDER_IMG];
  return rawImages.map(img => getCleanImageUrl(img));
});

const currentMainImage = computed(() => processedImages.value[currentImageIndex.value] || PLACEHOLDER_IMG);
const handleImageError = (e) => { e.target.src = PLACEHOLDER_IMG; };

// --- ACTIONS ---

const fetchListingDetails = async () => {
  try {
    const id = route.params.id;
    const response = await axios.get(`http://localhost:9090/listing/${id}`);
    listing.value = response.data;
    apptForm.location = response.data.location;
  } catch (error) {
    errorMessage.value = "Could not load listing details.";
  } finally {
    isLoading.value = false;
  }
};

const showPhone = () => {
  isPhoneVisible.value = true;
};

// APPOINTMENT
const openAppointmentModal = () => {
  const token = localStorage.getItem('token');
  if (!token) {
    if(confirm("Login required. Go to login?")) router.push({ path: '/login', query: { redirect: route.fullPath } });
    return;
  }
  showModal.value = true;
};
const closeModal = () => showModal.value = false;

const submitAppointment = async () => {
  isSubmittingAppt.value = true;
  apptError.value = ''; apptSuccess.value = '';
  try {
    const token = localStorage.getItem('token');
    // ID Check
    const sellerId = listing.value.userId || listing.value.user;
    if(!sellerId) throw new Error("Seller ID missing");

    const payload = {
      location: apptForm.location,
      date: apptForm.date,
      details: apptForm.details,
      sellerId: sellerId
    };

    await axios.post('http://localhost:9090/appointment/create', payload, {
      headers: { Authorization: `Bearer ${token}` }
    });
    apptSuccess.value = "Appointment created!";
    setTimeout(() => closeModal(), 1500);
  } catch (e) {
    apptError.value = e.response?.data || "Failed to create appointment.";
  } finally {
    isSubmittingAppt.value = false;
  }
};

// FEEDBACK
const openFeedbackModal = () => {
  const token = localStorage.getItem('token');
  if (!token) {
    if(confirm("Login required. Go to login?")) router.push({ path: '/login', query: { redirect: route.fullPath } });
    return;
  }
  feedbackForm.rating = 5; feedbackForm.description = '';
  showFeedbackModalState.value = true;
};
const closeFeedbackModal = () => showFeedbackModalState.value = false;

const submitFeedback = async () => {
  isSubmittingFeedback.value = true;
  feedbackError.value = ''; feedbackSuccess.value = '';
  try {
    const token = localStorage.getItem('token');
    const sellerId = listing.value.userId || listing.value.user;
    if(!sellerId) throw new Error("Seller ID missing");

    const payload = {
      toUserId: sellerId,
      rating: feedbackForm.rating,
      description: feedbackForm.description
    };

    await axios.post('http://localhost:9090/feedback/add', payload, {
      headers: { Authorization: `Bearer ${token}` }
    });
    feedbackSuccess.value = "Review posted!";
    setTimeout(() => closeFeedbackModal(), 1500);
  } catch (e) {
    feedbackError.value = e.response?.data || "Failed to post review.";
  } finally {
    isSubmittingFeedback.value = false;
  }
};

onMounted(() => {
  fetchListingDetails();
});
</script>

<style scoped>
/* LAYOUT */
.details-page { background-color: #f3f4f6; min-height: 90vh; padding: 2rem 0; }
.container { max-width: 1180px; margin: 0 auto; padding: 0 1.5rem; }
.grid-layout { display: grid; grid-template-columns: 2fr 1fr; gap: 2rem; }

/* HEADER */
.listing-header { background: white; padding: 1.5rem 2rem; border-radius: 12px; margin-bottom: 2rem; display: flex; justify-content: space-between; align-items: flex-start; box-shadow: 0 2px 10px rgba(0,0,0,0.03); }
.header-left h1 { font-size: 2rem; margin: 0.5rem 0; color: #111827; }
.price-tag { font-size: 2.2rem; font-weight: 800; color: #1a4a9c; }
.badges { display: flex; gap: 0.5rem; }
.badge { font-size: 0.8rem; font-weight: 700; padding: 0.25rem 0.6rem; border-radius: 4px; text-transform: uppercase; }
.badge.year { background: #e0f2fe; color: #0369a1; }
.badge.condition { background: #dcfce7; color: #15803d; }
.location-tag { color: #6b7280; display: flex; align-items: center; gap: 0.3rem; font-weight: 500; }

/* GALLERY */
.gallery-container { background: white; border-radius: 12px; overflow: hidden; margin-bottom: 1.5rem; border: 1px solid #e5e7eb; }
.main-image-wrapper { height: 450px; background: #000; display: flex; align-items: center; justify-content: center; }
.main-image { max-width: 100%; max-height: 100%; object-fit: contain; }
.thumbnails-scroll { display: flex; gap: 8px; padding: 10px; overflow-x: auto; background: #f9fafb; }
.thumb-item { width: 80px; height: 60px; border-radius: 4px; overflow: hidden; cursor: pointer; opacity: 0.6; border: 2px solid transparent; }
.thumb-item.active { opacity: 1; border-color: #1a4a9c; }
.thumb-item img { width: 100%; height: 100%; object-fit: cover; }

/* SPECS & CONTENT */
.specs-bar { display: grid; grid-template-columns: repeat(4, 1fr); gap: 1rem; margin-bottom: 1.5rem; }
.spec-box { background: white; padding: 1rem; border-radius: 10px; text-align: center; border: 1px solid #e5e7eb; display: flex; flex-direction: column; align-items: center; gap: 0.3rem; }
.spec-box .icon { font-size: 1.5rem; }
.spec-box .label { font-size: 0.75rem; text-transform: uppercase; color: #6b7280; font-weight: 600; }
.spec-box .val { font-weight: 700; color: #1f2937; }

.content-card { background: white; padding: 2rem; border-radius: 12px; border: 1px solid #e5e7eb; margin-bottom: 1.5rem; }
.content-card h3 { font-size: 1.25rem; margin-bottom: 1.2rem; border-bottom: 2px solid #f3f4f6; padding-bottom: 0.8rem; }
.tech-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
.tech-row { display: flex; justify-content: space-between; padding: 0.8rem 0; border-bottom: 1px solid #f9fafb; }
.tech-val { font-weight: 600; color: #111827; }

/* SIDEBAR & SELLER */
.seller-card { background: white; padding: 1.5rem; border-radius: 12px; border: 1px solid #e5e7eb; position: sticky; top: 2rem; }
.seller-profile { display: flex; align-items: center; gap: 12px; margin-bottom: 1rem; border-bottom: 1px solid #f3f4f6; padding-bottom: 1rem; }
.avatar-small { width: 45px; height: 45px; background: #1a4a9c; color: white; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-weight: 700; font-size: 1.2rem; }
.seller-name { margin: 0; font-weight: 700; font-size: 1.1rem; color: #1f2937; }
.seller-type { font-size: 0.8rem; color: #6b7280; }
.location-row { margin-bottom: 1.5rem; color: #6b7280; display: flex; align-items: center; gap: 6px; }

.seller-actions { display: flex; flex-direction: column; gap: 0.8rem; }
.full-width { width: 100%; display: flex; justify-content: center; align-items: center; gap: 8px; }
.btn-primary { background: #1a4a9c; color: white; border: none; padding: 1rem; border-radius: 8px; font-weight: 600; cursor: pointer; }
.btn-primary:hover { background: #153e85; }
.btn-outline { background: white; border: 1px solid #d1d5db; padding: 0.8rem; border-radius: 8px; font-weight: 600; cursor: pointer; color: #374151; }
.btn-outline:hover { background: #f9fafb; }

/* PHONE STYLES */
.phone-btn { background: #f9fafb; border: 1px solid #e5e7eb; color: #374151; padding: 0.8rem; border-radius: 8px; font-weight: 600; cursor: pointer; }
.phone-display { background: #ecfdf5; color: #059669; border: 1px solid #d1fae5; padding: 0.8rem; border-radius: 8px; text-decoration: none; font-weight: 700; font-size: 1.1rem; text-align: center; transition: all 0.2s; }
.phone-display:hover { background: #d1fae5; }
.safety-tip { font-size: 0.75rem; color: #6b7280; background: #fef2f2; padding: 0.8rem; border-radius: 6px; margin-top: 1.5rem; border: 1px solid #fee2e2; }

/* MODALS */
.modal-overlay { position: fixed; top: 0; left: 0; right: 0; bottom: 0; background: rgba(0, 0, 0, 0.6); display: flex; justify-content: center; align-items: center; z-index: 1000; backdrop-filter: blur(4px); }
.modal-content { background: white; padding: 2rem; border-radius: 12px; width: 90%; max-width: 500px; }
.form-group { margin-bottom: 1rem; }
.form-group label { display: block; margin-bottom: 0.5rem; font-weight: 600; }
.form-group input, .form-group textarea { width: 100%; padding: 0.75rem; border: 1px solid #d1d5db; border-radius: 6px; }
.modal-actions { display: flex; justify-content: flex-end; gap: 1rem; margin-top: 1.5rem; }
.btn-secondary { background: white; border: 1px solid #d1d5db; padding: 0.75rem 1.5rem; border-radius: 6px; cursor: pointer; }

/* STARS */
.star-input { display: flex; gap: 5px; font-size: 2rem; cursor: pointer; margin-bottom: 0.5rem; }
.star-input span { color: #d1d5db; transition: color 0.2s; }
.star-input span:hover, .star-input span.active { color: #fbbf24; }
.rating-text { font-size: 0.9rem; color: #6b7280; margin-top: -5px; }

/* STATES */
.loading-state, .error-state { text-align: center; padding: 4rem; }
.spinner { width: 40px; height: 40px; border: 4px solid #f3f3f3; border-top: 4px solid #1a4a9c; border-radius: 50%; animation: spin 1s linear infinite; margin: 0 auto 1rem; }
@keyframes spin { to { transform: rotate(360deg); } }

@media (max-width: 900px) {
  .grid-layout { grid-template-columns: 1fr; }
  .listing-header { flex-direction: column; gap: 1rem; }
  .seller-card { position: static; margin-top: 2rem; }
}
</style>