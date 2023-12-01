<template>
	<view class="container">
		<view class="navigation-bar">
			<!-- 导航栏 -->
			<uni-nav-bar dark :fixed="true" background-color="#f5f5f5" status-bar left-width="250rpx"
				right-width="160rpx">
				<block slot="left">
					<uni-icons @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em">评价</text>
				</block>
				<block slot="right">
					<button class="release-btn" type="primary" size="mini" style="border-radius:15px"
						@click="evaluate">发布</button>
				</block>
			</uni-nav-bar>
		</view>

		<view class="goods-body">
			<view class="goods-info-container">
				<view class="goods-image">
					<!-- 商品图片部分 -->
					<img :src="image" class="item-image" />
				</view>
				<!-- 商品文字部分 -->
				<view>
					<p class="item-name">{{ name }}</p>
					<p class="item-price">实付款￥{{ price }}</p>
				</view>
			</view>
		</view>

		<view class="evaluate-body">
			<!--评价内容-->
			<!--选择按键-->
			<view class="radio-buttons">
				<button @click="selectOption(0)" :class="{ 'selected': selectedOption == 0 }">很赞</button>
				<button @click="selectOption(1)" :class="{ 'selected': selectedOption == 1 }">一般</button>
				<button @click="selectOption(2)" :class="{ 'selected': selectedOption == 2 }">不满</button>
			</view>
			<!--输入文本-->
			<uni-easyinput class="input-info" :inputBorder="false" type="textarea" autoHeight v-model="releaseText"
				placeholder="聊聊本次的交易感受，把你的经历分享给其他使用该软件的同学吧~" />
			<!--添加图片-->
			<uni-file-picker class="input-with-picture" limit="9" fileMediatype="image"
				:image-styles="imageStyles"></uni-file-picker>
			<!--是否匿名-->
			<view class="check-release">
				<view>
					<uni-data-checkbox multiple v-model="checkbox1" :localdata="terms"></uni-data-checkbox>
				</view>
				<view class="check-info">你的头像将在评价中隐藏</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//图片格式
				imageStyles: {
					border: {
						width: 1,
						radius: '10px'
					}
				},
				image: '../../static/xiaomi.png',
				name: '出一个小米手环2 全新未拆',
				price: '25',
				checkbox1: [0],
				terms: [{
					text: '匿名评价',
					value: 1
				}],
				selectedOption: 4, // 初始化选中的单选按钮状态
				options: ["很赞", "一般", "不满"],
				realopts: ["好评","中评","差评"],
				releaseText: "",
				type: 1,
				tradeid: 0,
			};
		},
		onLoad: function(opt) {
			if (opt.type == 'json') {
				const order = JSON.parse(decodeURIComponent(opt.order));
				this.tradeid = order.orderid;
				this.image = order.productImage;
				this.name = order.productDescription;
				this.price = order.productPrice;
				this.type = order.type;
			} else if(opt.type == "id"){
				const tradeid = opt.tradeid;
				uni.request({
					url: this.$store.state.baseUrl + "/api/trades/id",
					data: {
						tradeID: tradeid,
						userID: this.$store.state.token
					},
					success: (res) => {
						const order = res.data.data;
						this.tradeid = order.orderid;
						this.image = order.productImage;
						this.name = order.productDescription;
						this.price = order.productPrice;
						this.type = order.type;
					}
				})
			}
			
			//type=1是卖家评价，type=3是买家评价
		},
		methods: {
			goBack() {
				uni.redirectTo({
					url: "/pages/order/orderdetail?type=id?tradeid=" + this.tradeid
				});
			},
			evaluate() {
				if (this.type == "1") {
					uni.request({
						url: this.$store.state.baseUrl + "/api/evaluations/seller",
						method: "POST",
						data: {
							Seller_Evaluation: this.releaseText,
							Seller_Status: this.options[this.selectedOption],
							Trade_ID: this.tradeid
						},
						success: (res) => {
							uni.showToast({
								title: "评价成功！"
							});
						}
					})
				} else {
					uni.request({
						url: this.$store.state.baseUrl + "/api/evaluations/buyer",
						method: "POST",
						data: {
							Buyer_Evaluation: this.releaseText,
							Buyer_Status: this.realopts[this.selectedOption],
							Trade_ID: this.tradeid
						},
						success: (res) => {
							uni.showToast({
								title: "评价成功！"
							});
						}
					})
				}
				uni.showToast({
					title: `发布成功`
				})
				uni.redirectTo({
					url: "/pages/order/orderdetail?type=id&tradeid=" + this.tradeid
				})
			},
			selectOption(option) {
				this.selectedOption = option; // 更新选中的单选按钮状态
			}
		}
	};
</script>

<style lang="scss">
	.navi-header-text {
		color: #000;
		/* 商品评价文字颜色 */
	}

	/* 评价信息 */
	.evaluate-body {
		background-color: #ffffff;
		border-radius: 5px;
	}

	/*选择按键*/
	.selected {
		filter: brightness(1.3);
		/* 降低亮度，数值在0~1之间，0为全黑，1为正常 */
		color: #fff;
	}

	.radio-buttons {
		display: flex;
	}

	.radio-buttons button {
		margin-top: 10px;
		border-radius: 100upx;
		font-size: 14px;
		width: 100px;
		font-weight: bold;
		color: #fff;
		opacity: 0.7;
	}

	.radio-buttons button:nth-child(1) {
		background-color: #3E9DC9;
	}

	.radio-buttons button:nth-child(2) {
		background-color: #D47415;
	}

	.radio-buttons button:nth-child(3) {
		background-color: #CF4829;
	}

	/* 商品信息 */
	.goods-body {
		background-color: #ffffff;
		border-radius: 5px;
		height: 80px;
		margin-bottom: 10px;
		display: flex;
		align-items: center;
	}

	.goods-info-container {
		display: flex;
		align-items: center;
	}

	.item-image {
		height: 52px;
		width: 52px;
		margin-left: 40px;
	}

	.item-name {
		font-size: 14px;
		margin-left: 20px;
		margin-bottom: 10px;
		font-weight: bold;
		opacity: 0.5;
	}

	.item-price {
		margin-left: 20px;
		font-size: 11px;
		font-weight: bold;
		opacity: 0.5;
	}


	.input-with-picture {
		padding-bottom: 20rpx;
		margin: 10px;
	}

	.input-info {
		padding: 5px;
	}

	/*是否匿名*/
	.check-release {
		display: flex;
		align-items: center;
		margin-left: 10px;
	}

	.check-info {
		font-size: 11px;
		opacity: 0.5;
	}
</style>