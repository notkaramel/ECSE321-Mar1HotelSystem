<template>
    <fwb-alert class="border-t-4 rounded-none" icon type="success" closable>
        Your booking was successful! Please view the booking summary below.
    </fwb-alert>
    <div class="flex flex-cbookingInfool justify-center">
        <BookingSummary :bookingId=bookingId />
    </div>
</template>

<script lang="ts">
import BookingSummary from '@/components/BookingSummary.vue';
import { FwbAlert } from 'flowbite-vue';

export default {
    name: 'BookingSummaryView',
    components: {
        BookingSummary,
        FwbAlert
    },
    setup() {
        return {
            BookingSummary
        }
    },
    data() {
        return {
            bookingId: Number(this.$route.params.bookingId),
        }
    }
}
</script>

