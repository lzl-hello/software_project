<template>
  <div class="category-page">
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
  </div>
</template>

  
<script setup lang="ts">
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const userId = route.params.userId;
</script>

  
<style scoped>
.category-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  background: #f5f5f5; /* 背景颜色 */
}

.header {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.title-container {
  text-align: center;
}

.nav {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); /* 自动填充每行至少两个分类 */
  gap: 20px; /* 分类之间的间距 */
  width: 100%;
}

.nav-block {
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none; /* 去掉链接下划线 */
}

.block-content {
  width: 100%;
  height: 300px;
  padding: 20px;
  color: white; /* 根据背景图片调整文字颜色 */
  background-size: cover;
  background-position: center;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.block-content h2 {
  margin: 0;
  font-size: 1.75rem; /* 标题字体大小 */
  color: white; /* 标题颜色 */
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.6); /* 文字阴影 */
}

.block-content p {
  margin: 10px 0 0;
  font-size: 1.25rem; /* 描述字体大小 */
  color: white; /* 描述颜色 */
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.6); /* 文字阴影 */
}
</style>

  