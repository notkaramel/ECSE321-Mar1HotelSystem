<template>
    <fwb-card class="BookingSummaryCard" img-alt="Stock Photo" img-src="https://flowbite.com/docs/images/blog/image-4.jpg"
        variant="horizontal">
        <div class="p-5">
            <p class="text-2xl font-semibold">
                Booking Summary
            </p>
            <p class="text-xl text-green-600">
                Booking ID: {{ bookingId }} 
            </p>
            <p class="text-gray-600">
                User email: {{ user.email || " " }}
            </p>
            <div id="roomInfo">
                <p class="text-gray-600">
                    <!-- Room #{{ room || " " }} -->
                </p>
                <p class="text-gray-600">
                    Room Type: {{ room.roomType || " " }}
                </p>
            </div>
            <p class="text-gray-600">
                Payment #{{ payment.paymentId || " " }}
                Amount: {{ payment.amount || " " }}
            </p>
        </div>
    </fwb-card>
</template>

<style scoped lang="postcss">
.BookingSummaryCard {
    @apply m-12;
}
</style>

<script lang="ts">
import { FwbCard } from 'flowbite-vue'
import { useRoute } from 'vue-router';
// import { ref } from 'vue';
export default {
    name: 'BookingSummaryView',
    props: {
        bookingId: Number,
    },
    components: {
        FwbCard
    },
    methods: {
        async setup() {
            const backend = import.meta.env.VITE_BACKEND;
            const route = useRoute();
            const bookingId = route.params.bookingId;
            console.log(bookingId);

            console.log(backend + '/booking/' + bookingId);

            let bookingInfo = await fetch(backend + '/booking/' + bookingId)
                .then(response => {
                    // console.log(response);
                    return response.json();
                })
                .then(json => {
                    // console.log(json);
                    // console.log(json["bookingId"]);
                    const user = json["generalUser"];
                    const room = json["room"];
                    const payment = json["payment"];
                    console.log(user);
                    console.log(room);
                    console.log(payment);
                    this.user = user;
                    this.room = room;
                    this.payment = payment;
                    return json;

                })
                .catch(error => {
                    console.log(error);
                });

            console.log(bookingInfo);
            return {
                bookingInfo
            }
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
            "roomNumber": " ",
            "roomType": " ",
            "roomPricePerNight": " ",
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
        this.setup();
    }
}

</script>