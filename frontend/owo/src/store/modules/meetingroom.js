import router from "@/router";
import axios from "axios";
// eslint-disable-next-line
export const meetingroom = {
  namespaced: true,
  state: {
    mySessionId: "12341234",
    meetingRoomList: [],
  },
  mutations: {
    SET_SESSION_ID: (state, mySessionId) => {
      state.mySessionId = mySessionId;
    },
    SET_MEETING_ROOM_LIST: (state, list) => {
      state.meetingRoomList = list;
    },
  },
  actions: {
    makeSession({ commit }, requestDto) {
      console.log(requestDto);

      axios({
        url: "http://localhost:9000/api/room",
        method: "post",
        data: {
          memberId: requestDto.memberId,
          secret: requestDto.secret,
          password: requestDto.password,
          mode: requestDto.mode,
          roomName: requestDto.roomName,
          type: requestDto.type,
          link: "~~",
        },
        headers: {
          "X-AUTH-TOKEN": requestDto.accesstoken,
        },
      })
        .then((res) => {
          console.log(res.data);
          const roomId = res.data.data.roomId;
          commit("SET_SESSION_ID", roomId);
          router.push("/competition");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getMeetingRoomList({ commit }, requestDto) {
      axios({
        url: `http://localhost:9000/room/${requestDto.mode}`,
        method: "get",
        headers: {
          "X-AUTH-TOKEN": requestDto.accesstoken,
        },
      })
        .then((res) => {
          console.log(res.data);
          const list = res.data.data;
          commit("SET_MEETING_ROOM_LIST", list);
          router.push("/competition");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    enterMeetingRoom({ commit }, requestDto) {
      axios({
        url: `http://localhost:9000/api/room/${requestDto.roomId}`,
        method: "get",
        headers: {
          "X-AUTH-TOKEN": requestDto.accesstoken,
        },
      })
        .then((res) => {
          console.log(res.data);
          commit("SET_SESSION_ID", requestDto.roomId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    leaveMeetingRoom({ commit }, requestDto) {
      axios({
        url: `http://localhost:9000/api/room/exit/${requestDto.roomId}`,
        method: "put",
        headers: {
          "X-AUTH-TOKEN": requestDto.accesstoken,
        },
      })
        .then((res) => {
          console.log(res.data);
          commit("SET_SESSION_ID", null);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  //   getters: {
  //     isLogin: (state) => state.accessToken,
  //     userInfo: (state) => state.userInfo,
  //   },
};