<template>
  <div class="student-view">
    <h1 style="color: white;">学生成绩查询</h1>
    <div class="table">


      <el-row class="row">

        <el-col :span="16">
          <Header @emitData="search" class="header"/>
          <el-table :data="tableData" stripe style="height: 500px;" >
            <el-table-column prop="studentId" label="学号" width="160" />
            <el-table-column prop="name" label="姓名" width="160" />
            <el-table-column prop="score" label="分数" width="800" />
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
const search = (data) => {
  get("/api/scores/" + data, (data) => {
    tableData.length = 0
    for (const dataItem of data) {
      let temp = ''
      for (const item of dataItem.scores) {
        temp += (item.projectName + ":" + item.score/100.0 + ';')
      }
      tableData.push({
        studentId: dataItem.accountId,
        name: dataItem.name,
        score: temp,
      })
    }
  })
}
get("/api/scores/", (data) => {
  console.log(data)
  for (const dataItem of data) {
    let temp = ''
    for (const item of dataItem.scores) {
      temp += (item.projectName + ":" + item.score/100.0 + ';')
    }

    tableData.push({
      studentId: dataItem.accountId,
      name: dataItem.name,
      score: temp,
    })
  }
})

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