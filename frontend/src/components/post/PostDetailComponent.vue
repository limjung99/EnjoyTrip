<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { deletePost, getPostDetail, postDislike, postLike } from "@/api/PostApi";
import { useLoginStore } from "@/stores/login";
import CommentComponent from "@/components/post/CommentComponent.vue";
import CommentItem from "@/components/post/item/CommenItem.vue";

const route = useRoute();
const router = useRouter();
const loginStore = useLoginStore();

const { postno } = route.params;
const postDetail = ref({});
const comments = ref([]);

onMounted(() => {
  getPost();
});

const leftPad = (value)=>{
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}

const toStringByFormatting = (src, delim='-')=>{
  const year = src.getFullYear();
  const month = leftPad(src.getMonth() + 1);
  const day = leftPad(src.getDate());

  return [year, month, day].join(delim);
}

const getPost = () => {
  getPostDetail(postno, (res) => {
    postDetail.value = res.data;
    comments.value = postDetail.value.comments;
    // convert date YYYY-MM-DD 
    const createdDate = new Date(postDetail.value.created_at);
    postDetail.value.created_at = toStringByFormatting(createdDate);
  }, (msg) => {
    console.log(msg);
  });
}

const moveList = ()=>{
  router.go(-1);
}

const checkOwner = ()=>{
  if(loginStore.loginUser == null){
    alert("로그인을 해주세요");
    router.go({name : "main"});
    return;
  }

  if(postDetail.value.user_id != loginStore.loginUser.id ){
    return false;
  }

  return true;
}

const moveModify = ()=>{
  if(!checkOwner()){
    alert("게시글을 수정할 권한이 없습니다.");
    return;
  }
  router.push({ name: "post-modify", params: { postno } });
}

const onDeletePost = ()=>{
  deletePost(postno,(res)=>{
    router.push({name : "post-list", query : {page : 1, size:10}});
  },(msg)=>{
    console.log(msg);
  });
}

const onLike = ()=>{
  postLike(postno,()=>{
    getPostDetail(postno, (res) => {

    postDetail.value = res.data;
    comments.value = postDetail.value.comments;
    // convert date YYYY-MM-DD 
    const createdDate = new Date(postDetail.value.created_at);
    postDetail.value.created_at = toStringByFormatting(createdDate);
  }, (err) => {
    console.log(err);
  });
  },(err)=>{
    console.log(err);
  })
}

const onCommentDelete = ()=>{
  console.log("delete emit")
  getPostDetail(postno, (res) => {
    postDetail.value = res.data;
    comments.value = postDetail.value.comments;
    // convert date YYYY-MM-DD 
    const createdDate = new Date(postDetail.value.created_at);
    postDetail.value.created_at = toStringByFormatting(createdDate);
  }, (msg) => {
    console.log(msg);
  });
}

const onDislike = ()=>{
  postDislike(postno,()=>{
  getPostDetail(postno, (res) => {
    postDetail.value = res.data;
    comments.value = postDetail.value.comments;
    // convert date YYYY-MM-DD 
    const createdDate = new Date(postDetail.value.created_at);
    postDetail.value.created_at = toStringByFormatting(createdDate);
  }, (msg) => {
    console.log(msg);
  });
  },(msg)=>{
    console.log(msg);
  })
}

const onCommentCreate = ()=>{
  getPostDetail(postno, (res) => {
    postDetail.value = res.data;
    comments.value = postDetail.value.comments;
    const createdDate = new Date(postDetail.value.created_at);
    postDetail.value.created_at = toStringByFormatting(createdDate);
    console.log(comments.value);
  }, (msg) => {
    console.log(msg);
  });
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          EnjoyTrip
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <div class="row my-2">
          <h2 class="text-secondary px-5">{{ postDetail.post_title }}</h2>
        </div>
        <div class="divider mb-3"></div>
        <div class="row mb-2">
          <div class="col-md-2">
            <p class="post-text"> 좋아요 : {{ postDetail.liked }}</p>
            <p class="post-text"> 싫어요 : {{ postDetail.hated }}</p>
          </div>
          <div class="col-md-2">
            <div>
              <button type="button" class="btn btn-outline-success mb-3 me-1" @click="onLike">
                추천
              </button>
              <button type="button" class="btn btn-outline-danger mb-3 me-1" @click="onDislike">
                비추천
              </button>
            </div>
          </div>
          <div class="col-md-8 text-end">
            <div class="d-flex align-items-center justify-content-end">
              <div>
                <p class="mb-0"><span class="fw-bold"> 작성자 : {{ postDetail.user_id }}</span></p>
                <p class="text-secondary mb-0"> 작성일시 : {{ postDetail.created_at }}</p>
              </div>
            </div>
          </div>
        </div>
        <div class="divider mb-3"></div>
        <div class="text-secondary text-box mb-3">
          {{ postDetail.post_text }}
        </div>
        <div class="divider mb-3"></div>
        <!-- comment -->
        <CommentItem @comment-delete="onCommentDelete" v-for="comment in comments" :commentInfo="comment"></CommentItem>
        <CommentComponent @comment-create="onCommentCreate"></CommentComponent>
        <div class="d-flex justify-content-between">
                 <div>
                    <button type="button" class="btn btn-outline-primary mb-3 me-1" @click="moveList">
                      글목록
                    </button>
                    <button type="button" class="btn btn-outline-success mb-3 me-1" @click="moveModify">
                      글수정
                    </button>
                    <button type="button" class="btn btn-outline-danger mb-3" @click="onDeletePost">
                      글삭제
                    </button>
              </div>
  </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.justify-content-between{
  margin-top: 10px;
}

.container {
  overflow: scroll;
  color: black;
  background-color: white;
  border-radius: 1%;
  margin-top: 7%;
  padding: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.text-box{
  height: 20em;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.divider {
  border-top: 1px solid #ddd;
  margin: 1rem 0;
}

.post-text {
  white-space: pre-wrap; /* 줄바꿈과 공백 유지 */
  margin-bottom: 0.25rem; /* 텍스트 아래 여백 조정 */
}


.btn-outline-success,
.btn-outline-danger,
.btn-outline-primary {
  margin-bottom: 0.25rem; /* 버튼 아래 여백 조정 */
  padding: 0.5rem 1rem; /* 버튼 내부 여백 조정 */
}

.btn{
  margin: 0.5px;
}

h2 {
  margin-bottom: 0.5rem; /* 제목 아래 여백 조정 */
}

.text-box{
  height: 20em;
  text-align:left;
}

.row.mb-2 {
  margin-bottom: 0.5rem; /* row 요소의 아래 여백 조정 */
}

.text-end p {
  margin-bottom: 0.25rem; /* 작성자 정보의 아래 여백 조정 */
}
</style>
