<template>
      <header class="header">
        <router-link to="/index/12" class="title-container">
          <h2 class="title">照片管理系统</h2>
        </router-link>
      </header>
      <!-- 空一行 -->
      <br>
      <br>
    <nav class="nav">
      <router-link :to="{ name: '/productperson', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>人物照</h2>
          <p>Go to Page 1</p>
        </div>
      </router-link>
  
      <router-link :to="{ name: '/productscenery', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/scenery.jpg');">
          <h2>风景照</h2>
          <p>Go to Page 2</p>
        </div>
      </router-link>
  
      <router-link :to="{ name: '/productanimal', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/animal.jpg');">
          <h2>动物照</h2>
          <p>Go to Page 3</p>
        </div>
      </router-link>
  
      <router-link :to="{ name: '/productevent', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/event.jpg');">
          <h2>事件</h2>
          <p>Go to Page 4</p>
        </div>
      </router-link>

      <router-link :to="{ name: '/productbuilding', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/building.jpg');">
          <h2>建筑</h2>
          <p>Go to Page 5</p>
        </div>
      </router-link>

      <router-link :to="{ name: '/productother', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/other.jpg');">
          <h2>其他</h2>
          <p>Go to Page 6</p>
        </div>
      </router-link>
    </nav>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue';
  import { useRoute } from 'vue-router';
  
  const route = useRoute();
  const userId = route.params.userId;
  </script>
  
  <style scoped>
  .nav {
    display: grid;
    grid-template-columns: repeat(2, 1fr); /* 每行两个分类 */
    gap: 20px; /* 分类之间的间距 */
  }
  
  .nav-block {
    width: 100%;
    height: 100%;
  }
  
  .block-content {
    height: 400px;
    padding: 40px;
    color: white; /* 根据背景图片调整文字颜色 */
    background-size: cover;
    background-position: center;
    border-radius: 10px;
    text-align: center;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    margin: 0;
  }
  
  p {
    margin: 0;
  }
  </style>
  