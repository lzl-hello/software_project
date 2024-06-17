<template>
  <div class="user-login">
    <el-form>
      <!-- 用户名和密码输入字段 -->
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>

      <el-form class="button-row"> 
        <!-- 注册按钮 -->
          <el-form-item>
            <el-button type="primary" @click="handleSubmit">登录</el-button>
          </el-form-item>

          <!-- 跳转到登录链接 -->
          <el-form-item>
            <div class="button-container">
              <router-link to="/register">没有账号？去注册</router-link>
            </div>
          </el-form-item>
      </el-form>

    </el-form>
  </div>
</template>

<script setup>

import { ref } from 'vue';
import { RouterLink } from 'vue-router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '@/router';

const loginForm = ref({
  username: '',
  password: ''
});

const handleSubmit = () => {
  console.log("现在去后端登录")
  axios.post('/api/login', loginForm.value)
    .then(response => {
      // 处理登录成功逻辑
      console.log(response.data.data)
      // ElMessage.success('登录成功');

      const user = response.data.data; // 获取返回的用户信息
      const userId = user.userId; // 获取用户的 id
      console.log(userId); // 打印用户的 id
      ElMessage.success('登录成功');
      // router.push('/product');
      // router.push({ name: '/product', params: { userId: userId } })
      router.push({ name: '/index', params: { userId: userId } })

    })
    .catch(error => {
      console.log("执行error逻辑")
      // 处理登录失败逻辑
      const errorMsg = error.response?.data?.msg || '登录失败'; // 使用可选链操作符处理错误消息
      ElMessage.error(`登录失败: ${errorMsg}`);
    });
};

</script>

<style scoped>
.user-login {
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
