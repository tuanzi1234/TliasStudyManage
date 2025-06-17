import request from "@/utils/request"

//分页查询所有员工数据的函数
export const getEmployeeByPage = (name, gender, begin, end, page, pageSize) =>
    request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`);

//新增员工数据的函数
export const addEmpData = (emp) => request.post('/emps', emp);

//根据id查询员工
export const getEmpById = (id) => request.get(`/emps/${id}`);

//根据id修改员工
export const updateEmpById = (emp) => request.put(`/emps`, emp);

//根据id删除/批量删除员工
export const deleteEmpById = (ids) => request.delete(`/emps?ids=${ids}`);

//查询所有员工
export const getAllEmp = () => request.get('/emps/list');
