<template>
    <div>
      <!-- 上传按钮 -->
      <el-button type="primary" @click="showUploadDialog">上传</el-button>
  
      <!-- 表格 -->
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
  
      <!-- 上传对话框 -->
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
  
    <el-dialog v-model="editDialogVisible" title="上传商品" :center="true">
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
        <el-button @click="hideEditDialog">取消</el-button>
        <el-button type="primary" @click="submitEditedForm">确定</el-button>
      </span>
    </el-dialog>
  
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  // 模拟获取数据
  const tableData = ref([]);
  
  onMounted(() => {
    axios.get('/api/product1').then((result) => {
      console.log(result.data.data);
      tableData.value = result.data.data;
    });
  });
  
  const editDialogVisible = ref(false);
  const editProduct = (row: { productName: string; productPrice: null; productImage: string; productInformation: string; productType: string; }) => {
    // 显示编辑表单对话框
    editDialogVisible.value = true;
    
    // 将当前行的数据填充到表单中
    formData.value.productName = row.productName;
    formData.value.productPrice = row.productPrice;
    formData.value.productImage = row.productImage;
    formData.value.productInformation = row.productInformation;
    formData.value.productType = row.productType;
  };
  
  // 定义提交编辑表单的函数
  const submitEditedForm = () => {
  
    const editedProductData: Product = {
      productName: formData.value.productName,
      productPrice: formData.value.productPrice,
      productImage: formData.value.productImage,
      productInformation: formData.value.productInformation,
      productType: formData.value.productType
    };
    // 提交表单数据到后端
    console.log(editedProductData)
    axios.post('/api/updateProduct', editedProductData)
      .then(response => {
        console.log('商品编辑成功:', response.data);
        // 在这里处理编辑成功后的逻辑
        // 关闭编辑表单对话框
        uploadDialogVisible.value = false;
      })
      .catch(error => {
        console.error('商品编辑失败:', error);
        // 在这里处理编辑失败后的逻辑
      });
  };
  
  const deleteProduct = (row: any) => {
    console.log('delete the product:', row);
  };
  
  const uploadDialogVisible = ref(false);
  const formData = ref({
    productName: '',
    productPrice: null,
    productImage: '',
    productInformation: '',
    productType: ''
  });
  
  const showUploadDialog = () => {
    console.log("点击上传");
    uploadDialogVisible.value = true;
    console.log(uploadDialogVisible.value);
  };
  
  const hideUploadDialog = () => {
    uploadDialogVisible.value = false;
  };
  const hideEditDialog = () =>{
    editDialogVisible.value = false;
  }
  
  interface Product {
    productName: string;
    productPrice: number | null;
    productImage: string;
    productInformation: string;
    productType: string;
  }
  
  const submitForm = (formName: string) => {
    // 构造与后端 Product 类相匹配的数据结构
    const productData: Product = {
      productName: formData.value.productName,
      productPrice: formData.value.productPrice,
      productImage: formData.value.productImage,
      productInformation: formData.value.productInformation,
      productType: formData.value.productType
    };
  
    // 提交表单数据到后端
    axios.post('/api/submitFormData', productData)
      .then(response => {
        console.log('表单提交成功:', response.data);
        // 在这里处理提交成功后的逻辑
      })
      .catch(error => {
        console.error('表单提交失败:', error);
        // 在这里处理提交失败后的逻辑
      });
  
    // 隐藏上传对话框
    uploadDialogVisible.value = false;
  };
  </script>
  