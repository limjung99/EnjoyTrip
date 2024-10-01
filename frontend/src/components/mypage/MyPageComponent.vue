<template>
    <div class="container">
        <div class="profile-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
            </svg>
        </div>
        <div class="profile-info">
            <p><strong>아이디:</strong> {{ loginUser.id }}</p>
            <p><strong>이름:</strong> {{ loginUser.name }}</p>
            <p><strong>이메일:</strong> {{ loginUser.email }}</p>
            <button @click="changePasswordModal" v-if="!pwdChangeModal">비밀번호 변경</button>
            <PasswordChangeComponent 
            @click-modal="clickModal"
            @pwd-change="changePassword"
            v-else ></PasswordChangeComponent>
        </div>
    </div>
</template>

<script setup>
import { useLoginStore } from "@/stores/login.js";
import { ref } from "vue"; 
import PasswordChangeComponent from "@/components/mypage/PasswordChangeComponent.vue";
import { changePasswordApi } from "@/api/authApi";

const pwdChangeModal = ref(false);
const loginStore = useLoginStore();
const loginUser = loginStore.loginUser;
const accessToken = loginStore.accessToken;
const refreshToken = loginStore.refreshToken;

const changePassword = (newPassword)=>{
    console.log("change start!");
    console.log("token:"+accessToken);
    const updateUser = loginStore.loginUser;
    updateUser.password = newPassword; // push back new password
    changePasswordApi(updateUser, accessToken,
    (res)=>{
        console.log(res);
        const updateUserRes = res.data;
        loginStore.loginUser = updateUserRes; 
    },(err)=>{
        console.log("failed to change");
        console.log(err);
    }
)
    pwdChangeModal.value = false;
}

const changePasswordModal = () => {
    pwdChangeModal.value = !pwdChangeModal.value;
}

const clickModal = ()=>{
    pwdChangeModal.value = !pwdChangeModal.value;
}

</script>

<style scoped>
body {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background: url('background.jpg') no-repeat center center fixed;
    background-size: cover;
}

.container {
    background-color: rgba(255, 255, 255, 0.9);
    color: black;
    border-radius: 10px;
    margin-top: 10%;
    padding: 20px;
    width: 30em;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 30em;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.profile-icon {
    margin-top: 1em;
}

.profile-info {
    text-align: left;
    margin-top: 1.5em;
    width: 100%;
}

.profile-info p {
    margin: 0.5em 0;
    font-size: 1.1em;
}

button {
    margin-top: 1.5em;
    padding: 0.7em 1.5em;
    font-size: 1em;
    color: white;
    background-color: #007bff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #0056b3;
}
</style>
