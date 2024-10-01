<script setup lang="ts">
import { ref, computed, watch,onMounted } from "vue"
import TripSearchBar from "@/components/trip/TripSearchBar.vue";
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import TripPageNavigation from "@/components/trip/TripPageNavigation.vue";
import TripMap from "@/components/trip/TripMap.vue";
import TripInfo from "@/components/trip/TripInfo.vue";

import { useTripStore } from "@/stores/trip.js";
const store = useTripStore()


</script>

<template>
  <div class="" style="background-color: rgba(0,0,0,0.7); width: 95%; margin-top: 100px">
    <div class=" container">
      <div class="button-container">
        <trip-search-bar />
      </div>
      <div class="row">
        <div class="col-5 container justify-content-center">
          <div class="h2 col-xl-12">
            관광 정보 지도
          </div>
          <TripMap />
        </div>
        <div class="col-1"></div>
        <div class="container col-md-6 ml-auto mr-auto justify-content-center">

          <TripInfo></TripInfo>
          <TripPageNavigation style="margin: 0 auto;position: relative;"></TripPageNavigation>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped></style>