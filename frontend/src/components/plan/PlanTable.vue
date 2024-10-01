<script setup>
import {ref, onMounted} from 'vue'
import PlanInfo from "./PlanInfo.vue";
import {usePlanStore} from "@/stores/plan.js"
import {useLoginStore} from "@/stores/login.js"
import { PerfectScrollbar } from 'vue3-perfect-scrollbar'

const store = usePlanStore()
const loginStore = useLoginStore()
const buttonColor = ref("btn_orange")
const openPlanNameInput = ref(false)
const newPlanName = ref("")
let beforeButton = null
const selectButton = (id, plan)=>{
  
  const button = document.getElementById(id)
  if(button){
    if(beforeButton && button.id== beforeButton.id){
      return
    }
    else if(beforeButton){
    }
    // beforeButton.style.backgroundColor=""
    button.ref="buttonColor"
    beforeButton = button
    store.selectedPlanId = plan.planId
    store.setPlanList()
    store.setPlanName(plan.planName)
  }

}
const openAddPlan=()=>{
  openPlanNameInput.value = !openPlanNameInput.value
}
const addPlanSure = ()=>{
    //TODO : userId를 로그인한 유저로 바꾸기
  console.log(newPlanName);
  store.addPlanList(newPlanName.value);
  openPlanNameInput.value = false
}
const confirmDelete = ()=>{
  let isDelete =confirm(store.planName+"을(를) 정말로 삭제하시겠습니까?")
  if(isDelete){
    //TODO : userId를 로그인한 유저로 바꾸기
    store.deletePlanList()
    beforeButton=null
  }
}

</script>

<template>
  <h3>
    {{ store.planName }}
  </h3>
  <div class="col">
    <!-- <p class="category">여행계획표</p> -->
    <!-- Tabs with Background on Card -->
    <div class="card" style="background: transparent;">
      <div class="card-header">
        <ul
          class="nav nav-tabs nav-tabs-neutral justify-content-start"
          role="tablist"
          data-background-color=""
        >
          <li v-for="(plan,idx) in store.plans" :key="plan.planId" class="nav-item">
            <button ref="" @click="selectButton(idx, plan)" :id="idx" class="nav-link" data-toggle="toggle"  role="tab" style="">{{ idx+1 }}</button>
          </li>
          <li class="nav-item">
            <button @click="openAddPlan" class="nav-link" data-toggle="toggle"  role="tab" style=""> <h6>+</h6> </button>
          </li>
          <li class="nav-item">
            <button @click="confirmDelete" v-if="store.selectedPlanId" class="nav-link" data-toggle="toggle"  role="tab" style=""><h6>삭제</h6></button>
          </li>
        </ul>
      </div>
      <div class="row nav nav-tabs nav-tabs-neutral" v-show="openPlanNameInput">

        <div class="form-group ">
          <input v-model="newPlanName" placeholder="여행 계획 제목" class="form-control" type="text" v-show="openPlanNameInput"/>
        </div>
        <div class="nav-item " >
          <button @click="addPlanSure" class="nav-link" role="tab" style="">추가</button>
        </div>
      </div>
      <div class="card-body" >
        <!-- Tab panes -->
        <div class="tab-content text-center">
          <PerfectScrollbar class="tab-pan active" id="home1" role="tabpanel">
            <PlanInfo></PlanInfo>

          </PerfectScrollbar>
        
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.ps {
  height: 400px; /* or max-height: 400px; */
}
.btn_orange{
  background-color: orangered;
}
</style>
