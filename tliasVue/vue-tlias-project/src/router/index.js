import {createRouter, createWebHistory} from 'vue-router'
import IndexView from '@/views/index/index.vue'
import EmpView from '@/views/emp/index.vue'
import DeptView from '@/views/dept/index.vue'
import LoginView from '@/views/login/index.vue'
import ClazzView from '@/views/clazz/index.vue'
import StuView from '@/views/stu/index.vue'
import LayoutView from '@/views/layout/index.vue'
import LogView from '@/views/log/index.vue'
import EmpReportView from '@/views/report/emp/index.vue'
import StuReportView from '@/views/report/stu/index.vue'


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: '/login', name: 'login', component: LoginView},
        {
            path: '/', name: 'layout', component: LayoutView, redirect: '/index',
            children: [
                {path: '/index', name: 'index', component: IndexView},
                {path: '/stu', name: 'stu', component: StuView},
                {path: '/emp', name: 'emp', component: EmpView},
                {path: '/dept', name: 'dept', component: DeptView},
                {path: '/log', name: 'log', component: LogView},
                {path: '/empReport', name: 'empReport', component: EmpReportView},
                {path: '/stuReport', name: 'stuReport', component: StuReportView},
                {path: '/clazz', name: 'clazz', component: ClazzView},
            ]
        },
        //  添加默认重定向
        { path: '/', redirect: '/login' }, // 根路径重定向到登录
        { path: '/:pathMatch(.*)*', redirect: '/login' } // 未匹配路由重定向到登录
    ]
})

export default router
