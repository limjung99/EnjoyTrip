<script setup>
import PostFormItem from "./item/PostFormItem.vue";

</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="white">글쓰기</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <PostFormItem type="regist" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container{
  background-color : white;
  border-radius: 3%;
  margin-top: 10%;
  height: 60em;
}
</style>
