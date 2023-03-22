<template>
  <div class="login-container p-d-flex p-jc-center p-ai-center p-flex-column">
    <h2 class="p-text-center">User Login</h2>
    <div class="login-form p-d-flex p-flex-column">
      <div class="p-field mb-2">
        <label for="username" class="p-d-block font-bold">username: </label>
        <InputText
          id="username"
          v-model="username"
          placeholder="Kullanıcı adınızı girin"
        />
      </div>
      <div class="p-field">
        <label for="password" class="p-d-block font-bold pr-1">Password:</label>
        <Password
          id="password"
          v-model="password"
          placeholder="Şifrenizi girin"
        />
      </div>
      <div class="pt-3 pl-2">
        <Button label="Login" @click="submitForm"></Button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    submitForm() {
      console.log(`Kullanıcı Adı: ${this.username}, Şifre: ${this.password}`);
    },
  },
};
</script>

<style scoped>
.login-container {
  height: 100vh;
  width: 100%;
}

.login-form {
  max-width: 400px;
  padding: 2rem;
  border-radius: 1rem;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
}
</style>
