<template>
  <view class="mypage-container">
    <!-- 自定义头部 -->
    <view class="head">
		<text class="wenzi" >订单详情</text>
    </view>
	
	<!-- 订单状态 -->
	<view class="ordercard">
		
		<uni-collapse-item :title="condition" :show-animation="true">
			<view class="content">
				<uni-steps :options="list1" :active="active" />
			</view>
		</uni-collapse-item>
		
		<view class="ordermess">
			
			<!-- 商品图片 -->
			<image class="avatar" :src="picture" mode=""></image>
			<!-- 订单信息 -->
			<view class="message-header" >
				
				  <!-- 商品信息 -->
				  <view class="top-text">
						<!-- 商品描述 -->
						<view class="message">
							
						{{order.message}}
						</view>
						
						<!-- 价格 -->
						<view class="prices">
							<img src="../../static/money.png" style="height: 25px; width: 25px;" />
							{{order.price}}
						</view>
						<view class="site">
						<!-- 发货地址 -->
						<uni-icons type="location-filled"></uni-icons>
							{{order.site}}
						</view>
				  </view>
			</view>
			
				
		</view>
		
		<view class="pricesmess">
			<view class="total" >
					商品总价
				<view class="prices" >
					¥{{order.total}}
				</view>
				
			</view>
			
			<view class="trans">
					运费
				<view class="prices">
					¥{{order.trans}}
				</view>
			</view>
			
			<view class="reality">
					实付款
				<view class="prices">
					<img src="../../static/money.png" alt="" />
					{{order.reality}}
				</view>
			</view>
			
		</view>	
		
		<view class="order-content">
			
		  <view class="order">
			订单编号
			<view class="" style="font-weight: 700; color: darkgrey; font-size: 11px;">
				{{number.ordernumber}}
			</view>
			<view >
				|复制
			</view>
		  </view>
		  
		  <view class="Wechat">
		  	微信交易号
			<view class="" style="color: darkgrey; font-size: 11px; font-weight: 700;">
				{{number.wechat}}
			</view>
			<view >
				|复制
			</view>
		  </view>
		  
		  <view class="address">
				收货地址
				<view class="" style="font-weight: 700; color: darkgrey; font-size: 11px;">
					{{number.site}}
				</view>
				<view class="" >
					|复制
				</view>
		  </view>
		  
		</view>
		
		<!-- 交易时间信息 -->
		<view class="order-time">
			<view v-for="(list, i) in lists" :key="i" class="message-time">
				<view class="left">
					{{list.text1}}
				</view>
				<view class="right">
					{{list.text2}}
				</view>
			</view>
			
		</view>
		
		
		<view class="order-button">
			<uni-goods-nav :options="options" :fill="true" :button-group="buttonGroup" @click="onClick"
				@buttonClick="buttonClick" />
		</view>
		
		
	</view>
		
  </view>
</template>

<script>
	
	export default {
		data() {
			return {	 
				 
				//订单描述信息
				picture:'../../static/watchs.png',
				condition:'双方待评价',
				
				order:
				{
					message:'出一个小米手环，想要的可以联系',
					site:'福州大学旗山校区35号楼',
					price:200.02,
					total:200.02,
					trans:0.02,
					reality:200.04,
				},
				
				number: {
					ordernumber:'192837198274198247xxxx13124124',
					wechat:'02840192741749820074xxxxxxxx',
					site:'福建省福州市闽侯县上街镇福州大学旗山校区1号楼',
				},
				
				
				
				active: 3, //目前进度选中状态
				
				list1: [{    //进度条内容
					title: '已下单'
				}, {
					title: '已付款'
				}, {
					title: '已发货'
				}, {
					title: '交易成功'
				},{
					title:'已评价',
				}
				],
				options: [{
							icon: 'chat',
							text: '联系卖家',
						}, 
						],
						
				buttonGroup: [{
								text: '取消订单',
								backgroundColor: 'linear-gradient(90deg, #b8b8b8, #b8b8b8)',
								color: '#fff'
							},
							
							{
								text: '前往付款',
								backgroundColor: 'linear-gradient(90deg, #409eff, #409eff)',
								color: '#fff'
							}
						],
				lists:[{
					text1:'卖家昵称',
					text2:"无敌师爷",
					},
					{
						text1:'下单时间',
						text2:"2023-11-11 12:09:01",
					},
					{
						text1:'付款时间',
						text2:"2023-11-11 12:10:59",
					},
					{
						text1:'发货时间',
						text2:"2023-11-12 12:16:59",
					},
					{
						text1:'成交时间',
						text2:"2023-11-12 12:20:59",
					}
				]
			}
		},
		onLoad() {
	
		},
		methods: {
			
		}
	}
</script>


<style scoped>
	.mypage-container {
	  background-color: #f2f2f2;
		margin-top: 20px;
		flex: 1;
	}
	
  .head {
    
	display: flex;
	height: 50px;
	font-size: 50rpx;
	margin-top: 30px;
	background-color: #fff;
	

  }
	.wenzi{
		font-size: 20px; 
		display: flex;
		color: black;
		padding-left: 145px; 
		margin-top: 15px;
		
	}
	.ordercard{
		background-color: #fff;
		margin-top: 10px;
	}
	.orderconition {
	  display: flex;
	  background-color: #fff;
	  padding: 20rpx 140rpx 20rpx 50rpx;
	  
	}
	
	.ordermess{
		
		background-color: #fff;
		height: 150px;
		display: grid;   //实现呈现列的布局
		grid-template-columns: 1fr 6fr ;
		grid-gap: 2px;
			
		}
	
	.ordermess .avatar {
		margin-top: 5px;
		width: 114.67px;
		height: 139.03px;
		margin-left: 7px;
	}

  .message-header {
    display: flex;
    font-size: 30rpx;
	padding-left: 20px;
	padding-top: 10px;
	flex-direction:column;
  }


  .top-text .message {
    font-size: 20rpx;
    font-weight: bold;
  }

 

  .top-text .prices {
    font-size: 25px;
	padding-top: 20px;
	color:rgba(255, 0, 0, 1);
	font-weight: 700;
  }
	.top-text .site {
	font-size: 20rpx;
	padding-top: 20px;
	}

	.pricesmess{
	
		height: 90px;
		margin-left: 15px;
		margin-right: 15px;
		background-color: #fff;
		
	}

	.total{
		display: grid;   //实现呈现列的布局
		grid-template-columns: 20fr 20fr ;
		grid-gap: 170px;
		font-family: 'Courier New', Courier, monospace;
			
	}
	.total .prices{
		font-size:18px ;
		font-weight: 700; 
		color: darkgrey; 
	}
	
	.trans{
		display: grid;   //实现呈现列的布局
		grid-template-columns: 20fr 15fr ;
		grid-gap: 170px;
		font-family: 'Courier New', Courier, monospace;
		padding-top: 5px;
	}
	
	.trans .prices{
		font-size:18px ;
		font-weight: 700; 
		color: darkgrey; 
	}
	.reality{
		display: grid;   //实现呈现列的布局
		grid-template-columns: 20fr 30fr ;
		grid-gap: 200px;
		font-family: 'Courier New', Courier, monospace;
		padding-top: 5px;
	}
	
	.reality .prices{
		font-size:18px ;
		font-weight: 700; 
		color: rgba(255, 0, 0, 1);
	}
	
	.order-content{
		height: 160px;
		margin-left: 7px;
		border-top: 0.1px solid ; 
		border-color: darkgrey;
		font-size: 14px;
		
	}
	.order{
		display: grid;
		grid-template-columns: 1.2fr 2fr 1fr;
		grid-gap: 15px;
		padding-top: 18px;
	}
	
	.Wechat{
		display: grid;
		grid-template-columns: 1.4fr 2fr 0.8fr;
		grid-gap: 15px;
		padding-top: 20px;
	}
	
	
	.address{
		display: grid;   //实现呈现列的布局
		grid-template-columns: 1fr 2fr 0.5fr ;
		grid-gap: 15px;
		padding-top: 20px;
	}
	
	.order-time{
		padding-top: 5px;
		margin-left: 8px;
		
	}
	
	.message-time{
		display: grid;
		grid-template-columns: 1fr 1fr ;
		grid-gap: 70px;
		padding-top: 5px;
	}
	
	.right{
		font-size:10px ;
		font-weight: 700; 
		color: darkgrey; 
	}
	.order-button{
			display: flex; 
			
			flex-direction: column;
			position: fixed;
			left: 0;
			right: 0;
			bottom: 0;
  
	}


</style>
