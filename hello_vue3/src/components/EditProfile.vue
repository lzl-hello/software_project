<template>
    <div class="container">
        <header class="header">
            <h1>修改个人信息</h1>
        </header>

        <form @submit.prevent="submitForm" class="form">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" id="username" v-model="user.username" required />
            </div>

            <div class="form-group">
                <label for="phonenumber">电话号码</label>
                <input type="tel" id="phonenumber" v-model="user.phonenumber" required />
            </div>

            <div class="form-group">
                <label for="oldPassword">旧密码</label>
                <input type="password" id="oldPassword" v-model="oldPassword" required />
            </div>

            <div class="form-group">
                <label for="newPassword">新密码</label>
                <input type="password" id="newPassword" v-model="newPassword" required />
            </div>

            <button type="submit" class="submit-button">保存更改</button>`
        </form>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import { pa } from 'element-plus/es/locales.mjs';

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
        if (oldPassword.value !== user.value.password) {
            alert('旧密码错误');
            return;
        }
        await axios.put(`/api/updateUser`, null, {
            params: {
                userId: userId,
                username: user.value.username,
                phonenumber: user.value.phonenumber,
                newPassword: newPassword.value
            }
        });

        alert('个人信息已更新');
        router.push('/');
    } catch (error) {
        console.error('Error updating user data:', error);
        alert('更新失败，请重试');
    }
};
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start; /* 让容器内的内容从顶部开始排列 */
    height: 100vh;
    background-color: #f5f5f5;
    font-family: Arial, sans-serif;
    padding: 20px;
    padding-top: 50px; /* 增加顶部内边距以将内容向下移动 */
    box-sizing: border-box;
}

.header {
    margin-bottom: 20px;
}

.form {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 300px;
}

.form-group {
    margin-bottom: 15px;
    text-align: left;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.form-group input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.submit-button {
    width: 100%;
    padding: 10px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.submit-button:hover {
    background-color: #0056b3;
}

</style>
