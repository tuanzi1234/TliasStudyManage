<script setup>
import {ref, onMounted} from 'vue'


const searchEmp = ref({name: '', gender: '', date: ''})

</script>

<template>
  <h1>员工管理</h1>

  <!--搜索栏-->
  <div class="container" style="width: 100%;">
    <el-form :inline="true" :model="searchEmp">
      <el-form-item label="姓名">
        <el-input v-model="searchEmp.name" placeholder="请输入姓名" clearable/>
      </el-form-item>
      <el-form-item label="性别">
        <el-select
            v-model="searchEmp.gender"
            placeholder="请选择性别"
            clearable
            style="width: 200px"
        >
          <el-option label="男" value="1"/>
          <el-option label="女" value="2"/>
        </el-select>
      </el-form-item>
      <el-form-item label="入职日期">
        <el-date-picker
            v-model="searchEmp.date"
            type="date"
            placeholder="Pick a date"
            clearable
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>

</style>