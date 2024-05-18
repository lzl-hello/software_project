<template>
    <el-button type="primary" @click="showSearchDialog">搜索</el-button>
    <el-button type="primary" @click="showUploadDialog">上传</el-button>
  
    <el-table :data="tableData">
      <el-table-column prop="productName" label="商品名称" width="180"></el-table-column>
      <el-table-column label="图像" width="380">
        <template v-slot="scope">
          <img :src="scope.row.productImage" width="150px" />
        </template>
      </el-table-column>
      <el-table-column prop="productPrice" label="价格" width="140"></el-table-column>
      <el-table-column prop="productInformation" label="基本信息" width="440"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="primary" @click="editProduct(scope.row)">编辑</el-button>
          <el-button type="primary" @click="deleteProduct(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  
    <div>
      <el-dialog v-model="uploadDialogVisible" title="上传商品" :center="true">
        <el-form :model="formData" label-width="100px">
          <el-form-item label="商品名称" prop="productName">
            <el-input v-model="formData.productName"></el-input>
          </el-form-item>
          <el-form-item label="价格" prop="productPrice">
            <el-input v-model.number="formData.productPrice"></el-input>
          </el-form-item>
          <el-form-item label="图片" prop="productImage">
            <el-input v-model.number="formData.productImage"></el-input>
          </el-form-item>
          <el-form-item label="基本信息" prop="productInformation">
            <el-input type="textarea" v-model="formData.productInformation"></el-input>
          </el-form-item>
          <el-form-item label="类型" prop="productType">
            <el-select v-model="formData.productType" placeholder="请选择">
              <el-option label="类型1" value="1"></el-option>
              <el-option label="类型2" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="hideUploadDialog">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script setup lang="ts">
  
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import { ElMessage, ElMessageBox } from 'element-plus';
  import { ta } from 'element-plus/es/locales.mjs';
  
  interface Product {
    id: number | null;
    productName: string;
    productPrice: number | null;
    productImage: string;
    productInformation: string;
    productType: string;
  }
  
  const tableData = ref<Product[]>([]);
  
  const formData = ref<Product>({
    id: null,
    productName: '',
    productPrice: null,
    productImage: '',
    productInformation: '',
    productType: ''
  });
  
  const uploadDialogVisible = ref(false);
  
  const showUploadDialog = () => {
    formData.value = {
      id: null,
      productName: '',
      productPrice: null,
      productImage: '',
      productInformation: '',
      productType: ''
    };
    uploadDialogVisible.value = true;
  };
  
  const hideUploadDialog = () => {
    uploadDialogVisible.value = false;
  };
  
  onMounted(() => {
    axios.get('/api/product1').then((result) => {
      tableData.value = result.data.data;
      console.log(tableData.value)
    });
  });
  
  const submitForm = () => {
    console.log("Submitting form with ID:", formData.value.id);
    if (formData.value.id) {
      console.log("更新")
      // 更新产品
      axios.post('/api/updateProduct', formData.value)
        .then(response => {
          console.log('表单更新成功:', response.data);
          // 在这里处理更新成功后的逻辑
          uploadDialogVisible.value = false;
        })
        .catch(error => {
          console.error('表单更新失败:', error);
          // 在这里处理更新失败后的逻辑
        });
    } else {
      // 新增产品
      console.log("上传")
      axios.post('/api/submitFormData', formData.value)
        .then(response => {
          console.log('表单提交成功:', response.data);
          // 在这里处理提交成功后的逻辑
          uploadDialogVisible.value = false;
        })
        .catch(error => {
          console.error('表单提交失败:', error);
          // 在这里处理提交失败后的逻辑
        });
    }
  };
  
  const editProduct = (row: Product) => {
    console.log("Editing product with ID:", row.id);
    formData.value = { ...row };
    uploadDialogVisible.value = true;
  };
  
  const deleteProduct = (row: Product) => {
    ElMessageBox.confirm(
      '此操作将永久删除该商品, 是否继续?',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    ).then(() => {
      axios.post('/api/deleteProduct', { id: row.id })
        .then(response => {
          console.log('商品删除成功:', response.data);
          const index = tableData.value.findIndex(product => product.id === row.id);
          if (index !== -1) {
            tableData.value.splice(index, 1);
          }
          ElMessage({
            type: 'success',
            message: '删除成功!'
          });
        })
        .catch(error => {
          console.error('商品删除失败:', error);
          ElMessage({
            type: 'error',
            message: '删除失败'
          });
        });
    }).catch(() => {
      ElMessage({
        type: 'info',
        message: '已取消删除'
      });
    });
  };
  
  const showSearchDialog = () => {
    ElMessage({
      message: '搜索功能待实现',
      type: 'info'
    });
  }
  
  </script>
  