<template>
  <div>
    <headBar></headBar>
    <div class="login_bg">
      <div class="login_box">
        <input
          class="username"
          type="text"
          placeholder="用户名："
          ref="user"
        >
        <input
          class="pwd"
          type="password"
          placeholder="密码："
          ref="pwd"
        >
        <div class="login_btn" @click="register">注册</div>
      </div>
    </div>
    <footerBar class="footerBar"></footerBar>
  </div>
</template>
<script>
import headBar from "@/components/nav/header";
import footerBar from "@/components/nav/footer";
export default {
  data(){
    return{
      username:'',
      password:''
      
    }
  },
  components: {
    headBar,
    footerBar
  },
  created:{
    
  },
  methods:{
    register:function(){
      debugger;
      let username = this.$refs.user.value;
      let password = this.$refs.pwd.value;
      let url = "http://localhost:80/front/froUser/save";
      if((username != "") && (password != "")){
        alert('参数正确');
        this.axios.post(url,{
          userName:username,
          password:password
        })
        .then((res=>{
          console.log(res);
        }))
        .catch((error=>{
          console.log(error);
        }))
      }else if (username === ""){
        alert('用户名不能为空');
      }else {
        alert('密码不能为空');
      }
    }
  }
  
};
</script>

<style>
.login_bg {
    width: 100%;
    height: 500px;
    background: url("../../assets/images/login_bg.jpg") no-repeat;
    background-size: 100% 100%;
    position: absolute;
    margin-top: 10px;
}
.login_box {
  width: 300px;
  height: 300px;
  margin: auto;
  margin-right: 200px;
  background: #f6f6f7;
  border-radius: 10px;
  margin-top: 100px;
  margin-bottom: 100px;
}
.login_box input {
  width: 240px;
  height: 30px;
  margin-left: 20px;
  margin-top: 30px;
  outline: none;
  background: #ffffff;
  color: #666666;
  padding-left: 10px;
}
.login_box input:first-child {
  margin-top: 60px;
}
.login_btn {
  width: 240px;
  height: 30px;
  background: #40b6fc;
  margin: 30px;
  color: #ffffff;
  text-align: center;
  line-height: 30px;
  border-radius: 5px;
  cursor: pointer;
}
.footerBar {
    margin-top: 520px;
}
</style>

