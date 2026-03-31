import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: () => import('../views/MainView.vue')
    },
    {
      path: '/study/:id',
      name: 'study',
      component: () => import('../views/StudyView.vue')
    },
    {
      path: '/ranking',
      name: 'ranking',
      component: () => import('../views/RankingBoardView.vue')
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: () => import('../views/MyPageView.vue')
    },
    {
      path: '/follow',
      name: 'follow',
      component: () => import('../views/FollowView.vue')
    },
    {
      path: '/edit-profile',
      name: 'editProfile',
      component: () => import('../views/EditProfileView.vue')
    },
  ]
})

export default router