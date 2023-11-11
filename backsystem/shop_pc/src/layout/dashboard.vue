<template>
  <div>
    <el-button type="primary" size="default" @click="onShow">新增</el-button>
    <!-- 弹框组件 -->
    <SysDialog
      :title="dialog.title"
      :visible="dialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <template v-slot:content> 弹框测试 </template>
    </SysDialog>
  </div>
</template>

<script setup lang="ts">
import SysDialog from "@/components/SysDialog.vue";
import { reactive } from "vue";
import  useDialog  from "@/hooks/useDialog";
//获取弹框属性
const {dialog,onClose,onConfirm,onShow} = useDialog()
</script>

<style scoped></style>
