import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import VueSetupExtend from 'vite-plugin-vue-setup-extend'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    VueSetupExtend()
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 7070,
    proxy: {
      '/api': {  // 修改代理路径前缀为 '/api'
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
