<template>
	<view class="container">
		<view class="topBox">
			<view class="name">
				推荐
			</view>
			<view class="searchBox flex">
				<view class="left flex">
					<uni-easyinput class="searchInput" trim="all" v-model="search"
						@focus="searchFocusFun"></uni-easyinput>
					<view class="btn">
						<uni-icons type="search" size="20" color="#fff"></uni-icons>
					</view>
				</view>
				<view class="right">
					<uni-icons type="list" size="25" color="#000"></uni-icons>
					<view class="text">
						分类
					</view>
				</view>
			</view>
		</view>
		<view class="moduleBox flex">
			<view :class="['moduleItem', 'flex','module'+item.id]" v-for="(item,index) in moduleList" :key="index">
				<view class="content">
					<uni-icons :type="item.icon" :size="item.size ? item.size : 20" color="#fff"
						custom-prefix="iconfont"></uni-icons>
					<view class="name">
						{{item.name}}
					</view>
				</view>

			</view>
		</view>

		<view class="listBox">
			<view class="tabsBox flex">
				<view :class="['tabsItem',activeTabs === index ? 'active' : '']" v-for="(tab,index) in tabsList"
					:key="tab.id" @click="tabsChange(tab.id)">
					{{tab.name}}
				</view>
			</view>
			<view class="goodsBox flex">
				<view class="cont-box" :style="{ '--layout-width': 100 / flowData.column - flowData.columnSpace + '%' }"
					v-for="(numVal, index) in flowData.column" :key="numVal">
					<view class="item-box" v-for="(item, j) in flowData[`column_${index + 1}`]" :key="j"
						@click="navToDetail(item)">
						<div class="imgBox">
							<image class="img-tip" :src="item.image" mode="widthFix" lazy-load />
						</div>
						<view class="tit-tip multi-line-omit">{{ item.title }}</view>
						<view class="info">
							<text class="price">￥ <text class="amount">{{ item.price }}</text></text>
							<!-- <text class="want">{{item.wantNum}}人想要</text> -->
						</view>
						<view class="empBox">
							<view class="avatar">
								<image class="avatar" :src="item.avatar" mode="widthFix" lazy-load />
							</view>
							<view class="name one-line-omit">{{item.empName}}</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- <tab-bar></tab-bar> -->
	</view>
</template>

<script>
	const localHomeListKey = '__local_home_list';
	export default {
		components: {},
		data() {
			return {
				search: '',
				moduleList: [{
						id: 1,
						name: '学习',
						icon: 'icon-jiaoyupeixun',
					},
					{
						id: 2,
						name: '数码',
						icon: 'icon-bijiben',
					},
					{
						id: 3,
						name: '服装',
						icon: 'icon-clothes-full',
					},
					{
						id: 4,
						size: 25,
						name: '食品',
						icon: 'icon-can',
					},
					{
						id: 5,
						size: 25,
						name: '运动',
						icon: 'icon-yundong',
					},
				],
				loadStatus: 'loadmore',
				list: [],
				flowData: {
					list: [], // 数据值
					column: 2, // 瀑布列数
					columnSpace: 2 // 瀑布列宽间距
				},
				activeTabs: 0,
				tabsList: [{
						id: 1,
						name: '热门商品'
					},
					{
						id: 2,
						name: '猜你喜欢'
					},
					{
						id: 3,
						name: '最新商品'
					},
				],
			}
		},
		created() {
			/* 初始化每一列的数据 */
			for (let i = 1; i <= this.flowData.column; i++) {
				this.$set(this.flowData, `column_${i}`, []);
			}
		},
		onLoad() {
			this.getHomepageGoods();
			setTimeout(() => {
				this.$nextTick(() => {
					this.initData(this.list); // 数据初始化
				});
			}, 200);
		},
		methods: {
			getHomepageGoods() {
				uni.request({
					url: this.$store.state.baseUrl + "/api/goods/home",
					success: (res) => {
						this.list = [];
						for (let item of res.data.data) {
							let newitem = {
								id: item.goods_Id,
								empName: item.user_Name,
								price: item.goods_Price,
								title: item.goods_Name,
								type: 1,
								image: item.image_Data,
								avatar: item.head_Portrait
							};
							this.list.push(newitem);
						}
						this.flowData.list = this.list;
						// uni.setStorageSync(localHomeListKey, this.list);
					}
				})
			},
			navToDetail(item) {
				console.log(item);
				uni.navigateTo({
					url: '/pages/product-details/product-details?item=' + encodeURIComponent(JSON.stringify(item))
				});
			},
			searchFocusFun() {
				uni.navigateTo({
					url: '/pages/search/search'
				})
			},
			deepCopy(source) {
				return JSON.parse(JSON.stringify(source))
			},
			tabsChange(id) {
				this.activeTabs = id - 1;
				console.log(id);
				let list = this.list.filter(item => {
					return item.type === id
				})
				this.initData(list)
			},
			initData(list) {
				const groupList = this.dynamicGrouping(list, this.flowData.column); // 数据动态分组
				groupList.forEach((item, i) => (this.flowData[`column_${i + 1}`] = item));
			},
			dynamicGrouping(data, i) {
				if (i <= 0) return "分组数必须大于0";
				const groups = [];
				for (let j = 0; j < i; j++) {
					groups.push([]);
				}
				for (let k = 0; k < data.length; k++) {
					const groupIndex = k % i;
					groups[groupIndex].push(data[k]);
				}
				return groups;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.flex {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}



	.container {
		padding-top: 170rpx;
		$borderRadius: 12rpx;

		.topBox {
			width: 100%;
			padding: 20rpx 20rpx;
			box-sizing: border-box;
			position: fixed;
			top: 0;
			left: 0;
			z-index: 111;
			background: linear-gradient(to bottom, rgb(71, 161, 251) 0%, rgb(222, 234, 243) 80%, rgb(225, 235, 241));

			.name {
				font-weight: 700;
				font-size: 30rpx;
				padding-left: 20rpx;
				margin-bottom: 10rpx;
			}

			.searchBox {

				.left {
					// width: 80%;
					flex: 1;
					height: 80rpx;
					padding: 10rpx;
					box-sizing: border-box;
					border: 1px solid #000;
					border-radius: 40rpx;
					background: #fff;
					overflow: hidden;
					margin-right: 20rpx;

					::v-deep .searchInput {
						height: 60rpx;

						.is-input-border {
							border: none !important;
						}
					}

					.btn {
						width: 80rpx;
						height: 100%;
						line-height: 60rpx;
						text-align: center;
						border-radius: 30rpx;
						background-color: #000;
					}
				}

				.right {
					.text {
						line-height: 20rpx;
					}
				}
			}

		}

		.moduleBox {
			padding: 0 30rpx;
			box-sizing: border-box;
			margin-top: 40rpx;

			.moduleItem {
				width: 16%;
				height: 140rpx;
				border-radius: 20rpx;
				color: #fff;
				justify-content: center;
				position: relative;
				overflow: hidden;
				background-image: linear-gradient(135deg, rgb(16, 186, 177), rgba(16, 186, 177, 0.4));

				// background: rgb(28, 192, 181);
				.name {
					width: 100%;
					text-align: center;
				}

				.content {
					text-align: center;
				}
			}

			.module2 {
				background-image: linear-gradient(135deg, rgb(240, 220, 23), rgba(240, 220, 23, 0.3));
			}

			.module3 {
				background-image: linear-gradient(135deg, rgb(239, 149, 138), rgba(239, 149, 138, 0.3));
			}

			.module4 {
				background-image: linear-gradient(135deg, rgb(245, 109, 93), rgba(245, 109, 93, 0.3));
			}

			.module5 {
				background-image: linear-gradient(135deg, rgb(98, 124, 245), rgba(98, 124, 245, 0.3));
			}
		}

		.listBox {
			margin-top: 40rpx;
			padding: 0 80rpx;
			box-sizing: border-box;

			.tabsBox {
				.tabsItem {
					width: 31%;
					height: 60rpx;
					line-height: 60rpx;
					border-radius: 40rpx;
					text-align: center;
					color: #000;
					background: #ccc;
				}

				.active {
					background: rgb(157, 208, 253);
				}
			}

			.goodsBox {
				align-items: flex-start;
				margin-top: 20rpx;

				.cont-box {
					width: var(--layout-width);

					.item-box {
						width: 100%;
						padding-bottom: 20rpx;
						margin-bottom: 30rpx;
						background-color: #fff;
						border-radius: $borderRadius;
						box-shadow: 0rpx 3rpx 6rpx rgba(0, 46, 37, 0.08);

						.imgBox {
							padding: 20rpx;
							box-sizing: border-box;

							.img-tip {
								width: 100%;
								border-radius: $borderRadius;
							}
						}


						.tit-tip {
							text-align: justify;
							font-size: 30rpx;
							padding: 10rpx 20rpx 0;
							font-weight: 900;
						}

						.info {
							padding: 0 10rpx;
							box-sizing: border-box;
							margin-top: 20rpx;

							.price {
								font-size: 28rpx;
								color: rgb(240, 112, 105);

								.amount {
									font-size: 32rpx;
									font-weight: 700;
								}
							}

							.want {
								color: rgba(0, 0, 0, 0.4);
								font-size: 24rpx;
								margin-left: 20rpx;
							}
						}

						.empBox {
							padding: 0 10px;
							box-sizing: border-box;
							width: 100%;
							display: flex;
							justify-content: flex-start;
							align-items: center;

							.avatar {
								width: 30rpx;
								height: 30rpx;
								border-radius: 50%;
								margin-right: 14rpx;
							}

							.name {
								font-size: 24rpx;
							}
						}

					}
				}
			}
		}
	}



	/* 多行省略 */
	.multi-line-omit {
		word-break: break-all; // 允许单词内自动换行，如果一个单词很长的话
		text-overflow: ellipsis; // 溢出用省略号显示
		overflow: hidden; // 超出的文本隐藏
		display: -webkit-box; // 作为弹性伸缩盒子模型显示
		-webkit-line-clamp: 2; // 显示的行
		-webkit-box-orient: vertical; // 设置伸缩盒子的子元素排列方式--从上到下垂直排列
	}

	/* 单行省略 */
	.one-line-omit {
		width: 100%; // 宽度100%：1vw等于视口宽度的1%；1vh等于视口高度的1%
		white-space: nowrap; // 溢出不换行
		overflow: hidden; // 超出的文本隐藏
		text-overflow: ellipsis; // 溢出用省略号显示
	}
</style>