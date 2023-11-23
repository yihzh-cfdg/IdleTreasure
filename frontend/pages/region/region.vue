<template>
	<view class="content">

		<!-- 图片布局 -->
		<image class="img1" src="../../static/p2.png" style="height: 50px;" mode="aspectFit"></image>
		<image class="img2" src="../../static/p1.png" mode="aspectFit"></image>
		<image class="img4" src="../../static/p3.png" style="height: 190px;" mode="aspectFit"></image>
		<image class="img3" src="../../static/people.png" style="height: 300px;" mode="aspectFit"></image>



		<view class="Btn">

			<!-- 跳转登录 -->
			<view class="loginBtn1" @click="login()">
				<text class="btnValue" style="color: black;">登录</text>

			</view>
			<!-- 跳转注册 -->
			<view class="loginBtn2" @click="register()">
				<text class="btnValue" style="color: black;">注册</text>


			</view>
		</view>



	</view>
	</view>
</template>



<script>
	export default {
		data() {
			return {}
		},
		onLoad() {

		},
		methods: {
			login() {
				uni.navigateTo({
					url: '../login/login',
				});
				//实现跳转登录
			},


			register() {
				uni.navigateTo({
					url: '../register/register',
				});
				//实现跳转注册
			}
		}
	}
</script>



<style>
	.content {
		width: 100vw;
		height: 100vh;
		background-color: #9cebff;
	}

	.img1 {
		left: 10px;
		top: 100.45px;
		width: 126.78px;
		height: 61.81px;
		opacity: 1;
		/* transform: rotate(0.93deg); */

	}

	.img2 {
		left: -113px;
		top: 123px;
		width: 222px;
		height: 35px;
		opacity: 1;

	}

	.img3 {
		top: -50px;
		left: 30px;

		/* width: 537px; */
		justify-content: center;
		align-items: flex-end;

	}

	.img4 {
		left: 147px;
		top: -30px;
	}

	.Btn {
		padding: 0 100upx;
		margin-top: -50px;

	}

	.loginBtn1 {
		flex: 1;
		width: 108%;
		height: 100upx;
		background: #5bc3e3;
		border-radius: 50upx;
		margin-top: 20px;
		display: flex;
		justify-content: center;
		align-items: center;

	}

	.loginBtn2 {
		flex: 1;
		width: 108%;
		height: 100upx;
		background: #e8e8e8;
		border-radius: 50upx;
		margin-top: 20px;
		display: flex;
		justify-content: center;
		align-items: center;

	}
</style>