<template>
    <fwb-navbar>
        <template #logo>
            <fwb-navbar-logo>
                Flowbite
            </fwb-navbar-logo>
        </template>
        <template #default="{ isShowMenu }">
            <fwb-navbar-collapse :is-show-menu="isShowMenu">
                <fwb-navbar-link is-active link="#">
                    Home
                </fwb-navbar-link>
                <fwb-navbar-link link="#">
                    Services
                </fwb-navbar-link>
                <fwb-navbar-link link="#">
                    Pricing
                </fwb-navbar-link>
                <fwb-navbar-link link="#">
                    Contact
                </fwb-navbar-link>
            </fwb-navbar-collapse>
        </template>
        <template #right-side>
            <fwb-button>
                Get started
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