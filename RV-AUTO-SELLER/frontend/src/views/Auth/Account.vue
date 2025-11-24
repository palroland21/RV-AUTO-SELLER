<template>
  <Navbar />
  <main class="account-container">

    <!-- Not logged in -->
    <div v-if="!isLoggedIn" class="card not-logged-card">
      <h2>You are not logged in!</h2>
      <p>Please login or register to access your account.</p>

      <div class="buttons">
        <button class="btn primary">Login</button>
        <button class="btn secondary">Register</button>
      </div>
    </div>

    <!-- Logged in -->
    <div v-else class="card profile-card">
      <h2>{{ user?.firstName }} {{ user?.lastName }}</h2>
      <p>{{ user?.email }}</p>

      <!-- Tabs -->
      <div class="tabs">
        <button :class="{ active: tab==='profile' }" @click="tab='profile'">Profil</button>
        <button :class="{ active: tab==='ads' }" @click="tab='ads'">Anunțurile mele</button>
        <button :class="{ active: tab==='favorites' }" @click="tab='favorites'">Favorite</button>
      </div>
    </div>

    <!-- TAB CONTENT -->
    <div class="tab-content" v-if="isLoggedIn">

      <!-- PROFILE TAB -->
      <div v-if="tab === 'profile'">
        <h3>Personal Information</h3>

        <div class="form-grid">
          <div>
            <label>First name</label>
            <input type="text" v-model="user!.firstName" />
          </div>

          <div>
            <label>Last name</label>
            <input type="text" v-model="user!.lastName" />
          </div>

          <div class="full">
            <label>Email</label>
            <input type="email" v-model="user!.email" disabled />
          </div>

          <div class="full">
            <label>Telephone</label>
            <input type="text" v-model="user!.telephone" />
          </div>

          <div class="full">
            <label>Username</label>
            <input type="text" v-model="user!.username" disabled />
          </div>

        </div>

        <button class="btn save">Save</button>
      </div>

      <!-- ADS -->
      <div v-if="tab === 'ads'">
        <p>Listing List</p>
      </div>

      <!-- FAVORITES -->
      <div v-if="tab === 'favorites'">
        <p>Favorite</p>
      </div>

    </div>

  </main>
  <Footer />
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from "vue";
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
import { getCurrentUser } from "@/api/userService";
import type { User } from "@/types/User";

export default defineComponent({
  name: "AccountPage",

  components: {
    Navbar,
    Footer,
  },

  setup() {
    const user = ref<User | null>(null);
    const isLoggedIn = ref<boolean>(false);
    const tab = ref<"profile" | "ads" | "favorites">("profile");

    onMounted(async () => {
      try {
        const data = await getCurrentUser();
        user.value = data;
        isLoggedIn.value = true;
      } catch (err) {
        console.error("User not logged in");
        isLoggedIn.value = false;
      }
    });

    return {
      isLoggedIn,
      user,
      tab,
    };
  },
});
</script>

<style>
.account-container {
display: flex;
justify-content: center;
padding-top: 120px; /* pentru a evita navbar-ul fix */
padding-bottom: 80px;
}

/* Card general */
.card {
width: 750px;
background: rgba(232, 243, 250, 1);
padding: 2rem;
border-radius: 12px;
box-shadow: 0 4px 20px rgba(0, 0, 0, 0.06);
}

.not-logged-card {
text-align: center;
}

/* Butoane login/register */
.buttons {
display: flex;
justify-content: center;
gap: 1rem;
margin-top: 1rem;
}

.btn {
padding: 10px 20px;
border-radius: 8px;
cursor: pointer;
font-weight: 600;
}

.btn.primary {
background: #1f6fff;
color: white;
border: none;
}

.btn.secondary {
background: white;
color: #1f6fff;
border: 2px solid #1f6fff;
}

/* Card user logat */
.profile-card h2 {
margin-bottom: 0;
}

/* Tabs (Profil / Anunțuri / Favorite) */
.tabs {
margin-top: 20px;
display: flex;
gap: 1rem;
background: #f4f6fb;
padding: 8px;
border-radius: 10px;
}

.tabs button {
padding: 8px 16px;
border-radius: 8px;
background: transparent;
border: none;
cursor: pointer;
font-weight: 600;
}

.tabs .active {
background: white;
box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

/* Formular profil */
.form-grid {
display: grid;
grid-template-columns: 1fr 1fr;
gap: 1rem;
margin-top: 1rem;
}

.form-grid .full {
grid-column: 1 / -1;
}

label {
font-size: 0.9rem;
color: #444;
}

input {
width: 100%;
padding: 10px;
border-radius: 6px;
border: 1px solid #dce0ea;
}

/* Buton salvare */
.btn.save {
margin-top: 1.5rem;
background: #1f6fff;
color: white;
border: none;
padding: 12px 24px;
border-radius: 8px;
}
</style>