import axios from "axios"
import Element from "element-ui"
import router from './router'
import store from "./store";

// axios.defaults.baseURL="http://114.55.173.20:8082"
axios.defaults.baseURL="http://localhost:8082"

//前置拦截
axios.interceptors.request.use(config =>{

  return config
})

//后置拦截
axios.interceptors.response.use(response => {
  let res = response.data
  console.log("==========")
  console.log(res)
  console.log("==========")

    return response
  // if(res.code === 200){
  //   return response
  // }else{
  //
  //   Element.Message.error('密码不正确',{duration:3*1000})
  //   return Promise.reject(res.message)
  // }

},
  error => {
    console.log(error)
      if(error.response.data){
        error.message = error.response.data.msg
      }
      if(error.response.status === 401){
          store.commit("REMOVE_INFO")

          Element.Message.error(error.message,{duration:3*1000})
          router.push("/")
      }
      Element.Message.error(error.message,{duration:3*1000})
      return Promise.reject(error)

  }
)
