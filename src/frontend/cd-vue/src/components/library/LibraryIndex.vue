<template>
  <el-container>
    <el-aside style="width: 300px;margin-top: 20px">
      <switch></switch>
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <videos class="videos-area" ref="booksArea"></videos>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Videos from './Video'

export default {
  name: 'AppLibrary',
  components: {Videos, SideMenu},
  methods: {
    listByCategory () {
      const _this = this
      const cid = this.$refs.sideMenu.cid
      const url = 'categories/' + cid + '/books'
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.$refs.videosArea.videos = resp.data.result
          _this.$refs.videosArea.currentPage = 1
        }
      })
    }
  }
}
</script>

<style scoped>
  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
