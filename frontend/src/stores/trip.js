import { ref, computed, watch } from "vue";
import { defineStore } from "pinia";

import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
export const useTripStore = defineStore("trip", () => {
  // const markers = ref([]);
  // const map = ref(kakao.maps.Map);

  const contents = ref([]);
  const planIds = ref([]);
  // const numOfMarkers = computed(() => {
  //   return contents.value.data.length;
  // });
  let kakaomap = null;
const contentTypeMap = [
  { type: "관광지", id: 12 },
  { type: "문화시설", id: 14 },
  { type: "축제공연행사", id: 15 },
  { type: "여행코스", id: 25 },
  { type: "레포츠", id: 28 },
  { type: "숙박", id: 32 },
  { type: "쇼핑", id: 38 },
  { type: "음식점", id: 39 },
];
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

  function setContents(contentList){
    contents.value = contentList
  }

  const maxPageIdx = computed(() => {
    return Math.floor(contents.value.length / 5);
  });

  const pageIdx = ref(0);
  const filteredContents = computed(() => {
    return contents.value.slice(pageIdx.value * 5, pageIdx.value * 5 + 5);
  });
  const markers = computed(() => {
    let result = [];
    for (let i in filteredContents.value) {
      let content = filteredContents.value[i];
      let marker = {
        lat: content.latitude,
        lng: content.longitude,

        image: new URL(
          "http://localhost:5173/src/assets/img/" +
            contentTypeMapper[content.contentTypeId] +
            ".png",
          import.meta.url
        ).href,
      };
      console.log(marker.image);
      
      result.push(marker);
    }
    return result;
  });
  watch(markers, () => {
    let cnt = 0;
    let lat = 0;
    let lng = 0;

    for (let i in markers.value) {
      lat += markers.value[i].lat;
      lng += markers.value[i].lng;
      cnt++;
    }
    if (cnt > 0) {
      lat = lat / cnt;
      lng = lng / cnt;

      kakaomap.panTo(new kakao.maps.LatLng(lat, lng));
    }
  });
  function setMap(map) {
    kakaomap = map;
  }
  function getMap() {
    return kakaomap
  }
  const beforeInfoWindow = ref(null);
  function setInfoWindow(infoWindow){
    if(beforeInfoWindow.value){
      
      beforeInfoWindow.value.setMap(null)
    }
    beforeInfoWindow.value = infoWindow
  }


  // 계획 리스트에 attraction을 추가하기 위한 영역
  const addTripId = 0;
  function addTripInPlanList(){

  }
  return {
    setContents,
    addTripInPlanList,
    setInfoWindow,
    contentTypeMapper,contentTypeMap,
    getMap,
    pageIdx,
    maxPageIdx,
    contents,
    filteredContents,
    markers,
    setMap,
    addTripId,
    planIds,
  };
});
