<template>
    <div id="index">
      <div id="contain">
        <header class="header">
          <router-link to="/index/12" class="title-container">
            <h2 class="title">照片管理系统</h2>
          </router-link>
        </header>
  
        <nav class="nav">
          <router-link :to="{ name: '/product', params: { userId: userId } }" class="nav-block">
            <div class="block-content">
              <h2>管理您的私人相册</h2>
              <p>Go to Page 1</p>
            </div>
          </router-link>
  
          <router-link :to="'/openproduct'" class="nav-block">
            <div class="block-content">
              <h2>欣赏其余用户公开的照片</h2>
              <p>Go to Page 2</p>
            </div>
          </router-link>
  
          <a href="http://localhost:9095/index.html#/aj/mqDNhXss" target="_blank" class="nav-block">
            <div class="block-content">
              <h2>查看您的照片统计</h2>
              <p>Go to Page 3</p>
            </div>
          </a>
  
          <router-link :to="{ name: '/productclassify', params: { userId: userId } }" class="nav-block">
            <div class="block-content">
              <h2>智能相册管理</h2>
              <p>Go to Page 4</p>
            </div>
          </router-link>
          
          <!-- 添加新的按钮 -->
          <router-link :to="{ name: '/editprofile', params: { userId: userId } }" class="nav-block">
            <div class="block-content">
              <h2>修改我的个人信息</h2>
              <p>Edit My Profile</p>
            </div>
          </router-link>
        </nav>

        <div class="content">
            <div class="section">
            <h3>最新动态</h3>
            <p>欢迎查看我们的最新动态：</p>
            <ul>
                <li>新增了智能相册管理功能。</li>
                <li>优化了用户界面和体验。</li>
                <li>修复了若干已知问题。</li>
            </ul>
            </div>

            <div class="section">
            <h3>联系信息</h3>
            <p>如果您有任何问题或建议，请随时联系我们：</p>
            <p>Email: info@example.com</p>
            <p>电话: +1234567890</p>
            </div>

            <div class="section">
            <h3>常见问题解答</h3>
            <p>以下是一些常见问题及其解答：</p>
            <ul>
                <li>问题1：如何上传照片？</li>
                <li>答：在相册页面点击上传按钮，选择您要上传的照片即可。</li>
                <li>问题2：如何修改个人信息？</li>
                <li>答：在个人信息页面修改相应字段后点击保存即可。</li>
                <!-- 添加更多常见问题解答... -->
            </ul>
            </div>
        </div>
  
        <div class="intro">
          <h3>欢迎使用照片管理系统</h3>
          <p>这个系统帮助您管理和欣赏您的私人相册，以及浏览其他用户公开的照片。</p>
        </div>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import { defineComponent, computed } from 'vue';
  import { useRoute } from 'vue-router';
  
  const route = useRoute();
  const userId = route.params.userId;
  console.log(userId)
  </script>
  
  <style lang="less" scoped>
  @keyframes animate {
    0% {
      filter: hue-rotate(0deg);
    }
    100% {
      filter: hue-rotate(360deg);
    }
  }
  
  #index {
    position: relative;
    width: 100vw;
    height: 100vh;
    background-size: 100% 100%;
    background-image: url('../../picture/building.jpg');
    background-size: cover;
    background-position: center;
    #contain {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      border-radius: 25px;
      border: 1px solid black;
      background-color: rgba(255, 255, 255, 0.1) !important;
      backdrop-filter: blur(5px);
      box-shadow: -5px -5px 10px rgb(39, 65, 65), 5px 5px 20px aqua;
      animation: animate 5s linear infinite;
      padding: 20px;
      box-sizing: border-box;
      width: 90%;
      max-width: 1200px;
      height: auto;
    }
  }
  
  .header {
    margin-bottom: 20px;
    margin-top: 40px;
    color: white;
  }
  
  .nav {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 20px;
    margin-bottom: 40px;
  }
  
  .nav-block {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 200px;
    height: 200px;
    background-color: rgba(0, 123, 255, 0.8);
    color: white;
    text-decoration: none;
    border-radius: 10px;
    transition: background-color 0.3s ease, transform 0.3s ease;
  }
  
  .nav-block:hover {
    background-color: rgba(0, 86, 179, 0.8);
    transform: scale(1.05);
  }
  
  .block-content {
    text-align: center;
  }
  
  .intro {
    margin-top: 20px;
    color: white;
    text-align: center;
  }

  .content {
  display: flex;
  justify-content: space-between;
}

.section {
  flex-basis: calc(33.33% - 10px); /* 三等分，考虑到间距 */
  padding: 20px;
  border-radius: 5px;
}

.section h3 {
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.section p, .section ul, .section ol {
  margin-bottom: 10px;
}
  </style>
  