<template>
  <div class="user-register">
    <!-- <el-form ref="registerForm" :model="registerForm" label-width="80px" class="register-form"> -->
      <el-form label-width="80px" class="register-form"> 
    <!-- 用户名输入字段 -->
      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>

      <!-- 密码输入字段 -->
      <el-form-item label="密码" prop="password">
        <el-input v-model="registerForm.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>

      <!-- 确认密码输入字段 -->
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="registerForm.confirmPassword" type="password" placeholder="请确认密码"></el-input>
      </el-form-item>

      <!-- 手机号输入字段 -->
      <el-form-item label="手机号" prop="phonenumber">
        <el-input v-model="registerForm.phonenumber" placeholder="请输入手机号"></el-input>
      </el-form-item>

      <el-form class="button-row"> 
        <!-- 注册按钮 -->
          <el-form-item>
            <el-button type="primary" @click="handleSubmit">注册</el-button>
          </el-form-item>

          <!-- 跳转到登录链接 -->
          <el-form-item>
            <div class="button-container">
              <router-link to="/login">已有账号？去登录</router-link>
            </div>
          </el-form-item>
      </el-form>

    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '@/router';

// 注册表单数据
const registerForm = ref({
  username: '',
  password: '',
  confirmPassword: '',
  phonenumber: ''
});

// 处理注册表单提交
const handleSubmit = () => {
  // 校验密码是否一致
  if (registerForm.value.password !== registerForm.value.confirmPassword) {
    ElMessage.error('密码不一致，请重新输入');
    return;
  }
  console.log("现在去后端注册")

  // 发起注册请求
  axios.post('/api/register', registerForm.value)
    .then(response => {
      ElMessage.success('注册成功');
      // 注册成功后跳转到登录页面
      router.push('/login');
    })
    .catch(error => {
      ElMessage.error(`注册失败: ${error.response.data.msg}`);
    });
};
</script>

<style scoped>
.register-form {
  max-width: 400px;
  margin: 0 auto;
}
.button-container {
  text-align: center;
  margin-left: 220px;
}
.button-row {
  display: flex; /* 使用 Flexbox 布局 */
  align-items: center; /* 垂直居中 */
}
</style>
