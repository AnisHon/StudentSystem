<template>
  <div>
    <h1 class="login-title">请登录</h1>
    <el-form
        ref="ruleFormRef"
        style="max-width: 300px"
        :model="form"
        status-icon
        :rules="rules"
    >
      <el-form-item prop="username" >
        <el-input
            class="input"
            placeholder="教工Id或学生Id"
            v-model="form.username"
            type="text"
            autocomplete="off"
        >
        <template #prefix>
          <el-icon><User /></el-icon>
        </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            class="input"
            placeholder="密码"
            v-model="form.password"
            type="password"
            autocomplete="off"
        >
          <template #prefix>
            <el-icon><Lock /></el-icon>
          </template>
        </el-input>

      </el-form-item>
      <el-form-item prop="password">
        <el-checkbox class="input" v-model="form.remember" label="记住我" />
      </el-form-item>


      <el-form-item >
        <el-button
            class="submit-button"
            color="#FFFA92"
            type="primary"
            @click="submitForm(ruleFormRef)"
            round
        >
          登陆
        </el-button>

      </el-form-item>
    </el-form>
    <p class="text-below">
      忘记密码请联系学生办
    </p>
  </div>

</template>

<script setup>
import { reactive, ref } from 'vue'
import {ElMessage} from "element-plus";
import {Lock, User} from "@element-plus/icons-vue"
import {get, post} from "@/http/index.js"
import router from "@/router/index.js";
import {useUserStore} from "@/stores/user.js";

const ruleFormRef = ref()
const userStore = useUserStore()


const validatePass = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入密码'))
  } else if (value.length < 8 || value.length > 16) {
    callback(new Error('密码必须是8-16位'))
  } else {
    callback()
  }

}
const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('用户名不能为空'))
  } else {
    callback()
  }
}

const form = reactive({
  username: '',
  password: '',
  remember: false,
})

const rules = reactive({
  password: [{ validator: validatePass, trigger: 'blur' }],
  username: [{ validator: validateUsername, trigger: 'blur' }]
})

const submitFunction = () => {
  post(
      "/api/auth/login",
      form,
      (data) => {
        ElMessage.success(data.message)
        router.push("/")
        console.log(data.data)

        userStore.setUser(data.data)
      }
  )

}
const submitForm = (formEl) => {
  if (!formEl) return
  formEl.validate((valid) => {
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


</script>



<style>

.submit-button {
  margin: auto;
  width: 250px;
  background-color: rgba(255, 253, 134, 0.5);
  border: None;
  color: plum;
}
.submit-button:hover {
  color: purple;
}

.login-title {
  font-weight: 400;
  text-align: center;
  color: plum;
}
.input {
  .el-input__wrapper {
    background-color: rgba(0, 0, 0, 0);
    border-radius: 99px;
  }


  .el-checkbox__inner{
    background: rgba(0, 0, 0, 0)!important;/* 透明 */
    /* background:red 其他颜色 */
  }
}

.text-below {
  color: wheat;
  font-size: 12px;
  text-align: center;
  margin-top: 30px;
}


</style>
