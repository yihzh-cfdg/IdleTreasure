<template>
	<view class="container">
		
		<!-- 导航栏 -->
		<view class="navigation-bar">
		
			<uni-nav-bar dark background-color="#f5f5f5" status-bar left-width="250rpx">
				<block slot="left">
					<uni-icons  @click="goBack()" class="back-icons" size="20" type="back"></uni-icons>
					<text class="navi-header-text" style="font-size:1.15em" >关于闲置宝</text>
				</block>
			</uni-nav-bar>		
		</view>
		
		<view class="team-info">
			<uni-card title="团队信息">
				<image :src=photo mode="widthFix"/>
				<text class="uni-body">&nbsp;&nbsp;我们是“代码写得都队”，
				一个由十位热情满满的同学组成的团队。怀着开发高效、实用、创新的软件产品的目标，
				我们相信，通过团结协作、不断学习、勇于创新，我们能够完成这个挑战。
				正如我们的队名“代码写得都队”，希望我们的代码写的都对！</text>
			</uni-card>			
			
		</view>

	，
		<uni-card title="产品信息" >
			<text class="uni-body">闲置宝，更适合FZU宝宝体质的二手交易平台！</text>
		</uni-card>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				photo: "https://yihzh-imgbed.oss-cn-fuzhou.aliyuncs.com/image/all.jpg"
			};
		},
		methods: {
			goBack(){
				uni.navigateBack();
			}
		}
	}
</script>

<style lang="scss">
	.navi-header-text{
			color: #000;
			/* 设置文字颜色 */
		}
	.team-info{
		margin-bottom: 1px;
	}
</style>
