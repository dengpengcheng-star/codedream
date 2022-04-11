import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'
import Home from '../components/Home'
import Play from '../components/video/Play'
import Register from '../components/Register'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path1: '/',
      name1: 'index',
      redirect1: '/index',
      component1: AppIndex,
      meta: {
        requireAuth: true
      },
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/library',
          name: 'Library',
          component: () => import('../components/library/LibraryIndex')
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/play',
      name: 'Play',
      component: Play
    }
  ]
})
