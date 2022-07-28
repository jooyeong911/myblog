import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    alias: "/home2",
    name: 'home2',
    component: () => import('@/components/apple/mainHome')
  }, 
  // /complain
  {
    path: '/complain',
    name: 'complain',
    component: () => import('@/components/complains/ComplainView')
  }, 
  {
    path: '/complains',
    name: 'complains',
    component: () => import('@/components/complains/AddComplain')
  },
  {
    path: '/board',
    name: 'board',
    component: () => import('@/views/BoardView')
  },  
  {
    path: '/add-board',
    name: 'add-board',
    component: () => import('@/views/AddBoard')
  },  
  {
    path: '/boards/:idx',
    name: 'edit-board',
    component: () => import('@/views/EditBoard')
  },
  // /upload
  {
    path: '/upload',
    name: 'upload',
    component: () => import('@/components/UploadImage')
  },  
  {
    path: "/tutorials",
    name: 'tutorials',
    component: () => import('@/components/tutorials/TutorialsList')
  },
  { 
    path: '/tutorials/:id',
    name: 'tutorial-details',
    component: () => import('@/components/tutorials/Tutorial')
  },  
  {
    path: '/add',
    name: 'add',
    component: () => import('@/components/tutorials/AddTutorial')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
