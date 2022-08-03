import { createStore } from 'vuex';
import { accounts } from '@/store/modules/accounts';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  modules: {
    accounts,
  },
  plugins: [createPersistedState({
    paths: ['accounts'],
  })],
});
