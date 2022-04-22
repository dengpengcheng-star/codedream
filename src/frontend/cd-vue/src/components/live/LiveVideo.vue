<!--//安装-->
<!--npm install vue-video-player &#45;&#45;save-->

<!--//代码-->
<template>
  <videoPlayer class="vjs-custom-skin videoPlayer" :options="playerOptions"></videoPlayer>
</template>
<script>

import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'

// import {videoPlayer} from 'vue-video-player/dist/ssr'
import { videoPlayer } from 'vue-video-player'
import 'videojs-flash'
export default {
  components: {
    videoPlayer
  },
  data () {
    return {
      // rtmp设置
      playerOptions: {
        height: '300',
        sources: [{
          type: 'rtmp/mp4',
          src: 'rtmp://localhost:1935/live/hello' // 需要播放的rtmp地址
        }],
        techOrder: ['flash'],
        autoplay: false,
        controls: true
      }
    }
  }
}
</script>
