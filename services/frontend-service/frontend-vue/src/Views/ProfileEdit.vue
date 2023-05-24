
<template>
  <Toast/>
  <h2 class="edit-profile-title">Edit Profile</h2>
  <form class="form-container" @submit.prevent="handleSubmit">
    <div class="form-group">
      <label class="form-label">Name:</label>
      <input v-model="form.firstname" type="text" required class="form-input" />
    </div>
    <div class="form-group">
      <label class="form-label">Surname:</label>
      <input v-model="form.surname" type="text" required class="form-input" />
    </div>
    <div class="form-group">
      <label class="form-label">UserName:</label>
      <input v-model="form.username" type="text" required class="form-input" />
    </div>
    <div class="form-group">
      <label class="form-label">Password :</label>
      <input v-model="form.password" type="password" required class="form-input" />
    </div>
    <div class="form-group">
      <label class="form-label">Mail Adress:</label>
      <input v-model="form.email" type="email" required class="form-input" />
    </div>
    <div class="form-group">
      <button icon="pi-save" type="submit" :disabled="loading" class="form-submit-button">
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

        this.saveToLocalStorage(this.userStore);
        this.$toast.add({severity: 'success', summary: 'Güncelleme Başarılı', detail: "Kullanıcı bilgileri güncellendi", life: 3000});
        setTimeout(() => {
          this.$router.push('/profile')
        }, 1000)
      } catch (error) {
        this.$toast.add({severity: 'error', summary: 'Güncelleme Başarısız', detail: "Kullanıcı bilgileri güncellenemedi", life: 3000});
        console.error(error);
      }
       this.loading = false;

      console.log(this.form);
    }
  }
};
</script>

<style>
.edit-profile-title {
  text-align: center;
  margin-top: 20px;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-label {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
}

.form-input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 300px;
}

.form-submit-button {
  padding: 10px 20px;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  background-color: #2196f3;
  color: white;
  border: none;
}
</style>