<template>
	<view class="container">
		<!-- 导航栏 -->
		<view class="navigation-bar">
			<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx" right-width="160rpx">
				<!--
				<block slot="left">
					<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em" >商品发布</text>
				</block>
				-->
				<block slot="right">
					<button class="release-btn" type="primary" size="mini" style="border-radius:15px" @click="release">发布</button>	
				</block>
			</uni-nav-bar>		
		</view>
		
		<!--发布内容-->
		<view class="release-body">
			<!--输入文本-->
			<uni-easyinput class="input-info":inputBorder="false" type="textarea" autoHeight v-model="releaseText" placeholder="描述一下闲置物品的具体信息" />		
			<!--添加图片-->
			<uni-file-picker class="input-with-picture" limit="9" fileMediatype="image" :image-styles="imageStyles"></uni-file-picker>
		</view>	
		
		<!--其他信息-->
		<uni-list border-full class="other-info">
			<uni-list-item showArrow title="价格" :rightText="price" clickable @click="openPriceInput"/>
			<picker class="delivery-method" :range="deliveryMethods" @change="changeDeliveryMethod">
				<view class="picker">
					<uni-list-item showArrow title="发货方式" :rightText="deliveryMethods[deliveryMethodIndex]" clickable/>
				</view>
			</picker>
			<uni-list-item v-if="deliveryMethods[deliveryMethodIndex] === '邮寄'" showArrow title="邮费":rightText="shippingfee" clickable @click="openFeeInput"/>
			<picker class="category-picker" :range="categories" @change="changeCategory">
				<view class="picker">
					<uni-list-item showArrow title="商品分类" :rightText="selectedCategory" clickable/>
				</view>
			</picker>
		</uni-list>
			
		<!--价格输入框-->
		<uni-popup ref="inputPrice" type="dialog">
		    <uni-popup-dialog mode="input" title="请输入价格" :value="price" :before-close="true" @close="closePriceInput" @confirm="confirmPriceInput" />
		</uni-popup>
		
		<!--邮费输入框-->
		<uni-popup ref="inputFee" type="dialog">
		    <uni-popup-dialog mode="input" title="请输入邮费" :value="shippingfee" :before-close="true" @close="closeFeeInput" @confirm="confirmFeeInput" />
		</uni-popup>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				releaseText:"",
				price:"0.00", //默认价格
				deliveryMethods: ['自提', '邮寄'],
				deliveryMethodIndex: 0,
				shippingfee:"10.00",//默认邮费
				categories: ['文娱用品', '学习用品', '生活用品', '交通工具', '奇奇怪怪'],
				selectedCategory: '请选择分类',
				//图片格式
				imageStyles:{
					border:{
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
			openFeeInput() {
				this.$refs.inputFee.open();
			},
			closeFeeInput() {
				this.$refs.inputFee.close();
			},
			confirmFeeInput(value) {
				this.shippingfee = value;
				this.$refs.inputFee.close();
			},
			release(){
				uni.showToast({
					title: `发布成功`
				})
			},
			changeDeliveryMethod(e) {
			  this.deliveryMethodIndex = e.detail.value;
			},
			changeCategory(e) {
				this.selectedCategory = this.categories[e.detail.value];
			},
		}
	};
</script>

<style lang="scss">
	.release-body{
		background-color: #ffffff;
		border-radius: 5px;
		padding: 10px;
		margin-bottom: 5px;

	}
	.navi-header-text{
		color: #000;
		/* 商品发布文字颜色 */
	}
</style>
