<template>
  <main>
    <div class="flex flex-col items-center py-50">
      <!-- Welcome to Mar-1 Hotel! -->
      {{ welcomeMsg }}
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

<script setup lang="ts">
import { FwbRating } from 'flowbite-vue'
</script>


<script lang="ts">
import axios from 'axios'

const backendUrl = import.meta.env.VITE_BACKEND;

let welcomeMsg = await axios.get(backendUrl).then((response) => response.data);
// Alternatively, using fetch 
// let welcomeMsg = await fetch(backendUrl).then(response => response.text());

export default {
  data() {
    return {
      welcomeMsg: welcomeMsg,
      // tea: tea,
    };
  }
}
</script>