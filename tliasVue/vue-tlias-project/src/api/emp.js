import request from "@/utils/request"

//分页查询所有员工数据的函数
export const getEmployeeByPage = (name, gender, begin, end, page, pageSize) =>
    request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`)

//新增部门数据的函数


//根据id查询部门


//根据id修改部门


//根据id删除部门
