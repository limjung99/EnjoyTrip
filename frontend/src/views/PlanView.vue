<script setup lang="ts">
import { ref, computed, watch,onMounted } from "vue"
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import PlanTable from "@/components/plan/PlanTable.vue"
import PlanMap from "@/components/plan/PlanMap.vue";

import { usePlanStore } from "@/stores/plan.js";
import { useLoginStore } from "@/stores/login";
const store = usePlanStore()
const loginStore = useLoginStore()
onMounted(()=>{
  store.setUser(loginStore.loginUser)
  store.selectedPlanId = ""
  store.setPlanList()
  
})
</script>

<template>
  <div class="" style="background-color: rgba(0,0,0,0.7); width: 95%; margin-top: 100px; padding-top:5%">
    <div class=" container">
      
      <div class="row">
        <div class="col-5 container justify-content-center">
          <div class="h2 col-xl-12">
            여행 계획 지도
          </div>
          <PlanMap/>
        </div>
        <div class="col-1"></div>
        <div class="container col-md-6 ml-auto mr-auto justify-content-center">
            <PlanTable></PlanTable>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped></style>