<script setup>
import TripInfoItem from "./TripInfoItem.vue";
import { useTripStore } from "@/stores/trip.js";
const store = useTripStore()

</script>

<template>
  <div v-for="content in store.filteredContents" :key="content.content_id">
    <trip-info-item :attraction="content"></trip-info-item>
  </div>
  <!-- <page-navigation @pageMove="(val) => { emit('pageMove', val) }" :numOfContents="numOfContents"></page-navigation> -->
</template>

<style scoped></style>