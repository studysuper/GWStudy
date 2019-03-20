<template>
  <div>
    <div class="menu_header">
      <headBar></headBar>
    </div>
    <div class="test_body">
      <div class="test_list">
        <div class="test_name_item" v-for="item in testList">
          <div class="test_name_item_in">
            <div class="name_title">
              {{item.title}}
            </div>
            <div class="name_desc">
              共{{item.all}}题/已做{{item.ready}}题
            </div>
            <div class="name_btns">
              <div class="name_btns_dati active">
                我要答题
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
    return {
      testList:[{title:"常识判断",all:1000,ready:300},
      {title:"言语理解与表达",all:2000,ready:200},
      {title:"数量关系",all:2300,ready:350},
      {title:"判断推理",all:1200,ready:600},
      {title:"资料分析",all:2400,ready:100}]
    };
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
.test_body {
  margin-top: 10px;
  width: 100%;
  /* height: 800px; */
  background: #f5f5f6;
}
.test_list {
  width: 1190px;
  margin: 0 auto;
  overflow: auto;
}
.test_name_item {
  float: left;
  width: 280px;
  height: 182px;
  box-sizing: border-box;
  border: 1px solid #e4e4e4;
  background-color: #f8f8f8;
  border-radius: 3px;
  margin-right: 23px;
  margin-top: 23px;
}
.test_name_item:nth-of-type(4n) {
  margin-right: 0;
}
.test_name_item_in {
  height: 100%;
  background-color: #fff;
  position: relative;
}
.test_name_item_in .name_title {
  width: 180px;
  margin: 0 auto;
  color: #333;
  font-size: 16px;
  line-height: 22px;
  overflow: hidden;
  text-align: center;
  margin-bottom: 20px;
  padding-top: 20px;
}
.test_name_item_in .name_desc {
  color: #999;
  line-height: 18px;
  font-size: 14px;
  padding-left: 20px;
}
.test_name_item_in .name_btns {
  position: absolute;
  width: 100%;
  height: 30px;
  right: 0;
  bottom: 25px;
}
.test_name_item_in .name_btns > div {
  width: 92px;
  height: 30px;
  box-sizing: border-box;
  text-align: center;
  line-height: 28px;
  background-color: #ff3f47;
  border: 1px solid #f66;
  color: #fff;
  margin-right: 20px;
  border-radius: 3px;
  cursor: pointer;
  margin: auto;
}
</style>

