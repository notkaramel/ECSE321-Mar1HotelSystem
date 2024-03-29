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
            <button class="bg-red-600 text-white font-semibold py-3 rounded-lg w-1/2" @click="cancelBooking">
                Cancel
            </button>
            <p> Booked by mistake? Cancel now with full refund! </p>
            <p v-if="error != ''"> {{error}} </p>
        </div>
    </div>
</template>

<style scoped lang="postcss">
.card {
    @apply block md:w-1/2 lg:w-1/3 bg-white rounded-lg border border-gray-200 shadow-md hover:bg-gray-100;
    @apply p-8 m-12;
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

#cancel-booking {
    @apply mt-8 p-1 flex flex-col items-center;
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
                    // alert("Unable to load booking summary!");
                    this.error = "Unable to load booking summary!";
                    if(error.response.status == 404)
                    {
                        alert("Unable to find booking with ID " + bookingId + "!");
                    }
                    console.log(error);
                });
            return {
                bookingInfo
            }
        },
        async cancelBooking() {
            const backend = import.meta.env.VITE_BACKEND;
            const bookingId = this.bookingId;
            // must delete all requests linked to the booking first
            await axios.get(backend + '/requests')
            .then((response) => {
                if(response.status == 200)
                {
                    const allRequests = response.data;
                    for(let req of allRequests) // iterate through all requests using for-of loop
                    {
                        // checking the bookingId of all requests
                        if(req.booking.bookingId == bookingId) 
                        {
                            axios.delete(backend + '/requests/delete/' + req.requestId)
                            .then(response => {
                                if(response.status == 200){
                                    console.log("Request " + req.requestId + " deleted successfully!");
                                }
                                else {
                                    console.error(`Response status: ${response.status}`)
                                    console.error("Unable to delete request with ID " + req.requestId + "!");
                                }
                            })
                            .catch(error => {
                                console.error(error);
                            });
                        }
                    }
                } else if (response.status == 404) {
                    alert("Unable to find booking with ID " + bookingId + "!");
                }
                else {
                    // alert("Unable to cancel booking!");
                    this.error = "Unable to cancel booking!";
                }
            });

            // Once all requests are deleted, delete the booking
            await axios.delete(backend + '/booking/delete/' + bookingId)
                .then(response => {
                    return (response.status == 200 ? alert("Booking cancelled successfully!") : null);
                })
                .catch(error => {
                    if(error.response.status == 404)
                    {
                        alert("Unable to find booking with ID " + bookingId + "!");
                    }
                    alert("Unable cancel booking!");
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
            error: ""
        }
    },
    beforeMount() {
        this.loadSummary();
    }
}
</script>