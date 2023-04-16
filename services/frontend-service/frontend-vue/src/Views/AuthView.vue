<template>
  <form @submit.prevent="login">
    <label>
      Kullanıcı Adı:
      <input type="text" v-model="username">
    </label>
    <label>
      Parola:
      <input type="password" v-model="password">
    </label>
    <button type="submit">Giriş Yap</button>
  </form>
</template>

<script>
import axios from 'axios';
import {useUserStore} from "../stores/UserStore.js";
import {UserListDTO} from "../dtos/UserListDTO.js";
export default {
  data() {
    return {
      username: "",
      password: "",
      URL: 'http://localhost:8090/api/v1/user/login',
        userStore: useUserStore(),
        userListDTO: new UserListDTO(),


    };

  },
  methods: {
    async login() {

      try {
        const response = await axios.post(`${this.URL}`, {
          username: this.username,
          password: this.password
        });
        localStorage.setItem("token", response.data.token); // cevap olarak aldığınız kimlik belirteci local storage'e kaydedi
        setTimeout(() => {
          this.$router.push('/profile')
        }, 1000)

          this.userListDTO.id = response.data.id;
          this.userListDTO.name = response.data.name;

          this.userStore.userId = this.userListDTO.id;
          this.userStore.userName = this.userListDTO.username;

          console.log(this.userStore.userId)
          this.$router.push('/profile');
      } catch (error) {
        console.error(error);
        // Başarısız giriş durumunu burada yönetin
      }
    },
  },
};
</script>
