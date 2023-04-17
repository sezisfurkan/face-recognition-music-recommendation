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
          this.userListDTO.username = response.data.username;
          this.userListDTO.fname = response.data.fname;
          this.userListDTO.sname = response.data.sname;
          this.userListDTO.email = response.data.email;

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
<style>
/* Form styles */
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 2rem;
  background-color: #222;
  color: #fff;
  border-radius: 0.5rem;
  padding: 2rem;
}

/* Form label styles */
form label {
  font-weight: bold;
  margin-bottom: 0.5rem;
}

/* Form input styles */
form input[type="text"],
form input[type="password"] {
  padding: 0.5rem;
  margin-bottom: 1rem;
  border-radius: 0.25rem;
  border: none;
  width: 100%;
  box-sizing: border-box;
  background-color: #444;
  color: #fff;
}

/* Form input focus styles */
form input[type="text"]:focus,
form input[type="password"]:focus {
  outline: none;
  box-shadow: 0 0 0 2px #007bff;
}

/* Form submit button styles */
form button[type="submit"] {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 0.25rem;
  padding: 0.5rem 1rem;
  margin-top: 1rem;
  cursor: pointer;
}

/* Form submit button hover styles */
form button[type="submit"]:hover {
  background-color: #0069d9;
}
</style>
