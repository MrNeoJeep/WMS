<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="login-content">
              <h1 style="text-align: center">仓库管理系统</h1>
                <h2 class="login-title">用户登录</h2>

                <el-form :model="loginForm" label-width="100px"
                         :rules="rules" ref="loginForm" >
                    <el-form-item label="账号" prop="no">
                        <el-input style="width: 200px" type="text" v-model="loginForm.no"
                                  autocomplete="off" size="small" prefix-icon="el-icon-s-custom"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input style="width: 200px" type="password" v-model="loginForm.password"
                                  show-password autocomplete="off" size="small"
                                  prefix-icon="el-icon-lock"
                                  @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item style="margin-left: 30px">
                        <el-button  type="primary" @click="confirm" :disabled="confirm_disabled">登 录</el-button>
                        <el-button  type="primary" @click="resetForm" :disabled="confirm_disabled">重 置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>


<script>
    export default {
        name: "Login",
        data(){
            return{
                confirm_disabled:false,
                loginForm:{
                    no:'',
                    password:''
                },
                rules:{
                    no: [
                        { required: true, message: '请输入账号', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输密码', trigger: 'blur' }
                    ],
                }
            }
        },
        methods:{
          resetForm(){
            this.$refs.loginForm.resetFields();
          },
            confirm(){
                this.confirm_disabled=true;
                this.$refs.loginForm.validate((valid) => {
                    if (valid) { //valid成功为true，失败为false
                        //去后台验证用户名密码
                        this.$axios.post('/user/login',this.loginForm).then(res=>{
                            console.log(res)
                            if(res.data.code===200){
                                this.$message({
                                  message: '登录成功！',
                                  type: 'success'
                                });
                                //存储
                                sessionStorage.setItem("CurUser",JSON.stringify(res.data.data.user))

                                console.log(res.data.data.menu)
                                this.$store.commit("setMenu",res.data.data.menu)
                                //jwt数据存储
                                const jwt = res.headers['authorization']
                                const userInfo = res.data.data.user
                                console.log(userInfo)

                                this.$store.commit("SET_TOKEN",jwt)
                                this.$store.commit("SET_USERINFO",userInfo)

                                console.log(this.$store.getters.getUser)


                                //跳转到主页
                                this.$router.replace('/Index');
                            }else{
                                this.confirm_disabled=false;
                                // alert('校验失败，用户名或密码错误！');
                                this.$message.error(res.data.msg)
                                return false;
                            }
                        });
                    } else {
                        this.confirm_disabled=false;
                        // console.log('校验失败');
                        this.$message.error("校验失败")
                        return false;
                    }
                });

            }
        }
    }
</script>

<style scoped>
    .loginBody {
        position: absolute;
        width: 100%;
        height: 100%;
        /*background-color: #B3C0D1;*/
      background-size: cover;
      /*background-color: #B3C0D1;*/
      background: #464646 url("../assets/loginBackground.jpg") no-repeat fixed center center;
    }
    .loginDiv {
        position: absolute;
        top: 50%;
        left: 80%;
        margin-top: -200px;
        margin-left: -250px;
        width: 450px;
        height: 330px;
        background: #fff;
        border-radius: 5%;

    }
    .login-title {
        margin: 20px 0;
        text-align: center;
    }
    .login-content {
        width: 400px;
        height: 250px;
        position: absolute;
        top: 25px;
        left: 25px;
    }
</style>