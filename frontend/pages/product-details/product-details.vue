<template>
  <view class="container">
	  <!--顶部导航栏-->
	  <view class="box-bg">
		  <uni-nav-bar dark :fixed="true"  background-color="#ffffff" status-bar size="22" >
					 <!--返回-->
					<block slot="left">
						<view class="back" @click="goBack()">
							<uni-icons type="left" size="22" color="#333" />
						</view>
					</block>
						 <!--搜索-->
						<view class="input-view" @click="toggleSearch">
	  						<uni-icons class="input-uni-icon" type="search" size="18" color="#999" />
	  						<input confirm-type="search" class="nav-bar-input" type="text" placeholder="搜索你要的宝贝"
	  							@confirm="confirm" />
	  					</view>
						 <!--分享 更多-->
	  					<block slot="right">
	  						<view class="share" @click="toggleShare">
	  							<uni-icons type="redo" size="22" color="#333" />
	  						</view>
							<view class="more" @click="toggleMore">
								<uni-icons type="more-filled" size="22" color="#333" />
							</view>
	  					</block>
	  				</uni-nav-bar>
	  			</view>
	
	<!-- 内容部分 -->
	<view class="content">
		 <!--用户-->
		<view style="display: flex; align-items: center;">
			<img :src="userAvatar" class="avatar" >
			<p class="user-name">{{ userName }}</p>
			<button class="follow" size="mini" @click="toggleFollowStatus">
			    {{ followStatus === 'unfollowed' ? '+关注' : followStatus === 'followed' ? '已关注' : '取消关注' }}
			  </button>
		</view>
			<!--商品信息-->
			<h3 class="product-price">{{ productPrice }}</h3>
			<p class="product-info">{{ productInfo }}</p>
			<view class="image-list">
			    <!-- 商品图片列表 -->
			    <image v-for="(image, index) in imageList" 
				:key="index" 
				:src="image" 
				class="product-image"
				@click="previewImage(index)"/>
			  </view>
	  
    </view>
    
    <!-- 底部导航栏-->
    <view class="goods-carts">
		
    			<uni-goods-nav :options="options" 
				:fill="true" 
				:button-group="buttonGroup" 
				@click="onClick"
    			@buttonClick="buttonClick" />
    		</view> 

  </view>
  
</template>

<script>
export default {
  data() {
    return {
      userAvatar: '../../static/cat.png',
      userName: '爱吃的丸子',
      productPrice: '￥25.8',
      productInfo: '备考2023星火英语四六级词汇必备大乱序版大学英语速记词汇书',
	  imageList: [
	          '../../static/book.png',
	          '../../static/book.png',
	          '../../static/book.png',
	          // 其他商品图片地址
	        ],
	  options: [{
	  			icon: 'star',
	  			text: '收藏'
	  		}, {
					icon: 'cart',
					text: '加入购物车',
					info: 0
				}],
	  	    buttonGroup: [{
	  	      text: '卖同款',
	  	      backgroundColor: '#F7F7F7',
	  	      color: '#333'
	  	    },
	  	    {
	  	      text: '我想要',
	  	      backgroundColor: '#409EFF',
	  	      color: '#fff'
	  	    }
	  	    ],
			isFavorite: false,
			followStatus: 'unfollowed'
			
    };
  },
  methods: {
    goBack() {
      // 返回上一页的逻辑
    },
	toggleShare(){
	//分享商品
	},
	toggleMore(){
	//更多
	},
	toggleFollowStatus() {
	      if (this.followStatus === 'unfollowed') {
	        this.followStatus = 'followed';
	      } else if (this.followStatus === 'followed') {
	        this.followStatus = 'unfollowed';
			uni.showToast({
				title: `取消关注`,
				icon: 'none'
			});
	      }
	    },
	toggleSearch(){
		uni.redirectTo({
			url: '../search/search'//跳转到搜索页面
		})
	},	
	onClick(e) {
					if (!this.isFavorite && e.content.text === '收藏') {
					        // 第一次点击收藏，将图标变成实心星星，文本变为"已收藏"
					        this.options[0].icon = 'star-filled';
					        this.options[0].text = '已收藏';
					        this.isFavorite = true;
							uni.showToast({
								title: `成功收藏！`,
								icon: 'none'
							});
					      } else if (this.isFavorite && e.content.text === '已收藏') {
					        // 第二次点击收藏，取消收藏
					        this.options[0].icon = 'star';
					        this.options[0].text = '收藏';
					        this.isFavorite = false;
							uni.showToast({
								title: `取消收藏`,
								icon: 'none'
							});
					      }else if (e.content.text === '加入购物车') {
							uni.showToast({
							title: '成功加入购物车',
							icon: 'none'
							});
							}
					
				},
				buttonClick(e) {
					console.log(e)
					this.options[0].info++
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

<style>
	.container {
		flex: 1;
		background-color: #FFFFFF;
		height: 100vh;
	}
/* 顶部导航栏样式 */
	$nav-height: 30px;

	.box-bg {
		background-color: #F5F5F5;
		padding: 5px 0;
		border:0;
	}

	.input-view {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		// width: 500rpx;
		flex: 1;
		background-color: #f8f8f8;
		height: $nav-height;
		border-radius: 15px;
		padding: 0 15px;
		flex-wrap: nowrap;
		margin: 7px 0;
		line-height: $nav-height;
		 margin-left: -10px;
	}

	.input-uni-icon {
		line-height: 30px;
	}

	.nav-bar-input {
		height: 30px;
		line-height: 30px;
		/* #ifdef APP-PLUS-NVUE */
		width: 370rpx;
		/* #endif */
		padding: 0 5px;
		font-size: 12px;
		background-color: #f8f8f8;
	}
	
	.share {
	  margin-right: 10px; 
	}
/* 底部导航栏样式 */
.example-body {
		padding: 0;
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
	}

	.goods-carts {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		position: fixed;
		left: 0;
		right: 0;
		/* #ifdef H5 */
		left: var(--window-left);
		right: var(--window-right);
		/* #endif */
		bottom: 0;
	}

/* 内容部分样式 */
.content {
	background-color: #FFFFFF;
    border:0;
}
.avatar {
  width: 40px; 
  height: 40px;
   margin-top: 6px;
   margin-left: 10px;
   margin-right: 10px;
}
.user-name {
  font-size: 16px; 
  font-weight: bold;
}
.follow {
  margin-right: 10px;
  border-radius: 15px;
  background: #F5F5F5;
  color: #333;
  border:none;
  padding: 2px 15px;
  font-size: 18upx;
  font-weight: bold;
   transition: all 0.3s ease;
 
}
.product-image{
	width: 95%;
	margin-right: 10px;
	margin-left: 10px;
}
.product-price {
  color: red; 
  font-size: 24px; 
   margin-top: 15px;
   margin-left: 10px;
}
.product-info{
	margin-top: 15px;
	margin-right: 10px;
	margin-left: 10px;
}

</style>
