<template>
    <fwb-navbar>
        <template #logo>
            <fwb-navbar-logo :image-url=logo>
                Mar-1 Hotel
            </fwb-navbar-logo>
        </template>
        <template #default="{ isShowMenu }">
            <fwb-navbar-collapse :is-show-menu="isShowMenu">
                <fwb-navbar-link link="/">
                    Home
                </fwb-navbar-link>
                <fwb-navbar-link link="/booking">
                    Booking
                </fwb-navbar-link>
                <fwb-navbar-link link="/employees">
                    Employee
                </fwb-navbar-link>
                <fwb-navbar-link link="/manager">
                    Manager
                </fwb-navbar-link>
            </fwb-navbar-collapse>
        </template>
        <template #right-side>
      <fwb-button @click="goToCreateBooking">
        Book Now
      </fwb-button>
      <fwb-button v-if="!isLoggedIn" @click="goToSignIn">
        Sign in
      </fwb-button>
      <fwb-button v-else @click="goToAccount">
        Account
      </fwb-button>
    </template>
  </fwb-navbar>
</template>
<script setup lang="ts">
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import {
  FwbButton,
  FwbNavbar,
  FwbNavbarCollapse,
  FwbNavbarLink,
  FwbNavbarLogo,
} from 'flowbite-vue';
import logo from '@/assets/logo.svg';

const router = useRouter();
const userEmail = ref(localStorage.getItem('userEmail'));

const isLoggedIn = computed(() => !!userEmail.value);

const goToSignIn = () => {
  router.push('/signin');
};

const goToCreateBooking = () => {
  router.push('/booking/create');
};

const goToAccount = () => {
  // Assuming different routes for different user roles
  const userRole = localStorage.getItem('userRole');
  if (userRole === 'employee') {
    router.push('/employees');
  } else if (userRole === 'manager') {
    router.push('/manager');
  } else {
    router.push(`/account/${userEmail.value}`);
  }
};
</script>
