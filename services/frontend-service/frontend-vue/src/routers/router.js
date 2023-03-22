import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../Views/HomeView.vue";
import ProfileView from "../Views/ProfileView.vue";
import AuthView from "../Views/AuthView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/profile",
    name: "ProfileView",
    component: ProfileView,
  },
  {
    path: "/auth",
    name: "AuthView",
    component: AuthView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
