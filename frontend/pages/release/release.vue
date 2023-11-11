<template>
	<view class="container">
		<view class="navigation-bar">
		<!-- 导航栏 -->
			<uni-nav-bar dark :fixed="true"  background-color="#f5f5f5" status-bar left-width="250rpx" right-width="160rpx">
				<block slot="left">
					<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em" >商品发布</text>
				</block>
				<block slot="right">
					<button class="release-btn" type="primary" size="mini" style="border-radius:15px" @click="release">发布</button>	
				</block>
			</uni-nav-bar>		
		</view>
	
		<view class="release-body">
		<!--发布内容-->
			<!--输入文本-->
			<uni-easyinput class="input-info":inputBorder="false" type="textarea" autoHeight v-model="releaseText" placeholder="描述一下闲置物品的具体信息" />		
			<!--添加图片-->
			<uni-file-picker class="input-with-picture" limit="9" fileMediatype="image" :image-styles="imageStyles"></uni-file-picker>
		</view>	

		<uni-list border-full class="other-info">
			<!--其他信息-->
			<uni-list-item showArrow title="价格" :rightText="price" clickable @click="openPriceInput" />
			<uni-list-item showArrow title="发货方式" :rightText="deliveryMethod" clickable @click="openDeliveryInput" />
		</uni-list>
		<!--发货方式输入框-->
		<uni-popup ref="inputDelivery" type="dialog">
		  <uni-popup-dialog mode="input" title="请输入发货方式" :value="deliveryMethod" :before-close="true" @close="closeDeliveryInput" @confirm="confirmDeliveryInput" />
		</uni-popup>
		<!--价格输入框-->
		<uni-popup ref="inputPrice" type="dialog">
		    <uni-popup-dialog mode="input" title="请输入价格" :value="price" :before-close="true" @close="closePriceInput" @confirm="confirmPriceInput" />
		</uni-popup>
	</view>
	
	

</template>

<script>
	export default {
		data() {
			return {
				releaseText:"",
				price:"0.00", //默认价格值
				deliveryMethod: "自提", // 默认发货方式
				//图片格式
				imageStyles:{
					border:{
						width:1,
						radius:'10px'
					}
				},
			};
		},
		methods:{
			goBack(){
				uni.navigateBack();
			},

			openPriceInput() {
				this.$refs.inputPrice.open();
			},
			closePriceInput() {
				this.$refs.inputPrice.close();
			},
			confirmPriceInput(value) {
				this.price = value;
				this.$refs.inputPrice.close();
			},
			openDeliveryInput() {
				this.$refs.inputDelivery.open();
			},
			closeDeliveryInput() {
				this.$refs.inputDelivery.close();
			},
			confirmDeliveryInput(value) {
				this.deliveryMethod = value;
				this.$refs.inputDelivery.close();
			},
			release(){
				uni.showToast({
					title: `发布成功`
				})
			}
		}
	};
</script>

<style lang="scss">
	.release-body{
		background-color: #ffffff;
		border-radius: 5px;
		width: 100%;

	}
	.navi-header-text{
		color: #000;
		/* 商品发布文字颜色 */
	}
	.input-with-picture{
		padding-bottom: 20rpx;
		margin: 5px;
	}
	.input-info{
		padding: 5px;
	}

</style>
