<template>
  <body id="paper">
  <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">User Register</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="account"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.name"
                auto-complete="off" placeholder="real name"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.phone"
                auto-complete="off" placeholder="phone"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.email"
                auto-complete="off" placeholder="E-Mail"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">Register</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
export default{
  data () {
    return {
      rules: {
        username: [{required: true, message: 'User name cannot be empty', trigger: 'blur'}],
        password: [{required: true, message: 'Password cannot be empty', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: '',
        name: '',
        phone: '',
        email: ''
      },
      loading: false
    }
  },
  methods: {
    register () {
      console.log(">>>")
      var _this = this
      this.$axios
        .post('/register', {
          username: this.loginForm.username,
          password: this.loginForm.password,
          name: this.loginForm.name,
          phone: this.loginForm.phone,
          email: this.loginForm.email
        })
        .then(resp => {
          if (resp.data.code === 200) {
            this.$alert(' registered successfully', 'message', {
              confirmButtonText: 'confirm'
            })
            _this.$router.replace('/login')
          } else {
            this.$alert(resp.data.message, 'message', {
              confirmButtonText: 'confirm'
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
    margin: -5px 0px;
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
