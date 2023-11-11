<template>
    <el-icon :size="30" style="cursor: pointer;color: #FFF;" @click="iconClick">
      <component :is="status ? Expand : Fold"></component>
    </el-icon>
  </template>
  
  <script setup lang="ts">
  import { Expand,Fold } from "@element-plus/icons-vue";
  import { computed } from 'vue';
  import { collapseStore } from '@/store/collapse/index';
  const store = collapseStore()
  const status = computed(()=>{
      return store.getCollapse
  })
  const iconClick = ()=>{
      store.setCollapse(!store.collapse)
  }
  </script>
  
  <style scoped></style>
  