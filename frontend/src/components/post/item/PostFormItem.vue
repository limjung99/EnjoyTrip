<script setup>
import { onMounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useLoginStore } from "@/stores/login";
import { createPost, updatePost } from "@/api/PostApi";

const loginStore = useLoginStore();
const router = useRouter();
const route = useRoute();

const props = defineProps({ type: String });

const post = ref({
  postTitle : "",
  postText : ""
});

const moveList = ()=>{
  router.push({ name: "post-list" , query : {page : 1, size : 10}});
}

const onSubmitPost = ()=>{

  const newPost = {
    post_title : post.value.postTitle,
    post_text : post.value.postText
  }

  if(newPost.post_text === "" || newPost.post_text === ""){
    alert("빈 제목 혹은 빈 본문은 허용되지 않습니다.");
    return;
  }

  createPost(newPost,(res)=>{
    router.go(-1); 
  },(msg)=>{
    console.log(msg);
  })
}

const onUpdatePost = ()=>{
  const postId = route.params.postno;

  const updateData = {
    update_text : post.value.postText,
    update_title : post.value.postTitle
  }

  updatePost(postId, updateData,(res)=>{
    router.push({ name: "post-list", query : { page : 1 , size : 10}});
  },(msg)=>{
    console.log(msg);
  })
}

</script>

<template>
    <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="subject" class="form-label">제목 : </label>
      <input type="text" class="form-control" v-model="post.postTitle" placeholder="제목..." />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용 : </label>
      <textarea class="form-control" v-model="post.postText" rows="100"></textarea>
    </div>
    <div class="col-auto text-center">
      <button type="submit" class="btn btn-outline-primary mb-3" @click="onSubmitPost" v-if="type === 'regist'">
        글작성
      </button>
      <button type="submit" @click="onUpdatePost" class="btn btn-outline-success mb-3" v-else>글수정</button>
      <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
        목록으로이동...
      </button>
    </div>
  </form>
</template>

<style scoped>


</style>
