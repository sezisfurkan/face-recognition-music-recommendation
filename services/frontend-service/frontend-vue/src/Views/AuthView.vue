<template>
    <div >
        <div class="surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden">
            <div class="flex flex-column align-items-center justify-content-center">
                <img src="/demo/images/logo/logo-dark.svg"  class="mb-5 w-6rem flex-shrink-0" />
                <div style="border-radius: 56px; padding: 0.3rem; background: linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)">
                    <div class="w-full surface-card py-8 px-5 sm:px-8" style="border-radius: 53px">
                        <div class="text-center mb-5">
                            <img src="/demo/images/login/avatar.png" alt="Image" height="50" class="mb-3" />
                            <div class="text-900 text-3xl font-medium mb-3">Welcome!</div>
                            <span class="text-600 font-medium">Sign in to continue</span>
                        </div>

                        <div>
                            <label  class="block text-900 text-xl font-medium mb-2">Username</label>
                            <InputText id="username" v-model="username" type="text" placeholder="UserName" class="w-full md:w-30rem mb-5" style="padding: 1rem" />

                            <label  class="block text-900 font-medium text-xl mb-2">Password</label>
                            <Password id="password1" v-model="password" placeholder="Password" :toggleMask="true" class="w-full mb-3" inputClass="w-full" :inputStyle="{ padding: '1rem' }"></Password>


                            <Button label="Sign In" class="w-full p-3 text-xl" @click="login"></Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
            loading: false,
        };

    },
    methods: {
        async login() {
            this.loading = true;

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
            this.loading = false;
        },
    },
};
</script>



