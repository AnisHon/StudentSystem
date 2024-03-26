<template>
  <div class="score-view">

    <div class="table">


      <el-row class="row" style="height: 100%;display: flex;justify-content: center;align-items: center">

        <div class="row-container" style="width: 50%; height: 85%; background-color: rgba(100, 100, 100, 0.15);padding: 20px">
            <h1 style="text-align: center;">成绩</h1>
          <div>
            <h3>用户信息</h3>
            <el-descriptions style="width: 100%;" >
              <el-descriptions-item label="学生姓名">{{ userInfo.username }}</el-descriptions-item>
              <el-descriptions-item label="学号">{{ userInfo.studentId }}</el-descriptions-item>
              <el-descriptions-item label="户籍">{{ userInfo.place }}</el-descriptions-item>
              <el-descriptions-item label="标识">
                <el-tag size="small">{{ userInfo.remark  }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="家庭住址" span="2">
                {{ userInfo.address }}
              </el-descriptions-item>
              <el-descriptions-item label="共修读">
                {{ userInfo.total }} 门课程
              </el-descriptions-item>
              <el-descriptions-item label="通过">
                {{ userInfo.pass }} 门
              </el-descriptions-item>
              <el-descriptions-item label="挂科情况">
                {{ userInfo.fail === 0 ? '未挂科' : '共挂' + userInfo.fail + '门'}}
              </el-descriptions-item>
            </el-descriptions>
          </div>
            <h3 >成绩信息</h3>
            <div class="wrapper" style="height: 100%">


                <el-table :data="tableData" style=" width: 100%; height: 100%;background-color: rgba(100, 100, 100, 0.15)" >
                  <el-table-column prop="projectName" label="科目" width="" />
                  <el-table-column prop="score" label="分数" width="" />
                </el-table>

            </div>



        </div>


      </el-row>


    </div>
  </div>


</template>

<script setup>

import {reactive} from "vue";
import { get } from "@/http/index.js"
import {useUserStore} from "@/stores/user.js";

const userStore = useUserStore()
const user = userStore.getUser
console.log(user)
const tableData = reactive([])
const userInfo = reactive({
  username: user.name,
  studentId: user.userId,
  place: "数据库中没存",
  remark: "学校",
  address: "占位置数据库中没存",
  total: 0,
  pass: 0,
  fail: 0,
})

get("/api/my-score", (data) => {

  let total = 0;
  userInfo.total = data.length;
  for (const dataItem of data) {
    total += dataItem.score / 100.0
    if (dataItem.score / 100.0 < 60) {
      userInfo.fail++
    } else {
      userInfo.pass++
    }
      tableData.push({
        projectName: dataItem.projectName,
        score: dataItem.score / 100.0,
      })
  }
  if (data.length !== 0) {
    tableData.push({
      projectName: "平均分",
      score: (total / data.length).toFixed(2)
    })
  }
})

</script>

<style>
.score-view {
  box-sizing: border-box;
  max-height: 100%;
  width: 100%;
  height: 100%;
  padding: 20px;
  .row,
  .table{
    height: 100%;
  }


  .row {
    height: 100%;
    .el-table {
      border-radius: 20px;
        .el-table__inner-wrapper {
          background-color: rgba(100, 100, 100, 0.15);
          padding: 10px;
        }
        tr,
        .el-table__header th {
          background-color: transparent;
        }
        .el-table__header th {
          color: white;
        }
        tr {
          color: #E5EAF3;
        }
        .el-table__row:hover td{
          background-color: rgba(0, 0, 0, 0.3);
          color: antiquewhite;
        }
    }
    .row-container {
      display: flex;
      flex-direction: column;
      border-radius: 30px;

      .el-table__inner-wrapper::before {
        background-color: transparent;
      }
      .el-descriptions__cell {
        .el-descriptions__label {
          color: white;
        }
        .el-descriptions__content {
          color: #E5EAF3;
        }
      }

      .el-descriptions__body {
        background-color: rgba(100, 100, 100, 0.3);
        border-radius: 20px;
        padding-left: 20px;
      }


    }
    h1,
    h3 {
      color: white;
    }
  }
}



</style>