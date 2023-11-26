<template>
    <div class="card">
        <p class="text-2xl font-semibold">
            Booking Summary
        </p>
        <p class="text-xl text-green-600">
            Booking ID: {{ bookingId }}
        </p>
        <div class="info">
            <h1>
                User Info:
            </h1>
            <p>
                User Name: {{ user.firstName || "?" }} {{ user.lastName || "?" }} <br />
                User email: {{ user.email || "?" }} <br />
                User phone number: {{ user.phoneNumber || " " }}
            </p>
        </div>
        <div class="info">
            <h1>
                Room booked:
            </h1>
            <p>
                Room ID: {{ room.roomId || "?" }} <br />
                Room Price Per Night: {{ room.pricePerNight || "?" }}
            </p>
            <p>
                Room Type: {{ room.roomType || "?" }}
            </p>
        </div>
        <div class="info">
            <h1>Payment Info</h1>
            <p>
                Amount: CA${{ payment.amount || "?" }} <br />
                Payment ID: {{ payment.paymentId || "?" }}
            </p>
        </div>
        <div id="cancel-booking">
            <p> Booked by mistake? Cancel now with full refund! </p>
            <button class="w-full bg-rose-600 text-white font-semibold py-3" @click="cancelBooking">
                Cancel
            </button>
        </div>
    </div>
</template>

<style scoped lang="postcss">
.card {
    @apply block w-1/2 bg-white rounded-lg border border-gray-200 shadow-md hover:bg-gray-100;
    @apply dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700;
}

.BookingSummaryCard {
    @apply m-12;
}

.info {
    @apply mt-4;
}

.info h1 {
    @apply text-xl font-semibold;
}

.info p {
    @apply text-lg text-gray-800;
}
</style>

<script lang="ts">
import { useRoute } from 'vue-router';
import axios from 'axios';

export default {
    name: 'BookingSummaryView',
    props: {
        bookingId: Number,
    },
    methods: {
        async loadSummary() {
            const backend = import.meta.env.VITE_BACKEND;
            const route = useRoute();
            const bookingId = route.params.bookingId;

            let bookingInfo = await fetch(backend + '/booking/' + bookingId)
                .then(response => {
                    return response.json();
                })
                .then(json => {
                    const user = json["generalUser"];
                    const room = json["room"];
                    const payment = json["payment"];
                    this.user = user;
                    this.room = room;
                    this.payment = payment;
                    return json;
                })
                .catch(error => {
                    console.log(error);
                });
            return {
                bookingInfo
            }
        },
        async cancelBooking() {
            const backend = import.meta.env.VITE_BACKEND;
            const bookingId = this.bookingId;
            await axios.delete(backend + '/booking/delete/' + bookingId)
                .then(response => {
                    return (response.status == 200 ? alert("Booking cancelled successfully!") : null);
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
    data() {
        // Declear data object schemas (DTOs)
        const UserDTO = {
            "email": "",
            "firstName": "",
            "lastName": "",
            "phoneNumber": "",
        };

        const RoomDTO = {
            "roomId": " ",
            "roomType": " ",
            "pricePerNight": " ",
            "roomIsAvaialble": " ",
        };

        const PaymentDTO = {
            "paymentId": " ",
            "amount": " "
        };
        return {
            user: UserDTO,
            room: RoomDTO,
            payment: PaymentDTO,
        }
    },
    beforeMount() {
        this.loadSummary();
    }
}
</script>