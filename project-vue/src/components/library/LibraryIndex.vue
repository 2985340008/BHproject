<template>
    <el-container>
        <el-aside style="width: 200px;margin-top: 20px">
            <switch></switch>
            <SideMenu @indexSelect="listByCategory" ref="SideMenu"></SideMenu>
            <!-- <Books></Books> -->
        </el-aside>
        <el-main>
            <books class="books-area" ref="booksArea"></books>
        </el-main>
    </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Books from './Book'
export default {
  name: 'AppLibrary',
  data () {
    return {
      cid: ''
    }
  },
  components: {SideMenu, Books},
  methods: {
    listByCategory () {
      var _this = this
      var cid = this.$refs.SideMenu.cid
      var url = 'categories/' + cid + '/books'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          _this.$refs.booksArea.books = resp.data
        }
        // alert(cid)
      })
    }
  }
}
</script>

<style scoped>
.books-area{
    width: 990px;
    margin-left: auto;
    margin-right: auto;
}
</style>
