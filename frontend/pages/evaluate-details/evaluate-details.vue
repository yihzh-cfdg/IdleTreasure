<template>
	<view class="container">
		<view class="navigation-bar">
		<!-- 导航栏 -->
			<uni-nav-bar dark :fixed="true"  background-color="#f5f5f5" status-bar left-width="250rpx" right-width="160rpx">
				<block slot="left">
					<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em" >评价详情</text>
				</block>
			</uni-nav-bar>		
		</view>
		
		<view class="goods-body">
			<view class="goods-info-container">
					<view class="goods-image">
						<!-- 商品图片部分 -->
						<img :src="image" class="item-image"/>
					</view> 
					<!-- 商品文字部分 -->
					<view>
						<p class="item-name">{{ name }}</p>
						<p class="item-price">实付款￥{{ price }}</p>
					</view>
			</view>
		</view>
		
		<view class="evaluate-body">
		<!--评价内容-->
		
		<view class="cart">
			 <!-- 卖家 -->
			<view style="display: flex; align-items: center;">
				<img :src="sellerAvatar" class="avatar" >
				<p class="name">{{ sellerName }}</p>
				<p class="name-type">卖家</p>
				<!-- 评价标签 -->
				<button class="label-button" 
				:class="{ 'good': sellerLabel === 'good',
				'average': sellerLabel === 'average',
				'bad': sellerLabel === 'bad' }">
				{{ getLabel(sellerLabel) }}</button>
			
			</view>
				<!--评价信息-->
				<p class="evaluate-info">{{ sellerEvaluate }}</p>
				<view>
					<!--评价时间-->
					<p class="evaluate-time">{{ sellerTime }}</p>
				</view>
		</view>
		
			
			<view class="cart">
				 <!-- 买家 -->
				<view style="display: flex; align-items: center;">
					<img :src="customerAvatar" class="avatar" >
					<p class="name">{{ customerName }}</p>
					<p class="name-type">买家</p>
					<!-- 评价标签 -->
					<button class="label-button"
					:class="{ 'good': customerLabel === 'good',
					'average': customerLabel === 'average',
					'bad': customerLabel === 'bad' }">
					{{ getLabel(customerLabel) }}</button>
				
				</view>
					<!--评价信息-->
					<p class="evaluate-info">{{ customerEvaluate }}</p>
					
					<view class="image-list">
					<!-- 商品图片列表 -->	
					    <image v-for="(image, index) in imageList" 
						:key="index" 
						:src="image" 
						class="evaluate-image" 
						@click="previewImage(index)"/>
					  </view>
					
					<view>
					<!--评价时间-->		
						<p class="evaluate-time">{{ customerTime }}</p>
					</view>
			</view>
			
		</view>	

		
	</view>
	
	

</template>

<script>
	export default {
		data() {
			return {
				image:'../../static/xiaomi.png',
				name:'出一个小米手环2 全新未拆',
				price:'25',
				checkbox1: [0],
				terms: [{
									text: '匿名评价',
									value: 0
								}],
				selectedOption: '',  // 初始化选中的单选按钮状态	
					
				//卖家	
				sellerAvatar: '../../static/cat.png',
				sellerName: '爱吃的丸子',
				sellerEvaluate: '收获速度很快！',
				sellerTime:'2023-10-12 21:26:38',
				sellerLabel: 'good',
				
				//买家
				customerAvatar: '../../static/cat2.png',
				customerName: '丸子',
				customerEvaluate: '一般般',
				customerTime:'2023-10-12 19:26:38',
				customerLabel: 'average',
				imageList: [
				        '../../static/shoes.png',
				        '../../static/book.png',
				        '../../static/book.png',
						'../../static/shoes.png',
				        // 其他图片地址
				      ],
			};
		},
		methods:{
			goBack(){
				uni.navigateBack();
			},
			getLabel(label) {
			// 获取评价标签的名称
				 if (label === 'good') {
			          return '很赞';
			        } else if (label === 'average') {
			          return '一般';
			        } else {
			          return '不满';
			        }
			      },
			// 点击预览图片
			  previewImage(index) {
			    uni.previewImage({
			      current: this.imageList[index],
			      urls: this.imageList 
			    });
			  }	  
		}
	};
</script>

<style lang="scss">
	.navi-header-text{
		color: #000;
		/* 文字颜色 */
	}
	/* 商品信息 */
	.goods-body{
		background-color: #ffffff;
		border-radius: 5px;
		height:80px;
		margin-bottom:10px;
		display: flex;
		align-items: center;
	}
	.goods-info-container{
		display: flex;
		align-items: center;	
	}
	.item-image{
	    height:52px;
		width:52px;
		margin-left:40px;
	}
	.item-name{
		font-size: 14px;
		margin-left:20px;
		margin-bottom:10px;
		font-weight: bold;
		opacity:0.5;
	}
	.item-price{
	    margin-left:20px;
	    font-size: 11px;
		font-weight: bold;
		opacity:0.5;
	}
	/* 评价信息 */
	.evaluate-body{
		background-color: #F5F5F5;
		border-radius: 5px;
	}
	/*评价卡片*/
	.cart{
		margin: 10px;
		padding: 10px;
		border-radius: 10px;
		background-color: #FFFFFF;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	}
	.avatar {
	  width: 40px; 
	  height: 40px;
	   border-radius: 50%;
	   margin-top: 6px;
	   margin-left: 10px;
	   margin-right: 10px;
	}
	.name {
	  font-size: 13px; 
	  font-weight: bold;
	}
	.name-type{
		margin-left: 10px;
		opacity:0.5;
		font-weight: bold;
	}
	.evaluate-time{
		opacity:0.5;
		font-size: 13px;
	}
	.evaluate-info{
		margin-top:10px;
		margin-bottom:10px;
		font-size: 13px;
	}
		
	/* 评价标签样式 */
	.label-button {
	  padding: 5px 10px;
	  border-radius: 50upx;
	  margin-right: 10px;
	  font-size: 12px;
	  cursor: pointer;
	  width:60px;
	  height:40px;
	}
	
	/* 评价标签的不同状态样式 */
	.good {
	  background-color: #8CC6FF; 
	  color: #fff;
	  font-weight: bold;
	  
	}
	.average {
	  background-color: #FFB85C; 
	  color: #fff;
	  font-weight: bold;
	}
	.bad{
	  background-color: #FF6E7F; 
	  color: #fff;
	   font-weight: bold;
	}
	
	
	/* 图片列表横向滑动 */
	.image-list {
	  width: 100%; 
	  overflow-x: auto; /* 横向滑动 */
	  white-space: nowrap; /* 图片横向排列 */
	}
	.image-container {
	  display: flex; 
	}
	.evaluate-image {
	  width: 100px; 
	  height: 100px; 
	  margin-right: 10px; 
	}
</style>
