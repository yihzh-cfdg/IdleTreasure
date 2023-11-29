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
				this.addressInfo.recipient = value;
				this.$refs.inputRecipient.close();
			},
			confirmPhoneNumberInput(value) {
				this.addressInfo.phoneNumber = value;
				this.$refs.inputPhoneNumber.close();
			},
			confirmAddressInput(value) {
				this.addressInfo.address = value;
				this.$refs.inputAddress.close();
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