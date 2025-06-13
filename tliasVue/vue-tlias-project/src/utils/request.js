import axios from "axios";

//创建axios实例
const request = axios.create({
  baseURL: "/api", // api 的 base_url
  timeout: 5000 // 请求超时时间
});

//axios的response拦截器
request.interceptors.response.use(
  response => {
    return response.data;
  },
  error => {
    return Promise.reject(error);
  }
);

export default request;