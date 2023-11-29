<template>
	<view>
		<view>
			<view style="display: flex;align-items: center;margin: 20rpx;height: 52rpx;">
				<img src="../../static/back.png" style="width: 40rpx;height: 40rpx;">
				<view style="flex: 1;">
					<view @click="changeIsClick()"
						style="background-color: white;border: 1px solid black;height: 52rpx;border-radius: 30rpx;padding: 0 20rpx;display: flex;align-items: center;">
						<view v-if="isClick == 1 && inputValue.length > 0"
							style="background-color: rgb(246, 246, 246);border-radius: 10rpx;padding: 0 10rpx;display: flex;align-items: center;">
							{{inputValue}}
							<icon type="clear" size="16" style="margin-left: 10px;" @click="clearIcon" />
						</view>
						<view v-if="isClick == 0" class="uni-input-wrapper">
							<input class="uni-input" focus :value="inputValue" @input="changeInput"
								@blur="handleBlur" />
						</view>
					</view>
				</view>
			</view>
		</view>
		<view>
			<view style="display: flex;justify-content: space-between;padding: 20rpx 100rpx;font-size: 40rpx;">
				<view @click="changeCurrent(0)" :style="{ fontWeight: current === 0 ? 'bold' : 'normal' }">商品</view>
				<view style="display: flex;align-items: center;">
					<view style="margin-right: 10rpx;">价格</view>
					<img src="../../static/jiantou.png" style="width: 40rpx;height: 40rpx;">
				</view>
				<view @click="changeCurrent(1)" :style="{ fontWeight: current === 1 ? 'bold' : 'normal' }">用户</view>
				<view style="display: flex;align-items: center;">
					<view style="margin-right: 10rpx;">筛选</view>
					<img src="../../static/saixuan-black.png" style="width: 40rpx;height: 40rpx;">
				</view>
			</view>
		</view>

		<view style="display: flex;flex-wrap: wrap;" v-if="current == 0">
			<view v-for="item,index in list" style="width: 50%;" @click="navToDetail(item)">
				<view style="padding: 10px;">
					<view><img :src="item.image"
							style="width: 100%;height: 450rpx;object-fit: cover;border-radius: 20rpx;"></view>
					<view class="line-view">{{item.title}}</view>
					<view style="color: red;margin-top: 10rpx;">￥{{item.price}}</view>
					<view style="display: flex;align-items: center;margin-top: 10rpx;">
						<img :src="item.image" style="width: 50rpx;height: 50rpx;border-radius: 50%;object-fit: cover">
						<view style="margin-left: 10rpx;">{{item.empName}}</view>
					</view>
				</view>
			</view>
		</view>
		<view v-if="current == 1" style="background-color: white;">
			<view v-for="item,index in list">
				<view style="display: flex;padding: 30rpx;">
					<view><img :src="item.header"
							style="width: 120rpx;height: 120rpx;border-radius: 50%;object-fit: cover"></view>
					<view style="margin-left: 30rpx;font-size: 18rpx;">
						<view style="font-size: 32rpx;font-weight: bold;">{{item.empName}}</view>
						<view style="color: #CCC;">闲置编号：1111122222</view>
						<view style="color: #CCC;">发布： 5 | 粉丝： 50</view>
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
				inputValue: '123',
				isClick: 1,
				showClearIcon: false,
				current: 0,
				hasItem: true,
				hasUser: true,
				list: [],
			}
		},
		created() {
			if (this.inputValue > 0) {
				this.showClearIcon = true;
			}
		},
		onLoad: function(opt) {
			if (opt.value) {
				const word = opt.value;
				this.inputValue = word;
				uni.request({
					url: this.$store.state.baseUrl + "/api/goods/search",
					data: {
						value: word
					},
					success: (res) => {
						this.list = [];
						for (let item of res.data.data) {
							let newitem = {
								id: item.goods_Id,
								empName: item.user_Name,
								price: item.goods_Price,
								title: item.goods_Name,
								type: 1,
								wantNum: 0,
								image: item.image_Data,
								header: item.head_Portrait
							};
							this.list.push(newitem);
						}
					}
				})
			}
		},
		methods: {
			navToDetail(item) {
				console.log(item);
				uni.navigateTo({
					url: '/pages/product-details/product-details?item=' + encodeURIComponent(JSON.stringify(item))
				});
			},
			changeIsClick() {
				this.isClick = 0
			},
			handleBlur() {
				this.isClick = 1
			},
			changeInput: function(event) {
				this.inputValue = event.detail.value;
			},
			clearIcon() {
				this.inputValue = '';
				this.showClearIcon = false;
			},
			changeCurrent(index) {
				this.current = index
			}
		}
	}
</script>

<style scoped>
	.line-view {
		display: -webkit-box;
		-webkit-line-clamp: 1;
		-webkit-box-orient: vertical;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.uni-input-wrapper {
		display: flex;
		align-items: center;
		flex-direction: row;
		flex-wrap: nowrap;
		background-color: #FFFFFF;
	}

	.uni-input {
		flex: 1;
		background-color: #FFFFFF;
	}
</style>