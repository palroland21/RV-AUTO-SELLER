<template>
  <Navbar />
  <div class="page-container">
    <div class="header-section">
      <h1>Frequently Asked Questions</h1>
      <div class="search-box">
        <input type="text" placeholder="Search for help topics..." />
        <button>Search</button>
      </div>
    </div>

    <div class="faq-container">

      <div class="faq-category">
        <h2>🚗 Buying & Appointments</h2>
        <div class="faq-item" v-for="(item, index) in buyingFaqs" :key="index">
          <button class="faq-question" @click="toggle(index, 'buying')" :class="{ active: activeBuying === index }">
            {{ item.question }}
            <span class="arrow">▼</span>
          </button>
          <div class="faq-answer" v-show="activeBuying === index">
            <p>{{ item.answer }}</p>
          </div>
        </div>
      </div>

      <div class="faq-category">
        <h2>📝 Selling & Listings</h2>
        <div class="faq-item" v-for="(item, index) in sellingFaqs" :key="index">
          <button class="faq-question" @click="toggle(index, 'selling')" :class="{ active: activeSelling === index }">
            {{ item.question }}
            <span class="arrow">▼</span>
          </button>
          <div class="faq-answer" v-show="activeSelling === index">
            <p>{{ item.answer }}</p>
          </div>
        </div>
      </div>

      <div class="faq-category">
        <h2>🔒 Safety & Account</h2>
        <div class="faq-item" v-for="(item, index) in safetyFaqs" :key="index">
          <button class="faq-question" @click="toggle(index, 'safety')" :class="{ active: activeSafety === index }">
            {{ item.question }}
            <span class="arrow">▼</span>
          </button>
          <div class="faq-answer" v-show="activeSafety === index">
            <p>{{ item.answer }}</p>
          </div>
        </div>
      </div>

    </div>
  </div>
  <Footer />
</template>

<script setup>
import { ref } from 'vue';
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";

const activeBuying = ref(null);
const activeSelling = ref(null);
const activeSafety = ref(null);

const toggle = (index, category) => {
  if(category === 'buying') activeBuying.value = activeBuying.value === index ? null : index;
  if(category === 'selling') activeSelling.value = activeSelling.value === index ? null : index;
  if(category === 'safety') activeSafety.value = activeSafety.value === index ? null : index;
};

const buyingFaqs = [
  { question: "How do I schedule a test drive?", answer: "Go to the vehicle's detail page. On the right side (desktop) or bottom (mobile), click the 'Schedule Appointment' button. Select a date and time, and the seller will be notified." },
  { question: "Is the price negotiable?", answer: "Prices are set by the sellers. You can use the messaging system or discuss the price during the appointment/test drive." },
  { question: "Can I cancel an appointment?", answer: "Yes. Go to your Account > Appointments tab. Find the meeting and click 'Cancel'. Please try to notify the seller at least 24 hours in advance." }
];

const sellingFaqs = [
  { question: "How much does it cost to sell a car?", answer: "Currently, RV Auto Seller is free for standard users for up to 3 active listings. Premium plans for dealers will be available soon." },
  { question: "How do I edit my listing?", answer: "Go to Account > My Listings. Click the 'Edit' icon on the card of the vehicle you wish to modify." },
  { question: "Why was my photo rejected?", answer: "Photos must be clear, relevant to the vehicle, and not contain offensive material or watermarks from other websites." }
];

const safetyFaqs = [
  { question: "How do I report a suspicious seller?", answer: "On every listing page, there is a 'Report' link at the bottom. Alternatively, contact us via the Contact page with the Listing ID." },
  { question: "Should I send money in advance?", answer: "NO. Never send money via wire transfer or payment apps before seeing the car in person and signing documents. RV Auto Seller does not handle payments between users." },
  { question: "How do I delete my account?", answer: "Please contact our support team via the Contact form with the subject 'Account Issues' to request data deletion." }
];
</script>

<style scoped>
.page-container { background: #f9fafb; min-height: 100vh; padding-bottom: 4rem; }
.header-section { background: #1a4a9c; color: white; padding: 3rem 1rem; text-align: center; }
.header-section h1 { margin-bottom: 1.5rem; }
.search-box { max-width: 600px; margin: 0 auto; display: flex; gap: 10px; }
.search-box input { flex: 1; padding: 12px; border-radius: 6px; border: none; }
.search-box button { padding: 12px 24px; background: #fbbf24; border: none; border-radius: 6px; font-weight: bold; cursor: pointer; color: #1f2937; }

.faq-container { max-width: 900px; margin: -30px auto 0; padding: 0 1.5rem; position: relative; z-index: 2; }
.faq-category { background: white; border-radius: 12px; padding: 2rem; box-shadow: 0 4px 15px rgba(0,0,0,0.05); margin-bottom: 2rem; }
.faq-category h2 { margin-top: 0; color: #1f2937; border-bottom: 2px solid #f3f4f6; padding-bottom: 1rem; margin-bottom: 1.5rem; font-size: 1.4rem; }

.faq-item { border-bottom: 1px solid #e5e7eb; }
.faq-item:last-child { border-bottom: none; }

.faq-question {
  width: 100%; text-align: left; background: none; border: none; padding: 1.2rem 0;
  font-size: 1.1rem; font-weight: 600; color: #374151; cursor: pointer;
  display: flex; justify-content: space-between; align-items: center;
}
.faq-question:hover { color: #1a4a9c; }
.faq-question .arrow { transition: transform 0.3s; font-size: 0.8rem; }
.faq-question.active .arrow { transform: rotate(180deg); }

.faq-answer { padding-bottom: 1.2rem; color: #6b7280; line-height: 1.6; animation: fadeIn 0.3s; }
@keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }
</style>