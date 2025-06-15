import axios from "axios";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

//创建axios实例
const request = axios.create({
    baseURL: "/api", // api 的 base_url
    timeout: 5000 // 请求超时时间
});

//axios的request拦截器
request.interceptors.request.use(
    (config) => {
        const user = JSON.parse(localStorage.getItem('loginUser'));
        if (user && user.token) {
            config.headers.token = user.token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
//axios的response拦截器
request.interceptors.response.use(
    (response) => {
        return response.data;
    },
    (error) => {
        if (error.response.status === 401) {
            ElMessage.error('未登录或登录已过期，请重新登录')
            localStorage.removeItem('loginUser');
            router.push('/login');
        }else {
            ElMessage.error('接口异常');
        }
        return Promise.reject(error);
    }
);

export default request;