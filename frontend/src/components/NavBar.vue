<template>
    <fwb-navbar>
        <template #logo>
            <fwb-navbar-logo
                alt="Mar-1 logo"
                image-url="../assets/kanaan.jpg"
                link="#">
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
                Book now
            </fwb-button>
            <!-- TODO how to add a space?? They are stuck together -->
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