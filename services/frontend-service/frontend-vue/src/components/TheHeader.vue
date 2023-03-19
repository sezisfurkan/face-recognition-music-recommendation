<template>
  <div class="the-header">
    <h1>Vue Js Vocabulary Flip App</h1>
    <div class="router">
      <router-link to="/">
        <Button>Home</Button>
      </router-link>

      <router-link to="/profile">
        <Button>profile</Button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.the-header {
  background-color: rgb(57, 142, 233);
  padding: 1px;
  text-align: center;
  color: white;
  border-bottom: 1px solid rgb(57, 142, 233);
  margin-bottom: 10px;
  display: flex;
}
.router {
  display: flex;
  padding-top: 25px;
  margin-left: auto;
}
h1 {
  display: inline;
  justify-content: center;
}
</style>
