<template>
    <fwb-card class="BookingSummaryCard" img-alt="Stock Photo" img-src="https://flowbite.com/docs/images/blog/image-4.jpg"
        variant="horizontal">
        <div class="p-5">
            <h3 class="text-2xl font-semibold">Booking Summary</h3>
            <h2 class="text-xl text-green-600">Booking ID: {{ $route.params.bookingId }} </h2>
            <p class="mt-2 text-gray-600">
                {{ bookingId }}
            </p>
        </div>
    </fwb-card>
</template>

<script lang="ts">
import { FwbCard } from 'flowbite-vue'
import { useRoute } from 'vue-router';
export default {
    name: 'BookingSummaryView',
    props: {
        bookingId: Number,
        roomId: Number,
        paymentId: Number,
        userEmail: String
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
                    console.log(response);
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });

            console.log(bookingInfo);
            return {
                bookingInfo
            }
        }
    }
}

</script>