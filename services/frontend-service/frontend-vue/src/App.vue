<template>
  <div>
    <the-header></the-header>

    <router-view />
  </div>
</template>

<script>
import TheHeader from "./components/TheHeader.vue";
export default {
  components: { TheHeader },
  data() {
    return {};
  },
};
</script>

<style></style>
