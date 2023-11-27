import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RoomView from '../views/RoomView.vue'
import CreateBookingView from '../views/CreateBookingView.vue'
import ViewBookingView from '../views/BookingSummaryView.vue'
import SignInView from '../views/SignInView.vue'
import RegistrationView from '../views/RegistrationView.vue'
import ManagerView from '../views/ManagerView.vue'
import AccountView from '../views/AccountView.vue';

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
      path: '/booking/create',
      name: 'createBooking',
      component: CreateBookingView
    },
    {
      path: '/booking/summary/:bookingId',
      name: 'Booking Summary View',
      component: ViewBookingView,
      props: route => ({ query: route.query.bookingId })
    },
    {
      path: '/signin',
      name: 'signin',
      component: SignInView
    },
    {
      path: '/manager',
      name: 'manager',
      component: ManagerView
    },
    {
      path: '/registration',
      name: 'registration',
      component: RegistrationView

    },
    {
      path:'/account/:email',
      name: 'account',
      component: AccountView
    }
  ],

})

export default router
