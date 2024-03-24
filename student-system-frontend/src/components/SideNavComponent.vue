<template>
  <div class="nav">
      <h5 class="mb-2">校园业务系统</h5>
      <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-menu-item index="1">
          <template #title>
            <el-icon><HomeFilled /></el-icon>
            <span>首页</span>
          </template>

        </el-menu-item>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><location /></el-icon>
            <span>学生查询</span>
          </template>

          <el-menu-item-group title="查询项目">
            <el-menu-item index="2-1">选课查询</el-menu-item>
            <el-menu-item index="2-2">成绩查询</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="选课项目">
            <el-menu-item index="2-3">选课</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>
            <el-icon><icon-menu /></el-icon>
            <span>教育管理</span>
          </template>
          <el-menu-item index="3-1">学生查询</el-menu-item>
          <el-menu-item index="3-2">学生成绩查询</el-menu-item>


        </el-sub-menu>
        <el-menu-item index="4" disabled>
          <el-icon><setting /></el-icon>
          <span>业务管理</span>
        </el-menu-item>
        <el-menu-item index="5" disabled>
          <el-icon><setting /></el-icon>
          <span>事务查询</span>
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
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}
</script>

<style scoped>
.nav {
  height: 100%;
}
.nav {
  .el-menu-vertical-demo {
    height: 100%;
  }
}
</style>
