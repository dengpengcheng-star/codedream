<template>
  <div>
    <nav-menu></nav-menu>
    <router-view/>
  </div>

</template>

<script>
import NavMenu from './common/NavMenu'
export default {
  name: 'Home',
  // eslint-disable-next-line standard/object-curly-even-spacing
  components: { NavMenu}
}
</script>

<style scoped>

</style>
