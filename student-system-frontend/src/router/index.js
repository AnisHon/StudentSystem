import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/IndexView.vue'
const routes = [
  {
    path: "/",
    name: "index",
    component: IndexView
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

export default router
