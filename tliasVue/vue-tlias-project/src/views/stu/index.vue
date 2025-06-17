<script setup>
import {getStuByPage, addStu, getStuById, updateStuById, deleteStuById, updateScore} from "@/api/stu.js"
import {ref, onMounted} from "vue";
import {getAllClazz} from "@/api/clazz.js";
import {ElMessage, ElMessageBox} from "element-plus";

//响应式数据
//学生表格的响应式数据
const stuList = ref([]);
//表格复选框的点击事件
const handleSelectionChange = (val) => {
  stuIds.value = val.map(item => item.id);
};
//查询学生的响应式数据
const searchStu = ref({
  name: "",
  degree: "",
  clazzId: "",
});
//分页条组件的响应式数据
const currentPage = ref(1);
const pageSize = ref(5);
const total = ref(10);
const background = ref(true);
const handleSizeChange = (val) => {
  search(val);
}
const handleCurrentChange = (val) => {
  search(val);
};
//学历数据
const degreeList = ref([
  {
    value: 1,
    label: "初中",
  },
  {
    value: 2,
    label: "高中",
  },
  {
    value: 3,
    label: "大专",
  },
  {
    value: 4,
    label: "本科",
  },
  {
    value: 5,
    label: "硕士",
  },
  {
    value: 6,
    label: "博士",
  },
])
//性别的响应式数据
const genderList = ref([
  {
    value: 1,
    label: "男",
  },
  {
    value: 2,
    label: "女",
  },
]);
//关于”是否为院校学生“的响应式数据
const isCollegeList = ref([
  {
    value: 1,
    label: "是",
  },
  {
    value: 0,
    label: "否",
  },
]);
//查询班级的响应式数据
const clazzList = ref(
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
//新增/修改学生的响应式数据
const students = ref({
  name: '',
  no: '',
  gender: '',
  phone: '',
  idCard: '',
  isCollege: '',
  address: '',
  degree: '',
  graduationDate: '',
  clazzId: '',
  violationScore: '',
})
//对话框的响应式数据
const dialogVisible = ref(false);
const dialogTitle = ref();
const dialogVisible2 = ref( false);
//表单验证的响应式数据
const rules = ref({
  name: [
    {required: true, message: "请输入姓名", trigger: "blur",},
    {min: 2, max: 10, message: "长度在 2 到 10 个字符", trigger: "blur",}
  ],
  no: [
    {required: true, message: "请输入学号", trigger: "blur",},
    {min: 10, max: 10, message: "长度必须为10个字符", trigger: "blur",}
  ],
  gender: [
    {required: true, message: "请选择性别", trigger: "blur",}
  ],
  phone: [
    {required: true, message: "请输入手机号", trigger: "blur",},
    {pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur'}
  ],
  idCard: [
    {required: true, message: "请输入身份证号", trigger: "blur",},
    {
      pattern: /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
      message: '请输入正确的身份证号',
      trigger: 'blur'
    }
  ],
  isCollege: [
    {required: true, message: "请选择是否是院校学员", trigger: "blur",}
  ],
  clazzId: [
    {required: true, message: "请选择所属班级", trigger: "blur",}
  ],
  violationScore: [
    {required: true, message: "请输入违纪分数", trigger: "blur",},
    {type: 'number', min: 0, max: 10, message: '分数范围0-10', trigger: 'blur'}
  ],
})
const stuFormRef = ref();
//记录复选框的id变化的函数
const stuIds = ref([]);
//记录当前学生id
const stuId = ref(null);

//按钮的点击事件
//点击搜索栏“查询”按钮的的函数
const search = async () => {
  const result = await getStuByPage(
      searchStu.value.name,
      searchStu.value.degree,
      searchStu.value.clazzId,
      currentPage.value,
      pageSize.value
  );
  if (result.code) {
    stuList.value = result.data.rows;
    total.value = result.data.total;
  }
};
//点击所属班级选择框时查询所有班级的函数
const showDeptList = async () => {
  const result = await getAllClazz();
  if (result.code) {
    clazzList.value = result.data;
  }
};
//点击“清空”按钮时触发的函数
const clear = () => {
  searchStu.value = {
    name: "",
    degree: "",
    clazzId: "",
  };
  search();
};
//点击"新增"按钮的函数
const showAddStu = () => {
  dialogVisible.value = true;
  dialogTitle.value = "新增学员";
  students.value = {};
  stuFormRef.value.resetFields();
};
//点击“编辑”按钮的函数
const edit = async (id) => {
  const result = await getStuById(id);
  if (result.code) {
    students.value = result.data;
  }
  dialogVisible.value = true;
  dialogTitle.value = "编辑学员";
  stuFormRef.value.resetFields();
};
//点击对话框中”保存“按钮的函数
const save = () => {
  //表单验证
  if (!stuFormRef.value) return;
  stuFormRef.value.validate(async (valid) => {
    if (valid) {
      let result;
      //如果有id值，则修改，没有则添加
      if (students.value.id) {
        //修改学员
        result = await updateStuById(students.value);
      }else {
        //添加学员
        result = await addStu(students.value);
      }
      if (result.code) {
        ElMessage.success('编辑成功');
        dialogVisible.value = false;
        await search();
      } else {
        ElMessage.error(result.msg);
      }
    }else{
      ElMessage.error('表单校验不通过')
    }
  });
};
//点击学员表格中”删除“按钮的函数
const showDeleteStuById = async (id) => {
  ElMessageBox.confirm('此操作将永久删除该学员, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    const result = await deleteStuById(id);
    if (result.code) {
      ElMessage.success('删除成功');
      await search();
    } else {
      ElMessage.error(result.msg);
    }
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
};
//点击”批量删除“的函数
const deleteBatchesByIds = async () => {
  ElMessageBox.confirm('此操作将永久删除该学员, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    if (stuIds.value && stuIds.value.length > 0){
      const result = await deleteStuById(stuIds.value);
      if (result.code) {
        ElMessage.success('删除成功');
        await search();
      } else {
        ElMessage.error(result.msg);
      }
    }else {
      ElMessage.error('请选择要删除的学员');
    }
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
};
//点击”违纪“按钮触发的函数
const editScore = async (id) => {
  dialogVisible2.value = true;
  dialogTitle.value = "违纪处理";
  stuId.value = id; // 存储当前学生ID
  students.value.violationScore = ''; // 重置分数
};
//点击违纪弹窗”保存“按钮触发的函数
const saveScore = async () => {
  const score = students.value.violationScore;
  //表单校验
  if (!stuFormRef.value) return;
  stuFormRef.value.validate(async (valid) => {
    if (valid){
      // 调用接口
      const result = await updateScore(stuId.value, score);
      if (result.code) {
        ElMessage.success('更新成功');
        dialogVisible2.value = false;
        await search(); // 刷新列表
      } else {
        ElMessage.error(result.msg);
      }
    }else {
      ElMessage.error('表单校验不通过');
    }
  });
};

//钩子函数
onMounted(() => {
  search();
  showDeptList();
});

</script>

<template>
  <h1>学员管理</h1>
  <!--  搜索栏-->
  <div class="container" style="width: 100%; margin-bottom: 0">
    <el-form :inline="true" :model="searchStu">
      <el-form-item label="姓名">
        <el-input v-model="searchStu.name" placeholder="请输入姓名" style="width: 200px" clearable/>
      </el-form-item>
      <el-form-item label="最高学历">
        <el-select
            v-model="searchStu.degree"
            placeholder="请选择"
            clearable
            style="width: 200px"
        >
          <el-option
              v-for="item in degreeList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select
            v-model="searchStu.clazzId"
            placeholder="请选择"
            clearable
            style="width: 200px"
        >
          <el-option
              v-for="item in clazzList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!--  新增/批量删除按钮-->
  <div class="container" style="margin-top: 0">
    <el-button type="primary" @click="showAddStu">新增学员</el-button>
    <el-button type="danger" @click="deleteBatchesByIds">批量删除</el-button>
  </div>
  <!--  学生列表-->
  <div class="container">
    <el-table
        border
        ref="multipleTableRef"
        :data="stuList"
        row-key="id"
        style="width: 100%"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column prop="name" label="姓名" width="120" align="center"/>
      <el-table-column prop="no" label="学号" width="120" align="center"/>
      <el-table-column prop="clazzName" label="班级" width="200" align="center"/>
      <el-table-column label="性别" width="80" align="center">
        <template #default="scope">
          {{ scope.row.gender === 1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="120" align="center"/>
      <el-table-column prop="degree" label="最高学历" width="100" align="center">
        <template #default="scope">
          <span v-if="scope.row.degree === 1">初中</span>
          <span v-else-if="scope.row.degree === 2">高中</span>
          <span v-else-if="scope.row.degree === 3">大专</span>
          <span v-else-if="scope.row.degree === 4">本科</span>
          <span v-else-if="scope.row.degree === 5">硕士</span>
          <span v-else>博士</span>
        </template>
      </el-table-column>
      <el-table-column prop="violationCount" label="违纪次数" width="100" align="center"/>
      <el-table-column prop="violationScore" label="违纪扣分" width="100" align="center"/>
      <el-table-column label="最后更新" align="center">
        <template #default="scope">
          {{ new Date(scope.row.updateTime).toLocaleString() }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
          <el-button type="warning" size="small" @click="editScore(scope.row.id)">违纪</el-button>
          <el-button type="danger" size="small" @click="showDeleteStuById(scope.row.id)">删除</el-button>
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

  <!-- 新增/修改学生的对话框 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <el-form :model="students" :rules="rules" ref="stuFormRef" label-width="125px">
      <!-- 基本信息 -->
      <!-- 第一行 -->
      <!--      姓名-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="students.name" placeholder="请输入学生姓名，2-10个字"></el-input>
          </el-form-item>
        </el-col>
        <!--        学号-->
        <el-col :span="12">
          <el-form-item label="学号" prop="no">
            <el-input v-model="students.no" placeholder="请输入学号，固定10个字"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <!--      性别-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="students.gender" placeholder="请选择性别" style="width: 100%;">
              <el-option
                  v-for="genders in genderList"
                  :key="genders.value"
                  :label="genders.label"
                  :value="genders.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <!--        手机号-->
        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="students.phone" placeholder="请输入学生手机号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <!--      身份证号-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="身份证号" prop="idCard">
            <el-input v-model="students.idCard" placeholder="请输入学生身份证号"></el-input>
          </el-form-item>
        </el-col>
        <!--        院校成员-->
        <el-col :span="12">
          <el-form-item label="是否是院校成员" prop="isCollege">
            <el-select v-model="students.isCollege" placeholder="请选择职位" style="width: 100%;">
              <el-option
                  v-for="college in isCollegeList"
                  :key="college.value"
                  :label="college.label"
                  :value="college.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第四行 -->
      <!--      联系地址-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="联系地址">
            <el-input v-model="students.address" placeholder="请输入联系地址"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最高学历">
            <el-select v-model="students.degree" placeholder="请选择学历" style="width: 100%;">
              <el-option
                  v-for="degrees in degreeList"
                  :key="degrees.value"
                  :label="degrees.label"
                  :value="degrees.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第五行 -->
      <!--      毕业时间-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="毕业时间">
            <el-date-picker v-model="students.graduationDate" type="date" style="width: 100%;" placeholder="选择日期"
                            format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
          </el-form-item>
        </el-col>
        <!--        班级-->
        <el-col :span="12">
          <el-form-item label="班级" prop="clazzId">
            <el-select v-model="students.clazzId" placeholder="请选择班级" style="width: 100%;">
              <el-option
                  v-for="clazz in clazzList"
                  :key="clazz.id"
                  :label="clazz.name"
                  :value="clazz.id"
              />
            </el-select>
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
<!--  违纪对话框-->
  <el-dialog v-model="dialogVisible2" :title="dialogTitle">
    <el-form :model="students" :rules="rules" ref="stuFormRef" label-width="125px">
          <el-form-item label="违纪扣分" prop="violationScore" >
            <el-input v-model.number="students.violationScore" placeholder="请输入分数（正数）"></el-input>
          </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveScore">保存</el-button>
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

</style>