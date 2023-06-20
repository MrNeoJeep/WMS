<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246);height: 100%;" >
      <Aside></Aside>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px;height: 100%; border-bottom: #b2b0b0 1px solid">
        <Header></Header>
      </el-header>

      <el-main style="height: 100%;">
        <Main></Main>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
import Main from "@/components/Main";
export default {
  name: "Index",
  components: {Main, Header, Aside}

}
</script>

<style scoped>
  .el-header {
    /*background-color: #B3C0D1;*/
    color: #333;
    line-height: 60px;
  }
  .el-main{
    padding: 15px;
  }
  .el-aside {
    color: #333;
  }
</style>