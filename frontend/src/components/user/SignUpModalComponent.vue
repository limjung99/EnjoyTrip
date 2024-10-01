<script setup>
import { ref } from 'vue'
import { onClickOutside } from '@vueuse/core'
import { useLoginStore } from "@/stores/login";
import { signUpApi } from '@/api/authApi';
import Swal from "sweetalert2";

const id = ref("");
const password = ref("");
const passwordCheck= ref("");
const name = ref("");
const email = ref("");

const store = useLoginStore()
const target = ref(null)

onClickOutside(target, event => {
  store.closeSignupModal();
});

const handleDuplicatedId = ()=>{
      Swal.fire({
      title: "중복된 ID입니다.",
      text: "이미 사용중인 ID입니다. 다른 ID를 선택해주세요",
      icon: "wrong"});
}

const emailUnvalidErr = ()=>{
  Swal.fire("이메일에는 '@'가 포함되어야 합니다.");
}

const handleWrongPwdConfirmErr = ()=>{
  Swal.fire("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
}

const handleShortIdErr = ()=>{
  Swal.fire("ID는 6자 이상 20자 이하여야 합니다.")
}

const validCheck = ()=>{
    if(password.value != passwordCheck.value){
      handleWrongPwdConfirmErr();
      return false;
    }

    if(id.value.length < 6 || id.value.length > 20){
      handleShortIdErr();
      return false;
    }

    if(!email.value.includes("@")){
      emailUnvalidErr();
      return false;
    }

    return true;
}

const submitSignup = ()=>{
  if(!validCheck()){
    return;
  }

    const newUser = {
        "id" : id.value,
        "password" : password.value,
        "name" : name.value,
        "email" : email.value
    }

    signUpApi(newUser,(res)=>{
        store.closeSignupModal();
        // if signup successful then login
        console.log(res.data);
        const tokenPair = res.data;
        const accessToken = tokenPair.token;
        const refreshToken = tokenPair.refresh_token;

        const loginUser = {
            "name" : name.value,
            "id" : id.value,
            "email" : email.value
        }

        // store.setLoginUser(loginUser);
        // store.setAccessToken(accessToken);
        // store.setRefreshToken(refreshToken);
        // store.setLogin();
    },(err)=>{
      console.log(err);
      handleDuplicatedId();
      store.closeSignupModal();
    })
}
</script>

<template>
  <div class=" container modal-overlay" style="position:inherit; margin-top: 5%">
    <div class="row">
      <div ref="target" class="card card-signup" data-background-color="orange">
        <form class="form" @submit.prevent="submitSignup">
          <div class="card-header text-center">
            <h3 class="card-title title-up">Signup</h3>
          </div>
          <div class="card-body">
            <div class="input-group no-border">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="now-ui-icons users_circle-08"></i>
                </span>
              </div>
              <!-- id part -->
              <input v-model="id" type="text" class="form-control" placeholder="ID">
            </div>
            <div class="input-group no-border">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="now-ui-icons text_caps-small"></i>
                </span>
              </div>
              <input v-model="password" type="password" placeholder="Password" class="form-control" />
            </div>
            <div class="input-group no-border">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
  <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z"/>
</svg>
                </span>
              </div>
              <input v-model="passwordCheck" type="password" placeholder="Password Check" class="form-control" />
            </div>
            <div class="input-group no-border">
              <div class="input-group-prepend">
                <span class="input-group-text">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
  <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
</svg>

                </span>
              </div>
              <input v-model="email" type="email" placeholder="Email" class="form-control" />
            </div>
            <div class="input-group no-border">
              <div class="input-group-prepend">
                <span class="input-group-text">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-alexa" viewBox="0 0 16 16">
  <path d="M7.996 0A8 8 0 0 0 0 8a8 8 0 0 0 6.93 7.93v-1.613a1.06 1.06 0 0 0-.717-1.008A5.6 5.6 0 0 1 2.4 7.865 5.58 5.58 0 0 1 8.054 2.4a5.6 5.6 0 0 1 5.535 5.81l-.002.046-.012.192-.005.061a5 5 0 0 1-.033.284l-.01.068c-.685 4.516-6.564 7.054-6.596 7.068A7.998 7.998 0 0 0 15.992 8 8 8 0 0 0 7.996.001Z"/>
</svg>
                </span>
              </div>
              <input v-model="name" type="text" placeholder="Name" class="form-control" />
            </div>
          </div>
          <div class="card-footer text-center">
            <button class="btn btn-neutral btn-round btn-lg">Submit</button>
          </div>
        </form>
    </div>
  </div>
  </div>
</template>

<style scoped>
.modal-overlay{
  z-index: 10;
}

</style>