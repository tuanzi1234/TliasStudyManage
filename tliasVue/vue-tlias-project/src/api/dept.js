import request from "@/utils/request"

//查询所有部门数据的函数
export const getDeptList = () => request.get('/depts');

//新增部门数据的函数
export const addDept = (dept) => request.post('/depts', dept);

//根据id查询部门
export const getDeptById = (id) => request.get(`/depts/${id}`);

//根据id修改部门
export const updateDeptById = (id, dept) => request.put(`/depts`, dept);

//根据id删除部门
export const deleteDeptById = (id) => request.delete(`/depts?id=${id}`);