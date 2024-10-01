<script setup>
import {ref,onMounted} from 'vue'

import { usePlanStore } from "@/stores/plan";
import { useTripStore } from "@/stores/trip";
import { useLoginStore } from '@/stores/login';
import {addTripPlanListApi} from "@/api/PlanApi"

const planStore = usePlanStore()
const tripStore = useTripStore()
const loginStore = useLoginStore()

const planId = ref(0)
const addPlan = ()=>{
  if(planId.value===0) return;
  // store.addTripInPlanList()
  addTripPlanListApi(planId.value, tripStore.addTripId,
    (data)=>{console.log(data.data);}
  )
  planId.value = 0
}

</script>

<template>
  <div class="modal fade modal-mini modal-primary" id="myModal1" tabindex="1050" role="dialog"
    aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">

        <div class="modal-body">
          <p>
            <select v-model="planId" id="search-area" class="form-select me-2">
              <option value="0" selected></option>
              <option v-for="plan in planStore.plans" :key="plan.planId" :value="plan.planId">{{ plan.planName }}</option>
            </select>에 해당 여행지를 추가하시겠습니까?</p>
        </div>
        <div tabindex="-1" class="modal-footer">
          <button type="button" class="btn btn-link btn-neutral" data-bs-dismiss="modal">아니오</button>
          <button @click="addPlan" type="button" class="btn btn-link btn-neutral" data-bs-dismiss="modal">네</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>