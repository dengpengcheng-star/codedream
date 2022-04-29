<template>
  <el-menu
    class="categories"
    default-active="0"
    @select="handleSelect"
    active-text-color="red" >
    <el-menu-item index="0" style="margin-left:auto">
    <i class="el-icon-menu"></i>
    <span slot="title">All</span>
  </el-menu-item>
    <div v-for="index in 14" >

    <el-menu-item :index="index" style="margin-left:auto">
      <i class="el-icon-menu"></i>
      <span slot="title">{{index*100-100}}-{{index*100-1}}</span>
    </el-menu-item>
<!--    <el-menu-item index="1">-->
<!--      <i class="el-icon-menu"></i>-->
<!--      <span slot="title">0-99</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="2">-->
<!--      <i class="el-icon-menu"></i>-->
<!--      <span slot="title">100-199</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="3">-->
<!--      <i class="el-icon-menu"></i>-->
<!--      <span slot="title">200-299</span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="4">-->
<!--      <i class="el-icon-menu"></i>-->
<!--      <span slot="title">PYTHON    </span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="5">-->
<!--      <i class="el-icon-menu"></i>-->
<!--      <span slot="title">SPRING    </span>-->
<!--    </el-menu-item>-->
<!--    <el-menu-item index="6">-->
<!--      <i class="el-icon-menu"></i>-->
<!--      <span slot="title">USEFULSKILL</span>-->
<!--    </el-menu-item>-->
    </div>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu',
  data () {
    return {
      cid: ''
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      this.cid = key
      this.$emit('indexSelect')
    }
  }
}
</script>

<style scoped>
  .categories {
    position: fixed;
    margin-left: 50%;
    left: -600px;
    top: 100px;
    width: 150px;

  }
</style>
