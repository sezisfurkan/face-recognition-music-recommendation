<template>
  <div>
    <div v-if="userStore.userId">
      <h2>User Information</h2>
      <p>Name: {{ userListDTO.fname }}</p>
      <p>Surname: {{ userListDTO.sname }}</p>
      <p>Email: {{ userListDTO.email }}</p>
    </div>
    <button @click="handleEditProfile">Edit Profile</button>
    <button icon="pi-sign-out" @click="signOut">Sign out</button>
  </div>
</template>

<script>
import { useUserStore } from "../stores/UserStore.js";
import {UserListDTO} from "../dtos/UserListDTO.js";
import axios from "axios";

export default {
  data() {
    return {
      userStore: useUserStore(),
      userListDTO: new UserListDTO(),
      currentUserId:'',
      URL: 'http://localhost:8090/api/v1/user'
    };
  },
  methods: {
    mounted(){
      const localStorageData = localStorage.getItem("user");
      const parsedLocalStorageData = JSON.parse(localStorageData);
      this.currentUserId = parsedLocalStorageData.userId;//for API
      this.URL += '/' + this.currentUserId;
      try {
        const response = axios.get(`${this.URL}`);
        this.userListDTO.id = response.data.id;
        this.userListDTO.name = response.data.name;
        this.userListDTO.username = response.data.username;
        this.userListDTO.fname = response.data.fname;
        this.userListDTO.sname = response.data.sname;
        this.userListDTO.email = response.data.email;
      }
      catch (error) {
        console.error(error);
      }
    },
    signOut(){
      localStorage.clear();
      this.$router.push('/auth');
    },
    handleEditProfile() {
      this.$router.push('/profile_edit');
      console.log("Edit Profile button clicked");
    }
  }
};
</script>

<style></style>
