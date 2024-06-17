<template>
    <nav class="nav">
      <router-link :to="{ name: '/productperson', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>分类一</h2>
          <p>Go to Page 1</p>
        </div>
      </router-link>
  
      <router-link :to="{ name: '/product', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>分类二</h2>
          <p>Go to Page 2</p>
        </div>
      </router-link>
  
      <router-link :to="{ name: '/product', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>分类三</h2>
          <p>Go to Page 3</p>
        </div>
      </router-link>
  
      <router-link :to="{ name: '/product', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>分类四</h2>
          <p>Go to Page 4</p>
        </div>
      </router-link>

      <router-link :to="{ name: '/product', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>分类五</h2>
          <p>Go to Page 5</p>
        </div>
      </router-link>

      <router-link :to="{ name: '/product', params: { userId: userId } }" class="nav-block">
        <div class="block-content" style="background-image: url('../../picture/person.jpg');">
          <h2>分类六</h2>
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
    height: 300px;
    padding: 20px;
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
  