<template>
  <div>
    <div class="row">
      <div class="col my-level-title">
        <p class=" p-0 m-0">나의 운동 레벨</p>
      </div>
      <div class="col refresh">
        <!--eslint-disable-next-line-->
        <!-- <svg @click="refresh()" class="refresh-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"/><path d="M5.463 4.433A9.961 9.961 0 0 1 12 2c5.523 0 10 4.477 10 10 0 2.136-.67 4.116-1.81 5.74L17 12h3A8 8 0 0 0 6.46 6.228l-.997-1.795zm13.074 15.134A9.961 9.961 0 0 1 12 22C6.477 22 2 17.523 2 12c0-2.136.67-4.116 1.81-5.74L7 12H4a8 8 0 0 0 13.54 5.772l.997 1.795z" fill="rgba(130,130,130,1)"/></svg> -->
      </div>
    </div>
    <div class="my-level-info align-items-center">
      <div class="my-level-icon align-self-center">
        <img
          class="my-level-icon-img"
          :src="require(`@/assets/icon/tier${state.tierNum}.png`)"
          alt="tier1">
      </div>
      <div class="bar-info">
        <div class="progress bar">
          <div
            class="progress-bar progress-bar-striped progress-bar-animated"
            role="progressbar"
            aria-label="Example with label"
            v-bind:style="{ width: state.recordPercentage,
              backgroundColor: state.tierColor[state.tierNum-1] }"
            :aria-valuenow="state.recordPercent"
            aria-valuemin="0"
            aria-valuemax="100">{{state.recordPercentage}}
          </div>
        </div>
        <div class="tier-info d-flex text-center align-items-end">
          <div class="tier-name">{{ state.tierName }}&nbsp;&nbsp;</div>
          <div class="me-auto user-percent">
            LEVEL {{ state.level }}&nbsp;&nbsp;|&nbsp;&nbsp;
            전체 사용자 중 상위 {{pointPercent}}%</div>
          <div class="user-point">{{ state.pointSet }} / {{ state.levelUp }} P</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { useStore } from 'vuex';
import { computed, reactive } from 'vue';

export default {
  name: 'MyExerciseLevel',
  components: {},
  setup() {
    const store = useStore();
    const record = computed(() => store.getters['accounts/record']);
    const pointPercent = computed(() => store.getters['accounts/pointPercent']);
    const state = reactive({
      tierColor: [
        '#78583f', '#919191', '#f5d327', '#08fe8b', '#58eef1',
      ],
      tierNum: 5,
      tierName: '브론즈',
      pointSet: '',
      levelUp: '',
      recordPercent: '',
      recordPercentage: '',
      level: '',
    });
    const refresh = () => {
      store.dispatch('accounts/fetchPoint');
    };
    return {
      state,
      record,
      refresh,
      pointPercent,
    };
  },
  created() {
    let level = 0;
    let po = this.record.point;
    let lvup = 100;
    if (po > lvup) {
      while (po > lvup) {
        po -= lvup;
        lvup += 5;
        level += 1;
      }
    }
    this.state.pointSet = po;
    this.state.levelUp = lvup;
    this.state.level = level;

    if (this.state.pointSet > 0) {
      this.state.recordPercent = Math.round((this.state.pointSet / this.state.levelUp) * 100);
    } else {
      this.state.recordPercent = 0;
    }
    const stringPercent = String(this.state.recordPercent);
    this.state.recordPercentage = stringPercent.concat('%');
    // console.log('포인트 퍼센트', this.state.recordPercent, this.state.recordPercentage);

    // 티어 그림 변경
    console.log('포인트퍼센트', this.pointPercent);
    if (this.pointPercent > 0 && this.pointPercent < 20) {
      this.state.tierNum = 5;
      this.state.tierName = '다이아';
    } else if (this.pointPercent >= 20 && this.pointPercent < 40) {
      this.state.tierNum = 4;
      this.state.tierName = '플레티넘';
    } else if (this.pointPercent >= 40 && this.pointPercent < 60) {
      this.state.tierNum = 3;
      this.state.tierName = '골드';
    } else if (this.pointPercent >= 60 && this.pointPercent < 80) {
      this.state.tierNum = 2;
      this.state.tierName = '실버';
    } else if (this.pointPercent >= 0 && this.pointPercent <= 100) {
      this.state.tierNum = 1;
      this.state.tierName = '브론즈';
    }
  },
  moundted() {},
  unmounted() {},
  methods: {},
};
</script>

<style scoped>
  /* 나의 운동 레벨 */
.my-level {
  /* height: 150px; */
  border-bottom: solid #DFDFDF 1px;
}

.my-level-title {
  text-align: left;
  padding-top: 30px;
  font-size: 18px;
  padding-left: 40px;
  padding-bottom: 10px;
}
.refresh {
  margin-top: 30px;
  margin-right: 80px;
  padding: 0;
  /* padding-left: 40px; */
  /* padding-bottom: 10px; */
  font-size: 18px;
  text-align: end;
  /* border: solid black 1px; */
}
.refresh-icon:hover {
  cursor: pointer;
}

.my-level-info {
  width: 90%;
  display: flex;
  padding-left: 40px;
  height: 80px;

}

.user-percent {
  /* font-family: 'LeferiPoint-WhiteA'; */
  font-size: 12px;
  /* font-weight: 900; */
}

.my-level-icon {
  width: 100px;
}
.my-level-icon-img {
  width: 50px;
}
.bar-info {
  width: 100%;
  padding-left: 15px;
}

.tier-bar {
  background-color: #89621d;
}

.bar {
  width: 100%;
  height: 18px;
}

.progress-bar {
  font-size: 14px;
  text-shadow: 2px 2px 2px #2E2E2E;
  /* width: 90% */
}
.tier-info {
  padding-top: 5px;
  font-size: 12px;
}
.tier-name {
  padding-top: 0px;
  font-size: 16px;
}
</style>
