<template>
  <Menubar :model="menuItems">
    <template #end>
      <h1 class="text-green-600 text-2xl font-normal m-0 p-0">
        Vue Js Music recommendation App
      </h1>
<!--      <head>
        <meta charSet="UTF-8">
        <title>My YouTube Player</title>
        <script src="https://www.youtube.com/player_api"></script>
      </head>-->
    </template>

  </Menubar>
</template>

<script >
export default {
  data() {
    return {};
  },
  computed: {
    menuItems() {
      const items = [
        {
          label: "Home",
          icon: "pi pi-home",
          to: "/",
        },
        {
          label: "Video",
          icon: "pi pi-youtube",
          to: "/homeview2",
        },
        {
          label: "Profile",
          icon: "pi pi-user",
          to: "/profile",
        },
        {
          label: "Auth",
          icon: "pi pi-sign-in",
          to: "/auth",
        },
        {
          label: "Register",
          icon: "pi pi-sign-up",
          to: "/register",
        },
      ];

      return items;
    },
  },
};
</script>


<style scoped></style>
