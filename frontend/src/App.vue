<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar from './components/NavBar.vue'
import Footer from './components/PageFooter.vue'

import './style.css'
</script>

<template>
    <Navbar />
    <div class="main">
        <RouterView /> 
    </div>
    <Footer />
</template>

<style lang="postcss">
.main {
    @apply p-2 m-4;
}
</style>