<template>
  <el-dialog
    :modal="true"
    :modal-append-to-body="false"
    :title="tittle"
    :visible.sync="isShow"

    :close-on-click-modal="false"
    :show-close="true"
    :close-on-press-escape="true"
    center>
<!--    :width="width"-->
<!--    :height="height"-->
    <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">User </h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username"
                  auto-complete="off" placeholder="account"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="old password"></el-input>
      </el-form-item>
      <el-form-item prop="passwordNew">
        <el-input type="password" v-model="loginForm.passwordNew"
                  auto-complete="off" placeholder="new password"></el-input>
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
        <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">Change</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: 'UserInfo',
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
      rules: {
        username: [{required: true, message: 'User name cannot be empty', trigger: 'blur'}],
        password: [{required: true, message: 'Password cannot be empty', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: '',
        passwordNew: '',
        name: '',
        phone: '',
        email: ''
      },
      loading: false
    }
  },
  mounted: function () {
    this.getIn()
  },
  methods: {
    getIn () {
      this.$axios
        .get('/getUserInfo').then(resp => {
          if (resp && resp.data.code === 200) {
            const data2 = resp.data.result
            this.loginForm.username = data2.username
            this.loginForm.name = data2.name
            this.loginForm.phone = data2.phone
            this.loginForm.email = data2.email
            console.log(resp.data.result)
          } else {
            this.$alert(resp.data.message, 'message', {
              confirmButtonText: 'confirm'
            })
          }
        })
    },
    register () {
      this.$axios
        .post('/changeUserInfo', {
          username: this.loginForm.username,
          password: this.loginForm.password,
          passwordNew: this.loginForm.passwordNew,
          name: this.loginForm.name,
          phone: this.loginForm.phone,
          email: this.loginForm.email
        })
    }
  }

}
</script>

<style scoped>

</style>
