import { ref, computed, watch, onMounted } from "vue";
import { defineStore } from "pinia";

import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";
import {
  deletePlanListApi,
  getPlanIdsApi,
  getTripPlanListApi,
  deleteTripInPlanListApi,
  addPlanListApi,
} from "@/api/PlanApi";
import { login } from "@/api/authApi";
export const usePlanStore = defineStore("plan", () => {
  let loginUser = null;
  let kakaomap = null;
  const markers = ref([]);
  function setMap(map) {
    kakaomap = map;
  }
  function getMap() {
    return kakaomap;
  }
  const plans = ref([]);
  const planList = ref([]);
  const planName = ref("");
  let selectedPlanId = ref(null);
  let selectedTripId = ref(null);
  const setPlanName = (name) => {
    planName.value = name;
  };
  const setTripId = (tripId) => {
    selectedTripId.value = tripId;
  };
  const setPlans = (userId) => {
    getPlanIdsApi(userId, (data) => {
      plans.value = data.data;
      selectedPlanId.value = data.data.planId;
      // console.log(selectedPlanId);
    });
  };
  const setUser = (newLoginUser) => {
    loginUser = newLoginUser;
    setPlans(loginUser.id);
    console.log(loginUser, newLoginUser);
  };
  const setPlanList = () => {
    
    if (!selectedPlanId.value ) {
      
      planList.value = []
    }
    else {
      
      getTripPlanListApi(selectedPlanId.value, (data) => {
        planList.value = data.data;
      });
    }
  };
  const deleteTrip = () => {
    deleteTripInPlanListApi(
      selectedPlanId.value,
      selectedTripId.value,
      () => {
        setPlanList();
      },
      () => {}
    );
  };
  const deletePlanList = () => {
    // console.log(selectedPlanId.value,userId);
    deletePlanListApi(selectedPlanId.value, () => {
      selectedPlanId.value = null;
      setPlans(loginUser.id);
      setPlanList();
    });
  };

  const addPlanList = (planName) => {
    console.log(loginUser.id);
    
    addPlanListApi(loginUser.id, planName, () => {
      setPlans(loginUser.id);
    });
  };

  //임시 id로 plan 초기화
  // setPlans("1"  )
  // setPlanList(1)
  // setPlanName("경주여행")
  //

  const beforeInfoWindow = ref(null);
  function setInfoWindow(infoWindow) {
    if (beforeInfoWindow.value) {
      beforeInfoWindow.value.setMap(null);
    }
    beforeInfoWindow.value = infoWindow;
  }

  return {
    setUser,
    deletePlanList,
    addPlanList,
    deleteTrip,
    setTripId,
    selectedTripId,
    selectedPlanId,
    planName,
    setPlanName,
    setPlanList,
    setInfoWindow,
    planList,
    setMap,
    getMap,
    setPlans,
    plans,
  };
});
