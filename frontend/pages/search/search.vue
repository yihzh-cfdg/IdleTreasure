<template>
	<view class="container">
		<view class="search-container">
			<!-- 搜索框 -->
			<view class="search-container-bar">
				<uni-search-bar ref="searchBar" style="flex:1;" radius="100" v-model="searchText" :focus="focus"
					clearButton="auto" cancelButton="always" @clear="clear" @confirm="confirm" @cancel="cancel" />
			</view>
		</view>
		<view class="search-body">
			<!-- 搜索历史 -->
			<view class="word-container" v-if="localSearchList.length">
				<view class="word-container_header">
					<text class="word-container_header-text">搜索历史</text>
					<uni-icons v-if="!localSearchListDel" @click="localSearchListDel = true" class="search-icons"
						style="padding-right: 0;" :color="iconColor" size="18" type="trash"></uni-icons>
					<view v-else class="flex-center flex-row" style="font-weight: 500;justify-content: space-between;">
						<text
							style="font-size: 22rpx;color: #666;padding-top:4rpx;padding-bottom:4rpx;padding-right:20rpx;"
							@click="LocalSearchListClear">全部删除</text>
						<text
							style="font-size: 22rpx;color: #c0402b;padding-top:4rpx;padding-bottom:4rpx;padding-left:20rpx;"
							@click="localSearchListDel = false">完成</text>
					</view>
				</view>

				<view class="word-container_body">
					<view class="flex-center flex-row word-container_body-text" v-for="(word,index) in localSearchList"
						:key="index" @click="LocalSearchlistItemClick(word,index)">
						<text class="word-display" :key="word">{{word}}</text>
						<uni-icons v-if="localSearchListDel" size="12" type="closeempty" />
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	const localSearchListKey = '__local_search_history'; //	本地历史存储字段名
	// 搜索记录去重
	const arrUnique = arr => {
		for (let i = arr.length - 1; i >= 0; i--) {
			const curIndex = arr.indexOf(arr[i]);
			const lastIndex = arr.lastIndexOf(arr[i]);
			curIndex != lastIndex && arr.splice(lastIndex, 1);
		}
		return arr;
	}
	export default {
		data() {
			return {
				localSearchList: [],
				localSearchListDel: false,
				searchText: '',
				iconColor: '#999999',
				keyBoardPopup: false,
				focus: true, //	是否自动聚焦
			}
		},
		onLoad() {
			this.getHistory()
		},
		methods: {
			getHistory() {
				uni.request({
					url: this.$store.state.baseUrl + "/api/history/user/" + this.$store.state.token,
					success: (res) => {
						console.log(res.data);
						for (let item of res.data.data)
							this.localSearchList.push(item.keyword);
						console.log(this.localSearchList);
						uni.setStorageSync(localSearchListKey, this.localSearchList);
					},
				})
			},
			clear(res) {
				console.log("res: ", res);
			},
			confirm(res) {
				// 键盘确认
				this.search(res.value);
			},
			cancel(res) {
				uni.hideKeyboard();
				this.searchText = '';
				this.loadList();
			},
			search(value) {
				if (!value) {
					return;
				}
				if (value) {
					if (this.searchText !== value)
						this.searchText = value;
					this.localSearchListManage(value);
					uni.navigateTo({
						url:"/pages/result/result?value="+ value
					})
				}
				uni.hideKeyboard();
				this.loadList(this.searchText);
			},
			localSearchListManage(word) {
				let list = uni.getStorageSync(localSearchListKey);
				if (list.length) {
					this.localSearchList.unshift(word);
					arrUnique(this.localSearchList);
					if (this.localSearchList.length > 10)
						this.localSearchList.pop();
				} else
					this.localSearchList = [word];
				uni.setStorageSync(localSearchListKey, this.localSearchList);
				uni.request({
					url: this.$store.state.baseUrl + "/api/history/insert",
					method: "POST",
					data: {
						user_ID: this.$store.state.token,
						search_ID: 0,
						keyword: word
					},
					success: (res) => {
						console.log("成功插入历史记录!");
					}
				})
			},
			LocalSearchListClear() {
				uni.showModal({
					content: '确认清空搜索历史吗？',
					confirmText: '删除',
					confirmColor: 'red',
					cancelColor: '#808080',
					success: res => {
						if (res.confirm) {
							this.localSearchListDel = false;
							this.localSearchList = [];
							uni.removeStorageSync(localSearchListKey)
						}
					}
				});
			},
			LocalSearchlistItemClick(word, index) {
				if (this.localSearchListDel) {
					this.localSearchList.splice(index, 1);
					uni.setStorageSync(localSearchListKey, this.localSearchList);
					if (!this.localSearchList.length) {
						this.localSearchListDel = false;
					}
					return;
				}
				this.search(word);
			},
			loadList(text = '') {
				getApp().globalData.searchText = text;
				uni.navigateBack()
			}
		},
	}
</script>

<style lang="scss">
	$search-bar-height: 52px;
	$word-container_header-height: 72rpx;

	.container {
		flex: 1;
		background-color: #f7f7f7;
	}

	.search-body {
		background-color: #fff;
		border-bottom-right-radius: 10px;
		border-bottom-left-radius: 10px;
	}

	@mixin uni-flex {
		display: flex;
	}

	@mixin words-display {
		font-size: 26rpx;
		color: #666;
	}

	.uni-searchbar__box {
		border-width: 0;
	}


	.uni-input-placeholder {
		font-size: 28rpx;
	}

	.search-container {
		height: $search-bar-height;
		@include uni-flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		background-color: #fff;

		@at-root {
			#{&}-bar {
				@include uni-flex;
				flex-direction: row;
				justify-content: center;
				align-items: center;
				position: absolute;
				top: 0;
				left: 0;
				right: 0;
			}
		}
	}

	.search-icons {
		padding: 16rpx;
	}

	.word-display {
		@include words-display;
	}

	.word-container {
		padding: 20rpx;

		@at-root {
			#{&}_header {
				@include uni-flex;
				height: $word-container_header-height;
				line-height: $word-container_header-height;
				flex-direction: row;
				justify-content: space-between;
				align-items: center;

				@at-root {
					#{&}-text {
						color: #3e3e3e;
						font-size: 30rpx;
						font-weight: bold;
					}
				}
			}

			#{&}_body {
				@include uni-flex;
				flex-wrap: wrap;
				flex-direction: row;

				@at-root {
					#{&}-text {
						@include uni-flex;
						@include words-display;
						justify-content: center;
						align-items: center;
						background-color: #f6f6f6;
						padding: 10rpx 20rpx;
						margin: 20rpx 30rpx 0 0;
						border-radius: 30rpx;
						text-align: center;
					}

					#{&}-info {
						flex: 1;
						text-align: center;
						font-size: 26rpx;
						color: #808080;
						margin-top: 20rpx;
					}
				}
			}
		}
	}
</style>