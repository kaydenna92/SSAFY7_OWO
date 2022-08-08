<template>
  <div class="container">
    <div class="circle">
      <svg width="300" viewBox="0 0 220 220" xmlns="http://www.w3.org/2000/svg">
      <g transform="translate(110,110)">
        <circle r="100" class="e-c-base"/>
        <g transform="rotate(-90)">
          <circle r="100" class="e-c-progress"/>
          <g id="e-pointer">
            <circle cx="100" cy="0" r="8" class="e-c-pointer"/>
          </g>
        </g>
      </g>
      </svg>
    </div>
    <div class="controlls">
      <div id="display-remain-time">{{timesetting}}</div>
      <button class="play" id="pause"></button>
    </div>
    <button @click="pauseTimer">버튼임</button>
    <!-- 인라인 스타일로 넣어야할듯? -->
  </div>
</template>

<script>
export default {
  name: 'ATest',
  components: {
  },
  data() {
    return {
      intervalTimer: null,
      timeLeft: null,
      wholeTime: 0.5 * 200,
      isPaused: false,
      isStarted: false,
      length: Math.PI * 2 * 100,
      timesetting: '01:00',
    };
  },
  setup() {},
  created() {},
  moundted() {},
  unmounted() {},
  methods: {
    pauseTimer() {
      // circle start
      const progressBar = document.querySelector('.e-c-progress');
      console.log(progressBar);
      progressBar.style.strokeDasharray = this.length;
      if (this.isStarted === false) {
        this.timer(this.wholeTime);
        this.isStarted = true;
      }
    },
    update(value, timePercent) {
      const offset = (+this.length) - (this.length * value) / (timePercent);
      this.progressBar.style.strokeDashoffset = offset;
      this.pointer.style.transform = `rotate(${(360 * value) / (timePercent)}deg)`;
    },
    displayTimeLeft(timeLeft) { // displays time on the input
      const minutes = Math.floor(timeLeft / 60);
      const seconds = timeLeft % 60;
      const displayString = `${minutes < 10 ? '0' : ''}${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
      this.timesetting = displayString;
      this.update(timeLeft, this.wholeTime);
    },
    timer(seconds) { // counts time, takes seconds
      const remainTime = Date.now() + (seconds * 1000);
      this.displayTimeLeft(seconds);
      this.intervalTimer = setInterval(() => {
        this.timeLeft = Math.round((remainTime - Date.now()) / 1000);
        if (this.timeLeft < 0) {
          clearInterval(this.intervalTimer);
          this.isStarted = false;
          this.displayTimeLeft(this.wholeTime);
          return;
        }
        this.displayTimeLeft(this.timeLeft);
      }, 1000);
    },
  },
};
// update(wholeTime, wholeTime); // refreshes progress bar
// displayTimeLeft(wholeTime);
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto:100,300');

button[data-setter] {
  outline: none;
  background: transparent;
  border: none;
  font-family: 'Roboto';
  font-weight: 300;
  font-size: 18px;
  width: 25px;
  height: 30px;
  color: #4e8aff;
  cursor: pointer;
}

button[data-setter]:hover { opacity: 0.5; }

.container {
  position: relative;
  top: 30px;
  width: 300px;
  margin: 0 auto;
}

.setters {
  position: absolute;
  left: 85px;
  top: 75px;
}

.minutes-set {
  float: left;
  margin-right: 28px;
}

.seconds-set { float: right; }

.controlls {
  position: absolute;
  left: 75px;
  top: 105px;
  text-align: center;
}

.display-remain-time {
  font-family: 'Roboto';
  font-weight: 100;
  font-size: 65px;
  color: #4e8aff;
}

#pause {
  outline: none;
  background: transparent;
  border: none;
  margin-top: 10px;
  width: 50px;
  height: 50px;
  position: relative;
}

.play::before {
  display: block;
  content: "";
  position: absolute;
  top: 8px;
  left: 16px;
  border-top: 15px solid transparent;
  border-bottom: 15px solid transparent;
  border-left: 22px solid #4e8aff;
}

.pause::after {
  content: "";
  position: absolute;
  top: 8px;
  left: 12px;
  width: 15px;
  height: 30px;
  background-color: transparent;
  border-radius: 1px;
  border: 5px solid #4e8aff;
  border-top: none;
  border-bottom: none;
}

#pause:hover { opacity: 0.8; }

.e-c-base {
  fill: none;
  stroke: #B6B6B6;
  stroke-width: 4px
}

.e-c-progress {
  fill: none;
  stroke: #4e8aff;
  stroke-width: 4px;
  transition: stroke-dashoffset 0.7s;
}

.e-c-pointer {
  fill: #FFF;
  stroke: #4e8aff;
  stroke-width: 2px;
}

#e-pointer { transition: transform 0.7s; }
h1 { margin-top:150px; text-align:center;}
body { background-color:#f7f7f7;}
</style>
