<script setup lang="ts">
import { onMounted,ref,watch } from 'vue';
import { useTripStore } from "@/stores/trip";
import {chatgptApi} from "@/stores/chatgpt.js"
const props = defineProps({
  attraction: Object
})
const store = useTripStore()
const addToPlan = (contentId) => {
  store.addTripId= contentId
  //추후 plan list 추가

}
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

const gptResponse = ref("설명을 불러오고 있습니다.")

onMounted(async () => {
  gptResponse.value = "설명을 불러오고 있습니다."
  const data = await chatgptApi(props.attraction.title)
  gptResponse.value = data
  console.log(data);
})

watch(props, async (newProps) => {
  gptResponse.value = "설명을 불러오고 있습니다."
  const data = await chatgptApi(newProps.attraction.title)
  gptResponse.value = data
  console.log(data);
  
})

</script>

<template>
  <tr class="text-center d-flex container">
    <th scope="row"></th>
    <td>
      <button @click="addToPlan(attraction.tripId)" class="btn btn-primary btn-round btn-outline-primary text-nowrap" data-bs-toggle="modal"
        data-bs-target="#myModal1">
        추가
      </button>

    </td>
    <td class="col-3"><img :src="attraction.img" width="80px" height="80px" /></td>
    <td class="text-start col-7 ">

      <a href="#" @click="openInfo(attraction);" class=" link-dark">
        {{ attraction.title }}
      </a>
      <div class="d-flex" v-if="gptResponse">{{ gptResponse }}</div>
    </td>
  </tr>




</template>

<style scoped></style>