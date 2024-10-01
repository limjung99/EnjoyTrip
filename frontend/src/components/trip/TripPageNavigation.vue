<script setup>
import { useTripStore } from "@/stores/trip.js";
import { ref } from 'vue';

const store = useTripStore();
const setPageIdx=(val)=>{
  store.pageIdx  = store.pageIdx+val
}
</script>

<template>
  <div class="d-flex col-sm-6  justify-content-center">
    <ul class="pagination ">
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">
            <i class="fa fa-angle-double-left" aria-hidden="true"></i>
          </span>
        </a>
      </li>
      <li v-show="store.pageIdx >= 2" class="page-item ">
        <a @click="setPageIdx(-2)" class="page-link" href="#">{{ store.pageIdx-2 }}</a>
      </li>
      <li v-show="store.pageIdx >= 1" class="page-item">
        <a @click="setPageIdx(- 1)" class="page-link" href="#">{{ store.pageIdx-1 }}</a>
      </li>
      <li class="page-item">
        <button @click="setPageIdx(0)" class="page-link" href="#" >{{ store.pageIdx }}</button>
      </li>
      <li v-show="store.maxPageIdx > store.pageIdx" class="page-item">
        <a @click="setPageIdx(1)" class="page-link" href="#">{{ store.pageIdx+1 }}</a>
      </li>
      <li v-show="store.maxPageIdx > store.pageIdx + 1" class="page-item">
        <a @click="setPageIdx(2)" class="page-link" href="#">{{ store.pageIdx+2 }}</a>
      </li>
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Next">
          <span aria-hidden="true">
            <i class="fa fa-angle-double-right" aria-hidden="true"></i>
          </span>
        </a>
      </li>
    </ul>
  </div>
</template>
<style>
.page-link{
  color:orangered;
}
</style>