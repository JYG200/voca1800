import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import VueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [
    vue(),
    VueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },

  server: {
    host: '0.0.0.0', // 도커 외부 접속 허용
    watch: {
      usePolling: true, 
    },
    proxy: {
      '/api': {
        target: 'http://voca1800-api:8080', // 백엔드 도커 서비스 이름
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '') // '/api'를 떼고 보냄
      }
    }
  }
})