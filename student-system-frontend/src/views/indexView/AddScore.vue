<template>
  <div class="student-score-view">
    <h1 style="color: white;">学生成绩查询</h1>
    <div class="table">


      <div class="row">
        <div class="col-1">

          <Header @emitData="search" class="header"/>
          <div class="table">
            <el-table :data="tableData" style="height: 100%;background-color: rgba(100, 100, 100, 0.15)" >
              <el-table-column prop="studentId" label="学号" width="160" />
              <el-table-column prop="name" label="姓名" width="160" />
              <el-table-column prop="score" label="分数" width="" />
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

<style>
.student-score-view {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  padding: 20px;
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
          background-color: rgba(100, 100, 100, 0.2);
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



.table {
  height: 100%;
}

/*
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
 */
</style>