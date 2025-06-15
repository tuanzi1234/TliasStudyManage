<script setup>
import {ref, onMounted, watch} from 'vue'
import {getEmployeeByPage, addEmpData, getEmpById, updateEmpById, deleteEmpById} from '@/api/emp.js'
import {getDeptList} from '@/api/dept.js'
import {ElMessage, ElMessageBox} from "element-plus";


//元数据
//动态展示职位的响应式数据
const jobList = ref([
  {name: '班主任', value: '1'},
  {name: '讲师', value: '2'},
  {name: '学工主管', value: '3'},
  {name: '教研主管', value: '4'},
  {name: '咨询师', value: '5'},
])
//动态展示性别的响应式数据
const genderList = ref([
  {name: '男', value: '1'},
  {name: '女', value: '2'}
])
//部门列表的响应式数据
const deptList = ref([])
//查询条件的响应式对象
const searchEmp = ref({name: '', gender: '', date: [], begin: '', end: ''})

//点击”查询“按钮触发的函数
const search = async () => {
  const Result = await getEmployeeByPage(
      searchEmp.value.name,
      searchEmp.value.gender,
      searchEmp.value.begin,
      searchEmp.value.end,
      currentPage.value,
      pageSize.value)
  if (Result.code) {
    empList.value = Result.data.rows;
    total.value = Result.data.total;
  }
}

//点击”清空“按钮触发的函数
const clear = () => {
  searchEmp.value = {name: '', gender: '', date: [], begin: '', end: ''};
  search();
}

//侦听data属性变化
watch(() => searchEmp.value.date, (newVal) => {
  if (newVal.length === 2) {
    searchEmp.value.begin = newVal[0];
    searchEmp.value.end = newVal[1];
  } else {
    searchEmp.value.begin = '';
    searchEmp.value.end = '';
  }
})

//员工列表的响应式数据
const empList = ref([{}])
//分页数据
const currentPage = ref(1) //当前页码
const pageSize = ref(10) //每页显示的条数
const background = ref(true) //是否显示背景颜色
const total = ref() //总条数

//当每页展示的记录数变化时触发的函数
const handleSizeChange = (val) => {
  search(val)
}
//当页码变化时触发的函数
const handleCurrentChange = (val) => {
  search(val)
}

//页面加载完毕时查询员工/点击部门选择时查询所有部门/上传头像时获取token时触发的钩子函数
onMounted(() => {
  search();//页面加载完毕时查询员工
  showDeptList();//点击部门选择时查询所有部门
  getToken();//上传头像时获取token
})

//新增/修改表单
const employee = ref({
  username: '',
  name: '',
  gender: '',
  phone: '',
  job: '',
  salary: '',
  deptId: '',
  entryDate: '',
  image: '',
  exprList: []
})

// 控制弹窗
const dialogVisible = ref(false)
const dialogTitle = ref('新增员工')

//文件上传
// 图片上传成功后触发
const handleAvatarSuccess = (response) => {
  employee.value.image = response.data
}
// 文件上传之前触发
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('只支持上传图片')
    return false
  } else if (rawFile.size / 1024 / 1024 > 10) {
    ElMessage.error('只能上传10M以内图片')
    return false
  }
  return true
}

//点击“新增员工”按钮触发的函数
const addEmp = () => {
  dialogVisible.value = true;
  dialogTitle.value = '新增员工';
  employee.value = {
    username: '',
    name: '',
    gender: '',
    phone: '',
    job: '',
    salary: '',
    deptId: '',
    entryDate: '',
    image: '',
    exprList: []
  }
  if (empFormRef.value) {
    empFormRef.value.resetFields()
  }
}

//点击部门选择时查询所有部门的函数
const showDeptList = async () => {
  const result = await getDeptList();
  if (result.code) {
    deptList.value = result.data;
  }
}

//点击“添加工作经历”按钮触发的函数
const addEmpWork = () => {
  employee.value.exprList.push({
    company: '',
    job: '',
    begin: '',
    end: '',
    exprDate: []
  })
}
//点击“删除工作经历”按钮触发的函数
const deleteEmpWork = (index) => {
  employee.value.exprList.splice(index, 1)
}
//将exprData中的第一个元素赋值给begin，第二个元素赋值给end的函数
watch(() => employee.value.exprList, (newVal, oldVal) => {
  if (employee.value.exprList && employee.value.exprList.length > 0) {
    employee.value.exprList.forEach(item => {
      item.begin = item.exprDate[0]
      item.end = item.exprDate[1]
    })
  }
}, {deep: true})
//点击添加员工的“保存”按钮触发的函数
const save = async () => {
  //表单校验
  if (!empFormRef.value) return;
  empFormRef.value.validate(async (valid) => {
    if (valid) {
      let result;
      if (employee.value.id) {
        //修改员工信息
        result = await updateEmpById(employee.value);
      } else {
        result = await addEmpData(employee.value);
      }
      if (result.code) {
        ElMessage.success('添加成功');
        dialogVisible.value = false;
        await search();
      } else {
        ElMessage.error(result.msg);
      }
    } else {
      ElMessage.error('表单校验不通过');
    }
  })

}

//表单校验
const rules = ref({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
  ],
  name: [
    {required: true, message: '请输入姓名', trigger: 'blur'},
    {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
  ],
  gender: [
    {required: true, message: '请选择性别', trigger: 'blur'}
  ],
  phone: [
    {required: true, message: '请输入手机号', trigger: 'blur'},
    {pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur'}
  ],
})
//表单校验的响应式对象
const empFormRef = ref();

//点击员工列表“编辑”按钮触发的函数
const edit = async (id) => {
  const result = await getEmpById(id);
  if (result.code) {
    dialogVisible.value = true;
    dialogTitle.value = '修改员工';
    employee.value = result.data;
  }
  //对工作经历进行处理
  let exprList = employee.value.exprList;
  if (exprList && exprList.length > 0) {
    exprList.forEach(expr => {
      expr.exprDate = [expr.begin, expr.end];
    });
  }
}

//点击员工表格中“删除”按钮触发的函数
const showDeleteEmpById = (id) => {
  ElMessageBox.confirm('此操作将永久删除该员工, 是否继续?', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then( async () => {
    const result = await deleteEmpById(id);
    if (result.code) {
      ElMessage.success('删除成功');
      await search();
    }else {
      ElMessage.error(result.msg);
    }
  }).catch(() => {
    ElMessage.info('已取消删除');
  })
}

//记录复选框勾选的员工的id的响应式数据
const checkedIds = ref([]);
//复选框变化时触发的函数:val为当前选中的复选框的数组
const handleSelectionChange = (val) => {
  /*val.forEach(emp => {
     checkedIds.value.push(emp.id); //不合适，会产生重复数据
  })*/
  checkedIds.value = val.map(emp => emp.id);
}
//点击“批量删除”按钮触发的函数
const deleteBatchesByIds = () => {
  ElMessageBox.confirm('此操作将永久删除该员工, 是否继续?', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => { //点击确定按钮时触发的函数
    if (checkedIds.value && checkedIds.value.length > 0){
      deleteEmpById(checkedIds.value).then(() => {
        ElMessage.success('删除成功');
        search();
      })
    }else {
      ElMessage.info('未选择任何员工');
    }
  }).catch(() => { //点击取消按钮时触发的函数
    ElMessage.info('已取消删除');
  })
}

//添加获取token的响应式变量
const token = ref('');
//为上传文件获取token
const getToken = () => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.token){
    token.value = loginUser.token;
  }
}


</script>

<template>

  <h1>员工管理</h1>
  <!--搜索栏-->
  <div class="container" style="width: 100%; margin-bottom: 0">
    <el-form :inline="true" :model="searchEmp">
      <el-form-item label="姓名">
        <el-input v-model="searchEmp.name" placeholder="请输入姓名" style="width: 200px" clearable/>
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
        <div class="block">
          <el-date-picker
              v-model="searchEmp.date"
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
  <!--  新增/批量删除按钮-->
  <div class="container" style="margin-top: 0">
    <el-button type="primary" @click="addEmp">新增员工</el-button>
    <el-button type="danger" @click="deleteBatchesByIds">批量删除</el-button>
  </div>
  <!--  员工列表-->
  <div class="container">
    <el-table
        border
        ref="multipleTableRef"
        :data="empList"
        row-key="id"
        style="width: 100%"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection"  width="55" align="center"/>
      <el-table-column prop="name" label="姓名" width="120" align="center"/>
      <el-table-column label="性别" width="80" align="center">
        <template #default="scope">
          {{ scope.row.gender === 1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column label="头像" width="120" align="center">
        <template #default="scope">
          <img :src="scope.row.image" style=" height: 50px" alt=""/>
        </template>
      </el-table-column>
      <el-table-column prop="deptName" label="部门" width="150" align="center"/>
      <el-table-column prop="job" label="职位" width="100" align="center">
        <template #default="scope">
          <span v-if="scope.row.job === 1">班主任</span>
          <span v-else-if="scope.row.job === 2">讲师</span>
          <span v-else-if="scope.row.job === 3">学工主管</span>
          <span v-else-if="scope.row.job === 4">教研主任</span>
          <span v-else>咨询师</span>
        </template>
      </el-table-column>
      <el-table-column prop="entryDate" label="入职日期" width="150" align="center"/>
      <el-table-column label="最后更新" align="center">
        <template #default="scope">
          {{ new Date(scope.row.updateTime).toLocaleString() }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="showDeleteEmpById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!--    分页条-->
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
  <!--新增/修改员工对话框-->
  <!-- 新增/修改员工的对话框 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <el-form :model="employee" :rules="rules" ref="empFormRef" label-width="80px">
      <!-- 基本信息 -->
      <!-- 第一行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="employee.username" placeholder="请输入员工用户名，2-20个字"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="employee.name" placeholder="请输入员工姓名，2-10个字"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="employee.gender" placeholder="请选择性别" style="width: 100%;">
              <el-option
                  v-for="genders in genderList"
                  :key="genders.value"
                  :label="genders.name"
                  :value="genders.value"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="employee.phone" placeholder="请输入员工手机号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="职位">
            <el-select v-model="employee.job" placeholder="请选择职位" style="width: 100%;">
              <el-option
                  v-for="jobs in jobList"
                  :key="jobs.value"
                  :label="jobs.name"
                  :value="jobs.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="薪资">
            <el-input v-model="employee.salary" placeholder="请输入员工薪资"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第四行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属部门">
            <el-select v-model="employee.deptId" placeholder="请选择部门" style="width: 100%;">
              <el-option v-for="dept in deptList"
                         :key="dept.id"
                         :label="dept.name"
                         :value="dept.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="入职日期">
            <el-date-picker v-model="employee.entryDate" type="date" style="width: 100%;" placeholder="选择日期"
                            format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第五行 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="头像">
            <el-upload
                class="avatar-uploader"
                action="/api/upload"
                :headers="{'token': token}"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
            >
              <img v-if="employee.image" :src="employee.image" class="avatar"/>
              <el-icon v-else class="avatar-uploader-icon">
                <Plus/>
              </el-icon>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>


      <!-- 工作经历 -->
      <!-- 第六行 -->
      <el-row :gutter="10">
        <el-col :span="24">
          <el-form-item label="工作经历">
            <el-button type="success" size="small" @click="addEmpWork">+ 添加工作经历</el-button>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第七行 ...  工作经历 -->
      <el-row :gutter="3" v-for="(expr, index) in employee.exprList" :key="expr.id">
        <el-col :span="10">
          <el-form-item size="small" label="时间" label-width="80px">
            <el-date-picker type="daterange" range-separator="至" v-model="expr.exprDate" start-placeholder="开始日期"
                            end-placeholder="结束日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item size="small" label="公司" label-width="60px">
            <el-input placeholder="请输入公司名称" v-model="expr.company"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item size="small" label="职位" label-width="60px">
            <el-input placeholder="请输入职位" v-model="expr.job"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="2">
          <el-form-item size="small" label-width="0px">
            <el-button type="danger" @click="deleteEmpWork(index)">- 删除</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </span>
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

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 78px;
  height: 78px;
  text-align: center;
  border-radius: 10px;
  /* 添加灰色的虚线边框 */
  border: 1px dashed var(--el-border-color);
}
</style>