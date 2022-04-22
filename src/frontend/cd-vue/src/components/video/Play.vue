<template>
  <div>
    <Header></Header>
  <div class="yj-txt">
    <!--
    <video :src="videoSrc" :poster="videoImg" :autoplay="playStatus" height="421" width="700" :muted="muteStatus">
      your browser does not support the video tag
    </video>
    <button @click="playClick" :class="{hide: isPlay}">点击播放</button> -->
    <!--class="video-js vjs-default-skin vjs-big-play-centered" -->
    <video    preload="preload" id="videoId"  :src="videoSrc" class="video-js"
              :poster="videoImg" height="height" width="width" align="center" controls="controls"  autoplay="autoplay">

    </video>

  </div>

  <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
    open
  </el-button>

  <el-drawer
    title="title"
    :visible.sync="drawer"
    :direction="direction">
<!--    :before-close="handleClose">-->


      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          label="name"
          width="180">
          <template slot-scope="scope">
<!--            <i class="el-icon-time"></i>-->
            <span style="margin-left: 10px">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="play">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="changeVideo(scope.$index, scope.row)">play</el-button>
          </template>
        </el-table-column>
      </el-table>

<!--    <el-link v-on:click="changeVideo(video1.videoSrc)">{{video1.name}}</el-link>-->

  </el-drawer>
  </div>
</template>

<script>
import Header from '../common/Header'
export default {
  name: 'Play',
  components: {Header},
  data () {
    return {
      videoSrc: '',
      videoImg: '../../../../static/full_res.jpg',
      playStatus: '',
      muteStatus: '',
      isMute: true,
      isPlay: false,
      width: '820', // 设置视频播放器的显示宽度（以像素为单位）
      height: '500', // 设置视频播放器的显示高度（以像素为单位）
      preload: 'auto', //  建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
      controls: true, // 确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
      autoplay: '',
      // you
      drawer: false,
      direction: 'rtl',
      videos: null,
      lectureId: '',
      tableData: []
    }
  },
  mounted: function () {
    this.loadVideos()
  },
  methods: {
    loadVideos () {
      this.lectureId = this.$route.params.lectureId
      this.$axios.get('/playVideo/' + this.lectureId + '/on')
      this.$axios.get('/lectures/' + 1 + '/videos').then(resp => {
        if (resp && resp.data.code === 200) {
          this.videos = resp.data.result
          console.log(this.lectureId)
          this.tableData = resp.data.result
          this.videoSrc = this.videos[0].videoSrc
          console.log(this.videoSrc)
        } else {
          alert(resp.data.message)
        }
      })
    },
    handleClose (done) {
      // this.$confirm('Confirm close？')
      //   .then(_ => {
      //     done()
      //   })
      //   .catch(_ => {})
    },
    changeVideo (index, row) {
      this.videoSrc = row.videoSrc
    }
  }
  // 自动播放属性,muted:静音播放
  // autoplay: 'muted',

}
</script>

<style  scoped>
.yj-txt{
  color: #fff;
  font-size: 36px;
  display: inline-block;
  padding-left: 40px;
  margin-top: 30px;

}
.vSrc{

}
/*播放器样式*/
#videoId {
  float: left;
  width: 70%;
  height: 580px;
}
/*//.video-txt{*/
/*//  float: left;*/
/*//  margin-left: 40px;*/
/*//}*/
</style>
