<template>
    <div class="flex flex-cbookingInfool items-center">
        <BookingSummary :bookingId="235" :roomId="523" :paymentId="35"/>
    </div>
</template>

<style scoped lang="postcss">
.BookingSummaryCard {
    @apply m-12;
}
</style>

<script lang="ts">
import BookingSummary from '@/components/BookingSummary.vue';

export default {
    title() {
        return 'Booking Summary';
    },
    name: 'BookingSummaryView',
    components: {
        BookingSummary
    },
    setup() {
        return {
            BookingSummary
        }
    }
}
</script>

