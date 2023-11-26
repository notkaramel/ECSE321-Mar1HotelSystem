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
                <fwb-navbar-link link="/rooms">
                    Room Options
                </fwb-navbar-link>
                <fwb-navbar-link link="/booking">
                    Booking
                </fwb-navbar-link>
                <fwb-navbar-link link="/employees">
                    Employees
                </fwb-navbar-link>
                <fwb-navbar-link link="/policy">
                    Policy
                </fwb-navbar-link>
            </fwb-navbar-collapse>
        </template>
        <template #right-side>
            <fwb-button>
                Sign in
            </fwb-button>
        </template>
    </fwb-navbar>
</template>
  
<script setup lang="ts">
import {
    FwbButton,
    FwbNavbar,
    FwbNavbarCollapse,
    FwbNavbarLink,
    FwbNavbarLogo
} from 'flowbite-vue'

import logo from '@/assets/logo.svg'
</script>