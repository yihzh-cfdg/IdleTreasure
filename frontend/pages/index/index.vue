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
					<uni-icons :type="item.icon" :size="item.size ? item.size : 20" color="#fff" custom-prefix="iconfont"></uni-icons>
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
					<view class="item-box" v-for="(item, j) in flowData[`column_${index + 1}`]" :key="j">
						<div class="imgBox">
							<image class="img-tip" :src="item.image" mode="widthFix" lazy-load />
						</div>
						<view class="tit-tip multi-line-omit">{{ item.title }}</view>
						<view class="info">
							<text class="price">￥ <text class="amount">{{ item.price }}</text></text>
							<text class="want">{{item.wantNum}}人想要</text>
						</view>
						<view class="empBox">
							<view class="avatar">
								<image class="avatar" :src="item.image" mode="widthFix" lazy-load />
							</view>
							<view class="name one-line-omit">{{item.empName}}</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<tab-bar></tab-bar>
	</view>
</template>

<script>
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
				list: [{
						empName: '摸鱼小狗',
						price: '35.66',
						title: 'CINESSD 男拖鞋2022新款时尚时尚最时尚',
						wantNum: 0,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/43390/15/19929/131606/6370b921Eefed6acc/8e9780a1736357e6.jpg!q70.dpg.webp',
					},
					{
						empName: '萨达',
						price: '75.20',
						title: '加湿器',
						wantNum: 3,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/208109/6/29643/155027/63ec3d92F817bd559/90a96c4dd880e40f.jpg!q70.dpg.webp',
					},
					{
						empName: 'dasf',
						price: '385.36',
						title: '气囊防摔超薄保护套镜头全包透明软壳',
						wantNum: 43,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/5972/32/17361/80842/626deb75E66225786/e7f1ff06504a1cca.jpg!q70.dpg.webp',
					},
					{
						empName: '周四',
						price: '784.03',
						title: '小米Redmi Buds3青春版 真无线蓝牙耳机 入耳式耳机 蓝牙耳机 小米无线耳机 蓝牙5.2 苹果华为手机通用',
						wantNum: 5,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/97453/11/35123/120822/63eb4ec8F554a9a71/5c0332fa1b04d502.jpg!q70.dpg.webp',
					},
					{
						empName: '你好',
						price: '91.00',
						title: '海尔（Haier）大容量囤货海尔（Haier）冰箱京东小家双开门冰箱532升电冰箱一级变频大超薄家用冰箱风冷无霜 BCD-532WGHSS8EL9U1',
						wantNum: 33,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/97453/11/35123/120822/63eb4ec8F554a9a71/5c0332fa1b04d502.jpg!q70.dpg.webp',
					},
					{
						empName: '问问',
						price: '1.52',
						title: '卫龙魔芋爽辣条休闲零食香辣素毛肚180g/袋约12小包即食小零食',
						wantNum: 45,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/97453/11/35123/120822/63eb4ec8F554a9a71/5c0332fa1b04d502.jpg!q70.dpg.webp',
					},
					{
						empName: '问问',
						price: '661.90',
						wantNum: 35,
						type: 1,
						title: '卫龙魔芋爽辣条休闲零食香辣素毛肚180g/袋约12小包即食小零食',
						image: 'https://img13.360buyimg.com/n2/s370x370_jfs/t1/51330/4/17889/64744/63ca2564F4cfd8ce3/a9ed18603e2855f8.jpg!q70.jpg.webp',
					},
					{
						empName: '哈哈哈哈',
						price: '164.37',
						title: '鞋子鞋子鞋子',
						wantNum: 3,
						type: 1,
						image: 'https://img10.360buyimg.com/n2/s370x370_jfs/t1/195846/4/32797/40099/63e348fbF14993564/472de8ed0c40f206.jpg!q70.jpg',
					},
					{
						empName: '试试',
						price: '1678.00',
						title: '优资莱（UZERO） 优资莱绿茶保湿礼盒洁面乳爽肤水乳液精华套装补水护肤品女 八件套礼盒',
						wantNum: 7,
						type: 3,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/200469/24/30778/90107/63989b02E6f47594f/cb91265ba594e7cb.jpg!q70.dpg.webp',
					},
					{
						empName: '筛选',
						price: '924.64',
						title: '兰蔻小黑瓶50ml 修护保湿',
						wantNum: 35,
						type: 1,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/160110/20/21070/176153/63eb2b42F599b4cb6/f466a798d5f63d83.jpg!q70.dpg.webp',
					},
					{
						empName: '丽丽',
						price: '8243.50',
						title: '加湿器',
						wantNum: 3,
						type: 2,
						image: 'https://img14.360buyimg.com/mobilecms/s360x360_jfs/t1/54121/6/21408/129268/631593a7E87b6d12a/b3c650bf886c6a5f.jpg!q70.dpg.webp',
					},
				],
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
			this.flowData.list = this.deepCopy(this.list);
			this.$nextTick(() => {
				this.initData(this.list); // 数据初始化
			});
		},
		methods: {
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
				background-image: linear-gradient(135deg, rgb(16,186,177), rgba(16,186,177,0.4));
				// background: rgb(28, 192, 181);
				.name {
					width: 100%;
					text-align: center;
				}
				.content{
					text-align: center;
				}
			}
			.module2{
				background-image: linear-gradient(135deg, rgb(240,220,23), rgba(240,220,23,0.3));
			}
			.module3{
				background-image: linear-gradient(135deg, rgb(239,149,138), rgba(239,149,138,0.3));
			}
			.module4{
				background-image: linear-gradient(135deg, rgb(245,109,93), rgba(245,109,93,0.3));
			}
			.module5{
				background-image: linear-gradient(135deg, rgb(98,124,245), rgba(98,124,245,0.3));
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
