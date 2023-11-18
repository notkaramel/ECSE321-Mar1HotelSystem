<script setup lang="ts">
</script>

<template>
  <main>
    <div class="flex flex-col items-center py-50">
      Welcome to Mar-1 Hotel!
      <fwb-rating :rating="4.75">
        <template #besideText>
          <p class="ml-2 text-sm font-medium text-gray-500 dark:text-gray-400">
            4.75 out of 5
          </p>
        </template>
      </fwb-rating>
    </div>
  </main>
</template>

<script setup>
import { FwbRating } from 'flowbite-vue'
</script>
