<template>
  <div>
    <div class="register-wrapper">
      <div id="register">
        <p class="title">注册</p>
        <el-form
          :model="ruleForm2"
          status-icon
          :rules="rules2"
          ref="ruleForm2"
          label-width="0"
          class="demo-ruleForm"
        >
          <!-- <el-form-item prop="username">
            <el-input v-model="ruleForm2.user" auto-complete="off" placeholder="账号"></el-input>
          </el-form-item> -->
          <el-form-item prop="username">
            <el-input type="username" v-model="ruleForm2.username" auto-complete="off" placeholder="输入账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="ruleForm2.password" auto-complete="off" placeholder="输入密码"></el-input>
          </el-form-item>
          <el-form-item prop="checkPassword">
            <el-input type="password" v-model="ruleForm2.checkPassword" auto-complete="off" placeholder="确认密码"></el-input>
          </el-form-item>
          <el-form-item prop="email">
            <el-input v-model="ruleForm2.email" auto-complete="off" placeholder="邮箱"></el-input>
          </el-form-item>
          <el-form-item prop="phone">
            <el-input v-model="ruleForm2.phone" auto-complete="off" placeholder="请输入手机号"></el-input>
          </el-form-item>
          <!-- <el-form-item prop="smscode" class="code">
            <el-input v-model="ruleForm2.smscode" placeholder="验证码"></el-input>
            <el-button type="primary" :disabled='isDisabled' @click="sendCode">{{buttonText}}</el-button>
          </el-form-item> -->
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm2')" style="width:100%;">注册</el-button>
            <p class="login" @click="gotoLogin">已有账号？立即登录</p>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data () {
    // <!--验证手机号是否合法-->
    let checkPhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号码'))
      } else if (!this.checkMobile(value)) {
        callback(new Error('手机号码不合法'))
      } else {
        callback()
      }
    }
    // 账号
    let validateUserName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'))
      } else {
        callback()
      }
    }
    //  <!--验证码是否为空-->
    // let checkSmscode = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请输入手机验证码'))
    //   } else {
    //     callback()
    //   }
    // }
    // <!--验证密码-->
    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm2.checkPassword !== '') {
          this.$refs.ruleForm2.validateField('checkPassword')
        }
        callback()
      }
    }
    // <!--二次验证密码-->
    let validatePassword2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm2.checkPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    let validateEmail = (rule, value, callback) => {
      if (value !== '') {
        if (value.length > 50) {
          callback(new Error('邮箱字符长度不能超过50个字符'))
        }
        // var reg = /[A-Za-z0-9._%+-]+@@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}/
        // var re = new RegExp(reg)
        // if (!re.test(value)) {
        //   callback(new Error('邮箱格式不正确'))
        // } else {
        callback()
        // }
      }
    }
    return {
      ruleForm2: {
        username: '',
        password: '',
        checkPassword: '',
        phone: '',
        // smscode: '',
        email: ''
      },
      rules2: {
        username: [{ validator: validateUserName, trigger: 'change' }],
        password: [{ validator: validatePassword, trigger: 'change' }],
        checkPassword: [{ validator: validatePassword2, trigger: 'change' }],
        phone: [{ validator: checkPhone, trigger: 'change' }],
        // smscode: [{ validator: checkSmscode, trigger: 'change' }],
        email: [{ validator: validateEmail, trigger: 'change' }]
      }
    //   },
    //   buttonText: '发送验证码',
    //   isDisabled: false, // 是否禁止点击发送验证码按钮
    //   flag: true
    }
  },
  methods: {
    // <!--发送验证码-->
    // sendCode () {
    //   let phone = this.ruleForm2.tel
    //   if (this.checkMobile(phone)) {
    //     console.log(phone)
    //     let time = 60
    //     this.buttonText = '已发送'
    //     this.isDisabled = true
    //     if (this.flag) {
    //       this.flag = false
    //       let timer = setInterval(() => {
    //         time--
    //         this.buttonText = time + ' 秒'
    //         if (time === 0) {
    //           clearInterval(timer)
    //           this.buttonText = '重新获取'
    //           this.isDisabled = false
    //           this.flag = true
    //         }
    //       }, 1000)
    //     }
    //   }
    // },
    // <!--提交注册-->
    submitForm (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var _this = this
          this.$axios
            .post('/register', {
              username: this.ruleForm2.username,
              password: this.ruleForm2.password,
              phone: this.ruleForm2.phone,
              email: this.ruleForm2.email
            })
          //        .then(_code => {
          //       console.log(_code)
          //       if (_code.data.code === 200) {
          //         alert('注册成功')
          //         this.reload()
          //       } else {
          //         alert('用户名或密码错误')
          //       }
          //     })
          //     .catch(failResponse => {
          //     })
          // } else {
          //   console.log('error submit!!')
          //   return false
          // }
            .then(resp => {
              if (resp.data.code === 200) {
                this.$alert('注册成功', '提示', {
                  confirmButtonText: '确定'
                })
                _this.$router.replace('/login')
              } else {
                this.$router(resp.data.message, '提示', {
                  confirmButtonText: '确定'
                })
              }
            })
            .catch(failResponse => {})
        }
      })
    },
    // <!--进入登录页-->
    gotoLogin () {
      this.$router.push({
        path: '/login'
      })
    },
    // 验证手机号
    checkMobile (str) {
      let re = /^1\d{10}$/
      if (re.test(str)) {
        return true
      } else {
        return false
      }
    }
  }
}
</script>

<style scoped>
.loading-wrapper {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background: #aedff8;
  display: flex;
  align-items: center;
  justify-content: center;
}
.register-wrapper img {
  position: absolute;
  z-index: 1;
}
.register-wrapper {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
}
#register {
  max-width: 340px;
  margin: 60px auto;
  background: #fff;
  padding: 20px 40px;
  border-radius: 10px;
  position: relative;
  z-index: 9;
}
.title {
  font-size: 26px;
  line-height: 50px;
  font-weight: bold;
  margin: 10px;
  text-align: center;
}
.el-form-item {
  text-align: center;
}
.login {
  margin-top: 10px;
  font-size: 14px;
  line-height: 22px;
  color: #1ab2ff;
  cursor: pointer;
  text-align: left;
  text-indent: 8px;
  width: 160px;
}
.login:hover {
  color: #2c2fd6;
}
.code >>> .el-form-item__content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.code button {
  margin-left: 20px;
  width: 140px;
  text-align: center;
}
.el-button--primary:focus {
  background: #409EFF;
  border-color: #409EFF;
  color: #fff;
}
</style>
