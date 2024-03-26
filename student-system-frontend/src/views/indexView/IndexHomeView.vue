<template>
  <div class="index-container">

    <div class="header">
      <span style="line-height: 35px;color: white;">
        <span >
          <el-icon style="transform: translateY(3px)" size="20"><Tools /></el-icon>
          &nbsp;
          &nbsp;
          <el-icon style="transform: translateY(3px)" size="20"><Location /></el-icon>
        </span>
      </span>



        <el-button @click='logout' color="transparent" round>
          <template #icon>
            <el-icon> <User /> </el-icon>
          </template>
          退出登录
        </el-button>

    </div>

    <div class="content">
      <div class="row-1">
        <div class="welcome">
          <div class="portrait">
            <el-icon size="100" color="white"><Avatar /></el-icon>
          </div>

          <span>你好 {{ user.name }}</span>
        </div>
        <div class="notice">
          <h1>公告</h1>
          <div class="notice-content">
            <p>{{ "没有公告" }}</p>
          </div>
        </div>
      </div>

      <div class="items">
        <div class="item" style="color: white; padding: 20px">
          <p>
            不知道放什么
          </p>
          <p>
            不知道放什么
          </p>
          <p>
            不知道放什么
          </p>
          <p>
            不知道放什么
          </p>

        </div>
      </div>




    </div>

  </div>
</template>


<script setup>
import { User, Avatar, Tools, Location } from "@element-plus/icons-vue"
import {useUserStore} from "@/stores/user.js";
import { get } from "@/http/index.js"
import router from "@/router/index.js";
import {ElMessage} from "element-plus";
const useUser = useUserStore()
const user = useUser.getUser

const logout = () => {
  get("/api/auth/logout", (data)=> {
    useUser.setUser(null)
    router.push("/login")
    ElMessage.success(data)
  })
}

</script>


<style scoped>
.index-container {
  height: 100%;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  .header {

    display: flex;
    justify-content: space-between;
    flex: 10;
    margin: 0 50px;
    padding: 0 10px;
    height: 35px;
    background-color: rgba(255, 255, 255, 0.15);
    border-radius: 30px;

  }

  .content {
    display: flex;
    justify-items: left;
    border-radius: 50px;
    padding: 20px;
    margin: 0 20px;
    //background-color: rgba(124, 123, 142, 0.5);
    height: 100%;
    flex-wrap: wrap;


    .row-1 {
      display: flex;
      flex-direction: column;
      flex: 3;
      .welcome {
        flex: 3;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin: 10px;
        background-color: rgba(255, 255, 255, 0.15);
        height: 300px;
        border-radius: 20px;

        .portrait {
          display: flex;
          justify-content: center;
          height: 120px;
          width: 120px;
          border-radius: 999px;
          background-color: rgba(0, 0, 0, 0.1);
        }
        >span {
          margin-top: 20px;
          font-size: 26px;
          color: white;
        }


      }


      .notice {
        flex: 5;
        margin: 10px;
        background-color: rgba(255, 255, 255, 0.15);
        border-radius: 30px;

        >h1 {
          font-weight: 400;
          color: white;
          text-align: center;
        }


        .notice-content {
          padding: 0 20px;
          p {
            text-indent: 20px;
            color: white;
          }

        }

      }
    }

    .items {
      flex: 2;
      width: 40%;
      margin: 10px;
      background-color: rgba(255, 255, 255, 0.15);
      border-radius: 30px;
    }



  }


}

div {

}
</style>