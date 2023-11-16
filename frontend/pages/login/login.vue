<template>
	<view class="content">
		<view class="avatorWrapper">
			<view class="avator">
				<!-- 图标 -->
				<image class="img" src="../../static/cat.png" mode="aspectFit"></image>
			</view>
		</view>
		
		<view class="form">
			<uni-forms  ref="form" :modelValue="formData" :rules="rules">
				<uni-forms-item name="iphone">
					<input class="input" type="text" v-model="formData.iphone" placeholder="请输入用户名/手机号" />
					
				</uni-forms-item>
				
				<uni-forms-item name="password"  >
					<input  class="input" type="password"  v-model="formData.password" placeholder="请输入密码" />
				</uni-forms-item>
			</uni-forms>
		
			<view class="loginBtn" @click="submit">
				<text class="btnValue" style="color: black;">登录</text>
			</view>
		</view>
		
		<view class="inputArea">
			<!-- 跳转注册界面 -->
			<navigator url="../register/register" hover-class="none" class="label">新用户注册</navigator>
		</view>
		
	</view>
	</view>
</template>



<script>
	export default {
		data() {
			return {
							
					formData: {
						name:'',
						iphone: '',
						password:'',
						
						
				},
				rules: {   
					
				}
			}
		},
		onLoad() {

		},
		methods: {
			
			submit() {
			          //实现提交数据
			       },
						
			
			
			login(e) {
	
				
			},




		}
	}
</script>



<style>
	.content {
		width: 100vw;
		height: 100vh;
		background-color: #9cebff;
	}

	.avatorWrapper {
		//margin-top: 16px;
		height: 30vh;
		width: 100vw;
		display: flex;
		justify-content: center;
		align-items: flex-end;
	}

	.avator {
		width: 300upx;
		height: 300upx;
		overflow: hidden;
	}

	.avator .img {
		width: 100%
	}

	.form {
		padding: 0 100upx;
		margin-top: 40px;
	}

	.input {
		flex: 1;
		/* margin-top: 20px; */
		font-size: 16px;
		width: 100%;
		height: 70upx;
		color: #666;
		border: 1px #e5e5e5 solid;
		border-radius:50px;
		padding: 10px;
		background-color: #ffffff;
		/* height: 300upx; */
		
	}
	.psd{
		margin-top: 8px;
		
	
	}
	
	.loginBtn {
		flex:1;
		width: 108%;
		height: 100upx;
		background: #5bc3e3;
		border-radius: 50upx;
		margin-top: 50px;
		display: flex;
		justify-content: center;
		align-items: center;

	}
	.icon{
		/* margin-top: 200px; */
		margin-left: 240px;
		margin-top: -20px;
		/* margin-top: 10px; */
		/* background: rgba(8, 8, 10, 0.96); */

	}
	.inputArea {
		padding: 20upx 10%;
		
	}
	.img{
		height: 120px;
		width: 120px;
	}
	.label{
		padding: 10upx 0;
		text-align: right;
		font-size: 30upx;
		color: #ffffff;
	}
</style>
