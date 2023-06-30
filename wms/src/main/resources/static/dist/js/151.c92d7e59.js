"use strict";(self["webpackChunkwms_web"]=self["webpackChunkwms_web"]||[]).push([[151],{2151:function(e,t,a){a.r(t),a.d(t,{default:function(){return m}});var o=function(){var e=this,t=e._self._c;return t("div",[t("div",{staticStyle:{"margin-bottom":"5px"}},[t("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入分类名","suffix-icon":"el-icon-search"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.loadPost.apply(null,arguments)}},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),t("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"primary"},on:{click:e.loadPost}},[e._v("查询")]),t("el-button",{attrs:{type:"success"},on:{click:e.resetParam}},[e._v("重置")]),t("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"primary"},on:{click:e.add}},[e._v("新增")])],1),t("el-table",{attrs:{data:e.tableData,"header-cell-style":{background:"#f2f5fc",color:"#555555"},border:""}},[t("el-table-column",{attrs:{prop:"id",label:"ID",width:"60"}}),t("el-table-column",{attrs:{prop:"name",label:"分类名",width:"180"}}),t("el-table-column",{attrs:{prop:"remark",label:"备注"}}),t("el-table-column",{attrs:{prop:"operate",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{size:"small",type:"success"},on:{click:function(t){return e.mod(a.row)}}},[e._v("编辑")]),t("el-popconfirm",{staticStyle:{"margin-left":"5px"},attrs:{title:"确定删除吗？"},on:{confirm:function(t){return e.del(a.row.id)}}},[t("el-button",{attrs:{slot:"reference",size:"small",type:"danger"},slot:"reference"},[e._v("删除")])],1)]}}])})],1),t("el-pagination",{attrs:{"current-page":e.pageNum,"page-sizes":[5,10,20,30],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),t("el-dialog",{attrs:{title:"提示",visible:e.centerDialogVisible,width:"30%",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[t("el-form",{ref:"form",attrs:{rules:e.rules,model:e.form,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"分类名",prop:"name"}},[t("el-col",{attrs:{span:20}},[t("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),t("el-form-item",{attrs:{label:"备注",prop:"remark"}},[t("el-col",{attrs:{span:20}},[t("el-input",{attrs:{type:"textarea"},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1)],1)],1),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("确 定")])],1)],1)],1)},s=[],r=(a(1703),{name:"GoodstypeManage",data(){let e=(e,t,a)=>{this.form.id?a():this.$axios.get("/goodstype/findByName?name="+t,{headers:{Authorization:localStorage.getItem("token")}}).then((e=>e.data)).then((e=>{console.log(e),200!=e.code?a():a(new Error("物品类别已经存在"))}))};return{tableData:[],pageSize:10,pageNum:1,total:0,name:"",centerDialogVisible:!1,form:{id:"",name:"",remark:""},rules:{name:[{required:!0,message:"请输入分类名",trigger:"blur"},{validator:e,trigger:"blur"}]}}},methods:{resetForm(){this.$refs.form.resetFields()},del(e){console.log(e),this.$axios.get("/goodstype/del?id="+e,{headers:{Authorization:localStorage.getItem("token")}}).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.$message({message:"操作成功！",type:"success"}),this.loadPost()):this.$message({message:"操作失败！",type:"error"})}))},mod(e){this.centerDialogVisible=!0,this.$nextTick((()=>{this.form.id=e.id,this.form.name=e.name,this.form.remark=e.remark}))},add(){this.centerDialogVisible=!0,this.$nextTick((()=>{this.resetForm(),this.form.id=""}))},doSave(){this.$axios.post("/goodstype/save",this.form,{headers:{Authorization:localStorage.getItem("token")}}).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.$message({message:"操作成功！",type:"success"}),this.centerDialogVisible=!1,this.loadPost(),this.resetForm()):this.$message({message:"操作失败！",type:"error"})}))},doMod(){this.$axios.post("/goodstype/update",this.form,{headers:{Authorization:localStorage.getItem("token")}}).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.$message({message:"操作成功！",type:"success"}),this.centerDialogVisible=!1,this.loadPost(),this.resetForm()):this.$message({message:"操作失败！",type:"error"})}))},save(){this.$refs.form.validate((e=>{if(!e)return console.log("error submit!!"),!1;this.form.id?this.doMod():this.doSave()}))},handleSizeChange(e){console.log(`每页 ${e} 条`),this.pageNum=1,this.pageSize=e,this.loadPost()},handleCurrentChange(e){console.log(`当前页: ${e}`),this.pageNum=e,this.loadPost()},resetParam(){this.name=""},loadPost(){this.$axios.post("/goodstype/listPage",{pageSize:this.pageSize,pageNum:this.pageNum,param:{name:this.name}},{headers:{Authorization:localStorage.getItem("token")}}).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.tableData=e.data,this.total=e.total):alert("获取数据失败")}))}},beforeMount(){this.loadPost()}}),l=r,i=a(1001),n=(0,i.Z)(l,o,s,!1,null,"5a1501e8",null),m=n.exports}}]);
//# sourceMappingURL=151.c92d7e59.js.map