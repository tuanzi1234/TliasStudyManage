<script setup>
import {ref} from 'vue'

const tableData = ref(
    [
      {
        date: '2016-05-03',
        name: 'Tom',
        updateTime: 'No. 189, Grove St, Los Angeles',
      },
    ]
)

</script>

<template>
  <!--标题-->
  <h1>部门管理</h1>

  <div class="container">
    <el-button type="primary">添加部门</el-button>
  </div>

  <!--表格-->
  <div class="container">
    <el-table :data="tableData" stripe border style="width: 100% " >
      <el-table-column type="index" label="序号" width="100" align="center"/>
      <el-table-column prop="name" label="部门名称" width="180" align="center"/>
      <el-table-column prop="updateTime" label="最后操作时间" width="200" align="center"/>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<style scoped>
.container {
  margin: 20px auto;
}

</style>
