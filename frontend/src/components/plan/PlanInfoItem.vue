<script setup lang="ts">
import { onMounted } from 'vue';
import { usePlanStore } from "@/stores/plan.js";
defineProps({
  attraction: Object
})
const store = usePlanStore()

const openInfo = (attraction) => {
  var iwContent = `<div class='d-flex'>
    <div class="text-black  d-flex">${attraction.title}</div>
    </div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(attraction.latitude+0.025, attraction.longitude), //인포윈도우 표시 위치입니다
    iwRemoveable = true; 
  var infowindow = new kakao.maps.InfoWindow({
    map: store.getMap(), // 인포윈도우가 표시될 지도
    position: iwPosition,
    content: iwContent,
    removable: iwRemoveable
  });
  store.setInfoWindow(infowindow)
  store.getMap().panTo(iwPosition)  
  
}
const deleteTrip = (tripId)=>{
  store.setTripId(tripId)
}

</script>

<template>
  <tr class="text-center d-flex align-items-center">
    <th scope="row"></th>
    <td>
      <button @click="deleteTrip(attraction.tripId)" class="btn btn-primary btn-round btn-outline-primary text-nowrap"
        data-bs-toggle="modal" data-bs-target="#removeModal">
        삭제
      </button>

    </td>
    <td><img :src="attraction.img" width="100px" height="100px" /></td>
    <td class="text-start d-flex flex-column justify-content-center align-items-center">

      <a href="#" @click="openInfo(attraction)" class=" link-dark">
        {{ attraction.title }}
      </a>
      <div>{{ attraction.address }}</div>
    </td>
  </tr>




</template>

<style scoped></style>