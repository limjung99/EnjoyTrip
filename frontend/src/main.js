
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import { useKakao } from "vue3-kakao-maps/@utils";

import "bootstrap/dist/js/bootstrap.bundle.min.js";
import { PerfectScrollbarPlugin } from 'vue3-perfect-scrollbar';
import 'vue3-perfect-scrollbar/style.css';

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

const { VITE_KAKAO_API_KEY } = import.meta.env;
useKakao(VITE_KAKAO_API_KEY);

const app = createApp(App);
const pinia = createPinia();
app.use(pinia);
app.use(router);
app.use(PerfectScrollbarPlugin, {componentName:"Scrollbar"});
app.use(VueSweetalert2);
app.mount('#app');
