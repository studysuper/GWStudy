<template>
  <div>
    <headBar></headBar>
    <div class="login_bg">
      <div class="login_box">
        <input
          class="username"
          type="text"
          placeholder="用户名："
        >
        <input
          class="pwd"
          type="password"
          placeholder="密码："
        >
        <div class="login_btn">登录</div>
      </div>
    </div>
    <footerBar class="footerBar"></footerBar>
  </div>
</template>
<script>
// import loginBg from "../assets/images/login_bg.png"
import headBar from "@/components/nav/header";
import footerBar from "@/components/nav/footer";
export default {
  components: {
    headBar,
    footerBar
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
}
.footerBar {
    margin-top: 520px;
}
</style>

