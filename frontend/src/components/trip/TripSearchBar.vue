<script setup lang="ts">
import {ref} from 'vue'
import { useTripStore } from "@/stores/trip.js";
import {availLocationApi,tripSearchApi} from "@/api/TripApi.js"
const store = useTripStore();

const title = ref("")
const sidos = ref([])
const sidoCode = ref(0)
const contentTypeId = ref(0)

availLocationApi(
  (data) => sidos.value = data.data,  
)
const search = () => {
  if (sidoCode.value===0 || contentTypeId.value===0) return
  tripSearchApi(
    sidoCode.value, contentTypeId.value, title.value,
    (data)=>{
      store.setContents(data.data)
    },
  )
}

</script>

<template>
  <header class=" text-center mt-5">

    
    <div class="d-inline-flex col-xl-10 justify-content-end">
      <form class="d-flex my-3" onsubmit="return false;" role="search">
        <select v-model="sidoCode" id="search-area" class="form-select me-2">
          <option value="0" selected>검색 할 지역 선택</option>
          <option v-for="sido in sidos" :key="sido.sidoCode" :value="sido.sidoCode">{{ sido.sidoName }}</option>
        </select>

        <select v-model="contentTypeId" id="search-content-id" class="form-select me-2">
          <option value="0" selected>관광지 유형</option>
          <option v-for="content in store.contentTypeMap" :key="content.id" :value="content.id">{{ content.type }}</option>
        </select>
        <input v-model="title" id="search-keyword" class="form-control me-2" type="search" placeholder="검색어"
          aria-label="검색어" />
        <button @click="search" id="btn-search" class="btn btn-primary" type="button">검색</button>
      </form>
    </div>
  </header>
</template>

<style scoped></style>