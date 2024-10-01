<template>
    <div class="row-mb-1 comment-box">
    <div class="col-md-10">
        <div class="form-floating">
        <textarea class="form-control"
         placeholder="댓글을 입력해주세요"
          id="floatingTextarea2"
          v-model="commentText"
           style="height: 100px"></textarea>
        </div>
    </div>
    <button type="button"
    @click="onCreateComment"
     class="btn btn-outline-primary mb-3 me-1">댓글달기</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { createComment, getPostDetail } from '@/api/PostApi';
import { useRoute} from "vue-router";

const commentText = ref("");
const route = useRoute();

const emit = defineEmits(['commentCreate']);

const onCreateComment = ()=>{
    const commentData = {
        comment_text : commentText.value
    }
    
    const postId = route.params.postno;
    commentText.value = "";

    createComment(postId, commentData,(res)=>{
        emit('commentCreate');
    },(err)=>{
        console.log(err);
    })
}

</script>

<style scoped>
.comment-box{
    display: flex;
}
</style>