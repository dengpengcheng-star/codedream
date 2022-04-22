<template>
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
    <el-table
      ref="singleTable"
      :data="tableData"
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        property="date"
        label="date"
        width="120">
      </el-table-column>
      <el-table-column
        property="courseId"
        label="Course Id"
        width="120">
      </el-table-column>
      <el-table-column
        property="completion"
        label="Completion">
        <template slot-scope="scope">
          <el-progress :percentage="scope.row.completion" status="success"></el-progress>
        </template>
      </el-table-column>
    </el-table>

  </el-dialog>
</template>

<script>
export default {
  name: 'VideoHD',
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
      default: ''
    },
    width: {
      type: String,
      default: '35%'
    },
    height: {
      type: String,
      default: '1000px'
    }

  },
  data () {
    return {
      tableData: [],
      // isShow: '',
      formLabelWidth: '120px'
    }
  },
  mounted: function () {
    this.getHD()
  },
  methods: {
    setCurrent (row) {
      this.$refs.singleTable.setCurrentRow(row)
    },
    handleCurrentChange (val) {
      this.currentRow = val
    },
    getHD () {
      this.$axios.get('/m/getVideoH').then(resp => {
        if (resp && resp.data.code === 200) {
          this.tableData = resp.data.result
          console.log(resp.data.result)
        }
      })
    }
  }

}
</script>

<style scoped>

</style>
