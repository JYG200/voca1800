import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

// 1. Vuetify 관련 필수 임포트
import 'vuetify/styles' 
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// 2. 아이콘 폰트 로드 (mdi-menu 같은 텍스트를 아이콘으로 바꿔줍니다)
import '@mdi/font/css/materialdesignicons.css' 

// 3. Vuetify 인스턴스 생성
const vuetify = createVuetify({
  components,
  directives,
})

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(vuetify) // 4. 앱에 Vuetify 등록 (이게 핵심입니다!)

app.mount('#app')