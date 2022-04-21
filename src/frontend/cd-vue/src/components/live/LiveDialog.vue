<template>
  <div>
    <el-dialog
      :modal="true"
      :modal-append-to-body="true"
      :title="tittle"
      :visible.sync="isShow"
      :width="width"
      :height="height"
      :close-on-click-modal="false"
      :show-close="true"
      :close-on-press-escape="true"
      center>
      <el-form :model="form">
        <el-form-item label="lecture name">
          <el-input v-model="form.lectureName"></el-input>
        </el-form-item>
        <el-form-item label="classId">
          <el-input v-model="form.classId"></el-input>
        </el-form-item>
        <el-form-item label="Description">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
<!--        <el-form-item>-->
<!--          <el-button type="primary" @click="onSubmit">Apply to create a live stream</el-button>-->
<!--          <el-button>cancel</el-button>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel()">cancel</el-button>
        <el-button type="primary" @click="submit()">confirm</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
export default {
  name: 'LiveDialog',
  props: {
    isShow: {
      type: Boolean,
      default: true
    },
    switchStyle: {
      type: Boolean,
      default: true
    },
    tittle: {
      type: String,
      default: 'New'
    },
    width: {
      type: String,
      default: '35%'
    },
    height: {
      type: String,
      default: '1000px'
    },
    path: '',
    rtmpKey: '',
    classId: '',
    lectureName: ''
  },
  data () {
    return {
      form: {
        lectureName: '',
        classId: '',
        desc: ''
      },
      // isShow: '',
      formLabelWidth: '120px'
    }
  },
  methods: {
    submit () {
      this.isShow = false
      this.$axios
        .post('/getLiveRoom', {
          lectureName: this.form.lectureName,
          classId: this.form.classId,
          desc: this.form.desc
        })
        .then(resp => {
          if (resp.data.code === 200) {
            const data = resp.data.result
            this.props.rtmpPath = data.rtmpPath
            this.props.lectureName = data.lectureName
            this.props.classId = data.classId
            this.props.rtmpKey = data.rtmpKey
            console.log(resp.data.code)
          } else {
            console.log(resp.data.code)
            console.log(resp.data.message)
            this.props.path = ''
            this.props.lectureName = ''
            this.props.classId = ''
            this.props.rtmpKey = ''
            this.$alert(resp.data.message, 'error message', {
              confirmButtonText: 'confirm'
            })
          }
        })
        .catch(failResponse => {})
    },
    cancel () {
      this.isShow = false
      this.props.path = ''
      this.props.lectureName = ''
      this.props.classId = ''
      this.props.key = ''
    }
  }
}

</script>
.divauto{
margin:  10px auto 0 auto;
}
<style scoped>

</style>
