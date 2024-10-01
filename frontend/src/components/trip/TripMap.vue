<script setup lang="ts">
import { ref, computed, watch } from "vue"
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { useTripStore } from "@/stores/trip.js"
const store = useTripStore()
const args = {
  lat: 37.566826,
  lng: 126.9786567,
  width: 445,
  height: 445,
};
const map = ref(kakao.maps.Map)

const onLoadKakaoMap = (mapRef: kakao.maps.Map) => {
  map.value = mapRef;
  store.setMap(mapRef)
}

</script>

<template>

  <KakaoMap :level="9" :lat="args.lat" :lng="args.lng" :width="args.width" :height="args.height" :draggable="true"
    @onLoadKakaoMap="onLoadKakaoMap">
    <KakaoMapMarker @click="console.log(marker.image )" v-for="marker in store.markers" :key="marker" :lat="marker.lat"
      :lng="marker.lng" :image="{
      imageSrc: marker.image,
      imageWidth: 48,
      imageHeight: 48,
      imageOption: {}
    }">

    </KakaoMapMarker>
  </KakaoMap>

</template>

<style scoped></style>