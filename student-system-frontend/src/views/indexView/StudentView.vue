<template>
  <div class="student-view">
    <h1 style="color: white;">学生信息查询</h1>
    <div class="table">

      <div class="row">
        <div class="col-1">
          <Header class="header" @emitData="search"/>

          <div class="table">
            <el-table :data="tableData" style="height: 100%; background-color: rgba(100, 100, 100, 0.15);" >
              <!--            <el-table-column v-for=""-->
              <el-table-column prop="index" label="序号" width="200"/>
              <el-table-column prop="studentId" label="学号" width="260" />
              <el-table-column prop="name" label="姓名" width=240 />
              <el-table-column prop="col1" label="不知道占位置" width="200" />
              <el-table-column prop="col2" label="占位置的数据" width="" />
            </el-table>
          </div>
        </div>

        <div class="col-2">
          <Sider class="sider"/>
        </div>
      </div>



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

<style>
.student-view {
  box-sizing: border-box;
  max-height: 100%;
  width: 100%;
  height: 100%;
  padding: 20px;
  .table {
    height: 100%;
  }

  .row {
    display: flex;
    height: 100%;

    .col-1 {
      flex: 7;
      display: flex;
      flex-direction: column;
      height: 100%;

      .header {
        flex: 3;
        margin-bottom: 20px;
        background-color: rgba(100, 100, 100, 0.5);
      }

      .table {
        flex: 6;

        .el-table__inner-wrapper:before {
          content: "";
          background-color: transparent;
        }
        colgroup,
        .el-table__body-wrapper,
        .table,
        .el-table,
        .hidden-columns,
        .el-table__inner-wrapper,
        .el-table__header-wrapper,
        .el-table__cell {
          background-color: transparent;
        }
        .el-table__row {
          background-color: rgba(100, 100, 100, 0.15);
          color: white;
        }
        .el-table__row:hover {
          color: lightblue;
        }
        .el-table__header tr {
          background-color: rgba(100, 100, 100, 0.4) !important;
          color: #E5EAF3;
        }

      }


    }



    .col-2 {
      height: 100%;
      flex: 3;

      .sider {
        background-color: rgba(100, 100, 100, 0.5);
        margin-left: 10px;
        height: 100%;
      }
    }
  }

}




</style>