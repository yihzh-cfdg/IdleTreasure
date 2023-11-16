<template>
	<view>
		<view>
			<view style="display: flex;align-items: center;margin: 20rpx;height: 52rpx;">
				<img src="../../static/back.png" style="width: 40rpx;height: 40rpx;">
				<view style="flex: 1;">
					<view  @click="changeIsClick()"
						style="background-color: white;border: 1px solid black;height: 52rpx;border-radius: 30rpx;padding: 0 20rpx;display: flex;align-items: center;">
						<view v-if="isClick == 1 && inputValue.length > 0"  style="background-color: rgb(246, 246, 246);border-radius: 10rpx;padding: 0 10rpx;display: flex;align-items: center;">
							{{inputValue}}
							<icon type="clear" size="16" style="margin-left: 10px;" @click="clearIcon"/>
						</view>
						<view v-if="isClick == 0" class="uni-input-wrapper">
							<input class="uni-input" focus :value="inputValue" @input="changeInput" @blur="handleBlur"/>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view>
			<view style="display: flex;justify-content: space-between;padding: 20rpx 100rpx;font-size: 40rpx;">
				<view @click="changeCurrent(0)" :style="{ fontWeight: current === 0 ? 'bold' : 'normal' }">商品</view>
				<view style="display: flex;align-items: center;">
					<view style="margin-right: 10rpx;">价格</view>
					<img src="../../static/jiantou.png" style="width: 40rpx;height: 40rpx;">
				</view>
				<view @click="changeCurrent(1)" :style="{ fontWeight: current === 1 ? 'bold' : 'normal' }">用户</view>
				<view style="display: flex;align-items: center;">
					<view style="margin-right: 10rpx;">筛选</view>
					<img src="../../static/saixuan-black.png" style="width: 40rpx;height: 40rpx;">
				</view>
			</view>
		</view>

		<view style="display: flex;flex-wrap: wrap;" v-if="current == 0">
			<view v-for="item,index in list" style="width: 50%;">
				<view style="padding: 10px;">
					<view><img :src="item.image"
							style="width: 100%;height: 450rpx;object-fit: cover;border-radius: 20rpx;"></view>
					<view class="line-view">{{item.title}}</view>
					<view style="color: red;margin-top: 10rpx;">￥{{item.price}}</view>
					<view style="display: flex;align-items: center;margin-top: 10rpx;">
						<img :src="item.image" style="width: 50rpx;height: 50rpx;border-radius: 50%;object-fit: cover">
						<view style="margin-left: 10rpx;">{{item.empName}}</view>
					</view>
				</view>
			</view>
		</view>
		<view v-if="current == 1" style="background-color: white;">
			<view v-for="item,index in list">
				<view style="display: flex;padding: 30rpx;">
					<view><img :src="item.image"
							style="width: 120rpx;height: 120rpx;border-radius: 50%;object-fit: cover"></view>
					<view style="margin-left: 30rpx;font-size: 18rpx;">
						<view style="font-size: 32rpx;font-weight: bold;">{{item.empName}}</view>
						<view style="color: #CCC;">闲置编号：1111122222</view>
						<view style="color: #CCC;">发布： 5 | 粉丝： 50</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				inputValue: '123',
				isClick:1,
				showClearIcon: false,
				current: 1,
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
			}
		},
		created() {
			if (this.inputValue > 0) {
				this.showClearIcon = true;
			}
		},
		methods: {
			changeIsClick(){
				this.isClick = 0
			},
			 handleBlur() {
			    this.isClick = 1
			  },
			changeInput: function(event) {
				this.inputValue = event.detail.value;
			},
			clearIcon() {
				this.inputValue = '';
				this.showClearIcon = false;
			},
			changeCurrent(index) {
				this.current = index
			}
		}
	}
</script>

<style scoped>
	.line-view {
		display: -webkit-box;
		-webkit-line-clamp: 1;
		-webkit-box-orient: vertical;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.uni-input-wrapper {
		display: flex;
		align-items: center;
		flex-direction: row;
		flex-wrap: nowrap;
		background-color: #FFFFFF;
	}

	.uni-input {
		flex: 1;
		background-color: #FFFFFF;
	}

</style>