import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);
// 用Vuex.Store对象用来记录token
const store = new Vuex.Store({
	state: {
		userInfo: {},
		hasLogin: false,
		baseUrl: "http://47.110.148.166:8085",
		// baseUrl: "http://localhost:8083",
		hasInfo: false,
		token: 100000001,
	},
	mutations: {
		login(state, provider) { //改变登录状态  
			state.hasLogin = true;
			state.token = provider.token;
			state.userInfo.username = provider.user_name;
			uni.setStorage({ //将用户信息保存在本地  
				key: 'userInfo',
				data: provider
			});
		},
		logout(state) { //退出登录  
			state.userInfo = {},
				state.hasLogin = false,
				state.hasInfo = false,
				uni.removeStorage({
					key: 'userInfo'
				});
		},
		getUserInfo(state) {
			state.hasInfo = true;
			console.log("querying userinfo");
			console.log("token:" + state.token);
			uni.request({
				url: state.baseUrl + "/api/wxUser/get",
				method: "GET",
				data: {
					id: state.token
				},
				success: (res) => {
					if (res.data.code == 200) {
						state.userInfo = res.data.data;
						state.userInfo.token = state.token;
					} else {
						console.log("获取用户信息失败！");
						console.log(res.data);
					}
				},
				fail: (res) => {
					console.log("获取用户信息失败！");
					console.log(res);
				}
			})
		},
	}
})

export default store;