<template>
    <fwb-navbar :key="$route.fullPath">
        <template #logo>
            <fwb-navbar-logo :image-url="logo">
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
      <fwb-button v-if="isLoggedIn" @click="goToAccount">
          Account
      </fwb-button>
      <!-- Logout button only appears if user is logged in -->
      <fwb-button v-if="isLoggedIn" @click="logout">
          Logout
      </fwb-button>
    </template>
  </fwb-navbar>
</template>

<style scoped lang="postcss">
.navbar-buttons {
    @apply flex items-center gap-2;
}
</style>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import {
    FwbButton,
    FwbNavbar,
    FwbNavbarCollapse,
    FwbNavbarLink,
    FwbNavbarLogo,
} from 'flowbite-vue';

import logo from '@/assets/logo.svg'
import { computed } from 'vue';

const router = useRouter();

const isLoggedIn = computed(() => {
    return localStorage.getItem('userEmail') !== null;
});

const userRole = computed(() => {
    return localStorage.getItem('userRole');
});

const goToSignIn = () => {
    router.push('/signin');
};

const logout = () => {
    // Clear user data from localStorage
    localStorage.removeItem('userEmail');
    localStorage.removeItem('userRole');
    
    // Redirect to the home page or login page
    router.push('/').then(() => {
      window.location.reload(); // Optional: Force reload the page
    });
};

const goToCreateBooking = () => {
    router.push('/booking/create');
};

const goToAccount = () => {
    const email = localStorage.getItem('userEmail');
    if (userRole.value === 'customer') {
        router.push(`/account/${email}`);
    } else if (userRole.value === 'employee') {
        router.push('/employees');
    } else if (userRole.value === 'manager') {
        router.push('/manager');
    }
};

</script>
