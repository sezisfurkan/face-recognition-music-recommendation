<template>
  <div>
    <h1>{{ userStore.userName }}</h1>
    <button @click="handleEditProfile">Edit Profile</button>
  </div>
</template>

<script>
import { useUserStore } from "../stores/UserStore.js";

export default {
  data() {
    return {
      userStore: useUserStore(),
    };
  },
  methods: {
    handleEditProfile() {
      this.$router.push('/profile_edit');
      console.log("Edit Profile button clicked");
    }
  }
};
</script>

<style></style>
