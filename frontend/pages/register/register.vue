<template>
	<view class="box">
		<view class="back">
		  <uni-icons type="back" size="30" style="margin-left: -45px;" @click="goBack"></uni-icons>
		</view>
		<view class="title">创建您的账户</view>
		<view class="form">
			<view class="image-container">
			  <image src="../../static/cat.png" mode="aspectFit" class="logo"></image>
			</view>
				<uni-easyinput class="input" placeholder="请输入用户名" v-model="username"></uni-easyinput>
				<uni-easyinput class="input" placeholder="请输入手机" v-model="phone"></uni-easyinput>
				<uni-easyinput class="input" placeholder="请输入学校" v-model="school"></uni-easyinput>
				<uni-easyinput class="input" placeholder="请输入学号" v-model="studentID"></uni-easyinput>
				<uni-easyinput class="input" type="password" placeholder="请输入密码" v-model="password"></uni-easyinput>			<uni-data-checkbox multiple v-model="checkbox1" :localdata="hobby"></uni-data-checkbox>
				<uni-data-checkbox multiple v-model="checkbox1" :localdata="terms"></uni-data-checkbox>
				<button class="btn star-button">闲置宝，启动！</button>
		</view>
	</view>
</template>

<script>

	
		export default {
				data() {
					 return {
						
							username: '',
					        phone: '',
					        school: '',
					        studentID: '',
					        password: '',
							checkbox1: [0],
							terms: [{
												text: '您是否同意该条款与政策',
												value: 0
											}],
					        }
		
				},
				onLoad() {},
				onReady() {},
				methods: {
						updateUsername(e) {
						    this.username = e.target.value;
						  },
						updatePhone(e) {
						    this.phone = e.target.value;
						  },
						updateSchool(e) {
						    this.school = e.target.value;
						  },
						updateStudentID(e) {
						    this.studentID = e.target.value;
						  },
						updatePassword(e) {
						    this.password = e.target.value;
						  },
						goBack() {
							uni.navigateBack();
						}
				}
			}
</script>

<style>
	.box{
		background: #9CEBFF;
		padding: 0 100upx;
		position: relative;
		 height: 100vh;
	}
	.back{	
		background: rgba(91, 195, 227, 0);
		font-size: 36upx;
		color: #FFFFFF;
		border: none;
	}
	/*调整字体*/
	.title {
		margin-top:70upx;
		text-align: center;
		font-size: 35px;
		font-family: '阿里巴巴普惠体';
		font-weight: bold;
		color: #333;
	}
	.form{
		background: #9CEBFF;
		margin-top: 100upx;
	}
	.image-container {
		display: flex;
		justify-content: center;
		margin-bottom: 30upx;
	}
	.logo {
		width: 100px;
		height: 100px;
	}
	/*输入框样式*/
	.input {	
		margin-bottom: 30upx;
		opacity:0.7;
	}
	/*调整字体*/
	.star-button {
		font-family: '阿里巴巴普惠体', sans-serif;
		font-size: 18px;
		font-weight: bold;
		color: #333;
	}
	.btn{
		background: #5BC3E3;
		color: #333;
		border: 0;
		border-radius: 100upx;
		font-size: 36upx;
		margin-top: 60upx;
	}
	.btn:after{
		border: 0;
	}
	/*按钮点击效果*/
	.btn.button-hover{
		transform: translate(1upx, 1upx);
	}
</style>
