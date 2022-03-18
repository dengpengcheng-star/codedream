<template>

  <el-descriptions class="margin-top" title="无边框列表" :column="3" :size="size">
    <template slot="extra">
      <el-button type="primary" size="small">操作</el-button>
    </template>
    <el-descriptions-item label="课程图"><div class="demo-image__placeholder">
      <div class="block">
        <span class="demonstration">默认</span>
        <el-image :src="src"></el-image>
      </div>
      <div class="block">
        <span class="demonstration">自定义</span>
        <el-image :src="src">
          <div slot="placeholder" class="image-slot">
            加载中<span class="dot">...</span>
          </div>
        </el-image>
      </div>
    </div>
    </el-descriptions-item>
    <el-descriptions-item label="课程名">kooriookami</el-descriptions-item>
    <el-descriptions-item label="老师">18100000000</el-descriptions-item>
    <el-descriptions-item label="播放量">苏州市</el-descriptions-item>
    <el-descriptions-item label="Tags">
<!--      <el-tag size="small">学校</el-tag>-->
    </el-descriptions-item>
    <el-descriptions-item label="描述">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
  </el-descriptions>
</template>

<script>
export default {
  data () {
    return {
      size: '',
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
    }
  }
}
</script>
