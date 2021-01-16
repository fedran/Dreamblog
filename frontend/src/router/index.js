import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/views/Home.vue'

const routes = [
  {
    path: '/a/:id(\\d+)',
    name: 'Article',
    props: true,
    component: () => import('@/views/Article.vue')
  },
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: () => import('@/views/NotFound.vue')
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
