<template>
  <footer class="site-footer">
    <div class="footer-container">
      <div class="footer-column about">
        <h3 class="footer-logo">RV Auto Seller</h3>
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
          <a href="#" aria-label="Facebook">F</a>
          <a href="#" aria-label="Instagram">I</a>
          <a href="#" aria-label="Twitter">T</a>
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
  background-color: var(--color-background-soft);
  color: var(--color-text);
  padding: 3rem 2rem 1rem;
  border-top: 1px solid var(--color-border);
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 2rem;
  margin-bottom: 2rem;
}

.footer-column h4,
.footer-logo {
  font-weight: bold;
  color: var(--brand-primary-dark);
  margin-bottom: 1rem;
}

.footer-logo {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.footer-column p {
  font-size: 0.9rem;
  line-height: 1.5;
  color: var(--vt-c-text-light-2);
}

.footer-column.links ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-column.links li {
  margin-bottom: 0.5rem;
}

.footer-column.links a {
  text-decoration: none;
  color: var(--vt-c-text-light-2);
  transition: color 0.3s ease;
}

.footer-column.links a:hover {
  color: var(--brand-primary);
}

.social-icons {
  display: flex;
  gap: 1rem;
}

.social-icons a {
  font-size: 1.2rem;
  color: var(--vt-c-text-light-2);
  text-decoration: none;
}

.footer-bottom {
  text-align: center;
  padding-top: 1.5rem;
  border-top: 1px solid var(--color-border);
  font-size: 0.85rem;
  color: var(--vt-c-text-light-2);
}
</style>