<template>
  <div id="login">
    <div id="contain">
      <div id="left_card">
        <h1>欢迎来到照片管理系统</h1>
        <span>Welcome to my Photo world</span>
      </div>
      <div id="right_card">
        <el-card class="el-card">
          <h2>欢迎登录</h2>
          <el-form>
            <el-form-item class="form-item" label="用户名" prop="username" label-width="80px">
              <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item class="form-item" label="密码" prop="password" label-width="80px">
              <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form class="button-row">
              <el-form-item>
                <el-button type="primary" @click="handleSubmit">登录</el-button>
              </el-form-item>
              <el-form-item class="register-link">
                <router-link to="/register">没有账号？去注册</router-link>
              </el-form-item>
            </el-form>
            <div class="message">
              <span v-html="error"></span>
            </div>
          </el-form>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '@/router';

const loginForm = ref({
  username: '',
  password: ''
});

const error = ref('');

const handleSubmit = () => {
  console.log("现在去后端登录");
  axios.post('/api/login', loginForm.value)
    .then(response => {
      // 处理登录成功逻辑
      console.log(response.data.data);
      const user = response.data.data; // 获取返回的用户信息
      const userId = user.userId; // 获取用户的 id
      console.log(userId); // 打印用户的 id
      ElMessage.success('登录成功');
      router.push({ name: '/index', params: { userId: userId } });
    })
    .catch(error => {
      console.log("执行error逻辑");
      // 处理登录失败逻辑
      const errorMsg = error.response?.data?.msg || '登录失败'; // 使用可选链操作符处理错误消息
      ElMessage.error(`登录失败: ${errorMsg}`);
    });
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

#login {
  position: relative;
  width: 100vw;
  height: 100vh;
  background-size: 100% 100%;
  background-image: url('../../picture/building.jpg');
  background-size: cover;
  background-position: center;
  #contain {
    height: 400px;
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
  }
}

#contain {
  display: flex;
  flex-direction: row;
  text-align: center;
  align-items: center;
  #left_card {
    width: 500px;
    h1 {
      color: white;
      white-space: nowrap;
    }
    span {
      font-size: 1.2rem;
      text-align: center;
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
}

#right_card {
  display: flex;
  justify-content: center;
  align-items: center;
  h2 {
    margin-bottom: 5px;
  }
  .el-form {
    width: 100%;
    .form-item {
      display: flex;
      align-items: center;
      justify-content: flex-start;
      label {
        text-align: right; // 标签右对齐
        padding-right: 10px; // 标签和输入框之间的间距
      }
      input {
        width: calc(100% - 90px); // 调整输入框的宽度，考虑到label-width和padding-right
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
    .button-container {
      text-align: center;
      margin: 10px 0;
    }
    .button-row {
      display: flex;
      align-items: center;
      justify-content: space-between;
      flex-direction: column;
    }
    .register-link {
      text-align: center;
      margin-top: 10px;
    }
    .message {
      margin-top: 26px;
      font-size: 0.9rem;
      color: red;
    }
    .loginbtn {
      width: 100%;
      height: 35px;
      margin-top: 10px;
      border-radius: 10px;
      background-color: rgba(207, 38, 38, 0.8);
    }
  }
}
</style>
