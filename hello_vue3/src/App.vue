<template>
  <div class="app">
    <!-- TODO: Add a router-link to index -->
    <router-link to="/index/12">
      <h2 class="title">照片管理系统</h2>
    </router-link>
    <!-- <h2 class="title">照片管理系统</h2> -->
    
    <div class="main-content">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script lang="ts" setup name="App">
  import {RouterView} from 'vue-router'

</script>

<style>
  .title {
    text-align: center;
    word-spacing: 5px;
    margin: 30px 0;
    height: 70px;
    line-height: 70px;
    background-image: linear-gradient(45deg, gray, white);
    border-radius: 10px;
    box-shadow: 0 0 2px;
    font-size: 30px;
  }
</style>