<script setup>
import { ref } from "vue";
import { useLoginStore } from "@/stores/login";

const emit = defineEmits(['clickModal','pwdChange']);
const loginStore = useLoginStore();


const modalClicked = ()=>{
    emit('clickModal');
}

const newPassword = ref("");

const stopPropagation = (event) => {
    event.stopPropagation();
}

const changePassword = ()=>{
    console.log("emit change : "+ newPassword.value);
    emit('pwdChange',newPassword.value);
}

</script>

<template>
    <div @click="modalClicked" class="wrapper">
        <div class="row g-4 align-items-center">
            <div class="col-auto">
                <label for="inputPassword6" class="col-form-label">변경할 비밀번호</label>
            </div>
            <div class="col-auto">
                <input @click="stopPropagation" v-model="newPassword" type="password" id="inputPassword6" class="form-control" aria-describedby="passwordHelpInline">
                <button class="btn btn-primary" @click="changePassword">변경</button>
            </div>
            <div class="col-auto">
                <span id="passwordHelpInline" class="form-text">
                    새롭게 바꿀 비밀번호를 입력해주세요
                </span>
            </div>
        </div>
    </div>
</template>

<style scoped>
.wrapper {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 8px;
}

#inputPassword6 {
    width: 100%;
}

.form-group {
    margin-bottom: 15px;
}

#passwordHelpInline {
    font-size: 14px;
}
</style>