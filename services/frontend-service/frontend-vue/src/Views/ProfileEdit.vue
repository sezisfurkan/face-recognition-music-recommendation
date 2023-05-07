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
      <button type="submit">Update Profile</button>
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      form: {
        firstname: '',
        surname: '',
        username: '',
        password: '',
        email: ''
      }
    }
  },
  methods: {
    handleSubmit() {
      console.log(this.form);
    }
  }
};
</script>
