<template>
  <view class="container">
	  <!-- 顶部 卖家头像 名称 -->
	  <view class="navigation-bar">
	  	<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx" right-width="160rpx">
	  		<block slot="left">
	  			<uni-icons @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
	  			<img :src="sellerAvatar" class="avatar" >
				<text class="navi-header-text" style="font-size:1.15em">{{sellerName}}</text>
	  		</block>
	  		
	  	</uni-nav-bar>
	  </view>
    <!-- 商品信息部分 -->
    <view class="goods-body">
    	<view class="goods-info-container">
    			<view class="goods-image">
    				<!-- 商品图片部分 -->
    				<img :src="image" class="item-image"/>
    			</view> 
    			<!-- 商品文字部分 -->
    			<view>
    				<p class="item-name">{{ name }}</p>
    				<view class="buy-goods">
					<p class="item-price">￥{{ price }}</p>
					<button @click="buy()" class="buy">立即购买</button>
					</view>
    			</view>
				
    	</view>
    </view>

    <!-- 聊天记录部分 -->
    <view class="chat-history" >
      <view v-for="message in messages" 
	  :key="message.id" 
	  :class="{ 'sent': message.from === 'me', 'received': message.from !== 'me' }"
	  :ref="chatContainer">
		      <text class="message--text">{{ message.text }}</text>
      </view>
    </view>
	<!-- 底部 图片 输入 发送  -->
	<view class="bottom-bar">
		 <uni-icons @click="sendImage" class="file-image" type="image" size="30" ></uni-icons>
		<view class="text-info">
	      <textarea 
		  v-model="newMessage" 
		  placeholder="想和TA说点什么..." 
		  /></textarea>
	      </view>
		  <button @click="sendMessage" class="send-btn">发送</button>
	    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {
	sellerAvatar: '../../static/cat.png',
	sellerName:'little',	
     imageStyles:{
     	border:{
     		width:1,
     		radius:'10px'
     	}
     },
     image:'../../static/xiaomi.png',
     name:'出一个小米手环2 全新未拆',
     price:'25',
      messages: [
        { id: 1, from: 'me', text: '你好，还有货吗？' },
        { id: 2, from: 'other', text: '还有货。' },
        { id: 3, from: 'me', text: '好，我下单了。' },
        { id: 4, from: 'other', text: '好的，我会尽快发货。' }
      ],
	  newMessage: '', // 存储新消息的变量
		nextMessageId: 5 // 下一个消息的ID，用于生成唯一的消息ID
    };
  },
    methods: {
		goBack(){
				  uni.navigateBack();
		},
		buy(){
			//立即购买
		},
		sendImage(){
			//发送图片
		},
       sendMessage() {
		   //发送消息
        if (this.newMessage.trim() === '') {
          return;
        }
  
        // 构造新消息对象
        const newMsg = {
          id: this.nextMessageId++,
          from: 'me',
          text: this.newMessage
        };
  
        // 将新消息添加到聊天记录中
        this.messages.push(newMsg);
  
        // 清空输入框
        this.newMessage = '';
		
      },
      
    }
};
</script>

<style>
	/* 顶部 */
	.navigation-bar {
	  position: fixed;
	  top: 0;
	  width: 100%;
	  z-index: 1000; /* 确保导航栏在最上层 */
	}
	.navi-header-text {
		color: #000;
	}
	/* 头像 */
	.avatar {
	  width: 30px; 
	  height: 30px;
	   border-radius: 50%;
	   margin-left: 10px;
	   margin-right: 10px;
	}
/* 商品信息 */
	.goods-body{
		background-color: #ffffff;
		border-radius: 5px;
		height:80px;
		margin-bottom:10px;
		display: flex;
		align-items: center;
		width: 100%;
		position: fixed;
		top: 40px;
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
		margin-bottom:15px;
		font-weight: bold;
		opacity:0.8;
	}
		
	.buy-goods{
		display: flex;
		align-items: center;
		margin-bottom:8px;
	}
	.item-price{
	    margin-left:20px;
	    font-size: 11px;
		font-weight: bold;
		color:red;
		opacity:0.8;
	}
	.buy{
	margin-right:20px;
	 font-size: 11px;
	 border-radius: 50px;
	 background: #e33b3b;
	color:#fff;
	font-weight: bold;
	}
	/* 聊天信息 */
.chat-history {
  margin-top: 80px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
   overflow-y: auto;
   padding-bottom: 100px; /* 留出足够的空间，避免被遮挡 */
}
.message-container {
  display: flex;
  align-items: center;
}

/* 发送 */
.sent {
  text-align: right;
  color: #fff;
  background-color: #409EFF;
  padding: 5px;
  margin: 5px 20px;
  border-radius: 10px;
   align-self: flex-end;
    max-width: 70%;
}
/* 接收 */
.received {
  text-align: left;
  color: #333;
  background-color: #fff;
  padding: 5px;
  margin: 5px 20px;
  border-radius: 10px;
    max-width: 70%;
}
/* 底部 */
.bottom-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color:#f5f5f5;
   position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
	
}
/* 输入文本 */
.text-info{
	 flex: 1;
	 margin-right: 5px;
	 margin-left: 5px;
	 color: #000;
	 border-radius: 15px;
	 background-color: #fff;
	 border: 1px solid #f5f5f5;
	 padding: 5px 10px;
}
 .text-info textarea {
    width: 100%;
    height: 100%;
    resize: none;
    border: none;
    outline: none;
    padding: 5px 10px;
	 overflow-y: hidden;
  }
.file-image{
	height:20px;
	width:20px;
	margin-right: 10px;
}
.send-btn{
	height:35px;
	border-radius: 30px;
	background:#409EFF;
	color:#fff;
	margin-right: 18px;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
