<template>
  <div class="box">
    <section id="video" class="video-js vjs-default-skin" ref="viodeRef"></section>
  </div>
</template>

<script>
import Video from 'video.js'
import 'video.js/dist/video-js.min.css'
export default {
  name: 'LivePlay',
  mounted () {
    this.player = Video('video', {
      // 确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。
      controls: true,
      // 自动播放属性,muted:静音播放
      muted: false,
      autoplay: true,
      // 建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
      preload: 'auto',
      // 设置视频播放器的显示宽度（以像素为单位）
      width: '800px',
      // 设置视频播放器的显示高度（以像素为单位）
      height: '500px',
      // poster: 'https://static.shuxuejia.com/img/video_image.png', // 封面图片
      sources: [{
        src: 'rtmp://localhost:1935/live/hello',
        type: 'video/rtmp'
      }],

      playbackRates: [0.5, 1, 1.5, 2] // 倍速播放

    }, function onPlayerReady () {
      Video.log('Your player is ready!') // 比如： 播放量+1请求

      this.on('ended', function () {
        Video.log('Awww...over so soon?!')
      })
    })
    this.player.src('rtmp://localhost:1935/live/hello')
    this.player.load('rtmp://localhost:1935/live/hello')
  },
  beforeDestroy () {
    if (this.player) {
      this.player.dispose()
    }
  }

}
</script>

<style scoped lang="scss">
.box{
  width:100%;
  height:100%;
  display:flex;
  align-items: center;
  justify-content: center;
  .video{
    margin:0 auto;
  }
}

</style>
