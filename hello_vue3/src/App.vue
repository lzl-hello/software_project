<template>
  <div class="app">
    <!-- <router-link to="/index/12" class="title-container">
      <h2 class="title">照片管理系统</h2>
    </router-link> -->
    <div class="main-content">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script lang="ts" setup name="App">
  import { RouterView } from 'vue-router'
</script>

<style>
.app {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #f5f5f5;
  min-height: 100vh;
  box-sizing: border-box;
}

.title-container {
  width: 50%;
  text-align: center;
  margin: 0 auto 30px auto;
  box-sizing: border-box;
  text-decoration: none;
}

.title {
  margin: 0;
  height: 70px;
  line-height: 70px;
  background-image: linear-gradient(45deg, #2193b0, #6dd5ed);
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  font-size: 30px;
  color: white;
  transition: transform 0.3s, box-shadow 0.3s;
  padding: 0 20px;
}

.title:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.main-content {
  width: 100%;
  height: 100%;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  /* background-image: url('../../picture/web2.jpg');
  background-size: cover;
  background-position: center; */
}
</style>
