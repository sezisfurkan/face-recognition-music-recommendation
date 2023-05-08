<template>
  <h2>Edit Profile</h2>
  <form class="form-container" @submit.prevent="handleSubmit">
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
    <div>
      <button type="submit" :disabled="loading">
        <span v-if="loading">
          <i class="pi pi-spin pi-spinner"></i> Lütfen Beklyiniz...
        </span>
        <span v-else>Update Profile</span>
      </button>
    </div>
  </form>
</template>

<script>
import {UserListDTO} from "../dtos/UserListDTO.js";
import axios from "axios";
import {useUserStore} from "../stores/UserStore.js";

export default {
  data() {
    return {
      form: {
        firstname: '',
        surname: '',
        username: '',
        password: '',
        email: ''
      },
      userStore: useUserStore(),
      URL: 'http://localhost:8090/api/v1/user',
      userListDTO: new UserListDTO(),
      currentUserId:'',
      loading: false
    }
  },
  methods: {
     async handleSubmit() {
      this.loading = true;
      const localStorageData = localStorage.getItem("user");
      const userData = {
        firstname: this.form.firstname,
        surname: this.form.surname,
        username: this.form.username,
        password: this.form.password,
        email: this.form.email,
      };
      if (localStorageData) {
        const parsedLocalStorageData = JSON.parse(localStorageData);
        this.userListDTO.username = userData.username;
        this.userListDTO.fname = userData.firstname;
        this.userListDTO.sname = userData.surname;
        this.userListDTO.email = userData.email;
        this.userListDTO.password = userData.password;
        this.userListDTO.name = userData.firstname + " " + userData.surname;
        this.userListDTO.id = parsedLocalStorageData.userId;
        this.currentUserId = parsedLocalStorageData.userId;//for API
      }
       this.URL += '/' + this.currentUserId
      try {
        const response = await axios.put(`${this.URL}`, this.userListDTO);
        console.log(response.data);
        this.userListDTO.id = response.data.id;
        this.userListDTO.name = response.data.name;

        this.userStore.userId = this.userListDTO.id;
        this.userStore.userName = this.userListDTO.username;

        console.log(this.userStore.userId)
        this.$router.push('/profile');
        this.saveToLocalStorage(this.userStore);
      } catch (error) {
        console.error(error);
      }
       this.loading = false;

      console.log(this.form);
    }
  }
};
</script>
