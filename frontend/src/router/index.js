import {createRouter, createWebHistory} from 'vue-router'

const routes = [
  {
    path: '/a/:id(\\d+)',
    name: 'Article',
    props: true,
    component: () => import('@/views/Article.vue')
  },
  {
    path: '/:queryString(new|top)?',
    name: 'ArticleList',
    props: true,
    component: () => import('@/views/ArticleList.vue')
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
