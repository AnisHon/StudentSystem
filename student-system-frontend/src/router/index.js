import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/IndexView.vue'
import IndexHomeView from "@/views/indexView/IndexHomeView.vue";
import {useUserStore} from "@/stores/user.js";
const routes = [
  {
    path: "/",
    name: "index",
    component: IndexView,
    redirect: "/home",
    children: [
      {
        path: "home",
        name: "home",
        component: IndexHomeView
      },
      {
        path: "select-result",
        name: "subjectView",
        component: () => import("@/views/indexView/SubjectView.vue")
      },
      {
        path: "choose",
        name: "SubjectChoose",
        component: () => import("@/views/indexView/SubjectChooseView.vue")
      },
      {
        path: "score",
        name: "score",
        component: () => import("@/views/indexView/ScoreView.vue")
      },
      {
        path: "student",
        name: "StudentView",
        component: () => import("@/views/indexView/StudentView.vue")
      },
      {
        path: "student-score",
        name: "StudentScore",
        component: () => import("@/views/indexView/StudentScoreView.vue")
      },
      {
        path:"add-score",
        name: "AddScore",
        component: () => import("@/views/indexView/AddScore.vue")
      }
    ]
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/LoginView.vue")
  }


]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const store = useUserStore()

  console.log(store)
  console.log(to)
  if (store.getUser == null && to.name !== 'login') {
    next("/login")


  } else if (store.getUser != null && to.name.endsWith("login")) {
    next("/")
  } else if(to.matched.length === 0) {
    next("/")
  } else {
    next()
  }
})
export default router
