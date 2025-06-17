<script setup>
import {getClazzByPage, addClazz, getClazzById, updateClazzById, deleteClazzById} from "@/api/clazz.js"
import {ref, watch, onMounted} from "vue";
import {getAllEmp} from "@/api/emp.js";
import {ElMessage, ElMessageBox} from "element-plus";

//响应式数据
//新增/修改班级的响应式数据
const clazz = ref(
    {
      name: '',
      room: '',
      beginDate: '',
      endDate: '',
      masterId: '',
      creatTime: '',
      updateTime: '',
      masterName: '',
      status: '',
      subject: '',
    }
);
//查询班级的响应式数据
const searchClazz = ref(
    {
      name: '',
      beginDate: '',
      endDate: '',
      date: [],
    }
);
//查询所有员工的响应式数据
const empList = ref({
  id: '',
  username: '',
  password: '',
  name: '',
  gender: '',
  image: '',
  job: '',
  salary: '',
  entryDate: '',
  deptId: '',
  createTime: '',
  updateTime: ''
});
//学科的响应式数据, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式
const subject = ref([
  {
    value: '1',
    label: 'java'
  },
  {
    value: '2',
    label: '前端'
  },
  {
    value: '3',
    label: '大数据'
  },
  {
    value: '4',
    label: 'Python'
  },
  {
    value: '5',
    label: 'Go'
  },
  {
    value: '6',
    label: '嵌入式'
  }
]);
//班级列表的响应式数据
const clazzList = ref([]);
//分页的响应式数据
const currentPage = ref(1);
const pageSize = ref(10);
const background = ref(false);
const total = ref();
const handleSizeChange = (val) => {
  search(val);
};
const handleCurrentChange = (val) => {
  search(val);
};
//对话框的响应式数据
const dialogFormVisible = ref(false);
const formLabelWidth = ref('80px');
const dialogTitle = ref();
//校验规则
// 表单验证规则
const rules = ref({
  name: [
    { required: true, message: '班级名称不能为空', trigger: 'blur' },
    { min: 2, max: 30, message: '班级名称长度不能小于2个字符或大于30个字符', trigger: 'blur'}
  ],
  room: [
    { required: true, message: '请输入教室名称', trigger: 'blur' },
    { validator: (rule, value, callback) => {
        if (value && (value.length < 2 || value.length > 20)) {
          callback(new Error('教室名称长度不能小于2个字符或大于20个字符'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ],
  beginDate: [
    { required: true, message: '请选择开课时间', trigger: 'change' }
  ],
  endDate: [
    { required: true, message: '请选择结课时间', trigger: 'change' },
    {
      validator: (rule, value, callback) => {
        if (clazz.value.beginDate && value) {
          if (value< clazz.value.beginDate) {
            callback(new Error('结课时间不能早于开课时间'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      },
      trigger: 'change'
    }
  ],
  subject: [
    { required: true, message: '请选择学科', trigger: 'change' }
  ],
});
// 表单引用
const formRef = ref();

//点击事件
//点击"查询"触发的函数
const search = async () => {
  const Result = await getClazzByPage(
      searchClazz.value.name,
      searchClazz.value.beginDate,
      searchClazz.value.endDate,
      currentPage.value,
      pageSize.value
  )
  if (Result.code) {
    clazzList.value = Result.data.rows;
    total.value = Result.data.total;
  }
}
//监听搜索栏的输入的date，第一个赋值给beginDate，第二个赋值给endDate
watch(() => searchClazz.value.date, (newValue) => {
  if (newValue.length === 2) {
    searchClazz.value.beginDate = newValue[0];
    searchClazz.value.endDate = newValue[1];
  } else {
    searchClazz.value.beginDate = '';
    searchClazz.value.endDate = '';
  }
}, {deep: true})
//清空的函数
const clear = () => {
  searchClazz.value = {
    name: '',
    beginDate: '',
    endDate: '',
    date: [],
  }
  search();
}
//点击"新增"按钮触发的函数
const showAddClazz = () => {
  dialogFormVisible.value = true;
  dialogTitle.value = '添加班级'
  clazz.value = {
    name: '',
    room: '',
    beginDate: '',
    endDate: '',
    masterId: '',
    creatTime: '',
    updateTime: '',
    masterName: '',
    status: '',
    subject: '',
  }
  if (formRef.value){
    formRef.value.resetFields();
  }
}
//选择班主任时查询所有员工
const showDeptList = async () => {
  const Result = await getAllEmp()
  if (Result.code) {
    empList.value = Result.data;
  }
}
//点击弹窗的“确定”按钮触发的函数
const addClazzData = async () => {
  //表单校验
  if( !formRef.value ) return;
  formRef.value.validate(async (valid) => {
    if ( valid ){
      //如果有id值，则修改，没有则添加
      let Result;
      if (clazz.value.id){
        Result = await updateClazzById(clazz.value)
      }else {
        Result = await addClazz(clazz.value)
      }
      if (Result.code) {
        ElMessage.success('编辑成功')
        dialogFormVisible.value = false;
        await search();
      } else {
        ElMessage.error(Result.msg)
      }
    }else {
      ElMessage.error('表单校验不通过')
    }
  })
}
//点击班级列表中"编辑"按钮的函数
const editClazz = async (id) => {
  dialogFormVisible.value = true;
  const Result = await getClazzById(id);
  if (Result.code) {
    clazz.value = Result.data;
    dialogTitle.value = '编辑班级信息';
  }
  if (formRef.value){
    formRef.value.resetFields();
  }
}
//点击班级列表中"删除"按钮的函数
const deleteClazz = async (id) => {
  ElMessageBox.confirm('此操作将永久删除该班级, 是否继续?', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then( async () => { //点击确定按钮时触发的函数
    const Result = await deleteClazzById(id);
    if (Result.code) {
      ElMessage.success('删除成功')
      await search();
    } else {
      ElMessage.error(Result.msg)
    }
  }).catch(() => { //点击取消按钮时触发的函数
    ElMessage.info('已取消删除');
  })
}

//钩子函数
onMounted(() => {
  search();
  showDeptList();
})

</script>

<template>
  <h1>班级管理</h1>
  <!--搜索栏-->
  <div class="container" style="width: 100%; margin-bottom: 0">
    <el-form :inline="true" :model="searchClazz">
      <el-form-item label="姓名">
        <el-input v-model="searchClazz.name" placeholder="请输入姓名" style="width: 200px" clearable/>
      </el-form-item>
      <el-form-item label="结课时间">
        <div class="block">
          <el-date-picker
              v-model="searchClazz.date"
              type="datetimerange"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              range-separator="到"
              value-format="YYYY-MM-DD"
          />
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!--  新增按钮-->
  <div class="container" style="margin-top: 0">
    <el-button type="primary" @click="showAddClazz">新增班级</el-button>
  </div>
  <!--  表格-->
  <div class="container">
    <el-table :data="clazzList" stripe border style="width: 100% ">
      <el-table-column type="index" label="序号" width="100" align="center"/>
      <el-table-column prop="name" label="班级名称" width="200" align="center"/>
      <el-table-column prop="room" label="班级教室" width="100" align="center"/>
      <el-table-column prop="masterName" label="班主任" width="100" align="center"/>
      <el-table-column prop="beginDate" label="开课时间" width="200" align="center"/>
      <el-table-column prop="endDate" label="结课时间" width="200" align="center"/>
      <el-table-column prop="status" label="状态" width="100" align="center"/>
      <el-table-column prop="updateTime" label="最后操作时间" width="200" align="center">
        <template #default="scope">
          {{ new Date(scope.row.updateTime).toLocaleString() }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="editClazz(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteClazz(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
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
  <!--  新增/修改班级的对话框-->
  <el-dialog v-model="dialogFormVisible" :title="dialogTitle" width="500">
    <el-form :model="clazz" :rules="rules" ref="formRef" label-width="120px">
      <el-form-item label="班级名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="clazz.name" autocomplete="off" placeholder="请输入班级"/>
      </el-form-item>
      <el-form-item label="班级教室" :label-width="formLabelWidth" prop="room">
        <el-input v-model="clazz.room" autocomplete="off" placeholder="请输入班级"/>
      </el-form-item>
      <el-form-item label="开课时间" :label-width="formLabelWidth" prop="beginDate">
        <el-date-picker
            v-model="clazz.beginDate"
            type="date"
            placeholder="选择日期"
            format="YYYY-MM-DD" value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="结课时间" :label-width="formLabelWidth" prop="endDate">
        <el-date-picker
            v-model="clazz.endDate"
            type="date"
            placeholder="选择日期"
            format="YYYY-MM-DD" value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="班主任" :label-width="formLabelWidth">
        <el-select v-model="clazz.masterId" placeholder="请选择">
          <el-option
              v-for="item in empList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学科" :label-width="formLabelWidth" prop="subject">
        <el-select v-model="clazz.subject" placeholder="请选择">
          <el-option
              v-for="item in subject"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addClazzData">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 20px auto;
}

.avatar {
  height: 40px;
}

.avatar-uploader .avatar {
  width: 78px;
  height: 78px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

</style>