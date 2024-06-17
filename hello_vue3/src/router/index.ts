// 第一步：引入createRouter
import {createRouter,createWebHistory} from 'vue-router'
// 引入一个一个可能要呈现组件
import product from '@/components/ProductInfo.vue'
import login from '@/components/Login.vue'
import register from '@/components/Register.vue'
import index from '@/components/Index.vue'  
import openproduct from '@/components/OpenProduct.vue'
import productclassify from '@/components/ProductClassify.vue'
import productperson from '@/components/ProductPerson.vue'
import productscenery from '@/components/ProductScenery.vue'
import productanimal from '@/components/ProductAnimal.vue'
import productbuilding from '@/components/ProductBuilding.vue'
import productevent from '@/components/ProductEvent.vue'
import productother from '@/components/ProductOther.vue'
import editprofile from '@/components/EditProfile.vue'


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
      path:'/openproduct',
      component:openproduct
    },
    {
      path:'/productclassify/:userId',
      name:'/productclassify',
      component:productclassify
    },
    {
      path:'/productscenery/:userId',
      name:'/productscenery',
      component:productscenery
    },
    {
      path:'/productanimal/:userId',
      name:'/productanimal',
      component:productanimal
    },
    {
      path:'/productbuilding/:userId',
      name:'/productbuilding',
      component:productbuilding
    },
    {
      path:'/productevent/:userId',
      name:'/productevent',
      component:productevent
    },
    {
      path:'/productother/:userId',
      name:'/productother',
      component:productother
    },
    {
      path:'/productperson/:userId',
      name:'/productperson',
      component:productperson
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
        component:login
      },
    {
      path:'/register',
      component:register
    },
    {
      path:'/editprofile/:userId',
      name:'/editprofile',
      component:editprofile
    },
  ]
})

// 暴露出去router
export default router
