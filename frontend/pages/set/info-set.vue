<template>
	<view class="container">
		<!-- 导航栏 -->
		<view class="navigation-bar">
			<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx">
				<block slot="left">
					<uni-icons @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em">个人信息</text>
				</block>
			</uni-nav-bar>
		</view>

		<!--头像-->

		<view class="avatar-part">
			<image :src="userInfo.avatar" class="avatar" @click="changeAvatar()"></image>
		</view>

		<!--账号昵称信息-->
		<uni-list-item title="账号" :rightText="userInfo.account" />
		<uni-list-item showArrow title="昵称" :rightText="userInfo.name" clickable @click="openUsernameInput" />

		<!--修改昵称弹窗-->
		<uni-popup ref="inputUserName" type="dialog">
			<uni-popup-dialog mode="input" title="修改昵称" :value="userInfo.name" :before-close="true"
				@close="closeUsernameInput" @confirm="confirmUsernameInput" />
		</uni-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//fake data
				userInfo: {
					name: "FZU_1234",
					avatar: "../../static/L19R5LODHPK2]1@[M9SS94K.png",
					account: "102101666",
				}
			}
		},
		onLoad: function(opt) {
			this.userInfo = JSON.parse(decodeURIComponent(opt.info));
		},
		methods: {
			goBack() {
				uni.navigateBack({
					url: '/pages/set/set-list'
				});
			},
			openUsernameInput() {
				this.$refs.inputUserName.open();
			},
			closeUsernameInput() {
				this.$refs.inputUserName.close();
			},
			changeAvatar() {
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'],
					sourceType: ['album'],
					success: async (res) => {
						console.log(res);
						let result = await this.uploadImg(res.tempFilePaths[0]);
						if (result == 1) {
							this.userInfo.avatar = res.tempFilePaths[0];
							uni.showToast({
								icon: "success",
								title: "图片上传成功"
							});
						} else {
							setTimeout(() => {
								uni.showToast({
									icon: "error",
									title: "图片上传失败"
								});
							}, 500)
						}
					}
				});
			},
			uploadImg(img) {
				return new Promise((resolve, reject) => {
					var filelist = [];
					for (var i = 0; i < 1; i++) {
						var obj = {};
						obj.name = "file";
						obj.uri = img;
						filelist.push(obj)
					}
					uni.uploadFile({
						url: this.$store.state.baseUrl + "/api/upload/uploadImage",
						fileType: 'image',
						files: filelist,
						name: "file",
						formData: {
							type: 1,
							id: this.$store.state.token
						},
						success: (res) => {
							let ress = JSON.parse(res.data)
							console.log(ress);
							if (ress.code == 200) {
								resolve(1);
							} else {
								resolve(0);
							}
						},
						fail: (res) => {
							resolve(0);
						}
					});
				})
			},
			confirmUsernameInput(value) {
				this.$refs.inputUserName.close();
				uni.showLoading({
					title: "修改中..."
				});
				uni.request({
					url: this.$store.state.baseUrl + "/api/wxUser/change-username",
					data: {
						id: this.$store.state.token,
						username: value
					},
					success: (res) => {
						uni.hideLoading();
						if (res.data.code == 200) {
							this.userInfo.name = value;
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

	.avatar-part {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 20px;
		padding: 40px;
		background-color: #fff;
	}

	.avatar {
		width: 120px;
		/* 设置头像宽度 */
		height: 120px;
		/* 设置头像高度 */
		border-radius: 50%;
		/* 将头像变为圆形 */
	}
</style>