<!--<template>-->
<!--    <div>-->
<!--      Hello World!-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: 'AppIndex'-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<template>
  <div>
    <i :class="buttonState" style="font-size: 25px;" v-on:click="getRecommend()"></i>
    <el-carousel indicator-position="outside" class="el-carousel" type="card" height="500px">
      <el-carousel-item v-for="lecture in lectures" :key="lecture.name">
        <el-image :src="lecture.coverSrc" ></el-image>
      </el-carousel-item>
    </el-carousel>

  </div>

</template>

<script>
export default {
  name: 'AppIndex',
  // eslint-disable-next-line standard/object-curly-even-spacing
  data () {
    return {
      lectures: null,
      buttonState: 'el-icon-refresh'
    }
  },
  mounted: function () {
    this.getRecommend()
  },
  methods: {
    getRecommend () {
      this.buttonState = 'el-icon-loading'
      this.$axios
        .post('/getRecommend').then(resp => {
          if (resp && resp.data.code === 200) {
            this.lectures = resp.data.result
          }
          this.buttonState = 'el-icon-refresh'
        })
    }
  }
}
</script>

<style scoped>
.el-carousel{
  height: 500px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 0px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 300px;
}
</style>
