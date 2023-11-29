<template>
	<view class="container">
		<!-- 导航栏 -->
		<view class="navigation-bar">
			<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx" right-width="160rpx">
				<block slot="left">
					<uni-icons @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em">商品发布</text>
				</block>
				<block slot="right">
					<button class="release-btn" type="primary" size="mini" style="border-radius:15px"
						@click="release">发布</button>
				</block>
			</uni-nav-bar>
		</view>

		<!--发布内容-->
		<view class="release-body">
			<!--输入文本-->
			<uni-easyinput class="input-info" :inputBorder="false" type="textarea" autoHeight v-model="releaseText"
				placeholder="描述一下闲置物品的具体信息" />
			<!--添加图片-->
			<view class="cu-form-group" style="border-top: 20rpx solid #eee;">
				<view class="action">
					图片
				</view>
				<view class="action">
					{{tempImgList.length}} / 4
				</view>
			</view>
			<view class="cu-form-group" style="border-top: 0rpx solid #eee;">
				<view class="grid col-4 grid-square flex-sub">
					<view class="bg-img" v-for="(item,index) in tempImgList" :key="index"
						:data-url="tempImgList[index]">
						<image :src=item mode="aspectFill" @click="viewImage(index)"></image>
						<view class="cu-tag bg-red" @click="delImg(index)" :data-index="index">
							<text class='cuIcon-close'></text>
						</view>
					</view>
					<view class="solids" @click="chooseImg()" v-if="tempImgList.length < count">
						<text class='cuIcon-cameraadd'></text>
					</view>
				</view>
			</view>

		</view>

		<!--其他信息-->
		<uni-list border-full class="other-info">
			<uni-list-item showArrow title="价格" :rightText="price" clickable @click="openPriceInput" />
			<picker class="delivery-method" :range="deliveryMethods" @change="changeDeliveryMethod">
				<view class="picker">
					<uni-list-item showArrow title="发货方式" :rightText="deliveryMethods[deliveryMethodIndex]" clickable />
				</view>
			</picker>
			<uni-list-item v-if="deliveryMethods[deliveryMethodIndex] === '邮寄'" showArrow title="邮费"
				:rightText="shippingfee" clickable @click="openFeeInput" />
			<picker class="category-picker" :range="categories" @change="changeCategory">
				<view class="picker">
					<uni-list-item showArrow title="商品分类" :rightText="selectedCategory" clickable />
				</view>
			</picker>
		</uni-list>

		<!--价格输入框-->
		<uni-popup ref="inputPrice" type="dialog">
			<uni-popup-dialog mode="input" title="请输入价格" :value="price" :before-close="true" @close="closePriceInput"
				@confirm="confirmPriceInput" />
		</uni-popup>

		<!--邮费输入框-->
		<uni-popup ref="inputFee" type="dialog">
			<uni-popup-dialog mode="input" title="请输入邮费" :value="shippingfee" :before-close="true"
				@close="closeFeeInput" @confirm="confirmFeeInput" />
		</uni-popup>
	</view>

</template>

<script>
	import moment from 'moment';
	export default {
		data() {
			return {
				count: 4,
				imgList: [],
				tempImgList: [],
				releaseText: "",
				price: "0.00", //默认价格
				deliveryMethods: ['自提', '邮寄'],
				deliveryMethodIndex: 0,
				shippingfee: "10.00", //默认邮费
				categories: ['文娱用品', '学习用品', '生活用品', '交通工具', '奇奇怪怪'],
				selectedCategory: '请选择分类',
				//图片格式
				imageStyles: {
					border: {
						radius: '10px'
					}
				},
			};
		},
		methods: {
			goBack() {
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
			uploadImg() {
				uni.uploadFile({
					url: this.$store.state.baseUrl + "/api/upload",
					files: this.tempImgList,
					name: "file",
					header: {
						'content-type': 'multipart/form-data',
					},
					success: (res) => {
						let ress = JSON.parse(uploadFileRes.data.data);
						this.imgList.push(ress.data);
						return 1;
					}
				})
				return 0;
			},
			release() {
				let nowtime = moment().format("YYYY-MM-DDTHH:mm:ss.SSS+08");
				uni.showLoading({
					title: "发布中"
				});
				if (this.uploadImg() == 1) {
					uni.request({
						url: this.$store.state.baseUrl + "/api/release",
						method: "POST",
						data: {
							goods_Price: this.price,
							goods_Name: this.releaseText.slice(0, 20),
							seller_ID: this.$store.state.userInfo.token,
							classification: this.selectedCategory,
							release_Time: nowtime,
							goods_Description: this.releaseText
						},
						success: (res) => {
							console.log(res.data);
							if (res.data.code == 200) {
								uni.showToast({
									title: `发布成功~`,
									icon: 'success'
								});
								uni.redirectTo({
									url: "/pages/user/user"
								});
							} else {
								uni.showToast({
									title: `发布失败`,
									icon: 'error'
								});
							}
						}
					});
				}else
				{
					uni.showToast({
						title: `上传图片失败`,
						icon: 'error'
					});
				}
			},
			chooseImg() {
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'],
					sourceType: ['album'],
					success: (res) => {
						// 获取到图片的本地文件路径列表，存储到临时列表tempImgList中
						console.log(res);
						if (this.tempImgList.length != 0) {
							this.tempImgList = this.tempImgList.concat(res.tempFilePaths)
						} else {
							this.tempImgList = res.tempFilePaths
						}
					}
				});
			},
			viewImage(index) {
				uni.previewImage({
					urls: this.tempImgList,
					current: index
				});
			},
			delImg(index) {
				uni.showModal({
					title: '',
					content: '确定要删除第' + (index + 1) + '张图片吗？',
					cancelText: '再看看',
					confirmText: '确定',
					success: res => {
						if (res.confirm) {
							this.tempImgList.splice(index, 1)
						}
					}
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
	.release-body {
		background-color: #ffffff;
		border-radius: 5px;
		padding: 10px;
		margin-bottom: 5px;

	}

	.navi-header-text {
		color: #000;
		/* 商品发布文字颜色 */
	}

	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>