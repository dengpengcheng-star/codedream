<template>
  <el-menu
    :default-active="currentPath"
    class="el-menu-admin"
    router
    mode="vertical"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    :collapse="isCollapse">
    <div style="height: 80px;"></div>
      <!--index 没有用但是必需字段-->
      <el-submenu  v-for="(item,i) in adminMenus" :key="item.path" :index="item.path" style="text-align: left">
        <span slot="title" style="font-size: 17px;">
          <i :class="item.iconCls"></i>
          {{item.nameZh}}
        </span>
<!--        <el-menu-item v-for="child in item.children" :key="child.path" :index="child.path">-->
<!--          <i :class="child.icon"></i>-->
<!--          {{ child.nameZh }}-->
<!--        </el-menu-item>-->
      </el-submenu>
  </el-menu>
</template>

<script>
import axios from 'axios'

export default {
  name: 'AdminMenu',
  data () {
    return {
      isCollapse: false,
      adminMenus: null
    }
  },
  mounted () {
    this.init()
  },
  computed: {

  },
  methods: {
    init () {
      axios.get('/menu').then(resp => {
        if (resp && resp.status === 200) {
          console.log(resp.data.result)
          const fmtRoutes = formatRoutes(resp.data.result)
          this.$router.addRoutes(fmtRoutes)
          this.adminMenus = fmtRoutes
        }
        // this.$router.replace('/admin/dashboard')
      })
    }
  }
}
const formatRoutes = (routes) => {
  let fmtRoutes = []
  routes.forEach(route => {
    if (route.children) {
      route.children = formatRoutes(route.children)
    }

    let fmtRoute = {
      path: route.path,
      component: resolve => {
        require(['./user/' + route.component + '.vue'], resolve)
      },
      name: route.name,
      nameZh: route.nameZh,
      iconCls: route.iconCls,
      meta: {
        requireAuth: true
      },
      children: route.children
    }
    fmtRoutes.push(fmtRoute)
  })
  return fmtRoutes
}

</script>

<style scoped>
  .el-menu-admin {
    border-radius: 5px;
    height: 100%;
  }
</style>
