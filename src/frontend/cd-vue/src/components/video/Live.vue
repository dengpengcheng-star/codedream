<template>
  <div>
    <div style="overflow: hidden; display: flex;width: 93%; margin: 0 auto;">
      <!-- 直播间信息区 -->
      <div class="live" :style="liveHeight">
        <div class="live-avatar">
          <a-avatar shape="square" :size="84" v-if="liveRoomInfo" :src="imageUrl + liveRoomInfo.anchorPhoto" />
          <div style="margin-left: 10px;" v-if="liveRoomInfo">
            <p style="font-size: 18px;">
              <b>{{ liveRoomInfo.liveroom.recordList[0].liveroomTitle }}</b>
            </p>
            <p>
              <a-icon type="user" />
              {{ liveRoomInfo.anchorName }}
            </p>
          </div>
        </div>
        <!-- 视频区 -->
        <div class="video-class">
          <div style=" position: relative;margin:0px auto;background:#000;">
            <videoPlayer
              class="vjs-custom-skin videoPlayer"
              :options="playerOptions"
              @play="onPlayerPlay($event)"
              @pause="onPlayerPause($event)"
              @ended="onPlayerEnded($event)"
              @loadeddata="onPlayerLoadeddata($event)"
              @waiting="onPlayerWaiting($event)"
              @playing="onPlayerPlaying($event)"
              @timeupdate="onPlayerTimeupdate($event)"
              @canplay="onPlayerCanplay($event)"
              @canplaythrough="onPlayerCanplaythrough($event)"
              @ready="playerReadied"
              @statechanged="playerStateChanged($event)"
            ></videoPlayer>
            <!-- 确保父元素是相对定位，弹幕容器是绝对定位 -->
            <v-barrage :arr="bulletChat.arr" :isPause="bulletChat.isPause" :percent="100"></v-barrage>
          </div>
        </div>
        <!-- 操作区 -->
        <div class="live-button">
          <a-button type="dashed">强制下播</a-button>
          <!-- <select style="margin:0px 12px;" v-model="bulletChat.direction">
            <option value="default">默认</option>
            <option value="top">顶部</option>
          </select> -->
        </div>
      </div>
      <!-- 弹幕区 -->
      <div class="chat" :style="liveHeight">
        <a-list item-layout="horizontal" :data-source="chatData" :split="false">
          <a-list-item slot="renderItem" slot-scope="item, index">
            <a-list-item-meta>
              <span slot="description">
                <span style="color: #2b94ff;">{{ item.name }}:</span>
                <span style="margin-left: 5px;color: #000000;">{{ item.content }}</span>
              </span>
            </a-list-item-meta>
          </a-list-item>
        </a-list>
      </div>
    </div>
  </div>
</template>

<script>
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
import videoPlayer from 'vue-video-player'
import 'videojs-contrib-hls'
import 'videojs-flash'
import VBarrage from './VBarrage/index.vue'

const chatData = [
  {
    name: '郭大炮',
    content: '我就说了，主播真帅'
  },
  {
    name: '一只小团团',
    content: '主播表演跳舞吗，红红火火恍恍惚惚'
  },
  {
    name: '圆圈它不圆',
    content: '圆圈它不圆圆圈它不圆圆圈它不圆'
  },
  {
    name: '河南加油',
    content: '河南加油河南加油河南加油河南加油'
  }
]
export default {
  name: 'Live',
  components: { videoPlayer, VBarrage },
  props: ['liveroomIds'],
  data () {
    return {
      imageUrl: process.env.MY_APP_IMAGE,
      liveroomId: this.liveroomIds,
      liveRoomInfo: null, // 直播间信息
      playerOptions: {
        height: '500px', // 播放器高度
        language: 'zh-CN',
        muted: false, // 默认情况下将会消除任何音频。
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          {
            type: ['application/x-mpegURL', 'rtmp/flv', 'video/x-flv', 'rtmp/mp4'], // 视频流协议，如果是hls，需要后端开启跨域
            src: '' // 拉流地址
          }
        ],
        autoplay: true, // 自动播放
        controls: true, // 编辑器控件
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间功能
          fullscreenToggle: true // 全屏按钮
        },
        flash: { hls: { withCredentials: false } },
        html5: { hls: { withCredentials: false } }
      },
      chatData: chatData, // 弹幕列表
      liveHeight: { height: 0 },
      bulletChat: {
        arr: [], // 传入的弹幕源数组
        isPause: false, // 控制是否暂停弹幕
        sendContent: null, // 自己发送的弹幕内容
        isJs: false, // 是否解析html
        direction: 'default'
      }
    }
  },
  mounted () {
    window.addEventListener('resize', this.changeHeight)
    this.changeHeight()
    this.init()
    // 初始化弹幕
    this.bulletChatData()
  },
  methods: {
    // 视频高度
    changeHeight () {
      this.liveHeight.height = window.innerHeight - 110 + 'px'
    },
    async init () {
      let resData = await this.getLiveRoomInfo()
      this.liveRoomInfo = resData
      // 将url赋值给src
      this.playerOptions['sources'][0]['src'] = this.liveRoomInfo.liveroom.pullUrl
    },
    // 获取直播间信息
    async getLiveRoomInfo () {
      return new Promise((resolve, reject) => {
        this.$axios
          .post(process.env.MY_API_LIVEBROADCAST + 'liveroom/getLiveRoomInfo?liveroomId=' + this.liveroomId)
          .then(res => {
            if (res.data.status === 200) {
              console.log(res.data.data)
              resolve(res.data.data)
            } else {
              if (res.data.msg === '主播已下播！') {
                this.closeModal()
              }
              this.$message.error({ content: res.data.msg })
            }
          })
          .catch(res => {
            console.log(res)
          })
      })
    },
    // 初始化模拟弹幕数据
    bulletChatData () {
      let arr = ['我就说了，主播真帅', '主播表演跳舞吗，红红火火恍恍惚惚', '圆圈它不圆圆圈它不圆圆圈它不圆', '河南加油河南加油河南加油河南加油']
      for (let i = 0; i < 6; i++) {
        for (let index = 0; index < 1000; index++) {
          if (index % 2 === 0) {
            this.bulletChat.arr.push({
              direction: 'top',
              content: arr[parseInt(Math.random() * arr.length)]
            })
          } else {
            this.bulletChat.arr.push({
              direction: 'default',
              content: arr[parseInt(Math.random() * arr.length)]
            })
          }
        }
      }
    },
    // 发送弹幕
    sendBarrage () {
      if (this.bulletChat.arr.length > 1 && this.bulletChat.sendContent !== '' && this.bulletChat.sendContent != null) {
        this.bulletChat.arr.unshift({
          content: this.bulletChat.sendContent, // 弹幕内容
          direction: this.bulletChat.direction, // 方向  default | top
          isSelf: true, // 是否是自己发的弹幕
          style: {
            color: 'red', // 弹幕颜色
            fontSize: '25px'
          },
          isJs: this.bulletChat.isJs // 是否解析html
        })
      } else {
        this.bulletChat.arr.push({
          content: this.bulletChat.sendContent,
          direction: this.bulletChat.direction,
          isSelf: true,
          style: {
            color: 'red'
          },
          isJs: this.bulletChat.isJs
        })
      }
      this.bulletChat.sendContent = null
    },
    // 播放回调
    onPlayerPlay (player) {
      console.log('player play!', player)
    },
    // 暂停回调
    onPlayerPause (player) {
      console.log('player pause!', player)
    },
    // 视频播完回调
    onPlayerEnded (player) {
      console.log('player ended!', player)
    },
    // DOM元素上的readyState更改导致播放停止
    onPlayerLoadeddata (player) {
      console.log('player Loadeddata!', player)
    },
    // 已开始播放回调
    onPlayerWaiting (player) {
      console.log('player Waiting!', player)
    },
    // 当播放器在当前播放位置下载数据时触发
    onPlayerPlaying (player) {
      console.log('player Playing!', player)
    },
    // 当前播放位置发生变化时触发
    onPlayerTimeupdate (player) {
      // console.log('player Timeupdate!', player.currentTime())
    },
    // 媒体的readyState为HAVE_FUTURE_DATA或更高
    onPlayerCanplay (player) {
      console.log('player Canplay!', player)
    },
    // 媒体的readyState为HAVE_ENOUGH_DATA或更高。这意味着可以在不缓冲的情况下播放整个媒体文件。
    onPlayerCanplaythrough (player) {
      console.log('player Canplaythrough!', player)
    },
    // 播放状态改变回调
    playerStateChanged (playerCurrentState) {
      console.log('player current update state', playerCurrentState)
      // if(playerCurrentState.waiting != null && playerCurrentState.waiting == true){
      //   this.$message.error({ content: "主播已离开！" });
      //   this.closeModal();
      // }
    },
    // 将侦听器绑定到组件的就绪状态。与事件监听器的不同之处在于，如果ready事件已经发生，它将立即触发该函数。。
    playerReadied (player) {
      // eslint-disable-next-line no-unused-vars
      const hls = player.tech({IWillNotUseThisInPlugins: true}).hls
      player.tech_.hls.xhr.beforeRequest = function (options) {
        return options
      }
    },
    // 关闭按钮
    closeModal () {
      this.$emit('fatherRefresh', false)
    }
  }
}
</script>

<style scoped="scoped" less>
.live {
  width: 70%;
  /* height: 830px; */
  border: 1px solid #e2e2e2;
  padding: 10px;
}
/deep/ .live-avatar {
  padding: 5px;
  overflow: hidden;
  display: flex;
  border-bottom: 1px solid #e2e2e2;
}
.live /deep/ .video-class {
  width: 100%;
}
.chat {
  width: 30%;
  height: 830px;
  border: 1px solid #e2e2e2;
  margin-left: 26px;
  padding: 5px 5px 5px 25px;
}
.live-button {
  height: 70px;
}
</style>

<style lang="scss" scoped>
.barrage-control {
  text-align: center;
  margin: 10px 0px;
}
</style>
