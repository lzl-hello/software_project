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


<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: #f5f5f5; /* 背景颜色 */
}

.hero-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 60px 20px;
  text-align: center;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  background: #ffffff; /* 背景颜色 */
  max-width: 800px;
  margin: 20px 0;
}

.hero-content {
  background: #ffffff;
  padding: 40px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.hero-content h1 {
  margin-bottom: 20px;
  font-size: 2.5rem; /* 标题字体大小 */
  color: #333; /* 标题颜色 */
}

.hero-content p {
  margin-bottom: 20px;
  font-size: 1.25rem; /* 描述字体大小 */
  color: #666; /* 描述颜色 */
}

.button-group {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>

