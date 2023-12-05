<script setup lang="ts">

import {
    ref
} from 'vue'

import {
    FwbButton,
    FwbInput,
    FwbP
} from 'flowbite-vue'

</script>

<script lang="ts">

import axios from 'axios';
import router from '@/router';

const backendUrl = import.meta.env.VITE_BACKEND;

let guest = ref(true);
let bookingIdInput = ref();
let error = ref(false);
let errorMessage = ref('');

async function getBookingExists(bookingId:number) {
    let output = true;
    let generalUser = await axios.get(backendUrl + "/booking/" + bookingId)
        .then(response => response.data)
        .catch(err => {
            output = false;
        });
    return output;
}

async function clearError() {
  error.value = false;
  errorMessage.value = "";
}

async function findBooking() {
    clearError();
    let bookingExists = await getBookingExists(bookingIdInput.value);
    if (bookingExists) {
        router.push('/booking/summary/' + bookingIdInput.value);
    } else {
        error.value = true;
        errorMessage.value = 'Booking not found! If this is a mistake, please contact us.';
    }
    
}

</script>


<template>
    <div class="form-container">
      <h1 class="text-3xl font-bold mb-4">Bookings</h1>
  
      <section v-if="!guest" class="mb-6">
        <!-- TODO add a table with all their bookings -->
        <h2 class="text-xl font-semibold mb-4">View your bookings</h2>
      </section>
  
      <section v-if="guest" class="mb-3">
        <h2 class="text-xl font-semibold mb-4">Find your booking</h2>
        <fwb-input v-model="bookingIdInput" placeholder="for example: 423" label="Please enter your booking code: " type="number" class="mb-4" number/>
      </section>
  
      
  
      <fwb-button type="submit" @click="findBooking" gradient="blue">
        Find Booking
      </fwb-button>
  
      <!-- Error Message -->
      <div v-if="error" class="error-message mt-4">
        <fwb-p class="text-red-600">{{ errorMessage }}</fwb-p>
      </div>
      <div class="mb-2">
        <fwb-p class="">Don't have a booking? <a href="/booking/create" class="text-lg font-medium text-blue-600 hover:underline">Book now!</a></fwb-p>
      </div>
    </div>
  </template>

<style scoped lang="postcss">
.form-container {
  @apply bg-gray-100 p-8 rounded-lg shadow-md w-full lg:w-1/2 xl:w-1/3 mx-auto border;
}
</style>