import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);
// 用Vuex.Store对象用来记录token
const store = new Vuex.Store({
    state: {
        userInfo: {},
        hasLogin: false,
		baseUrl: "http://localhost:8083",
		hasInfo: false
    },
    mutations: {
        login(state, provider) {//改变登录状态  
            state.hasLogin = true
            state.userInfo.token = provider.token
            state.userInfo.userName = provider.user_name
            uni.setStorage({//将用户信息保存在本地  
                key: 'userInfo',
                data: provider
            })
        },
        logout(state) {//退出登录  
            state.hasLogin = false
			state.hasInfo = false
            state.userInfo = {}
            uni.removeStorage({
                key: 'userInfo'
            })
        },
		addUser(state, user){
			state.hasInfo = true
			state.userInfo.header = user.head_Portrait
			state.userInfo.username = user.user_Name
			state.userInfo.fzukey = user.fzu_Key
			state.userInfo.like = user.like_Count
			state.userInfo.belike = user.beliked_Count
		}
    }
})

export default store;