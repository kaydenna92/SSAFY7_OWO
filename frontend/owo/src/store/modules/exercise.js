// eslint-disable-next-line
export const exercise = {
  namespaced: true,
  state: () => ({
    exerciseName: undefined,
    allSquatCountList: [],
    allLungeCountList: [],
    allburpeeCountList: [],
  }),

  mutations: {
    CHANGE_EXERCISE_NAME: (state, payload) => {
      console.log('여기임', state.exerciseName, payload);
      state.exerciseName = payload;
    },
    CHANGE_SQUAT_COUNT_LIST: (state, payload) => {
      for (let i = 0; i < state.allSquatCountList.length; i += 1) {
        // eslint-disable-next-line
        if (state.allSquatCountList[i].connectionId == payload.connectionId) {
          state.allSquatCountList.splice(i, 1);
        }
      }
      state.allSquatCountList.push(payload);
    },
    CHANGE_LUNGE_COUNT_LIST: (state, payload) => {
      for (let i = 0; i < state.allLungeCountList.length; i += 1) {
        // eslint-disable-next-line
        if (state.allLungeCountList[i].connectionId == payload.connectionId) {
          state.allLungeCountList.splice(i, 1);
        }
      }
      state.allLungeCountList.push(payload);
    },
    CHANGE_BURPEE_COUNT_LIST: (state, payload) => {
      for (let i = 0; i < state.allburpeeCountList.length; i += 1) {
        // eslint-disable-next-line
        if (state.allburpeeCountList[i].connectionId == payload.connectionId) {
          state.allburpeeCountList.splice(i, 1);
        }
      }
      state.allburpeeCountList.push(payload);
    },
  },
  actions: {
    changeExerciseName({ commit }, payload) {
      commit('CHANGE_EXERCISE_NAME', payload);
    },
    changeSquatCountList({ commit }, payload) {
      commit('CHANGE_SQUAT_COUNT_LIST', payload);
    },
    changeLungeCountList({ commit }, payload) {
      commit('CHANGE_LUNGE_COUNT_LIST', payload);
    },
    changeBurpeeCountList({ commit }, payload) {
      commit('CHANGE_BURPEE_COUNT_LIST', payload);
    },
  },
};
