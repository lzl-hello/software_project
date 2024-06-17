<template>
  <el-button type="primary" @click="showUploadDialog">上传</el-button>
  <div class="search-bar">
      <el-input v-model="searchQuery" placeholder="输入关键词搜索"></el-input>
      <el-button type="primary" @click="searchProducts">搜索</el-button>
    </div>

  <el-table :data="tableData">
    <el-table-column prop="productName" label="照片名称" width="180"></el-table-column>
    <el-table-column label="图像" width="380">
      <template v-slot="scope">
        <img :src="scope.row.productImage" width="150px" />
      </template>
    </el-table-column>
    <el-table-column prop="productType" label="类型" width="140"></el-table-column>
    <el-table-column prop="productInformation" label="基本信息" width="400"></el-table-column>
    <el-table-column prop="productTimeStamp" label="最后修改时间" width="200"></el-table-column>
    <el-table-column label="操作">
      <template v-slot="scope">
        <el-button type="primary" @click="editProduct(scope.row)">编辑</el-button>
        <el-button type="danger" @click="deleteProduct(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <div>
    <el-dialog v-model="uploadDialogVisible" title="修改照片" :center="true">
      <el-form :model="formData" label-width="100px">
        <el-form-item label="图片名称" prop="productName">
          <el-input v-model="formData.productName"></el-input>
        </el-form-item>
        <el-form-item label="类型描述" prop="productType">
          <el-input v-model.number="formData.productType"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="productImage">
          <el-input v-model="formData.productImage"></el-input>
        </el-form-item>
        <el-form-item label="基本信息" prop="productInformation">
          <el-input type="textarea" v-model="formData.productInformation"></el-input>
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
}

const tableData = ref<Product[]>([]);
const formData = ref<Product>({
  id: null,
  productName: '',
  productImage: '',
  productInformation: '',
  productType: '',
  productTimeStamp:''
});
const uploadDialogVisible = ref(false);
const searchQuery = ref('');

const showUploadDialog = () => {
  formData.value = {
    id: null,
    productName: '',
    productImage: '',
    productInformation: '',
    productType: '',
    productTimeStamp:''
  };
  uploadDialogVisible.value = true;
};

const hideUploadDialog = () => {
  uploadDialogVisible.value = false;
};

onMounted(() => {
  fetchProducts();
  console.log(userId)
});

const fetchProducts = () => {
  // axios.get('/api/product1')
  axios.get('/api/product1', { params: { userId: userId } })
  .then((result) => {
    tableData.value = result.data.data;
  });
};

const submitForm = () => {
  // 将格式化后的时间戳字符串添加到表单数据中
  const currentTime = moment().format('YYYY-MM-DD HH:mm:ss');
  formData.value.productTimeStamp = currentTime;
  if (formData.value.id) {
    // 更新产品
    axios.post('/api/updateProduct', formData.value)
      .then(response => {
        console.log('表单更新成功:', response.data);
        uploadDialogVisible.value = false;
        fetchProducts();
      })
      .catch(error => {
        console.error('表单更新失败:', error);
      });
  } else {
    // 新增产品
    axios.post('/api/submitFormData', formData.value, { params: { userId: userId } })
      .then(response => {
        console.log('表单提交成功:', response.data);
        uploadDialogVisible.value = false;
        fetchProducts();
      })
      .catch(error => {
        console.error('表单提交失败:', error);
      });
  }
};

const editProduct = (row: Product) => {
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
      console.log("要删除的产品ia",row.id);
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

const searchProducts = () => {
  axios.get('/api/searchProducts', { params: { userId:userId, query: searchQuery.value } })
    .then(response => {
      tableData.value = response.data.data;
    })
    .catch(error => {
      console.error('搜索失败:', error);
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
