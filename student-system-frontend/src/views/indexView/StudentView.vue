<template>
  <div class="student-view">
    <h1 style="color: white;">学生信息查询</h1>
    <div class="table">


      <el-row class="row">

        <el-col :span="16">

          <Header class="header" @emitData="search"/>


          <el-table :data="tableData" stripe style="height: 500px;" >
<!--            <el-table-column v-for=""-->
            <el-table-column prop="index" label="序号" width="200"/>
            <el-table-column prop="studentId" label="学号" width="260" />
            <el-table-column prop="name" label="姓名" width=240 />
            <el-table-column prop="col1" label="不知道占位置" width="200" />
            <el-table-column prop="col2" label="占位置的数据" width="200" />
          </el-table>
        </el-col>
        <el-col :span="8">
          <Sider class="sider"/>
        </el-col>
      </el-row>


    </div>
  </div>


</template>

<script setup>

import {reactive} from "vue";
import { get } from "@/http/index.js"
import Header from "@/components/Header.vue";
import Sider from "@/components/Sider.vue";
const tableData = reactive([])

get("/api/students/", (data) => {
  let i = 1
  for (const dataItem of data) {
    tableData.push({
      index: i++,
      studentId: dataItem.accountId,
      name: dataItem.name,
      col1: Math.random(),
      col2: Math.random(),
    })
  }
})

// const emitSearch = defineEmits("search")

function search(data) {
  tableData.length = 0
  get("/api/students/" + data, (data) => {
    let i = 1
    for (const dataItem of data) {
      tableData.push({
        index: i++,
        studentId: dataItem.accountId,
        name: dataItem.name,
        col1: Math.random(),
        col2: Math.random(),
      })
    }
  })
}

</script>

<style scoped>
.student-view {
  box-sizing: border-box;
  max-height: 100%;
  width: 100%;
  height: 100%;
  padding: 20px;


}


.header {

  height: 220px;
  margin-bottom: 20px;
  background-color: rgba(100, 100, 100, 0.5);
}

.sider {
  background-color: rgba(100, 100, 100, 0.5);
  margin-left: 10px;
  width: 97%;
  //margin: 0 20px;
}
.row {
  height: 100%;

}
</style>