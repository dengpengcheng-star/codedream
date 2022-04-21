<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>Live Info</span>
        <el-button style="float: right; padding: 3px 0" type="text" v-on:click="show()">New</el-button>
      </div>
      <el-descriptions>
<!--        title="Live Info"-->
        <el-descriptions-item label="rtmp path">{{rtmpPath}}</el-descriptions-item>
        <el-descriptions-item label="rtmp key">{{rtmpKey}}</el-descriptions-item>
        <el-descriptions-item label="class id">{{classId}}</el-descriptions-item>
        <el-descriptions-item label="lecture name">{{classId}}</el-descriptions-item>
        <!--    <el-descriptions-item label="备注">-->
        <!--      <el-tag size="small">学校</el-tag>-->
        <!--    </el-descriptions-item>-->
        <!--    <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>-->
      </el-descriptions>
    </el-card>

  <LiveDialog :title="tittle" :isShow="isShow"  :width="width" :height="height"></LiveDialog>
  </div>
</template>

<script>
import LiveDialog from './LiveDialog'// 引入组件

export default {
  name: 'LiveInfo',
  components: { LiveDialog },
  data () {
    return {
      rtmpPath: LiveDialog.props.rtmpPath,
      rtmpKey: LiveDialog.props.rtmpKey,
      classId: LiveDialog.props.classId,
      lectureName: LiveDialog.props.lectureName,
      tittle: 'new',
      isShow: false,
      width: '60%',
      height: '2000px'

    }
  },
  methods: {
    show () {
      this.isShow = true
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
}
</style>
