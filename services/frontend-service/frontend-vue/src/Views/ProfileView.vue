<template>
  <h1>{{this.userStore.userId}}</h1>
</template>

<script>
import {UserListDTO} from "../dtos/UserListDTO.js";
import axios from 'axios';
import {useUserStore} from "../stores/UserStore.js";
export default {
  data() {
    return {

      userStore: useUserStore(),

    };
  },
  mounted() {

  }
};
</script>
<style></style>
