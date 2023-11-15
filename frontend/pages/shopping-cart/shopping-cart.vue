<template>
  <view>
	<!--购物车列表-->
    <view class="cart-container">
			<view class="cart-item" v-for="(item, index) in cartItems" :key="index">
			<!--商品信息-->
			<view class="item-details">
				<button @click="updateSelection(item)" 
						class="select-btn" 
						:class="{ 'selected': item.selected }">
					{{ item.selected ? '' : '' }}
				</button>
				<img :src="item.image" class="product-image"/>
				<view class="item-text">
				<h3 class="item-name">{{ item.name }}</h3>
				<p class="item-price">￥{{ item.price }}</p>
						<!--数量控制按键-->
						<view class="quantity-controls">
							<button @click="decreaseQuantity(item)" class="quantity-btn">-</button>
							<p class="quantity">{{ item.quantity }}</p>
						<button @click="increaseQuantity(item)" class="quantity-btn">+</button>
						</view>
						<!--卖家信息-->
						<view class="store-info"> 
							<img :src='"../../static/cart.png"' class="store-avatar" />
							<p class="store-name">{{ item.storeName }}</p>
						</view>	
				</view>
			</view>
			
			</view>
	 
		<!--总价结算-->
		<view class="total">
			<view class="total-price">
			<p >合计: ￥{{ total | toFixed  }}</p>
			<button @click="deleteItems" class="delete-btn">删除</button>
			<navigator url="../check/check" >
			<button @click="checkout" class="liquid-btn">结算</button>
			</navigator>
			</view>
		</view>
	
	</view>
  </view>
 
</template>

<script>
export default {
  data() {
    return {
      cartItems: [
        {
          id: 1,
          name: '大学英语速记词汇书',
          price: 25.8,
          image: '../../static/book.png',
          quantity: 2,
          storeName: '爱吃的丸子',
          selected: false
        },
        {
          id: 2,
          name: 'ULTRABOOST 20 ',
          price: 250,
          image: '../../static/shoes.png',
          quantity: 1,
          storeName: 'bloon',
          selected: false
        },
        {
          id: 3,
          name: 'ULTRABOOST 20 ',
          price: 250,
          image: '../../static/shoes.png',
          quantity: 1,
          storeName: 'bloon',
          selected: false
        },
        {
          id: 4,
          name: 'ULTRABOOST 20 ',
          price: 250,
          image: '../../static/shoes.png',
          quantity: 1,
          storeName: 'bloon',
          selected: false
        },
		
		{
		  id: 5,
		  name: 'ULTRABOOST 20 ',
		  price: 250,
		  image: '../../static/shoes.png',
		  quantity: 1,
		  storeName: 'bloon',
		  selected: false
		}
        // 其他商品...
      ]
    };
  },
  computed: {
    total() {
      return this.cartItems.reduce((acc, item) => {
        if (item.selected) {
          acc += item.price * item.quantity;
        }
        return acc;
      }, 0);
    }
  },
  filters: {
	  //总价结果保留两位小数
    toFixed(value) {
      return value.toFixed(2);
    }
  },
  methods: {
    updateSelection(item) {
      item.selected = !item.selected;
    },
    checkout() {
      const selectedItems = this.cartItems.filter(item => item.selected);
      // 进行结算操作
      console.log('Selected items:', selectedItems);
    },
	decreaseQuantity(item) {
		//减少商品数量
	    if (item.quantity > 1) {
	      item.quantity--;
	    }
	  },
	  increaseQuantity(item) {
		  //增加商品数量
	    item.quantity++;
	  },
	   deleteItems() {
		   //删除商品
	      this.cartItems = this.cartItems.filter(item => !item.selected);
	    }
  }
};
</script>

<style>
/*购物车列表*/	
	.cart-container {
	  display: grid;
	  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* 自动适配列数 */
	  grid-gap: 5px; /* 设置间距 */
	  padding-bottom: 100px; /* 留出足够的空间，避免被总价结算部分遮挡 */
	}
	
	.cart-item {
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
		height:123px;
	    margin-bottom: 10px;
	    padding: 10px;
	    border-radius: 10px;
		background-color: #FFFFFF;
	    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	}
	
	.product-image{
		width: 110px;
		height: 110px;
		margin-left: 10px;
		margin-right: 5px;
	}
	/*商品信息*/
	.item-details {
	    display: flex;
	    align-items: center;
	}
	/*选择按键*/
	.select-btn{
		width: 20px;
		height: 20px;
		padding: 1upx 10px; /* 调整按钮的内边距 */
		font-size: 14px; /* 减小按钮的字体大小 */
		background-color: #FFFFFF;
		border: none;
		cursor: pointer;
		border-radius: 50%;
		box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
		transition: background-color 0.3s;
	}
	/*选中后变蓝*/
	.selected {
	    background-color: #409EFF;
	}
	/*商品信息*/
	.item-text{
		margin-top: 40px;
		margin-bottom: 20px;
	}
	.item-name{
		font-size: 14px;
	}
	.item-price{
	    color:red;
	    margin-top: 10px;
	    margin-bottom: 10px;
	    font-size: 14px;
	    font-weight: bold;
	}
	/*增减按键*/
	.quantity-controls {
	    display: flex;
	    align-items: center;
	}
	.quantity-btn {
		padding: 25.36 25.36; /* 调整按钮的内边距 */
	    font-size: 14px; /* 减小按钮的字体大小 */
	    background-color: #FFFFFF;
	    border: none;
	    cursor: pointer;
		box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
	    transition: background-color 0.3s;
	}
	.quantity {
	    margin: 0 5px;
	    font-size: 18px;
	}
	/*卖家信息*/
	.store-info{
		display: flex;
		align-items: center;
		margin-right: 10px;
		bottom: 0;
		left: 0;
		margin: 5px;
	}
	.store-avatar {
	  width: 15px;
	  height: 15px;
	  border-radius: 50%;
	  margin-right: 6px;
	}
	.store-name{
		margin-left: 5px;
		font-size: 11px;
		color:#000000;
		opacity:0.5;
	}
	
	/*固定结算部分*/
	.total{
	  position: fixed;
	  bottom: 50px; /* 距离底部的距离 */
	  height:60px;
	  left: 0;
	  right: 0;
	  background-color: #fff;
	  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}
	/*总价*/
	.total-price {
	  margin-top: 20px;
	  margin-left: 20px;
	  display: flex;
	  align-items: center;
	  font-weight: bold;
	  color:#FA6400;
	  
	}
	/* 删除按键样式 */
	.delete-btn {
		 position: absolute;
		  bottom: 5px;
		  left: 180px; /* 距离左侧的距离 */
		  width: 80px;
		  height: 48px;
		  padding: 0px 20px;
		  border: none;
		  border-radius: 20px;
		  background-color: #FF4949;
		  color: #FFFFFF;
	}
	/*结算按键*/
	.liquid-btn {
		position: absolute;
		  bottom: 5px;
		  right: 20px; /* 距离右侧的距离 */
		  width: 80px;
		  height: 48px;
		  padding: 0px 20px;
		  border: none;
		  border-radius: 20px;
		  background-color: #409EFF;
		  color: #FFFFFF;
	}
</style>