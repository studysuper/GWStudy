import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index'
import bar from '@/components/nav/header'
import footerBar from '@/components/nav/footer'
import xingceMenu from '@/components/test/xingce_menu'
import shenlunMenu from '@/components/test/shenlun_menu'
import login from '@/components/nav/login'
import xingceSprint from '@/components/test/xingce_menu_sprint'
import xingceSpecial from '@/components/test/xingce_menu_special'
import xingceTest from '@/components/test/xingce_test'
import shenlunTest from '@/components/test/shenlun_test'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/bar',
      name: 'bar',
      component: bar
    },
    {
      path: '/footerBar',
      name: 'footerBar',
      component: footerBar
    },
    {
      path: '/xingceMenu',
      name: 'xingceMenu',
      component: xingceMenu
    },
    {
      path: '/shenlunMenu',
      name: 'shenlunMenu',
      component: shenlunMenu
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/xingceSprint',
      name: 'xingceSprint',
      component: xingceSprint
    },
    {
      path: '/xingceSpecial',
      name: 'xingceSpecial',
      component: xingceSpecial
    },
    {
      path: '/xingceTest',
      name: 'xingceTest',
      component: xingceTest
    },
    {
      path: '/shenlunTest',
      name: 'shenlunTest',
      component: shenlunTest
    }
  ]
})
