import {extend} from 'umi-request';
import {message, notification} from 'antd';
import {history} from 'umi';
import {stringify} from "querystring";
/**
 * 异常处理程序
 */
const errorHandler = (error: { response: Response }): Response => {
  const {response} = error;

  return response;
};

/**
 * 配置request请求时的默认参数
 */
const request = extend({
  errorHandler, // 默认错误处理
  credentials: 'include', // 默认请求是否带上cookie
  prefix: process.env.NODE_ENV === "production" ? "http://user-backend.markniubi.fun" : undefined,
  // requestType: 'form',
});

/**
 * 所以请求拦截器
 */
request.interceptors.request.use((url, options): any => {

  return {
    url,
    options: {
      ...options,
      headers: {

      },
    },
  };
});

/**
 * 所有响应拦截器
 */
request.interceptors.response.use(async (response, options): Promise<any> => {
  const res = await response.clone().json();
  if (res.code === 0){
    return res.data;
  }
  if (res.code === 40100){
    message.error("login problem");
    history.replace({
      pathname: '/user/login',
      search: stringify({
        redirect: location.pathname,
      }),
    });
  }
  else{
    message.error(res.description);
  }

  return res.data;
});

export default request;
