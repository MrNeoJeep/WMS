<template>
  <div style="display: flex;line-height: 60px">
    <div>
      <i class="el-icon-s-fold" style="font-size: 20px; line-height: 60px"></i>
    </div>
    <div style="flex: 1;font-size: 35px;text-align: center">
      <span>仓库管理系统</span>
    </div>
    <el-dropdown>
      <span>王小虎</span>
      <i class="el-icon-s-custom" style="margin-left: 10px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "Header",
  methods:{
    toUser(){
      console.log("toUser")
    },

    logout(){
      console.log("logout")
    }
  }
}
</script>

<style scoped>

</style>