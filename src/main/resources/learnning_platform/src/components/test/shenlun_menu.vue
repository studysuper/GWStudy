<template>
  <div class="test_menu">
    <div class="menu_header">
      <headBar></headBar>
    </div>
    <div class="menu_body">
      <div class="record_buttons">
        <el-button type="text">评估报告</el-button>
        <el-button type="text">答题记录</el-button>
        <el-button type="text">收藏夹</el-button>
      </div>
      <div class="tit_bar">
        <span class="tit_span">在线题库-申论</span>
        <span class="tit_btn">切换行测题库</span>
      </div>
      <div class="test_list">
        <div class="test_con">
          <div class="test_part">
            <div class="test_bod_left">
              <div class="c_out">
                <span>专项训练</span>
              </div>
            </div>
            <div class="test_bod_rig">
              <div class="c_out">
                <span>历年真题</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <footerBar></footerBar>
  </div>
</template>
<script>
import headBar from "@/components/nav/header";
import footerBar from "@/components/nav/footer";
export default {
  data() {
    return {};
  },
  components: {
    headBar,
    footerBar
  }
};
</script>
<style>
.menu_header {
  width: 80%;
  margin: auto;
}
.menu_body {
  margin-top: 10px;
  height: 600px;
  background: #f6f6f7;
}
.record_buttons {
  width: 80%;
  margin-left: 10%;
  text-align: right;
}
.tit_bar {
  width: 80%;
  height: 40px;
  background: #40b6fc;
  margin-left: 10%;
  line-height: 40px;
}
.tit_span {
  margin-left: 10px;
  color: #333333;
}
.tit_btn {
  float: right;
  margin-right: 10px;
  color: #ffffff;
  font-size: 14px;
}
.test_list {
  width: 80%;
  margin-left: 10%;
}
.test_con {
  margin: 10px auto;
  width: 800px;
  height: 400px;
  /* border: 1px dashed #333333; */
}
.test_part {
  position: relative;
  height: 220px;
  margin-top: 10px;
}
.test_bod_left {
  cursor: pointer;
  position: absolute;
  display: inline-block;
  width: 200px;
  height: 200px;
  background: #ffffff;
  border-radius: 10px;
  left: 200px;
  top: 20px;
}
.test_bod_left:hover .c_out {
  background: #bfe9ff;
}
.test_bod_rig:hover .c_out {
  background: #bfe9ff;
}
@keyframes changeBg {
  /* from{
        background: #ffffff;
    }
    to{
        background: #bfe9ff;
    } */
}
.c_out {
  width: 150px;
  height: 150px;
  border: 1px dashed;
  border-radius: 50%;
  margin-left: 25px;
  margin-top: 25px;
}
.c_out span {
  display: inline-block;
  width: 120px;
  height: 120px;
  text-align: center;
  line-height: 120px;
  background: #bfe9ff;
  border-radius: 50%;
  margin-top: 15px;
  margin-left: 15px;
}
.test_bod_rig {
  cursor: pointer;
  position: absolute;
  display: inline-block;
  width: 200px;
  height: 200px;
  border-radius: 10px;
  background: #ffffff;
  right: 150px;
  top: 20px;
}
.test_bod_rig span {
  display: inline-block;
  width: 120px;
  height: 120px;
  text-align: center;
  line-height: 120px;
  background: #bfe9ff;
  border-radius: 50%;
  margin-top: 15px;
  margin-left: 15px;
}
</style>


