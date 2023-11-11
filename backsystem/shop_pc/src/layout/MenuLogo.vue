<template>
    <div class="logo">
      <img :src="MenuLogo" alt="logo" />
      <span  class="logo-title">{{ title }}</span>
    </div>
  </template>
  <script setup lang="ts">
  import MenuLogo from '@/assets/cat.png'
  import { ref } from "vue";
  
  const title = ref("闲置宝");
  
  </script>
  <style lang="scss" scoped>
  .logo {
      display: flex;
      width: 100%;
      height: 80px;
      line-height: 60px;
      background: #2b2f3a;
      text-align: center;
      cursor: pointer;
      align-items: center;
      img {
          width: 60px;
          height: 60px;
          margin-right: 12px;
          margin-left: 20px;
      }
      .logo-title {
          color: #fff;
          font-weight: 800;
          line-height: 60px;
          font-size: 22px;
          font-family: FangSong;
      }
  }
  </style>
  