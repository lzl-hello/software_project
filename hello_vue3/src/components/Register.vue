<template>
  <div id="register">
    <div id="contain">
      <div id="left_card">
        <h1>欢迎注册</h1>
        <span>Welcome to register</span>
      </div>
      <div id="right_card">
        <el-card class="el-card">
          <h2>欢迎注册</h2>
          <el-form label-width="80px" class="register-form">
            <!-- 用户名输入字段 -->
            <el-form-item class="form-item" label="用户名" prop="username" label-width="80px">
              <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>

            <!-- 密码输入字段 -->
            <el-form-item class="form-item" label="密码" prop="password" label-width="80px">
              <el-input v-model="registerForm.password" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>

            <!-- 确认密码输入字段 -->
            <el-form-item class="form-item" label="确认密码" prop="confirmPassword" label-width="80px">
              <el-input v-model="registerForm.confirmPassword" type="password" placeholder="请确认密码"></el-input>
            </el-form-item>

            <!-- 手机号输入字段 -->
            <el-form-item class="form-item" label="手机号" prop="phonenumber" label-width="80px">
              <el-input v-model="registerForm.phonenumber" placeholder="请输入手机号"></el-input>
            </el-form-item>

            <el-form class="button-row">
              <!-- 注册按钮 -->
              <el-form-item>
                <el-button type="primary" @click="handleSubmit">注册</el-button>
              </el-form-item>

              <!-- 跳转到登录链接 -->
              <el-form-item class="register-link">
                <router-link to="/login">已有账号？去登录</router-link>
              </el-form-item>
            </el-form>
          </el-form>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from '@/router';

// 注册表单数据
const registerForm = ref({
  username: '',
  password: '',
  confirmPassword: '',
  phonenumber: ''
});

// 处理注册表单提交
const handleSubmit = () => {
  // 检查所有表单字段是否已填写
  const { username, password, confirmPassword, phonenumber } = registerForm.value;
  if (!username || !password || !confirmPassword || !phonenumber) {
    ElMessage.error('请填写完整的注册信息');
    return;
  }

  // 校验密码是否一致
  if (password !== confirmPassword) {
    ElMessage.error('密码不一致，请重新输入');
    return;
  }

  console.log("现在去后端注册");

  // 发起注册请求
  axios.post('/api/register', registerForm.value)
    .then(response => {
      ElMessage.success('注册成功');
      // 注册成功后跳转到登录页面
      router.push('/login');
    })
    .catch(error => {
      ElMessage.error(`注册失败: ${error.response.data.msg}`);
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

#register {
  position: relative;
  width: 100vw;
  height: 100vh;
  background-size: 100% 100%;
  background-image: url('../../picture/building.jpg');
  background-size: cover;
  background-position: center;
  #contain {
    height: 500px;
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
style