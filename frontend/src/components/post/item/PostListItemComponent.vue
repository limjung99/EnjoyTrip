<template>
    <tr class="text-center">
        <th scope="col">{{ postInfo.postId }}</th>
        <th @click="moveDetail" scope="col">{{ postInfo.postTitle }}</th>
        <th scope="col">{{ postInfo.userId }}</th>
        <th scope="col">{{ postInfo.views }}</th>
        <th scope="col">{{ postInfo.liked }}</th>
        <th scope="col">{{ postInfo.hated }}</th>
        <th scope="col">{{ postInfo.createdAt }}</th>
    </tr>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const props = defineProps({
postInfo : Object
})

const postInfo = props.postInfo;

const moveDetail = ()=>{
    router.push({name : "post-view" , params : { postno : postInfo.postId }})
}


</script>

<style scoped>
.back-ground {
    background-color: pink;
    border-radius: 5%;
}
</style>