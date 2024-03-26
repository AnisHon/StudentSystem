<template>
  <div class="container">
    <el-row class="tac">
      <el-col :span="3" class="nav-col">

        <side-nav-component class="nav"/>

      </el-col>
      <el-col :span="21">
        <keep-alive>
          <transition name="el-fade-in" >
            <router-view></router-view>
          </transition>

        </keep-alive>

      </el-col>
    </el-row>


  </div>
</template>

<script setup>
import SideNavComponent from "@/components/SideNavComponent.vue";
</script>

<style scoped>
.tac {
  height: 90%;
  backdrop-filter: blur(3px);
}

div.container {
  position: relative;
  height: 100%;
  width: 100%;
  background: url("@/assets/index/background.jpeg");

  background-size: cover;

}

.el-transition {
  transition-duration: 3s;
}


</style>