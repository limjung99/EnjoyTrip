<script setup>
import NavBar from "@/components/include/Navbar.vue"
import { RouterView } from 'vue-router'
import { ref } from 'vue'
import TripAddModal from "@/components/trip/TripAddModal.vue";
import PlanRemoveModal from "./components/plan/PlanRemoveModal.vue";
const openLoginModal = ref(false)
</script>

<template>
  <nav-bar />
  <TripAddModal></TripAddModal>
  <PlanRemoveModal></PlanRemoveModal>
  <body class="index-page sidebar-collapse ">
    <div class="page-header clear-filter d-flex justify-content-center"  filter-color="orange" >
      <div class="page-header-image main-img" data-parallax="true">
      </div style="position:absolute;">
        <RouterView />
    </div>
      </body>
</template>

<style>
@import "./assets/css/bootstrap.min.css";
@import "./assets/css/now-ui-kit.css";
@import "./assets/demo/demo.css";
@import "https://fonts.googleapis.com/css?family=Montserrat:400,700,200";
@import "https://use.fontawesome.com/releases/v5.7.1/css/all.css";
.main-img {
  background-image: url("@/assets/img/header.jpg");
}

</style>
