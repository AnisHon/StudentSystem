<template>
  <div class="container">
    <div class="login-form">
      <login-component class="login-item"/>
    </div>
  </div>

</template>

<script setup>

import LoginComponent from "@/components/LoginComponent.vue";


</script>
<style scoped>
.login-form {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 500px;

}

.login-form:after {
  content: "";
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: rgba(255, 253, 134, 0.24);
  filter: blur(5px);
}

.login-item {
  width: 300px;
  z-index: 1;
}

.container {
  display: flex;
  align-items: center;
  justify-content: center;

  width: 100%;
  height: 100%;

  background: url("@/assets/login/background.png") center no-repeat;
  background-size: cover;
}




</style>