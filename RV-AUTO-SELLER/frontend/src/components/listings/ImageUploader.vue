<template>
  <div class="upload-container">
    <div
        class="drop-zone"
        @dragover.prevent="isDragging = true"
        @dragleave.prevent="isDragging = false"
        @drop.prevent="handleDrop"
        @click="triggerInput"
        :class="{ 'dragging': isDragging }"
    >
      <input
          type="file"
          ref="fileInput"
          multiple
          accept="image/png, image/jpeg, image/jpg"
          class="hidden-input"
          @change="handleFileSelect"
      />

      <div class="upload-content">
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="upload-icon"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/><polyline points="17 8 12 3 7 8"/><line x1="12" y1="3" x2="12" y2="15"/></svg>
        <p><strong>Click to upload</strong> or drag & drop</p>
        <span class="file-info">PNG, JPG (MAX. 5MB)</span>
      </div>
    </div>

    <div v-if="previews.length > 0" class="previews-grid">
      <div v-for="(preview, index) in previews" :key="index" class="preview-item">
        <img :src="preview" alt="Preview" />
        <button type="button" class="remove-btn" @click="removeImage(index)">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const emit = defineEmits(['update:images']);
const fileInput = ref(null);
const isDragging = ref(false);
const previews = ref([]);
const files = ref([]);

const triggerInput = () => {
  fileInput.value.click();
};

const processFiles = (selectedFiles) => {
  const newFiles = Array.from(selectedFiles).filter(file => file.type.startsWith('image/'));

  newFiles.forEach(file => {
    files.value.push(file);
    const reader = new FileReader();
    reader.onload = (e) => previews.value.push(e.target.result);
    reader.readAsDataURL(file);
  });

  emit('update:images', files.value);
};

const handleFileSelect = (event) => {
  processFiles(event.target.files);
};

const handleDrop = (event) => {
  isDragging.value = false;
  processFiles(event.dataTransfer.files);
};

const removeImage = (index) => {
  previews.value.splice(index, 1);
  files.value.splice(index, 1);
  emit('update:images', files.value);
};
</script>

<style scoped>
.upload-container {
  width: 100%;
}

.drop-zone {
  border: 2px dashed var(--color-border);
  border-radius: 12px;
  padding: 3rem;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s ease;
  background-color: var(--vt-c-white-soft);
}

.drop-zone:hover, .drop-zone.dragging {
  border-color: var(--brand-primary);
  background-color: rgba(26, 74, 156, 0.05);
}

.upload-icon {
  stroke: var(--brand-primary);
  margin-bottom: 1rem;
}

.upload-content p {
  color: var(--color-text);
  margin-bottom: 0.5rem;
}

.file-info {
  font-size: 0.85rem;
  color: var(--vt-c-text-light-2);
}

.hidden-input {
  display: none;
}

.previews-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
  gap: 1rem;
  margin-top: 1.5rem;
}

.preview-item {
  position: relative;
  aspect-ratio: 1;
  border-radius: 8px;
  overflow: hidden;
  border: 1px solid var(--color-border);
}

.preview-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.remove-btn {
  position: absolute;
  top: 5px;
  right: 5px;
  background: rgba(0,0,0,0.6);
  border: none;
  color: white;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>