import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import App from './App.vue'
import axios from "axios";
import VueRouter from "vue-router";
import router from "@/router";
import store from "@/store";
import "./axios.js"
import "./permission.js"


//添加axios，处理请求
Vue.prototype.$axios = axios;
//设置全局请求
// Vue.prototype.$httpUrl = 'http://localhost:8082';
// Vue.prototype.$httpUrl = '{address}';

Vue.config.productionTip = false;

// Vue.use(ElementUI)
Vue.use(ElementUI,{size:'small'})
Vue.use(VueRouter)


new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
