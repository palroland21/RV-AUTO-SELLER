<template>
  <Navbar />
  <main class="login-container">
    <form class="login-form" @submit.prevent="handleLogin">
      <h2>Login</h2>

      <div class="form-group">
        <label>Username</label>
        <input v-model="username" type="text" required placeholder="Username" />
      </div>

      <div class="form-group">
        <label>Password</label>
        <input v-model="password" type="password" required placeholder="Your password" />
      </div>

      <button type="submit">Login</button>

      <p v-if="errorMessage" class="error"> {{ errorMessage }}</p>
    </form>
  </main>
  <Footer />
</template>



<script setup>
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
import {ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const username = ref("");
const password = ref("");
const errorMessage = ref("");
const router = useRouter();

const handleLogin = async () => {
  try{
    const response = await axios.post("http://localhost:9090/auth/login", {
      username: username.value,
      password: password.value
    });

    const token = response.data.userToken;

    if(!token){
      throw new Error("No token received!");
    }

    localStorage.setItem("token", token);

    router.push("/account");

    // redirectare
    // router.push("/dashboard");
  } catch (error) {
     errorMessage.value = "Username or password is incorrect!";
     console.log(error);
  }
}
</script>


<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  padding: 40px 0;
}

.login-form {
  width: 350px;
  padding: 25px;
  border-radius: 8px;
  background: #f7f7f7;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

.login-form h2 {
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
  width: 100%;
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
}

.error {
  color: red;
  margin-top: 10px;
  text-align: center;
}
</style>