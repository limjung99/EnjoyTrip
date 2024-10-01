<script setup>
import PlanInfoItem from "./PlanInfoItem.vue";
import { usePlanStore } from "@/stores/plan.js";
const store = usePlanStore()

</script>

<template>
  <div v-for="content in store.planList" :key="content.content_id">
    <plan-info-item :attraction="content">1</plan-info-item>
  </div>
  <!-- <page-navigation @pageMove="(val) => { emit('pageMove', val) }" :numOfContents="numOfContents"></page-navigation> -->
</template>

<style scoped></style>