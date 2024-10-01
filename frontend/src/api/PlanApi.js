import { planAxios } from "@/util/http-commons";
import { tripAxios } from "@/util/http-commons";
const planApi = planAxios();
const tripApi = tripAxios();
function getPlanIdsApi(userId, success, fail) {
  planApi.get(`${userId}`).then(success).catch(fail);
}
function addTripPlanListApi(planId, tripId, success, fail) {
  planApi
    .post(`/add`, { planId: planId, tripId: tripId })
    .then(success)
    .catch(fail);
}
function getTripPlanListApi(planId, success, fail) {
  tripApi.get(`/planTrips/${planId}`).then(success).catch(fail);
}
function deleteTripInPlanListApi(planId, tripId, success, fail) {
  planApi.delete(`/delete/${planId}/${tripId}`).then(success).catch(fail);
}
function addPlanListApi(userId, planName, success, fail) {
  planApi.post("", { userId, planName }).then(success).catch(fail);
}
function deletePlanListApi(planId, success, fail) {
    console.log('delete');
  planApi.delete(`/delete/${planId}`).then(success).catch(fail);
}
export {
  deletePlanListApi,
  addPlanListApi,
  getPlanIdsApi,
  addTripPlanListApi,
  getTripPlanListApi,
  deleteTripInPlanListApi,
};
