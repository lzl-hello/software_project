<template>
    <div class="container">
        <header class="header">
            <h1>Welcome to My Page</h1>
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

        </nav>

        <main class="main-content">
            这个是index页面
        </main>
    </div>
</template>

<script setup lang="ts">

import { defineComponent, computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const userId = route.params.userId;
console.log(userId)


</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start; /* 改为 flex-start */
    height: 100vh;
    background-color: #f5f5f5;
    font-family: Arial, sans-serif;
    text-align: center;
    padding: 20px;
    box-sizing: border-box;
}

.header {
    margin-bottom: 20px;
    margin-top: 40px; /* 增加顶部外边距 */
}

.nav {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-bottom: 40px;
}

.nav-block {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 200px;
    height: 200px;
    background-color: #007BFF;
    color: white;
    text-decoration: none;
    border-radius: 10px;
    transition: background-color 0.3s ease, transform 0.3s ease;
}

.nav-block:hover {
    background-color: #0056b3;
    transform: scale(1.05);
}

.block-content {
    text-align: center;
}

.main-content {
    font-size: 18px;
}
</style>
