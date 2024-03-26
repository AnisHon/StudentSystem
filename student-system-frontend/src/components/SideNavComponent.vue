<template>
  <div class="nav">
      <h5 class="header">校园业务系统</h5>
      <el-menu
          default-active="/"
          class="nav-vertical"
          background-color="Transparent"
          text-color="#FFFFFF"
          :router="true"

      >
        <el-menu-item index="/">
          <template #title>
            <el-icon><HomeFilled /></el-icon>
            <span>首页</span>
          </template>

        </el-menu-item>
        <el-sub-menu index="2" :disabled="isTeacher">
          <template #title>
            <el-icon><location /></el-icon>
            <span>学生查询</span>
          </template>

          <el-menu-item-group title="查询项目">
            <el-menu-item index="/select-result">选课查询</el-menu-item>
            <el-menu-item index="/score">成绩查询</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="选课项目">
            <el-menu-item index="/choose">选课</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="3" :disabled="!isTeacher">
          <template #title>
            <el-icon><icon-menu /></el-icon>
            <span>教育管理</span>
          </template>
          <el-menu-item index="">成绩录入</el-menu-item>
          <el-menu-item index="/student">学生查询</el-menu-item>
          <el-menu-item index="/student-score">学生成绩查询</el-menu-item>



        </el-sub-menu>
        <el-menu-item index="4" disabled>
          <el-icon><setting /></el-icon>
          <span>校园业务管理</span>
        </el-menu-item>
        <el-menu-item index="5" disabled>
          <el-icon><setting /></el-icon>
          <span>课程事务查询</span>
        </el-menu-item>
        <el-menu-item index="5" disabled>
          <el-icon><setting /></el-icon>
          <span>教室申请</span>
        </el-menu-item>
        <el-menu-item index="5" disabled>
          <el-icon><setting /></el-icon>
          <span>唱跳 Rap 篮球</span>
        </el-menu-item>
      </el-menu>
  </div>
</template>

<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
  HomeFilled
} from '@element-plus/icons-vue'
import {useUserStore} from "@/stores/user.js";
import {ref} from "vue";

const useUser = useUserStore()
const isTeacher = ref(useUser.getUser.role === "teacher");
console.log(useUser.getUser.role === "teacher")

</script>



<style scoped>

.nav {
  position: relative;
  height: 100%;
  .nav-vertical {
    background-color: rgba(0, 0, 0, 0);
    backdrop-filter: blur(5px);
    height: 100%;
    z-index: 2;
  }


}
.header {
  font-weight: 500;
  font-size: 15px;
  color: white;
}

</style>
