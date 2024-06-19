<template>
  <div id="photo-gallery">
    <header class="header">
      <router-link to="/index/12" class="title-container">
        <h2 class="title">照片管理系统</h2>
      </router-link>
    </header>

    <div class="top-bar">
      <el-button type="primary" @click="showUploadDialog">上传</el-button>
      <div class="search-bar">
        <el-input v-model="searchQuery" placeholder="输入关键词搜索"></el-input>
        <el-button type="primary" @click="searchProducts">搜索</el-button>
      </div>
    </div>

    <div class="photo-grid">
      <el-card v-for="item in tableData" :key="item.id" class="photo-card">
        <img :src="item.productImage" class="photo" />
        <div class="photo-info">
          <h3>{{ item.productName }}</h3>
          <p><strong>类型:</strong> {{ item.productType }}</p>
          <p><strong>基本信息:</strong> {{ item.productInformation }}</p>
          <p><strong>最后修改时间:</strong> {{ item.productTimeStamp }}</p>
          <p><strong>地点:</strong> {{ item.location }}</p>
          <p><strong>权限:</strong> <span v-if="item.permission">公开</span><span v-else>私有</span></p>
          <!-- <div class="actions">
            <el-button type="primary" @click="editProduct(item)">编辑</el-button>
            <el-button type="danger" @click="deleteProduct(item)">删除</el-button>
          </div> -->
        </div>
        <div class="photo-actions">
          <el-button type="primary" @click="editProduct(item)">编辑</el-button>
          <el-button type="danger" @click="deleteProduct(item)">删除</el-button>
        </div>
      </el-card>
    </div>

    <el-dialog v-model="uploadDialogVisible" title="修改照片" :center="true">
      <el-form :model="formData" label-width="100px">
        <el-form-item label="图片名称" prop="productName">
          <el-input v-model="formData.productName"></el-input>
        </el-form-item>
        <el-form-item label="类型描述" prop="productType">
          <el-radio-group v-model="formData.productType">
            <el-radio label="人物">人物</el-radio>
            <el-radio label="风景">风景</el-radio>
            <el-radio label="动物">动物</el-radio>
            <el-radio label="建筑">建筑</el-radio>
            <el-radio label="事件">事件</el-radio>
            <el-radio label="其他">其他</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图片" prop="productImage">
          <el-input v-model="formData.productImage"></el-input>
        </el-form-item>
        <el-form-item label="图片上传">
          <el-upload
            action="/api/upload"
            list-type="picture"
            :on-success="handleUploadSuccess"
            :auto-upload="true"
          >
            <el-button type="primary">选择并上传图片</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="基本信息" prop="productInformation">
          <el-input type="textarea" v-model="formData.productInformation"></el-input>
        </el-form-item>
        <el-form-item label="地点" prop="location">
          <el-input v-model.number="formData.location"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="permission">
          <el-radio-group v-model="formData.permission">
            <el-radio :label="true">公开</el-radio>
            <el-radio :label="false">私有</el-radio>
          </el-radio-group>
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
  productTimeStamp: '',
  location: '',
  permission: 0,
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
    productTimeStamp: '',
    location: '',
    permission: 0,
  };
  uploadDialogVisible.value = true;
};

const hideUploadDialog = () => {
  uploadDialogVisible.value = false;
};

onMounted(() => {
  fetchProducts();
});

const fetchProducts = () => {
  axios.get('/api/product1', { params: { userId: userId } })
    .then((result) => {
      tableData.value = result.data.data;
    });
};

const submitForm = () => {
  const currentTime = moment().format('YYYY-MM-DD HH:mm:ss');
  formData.value.productTimeStamp = currentTime;
  if (formData.value.id) {
    axios.post('/api/updateProduct', formData.value)
      .then(response => {
        uploadDialogVisible.value = false;
        fetchProducts();
      })
      .catch(error => {
        console.error('表单更新失败:', error);
      });
  } else {
    axios.post('/api/submitFormData', formData.value, { params: { userId: userId } })
      .then(response => {
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
    axios.post('/api/deleteProduct', { id: row.id })
      .then(response => {
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
  if (!searchQuery.value.trim()) {
    ElMessage({
      type: 'warning',
      message: '搜索条件不能为空'
    });
    return;
  }

  axios.get('/api/searchProducts', { params: { userId: userId, query: searchQuery.value } })
    .then(response => {
      tableData.value = response.data.data;
    })
    .catch(error => {
      console.error('搜索失败:', error);
    });
};


const handleUploadSuccess = (response: any) => {
  formData.value.productImage = response.url;
  ElMessage.success('图片上传成功');
};
</script>

<style scoped>
#photo-gallery {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.header {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.title-container {
  text-align: center;
}

.top-bar {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-bar el-input {
  margin-right: 10px;
}

.photo-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
  width: 100%;
}

.photo-card {
  width: 250px; /* 固定宽度 */
  height: 600px; /* 固定高度 */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border: 1px solid #ccc;
  border-radius: 8px;
  overflow: hidden;
  padding: 0;
}

.photo {
  width: 100%;
  height: 200px; /* 固定高度 */
  border-radius: 8px 8px 0 0;
  object-fit: cover;
}

.photo-info {
  padding: 10px;
  flex-grow: 1; /* 使信息部分填满剩余空间 */
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 0 0 8px 8px;
}

.photo-actions {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  /* background-color: #f0f0f0; */
}

.photo-actions .el-button {
  width: 48%; /* 确保两个按钮在一行显示 */
}

</style>
