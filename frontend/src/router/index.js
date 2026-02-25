import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: () => import('../views/MainView/MainView.vue')
    },
    {
      path: '/study/:id', 
      name: 'study',
      component: () => import('../views/StudyView/StudyView.vue')
    },
  ]
})

export default router