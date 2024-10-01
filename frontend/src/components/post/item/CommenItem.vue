<template>
    <div class="card">
  <div class="card-body">
    <div class="row">
        <div class="col comment-text">
            {{ commentInfo.comment_text }}
        </div>
        <div class="col user-id">
            <svg v-if="commentInfo.user_id == loginUser.id "
            @click="onCommentDelete"
            xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
            </svg> 
            <div>작성자 : {{ commentInfo.user_id }}</div>
        </div>
    </div>
  </div>
</div>
</template>

<script setup>
import {useLoginStore} from "@/stores/login.js";
import { deleteComment } from "@/api/PostApi.js";
import { useRoute } from "vue-router";

const route = useRoute();
const loginStore = useLoginStore();
const props = defineProps(['commentInfo']);
const emit = defineEmits(['commentDelete']);
const loginUser = loginStore.loginUser;
const commentInfo = props.commentInfo;
const postId = route.params.postno;

const onCommentDelete = ()=>{
    deleteComment(postId,commentInfo.comment_id,(res)=>{
        emit("commentDelete");
    },(msg)=>{
        console.log(msg);
    })
}

</script>

<style scoped>
.comment-text{
    text-align: start;
}

.user-id{
    text-align: end;
}

.card {
  margin-bottom: 10px; /* 카드 간의 간격을 5px로 설정 */
}

.card-body {
  border-radius: 10%;
  text-align: start;
}

</style>

