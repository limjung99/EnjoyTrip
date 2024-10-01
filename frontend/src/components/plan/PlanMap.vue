<script setup lang="ts">
import { ref, computed, watch } from "vue";
import {
  KakaoMap,
  KakaoMapMarker,
  KakaoMapMarkerPolyline,
  type KakaoMapMarkerListItem,
} from "vue3-kakao-maps";
import { usePlanStore } from "@/stores/plan.js";
import { storeToRefs } from "pinia";
const contentTypeMapper = {
  12: "관광지",
  14: "문화시설",
  15: "축제공연행사",
  25: "여행코스",
  28: "레포츠",
  32: "숙박",
  38: "쇼핑",
  39: "음식점"
}
const store = usePlanStore();
const args = {
  lat: 36.566826,
  lng: 127.9786567,
  width: 445,
  height: 445,
};
const map = ref(kakao.maps.Map);
const onLoadKakaoMap = (mapRef: kakao.maps.Map) => {
  map.value = mapRef;
  store.setMap(mapRef);
};
const image = {
  imageSrc: "https://vue3-kakao-maps.netlify.app/images/redMarker.png",
  imageWidth: 48,
  imageHeight: 48,
};
function getImage(contentType){
  const image = {
    imageSrc: new URL("http://localhost:5173/src/assets/img/"+contentTypeMapper[contentType]+".png" ,import.meta.url).href,
    imageWidth: 48,
    imageHeight: 48,
  }
  return image
}
const { planList } = storeToRefs(store);
const markerList: Ref<KakaoMapMarkerListItem[]> = computed(() => {
  const result = [];
  if(planList.value.length===0){
    return result
  }
  for (let p in planList.value) {
    let plan = planList.value[p];
    console.log(plan);
    const marker = {
      lat: plan.latitude,
      lng: plan.longitude,
      image: getImage(plan.contentTypeId),
      // image,
      orderBottomMargin: "37px",
    };
    result.push(marker);
  }
  result.push({
    lat: planList.value[0].latitude,
    lng: planList.value[0].longitude,
    image:getImage(planList.value[0].contentTypeId),
    orderBottomMargin:"37px"
  });
  return result;
});
</script>

<template>
  <KakaoMap
    :level="13"
    :lat="args.lat"
    :lng="args.lng"
    :width="args.width"
    :height="args.height"
    :draggable="true"
    @onLoadKakaoMap="onLoadKakaoMap"
  >
    <KakaoMapMarkerPolyline
      :markerList="markerList"
      :showMarkerOrder="true"
      strokeColor="#C74C5E"
      :strokeOpacity="1"
      strokeStyle="shortdot"
    />
  </KakaoMap>
</template>

<style scoped></style>
