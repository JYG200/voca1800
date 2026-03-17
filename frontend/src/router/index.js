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
    {
      path: '/ranking',
      name: 'ranking',
      component: () => import('../views/RankingBoardView/RankingBoardView.vue')
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: () => import('../views/MyPageView/MyPageView.vue')
    },
    {
      path: '/follow',
      name: 'follow',
      component: () => import('../views/FollowView/FollowView.vue')
    },
    {
      path: '/edit-profile',
      name: 'editProfile',
      component: () => import('../views/EditProfileView/EditProfileView.vue')
    },
  ]
})

export default router