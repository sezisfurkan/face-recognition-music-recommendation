import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../Views/HomeView.vue";
import ProfileView from "../Views/ProfileView.vue";
import AuthView from "../Views/AuthView.vue";
import RegisterView from "../Views/RegisterView.vue";
import HomeViewiki from "../Views/HomeViewiki.vue";
import ProfileEdit from "../Views/ProfileEdit.vue";
import PlayList from "../Views/PlayList.vue";

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
/*  {
    path: "/homeview2",
    name: "home2",
    query:'',
    component: HomeViewiki,

  },*/
  {
    path: "/homeview2",
    name: "home2",
    params:'bos',
    component: HomeViewiki,
   /* props: (route) => ({ query: route.query }),*/
  },
  {
    path: "/profile_edit",
    name: "profile_edit",
    params:'bos',
    component: ProfileEdit,
    /* props: (route) => ({ query: route.query }),*/
  },
  {
    path: "/playlist",
    name: "playlist",
    params:'bos',
    component: PlayList,
    /* props: (route) => ({ query: route.query }),*/
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
