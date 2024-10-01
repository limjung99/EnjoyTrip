import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView2.vue'
import TestView from '@/views/TestView.vue'
import PostView from '@/views/PostView.vue'
import TripView from '@/views/TripView.vue'
import MyPageView from '@/views/MyPageView.vue'
import { useLoginStore } from '@/stores/login'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/test",
      name: "test",
      component: TestView,
    },
    {
      path: "/trip",
      name: "trip",
      component: TripView,
      beforeEnter: (to, from, next) => {
        const loginStore = useLoginStore();
        if (!loginStore.isLogin) {
          console.log("hook!");
          next({ name: "main" });
        }
        next();
      },
    },
    {
      path: "/posts",
      name : "posts",
      redirect : {name : "post-list", query : {page : 1, size : 10}},
      children : [
        {
          path : "list",
          name : "post-list",
          component : ()=>import("@/components/post/PostListComponent.vue")
        },
        {
          path : "view/:postno",
          name : "post-view",
          component : ()=>import("@/components/post/PostDetailComponent.vue")
        },
        {
          path : "write",
          name : "post-write",
          component : ()=>import("@/components/post/PostWriteComponent.vue")
        },
        {
          path : "modify/:postno",
          name : "post-modify",
          component : ()=>import("@/components/post/PostModifyComponent.vue")
        }
      ]
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MyPageView,
      beforeEnter: (to, from, next) => {
        const loginStore = useLoginStore();
        if (!loginStore.isLogin) {
          console.log("hook!");
          next({ name: "main" });
        }
        next();
      },
    },
    {
      path: "/plan",
      name: "plan",
      component: () => import("@/views/PlanView.vue"),
      beforeEnter: (to, from, next) => {
        const loginStore = useLoginStore();
        if (!loginStore.isLogin) {
          console.log("hook!");
          next({ name: "main" });
        }
        next();
      },
    },
  ],
});

export default router
