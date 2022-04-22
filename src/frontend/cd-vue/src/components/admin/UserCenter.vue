<template>
  <div>
    <NavMenu></NavMenu>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="name"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>name: {{ scope.row.name }}</p>
          <p>abs: {{ scope.row.abs }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.name }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="option">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">option</el-button>
      </template>
    </el-table-column>
  </el-table>
    <VideoHD :title="tittle" :isShow="isShowV"  :width="width" :height="height"></VideoHD>
    <UserInfo :title="tittle" :isShow="isShowU"  :width="width" :height="height"></UserInfo>
  </div>
</template>

<script>
import VideoHD from './VideoHD'
import NavMenu from '../common/NavMenu'
import UserInfo from './UserInfo'
export default {
  name: 'UserManagement',
  components: {UserInfo, NavMenu, VideoHD},
  data () {
    return {
      tableData: [{
        name: 'Profile',
        abs: 'user information'
      },
      //   {
      //   name: 'class',
      //   address: 'the class you are in'
      // },
      {
        name: 'Lessons',
        address: 'show the history'
      }],
      tittle: 'new',
      isShowV: false,
      isShowU: false,
      width: '60%',
      height: '2000px'
    }
  },
  methods: {
    handleEdit (index, row) {
      console.log(index)
      console.log(row)
      if (index === 1) {
        this.showHistory()
      } else if (index === 0) {
        this.showUserInfo()
      }
    },
    showHistory () {
      this.isShowV = true
      this.isShowU = false
    },
    showUserInfo () {
      this.isShowU = true
      this.isShowV = false
    }
  }
}
</script>

<style scoped>

</style>
