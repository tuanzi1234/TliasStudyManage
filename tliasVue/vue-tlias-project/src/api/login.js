import request from "@/utils/request.js";

 //登录
export const loginApi = (data) => request.post('/login', data)