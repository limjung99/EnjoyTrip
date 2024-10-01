<template>
    <div class="pagination-container">
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" @click="clickPrev" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li v-for="page in totalPages" class="page-item" :key="page">
            <a class="page-link" @click="clickPage(page)" aria-label="Previous">
              <span aria-hidden="true">{{ page }}</span>
            </a>
          </li>
          <li class="page-item">
            <a class="page-link" @click="clickNext" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, watch, onMounted } from 'vue';
  
  const props = defineProps({
    postCount: {
      type: Number,
      required: true
    }
  });
  
  const emit = defineEmits(['getNewPage']);
  
  const nowPageNum = ref(1);
  const itemsPerPage = 10; // 페이지당 아이템 수
  const totalPages = computed(() => {
    return Math.ceil(props.postCount / itemsPerPage);
  });
    
  watch(() => props.postCount, (newVal) => {
    console.log('postCount changed:', newVal);
    if (nowPageNum.value > totalPages.value) {
      nowPageNum.value = totalPages.value;
    }
  });
  
  const clickPage = (page) => {
    nowPageNum.value = page;
    emit('getNewPage', nowPageNum.value);
  };
  
  const clickNext = () => {
    if (nowPageNum.value < totalPages.value) {
      nowPageNum.value++;
      emit('getNewPage', nowPageNum.value);
    }
  };
  
  const clickPrev = () => {
    if (nowPageNum.value > 1) {
      nowPageNum.value--;
      emit('getNewPage', nowPageNum.value);
    }
  };
  </script>
  
  <style scoped>
  .pagination-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  </style>
  