<template>
  <Navbar />
  <main class="account-container">

    <div v-if="!isLoggedIn" class="card not-logged-card">
      <div class="icon-wrapper">🔒</div>
      <h2>Authentication Required</h2>
      <p>Please login or register to manage your account and listings.</p>

      <div class="buttons">
        <router-link to="/login" class="btn primary">Login</router-link>
        <router-link to="/register" class="btn secondary">Register</router-link>
      </div>
    </div>

    <div v-else class="card profile-card">
      <div class="profile-header">
        <div class="avatar-circle">
          {{ user?.firstName?.charAt(0) }}{{ user?.lastName?.charAt(0) }}
        </div>
        <div class="header-info">
          <h2>{{ user?.firstName }} {{ user?.lastName }}</h2>
          <p class="email-text">{{ user?.email }}</p>
        </div>
      </div>

      <div class="tabs">
        <button :class="{ active: tab==='profile' }" @click="tab='profile'">
          <span class="icon">👤</span> My Profile
        </button>
        <button :class="{ active: tab==='ads' }" @click="fetchMyListings(); tab='ads'">
          <span class="icon">🚗</span> My Listings
        </button>
        <button :class="{ active: tab==='appointments' }" @click="fetchMyAppointments(); tab='appointments'">
          <span class="icon">📅</span> Appointments
        </button>
        <button :class="{ active: tab==='reviews' }" @click="fetchMyReviews(); tab='reviews'">
          <span class="icon">⭐</span> Reviews
        </button>
      </div>

      <div v-if="successMessage" class="alert success">{{ successMessage }}</div>
      <div v-if="errorMessage" class="alert error">{{ errorMessage }}</div>


      <div v-if="tab === 'profile'" class="tab-content fade-in">
        <h3>Personal Information</h3>
        <div class="form-grid">
          <div class="input-group">
            <label>First name</label>
            <input type="text" v-model="user!.firstName" />
          </div>

          <div class="input-group">
            <label>Last name</label>
            <input type="text" v-model="user!.lastName" />
          </div>

          <div class="input-group full">
            <label>Email Address</label>
            <input type="email" v-model="user!.email" disabled class="disabled-input"/>
          </div>

          <div class="input-group full">
            <label>Phone Number</label>
            <input type="text" v-model="user!.telephone" />
          </div>

          <div class="input-group full">
            <label>Username</label>
            <input type="text" v-model="user!.username" disabled class="disabled-input"/>
          </div>
        </div>
        <div class="actions-footer">
          <button class="btn save" @click="updateChanges" :disabled="isLoading">
            {{ isLoading ? 'Saving...' : 'Save Changes' }}
          </button>
        </div>
      </div>


      <div v-if="tab === 'ads'" class="tab-content fade-in">
        <div class="tab-header">
          <h3>My Active Listings</h3>
          <button class="btn-small" @click="router.push('/sell-form')">+ Add New</button>
        </div>

        <div v-if="isLoadingListings" class="spinner-container"><div class="spinner"></div></div>

        <div v-else-if="myListings.length === 0" class="empty-state">
          <p>You haven't posted any cars yet.</p>
          <button @click="router.push('/sell-form')" class="btn primary">Sell your car</button>
        </div>

        <div v-else class="listings-list">
          <div v-for="car in myListings" :key="car.id" class="listing-item">
            <div class="listing-img">
              <img :src="getCleanImageUrl(car.listingImages?.[0])" alt="car" />
            </div>
            <div class="listing-info">
              <h4>{{ car.title }}</h4>
              <span class="price">€{{ car.price?.toLocaleString() }}</span>
              <div class="meta">
                <span>{{ car.yearOfManufacture }}</span> • <span>{{ car.fuelType }}</span>
              </div>
            </div>
            <div class="listing-actions">
              <button class="btn-icon view" @click="router.push(`/listing/${car.id}`)" title="View">👁️</button>
              <button class="btn-icon delete" @click="deleteListing(car.id)" title="Delete">🗑️</button>
            </div>
          </div>
        </div>
      </div>


      <div v-if="tab === 'appointments'" class="tab-content fade-in">
        <h3>My Schedule</h3>
        <p class="subtitle">Test drives and meetings.</p>

        <div v-if="isLoadingAppts" class="spinner-container"><div class="spinner"></div></div>

        <div v-else-if="myAppointments.length === 0" class="empty-state">
          <p>No appointments scheduled yet.</p>
        </div>

        <div v-else class="appointments-list">
          <div v-for="appt in myAppointments" :key="appt.id" class="appt-card">
            <div class="appt-date">
              <span class="day">{{ new Date(appt.date).getDate() }}</span>
              <span class="month">{{ new Date(appt.date).toLocaleString('default', { month: 'short' }) }}</span>
              <span class="time">{{ new Date(appt.date).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'}) }}</span>
            </div>
            <div class="appt-details">
              <div class="appt-location">📍 {{ appt.location }}</div>
              <div class="appt-info">
                <span v-if="appt.details" class="note">"{{ appt.details }}"</span>
              </div>
            </div>
            <div class="appt-status badge-pending">Confirmed</div>
          </div>
        </div>
      </div>

      <div v-if="tab === 'reviews'" class="tab-content fade-in">
        <h3>My Reputation</h3>

        <div v-if="isLoadingReviews" class="spinner-container"><div class="spinner"></div></div>

        <div v-else-if="myReviews.length === 0" class="empty-state">
          <p>No reviews received yet.</p>
        </div>

        <div v-else class="reviews-list">
          <div v-for="review in myReviews" :key="review.id" class="review-card">
            <div class="review-header">
              <span class="reviewer-name">{{ review.fromUserName }}</span>
              <span class="review-date">{{ new Date(review.createdAt).toLocaleDateString() }}</span>
            </div>
            <div class="star-rating">
              <span v-for="n in 5" :key="n" :class="{ filled: n <= review.rating }">★</span>
            </div>
            <p class="review-text">{{ review.description }}</p>
          </div>
        </div>
      </div>

    </div>
  </main>
  <Footer />
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
import { getCurrentUser, updateUser } from "@/api/userService";
import type { User } from "@/types/User";
import axios from "axios";

// --- INTERFACES ---
interface Listing {
  id: number;
  title: string;
  price: number;
  yearOfManufacture: number;
  fuelType: string;
  listingImages: any[];
}

interface Appointment {
  id: number;
  date: string;
  location: string;
  details?: string;
}

interface Review {
  id: number;
  fromUserName: string;
  rating: number;
  description: string;
  createdAt: string;
}

// --- STATE ---
const router = useRouter();
const user = ref<User | null>(null);
const isLoggedIn = ref<boolean>(false);
const tab = ref<"profile" | "ads" | "appointments" | "reviews">("profile");

// Feedback UI State
const isLoading = ref(false);
const successMessage = ref("");
const errorMessage = ref("");

// Data State
const myListings = ref<Listing[]>([]);
const isLoadingListings = ref(false);

const myAppointments = ref<Appointment[]>([]);
const isLoadingAppts = ref(false);

const myReviews = ref<Review[]>([]);
const isLoadingReviews = ref(false);

const BASE_UPLOAD_URL = 'http://localhost:9090/uploads/';

// --- HELPER FUNCTIONS ---
const getCleanImageUrl = (imgObj: any) => {
  if (!imgObj) return 'https://placehold.co/150x100?text=No+Img';
  let rawUrl = imgObj.url || imgObj;
  if (typeof rawUrl === 'string') {
    if (rawUrl.includes('uploads')) rawUrl = rawUrl.split(/uploads[\\/]/).pop();
    if (rawUrl.startsWith('/') || rawUrl.startsWith('\\')) rawUrl = rawUrl.substring(1);
    return `${BASE_UPLOAD_URL}${rawUrl}`;
  }
  return 'https://placehold.co/150x100?text=Error';
};

const showSuccess = (msg: string) => {
  successMessage.value = msg;
  setTimeout(() => successMessage.value = "", 3000);
};

// --- API ACTIONS ---

// 1. Update Profile
const updateChanges = async () => {
  if (!user.value) return;
  isLoading.value = true;
  errorMessage.value = "";

  try {
    await updateUser(user.value);
    const refreshedUser = await getCurrentUser();
    user.value = refreshedUser;
    showSuccess("Profile updated successfully!");
  } catch (error) {
    errorMessage.value = "Failed to update profile.";
  } finally {
    isLoading.value = false;
  }
};

// 2. Fetch Listings
const fetchMyListings = async () => {
  isLoadingListings.value = true;
  try {
    const token = localStorage.getItem('token');
    const response = await axios.get('http://localhost:9090/listing/my-listings', {
      headers: { Authorization: `Bearer ${token}` }
    });
    myListings.value = response.data;
  } catch (error) {
    console.error("Error loading listings", error);
  } finally {
    isLoadingListings.value = false;
  }
};

// 3. Delete Listing
const deleteListing = async (id: number) => {
  if(!confirm("Are you sure you want to delete this listing? This action cannot be undone.")) return;

  try {
    const token = localStorage.getItem('token');
    await axios.delete(`http://localhost:9090/listing/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });
    showSuccess("Listing deleted.");
    myListings.value = myListings.value.filter((item) => item.id !== id);
  } catch (error) {
    errorMessage.value = "Could not delete listing.";
  }
};

// 4. Fetch Appointments
const fetchMyAppointments = async () => {
  isLoadingAppts.value = true;
  try {
    const token = localStorage.getItem('token');
    const response = await axios.get('http://localhost:9090/appointment/my-appointments', {
      headers: { Authorization: `Bearer ${token}` }
    });
    myAppointments.value = response.data;
  } catch (error) {
    console.error("Error loading appointments", error);
  } finally {
    isLoadingAppts.value = false;
  }
};

// 5. Fetch Reviews
const fetchMyReviews = async () => {
  isLoadingReviews.value = true;
  try {
    const token = localStorage.getItem('token');
    const response = await axios.get('http://localhost:9090/feedback/my-reviews', {
      headers: { Authorization: `Bearer ${token}` }
    });
    myReviews.value = response.data;
  } catch (error) {
    console.error("Error loading reviews", error);
  } finally {
    isLoadingReviews.value = false;
  }
};

// --- LIFECYCLE ---
onMounted(async () => {
  try {
    const data = await getCurrentUser();
    user.value = data;
    isLoggedIn.value = true;
  } catch (err) {
    isLoggedIn.value = false;
  }
});
</script>

<style scoped>
.account-container {
  display: flex;
  justify-content: center;
  padding-top: 100px;
  padding-bottom: 60px;
  background-color: #f8f9fa;
  min-height: 100vh;
}

/* CARDS */
.card {
  width: 100%;
  max-width: 800px;
  background: white;
  padding: 2.5rem;
  border-radius: 16px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.04);
  border: 1px solid #eef1f6;
  margin: 0 1rem;
}

.not-logged-card { text-align: center; padding: 4rem 2rem; }
.icon-wrapper { font-size: 3rem; margin-bottom: 1rem; }
.profile-header { display: flex; align-items: center; gap: 1.5rem; margin-bottom: 2rem; }
.avatar-circle { width: 70px; height: 70px; background: #1a4a9c; color: white; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 1.8rem; font-weight: 700; text-transform: uppercase; }
.header-info h2 { margin: 0; font-size: 1.6rem; color: #1f2937; }
.email-text { color: #6b7280; margin-top: 0.3rem; }

/* TABS */
.tabs { display: flex; gap: 0.8rem; background: #f3f4f6; padding: 6px; border-radius: 10px; margin-bottom: 2rem; overflow-x: auto; }
.tabs button { flex: 1; padding: 12px; border-radius: 8px; border: none; background: transparent; cursor: pointer; font-weight: 600; color: #6b7280; display: flex; align-items: center; justify-content: center; gap: 8px; transition: all 0.2s; white-space: nowrap; }
.tabs button:hover { background: rgba(255,255,255,0.5); }
.tabs .active { background: white; color: #1a4a9c; box-shadow: 0 2px 5px rgba(0,0,0,0.05); }

/* FORMS */
.form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 1.5rem; margin-top: 1.5rem; }
.full { grid-column: 1 / -1; }
.input-group { display: flex; flex-direction: column; gap: 0.5rem; }
label { font-size: 0.85rem; font-weight: 600; color: #374151; }
input { padding: 12px; border-radius: 8px; border: 1px solid #d1d5db; background: #fff; font-size: 1rem; }
.disabled-input { background: #f9fafb; color: #6b7280; cursor: not-allowed; }
.actions-footer { margin-top: 2rem; display: flex; justify-content: flex-end; }
.btn.save { background: #1a4a9c; color: white; padding: 12px 28px; border: none; border-radius: 8px; font-weight: 600; cursor: pointer; }
.btn.save:hover { background: #153e85; }

/* ALERTS */
.alert { padding: 1rem; border-radius: 8px; margin-bottom: 1.5rem; text-align: center; font-weight: 500; }
.success { background: #dcfce7; color: #166534; }
.error { background: #fee2e2; color: #991b1b; }

/* LISTINGS TAB */
.tab-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem; }
.btn-small { background: #1a4a9c; color: white; border: none; padding: 8px 16px; border-radius: 6px; cursor: pointer; font-size: 0.9rem; font-weight: 600; }
.listings-list { display: flex; flex-direction: column; gap: 1rem; }
.listing-item { display: flex; align-items: center; gap: 1rem; padding: 1rem; border: 1px solid #e5e7eb; border-radius: 10px; transition: transform 0.2s; background: white; }
.listing-item:hover { transform: translateY(-2px); box-shadow: 0 4px 12px rgba(0,0,0,0.05); }
.listing-img { width: 80px; height: 60px; border-radius: 6px; overflow: hidden; background: #eee; flex-shrink: 0; }
.listing-img img { width: 100%; height: 100%; object-fit: cover; }
.listing-info { flex: 1; }
.listing-info h4 { margin: 0 0 0.3rem 0; font-size: 1rem; color: #111827; }
.listing-info .price { color: #1a4a9c; font-weight: 700; }
.listing-info .meta { font-size: 0.8rem; color: #6b7280; margin-top: 0.2rem; }
.listing-actions { display: flex; gap: 0.5rem; }
.btn-icon { width: 36px; height: 36px; border-radius: 50%; border: 1px solid #e5e7eb; background: white; cursor: pointer; display: flex; align-items: center; justify-content: center; transition: all 0.2s; }
.btn-icon:hover { background: #f3f4f6; }
.btn-icon.delete:hover { background: #fee2e2; border-color: #fecaca; }

/* APPOINTMENTS TAB */
.appt-card { display: flex; border: 1px solid #e5e7eb; border-radius: 10px; margin-bottom: 1rem; overflow: hidden; }
.appt-date { background: #f8fafc; padding: 1rem; display: flex; flex-direction: column; align-items: center; justify-content: center; min-width: 80px; border-right: 1px solid #e5e7eb; }
.appt-date .day { font-size: 1.4rem; font-weight: 800; color: #1f2937; }
.appt-date .month { font-size: 0.8rem; text-transform: uppercase; color: #6b7280; font-weight: 600; }
.appt-date .time { font-size: 0.8rem; color: #1a4a9c; margin-top: 0.3rem; font-weight: 600; }
.appt-details { padding: 1rem; flex: 1; }
.appt-location { font-weight: 600; color: #374151; margin-bottom: 0.5rem; }
.note { font-style: italic; color: #6b7280; font-size: 0.9rem; }
.appt-status { margin: 1rem; align-self: center; padding: 0.3rem 0.8rem; border-radius: 20px; font-size: 0.75rem; font-weight: 700; text-transform: uppercase; }
.badge-pending { background: #fef3c7; color: #b45309; }

/* REVIEWS TAB (STILURI NOI) */
.reviews-list { display: flex; flex-direction: column; gap: 1rem; }
.review-card {
  background: white;
  border: 1px solid #e5e7eb;
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.02);
}
.review-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;
  font-size: 0.9rem;
}
.reviewer-name { font-weight: 700; color: #1a4a9c; }
.review-date { color: #9ca3af; font-size: 0.8rem; }
.star-rating {
  color: #e5e7eb;
  font-size: 1.2rem;
  margin-bottom: 0.8rem;
}
.star-rating .filled { color: #fbbf24; }
.review-text { color: #4b5563; line-height: 1.5; font-style: italic; }

/* COMMON */
.empty-state { text-align: center; padding: 3rem; color: #6b7280; }
.fade-in { animation: fadeIn 0.3s ease-out; }
@keyframes fadeIn { from { opacity: 0; transform: translateY(5px); } to { opacity: 1; transform: translateY(0); } }
.spinner { width: 30px; height: 30px; border: 3px solid #f3f3f3; border-top: 3px solid #1a4a9c; border-radius: 50%; animation: spin 1s linear infinite; margin: 2rem auto; }
@keyframes spin { to { transform: rotate(360deg); } }
</style>