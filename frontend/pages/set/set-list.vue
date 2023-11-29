<template>
	<view class="container">
		<view class="navigation-bar">
		<!-- 导航栏 -->
			<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx">
				<block slot="left">
					<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em" >设置</text>
				</block>
			</uni-nav-bar>		
		</view>
		
		<!--头像设置-->
		<view class="avatar-set">
			<uni-list-chat :avatar-circle="true" 
			:title="userInfo.name" :avatar="userInfo.avatar" 
			:note="userInfo.account"clickable link to="/pages/set/info-set">
				<uni-icons class="next-icons" size="17" type="right" color="#bbbec6" 
				style="margin-right:-5px;margin-top:10px;"></uni-icons>
			</uni-list-chat>

		</view>	
		
		<!--其他设置-->
		<uni-list border-full class="other-set">
			<!--收货地址-->
			<uni-list-item showArrow title="收货地址" clickable link to="/pages/set/address-set"/>
			<!--支付
			<uni-list-item showArrow title="支付设置" clickable/>
			设置-->
			<!--我们-->
			<uni-list-item showArrow title="我们" clickable link to="/pages/set/about"/>
			<!--退出登录-->
			<uni-list-item showArrow title="退出登录" clickable @click="logOut"/>
		</uni-list>
	</view>

</template>

<script>
	import {
		mapMutations
	} from "vuex";
	export default {
		data() {
			return {
				//fake data
				userInfo: {
					name: "FZU_1234", 
					avatar: "../../static/L19R5LODHPK2]1@[M9SS94K.png", 
					account: "102101666"
				  }
			};
		},
		methods:{
			...mapMutations(["logout"]),
			goBack(){
				uni.navigateBack();
			},
			logOut(){
				let that = this;
				//退出登录
				uni.showModal({
					title: '提示',
					content: '确认退出登录？',
					success: function(res) {
						if (res.confirm) {
							that.logout();
							uni.removeStorage({
								key: 'token',
								success(res) {
									uni.redirectTo({
										url: '/pages/region/region'//跳转到登录页面
									})
								}
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			}
			
		}
	};
</script>

<style lang="scss">
	.navi-header-text{
		color: #000;
		/* 设置文字颜色 */
	}
</style>

