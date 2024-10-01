<template>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-10">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
              <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
                글쓰기
              </button>
            </div>
            <div class="col-md-5 offset-5">
              <form class="d-flex">
                <div class="input-group input-group-sm">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="검색어..."
                  />
                  <button class="btn btn-dark" type="button">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">좋아요</th>
                <th scope="col">싫어요</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>
              <PostListItemComponent 
                  v-for="postInfo in postInfoList"
                  :key="postInfo.postId"
                  :postInfo="postInfo"
              ></PostListItemComponent>
            </tbody>
          </table>
          <PaginationComponent
  @getNewPage="getNewPostInfoList"
  :postCount="postNumber"
/>
        </div>
      </div>
    </div>
  </template>   

<script setup>
import { getPostsBySizeNPage } from "@/api/PostApi";
import PostListItemComponent from "@/components/post/item/PostListItemComponent.vue"
import PaginationComponent from "./PaginationComponent.vue";
import { onMounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

const pageOffset = route.query.page;
const size = route.query.size;

const postInfoList = ref([]);
const postNumber = ref(0);

const moveWrite = ()=>{
  router.push({name : "post-write"})
}

const getNewPostInfoList = (nextPage)=>{
  getPostsBySizeNPage(nextPage,size,(res)=>{
    postInfoList.value = res.data.items
                            .map(item=>{
                              return {
                                postId : item.post_id,
                                postTitle : item.post_title,
                                userId : item.user_id,
                                createdAt : item.created_at,
                                hated : item.hated,
                                liked : item.liked,
                                views : item.views
                              }});

    postNumber.value = res.data.meta.total;},(msg)=>{
      console.log(msg);
    })
}

onMounted(()=>{
  getPostsBySizeNPage(pageOffset,size,(res)=>{
    console.log(res.data);
    postInfoList.value = res.data.items
                            .map(item=>{
                              return {
                                postId : item.post_id,
                                postTitle : item.post_title,
                                userId : item.user_id,
                                createdAt : item.created_at,
                                hated : item.hated,
                                liked : item.liked,
                                views : item.views
                              }});
                              console.log(postInfoList.value);
    postNumber.value = res.data.meta.total;
  },(msg)=>{
    console.log(msg);
  })
})
</script>

<style scoped>
.container{
  background-color : white;
  border-radius: 1%;
  margin-top: 7%;
  height: 60em;
}


.input-group{
  align-items: center;
}
</style>
