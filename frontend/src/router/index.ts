import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RoomView from '../views/RoomView.vue'
import ViewBookingView from '../views/BookingSummaryView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/rooms',
      name: 'rooms',
      component: RoomView
    },
    {
      path: '/booking/summary/:bookingId',
      name: 'Booking Summary View',
      component: ViewBookingView,
      props: route => ({ query: route.query.bookingId })
    }
  ],

})

export default router
