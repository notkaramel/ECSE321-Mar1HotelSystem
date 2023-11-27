<script setup lang="ts">

import '../style.css'

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
const paymentCode = ref()

const guest = ref(true)

const error = ref(false)
const errorMessage = ref('')

const requests = ref<string[]>([])


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

async function makeError() {
  error.value = true;
  errorMessage.value = "womp womp";
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

  if (guest.value) {
    let guestId = await createGeneralUser(firstName.value, lastName.value, email.value, phoneNumber.value);
  } else {
    getCustomerInfo();
  }
  if (error.value) return;
  let roomId = await getRoomOfType(roomType.value);
  if (error.value) return;
  let cost = await getCost(roomType.value, checkInDate.value, checkOutDate.value);
  if (error.value) return;
  let paymentId = await createPayment(cost);
  if (error.value) return;
  let bookingId = await createBooking(email.value, roomId, paymentId);
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
    <h1 class="main-heading">Book a Room</h1>
    <div v-if="guest" class="personal-info">
      <h2 class="sub-heading">Personal Information </h2>
      <fwb-input
        v-model="firstName"
        placeholder="Enter your first name"
        label="First name"
      />

      <fwb-input
        v-model="lastName"
        placeholder="Enter your last name"
        label="Last name"
        type="text"
      />

      <fwb-input
        v-model="email"
        placeholder="Enter your email address"
        label="Email"
        type="email"
      />

      <fwb-input
        v-model="phoneNumber"
        placeholder="Enter your phone number"
        label="Phone number"
        type="number"
      />
      
    </div>

    <div class="room-info">
      <h2 class="sub-heading">Room Information </h2>
      <fwb-select
        v-model="roomType"
        placeholder="Select a room type"
        label="Room type"
        :options="[
          { value: 'Regular', name: 'Regular ($40/night)' },
          { value: 'Suite', name: 'Suite ($60/night)' },
          { value: 'Deluxe', name: 'Deluxe ($80/night)' },
        ]"
      />

      <fwb-input
        v-model="checkInDate"
        placeholder="Enter your check-in date"
        label="Check-in date"
        type="date"
      />

      <fwb-input
        v-model="checkOutDate"
        placeholder="Enter your check-out date"
        label="Check-out date"
        type="date"
      />
    </div>

    <div class="payment-info">
      <h2 class="sub-heading">Payment Information </h2>
      <fwb-input
        v-model="paymentCode"
        placeholder="Enter your payment code"
        label="Payment Code"
        type="number"
      />
    </div>
    <div class="Requests">
      <h2 class="sub-heading">Requests </h2>
      <div class = "request-fields" v-for="(request, index) in requests">
        <fwb-input
          v-model="requests[index]"
          placeholder="Enter your request"
          :label="`Request ${index + 1}`"
          type="text"
        />
      </div>
      <fwb-button @click="addRequest">Add</fwb-button>
      <fwb-button @click="removeRequest">Remove</fwb-button>
    </div>

    <fwb-button type="submit" @click="submitBooking">Submit</fwb-button>
  </div>
  <div v-if="error" class="error-message">
    <fwb-p class="text-red-600">
      {{errorMessage}}
    </fwb-p>
  </div>
</template>

<style scoped>
.main-heading {
  font-size: 32px;
  font-weight: bold;
}

.sub-heading {
  font-size: 20px;
}

/* Set display to block for labels and inputs to make them appear on new lines */
label,
input {
  display: block;
  margin-bottom: 10px; /* Add margin between each label-input pair */
}

</style>