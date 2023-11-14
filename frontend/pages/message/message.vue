<template>
		<view class="aui-news-box">
			<view class="top">
				<view class="infor">
					消息
					<uni-icons class="icon" type="gear" size="25" @click="setting"></uni-icons>
				</view>
				
				
			</view>
			
			<uni-search-bar class="uni-mt-10" radius="40" placeholder="搜索你想要的宝贝" clearButton="auto"  bgColor="#f7f7f7"cancelButton="none" @confirm="search" />
			
			<view v-for="(list, i) in lists" :key="i" class="aui-news-item">
				<view class="aui-news-item-hd">
					<image :src="list.img"  class="img"/>
				</view>
				<view class="aui-news-item-bd">
					<h4>{{list.tit1}}</h4>
					<p>{{list.tit2}}</p>
					
					<p class="time">{{list.time}}</p>
				</view>
			</view>
		</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				lists: [{
						"tit1": "通知消息",
						"tit2": "来看看有没有错过什么~",
						"time": "08:48",
						"img": '/static/yellow.png'
					},
					{
						"tit1": "互动消息",
						"tit2": "小龙关注了你",
						"time": "08:48",
						"img": '/static/interact.png'
					},
					{
						"tit1": "空门苍",
						"tit2": "您已经确认购买稻荷",
						"time": "10-10",
						"img": '/static/16.png'
					},
					{
						"tit1": "鸣濑白羽",
						"tit2": "请问您还需要吗？大师球",
						"time": "10-10",
						"img": '/static/22.png'
					},
					{
						"tit1": "羽依里",
						"tit2": "飞跃夏日，请您等待卖家发货",
						"time": "10-31",
						"img": '/static/分组 9.png'
					},
				],
			}
		},
		onLoad() {

		},
		methods: {
			setting(){
				uni.navigateTo({
					url:'',//跳转到设置
				})
			},
			search(){
				uni.navigateTo({
					url:'',//实现直接启动搜索
				})
			}
		}
	}
</script>

<style>
	/* @import '../../css/style.css'; */
	.aui-news-box {
	    margin-top: 10px;
	    background: #fff;
	}
	.aui-news-item {
	    padding: 15px;
	    position: relative;
	    display: -webkit-box;
	    display: -webkit-flex;
	    display: flex;
	    -webkit-box-align: center;
	    -webkit-align-items: center;
	    align-items: center;
	}
	
	.aui-news-item-hd {
	    margin-right: .8em;
	    line-height: 55px;
	    text-align: center;
	    background: #fff;
	    border-radius: 15px;
	}
	
	.aui-news-item-hd img {
	    width: 100%;
	    max-height: 100%;
	    vertical-align: top
	}
	
	.aui-news-item-bd {
	    -webkit-box-flex: 1;
	    -webkit-flex: 1;
	    flex: 1;
	    min-width: 0;
		margin-top: -40rpx;
	}
	
	.aui-news-item-bd h4 {
	    font-weight: 400;
	    font-size: 20px;
	    width: auto;
	    overflow: hidden;
	    text-overflow: ellipsis;
	    white-space: nowrap;
	    word-wrap: normal;
	    word-wrap: break-word;
	    word-break: break-all;
	    padding-bottom: 10px;
	    color: #000000;
	}
	
	.aui-news-item-bd p {
	    color: #848689;
	    font-size: 13px;
	    line-height: 1.2;
	    overflow: hidden;
	    text-overflow: ellipsis;
	    display: -webkit-box;
	    -webkit-box-orient: vertical;
	    -webkit-line-clamp: 1;
	}
	
	.aui-news-item:before {
	    content: '';
	    position: absolute;
	    z-index: 2;
	    bottom: 0;
	    left: 0;
	    width: 100%;
	    height: 1px;
	    border-bottom: 1px solid #d8d8d8;
	    -webkit-transform: scaleY(0.5);
	    transform: scaleY(0.5);
	    -webkit-transform-origin: 0 100%;
	    transform-origin: 0 100%;
	}
	
	.top{
		display: flex;
		justify-content: space-between;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		font-size: 50rpx;
	}
	
	
	
	
	.img{
		border-radius: 50px;
		width: 55px;
		height: 55px;
	}
	.infor{
		font-size: 20px;
		margin-top: 40px;
		
	}
	.time{
		margin-top: 10px;
	}
	.icon{
		margin-left:290px;
		/* display: flex; */
		/* flex-direction: row-reverse; */
		font-size: 26rpx;
	}
</style>
