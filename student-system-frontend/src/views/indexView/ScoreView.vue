<template>
  <div class="student-view">

    <div class="table">


      <el-row class="row" style="height: 100%;display: flex;justify-content: center;align-items: center">

        <div style="width: 50%; height: 85%; background-color: white;padding: 20px">
            <h1 style="text-align: center;">成绩</h1>
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

          <h3 >成绩信息</h3>
            <el-table :data="tableData" stripe style=" width: 100%" >
              <el-table-column prop="projectName" label="科目" width="" />
              <el-table-column prop="score" label="分数" width="" />
            </el-table>


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

<style scoped>
.student-view {
  box-sizing: border-box;
  max-height: 100%;
  width: 100%;
  height: 100%;
  padding: 20px;
.row,
.table{
  height: 100%;
}

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