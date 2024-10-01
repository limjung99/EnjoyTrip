<script setup>
import IndexPage from "@/components/main/IndexPage.vue"
import LoginModal from "@/components/user/LoginModal.vue"
import SignUpModalComponent from "@/components/user/SignUpModalComponent.vue"
import HotPlace from "@/components/hotplace/HotPlace.vue";
import { useLoginStore } from "@/stores/login";

const loginStore = useLoginStore();
</script>

<template>

  <index-page />
  <login-modal v-if="loginStore.loginModal"/>
  <sign-up-modal-component v-if="loginStore.signUpModal"/>
  <hot-place v-if="loginStore.isLogin" />
</template>

<style scoped>
/* .main_img {
  background-image: url("@/assets/img/header.jpg");
} */
</style>