<template>
  <Navbar />
  <div class="page-container">
    <div class="header-banner">
      <h1>Contact Us</h1>
      <p>We are here to help you with any questions regarding buying or selling on RV Auto Seller.</p>
    </div>

    <div class="content-wrapper">
      <div class="grid-layout">

        <div class="card form-card">
          <h3>Send us a message</h3>
          <form @submit.prevent="sendMessage">
            <div class="form-group">
              <label>Subject</label>
              <select v-model="form.subject">
                <option>General Inquiry</option>
                <option>Report a Listing</option>
                <option>Account Issues</option>
                <option>Business Partnership</option>
              </select>
            </div>

            <div class="form-row">
              <div class="form-group">
                <label>Name</label>
                <input type="text" v-model="form.name" placeholder="Your full name" required />
              </div>
              <div class="form-group">
                <label>Email</label>
                <input type="email" v-model="form.email" placeholder="name@example.com" required />
              </div>
            </div>

            <div class="form-group">
              <label>Message</label>
              <textarea v-model="form.message" rows="6" placeholder="How can we help you today?" required></textarea>
            </div>

            <button type="submit" class="btn-primary">Send Message</button>
          </form>
        </div>

        <div class="info-column">
          <div class="card info-card">
            <h3>Contact Details</h3>
            <ul class="contact-list">
              <li>
                <span class="icon">📍</span>
                <div>
                  <strong>Headquarters</strong>
                  <p>Strada Victoriei 100, Bucharest, Romania</p>
                </div>
              </li>
              <li>
                <span class="icon">📞</span>
                <div>
                  <strong>Phone Support</strong>
                  <p>+40 745 123 456</p>
                  <small>Mon-Fri: 09:00 - 18:00</small>
                </div>
              </li>
              <li>
                <span class="icon">✉️</span>
                <div>
                  <strong>Email</strong>
                  <p>support@rvautoseller.com</p>
                </div>
              </li>
            </ul>
          </div>

          <div class="card map-card">
            <div class="map-placeholder">
              <span>Google Maps Area</span>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
  <Footer />
</template>

<script setup>
import { reactive } from 'vue';
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";

const form = reactive({
  subject: 'General Inquiry',
  name: '',
  email: '',
  message: ''
});

const sendMessage = () => {
  alert(`Thank you, ${form.name}! Your message has been sent. We will reply to ${form.email} shortly.`);
  // Aici ai lega de un endpoint real
  form.message = '';
};
</script>

<style scoped>
.page-container { background: #f8f9fa; min-height: 80vh; padding-bottom: 4rem; }
.header-banner { background: #1a4a9c; color: white; padding: 4rem 1rem; text-align: center; margin-bottom: 2rem; }
.header-banner h1 { font-size: 2.5rem; margin-bottom: 0.5rem; }
.content-wrapper { max-width: 1100px; margin: 0 auto; padding: 0 1.5rem; }

.grid-layout { display: grid; grid-template-columns: 1.5fr 1fr; gap: 2rem; }

.card { background: white; padding: 2rem; border-radius: 12px; border: 1px solid #e5e7eb; box-shadow: 0 4px 6px rgba(0,0,0,0.02); }
.card h3 { margin-top: 0; margin-bottom: 1.5rem; color: #1f2937; border-bottom: 1px solid #eee; padding-bottom: 1rem; }

.form-group { margin-bottom: 1.2rem; }
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
label { display: block; margin-bottom: 0.5rem; font-weight: 600; color: #374151; }
input, select, textarea { width: 100%; padding: 0.8rem; border: 1px solid #d1d5db; border-radius: 6px; font-family: inherit; }
.btn-primary { background: #1a4a9c; color: white; padding: 1rem 2rem; border: none; border-radius: 6px; font-weight: 700; cursor: pointer; width: 100%; font-size: 1rem; }

.contact-list { list-style: none; padding: 0; }
.contact-list li { display: flex; gap: 1rem; margin-bottom: 1.5rem; }
.contact-list .icon { font-size: 1.5rem; background: #eff6ff; width: 50px; height: 50px; display: flex; align-items: center; justify-content: center; border-radius: 50%; }

.map-card { padding: 0; overflow: hidden; height: 250px; margin-top: 1.5rem; }
.map-placeholder { width: 100%; height: 100%; background: #e5e7eb; display: flex; align-items: center; justify-content: center; color: #6b7280; font-weight: 600; }

@media(max-width: 768px) { .grid-layout { grid-template-columns: 1fr; } }
</style>