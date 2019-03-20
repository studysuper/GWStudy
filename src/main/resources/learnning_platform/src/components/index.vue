<template>
  <div class="main">
    <img src="../assets/images/banner.png">
    <div class="nav_menu">
      <headBar></headBar>
    </div>
    <div class="content">
      <div class="con_left">
        <!-- <canvas id="canvas" width="500" height="500"></canvas> -->
        <div class="time_show">
          <p>{{this.nowdays.today}}</p>
          <p>星期{{this.nowdays.date}}</p>
          <p>{{this.nowdays.time}}</p>
        </div>
        <div class="time_tip">
          <p>今天是你复习考试的第88天</p>
          <p>距离考试还有22天</p>
        </div>
      </div>
      <div class="con_mid">
        <el-carousel
          :interval="5000"
          arrow="always"
        >
          <el-carousel-item
            v-for="(item,index) in newImg"
            :key="index"
          >
            <img class="new_img" :src="item.img">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="con_right">
        <h2>找资讯</h2>
        <p>江苏 | 2019扬州市江都区卫生健康委员会所属事业单位招聘医疗岗位（69人）</p>
        <p>四川 | 2019年四川省粮食和物资储备局直属事业单位招聘教师6名公告</p>
        <p>湖南 | 2019湖南益阳市妇幼保健院公开招聘公告（32人）</p>
        <p>江苏 | 2019年江苏常州市、区属事业单位统考招聘114人公告</p>
        <p>北京 | 2019年中国林科院第二批招聘118人公告</p>
      </div>
    </div>
    <footerBar></footerBar>
  </div>
</template>

<script>
import img1 from '../assets/images/new1.png'
import img2 from '../assets/images/new2.png'
import headBar from '@/components/nav/header'
import footerBar from '@/components/nav/footer'
export default {
  data() {
    return {
      nowdays: "",
      newImg:[{img:img1},{img:img2}]
    };
  },
  components: {
    headBar,
    footerBar
  },
  mounted() {
    this.timer = setInterval(() => {
      this.nowdays = this.showTime();
    }, 50);
  },
  beforeDestroy() {
    clearInterval(this.timer);
  },
  methods: {
    showTime() {
      var day;
      var date;
      var now = new Date();
      var today = now.toLocaleDateString();
      var time = now.toLocaleTimeString();
      var day = now.getDay();
      var hrs = now.getHours();
      var min = now.getMinutes();
      var sec = now.getSeconds();
      var mil = now.getMilliseconds();
      var smoothsec = sec + mil / 1000;
      var smoothmin = min + smoothsec / 60;
      var hours = hrs + smoothmin / 60;
      var milliseconds = smoothsec;
      var minutes = smoothmin;
      var hour = hours;
      switch (day) {
        case 1:
          date = "一";
          break;
        case 2:
          date = "二";
          break;
        case 3:
          date = "三";
          break;
        case 4:
          date = "四";
          break;
        case 5:
          date = "五";
          break;
        case 6:
          date = "六";
          break;
        case 0:
          date = "日";
          break;
      }
      var arr = { today: today, date: date, time: time };

      return arr;
    }
  }
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main {
  width: 100%;
}
.nav_menu {
  width: 80%;
  margin-left: 10%;
}
.content {
  width: 80%;
  height: 320px;
  padding-top: 10px;
  margin-left: 10%;
}
.con_left {
  width: 30%;
  height: 300px;
  text-align: center;
  display: inline-block;
  background: #eeeeee;
  border-radius: 20px;
  margin-left: 0;
  float: left;
}
.time_show {
  width: 150px;
  height: 150px;
  background: url("../assets/images/clock_bg.png") no-repeat;
  background-size: 100% 100%;
  margin: 20px auto;
}
.time_show p {
  font-size: 14px;
  height: 10px;
}
.time_show p:first-child {
  padding-top: 40px;
}
.time_tip {
  padding-top: 20px;
}
.time_tip p {
  font-size: 18px;
  height: 10px;
}
.con_mid {
  width: 38%;
  height: 320px;
  display: inline-block;
  float: left;
  margin-left: 1%;
  margin-right: 1%;

}
.new_img {
  width: 100%;
  height: 100%;
}
.con_right {
  width: 30%;
  height: 300px;
  background: #eeeeee;
  display: inline-block;
  float: left;
  border-radius: 10px;
}
.con_right h2 {
  font-size: 18px;
  font-weight: 600;
  text-align: left;
  margin-left: 10px
}
.con_right p {
  font-size: 14px;
  text-align: left;
  margin-left: 10px;
}

</style>
