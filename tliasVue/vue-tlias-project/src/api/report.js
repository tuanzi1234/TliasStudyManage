import request from "@/utils/request.js";

//统计员工职位人数的函数
export const getEmpCount = () => request.get('/report/empJobData');

//统计员工性别人数的函数
export const getEmpSexCount = () => request.get('/report/empGenderData');

//统计班级学生人数的函数
export const getStuCount = () => request.get('/report/studentCountData');

//统计学生学历人数的函数
export const getStuEduCount = () => request.get('/report/studentDegreeData');

//日志统计的函数
export const getLog = (page, pageSize) => request.get(`/log/page?page=${page}&pageSize=${pageSize}`);
