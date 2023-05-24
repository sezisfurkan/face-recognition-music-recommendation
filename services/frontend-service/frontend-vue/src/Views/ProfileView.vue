<template>
  <Toast/>
  <div>
    <div>
      <h2>User Information</h2>
      <p>Name: {{ userListDTO.fname }}</p>
      <p>Surname: {{ userListDTO.sname }}</p>
      <p>Email: {{ userListDTO.email }}</p>
    </div>
    <button @click="handleEditProfile">Edit Profile</button>
    <button @click="goToPlaylist">Go To Playlist</button>
    <button icon="pi-sign-out" @click="signOut">Sign out</button>
  </div>
</template>

<script>
import { useUserStore } from "../stores/UserStore.js";
import {UserListDTO} from "../dtos/UserListDTO.js";
import axios from "axios";

export default {
  mounted() {
    this.loadProfileData();
  },
  data() {
    return {
      userStore: useUserStore(),
      userListDTO: new UserListDTO(),
      currentUserId:'',
      URL: 'http://localhost:8090/api/v1/user'
    };
  },
  methods: {
    async loadProfileData(){
      const localStorageData = localStorage.getItem("user");
      const parsedLocalStorageData = JSON.parse(localStorageData);
      this.currentUserId = parsedLocalStorageData.id;//for API
      this.URL += '/' + this.currentUserId;
      try {
        const response = await axios.get(`${this.URL}`);
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
      this.$toast.add({severity: 'success', summary: 'Başarıyla çıkış yapıldı', detail: "Görüşmek üzere", life: 3000});
      setTimeout(() => {
        this.$router.push('/auth')
      }, 1000)
    },
    handleEditProfile() {
      this.$router.push('/profile_edit');
      console.log("Edit Profile button clicked");
    },
    goToPlaylist() {
      this.$router.push('/playlist');
      console.log("playlist button clicked");
    }
  }
};
</script>

<style></style>
