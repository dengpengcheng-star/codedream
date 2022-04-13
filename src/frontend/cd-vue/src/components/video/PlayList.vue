<!--<script lang="ts" setup>-->
<!--import ref from 'vue'-->
<!--const myVideo = ref()-->
<!--// const videoUrl = ref<string | null>(null)  //默认为空-->
<!--const videoUrl = ref('https://vjs.zencdn.net/v/oceans.mp4') // 默认视频-->
<!--const videoShowA = (e: any) => {-->
<!--  // videoUrl.value = e-->
<!--  myVideo.value.src = e-->
<!--  console.log(e, 'kkkkkkkkk')-->
<!--}-->
<!--const videoShowB = (e: any) => {-->
<!--  // videoUrl.value = e-->
<!--  myVideo.value.src = e-->
<!--  console.log(e, 'hhhhhhhhhh')-->
<!--}-->
<!--</script>-->

<!--<div class="yj-txt">-->
<!--<video-->
<!--  id="my-player"-->
<!--  class="video-js"-->
<!--  controls-->
<!--  preload="auto"-->
<!--  ref="myVideo"-->
<!--  poster="./1.jpg"-->
<!--  data-setup="{}"-->
<!--&gt;-->
<!--  &lt;!&ndash; poster="//vjs.zencdn.net/v/oceans.png" &ndash;&gt;-->
<!--  &lt;!&ndash;  src="./video.mp4" &ndash;&gt;-->
<!--  <source-->
<!--    :src="videoUrl"-->
<!--    type="video/mp4"-->
<!--  >-->
<!--</video>-->

<!--&lt;!&ndash; 视频名称列表布局 &ndash;&gt;-->
<!--<div class="video-txt">-->
<!--  <div @click="videoShowA('https://vjs.zencdn.net/v/oceans.mp4')">-->
<!--    视频1-->
<!--  </div>-->
<!--  <div @click="videoShowB('https://docs.egret.com/engine/img/engine2d.mp4')">-->
<!--    视频2-->
<!--  </div>-->
<!--</div>-->
<!--</div>-->

<!--<style lang="scss" scoped>-->
<!--.yj-txt{-->
<!--  color: #fff;-->
<!--  font-size: 36px;-->
<!--  display: inline-block;-->
<!--  padding-left: 40px;-->
<!--  margin-top: 30px;-->

<!--}-->
<!--//播放器样式-->
<!--video#my-player.video-js {-->
<!--  float: left;-->
<!--  width: 70%;-->
<!--  height: 580px;-->
<!--}-->
<!--.video-txt{-->
<!--  float: left;-->
<!--  margin-left: 40px;-->
<!--}-->
<!--</style>-->
