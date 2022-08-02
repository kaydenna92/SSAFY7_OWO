import router from '@/router';
import axios from 'axios';
// eslint-disable-next-line
export const accounts = {
  namespaced: true,
  state: () => ({
    isLogin: false,
    isLoginErr: false,
    accessToken: null,
    refreshToken: null,
    userInfo: {
      nick: '',
      gender: '',
      age: '',
      height: '',
      weight: '',
      activityNum: '',
      activityHour: '',
      activityLevel: '',
      id: '',
    },
    physicalInfo: {
      bmi: null,
      bmr: null,
      caloriePerDay: null,
    },
  }),
  mutations: {
    SET_TOKEN: (state, token) => {
      console.log('set_token_mutation');
      state.accessToken = token.accesstoken;
      state.refreshToken = token.refreshtoken;
      console.log(state.accessToken);
    },
    SET_USER_INFO: (state, payload) => {
      state.userInfo.id = payload.id;
      state.userInfo.nick = payload.nick;
      state.userInfo.gender = payload.gender;
      state.userInfo.age = payload.age;
      state.userInfo.height = payload.height;
      state.userInfo.weight = payload.weight;
      state.userInfo.activityNum = payload.activityNum;
      state.userInfo.activityHour = payload.activityHour;
      state.userInfo.activityLevel = payload.activityLevel;
    },
    SET_PHYSICAL_INFO: (state, payload) => {
      state.physicalInfo.bmi = payload.bmi;
      state.physicalInfo.bmr = payload.bmr;
      state.physicalInfo.caloriePerDay = payload.caloriePerDay;
    },
  },
  actions: {
    saveToken({ commit }, token) {
      console.log('saveToken');
      commit('SET_TOKEN', token);
      localStorage.setItem('accessToken', token.accesstoken);
      localStorage.setItem('refreshToken', token.refreshtoken);
    },
    removeToken({ commit }) {
      console.log('removeTOken');
      commit('SET_TOKEN', null);
      localStorage.setItem('accessToken', null);
      localStorage.setItem('refreshToken', null);
    },
    setUserInfo({ commit }, payload) {
      commit('SET_USER_INFO', payload);
    },
    login({ dispatch }, credentials) {
      axios.post('https://i7c202.p.ssafy.io:8282/auth/login', credentials)
        .then((res) => {
          const token = {
            accesstoken: res.data.data.accessToken,
            refreshtoken: res.data.data.refreshToken,
          };
          console.log(token);
          dispatch('saveToken', token);
          dispatch('setUserInfo', res.data.data);
          router.push('/');
          console.log(res);
        })
        .catch((err) => {
          console.log('에러', err);
        });
    },
    setPhysicalInfo({ commit }, payload) {
      commit('SET_PHYSICAL_INFO', payload);
    },
    logout({ dispatch, state }) {
      axios.get('https://i7c202.p.ssafy.io:8282/api/logout', state.refreshToken)
        .then(() => {
          dispatch('removeToken');
          alert('로그아웃');
          router.push('/');
        })
        .catch((err) => {
          console.log('에러');
          console.log(err);
        });
    },
    physicalInfo({ dispatch, state }, physicalInfo) {
      axios.get(`https://i7c202.p.ssafy.io:8282/api/user/bmi/${state.userInfo.id}`, physicalInfo)
        .then((res) => {
          console.log(res);
          dispatch('setPhysicalInfo', res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  getters: {
    isLogin: (state) => state.accessToken,
    userInfo: (state) => state.userInfo,
    physicalInfo: (state) => state.physicalInfo,
  },
};
