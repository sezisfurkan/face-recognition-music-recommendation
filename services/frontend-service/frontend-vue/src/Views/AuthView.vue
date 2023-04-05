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
export default {
  data() {
    return {
      username: "",
      password: "",
      URL: 'http://localhost:8090/api/v1/user/login',
    };

  },
  methods: {
    async login() {
      try {
        const response = await axios.post(`${this.URL}`, {
          username: this.username,
          password: this.password
        });
        localStorage.setItem("token", response.data.token); // cevap olarak aldığınız kimlik belirteci local storage'e kaydedin
        // Başarılı giriş durumunu burada yönetin
      } catch (error) {
        console.error(error);
        // Başarısız giriş durumunu burada yönetin
      }
    },
  },
};
</script>
