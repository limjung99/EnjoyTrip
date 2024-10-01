import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { login } from "@/api/authApi.js"

export const useLoginStore = defineStore("login", () => {
  const isLogin = ref(false);
  const loginModal = ref(false);
  const accessToken = ref("");
  const refreshToken = ref("");
  const loginUser = ref(null);
  const signUpModal = ref(false);

  function tryLogin(id, password) {
    login(id, password,
      (res) => { 
        const user = res.data.user;
        const tokenPair = res.data.token_pair;
        setAccessToken(tokenPair.token);
        setRefreshToken(tokenPair.refresh_token);
        setLoginUser(user);
        closeModal();
        setLogin();
       },
      (msg) => { 
        console.log('fail to login');
      }
    )
  }

  function accessLogin(){
    isLogin.value = true
    loginModal.value = true
  }

  function setAccessToken(_accessToken){
    accessToken.value = _accessToken;
  }

  function setRefreshToken(_refreshToken){
    refreshToken.value = _refreshToken;
  }

  function setLoginUser(updateUser){
    loginUser.value = updateUser;
  }

  function logout () {
    refreshToken.value = "";
    accessToken.value = "";
    loginUser.value = null;
    isLogin.value = false
  }

  function setLogin(){
    isLogin.value = true;
  }

  function openModal() {
    loginModal.value = true
  }

  function closeModal() {
    loginModal.value = false
  }

  function openSignupModal(){
    signUpModal.value = true;
  }

  function closeSignupModal(){
    signUpModal.value = false;
  }

  return { isLogin, loginModal, accessLogin, logout, openModal, closeModal,
     tryLogin, loginUser, accessToken, refreshToken, openSignupModal, signUpModal, closeSignupModal,
    setAccessToken, setRefreshToken, setLoginUser, setLogin};
});
