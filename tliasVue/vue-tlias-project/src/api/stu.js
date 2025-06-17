import request from "@/utils/request"

// 按条件分页查询所有学生的函数
export const getStuByPage = (name, degree, clazzId, page, pageSize) =>
    request.get(`/students?name=${name}&degree=${degree}&clazzId=${clazzId}&page=${page}&pageSize=${pageSize}`);

//新增学生
export const addStu = (student) => request.post('/students', student);

//根据id查询学生
export const getStuById = (id) => request.get(`/students/${id}`);

//根据id修改学生
export const updateStuById = (student) => request.put(`/students`, student);

//根据id删除学生
export const deleteStuById = (ids) => request.delete(`/students/${ids}`);

//违纪
export const updateScore = (id, score) => request.put(`/students/violation/${id}/${score}`);