<template>
    <Menubar :model="menuItems">
        <template #end>
            <h1 class="text-green-600 text-2xl font-normal m-0 p-0">
                Vue Js Music recommendation App
            </h1>
        </template>
    </Menubar>
</template>

<script>
import { useUserStore } from "../stores/UserStore.js";

export default {
    data() {
        return {
            userStore: useUserStore(),
        };
    },
    computed: {

        isUserLoggedIn() {
            return this.userStore.userId !== "";
        },
        menuItems() {
            const baseItems = [
                {
                    label: "Home",
                    icon: "pi pi-home",
                    to: "/",
                },
                {
                    label: this.isUserLoggedIn ? this.userStore.userName : "Profile",
                    icon: "pi pi-user",
                    to: "/profile",
                },
            ];

            const authItems = [
                {
                    label: "Auth",
                    icon: "pi pi-sign-in",
                    to: "/auth",
                },
                {
                    label: "Register",
                    icon: "pi pi-sign-in",
                    to: "/register",
                },
            ];

            return this.isUserLoggedIn ? baseItems : [...baseItems, ...authItems];
        },
    },
};
</script>

<style scoped></style>
