<template>
	<view class="container">
		<!-- 顶部导航栏 -->
		<uni-nav-bar dark :fixed="true"  background-color="#f5f5f5" status-bar left-width="250rpx">
			<block slot="left">
				<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
				<text class="navi-header-text" style="font-size:1.15em" >确认购买</text>
			</block>
		</uni-nav-bar>
		
		<!--商品相关-->
		<view class="product-info" v-for="(product, index) in products" :key="index">
			<!--商品图片-->
			<image class="product-image" :src="product.image" />
			<!--其他信息：商品名；商品价格-->
			<view class="product-details">
				<text class="product-name">{{ product.name }}</text>
				<text class="product-price">￥{{ product.price }}</text>
			</view>
			<!--发货信息：发货方式；运费；收货地址-->
			<view class="delivery-info">
				<text class="delivery-method">发货方式：{{ product.deliveryMethod }}</text>
				<text class="shipping-fee">运费：￥{{ product.shippingFee }}</text>
				<text class="address" v-if="product.deliveryMethod === '邮寄'">收货地址：{{ product.address }}</text>
			</view>
		</view>	
		<!-- 底部导航-->
		<view class="bottom-nav">
			<text class="total-amount">实付款：￥{{ totalPrice }}</text>
			<button class="confirm-button" @click="confirmPurchase">确认购买</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//fake data
				products: [
					{
					  id: "1",
					  name: "L19",
					  image: "../../static/L19R5LODHPK2]1@[M9SS94K.png",
					  price: 100,
					  deliveryMethod: '自提',
					  address: '',  // 自提不需要显示地址
					  shippingFee: 0  // 自提不需要运费
					},
					{
					  id: "2",
					  name: "L19R5L",
					  image: "../../static/L19R5LODHPK2]1@[M9SS94K.png",
					  price: 200,
					  deliveryMethod: '邮寄',
					  address: '福州大学生活三区',  // 邮寄需要显示地址
					  shippingFee: 10  // 邮寄运费为10元
					},
					{
					  id: "3",
					  name: "L19R5LODH",
					  image: "../../static/L19R5LODHPK2]1@[M9SS94K.png",
					  price: 200,
					  deliveryMethod: '邮寄',
					  address: '福州大学生活三区',  // 自提不需要显示地址
					  shippingFee: 15  // 自提不需要运费
					}
				],
			};
		},
		computed: {
			totalPrice() {
				let total = this.products.reduce((sum, product) => sum + product.price, 0);
				let shippingTotal = this.products.reduce((sum, product) => sum + this.calculateShippingFee(product), 0);
				return total + shippingTotal;
			}
		},
		methods: {
			goBack() {
				uni.navigateBack();
			},
			onClick(e){
				uni.showToast({title: `点击${e.content.text}`,icon: 'none'});
			},
			buttonClick(e){
				console.log(e);
				this.options[2].info++;
			},
			changeDeliveryMethod(e) {
				this.deliveryMethodIndex = e.detail.value;
				this.calculateShippingFee();
			},
			calculateShippingFee(product) {
			if (product.deliveryMethod === '自提')
				return 0;  // 如果是自提，运费为0
			else if (product.deliveryMethod === '邮寄')
				return product.shippingFee;
			},
			confirmPurchase() {
				// 确认购买操作，调用后端接口
				uni.showToast({
					title: `确认购买成功`
				})
			},
		},
	};
</script>

<style lang="scss">
	.navi-header-text{
		color: #000;
		/* 确认购买文字颜色 */
	}
	.container{
		background-color: #f9f9f9;
		height: 1000px;
		/*页面背景*/
	}
	.product-info{
		margin: 10px;
		margin-left: 10px;
		margin-right: 10px;
		padding: 10px;
		background-color: #ffffff;
		border-radius: 5px;
		
	}
	.product-image {
	  width: 100px;
	  height: 100px;
	  border-radius: 10px;
	  
	}
	.product-details {
		display: flex;
		justify-content: space-between;
		font-size: 10px;
		border-bottom: 1px solid #e0e0e0; /* 添加底部边框作为间隔线 */
		padding-bottom: 5px; /* 调整底部内边距 */
		margin-bottom: 5px; /* 调整底部外边距 */
	}
	
	.delivery-method,
	.address,
	.shipping-fee {
	  margin-top:5px;
	  display: block;
	  font-size: 10px;
	}
	.product-price{
		color:#ff0000;
	}
	.bottom-nav {
		display: flex;
		justify-content: space-between;
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		background-color: #fff;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}
	.confirm-button {
		margin:15px;
		padding:2px,5px;/* 调整按钮内边距 */
		border: none;
		border-radius: 5px;
		background-color: #409EFF;
		color: #FFFFFF;
		font-size: 14px;
	}
	.total-amount{
		font-weight: bold;
		color: #FF0000; 
		margin:20px;
		
	}
</style>
