import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../Views/HomeView.vue";
import ProfileView from "../Views/ProfileView.vue";
import AuthView from "../Views/AuthView.vue";
import RegisterView from "../Views/RegisterView.vue";
import HomeViewiki from "../Views/HomeViewiki.vue";

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
  {
    path: "/register",
    name: "RegisterView",
    component: RegisterView,
  },
  {
    path: "/homeview",
    name: "home",
    component: HomeViewiki,

  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
