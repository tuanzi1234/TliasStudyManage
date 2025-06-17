<script setup>
import {ref, onMounted} from 'vue';
import {getDeptList, addDept, getDeptById, updateDeptById, deleteDeptById} from "@/api/dept.js";
import {ElMessage, ElMessageBox} from "element-plus";


//调用查询方法的函数
onMounted(() => {
  search();
})

//查询部门列表的函数
const search = async () => {
  // const result = await axios.get('http://localhost:8080/depts');
  // if (result.data.code){ //JS隐式类型转换 0为false, 其他数字为true, ’‘为false, null为false, undefined为false, NaN为false
  //   deptList.value = result.data.data;
  // }
  const result = await getDeptList();
  if (result.code) { //JS隐式类型转换 0为false, 其他数字为true, ’‘为false, null为false, undefined为false, NaN为false
    deptList.value = result.data;
  }
}

//创建一个查询所有部门列表的响应式对象
const deptList = ref([])

// dialog对话框
const dialogVisible = ref(false);
const dept = ref({});
const formTitle = ref('');

//点击添加部门按钮触发的函数
const showAddDept = () => {
  dialogVisible.value = true;//显示对话框
  formTitle.value = '添加部门';//设置对话框的标题
  dept.value = {};//清空对象
  formRef.value.resetFields();//重置表单校验
}
//点击对话框"确定"触发的函数
const save = async () => {
  //若校验规则不通过,  则返回
  if (!formRef.value) return;
  formRef.value.validate(async (valid) => {
    //校验通过
    if (valid) {
      let result;
      //判断dept.value.id是否存在, 若存在, 则为修改部门; 若不存在, 则为添加部门
      if (dept.value.id) {
        //调用修改部门的函数
        result = await updateDeptById(dept.value.id, dept.value);
      } else {
        //调用添加部门的函数
        result = await addDept(dept.value);
      }
      if (result.code) {
        //提示信息
        ElMessage.success("操作成功");
        //关闭对话框
        dialogVisible.value = false;
        //查询
        await search();
      } else {
        ElMessage.error(result.msg);
      }
    } else {
      ElMessage.error("表单校验失败");
    }
  })
}

//表单校验
const formRef = ref();
const rules = ref({
      name: [
        {required: true, message: '请输入部门名称', trigger: 'blur'},
        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
      ]
    }
);

//点击编辑部门按钮触发的函数：数据回显
const edit = async (id) => {
  const result = await getDeptById(id);
  if (result.code) {
    dept.value = result.data;
    dialogVisible.value = true;
    formTitle.value = '修改部门';
    formRef.value.resetFields();
  }
}

//点击删除按钮时触发的函数
const showDeleteDeptById = async (id) => {
  //弹出删除部门的确认弹窗
  ElMessageBox.confirm('此操作将永久删除该部门, 是否继续?', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => { //点击确定按钮时触发的函数
    const result = await deleteDeptById(id);
    if (result.code) {
      ElMessage.success('删除成功');
      const newList = await getDeptList();
      if (newList.code) {
        deptList.value = newList.data;
      }
    } else {
      ElMessage.error(result.msg);
    }
  }).catch(() => { //点击取消按钮时触发的函数
    ElMessage.info('已取消删除');
  })
}


</script>

<template>
  <!--标题-->
  <h1>部门管理</h1>

  <div class="container">
    <el-button type="primary" @click="showAddDept">添加部门</el-button>
  </div>

  <!--表格-->
  <div class="container">
    <el-table :data="deptList" stripe border style="width: 100% ">
      <el-table-column type="index" label="序号" width="200" align="center"/>
      <el-table-column prop="name" label="部门名称" width="400" align="center"/>
      <el-table-column prop="updateTime" label="最后操作时间" width="400" align="center">
        <template #default="scope">
          {{ new Date(scope.row.updateTime).toLocaleString() }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="showDeleteDeptById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!--dialog对话框实现新增/修改部门-->
  <el-dialog :title="formTitle" v-model="dialogVisible" width="500">
    <el-form ref="formRef" :rules="rules" :model="dept" label-width="80px">
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="dept.name"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="save">保存</el-button>
    </template>
  </el-dialog>

</template>

<style scoped>
.container {
  margin: 20px auto;
}

</style>
