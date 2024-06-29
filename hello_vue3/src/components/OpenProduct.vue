<template>
    <header class="header">
      <router-link to="/index/12" class="title-container">
        <h2 class="title">照片管理系统</h2>
      </router-link>
    </header>

    <div class="search-bar">
        <el-input v-model="searchQuery" placeholder="输入关键词搜索"></el-input>
        <el-button type="primary" @click="searchProducts">搜索</el-button>
      </div>
  
    <el-table :data="tableData">
      <!-- <el-table-column prop="productName" label="照片名称" width="150"></el-table-column> -->
      <el-table-column prop="productName" label="照片名称" width="150" sortable></el-table-column>
      <el-table-column label="图像" width="250">
        <template v-slot="scope">
          <img :src="scope.row.productImage" width="150px" />
        </template>
      </el-table-column>
      <el-table-column prop="productType" label="类型" width="140"></el-table-column>
      <el-table-column prop="productInformation" label="基本信息" width="380"></el-table-column>
      <!-- <el-table-column prop="productTimeStamp" label="最后修改时间" width="200"></el-table-column> -->
      <el-table-column prop="productTimeStamp" label="最后修改时间" width="200" sortable></el-table-column>
      <el-table-column prop="location" label="地点" width="120"></el-table-column>
      <el-table-column label="点赞" width="120">
        <template v-slot="scope">
          <el-button type="text" @click="thumbUp(scope.row)">
            👍 {{ scope.row.productThumb }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  
    
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted, watch } from 'vue';
  import axios from 'axios';
  import { ElMessage, ElMessageBox } from 'element-plus';
  import { useRoute } from 'vue-router';
  import moment from 'moment';

  interface Product {
    id: number | null;
    productName: string;
    productImage: string;
    productInformation: string;
    productType: string;
    productTimeStamp: string;
    location: string;
    permission: number;
    productThumb: number;
  }
  
  const tableData = ref<Product[]>([]);
  const formData = ref<Product>({
    id: null,
    productName: '',
    productImage: '',
    productInformation: '',
    productType: '',
    productTimeStamp:'',
    location: '',
    permission: 0,
    productThumb: 0,
  });
  const uploadDialogVisible = ref(false);
  const searchQuery = ref('');
  
  onMounted(() => {
    fetchProducts();
  });
  
  const fetchProducts = () => {
    // axios.get('/api/product1')
    axios.get('/api/productAll')
    .then((result) => {
      tableData.value = result.data.data;
    });
  };
  
  const searchProducts = () => {
    axios.get('/api/searchAllProducts', { params: { query: searchQuery.value } })
      .then(response => {
        tableData.value = response.data.data;
      })
      .catch(error => {
        console.error('搜索失败:', error);
      });
  };

  const thumbUp = (product: Product) => {
  axios.post('/api/thumb', null, { params: { userId: product.id } })
    .then(response => {
      console.log('Response received:', response.data);
      if (response.data.code === 1) { // 根据后端返回的 code 判断
        product.productThumb += 1;
        ElMessage.success('点赞成功');
      } else {
        ElMessage.error('点赞失败');
      }
    })
    .catch(error => {
      console.error('点赞失败:', error.response ? error.response.data : error.message);
      ElMessage.error('点赞失败');
    });
  };
//   const handleSortChange = (sortInfo: { prop: string; order: string }) => {
//   if (sortInfo.prop === 'productTimeStamp') {
//     tableData.value.sort((a, b) => {
//       if (sortInfo.order === 'ascending') {
//         return new Date(a.productTimeStamp).getTime() - new Date(b.productTimeStamp).getTime();
//       } else {
//         return new Date(b.productTimeStamp).getTime() - new Date(a.productTimeStamp).getTime();
//       }
//     });
//   } else if (sortInfo.prop === 'productName') {
//     tableData.value.sort((a, b) => {
//       if (sortInfo.order === 'ascending') {
//         return a.productName.localeCompare(b.productName, 'zh-Hans-CN', { numeric: true });
//       } else {
//         return b.productName.localeCompare(a.productName, 'zh-Hans-CN', { numeric: true });
//       }
//     });
//   }
// };
  
  </script>
  
  <style scoped>
  .search-bar {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .search-bar el-input {
    margin-right: 10px;
  }
  </style>
  
