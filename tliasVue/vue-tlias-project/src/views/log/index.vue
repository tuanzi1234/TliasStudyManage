<script setup>
import {ref, onMounted} from "vue";
import {getLog} from "@/api/report.js";
import {ElMessage} from "element-plus";

//响应式数据
// 日志列表的响应式数据
const logList = ref([]);
//分页数据
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref();
const background = ref(true);
const handleSizeChange = (val) => {
  getLogList(val);
};
const handleCurrentChange = (val) => {
  getLogList(val);
};

//查询日志
const getLogList =  async () => {
  const result = await getLog(
      currentPage.value,
      pageSize.value,
  );
  if (result.code){
    logList.value = result.data.rows;
    total.value = result.data.total;
  }else {
    ElMessage.error(result.message);
  }
};

//钩子函数
onMounted(() => {
  getLogList();
});
</script>

<template>
  <h1>日志管理</h1>
  <!--  日志表格-->
  <!--  表格-->
  <div class="container">
    <el-table :data="logList" stripe border style="width: 100% ">
      <el-table-column prop="operateEmpName" label="操作人" width="90" align="center"/>
      <el-table-column prop="operateTime" label="操作时间" width="150" align="center">
        <template #default="scope">
          {{new Date(scope.row.operateTime).toLocaleString()}}
        </template>
      </el-table-column>
      <el-table-column prop="className" label="类名" align="center"/>
      <el-table-column prop="methodName" label="方法名" width="100" align="center"/>
      <el-table-column prop="costTime" label="操作耗时(ms)" width="120" align="center"/>
      <el-table-column prop="methodParams" label="请求参数" show-overflow-tooltip align="center"/>
      <el-table-column prop="returnValue" label="返回值" width="270" align="center"/>
    </el-table>
  </div>
  <!--  分页条-->
  <div class="container">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5,10,20,30,40,50]"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

</template>

<style scoped>
.container {
  margin-top: 20px;
}
</style>