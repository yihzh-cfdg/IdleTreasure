<template>
	<view class="container">
		<!-- 顶部导航栏 -->
		<uni-nav-bar dark :fixed="true" background-color="#f5f5f5" status-bar left-width="250rpx">
			<block slot="left">
				<uni-icons @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
				<text class="navi-header-text" style="font-size:1.15em">订单列表</text>
			</block>
		</uni-nav-bar>

		<!-- 订单列表 -->
		<view class="order-list" v-for="(order, index) in orders" :key="index">
			<!-- 卖家名和订单状态 -->
			<view class="order-info">
				<view class="user-info">
					<img :src="order.avatar" style="width: 50rpx;height: 50rpx;border-radius: 50%;object-fit: cover">
					<text class="seller">{{ order.seller }}</text>
				</view>
				<text class="transaction-status">{{ order.transactionStatus }}</text>
			</view>
			<view class="order-details" @click="toOrderDetail(order)">
				<!-- 订单商品图片 -->
				<image :src="order.productImage" class="product-image"></image>
				<!-- 订单商品描述和价格 -->
				<text class="product-description">{{ order.productDescription }}</text>
				<text class="product-price">¥{{ order.productPrice }}</text>
			</view>
			<!-- 我买到的 -->
			<view class="buttons" v-if="type==3">
				<button class="contact-seller-button" @click="contactSeller()">联系卖家</button>
				<button v-if="order.transactionStatus=='已评价'" class="review-button"
					@click="reviewOrder(order, 1)">查看评价</button>
				<button v-else-if="order.transactionStatus=='交易成功'" class="review-button"
					@click="reviewOrder(order, 2)">去评价</button>
				<button v-else-if="order.transactionStatus=='已发货'" class="review-button"
					@click="reviewOrder(order, 3)">确认收货</button>
				<button v-else-if="order.transactionStatus=='已拍下'" class="review-button"
					@click="reviewOrder(order, 4)">去付款</button>
			</view>
			<!-- 我卖出的 -->
			<view class="buttons" v-else-if="type==1">
				<button class="contact-seller-button" @click="contactSeller()">联系买家</button>
				<button v-if="order.transactionStatus=='已评价'" class="review-button"
					@click="reviewOrder(order, 1)">查看评价</button>
				<button v-else-if="order.transactionStatus=='交易成功'" class="review-button"
					@click="reviewOrder(order, 2)">去评价</button>
				<button v-else-if="order.transactionStatus=='已发货'" class="review-button"
					@click="reviewOrder(order, 3)">等待收货</button>
				<button v-else-if="order.transactionStatus=='已拍下'" class="review-button"
					@click="reviewOrder(order, 4)">等待买家付款</button>
			</view>
		</view>
	</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				type: 1,
				orders: [{
						orderid: '20230101',
						seller: '卖家A',
						avatar: "../../static/cat.png",
						productImage: '../../static/L19R5LODHPK2]1@[M9SS94K.png',
						productDescription: '鞋子',
						productPrice: 100.5,
						transactionStatus: '交易成功'
					},
					{
						orderid: '20230101',
						seller: '卖家A',
						avatar: "../../static/cat.png",
						productImage: '../../static/L19R5LODHPK2]1@[M9SS94K.png',
						productDescription: '鞋子',
						productPrice: 100.5,
						transactionStatus: '交易成功'
					},
				]
			};
		},
		onLoad: function(opt) {
			let that = this;
			that.orders = [];
			that.type = opt.type;
			if (that.type == "1") {
				// 我卖出的
				uni.request({
					url: that.$store.state.baseUrl + "/api/trades/seller",
					method: "GET",
					data: {
						sellerID: that.$store.state.userInfo.token
					},
					success: function(res) {
						if (res.data.code == 200) {
							for (let i of res.data.data) {
								that.orders.push(i);
							}
						} else {
							uni.showToast({
								title: "获取失败"
							});
						}
					}
				});
			} else if (that.type == "2") {
				// 我发布的
				uni.request({
					url: that.$store.state.baseUrl + "/api/trades/publish",
					method: "GET",
					data: {
						publisherID: that.$store.state.userInfo.token
						// publisherID: 100000003
					},
					success: function(res) {
						if (res.data.code == 200) {
							for (let i of res.data.data) {
								that.orders.push(i);
								console.log(i);
							}
						} else {
							uni.showToast({
								title: "获取失败"
							});
						}
					}
				});
			} else if (that.type == "3") {
				// 我买到的
				uni.request({
					url: that.$store.state.baseUrl + "/api/trades/buyer",
					method: "GET",
					data: {
						buyerID: that.$store.state.userInfo.token
					},
					success: function(res) {
						if (res.data.code == 200) {
							for (let i of res.data.data) {
								that.orders.push(i);
							}
						} else {
							uni.showToast({
								title: "获取失败"
							});
						}
					}
				});
			}
		},
		methods: {
			goBack() {
				uni.navigateBack();
			},
			contactSeller() {
				// 跳转联系卖家
				uni.navigateTo({
					url: '',
				});
			},
			reviewOrder(order, e) {
				// 处理订单详情逻辑
				// 1-查看评价 2-去评价 3-确认收货 4-去付款
				if(e == 1)
				{
					uni.navigateTo({
						url: '/pages/evaluate-details/evaluate-details?type=id&tradeid=' + order.orderid,
					});
				}
				else if(e == 2)
				{
					uni.navigateTo({
						url:`/pages/evaluate/evaluate?type=id&tradeid=${order.orderid}`
					})
				}
				else if(e == 3)
				{
					uni.navigateTo({
						url: '/pages/order/orderdetail?type=json&order=' + encodeURIComponent(JSON.stringify(order)),
					});
				}
				else if(e == 4)
				{
					uni.navigateTo({
						url: '/pages/order/orderdetail?type=json&order=' + encodeURIComponent(JSON.stringify(order)),
					});
				}
			},
			toOrderDetail(order) {
				if (this.type == "2") {
					let item = {
						id: order.orderid,
						empName: order.seller,
						price: order.productPrice,
						title: order.productDescription,
						image: order.productImage,
						avatar: order.avatar
					};
					if (order.transactionStatus == "已售出") {
						order.orderid = order.tradesID;
						uni.navigateTo({
							url: '/pages/order/orderdetail?type=json&order=' + encodeURIComponent(JSON.stringify(order)),
						});
					} else {
						uni.navigateTo({
							url: '/pages/product-details/product-details?item=' + encodeURIComponent(JSON
								.stringify(item)),
						})
					}

				} else {
					uni.navigateTo({
						url: '/pages/order/orderdetail?type=json&order=' + encodeURIComponent(JSON.stringify(order)),
					});
				}

			},
		}
	};
</script>

<style lang="scss">
	.navi-header-text {
		color: #000;
		/* 订单列表文字颜色 */
	}

	container {
		background-color: #f9f9f9;
		height: 1000px;
		/*页面背景*/
	}

	.order-list {
		margin: 10px;
		margin-left: 0px;
		margin-right: 0px;
		margin-top: 0px;
		padding: 10px;
		background-color: #ffffff;
		border-radius: 5px;
	}

	.order-info {
		display: flex;
		justify-content: space-between;
		font-size: 10px;
		padding-bottom: 5px;
		/* 调整底部内边距 */
		margin-bottom: 5px;
		/* 调整底部外边距 */
		//align-items: center;
	}

	.user-info {
		display: flex;
		align-items: center;
	}

	.product-image {
		width: 80px;
		height: 80px;
		border-radius: 10px;
		margin-right: 5px;
	}

	.order-details {
		display: flex;
		align-items: center;
	}

	.seller {
		font-size: 15px;
	}

	.product-description {
		font-size: 26rpx;
		color: #999999;
	}

	.product-price {
		font-size: 20px;
		color: #000000;
		/* 将颜色改为黑色 */
		font-weight: bold;
		/* 加粗文字 */
		margin-left: auto;
	}

	.transaction-status {
		font-size: 15px;
	}

	.buttons {
		margin-top: 10px;
		display: flex;
		justify-content: flex-end;
		border: #000000;
	}

	.contact-seller-button {
		font-size: 15px;
		background-color: #f5f5f5;
		color: #000000;
		padding-left: 5px;
		padding-right: 5px;
		border-radius: 15px;
		margin-right: 5px;
	}

	.review-button {
		font-size: 15px;
		background-color: #62bbff;
		color: #000000;
		padding: 0px 15px;
		margin-right: 1px;
		margin-left: 5px;
		border-radius: 15px;
	}
</style>