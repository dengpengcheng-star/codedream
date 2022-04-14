<template>
  <div>
    <el-row class="tac">
      <el-col :span="12">
        <h4>students</h4>
        <!--eslint-disable-next-line-->
        <div v-for="student in students" >
            <p  v-if="student.isUp==='true'">{{student.name}}:{{student.id}}</p>
            <p v-else>{{student.name}}:{{student.id}}</p>

        </div>


        </div>
      </el-col>
    </el-row>
    <Live :liveroomIds="liveRoomId"></Live>
  </div>

</template>

<script>
import Live from './Live'
export default {
  name: 'LiveRoom',
  components: {Live},
  data () {
    return {
      direction: 'rtl',
      liveRoomId: '',
      students: null
    }
  },
  mounted: function () {
    window.addEventListener(this, 'unload', e => this.unloadHandler(e))
    this.up()
  },
  destroyed () {
    // window.removeEventListener('beforeunload', e => this.beforeunloadHandler(e))
    window.removeEventListener(this, 'unload', e => this.unloadHandler(e))
  },
  methods: {
    up () {
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
