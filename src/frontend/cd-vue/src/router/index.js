import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/AppIndex'
import Login from '../components/login/Login'
import Home from '../components/Home'
import Play from '../components/video/Play'
import Register from '../components/login/Register'

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
        },
        {
          path: '/liveInfo',
          name: 'LiveInfo',
          component: () => import('../components/live/LiveInfo')
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
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('../components/admin/AdminIndex'),
      meta: {
        requireAuth: true
      }

    }
  ]
})

// 用于创建默认路由
export const createRouter = routes => new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      // home页面并不需要被访问，只是作为其它组件的父组件
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: () => import('../components/home/AppIndex')
        },
        {
          path: '/admin/content/editor',
          name: 'Editor',
          component: () => import('../components/admin/content/ArticleEditor'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/library',
          name: 'Library',
          component: () => import('../components/library/LibraryIndex')
        }, {
          path: '/liveInfo',
          name: 'LiveInfo',
          component: () => import('../components/live/LiveInfo')
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../components/login/Login')
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('../components/login/Register')
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import('../components/admin/AdminIndex'),
      meta: {
        requireAuth: true
      },
      children: [
        {
          // path: '/admin/dashboard',
          // name: 'Dashboard',
          // component: () => import('../components/admin/dashboard/admin/index'),
          // meta: {
          //   requireAuth: true
          // }
        }
      ]
    },

    {
      path: '*',
      component: () => import('../components/home/Error404')
    }
  ]
})
