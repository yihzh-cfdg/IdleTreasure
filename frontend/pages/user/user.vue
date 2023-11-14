<template>
  <view class="mypage-container">
    <!-- 自定义头部 -->
    <view class="head">
		
    </view>
	
    <view class="mypage-top-box">
		<view class="mypage-header">
			<!-- 个人信息 -->
			<view class="userinfo" @click="userInformation">
					<!-- 用户头像 -->
				  <image class="avatar" :src="picture" mode=""></image>
				  
				  <!-- 用户信息 -->
				  <view class="top-text">
						<!-- 用户昵称 -->
						<view class="name">
							{{userinfo.username}}
						</view>
						<!-- 用户ID -->
						<view class="accountName">
							用户名:{{userinfo.userID}}
						</view>
						<!-- 用户关注数及粉丝数 -->
						<view class="fans">
							关注{{userinfo.follow}} &nbsp;|&nbsp; 粉丝{{userinfo.fans}}
						</view>
				  </view>
			</view>
				<!-- 设置 -->
				<view class="setting">
				  <uni-icons type="gear" size="25" @click="setting"></uni-icons>
				  <text class="text">设置</text>
				</view>
				
			</view>
			  <!-- 收藏订阅 -->
				<view class="userinfo-operate">
					<!-- 收藏 -->
					<view class="collection">
					  <uni-icons type="star" class="icon" size="30"></uni-icons>
					  <text>收藏:{{record.collect}}</text>
					</view>
					
					<!-- 足迹 -->
					<view class="footmark">
					  <uni-icons type="location" class="icon" size="30"></uni-icons>
					  <text>足迹:{{record.history}}</text>
					</view>
				</view>
    </view>

    <view class="mypage-content">
      <!-- 我的交易 -->
      <view class="my-order">
			<view class="my-order-text">
				  <view class="myorder">
					我的交易
				  </view>
				  <view class="Profile" style="color: grey;">
				  		总共赚了{{trade.profile}}元
				  </view>
				  			  
			  
			</view>
			
			<view class="my-order-icon">
			  <view class="my-order-operate">
					<!-- 我发布的 -->
					<view class="wait-pay" @click="clickpublish">
					  <uni-icons type="shop" class="icon" size="30"></uni-icons>
					  <text>我发布的&nbsp;{{trade.publish}}</text>
					</view>
					
					<!-- 我卖出的 -->
					<view class="wait-pay" @click="clicksell">
					  <uni-icons type="wallet" class="icon" size="33"></uni-icons>
					  <text>我卖出的&nbsp;{{trade.sell}}</text>
					</view>
					
					<!-- 我买到的 -->
					<view class="wait-pay"@click="clickbuy">
					  <uni-icons type="cart-filled" class="icon" size="30"></uni-icons>
					  <text>我买到的&nbsp;{{trade.buy}}</text>
					</view>
				
			  </view>
        </view>
      </view>
    </view>
	
  </view>
</template>

<script>
	
	export default {
		data() {
			return {	
				picture:"../../static/user.png",  //头像
				userinfo:{      //用户信息
					username:'土豆小狗',
					userID:'Potato_Dog',
					fans:9,
					follow:5,
				},
				record:{   //收藏与足迹
					collect:12,
					history:34,
				},
				trade:{   //我的交易
					profile:9999,
					publish:5,
					sell:4,
					buy:20,
				}
			}
		},
		onLoad() {
	
		},
		methods: {
			userInformation(){
				uni.navigateTo({
					url:'',//实现跳转用户信息
				})
			},
			setting(){
				uni.navigateTo({
							 url:'',
					});//实现跳转设置
				    
				},
			clicksell(){
				uni.navigateTo({
							 url:'',
					});//实现跳转我卖出的
				},
			clickpublish(){
				uni.navigateTo({
							 url:'',
					});//实现跳转我发布的
				},
			clickbuy(){
				uni.navigateTo({
							 url:'',
					});//实现跳转我买到的
				},
			clickcollect(){
				uni.navigateTo({
							 url:'',
					});//实现跳转收藏
				},
			clickhistory(){
				uni.navigateTo({
							 url:'',
					});//实现跳转足迹
				},
							
			
			
			
			
		}
	}
</script>




<style scoped>
  .head {
    height: 140rpx;
  }

  .mypage-container {
    background-color: #f7f7f7;
  }

  .mypage-header {
    display: flex;
    justify-content: space-between;
    font-size: 30rpx;
    padding: 0 30rpx 40rpx 0;
  }

  .userinfo {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 30rpx;
  }

  .mypage-top-box .avatar {
    width: 155rpx;
    height: 145rpx;
    border-radius: 90rpx;
    margin-right: 20rpx;
  }

  .top-text .name {
    font-size: 40rpx;
    margin-bottom: 20rpx;
    font-weight: bold;
  }

  .top-text .accountName {
    margin-bottom: 20rpx;
    font-size: 26rpx;
    color: #8f8c85;
  }

  .top-text .fans {
    font-size: 26rpx;
  }

  .setting {
    display: flex;
    flex-direction: column;
    font-size: 26rpx;
  }
  
  /* 个人信息-收藏等 */
  .userinfo-operate {
    display: flex;
    justify-content: space-between;
	background-color: #e6fcff;
    padding: 16rpx 120rpx;
    margin-top: 30rpx;
  }
content
  .userinfo-operate view {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 50rpx;
  }

  .userinfo-operate .icon {
    margin-bottom: 10rpx;
  }

  .mypage-content {
    margin: 0rpx 8rpx 0 8rpx;
  }

  .my-order {
    display: flex;
    flex-direction: column;
    background-color: #ffffff;
    padding: 26rpx 20rpx 26rpx 26rpx;
    /* border-radius: rpx; */
  }

  .my-order-text {
    display: flex;
    justify-content: space-between;
    margin-bottom: 32rpx;
  }

  .my-order-text .myorder {
    font-size:35rpx;
    font-weight: bold;
  }
  .my-order-operate {
    display: flex;
    justify-content: space-between;
    padding: 0 36rpx;
  }

  .my-order-operate view {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 26rpx;
  }

  .my-order-operate .wait-pay .icon {
    margin-bottom: 16rpx;
  }

</style>
