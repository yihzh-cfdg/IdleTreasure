<template>
    <div class="logo">
        <img :src="MenuLogo"/>
        <span v-if="show" class="logo-title">{{ title }}</span>
    </div>
</template>

<script setup lang="ts"> 
import MenuLogo from '@/assets/cat.png'
import { ref,watch } from 'vue';
import { collapseStore } from '@/store/collapse/index';
const store = collapseStore()
const title = ref('闲置宝')
const show = ref(true)
watch(
    ()=>store.getCollapse,
    (collapsed:boolean)=>{
        if(!collapsed){
            setTimeout(() => {
                show.value = !collapsed;
            }, 300);
        }else{
            show.value = !collapsed;
        }
    }
)
</script>

<style scoped lang="scss">
.logo{
    display: flex;
    width: 100%;
    height: 60px;
    line-height: 60px;
    background-color: #2b2f3a;
    cursor: pointer;
    align-items: center;
    img{
        width: 46px;
        height: 46px;
        margin-left: 5px;
    }
    .logo-title{
        color: #FFF;
        font-weight: 600;
        font-size: 22px;
        font-family: FangSong;
    }
}
</style>