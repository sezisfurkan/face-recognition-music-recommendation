<template>
  <form @submit.prevent="handleSubmit">
    <div>
      <label>Name:</label>
      <input v-model="form.firstname" type="text" required />
    </div>
    <div>
      <label>Surname:</label>
      <input v-model="form.surname" type="text" required />
    </div>
    <div>
      <label>UserName:</label>
      <input v-model="form.username" type="text" required />
    </div>
    <div>
      <label>Password :</label>
      <input v-model="form.password" type="password" required />
    </div>
    <div>
      <label>Mail Adress:</label>
      <input v-model="form.email" type="email" required />
    </div>


    <button type="submit">Register</button>
  </form>
</template>

<script>
import { reactive } from 'vue';
import {UserListDTO} from "../dtos/UserListDTO.js";
import axios from 'axios';
import {useUserStore} from "../stores/UserStore.js";


export default {
  data() {
    return {
      form: {
        firstname: '',
        surname: '',
        username: '',
        password: '',
        email: '',
      },
      userStore: useUserStore(),
      URL: 'http://localhost:8090/api/v1/user',
      userListDTO: new UserListDTO(),
    };
  },
  methods: {
    async handleSubmit() {
      const userData = {
        firstname: this.form.firstname,
        surname: this.form.surname,
        username: this.form.username,
        password: this.form.password,
        email: this.form.email,
      };

      this.userListDTO.username = userData.username;
      this.userListDTO.fname = userData.firstname;
      this.userListDTO.sname = userData.surname;
      this.userListDTO.email = userData.email;
      this.userListDTO.password = userData.password;
      this.userListDTO.name = userData.firstname + " " + userData.surname;

      try {
        const response = await axios.post(`${this.URL}`, this.userListDTO);
        console.log(response.data);
          this.userListDTO.id = response.data.id;
          this.userListDTO.name = response.data.name;

          this.userStore.userId = this.userListDTO.id;
          this.userStore.userName = this.userListDTO.username;

        console.log(this.userStore.userId)
        this.$router.push('/profile');
      } catch (error) {
        console.error(error);
      }

    },
  },

};
</script>