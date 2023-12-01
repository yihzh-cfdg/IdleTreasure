<template>
	<view class="container">
		<!-- 导航栏 -->
		<view class="navigation-bar">

			<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx">
				<block slot="left">
					<uni-icons @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em">收货地址</text>
				</block>
			</uni-nav-bar>
		</view>
		<uni-list-item title="收货人" :rightText="addressInfo.recipient" clickable @click="openRecipientInput" />
		<uni-list-item title="电话号码" :rightText="addressInfo.phoneNumber" clickable @click="openPhoneNumberInput" />
		<uni-list-item title="收货地址" :rightText="addressInfo.address" clickable @click="openAddressInput" />

		<uni-popup ref="inputRecipient" type="dialog">
			<uni-popup-dialog mode="input" title="修改收货人" :value="addressInfo.recipient" :before-close="true"
				@close="closeRecipientInput" @confirm="confirmRecipientInput" />
		</uni-popup>

		<uni-popup ref="inputPhoneNumber" type="dialog">
			<uni-popup-dialog mode="input" title="修改电话号码" :value="addressInfo.phoneNumber" :before-close="true"
				@close="closePhoneNumberInput" @confirm="confirmPhoneNumberInput" />
		</uni-popup>

		<uni-popup ref="inputAddress" type="dialog">
			<uni-popup-dialog mode="input" title="修改收货地址" :value="addressInfo.address" :before-close="true"
				@close="closeAddressInput" @confirm="confirmAddressInput" />
		</uni-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 虚拟的收货地址信息
				addressInfo: {
					recipient: "小张",
					phoneNumber: "13800138000",
					address: "福州大学三区"
				}
			}
		},
		onLoad:function(){
			const token = this.$store.state.userInfo.token;
			// TODO: 查询用户名、电话、地址
			uni.request({
				url:this.$store.state.baseUrl + "/api/wxUser/get-address",
				data:{
					id: token
				},
				success: (res) => {
					this.addressInfo.recipient = res.data.data.recipient;
					this.addressInfo.phoneNumber = res.data.data.phone;
					this.addressInfo.address = res.data.data.delivery;
				}
			})
		},
		methods: {
			goBack() {
				uni.navigateBack({
					url: '/pages/set/set-list'
				});
			},
			openRecipientInput() {
				this.$refs.inputRecipient.open();
			},
			openPhoneNumberInput() {
				this.$refs.inputPhoneNumber.open();
			},
			openAddressInput() {
				this.$refs.inputAddress.open();
			},
			closeRecipientInput() {
				this.$refs.inputRecipient.close();
			},
			closePhoneNumberInput() {
				this.$refs.inputPhoneNumber.close();
			},
			closeAddressInput() {
				this.$refs.inputAddress.close();
			},
			confirmRecipientInput(value) {
				this.$refs.inputRecipient.close();
				uni.showLoading({
					title: "修改中..."
				});
				uni.request({
					url: this.$store.state.baseUrl + "/api/wxUser/change-recipient",
					data: {
						id: this.$store.state.userInfo.token,
						recipient: value
					},
					success: (res) => {
						uni.hideLoading();
						if (res.data.code == 200) {
							this.addressInfo.recipient = value;;
							uni.showToast({
								title: "修改成功",
								icon: 'success'
							});
						} else {
							uni.showToast({
								title: "修改失败！",
								icon: 'error'
							});
						}
					},
					fail: (res) => {
						uni.hideLoading();
						uni.showToast({
							title: "修改失败！",
							icon: 'error'
						});
					}
				})
			},
			confirmPhoneNumberInput(value) {
				this.$refs.inputPhoneNumber.close();
				uni.showLoading({
					title: "修改中..."
				});
				uni.request({
					url: this.$store.state.baseUrl + "/api/wxUser/change-phone",
					data: {
						id: this.$store.state.userInfo.token,
						phone: value
					},
					success: (res) => {
						uni.hideLoading();
						if (res.data.code == 200) {
							this.addressInfo.phoneNumber = value;
							uni.showToast({
								title: "修改成功",
								icon: 'success'
							});
						} else {
							uni.showToast({
								title: "修改失败！",
								icon: 'error'
							});
						}
					},
					fail: (res) => {
						uni.hideLoading();
						uni.showToast({
							title: "修改失败！",
							icon: 'error'
						});
					}
				})
			},
			confirmAddressInput(value) {
				this.$refs.inputAddress.close();
				uni.showLoading({
					title: "修改中..."
				});
				uni.request({
					url: this.$store.state.baseUrl + "/api/wxUser/change-address",
					data: {
						id: this.$store.state.userInfo.token,
						address: value
					},
					success: (res) => {
						uni.hideLoading();
						if (res.data.code == 200) {
							this.addressInfo.address = value;
							uni.showToast({
								title: "修改成功",
								icon: 'success'
							});
						} else {
							uni.showToast({
								title: "修改失败！",
								icon: 'error'
							});
						}
					},
					fail: (res) => {
						uni.hideLoading();
						uni.showToast({
							title: "修改失败！",
							icon: 'error'
						});
					}
				})
			}
		}
	}
</script>

<style>
	.navi-header-text {
		color: #000;
		/* 设置文字颜色 */
	}
</style>