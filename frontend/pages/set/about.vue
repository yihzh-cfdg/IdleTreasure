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
				<text class="uni-body">这是一个示例，这是一个示例，这是一个示例，这是一个示例，
				这是一个示例，这是一个示例，这是一个示例，这是一个示例，这是一个示例，
				这是一个示例，这是一个示例，这是一个示例，这是一个示例，这是一个示例，这是一个示例，
				这是一个示例，这是一个示例，这是一个示例。</text>
			</uni-card>			
			
		</view>

	，
		<uni-card title="产品信息" >
			<text class="uni-body">这是一个示例，这是一个示例，这是一个示例，这是一个示例，
			这是一个示例，这是一个示例，这是一个示例，这是一个示例，这是一个示例，
			这是一个示例，这是一个示例，这是一个示例，这是一个示例，这是一个示例，这是一个示例，
			这是一个示例，这是一个示例，这是一个示例。</text>
		</uni-card>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
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
