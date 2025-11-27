<template>
  <Navbar />
  <main class="register-container">
    <form class="register-form" @submit.prevent="handleRegister">
      <h2>Register</h2>

      <div class="form-group">
        <label>First Name</label>
        <input
            v-model="firstName"
            type="text"
            required
            maxlength="30"
            placeholder="First Name"
        />
      </div>

      <div class="form-group">
        <label>Last Name</label>
        <input
            v-model="lastName"
            type="text"
            required
            maxlength="30"
            placeholder="Last Name"
        />
      </div>

      <div class="form-group">
        <label>Username</label>
        <input
            v-model="username"
            type="text"
            required
            maxlength="20"
            placeholder="Username"
        />
      </div>

      <div class="form-group">
        <label>Email</label>
        <input
            v-model="email"
            type="email"
            required
            maxlength="50"
            placeholder="example@mail.com"
        />
      </div>

      <div class="form-group">
        <label>Telephone</label>
        <input
            v-model="telephone"
            type="tel"
            required
            maxlength="10"
            placeholder="07xxxxxxxx"
        />
      </div>

      <div class="form-group">
        <label>Password</label>
        <input
            v-model="password"
            type="password"
            required
            placeholder="Create a password"
        />
      </div>

      <div class="form-group">
        <label>Confirm Password</label>
        <input
            v-model="confirmPassword"
            type="password"
            required
            placeholder="Retype password"
        />
      </div>

      <button type="submit">Create Account</button>

      <p v-if="errorMessage" class="error"> {{ errorMessage }}</p>
    </form>
  </main>
  <Footer />
</template>

<script setup>
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";


const firstName = ref("");
const lastName = ref("");
const username = ref("");
const email = ref("");
const telephone = ref("");
const password = ref("");
const confirmPassword = ref("");

const errorMessage = ref("");
const router = useRouter();

const handleRegister = async () => {
  errorMessage.value = "";

  if(password.value !== confirmPassword.value){
    errorMessage.value = "Passwords do not match";
    return;
  }

  try {
    await axios.post("http://localhost:9090/auth/register", {
      firstName: firstName.value,
      lastName: lastName.value,
      username: username.value,
      email: email.value,
      telephone: telephone.value,
      password: password.value
      // role este setat default
    });

    // Daca e succes, redirectionam catre login
    router.push("/login");

  } catch (error) {
    if (error.response && error.response.data) {
      // Daca backend-ul trimite un mesaj de eroare specific
      errorMessage.value = error.response.data.message || "Registration failed.";
    } else {
      errorMessage.value = "An error occurred during registration.";
    }
    console.error(error);
  }
}
</script>

<style scoped>
/* Aceleasi stiluri ca la Login */
.register-container {
  display: flex;
  justify-content: center;
  padding: 40px 0;
}

.register-form {
  width: 350px;
  padding: 25px;
  border-radius: 8px;
  background: #f7f7f7;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

.register-form h2 {
  margin-bottom: 20px;
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  font-weight: 600;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%; /* Important pentru input-uri */
  box-sizing: border-box; /* Asigura ca padding-ul nu strica latimea */
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  background: #1f6fff;
  color: white;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background: #1558d6;
}

.error {
  color: red;
  margin-top: 10px;
  text-align: center;
  font-size: 0.9em;
}
</style>