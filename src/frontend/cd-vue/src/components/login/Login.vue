<template>
  <body id="paper">
  <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">Login</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="Username"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="Password"></el-input>
    </el-form-item>
    <el-checkbox class="login_remember" v-model="checked"
                 label-position="left"><span style="color: #505458">Remember me?</span></el-checkbox>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="login">Login</el-button>
      <router-link to="register"><el-button type="primary" style="width: 40%;background: #505458;border: none">Register</el-button></router-link>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
export default{
  data () {
    return {
      rules: {
        username: [{required: true, message: 'Username can not be empty', trigger: 'blur'}],
        password: [{required: true, message: 'Password can not be empty', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    login () {
      const _this = this
      console.log(this.$axios)
      this.$axios
        .post('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(resp => {
          if (resp.data.code === 200) {
            const data = resp.data.result
            _this.$store.commit('login', data)
            const path = _this.$route.query.redirect
            _this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
            console.log(resp.data.code)
          } else {
            console.log(resp.data.message)
            this.$alert(resp.data.message, 'Hint', {
              confirmButtonText: 'Confirm'
            })
          }
        })
        .catch(failResponse => {})
    }
  }
}
</script>
<style>
#paper {
  background: url("../../assets/sea.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: 0;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
