<template>
    <fwb-table striped>
        <fwb-table-head>
            <fwb-table-head-cell>Booking ID</fwb-table-head-cell>
            <fwb-table-head-cell>Payment ID</fwb-table-head-cell>
            <fwb-table-head-cell>Payment amount</fwb-table-head-cell>
            <fwb-table-head-cell>Room ID</fwb-table-head-cell>
            <fwb-table-head-cell>Room Type</fwb-table-head-cell>
            <fwb-table-head-cell>Guest Email</fwb-table-head-cell>
            <fwb-table-head-cell>Guest Last Name</fwb-table-head-cell>
            <span class="sr-only">Edit</span>
        </fwb-table-head>
        <fwb-table-body>
            <fwb-table-row v-for="booking in allBookings" :key="booking.bookingId">
                <fwb-table-cell>{{ booking.bookingId }}</fwb-table-cell>
                <fwb-table-cell>{{ booking.payment.paymentId }}</fwb-table-cell>
                <fwb-table-cell>{{ booking.payment.amount }}</fwb-table-cell>
                <fwb-table-cell>{{ booking.room.roomId }}</fwb-table-cell>
                <fwb-table-cell>{{ booking.room.roomType }}</fwb-table-cell>
                <fwb-table-cell>{{ booking.generalUser.email }}</fwb-table-cell>
                <fwb-table-cell>{{ booking.generalUser.lastName }}</fwb-table-cell>
                <FwbTableCell>
                    <FwbButton>Edit</FwbButton>
                </FwbTableCell>
            </fwb-table-row>
        </fwb-table-body>
    </fwb-table>
</template>

<script lang="ts">
import {
    FwbTable,
    FwbTableBody,
    FwbTableCell,
    FwbTableHead,
    FwbTableHeadCell,
    FwbTableRow,
    FwbButton,
} from 'flowbite-vue'
import axios from 'axios';

const BACKEND_URL = import.meta.env.VITE_BACKEND;

export default {
    name: 'ManageBooking',
    components: {
        FwbTable,
        FwbTableBody,
        FwbTableCell,
        FwbTableHead,
        FwbTableHeadCell,
        FwbTableRow,
        FwbButton,
    },
    data() {
        // define DTO
        const bookingDTO = {
            bookingId: Number,
            payment: {
                paymentId: Number,
                amount: Number,
            },
            room: {
                roomId: Number,
                roomType: String,
            },
            generalUser: {
                email: String,
                lastName: String,
            },
        }
        const allBookingsDTO = [bookingDTO];
        return {
            allBookings: allBookingsDTO,
        }
    },
    methods: {
        async getBookings() {
            await axios.get(BACKEND_URL + '/booking/all')
                .then(response => {
                    this.allBookings = response.data;
                    console.log("hehehehe")
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error);
                });
        },
    },
    mounted() {
        this.getBookings();
    }
}
</script>

<style scoped lang="postcss"></style>