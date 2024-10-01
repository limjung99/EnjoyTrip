import { authAxios } from "@/util/http-commons";

const authApi = authAxios();

function login(id, password, success, fail) {
  authApi.post(`/users/login`, { id: id, password: password })
          .then(success)
          .catch(fail);
}

function changePasswordApi(changeUser, token, success, fail) {
  authApi.patch('/users', changeUser, {
    "headers" : {"Authorization" : "Bearer " + token}
  })
      .then(success)
      .catch(fail);
}

function signUpApi(newUser, successHandler, failHandler){
  authApi.post("/users", newUser)
  .then(successHandler)
  .catch(failHandler);
}


export { login, changePasswordApi, signUpApi };
