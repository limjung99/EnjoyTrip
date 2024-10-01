<script setup>
import PostFormItem from "./item/PostFormItem.vue";
import { useRoute } from "vue-router";

const route = useRoute();

</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          게시글 수정
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <PostFormItem type="modify" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  background-color: white;
  border-radius: 1%;
  margin-top: 7%;
  padding: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.text-center{
  color: black;
}
</style>
