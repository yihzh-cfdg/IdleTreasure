<template>
    <MenuLogo></MenuLogo>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      unique-opened
      router
      @open="handleOpen"
      @close="handleClose"
      background-color="#304156"
    >
      <MenuItem :menuList="menuList"></MenuItem>
    </el-menu>
  </template>
  
  <script setup lang="ts">
  import { ref,reactive } from 'vue'
  import { useRoute } from 'vue-router'
  import MenuItem from './MenuItem.vue';
import { computed } from '@vue/reactivity';
  import MenuLogo from './MenuLogo.vue';
  const route  = useRoute()
  const activeIndex = computed(()=>{
    const {path} = route;  
    return  path
  })
  //菜单数据
  let menuList = reactive([
    {
      path: "/dashboard",
      component: "Layout",
      name: "dashboard",
      meta: {
        title: "首页",
        icon: "HomeFilled",
        roles: ["sys:dashboard"],
      },
    },
    {
      path: "/system",
      component: "Layout",
      name: "system",
      meta: {
        title: "系统管理",
        icon: "Setting",
        roles: ["sys:manage"],
      },
      children: [
        {
          path: "/adminUser",
          component: "/system/AdminUser",
          name: "adminUser",
          meta: {
            title: "管理员管理",
            icon: "UserFilled",
            roles: ["sys:adminUser"],
          },
        },
        {
          path: "/userList",
          component: "/system/UserList",
          name: "userList",
          meta: {
            title: "用户管理",
            icon: "Wallet",
            roles: ["sys:userList"],
          },
        },
        {
          path: "/menuList",
          component: "/system/MenuList",
          name: "menuList",
          meta: {
            title: "菜单管理",
            icon: "Menu",
            roles: ["sys:menu"],
          },
        },
      ],
    },
    {
      path: "/goodsRoot",
      component: "Layout",
      name: "goodsRoot",
      meta: {
        title: "商品管理",
        icon: "Setting",
        roles: ["sys:goodsRoot"],
      },
      children: [
        {
          path: "/goodsType",
          component: "/goods/GoodsType",
          name: "goodsType",
          meta: {
            title: "商品分类",
            icon: "View",
            roles: ["sys:goodsType"],
          },
        },
        {
          path: "/unusedList",
          component: "/goods/UnusedList",
          name: "unusedList",
          meta: {
            title: "闲置商品",
            icon: "TakeawayBox",
            roles: ["sys:unusedList"],
          },
        },
      ],
    },
    {
      path: "/order",
      component: "Layout",
      name: "order",
      meta: {
        title: "订单管理",
        icon: "Setting",
        roles: ["sys:order"],
      },
      children: [
        {
          path: "/unusedOrder",
          component: "/order/UnusedOrder",
          name: "unusedOrder",
          meta: {
            title: "闲置订单",
            icon: "UserFilled",
            roles: ["sys:unusedOrder"],
          },
        },
        {
          path: "/buyOrder",
          component: "/order/BuyOrder",
          name: "buyOrder",
          meta: {
            title: "求购订单",
            icon: "Wallet",
            roles: ["sys:buyOrder"],
          },
        },
      ],
    },
    {
      path: "/comment",
      component: "Layout",
      name: "comment",
      meta: {
        title: "评论管理",
        icon: "Setting",
        roles: ["sys:comment"],
      },
      children: [
        {
          path: "/commentList",
          component: "/comment/CommentList",
          name: "commentList",
          meta: {
            title: "评论列表",
            icon: "UserFilled",
            roles: ["sys:commentList"],
          },
        },
      ],
    },
  ]);
  const isCollapse = ref(false)
  const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
  }
  const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
  }
  </script>
  
  <style scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 230px;
    min-height: 400px;
  }
  .el-menu {
    border-right: none;
  }
  
  :deep(.el-sub-menu .el-sub-menu__title) {
    color: #f4f4f5 !important;
  }
  
  :deep(.el-menu .el-menu-item) {
    color: #bfcbd9;
  }
  /* 菜单点中文字的颜色 */
  
  :deep(.el-menu-item.is-active) {
    color: #409eff !important;
  }
  /* 当前打开菜单的所有子菜单颜色 */
  
  :deep(.is-opened .el-menu-item) {
    background-color: #1f2d3d !important;
  }
  /* 鼠标移动菜单的颜色 */
  
  :deep(.el-menu-item:hover) {
    background-color: #001528 !important;
  }
  </style>
  