// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import routers from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueRouter from 'vue-router'


Vue.config.productionTip = false

Vue.use(ElementUI)

Object.defineProperty(Vue.prototype, '$axios', { value: axios })

const router = new VueRouter({
  mode: 'history',
  routes: routers
})

window.myIp = "127.0.0.1:8082"

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
