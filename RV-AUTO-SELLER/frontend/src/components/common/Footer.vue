<template>
  <footer class="site-footer">
    <div class="footer-container">

      <div class="footer-column about">
        <div class="footer-brand">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="brand-icon"><path d="M19 17h2c.6 0 1-.4 1-1v-3c0-.9-.7-1.7-1.5-1.9C18.7 10.6 16 10 16 10s-1.3-1.4-2.2-2.3c-.5-.4-1.1-.7-1.8-.7H5c-.6 0-1.1.4-1.4.9l-1.4 2.9A3.7 3.7 0 0 0 2 12v4c0 .6.4 1 1 1h2"/><circle cx="7" cy="17" r="2"/><circle cx="17" cy="17" r="2"/></svg>
          <h3 class="footer-logo-text">RV Auto Seller</h3>
        </div>
        <p class="footer-description">Platforma ta de încredere pentru cumpărarea și vânzarea de automobile.</p>
      </div>

      <div class="footer-column links">
        <h4>Navigare</h4>
        <ul>
          <li><a href="/">Acasă</a></li>
          <li><a href="/listings">Anunțuri</a></li>
          <li><a href="/sell">Vinde</a></li>
        </ul>
      </div>

      <div class="footer-column links">
        <h4>Suport</h4>
        <ul>
          <li><a href="/contact">Contact</a></li>
          <li><a href="/faq">Întrebări frecvente</a></li>
          <li><a href="/politics">Politică de confidențialitate</a></li>
        </ul>
      </div>

      <div class="footer-column social">
        <h4>Urmărește-ne</h4>
        <div class="social-icons">
          <a href="#" aria-label="Facebook">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>
          </a>
          <a href="#" aria-label="Instagram">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>
          </a>
          <a href="#" aria-label="YouTube">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M22.54 6.42a2.78 2.78 0 0 0-1.94-2C18.88 4 12 4 12 4s-6.88 0-8.6.46a2.78 2.78 0 0 0-1.94 2A29 29 0 0 0 1 11.75a29 29 0 0 0 .46 5.33A2.78 2.78 0 0 0 3.4 19c1.72.46 8.6.46 8.6.46s6.88 0 8.6-.46a2.78 2.78 0 0 0 1.94-2 29 29 0 0 0 .46-5.25 29 29 0 0 0-.46-5.33z"></path><polygon points="9.75 15.02 15.5 11.75 9.75 8.48 9.75 15.02"></polygon></svg>
          </a>
        </div>
      </div>

    </div>

    <div class="footer-bottom">
      <p>&copy; {{ new Date().getFullYear() }} RV Auto Seller. Toate drepturile rezervate.</p>
    </div>
  </footer>
</template>

<script setup>
</script>

<style scoped>
.site-footer {
  background-color: var(--vt-c-white);
  border-top: 1px solid var(--color-border);
  padding: 4rem 2rem 1.5rem;
  color: var(--color-text);
}

.footer-container {
  max-width: 1280px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1.5fr 1fr 1fr 1fr;
  gap: 3rem;
  margin-bottom: 3rem;
}

.footer-brand {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-bottom: 1rem;
}

.brand-icon {
  stroke: var(--brand-primary-dark);
}

.footer-logo-text {
  font-size: 1.3rem;
  font-weight: 800;
  color: var(--brand-primary-dark);
  margin: 0;
}

.footer-description {
  font-size: 0.95rem;
  line-height: 1.6;
  color: var(--vt-c-text-light-2);
  max-width: 300px;
}


.footer-column h4 {
  font-size: 1.1rem;
  font-weight: 600;
  color: var(--brand-primary-dark);
  margin-bottom: 1.2rem;
}

.footer-column.links ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-column.links li {
  margin-bottom: 0.8rem;
}

.footer-column.links a {
  text-decoration: none;
  color: var(--vt-c-text-light-2);
  font-size: 0.95rem;
  transition: color 0.2s ease, padding-left 0.2s ease;
}

.footer-column.links a:hover {
  color: var(--brand-primary);
  padding-left: 3px;
}

.social-icons {
  display: flex;
  gap: 1.2rem;
}

.social-icons a {
  color: var(--vt-c-text-light-2);
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
}

.social-icons a:hover {
  color: var(--brand-primary);
  transform: translateY(-2px);
}

.footer-bottom {
  text-align: center;
  padding-top: 2rem;
  border-top: 1px solid var(--color-border);
  font-size: 0.9rem;
  color: #9ca3af;
}


@media (max-width: 768px) {
  .footer-container {
    grid-template-columns: 1fr;
    gap: 2rem;
    text-align: center;
  }

  .footer-brand {
    justify-content: center;
  }

  .footer-description {
    margin: 0 auto;
  }

  .social-icons {
    justify-content: center;
  }
}
</style>