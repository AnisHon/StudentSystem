<template>
  <div class="add-score-view">
    <h1 style="color: white;">录入成绩</h1>
    <div class="table">


      <div class="row">
        <div class="col-1">


          <div class="header">
            <h1 style="color: white; text-align: center">添加学生成绩</h1>

            <div class="add-form">
              <div class="">
                <el-form :inline="true" :model="form" class="score-input" ref="ruleFormRef" :rules="rules">
                  <el-form-item label="学号" prop="name" class="item">
                    <el-input v-model="form.name" placeholder="请输入学号" />
                  </el-form-item>
                  <el-form-item label="科目" prop="subject" class="item">
                    <el-input v-model="form.subject" placeholder="请输入科目" />
                  </el-form-item>
                  <el-form-item label="成绩" prop="score" class="item">
                    <el-input v-model="form.score" placeholder="请输入成绩" />
                  </el-form-item>

                </el-form>
              </div>
              <div class="btn">
                  <el-button type="primary" @click="onSubmit(ruleFormRef)">添加</el-button>
              </div>

            </div>

          </div>

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

import {reactive, ref} from "vue";
import {get, post} from "@/http/index.js"
import Header from "@/components/Header.vue";
import Sider from "@/components/Sider.vue";
import {ElMessage} from "element-plus";
const ruleFormRef = ref()
const rules = reactive({
  name: [{validator: validateName, trigger: 'blur'}],
  score: [{validator: validateScore, trigger: 'blur'}],
  subject: [{validator: validateSubject, trigger: 'blur'}]

})

function validateSubject(rule, value, callback) {
  if (value === '') {
    callback(new Error('请输入科目'))
  } else  {
    callback()
  }
}

function validateName(rule, value, callback) {
  if (value === '') {
    callback(new Error('请输入学号'))
  } else {
    get("/api/exist/" + value, (data) => {

      if (!data) {
        callback(new Error('该学号不存在'))
      } else {
        callback()
      }

    })


  }
}

function validateScore(rule, value, callback) {
  if (value === '') {
    callback(new Error('请输入分数'))
    return
  }
  let n = Number(value);
  if (isNaN(n)) {
    callback(new Error('请输入有效数字'))
  } else if (n < 0 || n > 100) {
    callback(new Error('分数只能在0-100之间'))
  } else {
    callback()
  }

}

function submitFunction() {
  post("/api/add-score", {
    name: form.name,
    subject: form.subject,
    score: (Number(form.score).toFixed(2) * 100)
  }, (data) => {
    ElMessage.success("添加成功")
    form.score = ''
    form.subject = ''
  })
}

const tableData = reactive([])
const onSubmit = (ruleFormRef) => {
  if (!ruleFormRef) return
  ruleFormRef.validate((valid) => {
    if (valid) {
      submitFunction()
    } else {
      ElMessage({
        message: '请正确填写表单',
        type: 'warning',
      })
      return false
    }
  })
}

const form = reactive({
  name: "",
  score: "",
  subject: ""
})
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
.add-score-view {

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

  .header {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .add-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .score-input {
      display: flex;
      justify-content: center;
    }
    .item label{
      color: white;
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