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


export default {
  setup() {
    let userData = {};
    const URL = 'http://localhost:8080/api/v1';
    this.userListDTO = new UserListDTO();
    const form = reactive({
      firstname: '',
      surname: '',
      username: '',
      password:'',
      email: '',
    });

    function handleSubmit() {
        userData = {
        firstname: form.fname,
        surname: form.surname,
        username: form.username,
        password: form.password,
        email: form.email,
      };

      // userData DTO olarak gönderilebilir
      console.log(userData);
      saveUser(userData);
    }
    async function saveUser(userData){
      this.userListDTO.username = userData.username;
      this.userListDTO.fname = userData.firstname;
      this.userListDTO.sname = userData.surname;
      this.userListDTO.email = userData.email;
      this.userListDTO.password = userData.password;

      try {
        const response = await axios.post(URL + '/user', this.userListDTO);
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }

    }

    return { form, handleSubmit };
  },
};
</script>