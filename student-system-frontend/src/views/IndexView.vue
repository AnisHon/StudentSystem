<template>
  <div>
    <el-row class="tac">
      <el-col :span="3">

        <side-nav-component/>

      </el-col>
      <el-col :span="21">

        <h1>
          test
        </h1>


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
}
</style>