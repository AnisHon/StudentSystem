<template>

  <div class="inputFrom">
    <div>
    <h1>查询</h1>
    <el-input
        v-model="selectForName"
        style="max-width: 600px"
        placeholder="Please input"
        class="input-with-select"
    >
      <template #prepend>
        <el-select v-model="select" placeholder="搜索学生名" style="width: 115px">
          <el-option label="搜索学生名" value="1" />
          <el-option label="不想做了" value="2" />
          <el-option label="没实现" value="3" />
        </el-select>
      </template>
      <template #append>
        <el-button @click="click" :icon="Search" />
      </template>
    </el-input>
    </div>
  </div>

</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
const selectForName = ref('')
const select = ref('')
const inputFinished = () => {
  click();
}

const emitData = defineEmits(['emitData'])
const click = () => {
  emitData('emitData', selectForName.value)
}

</script>

<style>
.inputFrom {
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-input-group__prepend,
.el-input-group__append,
.el-input__wrapper {
  border-color: #000;
  background-color: transparent!important;
}
.inputFrom>div>h1 {
  text-align: center;
  color: white;
}
.inputFrom>div {
  width: 500px;
}

</style>
