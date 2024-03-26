<template>
  <div>
    <router-view class="center"></router-view>
  </div>
</template>




<script setup>

import {useUserStore} from "@/stores/user.js";
import {get} from "@/http/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const store = useUserStore()

if (store.getUser == null) {
  get("/api/me", (data) => {



    store.setUser(data)
    ElMessage.success("已登陆")
    router.push("/")
  }, () => {
    store.setUser(null)
    ElMessage.info("请登录")
  })

}

</script>

<style scoped>
div {
  height: 100%;
}


</style>
