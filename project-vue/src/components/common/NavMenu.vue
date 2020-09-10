<template>
    <el-menu
  :default-active="'/index'"
  router
  mode="horizontal"
  background-color="white"
  text-color="#222"
  active-text-color="red"
  style="min-width: 1300px">
  <el-menu-item v-for="(item,i) in navList" :key = "i" :index="item.name">{{item.navItem}}</el-menu-item>
  <a href="#nowhere" style="color:#222;float:right;padding:20px;">欢迎欢迎！！！</a>
  <i class="el-iocn-menu" style="folat:right;font-size:45px;color:#222;padding-top:8px"></i>
  <i class="el-iocn-switch-button" v-on:click="logout" style="color:#222;float:right;padding:20px;">退出</i>
  <span style="position:absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">北海软件工程有限公司</span>
</el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/jotter', navItem: '笔记本'},
        {name: '/library', navItem: '图书馆'},
        {name: '/admin', navItem: '个人中心'}
      ]
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$axios.get('/logout').then(resp => {
        if (resp.data.code === 200) {
          _this.$store.commit('logout')
          _this.$router.replace('/login')
        }
      })
    }
  }
}
</script>

<style scoped>
a{
    text-decoration: none;
}
span{
    pointer-events: none;
}
.el-iocn-switch-button {
  cursor: pointer;
  outline: 0;
}
</style>
