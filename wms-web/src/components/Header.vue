<template>
  <div style="display: flex;line-height: 60px">
    <div>
      <i :class="icon" style="font-size: 20px; line-height: 60px;cursor:pointer;" @click="collapse"></i>
    </div>
    <div style="flex: 1;font-size: 35px;text-align: center">
      <span>仓库管理系统</span>
    </div>
    <el-dropdown>
      <span style="cursor: pointer">{{user.name}}</span>
      <i class="el-icon-s-custom" style="margin-left: 10px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="EditPwd">修改密码</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <!--修改密码表单-->
    <el-dialog
        title="修改密码"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="原密码" prop="oldPassword">
          <el-input
              type="password"
              show-password
              auto-complete="off"
              placeholder="请输入原密码"
              v-model="form.oldPassword"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input
              type="password"
              show-password
              auto-complete="off"
              placeholder="请设置新密码"
              v-model="form.password"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password2">
          <el-input
              type="password"
              show-password
              auto-complete="off"
              placeholder="请确认新密码"
              v-model="form.password2"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="saveMod">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
import md5 from 'js-md5';
export default {
  name: "Header",
  data(){
    let checkPassword = (rule,value,callback)=>{
      this.form.id = this.user.id
      this.form.no = this.user.no
      this.form.oldPassword = value
      this.$axios.get("/user/findByNo?no="+this.form.no,{
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)

        if(res.code===200 && res.data[0].password === md5(this.form.oldPassword)){
          callback()
        }else{
          callback(new Error('旧密码不正确'));
        }
      })
    };

    let validateNewPassword = (rule,value,callback)=>{
      if(value === this.form.password){
        callback()
      }else{
        callback(new Error('两次输入密码不一致'))
      }
    };
    return{
      user:JSON.parse(sessionStorage.getItem('CurUser')),
      form:{
        id:'',
        no:'',
        oldPassword:'',
        password:'',
        password2:''
      },
      centerDialogVisible:false,
      rules: {
        oldPassword: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator:checkPassword,trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        password2: [
          {required: true, message: '请再次输入新密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator:validateNewPassword,trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    toUser(){
      console.log("toUser")
      this.$router.push('/Home')
    },
    EditPwd(){
      console.log("EditPassword")
      this.centerDialogVisible = true
      this.$nextTick(()=>{
        this.resetForm()
      })
    },
    resetForm(){
      this.$refs.form.resetFields()
    },
    saveMod(){
      this.$axios.post('/user/savePwd',this.form,{
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if(res.code===200){
          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.resetForm()
          this.$router.push("/")
          sessionStorage.clear()
        }else{
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    logout(){
      console.log("logout")
      this.$confirm('您确定要退出登录吗?', '提示', {
        confirmButtonText: '确定',  //确认按钮的文字显示
        type: 'warning',
        center: true, //文字居中显示

      })
          .then(() => {
            //向后端发起logout请求

            this.$axios.get('/user/logout',{
              headers: {
                "Authorization": localStorage.getItem("token")
              }
            }).then(res=>res.data).then(res => {
              if(res.code == 200){

                this.$store.commit("REMOVE_INFO")
                this.$router.push("/login")
                this.$message({
                  type:'success',
                  message:'退出登录成功'
                })

                this.$router.push("/")
                sessionStorage.clear()
              }else{
                this.$message.error("退出登录失败")
              }
            })

          })
          .catch(() => {
            this.$message({
              type:'info',
              message:'已取消退出登录'
            })
          })
    },
    collapse(){
      this.$emit("doCollapse")
    }
  },
  created() {
    this.$router.push('/Home')
    console.log(this.$store.getters.getUser)
    if(this.$store.getters.getUser.name){
      this.user = this.$store.getters.getUser
    }

  },
  props:{
    icon:String
  }
}
</script>

<style scoped>

</style>