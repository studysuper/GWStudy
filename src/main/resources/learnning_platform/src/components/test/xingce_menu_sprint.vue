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
              {{item.desc}}
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
      testList:[{title:"2019年国家公务员考试《行测》真题（副省级）",desc:'共135题，总时间120分钟'},
      {title:"2018年国家公务员考试《行测》真题（副省级）",desc:'共135题，总时间120分钟'},
      {title:"2017年国家公务员考试《行测》真题（副省级）",desc:'共135题，总时间120分钟'},
      {title:"2016年国家公务员考试《行测》真题（副省级）",desc:'共135题，总时间120分钟'},
      {title:"2015年国家公务员考试《行测》真题（副省级）",desc:'共135题，总时间120分钟'}]
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

