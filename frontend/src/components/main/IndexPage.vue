<script setup>
import { useLoginStore } from "@/stores/login";

const store = useLoginStore()

const openLoginModal = () => {
  store.openModal();
}

const openSignupModal = ()=>{
  store.openSignupModal();
}

</script>

<template>
    <div class="col-6 container mt-5" style="position:relative; z-index: -1;">
      <div class="content-center brand" style=" z-index: 2;">
        <h1 class="d-flex justify-content-center h1-seo">Enjoy Trip</h1>
          <button @click="openLoginModal" 
            class="btn btn-neutral btn-round" 
            v-if="!store.isLogin"
            type="button" data-bs-toggle="modal"
            data-bs-target="#loginModal">Login</button>
          <button @click="openSignupModal" 
            class="btn btn-neutral btn-round" 
            v-if="!store.signUpModal && !store.isLogin" 
            type="button" data-bs-toggle="modal"
            data-bs-target="#loginModal">Signup</button>
      </div>
    </div>
</template>

<style scoped>

</style>