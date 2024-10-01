import { tripAxios } from "@/util/http-commons";

const tripApi = tripAxios();

function availLocationApi(success, fail){
    tripApi.get(`/sido`).then(success).catch(fail)
}

function tripSearchApi(sidoCode, contentTypeId, keyword, success, fail){
    
    tripApi.post(`/search`, {keyword:keyword, sidoCode:sidoCode, contentTypeId:contentTypeId}).then(success).catch(fail)
}

function searchHotplaceApi(success, fail) {
    tripApi.get(`/hotplace`).then(success).catch(fail)
}

export { availLocationApi,tripSearchApi,searchHotplaceApi };
