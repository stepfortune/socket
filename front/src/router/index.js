import Vue from 'vue'
import Router from 'vue-router'

import ShowGroupPage from '../components/ShowGroupPage.vue'
import ClientPage from '../components/ClientPage.vue'


Vue.use(Router)

const routers = [
  {
    path: '/',
    name: 'show-group-page',
    component: ShowGroupPage
  },
  {
    path : '/config/:groupId',
    name: 'client-page',
    component: ClientPage,
    props : true
  }
]

export default routers
