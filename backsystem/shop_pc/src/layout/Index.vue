<template>
    <el-container class="layout">
      <el-aside width="230px" class="asside">
        <Menu></Menu>
      </el-aside>
      <el-container>
        <el-header class="header">Header</el-header>
        <el-main class="main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </template>
  <script setup lang="ts">
  import Menu from './Menu.vue';
  </script>
  <style lang="scss">
  .layout {
    height: 100%;
    .asside {
      background-color: #304156;
    }
    .header {
      background-color: rgb(134, 209, 237);
    }
    .main {
      background-color: #fff;
    }
  }
  </style>
   