import { createStore } from 'vuex';
import { accounts } from '@/store/modules/accounts';
import { openvidu } from '@/store/modules/openvidu';

export default createStore({
  modules: {
    accounts,
    openvidu,
  },
});
