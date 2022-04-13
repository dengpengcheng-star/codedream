<template>
  <div>
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
    点我打开
  </el-button>

  <el-drawer
    title="我是标题"
    :visible.sync="drawer"
    :direction="direction"
    :before-close="handleClose">
    <div v-for="video1 in videos" >
    <el-link onclick="changeVideo(video1.videoSrc)">{{video1.name}}</el-link>
    </div>

    <span>我来啦!</span>
  </el-drawer>
  </div>
</template>

<script>
export default {
  name: 'Play',
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
      lectureId: ''
    }
  },
  mounted: function () {
    this.loadVideos()
  },
  methods: {
    loadVideos () {
      this.lectureId = this.$route.params.lectureId
      // const videoV = document.getElementById('videoId')
      // // const source = document.createElement('source')
      // // source.src = this.videoSrc
      // // source.type = 'video/mp4'
      // // videoV.append(source)
      // videoV.src = this.videoSrc
      // console.log(this.videoSrc)
      // eslint-disable-next-line standard/object-curly-even-spacing
      // this.$axios({ method: 'get', url: this.videoSrc}).then(
      //
      // )

      // document.getElementById('videoId').load()
      // document.getElementById('videoId').play()
      // const _this = this
      // this.$axios.get('/playVideo').then(resp => {
      //   if (resp && resp.data.code === 200) {
      //     _this.videoSrc = resp.data.result.videoSrc
      //   }
      // })
      this.$axios.get('/lectures/' + this.lectureId + '/videos').then(resp => {
        if (resp && resp.data.code === 200) {
          this.videos = resp.data.result
          console.log(this.lectureId)
          this.videoSrc = this.videos[0].videoSrc
          console.log(this.videoSrc)
        } else {
          alert(resp.data.message)
        }
      })
    },
    handleClose (done) {
      this.$confirm('Confirm close？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    changeVideo (videoSrc) {
      this.videoSrc = videoSrc
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
