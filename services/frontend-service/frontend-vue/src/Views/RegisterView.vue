<!--
<template>
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


    <button type="submit">Register</button>
  </form>
</template>

-->
<template>
  <Toast/>
  <div>
    <div class="surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden">
      <div class="flex flex-column align-items-center justify-content-center">
        <img src="/demo/images/logo/logo-dark.svg"  class="mb-5 w-6rem flex-shrink-0" />
        <div style="border-radius: 56px; padding: 0.3rem; background: linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)">
          <div class="w-full surface-card py-8 px-5 sm:px-8" style="border-radius: 53px">
            <div class="text-center mb-5">
              <img  src="/demo/images/login/avatar.png" alt="Image" height="50" class="mb-3" style="border-radius: 50%;" />
              <div class="text-900 text-3xl font-medium mb-3">Welcome!</div>
              <span class="text-600 font-medium">Sign in to continue</span>
            </div>

              <label class="block text-900 text-xl font-medium mb-2">Name</label>
              <InputText id="firstname" v-model="form.firstname" type="text" placeholder="First Name" class="w-full md:w-30rem mb-5" style="padding: 1rem" required />

              <label class="block text-900 text-xl font-medium mb-2">Surname</label>
              <InputText id="surname" v-model="form.surname" type="text" placeholder="Surname" class="w-full md:w-30rem mb-5" style="padding: 1rem" required />

              <label class="block text-900 text-xl font-medium mb-2">Username</label>
              <InputText id="username" v-model="form.username" type="text" placeholder="Username" class="w-full md:w-30rem mb-5" style="padding: 1rem" required />

              <label class="block text-900 text-xl font-medium mb-2">Password</label>
              <Password id="password1" v-model="form.password" placeholder="Password" :toggleMask="true" :feedback="false" class="w-full md:w-30rem mb-5" inputClass="w-full" :inputStyle="{ padding: '1rem' }" required />

              <label class="block text-900 text-xl font-medium mb-2">Mail Address</label>
              <InputText id="email" v-model="form.email" type="email" placeholder="Email" class="w-full md:w-30rem mb-5" style="padding: 1rem" required />

              <Button label="Register" class="w-full p-3 text-xl" @click="handleSubmit()"></Button>
          </div>
        </div>
      </div>
    </div>
  </div>
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
      /*saveToLocalStorage(userStore) {
          const userData = {
              userId: userStore.userId,
              userName: userStore.userName,
          };
          localStorage.setItem("user", JSON.stringify(userData));
      },*/
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
        localStorage.setItem("user", JSON.stringify(response.data));
          this.userListDTO.id = response.data.id;
          this.userListDTO.name = response.data.name;

          this.userStore.userId = this.userListDTO.id;
          this.userStore.userName = this.userListDTO.username;

        console.log(this.userStore.userId)
        this.$toast.add({severity: 'success', summary: 'Register Successful', detail: "Logged In", life: 3000});
        setTimeout(() => {
          this.$router.push('/profile')
        }, 1000)
          /*this.saveToLocalStorage(this.userStore);*/
      } catch (error) {
        this.$toast.add({severity: 'error', summary: 'Register Failed', detail: "password is not enough", life: 3000});
        console.error(error);
      }

    },
  },

};
</script>
