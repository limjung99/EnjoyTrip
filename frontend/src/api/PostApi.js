import { postAxios } from "@/util/http-commons"
import { useLoginStore } from "@/stores/login";

const postApi = postAxios();

const getPostsBySizeNPage = (offset,_size,successHandle,failHandle) =>{
    postApi.get("",{
        params : {
            page : offset,
            size : _size
        }
    })
            .then(successHandle)
            .catch(failHandle);
}

const createPost = (newPost, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;
    const refreshToken = loginStore.refreshToken;

    postApi.post("",newPost,{
        headers : {
            "Authorization" : "Bearer " + accessToken
        }
    })
    .then(successHandle)
    .catch(failHandle);
}

const getPostDetail = (postId, successHandle, failHandle)=>{
    postApi.get("/"+postId)
        .then(successHandle)
        .catch(failHandle);
}

const updatePost = (postId, updatePost, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;
    const refreshToken = loginStore.refreshToken;

    postApi.patch("/"+postId, updatePost,{
        "headers" : {
            "Authorization" : "Bearer " + accessToken
        }
    })
            .then(successHandle)
            .catch(failHandle);
}

const deletePost = (postId, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;
    const refreshToken = loginStore.refreshToken;

    postApi.delete("/"+postId,{
        headers : {
            "Authorization" : "Bearer " + accessToken
        }
    }).then(successHandle)
        .catch(failHandle);
}

const postLike = (postId, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;
    const refreshToken = loginStore.refreshToken;
    console.log(accessToken);
    postApi.post("/"+postId+"/like",{},{
        headers : {
            "Authorization" : "Bearer " + accessToken
        }
    }).then(successHandle)
    .catch(failHandle);
}

const postDislike = (postId, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;
    const refreshToken = loginStore.refreshToken;

    postApi.post("/"+postId+"/dislike",{},{
        headers : {
            "Authorization" : "Bearer " + accessToken
        }
    }).then(successHandle)
    .catch(failHandle);
}

const createComment = (postId, comment, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;

    postApi.post("/"+postId+"/comment",comment,{
        headers : {
            "Authorization" : "Bearer " + accessToken
        }
    }).then(successHandle)
        .catch(failHandle);
}

const deleteComment = (postId, commentId, successHandle, failHandle)=>{
    const loginStore = useLoginStore();
    const accessToken = loginStore.accessToken;

    postApi.delete("/"+postId+"/comment/"+commentId,{
        headers : {
            "Authorization" : "Bearer " + accessToken
        }
    }).then(successHandle)
        .catch(failHandle);
}

export { getPostsBySizeNPage, deleteComment, createPost, getPostDetail, updatePost, deletePost, postLike, postDislike, createComment }