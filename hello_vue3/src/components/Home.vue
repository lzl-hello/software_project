<template>
  <div class="home">
    <div class="hero-section">
      <div class="hero-content">
        <h1>照片管理系统</h1>
        <p>管理和分享您的照片从未如此简单。我们的系统提供了强大的功能，帮助您轻松组织和分享您的照片。</p>
        <div class="button-group">
          <el-button type="primary" size="large" @click="navigateTo('/login')">登录</el-button>
          <el-button type="success" size="large" @click="navigateTo('/register')">注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter();

const navigateTo = (path: string) => {
  router.push(path).catch(err => {
    ElMessage.error(err.message);
  });
};
</script>

<style>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;

}

.hero-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 200px 200px;
  text-align: center;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);

  /* 设置透明度 */
  opacity: 0.9;
  max-width: 800px;
  margin: 20px 0;
}

.hero-content {
  background: white;
  padding: 40px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.button-group {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>
