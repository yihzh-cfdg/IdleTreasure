<template>
	<view>
		<view class="tab-content">
			<slot />
		</view>	
		<view class="tabbar">
			<view class="navigator">
				<view class="navigator-item" v-for="(item,index) in tabBar.list" :key="item.pagePath">
					<view v-if="item.text != 'center'" @click="switchTab(item,index)">
						<view style="display: flex;justify-content: center;align-items: center;">
							<img :src="item.iconPath" class="icon" v-if="selectedIndex !== index">
							<img :src="item.selectedIconPath" class="icon" v-else>
						</view>
						<view class="itemCenter">
							<text>{{item.text}}</text>
						</view>
					</view>
					<view v-else style="width: 200rpx;" class="itemCenter" @click="switchTab(item,index)">
						<view style="background-color: #409ffd;width: 150rpx;height: 150rpx;border-radius: 50%;position: fixed;bottom: 2rpx;" class="itemCenter">
							<img :src="item.iconPath" style="width: 120rpx;height: 120;" >
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
				selectedIndex: uni.getStorageSync('selectedIndex') || 0,
				tabBar: {
					list: [{
							"pagePath": "pages/index/index",
							"iconPath": "../static/tabIcon.index.png",
							"selectedIconPath": "/static/tabIcon/index1.png",
							"text": "首页"
						}, {
							"pagePath": "pages/cart/cart",
							"iconPath": "/static/tabIcon/cart.png",
							"selectedIconPath": "/static/tabIcon/cart1.png",
							"text": "购物车"
						}, {
							"pagePath": "pages/add/add",
							"iconPath": "/static/tabIcon/add.png",
							"text": "center"
						}, {
							"pagePath": "pages/message/message",
							"iconPath": "/static/tabIcon/message.png",
							"selectedIconPath": "/static/tabIcon/message1.png",
							"text": "消息"
						},
						{
							"pagePath": "pages/my/my",
							"iconPath": "/static/tabIcon/my.png",
							"selectedIconPath": "/static/tabIcon/my1.png",
							"text": "我的"
						}
					]
				},
			}
		},
		methods: {
			switchTab(item, index) {
				let url = '/' + item.pagePath
				let pagePath = url
				uni.switchTab({url})
				this.tabBar.list.forEach((v, i) => {
					if (item.pagePath === v.pagePath) {
						uni.setStorageSync('selectedIndex', index);
					}
				})
			}
		},
	}
</script>
 
<style>
	.tabbar {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		height: 110rpx;
		z-index: 999;
		background: white;
		border-top: 2rpx solid #eee;
	}
 
	.navigator {
		display: flex;
		justify-content: space-between;
		width: 85%;
		margin: 0 auto;
		padding: 10rpx;
	}
 
	.navigator-item {
		display: flex;
		align-items: center;
		flex-direction: column;
		width: 50rpx;
		height: 100%;
	}
 
	.item-text {
		margin-top: 3rpx;
		color: black;
		font-weight: 600;
		font-size: 18rpx;
		width: 100rpx;
		text-align: center;
	}
 
 
	.icon {
		width: 60rpx;
		height: 60rpx;
	}
	.itemCenter{
		display: flex;
		justify-content: center;
		align-items: center;
	}
</style>