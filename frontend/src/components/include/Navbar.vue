<script setup>
import { RouterLink } from "vue-router"
import { useLoginStore } from "@/stores/login.js"
const store = useLoginStore()

const logout = ()=>{
  store.logout();
}
</script>
  
<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
      <div class="container"  >
        <div class="navbar-translate">
          <router-link :to="{name:'main'}" class="navbar-brand">
            Enjoy Trip
          </router-link>
        </div>
        <div class="collapse navbar-collapse justify-content-end" id="navigation"
          data-nav-image="./assets/img/blurred-image-1.jpg">
          <ul class="navbar-nav">
            <div class="navbar-nav" v-if="store.isLogin">
              <li class="nav-item">
                <router-link :to="{ name: 'trip' }" class="nav-link">여행지검색</router-link>
              </li>
              <li class="nav-item">
                <router-link :to="{ name: 'plan' }" class="nav-link">나의여행지</router-link>
              </li>
              <li class="nav-item">
                    <router-link :to="{ name: 'posts' }" class="nav-link">자유게시판</router-link>
                  </li>
                  <li class="nav-item">
                    <router-link :to="{ name: 'mypage' }" class="nav-link">마이페이지</router-link>
                  </li>
                  <li class="nav-item">
                  <router-link @click="logout" :to="{ name: 'main' }" class="nav-link">로그아웃</router-link>
              </li>
            </div>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<style scoped>
</style>