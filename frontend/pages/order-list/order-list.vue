<template>
  <view class="container">
		<!-- 顶部导航栏 -->
		<uni-nav-bar dark :fixed="true"  background-color="#f5f5f5" status-bar left-width="250rpx">
			<block slot="left">
				<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
				<text class="navi-header-text" style="font-size:1.15em" >订单列表</text>
			</block>
		</uni-nav-bar>
		
		<!-- 订单列表 -->
		<view class="order-list"v-for="(order, index) in orders" :key="index">
			<!-- 卖家名和订单状态 -->
			<view class="order-info">
				<text class="seller">{{ order.seller }}</text>
				<text class="transaction-status">{{ order.transactionStatus }}</text>
			</view>
			<view class="order-details" @click="toOrderDetail()">
				<!-- 订单商品图片 -->
				<image :src="order.productImage" class="product-image"></image>
				<!-- 订单商品描述和价格 -->
				<text class="product-description">{{ order.productDescription }}</text>
				<text class="product-price">¥{{ order.productPrice }}</text>
			</view>
			<view class="buttons">
				<button class="contact-seller-button" @click="contactSeller()">联系卖家</button>
				<button class="review-button" @click="reviewOrder()">评价</button>
			</view>
		</view>
		</view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      orders: [
        { 
          orderid: '20230101', 
          seller: '卖家A', 
          productImage: '../../static/L19R5LODHPK2]1@[M9SS94K.png',
          productDescription: '鞋子',
          productPrice: 100.5,
          transactionStatus: '交易成功'
        },
		{
		  orderid: '20230101', 
		  seller: '卖家A', 
		  productImage: '../../static/L19R5LODHPK2]1@[M9SS94K.png',
		  productDescription: '鞋子',
		  productPrice: 100.5,
		  transactionStatus: '交易成功'
		},
      ]
    };
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
    reviewOrder() {
      // 处理评价订单逻辑
		uni.navigateTo({
		url: '/pages/evaluate-details/evaluate-details',
		});
    },
	toOrderDetail(){
		uni.navigateTo({
		url: '/pages/order/orderdetail',
		});
	}
  }
};
</script>

<style lang="scss">
	.navi-header-text{
		color: #000;
		/* 订单列表文字颜色 */
	}
	container{
		background-color: #f9f9f9;
		height: 1000px;
		/*页面背景*/
	}
	.order-list{
		margin: 10px;
		margin-left: 0px;
		margin-right: 0px;
		margin-top: 0px;
		padding: 10px;
		background-color: #ffffff;
		border-radius: 5px;
	}
	.order-info{
		display: flex;
		justify-content: space-between;
		font-size: 10px;
		padding-bottom: 5px; /* 调整底部内边距 */
		margin-bottom: 5px; /* 调整底部外边距 */
	}

	.product-image {
		width: 80px;
		height: 80px;
		border-radius: 10px;
		margin-right: 5px;
	}
	.order-details {
		display:flex;
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
		color: #000000; /* 将颜色改为黑色 */
		font-weight: bold; /* 加粗文字 */
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
		padding-left:5px;
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
