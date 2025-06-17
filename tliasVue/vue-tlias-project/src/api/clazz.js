import request from "@/utils/request"

//分页查询班级
export const getClazzByPage = (name, begin, end, page, pageSize) =>
    request.get(`/clazzs?name=${name}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`);

//新增班级
export const addClazz = (clazz) => request.post('/clazzs', clazz);

//通过id查询班级
export const getClazzById = (id) => request.get(`/clazzs/${id}`);

//根据id修改班级
export const updateClazzById = (clazz) => request.put(`/clazzs`, clazz);

//删除班级
export const deleteClazzById = (id) => request.delete(`/clazzs/${id}`);

//查询所有班级
export const getAllClazz = () => request.get('/clazzs/list');

