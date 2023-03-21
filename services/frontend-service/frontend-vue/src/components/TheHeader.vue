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
          label: "Profie",
          icon: "pi pi-user",
          to: "/Profile",
        },
      ];

      return items;
    },
  },
};
</script>

<style scoped></style>
