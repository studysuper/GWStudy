<template>
  <div>
    <div class="test_head">
      <div class="logo_box">
        <img
          src="../../assets/images/logo.png"
          alt=""
        >
      </div>
    </div>
    <div class="test_list_body">
      <div class="test_body_left">
        <div class="test_box">
          <div class="test_title">
            2019年国家公务员考试《行测》真题（市地级）
          </div>
          <div
            class="test_item_box"
            v-for="item in testList"
          >
            <p class="test_tit_p">
              {{item.num}}. {{item.title}}
            </p>
            <div class="test_answer">
              <p>A. {{item.ans1}}</p>
              <p>B. {{item.ans2}}</p>
              <p>C. {{item.ans3}}</p>
              <p>D. {{item.ans4}}</p>
            </div>
            <div class="test_choice">
              <el-radio-group v-model="radio2">
                <el-radio :label="3">A</el-radio>
                <el-radio :label="6">B</el-radio>
                <el-radio :label="9">C</el-radio>
                <el-radio :label="12">D</el-radio>
              </el-radio-group>
            </div>
          </div>
        </div>
      </div>
      <div class="test_body_right">
        <div class="clock_img">
          <img
            src="../../assets/images/clock.png"
            alt=""
          >
        </div>
        <div class="timer_box">
          <p>{{str}}</p>
        </div>
        <div class="btns_box">
          <div class="btn btn_stop" @click="stop">暂停</div>
          <div class="btn btn_after" @click="start">下次做</div>
          <div class="btn btn_sub">交卷</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      radio2: 3,
      h:0, 
      m:0,
      s:0,
      ms:0,
      time:0,
      str:'',
      mytime:'',
      testList: [
        {
          num: 1,
          title:
            "党的十八大以来，以习近平同志为核心的党中央，紧密结合新的时代条件和实践要求，以全新的视野深化对共产党执政规律，社会主义建设规律，人类社会发展规律的认识，创立了习近平新时代中国特色社会主义思想，其核心要义是：",
          ans1: "坚持和发展中国特色社会主义",
          ans2: "中国特色社会主义进入了新时代",
          ans3: "实现社会主义现代化和中华民族伟大复兴",
          ans4: "坚持以人民为中心的发展思想"
        },
        {
          num: 2,
          title:
            "党的十八大以来，以习近平同志为核心的党中央，紧密结合新的时代条件和实践要求，以全新的视野深化对共产党执政规律，社会主义建设规律，人类社会发展规律的认识，创立了习近平新时代中国特色社会主义思想，其核心要义是：",
          ans1: "坚持和发展中国特色社会主义",
          ans2: "中国特色社会主义进入了新时代",
          ans3: "实现社会主义现代化和中华民族伟大复兴",
          ans4: "坚持以人民为中心的发展思想"
        },
        {
          num: 3,
          title:
            "党的十八大以来，以习近平同志为核心的党中央，紧密结合新的时代条件和实践要求，以全新的视野深化对共产党执政规律，社会主义建设规律，人类社会发展规律的认识，创立了习近平新时代中国特色社会主义思想，其核心要义是：",
          ans1: "坚持和发展中国特色社会主义",
          ans2: "中国特色社会主义进入了新时代",
          ans3: "实现社会主义现代化和中华民族伟大复兴",
          ans4: "坚持以人民为中心的发展思想"
        },
        {
          num: 4,
          title:
            "党的十八大以来，以习近平同志为核心的党中央，紧密结合新的时代条件和实践要求，以全新的视野深化对共产党执政规律，社会主义建设规律，人类社会发展规律的认识，创立了习近平新时代中国特色社会主义思想，其核心要义是：",
          ans1: "坚持和发展中国特色社会主义",
          ans2: "中国特色社会主义进入了新时代",
          ans3: "实现社会主义现代化和中华民族伟大复兴",
          ans4: "坚持以人民为中心的发展思想"
        }
      ]
    };
  },
  mounted(){
      this.start();
  },
  methods:{
      timer(){
          this.ms = this.ms +50;
          if(this.ms>=1000){
              this.ms = 0;
              this.s = this.s + 1;
          }
          if(this.s>=60){
              this.s = 0;
              this.m = this.m + 1;
          }
          if(this.m>=60){
              this.m = 0;
              this.h = this.h + 1;
          }
          this.str = this.toDub(this.h) + ":" + this.toDub(this.m) + ":" + this.toDub(this.s);
      },
      reset(){
          clearInterval(this.time);
          this.h = 0;
          this.m = 0;
          this.s = 0;
          this.ms = 0;
          this.str = "00:00:00";
      },
      start(){
          this.time = setInterval(this.timer,50);
      },
      stop(){
          clearInterval(this.time);
      },
      toDub(n){
          if(n<10){
              return "0"+n;
          }else{
              return n;
          }
      }
  }
};
</script>
<style>
.test_head {
  width: 100%;
  height: 60px;
  border-bottom: 1px solid #cbcbcb;
}
.logo_box {
  width: 1190px;
  margin-left: 10%;
}
.logo_box img {
  width: 140px;
}
.test_list_body {
  width: 1190px;
  margin-left: 10%;
  overflow: hidden;
}
.test_body_left {
  float: left;
  width: 880px;
}
.test_box {
  padding-bottom: 20px;
}
.test_title {
  height: 40px;
  line-height: 40px;
  border-bottom: 1px solid;
}
.test_tit_p {
  font-size: 14px;
  line-height: 24px;
}
.test_answer p {
  font-size: 14px;
}
.test_choice {
  margin-top: 20px;
}
.test_body_right {
  float: right;
  width: 285px;
  border: 1px solid #cbcbcb;
  border-top: none;
}
.test_item_box {
  border-bottom: 1px dotted #cbcbcb;
  padding-bottom: 20px;
}
.clock_img {
  width: 100%;
  padding-top: 20px;
  padding-bottom: 20px;
  text-align: center;
}
.clock_img img {
  width: 114px;
}
.timer_box {
  width: 100%;
}
.timer_box p {
  text-align: center;
  font-size: 14px;
  color: #cbcbcb;
}
.btn {
  width: 245px;
  height: 32px;
  margin: auto;
  text-align: center;
  line-height: 32px;
  border-radius: 10px;
  font-size: 14px;
  margin-top: 10px;
  cursor: pointer;
}
.btn_stop,
.btn_after {
  border: 1px solid #409eff;
  color: #409eff;
}
.btn_sub {
  background: #409eff;
  color: #ffffff;
  margin-bottom: 20px;
}
</style>

