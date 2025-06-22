<script setup>
import {ref, onMounted} from 'vue'
import router from "@/router/index.js";
import {updateEmpById} from "@/api/emp.js"
import {getEmpById} from "@/api/emp.js"
import {ElMessage} from "element-plus";
//响应式数据
//当前登录用户数据
const loginName = ref('')
//修改密码弹窗数据
const dialogFormVisible = ref()
const formLabelWidth = ref('80px')
//修改密码弹窗数据
const resetPasswordForm = ref({
  password: '',
  newPassword: '',
  confirmPassword: ''
})
//修改密码数据
const employee = ref({
  id: '',
  password: '',
})
//表单验证规则
const rules = {
  password: [
    {required: true, message: '请输入原始密码', trigger: 'blur'},
    {min: 6, message: '密码长度不能小于6位', trigger: 'blur'}
  ],
  newPassword: [
    {required: true, message: '请输入新密码', trigger: 'blur'},
    {min: 6, message: '密码长度不能小于6位', trigger: 'blur'}
  ],
  confirmPassword: [
    {required: true, message: '请输入确认密码', trigger: 'blur'},
    {min: 6, message: '密码长度不能小于6位', trigger: 'blur'}
  ]
}
//修改密码表单验证
const resetPasswordRules = ref();
//获取当前登录员工的函数
const getCurrentEmp = () => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if (loginUser && loginUser.name) {
    loginName.value = loginUser.name;

  }
}
//钩子函数
onMounted(() => {
  getCurrentEmp();//获取当前登录员工
})

//退出登录的函数
const logout = () => {
  localStorage.removeItem('loginUser');
  router.push('/login');
}
//修改密码的函数
const showResetPassword = () => {
  dialogFormVisible.value = true;
  resetPasswordRules.value.resetFields();
}
//点击修改密码弹窗“确认”按钮触发的函数
const resetPassword = async () => {
  //表单校验
  if (!resetPasswordRules.value.validate()) return;
  resetPasswordRules.value.validate(async (valid) => {
    if (valid) {
      const loginUser = JSON.parse(localStorage.getItem('loginUser'));
      if (loginUser && loginUser.id) {
        const result = await getEmpById(loginUser.id);
        if (result.code) {
          if (result.data.password === resetPasswordForm.value.password) {
            if (resetPasswordForm.value.newPassword === resetPasswordForm.value.confirmPassword) {
              employee.value.id = loginUser.id;
              employee.value.password = resetPasswordForm.value.newPassword;
              const result = await updateEmpById(employee.value);
              if (result.code) {
                ElMessage.success('修改密码成功');
                await router.push('/login');
                localStorage.removeItem('loginUser');
              } else {
                ElMessage.error(result.msg);
              }
            } else {
              ElMessage.error('新密码与确认密码不一致');
            }
          } else {
            ElMessage.error('原始密码输入错误');
          }
        } else {
          ElMessage.error()
        }
      }
    } else {
      ElMessage.error('表单校验不通过');
    }
  })
}

</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <span class="title">Tlias智能学习辅助系统</span>
        <span class="right_tool">
          <a href="javascript:void(0)" @click="showResetPassword">
            <el-icon><EditPen/></el-icon> 修改密码 &nbsp;&nbsp;&nbsp; |  &nbsp;&nbsp;&nbsp;
          </a>
          <a href="javascript:void(0)" @click="logout">
            <el-icon><SwitchButton/></el-icon> 退出登录 <span>【{{ loginName }}】</span>
          </a>
        </span>
      </el-header>

      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px" class="aside">
          <el-menu router>
            <!--首页-->
            <el-menu-item index="/index">
              <el-icon>
                <Promotion/>
              </el-icon>
              <span>首页</span>
            </el-menu-item>

            <!--班级学员管理-->
            <el-sub-menu index="/manager">
              <template #title>
                <el-icon>
                  <Menu/>
                </el-icon>
                班级学员管理
              </template>
              <el-menu-item index="/clazz">
                <el-icon>
                  <HomeFilled/>
                </el-icon>
                班级管理
              </el-menu-item>
              <el-menu-item index="/stu">
                <el-icon>
                  <UserFilled/>
                </el-icon>
                学员管理
              </el-menu-item>
            </el-sub-menu>

            <!--系统信息管理-->
            <el-sub-menu index="/system">
              <template #title>
                <el-icon>
                  <Tools/>
                </el-icon>
                系统信息管理
              </template>
              <el-menu-item index="/dept">
                <el-icon>
                  <HelpFilled/>
                </el-icon>
                部门管理
              </el-menu-item>
              <el-menu-item index="/emp">
                <el-icon>
                  <Avatar/>
                </el-icon>
                员工管理
              </el-menu-item>
            </el-sub-menu>

            <!--数据统计管理-->
            <el-sub-menu index="/report">
              <template #title>
                <el-icon>
                  <Tools/>
                </el-icon>
                数据统计管理
              </template>
              <el-menu-item index="/empReport">
                <el-icon>
                  <InfoFilled/>
                </el-icon>
                员工信息统计
              </el-menu-item>
              <el-menu-item index="/stuReport">
                <el-icon>
                  <Share/>
                </el-icon>
                学员信息统计
              </el-menu-item>
              <el-menu-item index="/log">
                <el-icon>
                  <Operation/>
                </el-icon>
                日志信息统计
              </el-menu-item>
            </el-sub-menu>

          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>

    <!--    修改密码的弹窗-->
    <el-dialog v-model="dialogFormVisible" title="修改密码" width="500">
      <el-form :model="resetPasswordForm" :rules="rules" ref="resetPasswordRules">
        <el-form-item label="原始密码" :label-width="formLabelWidth" prop="password">
          <el-input type="password" v-model="resetPasswordForm.password" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth" prop="newPassword">
          <el-input type="password" v-model="resetPasswordForm.newPassword" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth" prop="confirmPassword">
          <el-input type="password" v-model="resetPasswordForm.confirmPassword" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click=" dialogFormVisible.value = false">取消</el-button>
          <el-button type="primary" @click="resetPassword">
            确认
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.header {
  background-image: linear-gradient(to right, #00547d, #007fa4, #00aaa0, #00d072, #a8eb12);
}

.title {
  color: white;
  font-size: 40px;
  font-family: 楷体;
  line-height: 60px;
  font-weight: bolder;
}

.right_tool {
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}

.aside {
  width: 220px;
  border-right: 1px solid #ccc;
  height: 730px;
}
</style>
