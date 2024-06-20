<template>
      <header class="header">
        <router-link :to="{ name: '/productclassify', params: { userId: userId } }" class="title-container">  
          <h2 class="title">照片管理系统</h2>
        </router-link>
      </header>
  
    <el-table :data="tableData">
      <el-table-column prop="productName" label="照片名称" width="150"></el-table-column>
      <el-table-column label="图像" width="250">
        <template v-slot="scope">
          <img :src="scope.row.productImage" width="150px" />
        </template>
      </el-table-column>

      <el-table-column prop="productInformation" label="基本信息" width="380"></el-table-column>

      <el-table-column prop="location" label="地点" width="120"></el-table-column>
    </el-table>
  
    
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted, watch } from 'vue';
  import axios from 'axios';
  import { ElMessage, ElMessageBox } from 'element-plus';
  import { useRoute } from 'vue-router';
  import moment from 'moment';

  const route = useRoute();
  const userId = route.params.userId;

  interface Product {
    id: number | null;
    productName: string;
    productImage: string;
    productInformation: string;
    productType: string;
    productTimeStamp: string;
    location: string;
    permission: number;
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
  });
  const uploadDialogVisible = ref(false);
  const searchQuery = ref('');
  
  onMounted(() => {
    fetchProducts();
  });

  const type = "人物";
  
  const fetchProducts = () => {
    // axios.get('/api/product1')
    axios.get('/api/albumInfo', { params: { userId: userId ,productType: "其他" }})
    .then((result) => {
      tableData.value = result.data.data;
    });
  };
  
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
  
