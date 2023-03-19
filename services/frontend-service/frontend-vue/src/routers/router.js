import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../Views/HomeView.vue";
import ProfileView from "../Views/ProfileView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/profile",
    name: "Profile View",
    component: ProfileView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
