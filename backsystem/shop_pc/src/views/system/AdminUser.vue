<template>
  <el-main>
    <!-- 搜索栏 -->
    <el-form :model="searchParm" :inline="true" size="default">
      <el-form-item>
        <el-input
          v-model="searchParm.nickName"
          placeholder="请输入姓名"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="Search">搜索</el-button>
        <el-button icon="Close" type="danger" plain>重置</el-button>
        <el-button type="primary" icon="Plus" @click="addBtn">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增、编辑弹框 -->
    <SysDialog
      :title="dialog.title"
      :width="dialog.width"
      :height="dialog.height"
      :visible="dialog.visible"
      @on-close="onClose"
      @on-confirm="commit"
    >
      <template v-slot:content>
        <el-form
          :model="addModel"
          ref="addRef"
          :rules="rules"
          label-width="80px"
          :inline="false"
          size="default"
        >
          <el-form-item prop="nickName" label="姓名:">
            <el-input v-model="addModel.nickName"></el-input>
          </el-form-item>
          <el-form-item prop="sex" label="性别:">
            <el-radio-group v-model="addModel.sex">
              <el-radio :label="'0'">男</el-radio>
              <el-radio :label="'1'">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="phone" label="电话:">
            <el-input v-model="addModel.phone"></el-input>
          </el-form-item>
          <el-form-item prop="username" label="账号:">
            <el-input v-model="addModel.username"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码:">
            <el-input v-model="addModel.password"></el-input>
          </el-form-item>
          <el-form-item prop="status" label="状态:">
            <el-radio-group v-model="addModel.status">
              <el-radio :label="'0'">启用</el-radio>
              <el-radio :label="'1'">停用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </template>
    </SysDialog>
  </el-main>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { ElMessage, type FormInstance } from "element-plus";
import { addAdminUserApi } from "@/api/user/index";
//表单ref属性
const addRef = ref<FormInstance>();
//获取弹框属性
const { dialog, onClose } = useDialog();
//搜索绑定的对象
const searchParm = reactive({
  nickName: "",
});
//新增按钮
const addBtn = () => {
  dialog.height = 300;
  dialog.visible = true;
};
//新增表单绑定的对象
const addModel = reactive({
  userId: "",
  username: "",
  password: "",
  nickName: "",
  phone: "",
  sex: "",
  status: "",
});
//表单验证规则
const rules = {
  nickName: [{ required: true, message: "请输入姓名", trigger: "blur" }],
  sex: [{ required: true, message: "请选择性别", trigger: "blur" }],
  phone: [{ required: true, message: "请输入电话", trigger: "blur" }],
  username: [{ required: true, message: "请输入账户", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
  status: [{ required: true, message: "请选择状态", trigger: "blur" }],
};
//新增、编辑提交
const commit = () => {
  //表单验证
  addRef.value?.validate(async (valid) => {
    if (valid) {
      console.log(addModel);
      //提交数据
      const res = await addAdminUserApi(addModel);
      if (res && res.code == 200) {
        //信息提示
        ElMessage.success(res.msg)
        //关闭弹框
        dialog.visible = false;
      }
    }
  });
};
</script>

<style scoped></style>
