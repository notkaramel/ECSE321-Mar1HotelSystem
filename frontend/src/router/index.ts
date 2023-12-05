import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateBookingView from '../views/CreateBookingView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import ViewBookingView from '../views/BookingSummaryView.vue'
import SignInView from '../views/SignInView.vue'
import RegistrationView from '../views/RegistrationView.vue'
import ManagerView from '../views/ManagerView.vue'
import AccountView from '../views/AccountView.vue';
import BookingView from '../views/BookingView.vue';
import ContactUsViewVue from '@/views/ContactUsView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/booking/create',
      name: 'createBooking',
      component: CreateBookingView
    },
    {
      path: '/employees',
      name: 'employees',
      component: EmployeeView
    },
    {
      path: '/booking/summary/:bookingId',
      name: 'Booking Summary View',
      component: ViewBookingView,
      props: route => ({ query: route.query.bookingId })
    },
    {
      path: '/booking',
      name: 'Find Booking View',
      component: BookingView,
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
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'notFound',
      component: HomeView
    },
    {
      path: '/contact',
      name: 'contact',
      component: ContactUsViewVue
    }
  ],

})

export default router
