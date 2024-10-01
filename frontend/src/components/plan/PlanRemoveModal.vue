<script setup>
import { usePlanStore } from "@/stores/plan";
const store = usePlanStore()

const removePlan = ()=>{
  store.deleteTrip()
}

</script>

<template>
  <div class="modal fade modal-mini modal-primary" id="removeModal" tabindex="1050" role="dialog"
    aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">

        <div class="modal-body">
          <p>{{store.planName}}에서 해당 여행지를 삭제하시겠습니까?</p>
        </div>
        <div tabindex="-1" class="modal-footer">
          <button type="button" class="btn btn-link btn-neutral" data-bs-dismiss="modal">아니오</button>
          <button @click="removePlan" type="button" class="btn btn-link btn-neutral" data-bs-dismiss="modal">네</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>