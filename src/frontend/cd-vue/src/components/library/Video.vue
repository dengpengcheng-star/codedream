<template>
  <div>
    <el-row style="height: 840px;">
      <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
<!--      <view-switch class="switch"></view-switch>-->
      <el-tooltip effect="dark" placement="right"
                  v-for="item in videos.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">name:{{item.courseName}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>id:{{item.id}}</span> /
<!--          <span>{{item.date}}</span> /-->
<!--          <span>{{item.press}}</span>-->
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover">
            <img :src="item.picSrc" alt="封面">
          </div>
          <div class="info">
            <div class="title">
              <a href="" style="font-size: 25px;">{{item.id}}</a>
              <i class="el-icon-video-play" v-on:click="openVideo(1)" style="font-size: 25px;"></i>
            </div>
          </div>
          <div class="author">
<!--            id:{{item.id}}-->

<!--            <el-button type="primary" class="el-icon-video-play"  size="mini" v-on:click="openVideo(1)" :videoSrc="item.videoSrc"></el-button>-->
          </div>

        </el-card>
      </el-tooltip>
    </el-row>
    <el-row>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        :total="videos.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import SearchBar from './SearchBar'
import ViewSwitch from './ViewSwitch'

export default {
  name: 'Videos',
  components: {SearchBar, ViewSwitch},
  data () {
    return {
      videos: [],
      currentPage: 1,
      pagesize: 18
    }
  },
  mounted: function () {
    this.loadVideos()
  },
  methods: {
    loadVideos () {
      const _this = this
      this.$axios.get('/lectures').then(resp => {
        if (resp && resp.data.code === 200) {
          _this.videos = resp.data.result
        }
      })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    searchResult () {
      const _this = this
      this.$axios
        .get('/search?keywords=' + this.$refs.searchBar.keywords, {
        }).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.videos = resp.data.result
          }
        })
    },
    openVideo (lectureId) {
      // console.log(lectureId)
      this.$router.push({
        path: '/play',
        name: 'Play',
        params: {
          lectureId: lectureId
        }
        /* query: {
            key: 'key',
            msgKey: this.msg
        } */
      })
    }
  }
}
</script>
<style scoped>

  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
    /*margin: 0 auto;*/
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
    display: flex;
    flex-direction: row;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  .el-icon-delete {
    cursor: pointer;
    float: right;
  }

  .switch {
    display: flex;
    position: absolute;
    left: 880px;
    top: 5px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }

</style>
