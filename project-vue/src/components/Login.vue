<template>
  <body id="poster">
  <el-form class="login-container" status-icon :rules="rules2" :model="loginForm" ref="loginForm" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text"  v-model="loginForm.username" autocomplete="off"
                placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" autocomplete="off"
                placeholder="密码"></el-input>
    </el-form-item>
    <!-- <el-checkbox v-model="checked">记住密码</el-checkbox> -->
    <el-checkbox class="login_remember" v-model="checked" label-position="left"> <span style="color:#505458">记住密码</span>
    </el-checkbox>
    <el-form-item style="width: 100%">
      <!-- <el-button type="primary" style="width:100%;background:#505458;border:none"
                 @click="login('loginForm')">登录</el-button> -->
      <el-button type="primary" style="width:40%;background:#505458;border:none"
                 v-on:click="login">登录</el-button>
      <el-button type="primary" style="width:40%;background:#505458;border:none"
                 v-on:click="register">注册</el-button>
    </el-form-item>
    </el-form>
  </body>
</template>

<script>
// import { isvalidPass } from '@/validate.js'
export default {
  name: 'Login',
  data () {
    // var validatePass = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请输入密码'))
    //   } else if (!isvalidPass(value)) {
    //     callback(new Error('密码以字母开头 长度在8~18之间 只能包含字母、数字和下划线'))
    //   } else {
    //     callback()
    //   }
    // }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      }
      callback()
    }
    var validateUserName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules2: {
        password: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        username: [
          { required: true, validator: validateUserName, trigger: 'blur' }
        ]
      },
      checked: true,
      responseResult: []
    }
  },
  // methods: {
  //   login (formName) {
  //     this.$refs.formName.validate((valid) => {
  //       if (valid) {
  //         this.$axios
  //           .post('/login', {
  //             username: this.loginForm.username,
  //             password: this.loginForm.password
  //           })
  //           .then(_code => {
  //             console.log(_code)
  //             if (_code.data.code === 200) {
  //               // console.log('成功')
  //               // this.$router.replace({path: '/index'})
  //               this.$store.commit('login', this.loginForm)
  //               var path = this.$route.query.redirect
  //               this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
  //             } else {
  //               alert('用户名或密码错误')
  //             }
  //           })
  //           .catch(failResponse => {
  //           })
  //       } else {
  //         console.log('error submit!!')
  //         return false
  //       }
  //     })
  //   }
  // }
  methods: {
    login (formName) {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.$axios
            .post('/login', {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
            .then(_code => {
              console.log(_code)
              if (_code.data.code === 200) {
                this.$store.commit('login', this.loginForm)
                var path = this.$route.query.redirect
                console.log(_code.data)
                this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
              } else {
                alert('用户名或密码错误')
              }
            })
            .catch(failResponse => {
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    register () {
      this.$router.push({
        path: '/register'
      })
    }
  }
}
</script>
<style>
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
  #poster {
    margin-top: 0%;
    background:url("../assets/bg.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
</style>
