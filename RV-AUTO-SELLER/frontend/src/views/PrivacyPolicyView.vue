<template>
  <Navbar />
  <div class="policy-container">
    <div class="policy-wrapper">
      <h1 class="policy-title">Privacy Policy</h1>
      <p class="last-updated">Last Updated: January 14, 2026</p>

      <div class="policy-content">
        <section>
          <h2>1. Introduction</h2>
          <p>Welcome to RV Auto Seller ("we," "our," or "us"). We are committed to protecting your personal information and your right to privacy. If you have any questions or concerns about this privacy notice, or our practices with regards to your personal information, please contact us.</p>
          <p>When you visit our website and use our services, you trust us with your personal information. We take your privacy very seriously. In this privacy notice, we describe our privacy policy. We seek to explain to you in the clearest way possible what information we collect, how we use it, and what rights you have in relation to it.</p>
        </section>

        <section>
          <h2>2. Information We Collect</h2>
          <p>We collect personal information that you voluntarily provide to us when registering at the Services expressing an interest in obtaining information about us or our products and services, when participating in activities on the Services (such as posting messages in our online forums or entering competitions, contests or giveaways) or otherwise contacting us.</p>
          <ul>
            <li><strong>Personal Data:</strong> Name, email address, phone number, passwords, and other similar security information.</li>
            <li><strong>Vehicle Data:</strong> VIN numbers, vehicle photos, descriptions, and location data related to the sale.</li>
            <li><strong>Usage Data:</strong> IP address, browser type, operating system, referring URLs, device information.</li>
          </ul>
        </section>

        <section>
          <h2>3. How We Use Your Information</h2>
          <p>We use personal information collected via our Services for a variety of business purposes described below. We process your personal information for these purposes in reliance on our legitimate business interests, in order to enter into or perform a contract with you, with your consent, and/or for compliance with our legal obligations.</p>
          <ul>
            <li>To facilitate account creation and logon process.</li>
            <li>To post user-generated content (listings, reviews).</li>
            <li>To facilitate appointments and communication between Buyers and Sellers.</li>
            <li>To send administrative information to you, such as product, service and new feature information.</li>
            <li>To protect our Services (fraud monitoring and prevention).</li>
          </ul>
        </section>

        <section>
          <h2>4. Sharing Your Information</h2>
          <p>We only share information with your consent, to comply with laws, to provide you with services, to protect your rights, or to fulfill business obligations.</p>
          <p>Specifically, your contact information (Name, Phone Number) will be visible to other users when you post a listing or schedule an appointment, to facilitate the transaction.</p>
        </section>

        <section>
          <h2>5. Cookies and Tracking Technologies</h2>
          <p>We may use cookies and similar tracking technologies (like web beacons and pixels) to access or store information. Specific information about how we use such technologies and how you can refuse certain cookies is set out in our Cookie Notice.</p>
        </section>

        <section>
          <h2>6. Data Retention</h2>
          <p>We will only keep your personal information for as long as it is necessary for the purposes set out in this privacy notice, unless a longer retention period is required or permitted by law (such as tax, accounting or other legal requirements).</p>
        </section>

        <section>
          <h2>7. Your Privacy Rights (GDPR)</h2>
          <p>If you are a resident of the European Economic Area (EEA), you have certain rights under applicable data protection laws. These may include the right (i) to request access and obtain a copy of your personal information, (ii) to request rectification or erasure; (iii) to restrict the processing of your personal information; and (iv) if applicable, to data portability.</p>
          <p>To make such a request, please use the contact details provided in our Contact page. We will consider and act upon any request in accordance with applicable data protection laws.</p>
        </section>

        <section>
          <h2>8. Contact Us</h2>
          <p>If you have questions or comments about this policy, you may email us at privacy@rvautoseller.com or by post to:</p>
          <address>
            RV Auto Seller Inc.<br>
            Strada Victoriei 100<br>
            Bucharest, Romania
          </address>
        </section>
      </div>
    </div>
  </div>
  <Footer />
</template>

<script setup>
import Navbar from "@/components/common/Navbar.vue";
import Footer from "@/components/common/Footer.vue";
</script>

<style scoped>
.policy-container { background: white; color: #333; padding: 3rem 0; min-height: 100vh; }
.policy-wrapper { max-width: 900px; margin: 0 auto; padding: 0 2rem; }

.policy-title { font-size: 2.5rem; color: #111827; margin-bottom: 0.5rem; border-bottom: 1px solid #eee; padding-bottom: 1rem; }
.last-updated { color: #6b7280; font-style: italic; margin-bottom: 3rem; }

.policy-content section { margin-bottom: 3rem; }
.policy-content h2 { font-size: 1.5rem; color: #1a4a9c; margin-bottom: 1rem; font-weight: 700; }
.policy-content p { line-height: 1.8; color: #4b5563; margin-bottom: 1rem; font-size: 1.05rem; }
.policy-content ul { margin-left: 1.5rem; margin-bottom: 1rem; }
.policy-content li { margin-bottom: 0.5rem; color: #4b5563; line-height: 1.6; }
.policy-content address { font-style: normal; background: #f3f4f6; padding: 1rem; border-left: 4px solid #1a4a9c; color: #374151; display: inline-block; margin-top: 1rem; }
</style>