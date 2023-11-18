<template>
    <fwb-navbar>
        <template #logo>
            <fwb-navbar-logo>
                Mar-1 Hotel
            </fwb-navbar-logo>
        </template>
        <template #default="{ isShowMenu }">
            <fwb-navbar-collapse :is-show-menu="isShowMenu">
                <fwb-navbar-link is-active link="/">
                    Home
                </fwb-navbar-link>
                <fwb-navbar-link link="/rooms">
                    Room Options
                </fwb-navbar-link>
                <fwb-navbar-link link="/schedule">
                    Schedule
                </fwb-navbar-link>
                <fwb-navbar-link link="/contact">
                    Contact
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
    FwbNavbarLogo,
} from 'flowbite-vue'
</script>