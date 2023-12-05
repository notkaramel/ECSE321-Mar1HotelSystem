<template>
    <main>
        <div>
            <h1 class="centeredHeader"> Employee Information </h1>
            <!-- Table for the employee -->
            <table>
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Hours Worked</th>
                </tr>
                </thead>
                <tr>
                    <td>{{ employeesInfo.firstName }}</td>
                    <td>{{ employeesInfo.lastName }}</td>
                    <td>{{ employeesInfo.email }}</td>
                    <td>{{ employeesInfo.phoneNumber }}</td>
                    <td>{{ employeesInfo.hoursWorked }}</td>
                </tr>
            </table>
            <br>

            
            <!-- Section that opens when an employee logs in -->
            <div v-if = canDisplay>
                <h1 class="centeredHeader"> List of all shifts </h1>
                <!-- Table for all shifts -->
                <table :key = refreshShift>
                    <thead>
                    <tr>
                        <th>Shift ID</th>
                        <th>Shift date</th>
                        <th>Shift start time</th>
                        <th>Shift end time</th>
                    </tr>
                    </thead>
                    <tr v-for="s in shiftsList" v-bind:key="s.shiftId">
                        <td>{{ s.shiftId }}</td>
                        <td>{{ s.date }}</td>
                        <td>{{ s.startTime }}</td>
                        <td>{{ s.endTime }}</td>
                    </tr>
                </table>
                <b> <h1 class="centeredHeader"> List of all requests </h1> </b>

                <!-- Table for all requests -->
                <table :key = refreshRequests>
                    <thead>
                    <tr>
                        <th>Request ID</th> <th>Booking ID</th> <th>Description</th>
                    </tr>
                    </thead>
                    <tr v-for="request in requestsList.filter((p) => p.isFulfilled === false)"
                        v-bind:key="request.requestId" class = "tableBody">
                        <td>{{ request.requestId }}</td>
                        <td>{{ request.booking.bookingId }}</td>
                        <td>{{ request.description }}</td>
                    </tr>
                </table>
                <b> <h1 class="centeredHeader"> List of requests assigned to {{ currentEmployeeEmail }} </h1> </b>

                <!-- Table for all requests assigned to employee -->
                <table :key = refreshAssignment>
                    <thead>
                    <tr>
                        <th>Assignment ID </th> <th>Request ID</th> <th>Booking</th>
                    </tr>
                    </thead>
                    <tr v-for="employeeRequest in employeeRequestList.filter((a) => a.request.isFulfilled === false)" v-bind:key="employeeRequest.assignmentId"
                        class = "tableBody">
                        <td>{{ employeeRequest.assignmentId }}</td> 
                        <td>{{employeeRequest.request.requestId}}</td> 
                        <td>{{employeeRequest.request.booking.bookingId}}</td> 
                    </tr>
                </table>

                <!-- Selecting request -->
                <div class="centered">
                    <h1 class="centeredHeader"> Selecting request </h1>
                    <input type="text" placeholder="request id" v-model="requestId">
                    <br>
                    <button @click="selectRequest(requestId)">Select request</button>
                    <button @click="fulfillRequest(parseInt(requestId))">Fulfill request</button>
                    <br>
                    <span class="errorDisplay">{{ errorMessageDisplaySelect }}</span>
                </div>
                <b> <h1 class="centeredHeader"> All bookings </h1> </b>

                <!-- Table for all bookings -->
                <table :key = refreshBooking>
                    <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Room ID</th>
                        <th>Customer email</th>
                    </tr>
                    </thead>
                    <tr v-for="booking in bookingsList" v-bind:key="booking.bookingId"
                    class = "tableBody">
                        <td>{{ booking.bookingId }}</td>
                        <td>{{ booking.room.roomId }}</td>
                        <td>{{ booking.generalUser.email }}</td>
                    </tr>
                </table>

                <!-- Creating request -->
                <div class="centered">
                    <h1 class="centeredHeader"> Create a request </h1>
                    <input type="text" placeholder="booking id" v-model="bookingId">
                    <br>
                    <textarea placeholder="description" v-model="descriptionRequest"></textarea>
                    <br>
                    <button @click="createRequestMain(bookingId, descriptionRequest)">Create request</button>
                    <br>
                    <span class="errorDisplay">{{ errorMessageDisplayRequest }}</span>
                </div>
            </div>

        </div>
    </main>
</template>

<script setup lang="ts">

import { ref } from 'vue';
// let refreshShift = ref(0);
// let refreshRequests = ref(0);
// let refreshAssignment = ref(0);
// let refreshBooking = ref(0);

// const forceRender = () => {
//     refreshShift.value += 1;
//     refreshRequests.value += 1;
//     refreshAssignment.value += 1;
//     refreshBooking.value += 1;
// }

async function createSetup() {
    let user: any = await axios.post(backendUrl + "/generalUsers/create", {
        email: "u@mail.com",
        firstName: "an",
        lastName: "user", 
        phoneNumber: "12345678"})
        .then(response => response.data).catch(error => console.log(error));
    let payments: any[] = await axios.get(backendUrl + "/payment/all").then(response => response.data).catch(error => console.log(error));
    let room: any = await axios.post(backendUrl + "/room/create", {
        roomType: "Regular",
        bedType: "Doubles",
        isAvailable: true,
        pricePerNight: 10,
        maxCapacity: 1})
        .then(response => response.data).catch(error => console.log(error));
    let booking: any = await axios.post(backendUrl + "/booking/create", {
        generalUserEmail: "u@mail.com",
        roomId: 402, 
        paymentId: 202}).then(response => response.data).catch(error => console.log(error));
}

//createSetup();



</script>

<script lang="ts">
import axios from 'axios';

const backendUrl = import.meta.env.VITE_BACKEND;

// HELPER METHODS

// Get all employees
async function getEmployees() {
    let employees: any[] = await axios.get(backendUrl + "/employees")
        .then(response => response.data)
        .catch(error => console.log(error));
    console.log(employees);
    return employees;
}

// Get employee by email
async function getEmployee(email: string) {
    let employee: any = await axios.get(backendUrl + "/employee/" + email)
        .then(response => response.data)
    return employee;
}

// Get shifts by employee email
async function getShifts(email: string) {
    let shifts: any[] = await axios.get(backendUrl + "/employee/" + email + "/shifts")
        .then(response => response.data)
        .catch(error => console.log(error));
    return shifts;
}

// Get all requests
async function getAllRequests() {
    let requests: any[] = await axios.get(backendUrl + "/requests")
        .then(response => response.data)
        .catch(error => console.log(error));
    return requests;
}

// Get all requests assigned to employee
async function getEmployeeAssignments(email: string) {
    let employeeRequests: any[] = [];
    employeeRequests = await axios.get(backendUrl + "/assignments/all")
    .then((response) => {
        return response.data}).then((response => response.filter((assignment: any) => assignment.employee.email === email)))
    .catch(error => console.log(error));
    // for (let assignment of assignments) {
    //     if (assignment.employee.email === email) {
    //         employeeRequests.push(assignment);
    //     }
    // }
    return employeeRequests;
}

// Create request
async function createRequest(bookingId: string, description: string) {
    let request: any = await axios.post(backendUrl + "/request/create", {
        bookingId: bookingId,
        description: description,
        isFulfilled: false
    })
        .then(response => response.data)
    return request;
}

// Get all bookings
async function getBookings() {
    let bookings: any[] = await axios.get(backendUrl + "/booking/all")
        .then(response => response.data)
        .catch(error => console.log(error));
    return bookings;
}

// Create assignment
async function createAssignment(requestId: string, employeeEmail: string) {
    let assignment: any = await axios.post(backendUrl + "/assignment/create", {
        employeeId: employeeEmail,
        requestId: requestId
    })
        .then(response => response.data)
    return assignment;
}

// Fulfill request
async function fulfillChange(requestId: number) {
    let request: any = await axios.get(backendUrl + "/requests/" + requestId).then(response => response.data);
    let requestUpdate: any = await axios.put(backendUrl + "/requests/update/" + request.requestId, {
        description: request.description,
        bookingId: request.booking.bookingId,
        isFulfilled: true
    }).then(response => response.data);
    
    console.log(requestUpdate);
    return requestUpdate;
}

// MAIN EXPORT

let requests: any[] = await getAllRequests();
let bookings: any[] = await getBookings();
let shifts: any[] = [];
let assignments: any[] = [];
let email: any = localStorage.getItem('employeeEmail');
let employee: any = await getEmployee(email);
export default {

    mounted() {
        this.autoLoginIfCredentialsExist();
    },

    components: {
    },
    data() {
        return {
            employeesInfo: employee,
            text: "",
            shiftsList: shifts,
            password: "",
            errorMessageDisplay: "",
            canDisplay: false,
            employeeRequestList: assignments,
            bookingId: "",
            descriptionRequest: "",
            errorMessageDisplayRequest: "",
            bookingsList: bookings,
            currentEmployeeEmail: email,
            requestId: "",
            errorMessageDisplaySelect: "",
            requestsList: requests,
            refreshShift: 0,
            refreshRequests: 0,
            refreshAssignment: 0,
            refreshBooking: 0
        }
    },

    methods: {

        autoLoginIfCredentialsExist() {
            const employeeEmail = localStorage.getItem('employeeEmail');
            const employeePassword = localStorage.getItem('employeePassword');
            // Assuming you have a method to verify the employee
            if (employeeEmail && employeePassword) {
                this.login(employeeEmail, employeePassword);
            }
            },


        // Login method
        login: async function(email: string, password: string) {
            await this.checkPassword(email, password);
            await this.getShiftsList(this.currentEmployeeEmail);
            await this.getAssignments(this.currentEmployeeEmail);
        },

        // Check if password is correct
        checkPassword: async function(email: string, password: string) {
            try {
                let employee: any = await getEmployee(email);
                if (employee.password === password) {
                    this.canDisplay = true;
                    this.errorMessageDisplay = "";
                } else {
                    this.errorMessageDisplay = "Password is incorrect";
                    this.canDisplay = false;
                }
                // } catch (error: AxiosError) {
            } catch (error: any) {
                if (error.response?.status === 404) {
                    this.errorMessageDisplay = "Employee not found";
                    this.canDisplay = false;
                }
                else {
                    this.errorMessageDisplay = error.message;
                    this.canDisplay = false;
                }
            }

        },
        // Get shifts by employee email
        getShiftsList: async function(email: string) {
            let shifts: any[] = await getShifts(email);
            this.shiftsList = shifts;
        },
        // Get all requests assigned to employee
        getAssignments: async function(email: string) {
            let employeeRequests: any[] = await getEmployeeAssignments(email);
            this.employeeRequestList = employeeRequests;
        },
        // Create request
        createRequestMain: async function(bookingId: string, description: string) {
            try {
                let request: any = await createRequest(bookingId, description);
                alert("Request created");
                this.errorMessageDisplayRequest = "";
                this.forceRender();
            } catch (error: any) {
                if (error.response?.status === 404) {
                    this.errorMessageDisplayRequest = "Booking not found";
                }
                else {
                    this.errorMessageDisplayRequest = error.message;
                }
            }
        },
        // Select request
        selectRequest: async function(requestId: string) {
            try {
                let assignment: any = await createAssignment(requestId, this.currentEmployeeEmail);
                alert("Request selected");
                this.errorMessageDisplaySelect = "";
                this.forceRender();
                // } catch (error: AxiosError) {
            } catch (error: any) {

                if (error.response?.status === 404) {
                    this.errorMessageDisplaySelect = "Request not found";
                }
                else {
                    this.errorMessageDisplaySelect = error.message;
                }
            }
        },
        // Fulfill request
        fulfillRequest: async function(requestId: number) {
            try {
                let request: any = await fulfillChange(requestId);
                alert("Request fulfilled");
                this.errorMessageDisplaySelect = "";
                this.forceRender();
                // } catch (error:AxiosError) {
            } catch (error: any) {

                if (error.response?.status === 404) {
                    this.errorMessageDisplaySelect = "Request not found";
                }
                else {
                    this.errorMessageDisplaySelect = error.message;
                }
            }
        }, 
        forceRender: async function() {
            this.requestsList = await getAllRequests();
            this.employeeRequestList = await getEmployeeAssignments(this.currentEmployeeEmail);
            this.shiftsList = await getShifts(this.currentEmployeeEmail);
            this.bookingsList = await getBookings();
            this.refreshShift += 1;
            this.refreshRequests += 1;
            this.refreshAssignment += 1;
            this.refreshBooking += 1;
        }
    }
}
</script>

<style scoped lang="postcss">

thead {
    @apply bg-gray-50 dark:bg-gray-700 dark:text-gray-400;
}

table {
    @apply table-auto ;
    @apply w-full text-base text-left rtl:text-right text-gray-500 dark:text-gray-400 -m-1.5;
    font-family: arial, sans-serif;
    border-width: thin;
    width: 80%;
    text-align: center;
    margin: auto;
}

.tableBody {
    @apply bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600;
}

button {
    @apply hover:bg-green-600 text-white font-bold py-2 px-4 rounded-md;
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 10px;
    border-radius: 12px;
}

.errorDisplay {
    color: red;
}

input {
    @apply rounded-md;
    margin: 10px;
    border: 1px solid #ccc;
}

textarea {
    @apply w-1/2 h-24 p-2 border rounded-md;
    margin: 10px;
    border: 1px solid #ccc;
}

.centered {
    text-align: center;
    margin: auto;
}

.centeredHeader {
    @apply text-2xl font-bold text-gray-700 dark:text-gray-200;
    font: sans-serif;
    text-align: center;
    margin: auto;
    margin-top: 50px;
}
</style>