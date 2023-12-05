<script setup lang="ts">

import {
    ref
} from 'vue'

import {
    FwbButton,
    FwbInput,
    FwbSelect,
    FwbP
} from 'flowbite-vue'


</script>



<script lang="ts">

import axios from 'axios';
import router from '@/router';

const backendUrl = import.meta.env.VITE_BACKEND;

const firstName = ref('')
const lastName = ref('')
const email = ref('')
const phoneNumber = ref()

const roomType = ref('')
const checkInDate = ref()
const checkOutDate = ref()
// const paymentCode = ref()

const guest = ref(true)

const error = ref(false)
const errorMessage = ref('')

const requests = ref<string[]>([])


async function getGeneralUserExists(email:string) {
    let output = true;
    let generalUser = await axios.get(backendUrl + "/generalUsers/" + email)
        .then(response => response.data)
        .catch(err => {
            if (err.response.status != 404) console.log(err);
            output = false;
        });
    return output;
}

async function createGeneralUser(firstName: string, lastName: string, email: string, phoneNumber: number) {
    let createdGeneralUser = await axios.post(backendUrl + "/generalUsers/create", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber})
        .then(response => response.data)
        .catch(err => {
            console.log(err);
            error.value = true;
            errorMessage.value = err.response.data;

        });
    return createdGeneralUser;
}

async function createBooking(email:string, roomId: string, paymentId: number) {
    let createdBooking = await axios.post(backendUrl + "/booking/create", {"generalUserEmail": email, "roomId": roomId, "paymentId": paymentId})
        .then(response => response.data)
        .catch(err => {
            console.log(err);
            error.value = true;
            errorMessage.value = err.response.data;
        });
    if (error.value) return;
    let createdBookingId = createdBooking.bookingId;
    return createdBookingId;
}

async function getRoomOfType(roomType:string) {
    if (roomType == "") {
        error.value = true;
        errorMessage.value = "Please select a room type!";
        return;
    }
    let room = await axios.get(backendUrl + "/rooms/roomType/" + roomType)
        .then(response => response.data)
        .catch(err => {
            console.log(err);
            error.value = true;
            errorMessage.value = err.response.data;
        });

    if (room.roomList.length == 0) {
        error.value = true;
        errorMessage.value = "No rooms of this type available!";
        return;
    }
    room = room.roomList[0];
    let roomId = room.roomId;
    return roomId;
  
}


async function getCost(roomType:string, checkInDate:Date, checkOutDate:Date) {
    if (checkInDate == null || checkOutDate == null) {
        error.value = true;
        errorMessage.value = "Please enter a check-in and check-out date!";
        return 1;
    }
    let cid = new Date(checkInDate);
    let cod = new Date(checkOutDate);
    if (cid >= cod) {
        error.value = true;
        errorMessage.value = "Check-in date must be before check-out date!";
        return 1;
    }
    let nights = (cod.getTime() - cid.getTime()) / (1000 * 3600 * 24);
    let cost = 0;
    if (roomType == "Regular") {
        cost = 40 * nights;
    } else if (roomType == "Suite") {
        cost = 60 * nights;
    } else if (roomType == "Deluxe") {
        cost = 80 * nights;
    }
    return cost;
  
}

async function createPayment(amount:number) {
    let createdPayment = await axios.post(backendUrl + "/payment/create", {"amount": amount})
        .then(response => response.data)
        .catch(err => {
            console.log(err);
            error.value = true;
            errorMessage.value = err.response.data;
        });
    let paymentId = createdPayment.paymentId;
    return paymentId;
}

async function createRequest(requestDescription:string, bookingId:number) {
  if (requestDescription == "") {
    error.value = true;
    errorMessage.value = "Please make sure all requests are filled out!";
    return;
  }
  let createdRequest = await axios.post(backendUrl + "/request/create", {"description": requestDescription, "bookingId": bookingId, "isFulfilled": false})
        .then(response => response.data)
        .catch(err => {
            console.log(err);
            error.value = true;
            errorMessage.value = err.response.data;
        });
    let requestId = createdRequest.requestId;
    return requestId;

}


async function clearError() {
  error.value = false;
  errorMessage.value = "";
}

async function addRequest() {
  requests.value.push("");
}

async function removeRequest() {
  requests.value.pop();
}


//TODO replace this with actually getting the customer info
async function getCustomerInfo() {
  firstName.value = "Customery";
  lastName.value = "Customeroo";
  email.value = "customery.customeroo@mail.ca";
  phoneNumber.value = 1234567890;
}

async function submitBooking() {
  clearError();
  let guestId = "";
  if (guest.value) {
    if (firstName.value == "" || lastName.value == "" || email.value == "" || phoneNumber.value == "") {
      error.value = true;
      errorMessage.value = "Please make sure all fields are filled out!";
      return;
    }
    // if guest already exists under  this email, add the booking to their account
    let returning = await getGeneralUserExists(email.value);
    let guestId = "";
    if (!returning) {
      guestId = await createGeneralUser(firstName.value, lastName.value, email.value, phoneNumber.value);
    } else {
      guestId = email.value;
    }
  } else {
    getCustomerInfo();
    let guestId = email.value;
  }
  if (error.value) return;
  let roomId = await getRoomOfType(roomType.value);
  if (error.value) return;
  let cost = await getCost(roomType.value, checkInDate.value, checkOutDate.value);
  if (error.value) return;
  let paymentId = await createPayment(cost);
  if (error.value) return;
  console.log(paymentId);
  console.log(roomId);
  let bookingId = await createBooking(guestId, roomId, paymentId);
  if (error.value) return;

  await addRequests(bookingId);

  router.push("/booking/summary/" + bookingId);
  
}

async function addRequests(bookingId:number) {

  const numRequests = requests.value.length;

  for (let i = 0; i < numRequests; i++) {
    let request = requests.value[i];
    await createRequest(request, bookingId);
    if (error.value) return;
  }
  
}

</script>

<template>
  <div class="form-container">
    <h1 class="text-3xl font-bold mb-4">Book a Room</h1>

    <section v-if="guest" class="mb-3">
      <h2 class="text-xl font-semibold mb-4">Personal Information</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <fwb-input v-model="firstName" placeholder="First Name" label="First Name" class="mb-4" />
        <fwb-input v-model="lastName" placeholder="Last Name" label="Last Name" class="mb-4" />
        <fwb-input v-model="email" placeholder="Email Address" label="Email" type="email" class="mb-4" />
        <fwb-input v-model="phoneNumber" placeholder="Phone Number" label="Phone Number" type="number" class="mb-4" />
      </div>
    </section>

    <section class="mb-4">
      <h2 class="text-xl font-semibold mb-3">Room Information</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <fwb-select
          v-model="roomType"
          placeholder="Select a Room Type"
          label="Room Type"
          :options="[
            { value: 'Regular', name: 'Regular ($40/night)' },
            { value: 'Suite', name: 'Suite ($60/night)' },
            { value: 'Deluxe', name: 'Deluxe ($80/night)' },
          ]"
          class="mb-4"
        />
        <div class="flex flex-col gap-4">
          <fwb-input v-model="checkInDate" placeholder="Check-in Date" label="Check-in Date" type="date" class="mb-4" />
          <fwb-input v-model="checkOutDate" placeholder="Check-out Date" label="Check-out Date" type="date" class="mb-4" />
        </div>
      </div>
    </section>

    <!-- <section class="mb-6">
      <h2 class="text-xl font-semibold mb-4">Payment Information</h2>
      <fwb-input v-model="paymentCode" placeholder="Payment Code" label="Payment Code" type="number" class="mb-4" />
    </section> -->

    <section class="mb-6">
      <h2 class="text-xl font-semibold mb-2">Requests</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div v-for="(request, index) in requests" :key="index">
          <fwb-input
            v-model="requests[index]"
            placeholder="Enter Your Request"
            :label="`Request ${index + 1}`"
            type="text"
            class="mb-2"
          />
        </div>
      </div>
      <div class="flex space-x-2 mt-4">
        <fwb-button @click="addRequest" gradient="green">
          Add Request
        </fwb-button>
        <fwb-button @click="removeRequest" gradient="red">
          Remove Request
        </fwb-button>
      </div>
    </section>

    <fwb-button type="submit" @click="submitBooking" gradient="blue" class="submit-btn">
      Submit
    </fwb-button>

    <!-- Error Message -->
    <div v-if="error" class="error-message mt-4">
      <fwb-p class="text-red-600">{{ errorMessage }}</fwb-p>
    </div>
  </div>
</template>

<style scoped lang="postcss">
.form-container {
  @apply bg-gray-100 p-8 rounded-lg shadow-md w-full lg:w-1/2 xl:w-1/3 mx-auto ;
}

.submit-btn {
  @apply text-white w-full text-center;
}

</style>