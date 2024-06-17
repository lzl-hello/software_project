// 第一步：引入createRouter
import {createRouter,createWebHistory} from 'vue-router'
// 引入一个一个可能要呈现组件
import product from '@/components/ProductInfo.vue'
import login from '@/components/Login.vue'
import register from '@/components/Register.vue'
import index from '@/components/Index.vue'  


// 第二步：创建路由器
const router = createRouter({
  history:createWebHistory(), 
  routes:[ //一个一个的路由规则
    {
      path:'/product/:userId',
      name:'/product',
      component:product
    },
    {
      path:'/login',
      component:login
    },
    {
      path:'/index/:userId',
      name:'/index',
      component:index
    },
    {
        path:'/',
        component:index
      },
    {
      path:'/register',
      component:register
    },
  ]
})

// 暴露出去router
export default router
