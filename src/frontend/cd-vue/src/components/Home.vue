<template>
  <div>
    <router-view/>
    <Video></Video>
  </div>

</template>

<script>
import Video from './common/Video'
export default {
  name: 'Home',
  components: {Video}
}
</script>

<style scoped>

</style>
