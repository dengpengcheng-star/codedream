<template>
  <div>
    <Header></Header>
    <el-row class="tac">
      <el-col :span="12">
        <h3>students</h3>
        <h4>total:{{total}}</h4>
        <h4>online:{{up}}</h4>
        <!--eslint-disable-next-line-->
        <div v-for="student in students" >
            <p  v-if="student.up==='true'" style="color: #2b94ff">{{student.name}}:{{student.id}}</p>
            <p v-else style="color: #2c3e50">{{student.name}}:{{student.id}}</p>

        </div>

      </el-col>
    </el-row>
    <LiveVideo ></LiveVideo>
<!--    :liveroomIds="liveRoomId"-->
  </div>

</template>

<script>
// import Live from './Live'
import LiveVideo from './LiveVideo'
import Header from '../common/Header'
export default {
  name: 'LiveRoom',
  components: {Header, LiveVideo},
  data () {
    return {
      direction: 'rtl',
      liveRoomId: '',
      students: null,
      total: 0,
      up: 0
    }
  },
  mounted: function () {
    window.addEventListener(this, 'unload', e => this.unloadHandler(e))
    this.setUp()
  },
  destroyed () {
    // window.removeEventListener('beforeunload', e => this.beforeunloadHandler(e))
    window.removeEventListener(this, 'unload', e => this.unloadHandler(e))
  },
  methods: {

    setUp () {
      const url = '/live/' + this.liveRoomId + '/up'
      this.$axios.get(url)
    },
    unloadHandler () {
      const url = '/live/' + this.liveRoomId + '/remove'
      this.$axios.get(url)
    },
    getUpList () {
      const url = '/live/' + this.liveRoomId + '/getUpList'
      this.$axios.get(url).then(resp => {
        if (resp && resp.data.code === 200) {
          this.students = resp.data.result
          let up = 0
          let total = this.students.length
          for (let i = 0; i < total; i++) {
            if (this.students[i].up === 'true') {
              up = up + 1
            }
          }
          this.total = total
          this.up = up
        } else {
          alert(resp.data.message)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
