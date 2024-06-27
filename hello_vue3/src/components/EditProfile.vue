<template>
    <div id="edit-profile">
      <div id="contain">
        <header class="header">
          <router-link to="/index/12" class="title-container">
            <h2 class="title">照片管理系统</h2>
          </router-link>
        </header>
        <div id="content">
          <div id="left_card">
            <h1>修改个人信息</h1>
          </div>
          <div id="right_card">
            <el-card class="el-card">
              <el-form @submit.prevent="submitForm" class="form">
                <el-form-item class="form-item" label="用户名" prop="username" label-width="80px">
                  <el-input type="text" id="username" v-model="user.username" required placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item class="form-item" label="电话号码" prop="phonenumber" label-width="80px">
                  <el-input type="tel" id="phonenumber" v-model="user.phonenumber" required placeholder="请输入电话号码"></el-input>
                </el-form-item>
                <el-form-item class="form-item" label="旧密码" prop="oldPassword" label-width="80px">
                  <el-input type="password" id="oldPassword" v-model="oldPassword" required placeholder="请输入旧密码"></el-input>
                </el-form-item>
                <el-form-item class="form-item" label="新密码" prop="newPassword" label-width="80px">
                  <el-input type="password" id="newPassword" v-model="newPassword" required placeholder="请输入新密码"></el-input>
                </el-form-item>
                <el-form class="button-row">
                  <el-form-item>
                    <el-button type="primary" class="submit-button" @click="submitForm">保存更改</el-button>
                  </el-form-item>
                </el-form>
              </el-form>
            </el-card>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import { useRoute, useRouter } from 'vue-router';
  import axios from 'axios';
  import { ElMessage } from 'element-plus';
  
  const route = useRoute();
  const router = useRouter();
  const userId = route.params.userId;
  
  const user = ref({
    username: '',
    phonenumber: '',
    password: ''
  });
  
  const oldPassword = ref('');
  const newPassword = ref('');
  
  onMounted(async () => {
      try {
          axios.get('/api/getUserById', { params: { userId: userId } })
          .then((result) => {
              user.value = result.data.data;
      });
      } catch (error) {
          console.error('Error fetching user data:', error);
      }
  });
  
  const submitForm = async () => {
      try {

          await axios.put(`/api/updateUser`, null, {
              params: {
                  userId: userId,
                  username: user.value.username,
                  phonenumber: user.value.phonenumber,
                  oldPassword: oldPassword.value,
                  newPassword: newPassword.value
              }
          }).then((result) => {
              if (result.data.msg === "原密码错误"){
                  ElMessage.error('原密码错误，请重试');
              } else {
                  ElMessage.success('更新成功');
                  router.push('/')
              }
              // console.log(result.data.msg);

          })
          // router.push('/');
      } catch (error) {
          console.error('Error updating user data:', error);
          ElMessage.error('更新失败，请重试');
      }
  };
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
  
  #edit-profile {
    position: relative;
    width: 100vw;
    height: 100vh;
    background-size: 100% 100%;
    background-image: url('../../picture/building.jpg');
    background-size: cover;
    background-position: center;
    #contain {
      height: auto;
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
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 20px;
      box-sizing: border-box;
    }
  }
  
  .header {
    margin-bottom: 20px;
    .title-container {
        width: 100%;
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
      h2 {
        color: white;
        margin: 0;
        text-decoration: underline;
        cursor: pointer;
        &:hover {
          color: aqua;
        }
      }
    }
  }
  
  #content {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    text-align: center;
  }
  
  #left_card {
    width: 500px;
    h1 {
      color: white;
      white-space: nowrap;
    }
  }
  
  #right_card {
    width: 400px;
    .el-card {
      margin: 0 45px;
      border-radius: 25px;
      background-color: rgba(255, 255, 255, 0.1);
    }
  }
  
  #right_card {
    display: flex;
    justify-content: center;
    align-items: center;
    .el-form {
      width: 100%;
      .form-item {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        label {
          text-align: right;
          padding-right: 10px;
        }
        input {
          width: calc(100% - 90px);
        }
      }
      input {
        width: 80%;
        height: 45px;
        margin-top: 10px;
        border: 1px solid white;
        background-color: rgba(255, 255, 255, 0.5);
        border-radius: 10px;
        font-size: inherit;
        padding-left: 20px;
        outline: none;
      }
      .button-row {
        display: flex;
        align-items: center;
        justify-content: space-between;
        flex-direction: column;
      }
    }
  }
  
  .submit-button {
    width: 100%;
    height: 35px;
    margin-top: 10px;
    border-radius: 10px;
    background-color: rgba(207, 38, 38, 0.8);
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .submit-button:hover {
    background-color: rgba(207, 38, 38, 1);
  }
  </style>
  