<template>
    <main>
        <div>
            <!-- Table for all employees -->
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
                <tr v-for="e in employeesList" v-bind:key="e.email">
                    <td>{{ e.firstName }}</td>
                    <td>{{ e.lastName }}</td>
                    <td>{{ e.email }}</td>
                </tr>
            </table>

            <!-- Login form -->
            <div class="centered">
                <input type="text" placeholder="email" v-model="text">
                <input type="password" placeholder="password" v-model="password">
                <br>
                <button @click="login(text, password)">Login</button>
                <br>
                <span class="errorDisplay">{{ errorMessageDisplay }}</span>
            </div>
            
            <!-- Section that opens when an employee logs in -->
            <div v-if = canDisplay>

                <!-- Table for all shifts -->
                <table>
                    <tr>
                        <th>Shift ID</th>
                        <th>Shift date</th>
                        <th>Shift start time</th>
                        <th>Shift end time</th>
                    </tr>
                    <tr v-for="s in shiftsList" v-bind:key="s.shiftId">
                        <td>{{ s.shiftId }}</td>
                        <td>{{ s.date }}</td>
                        <td>{{ s.startTime }}</td>
                        <td>{{ s.endTime }}</td>
                    </tr>
                </table>
                <b> <h1 class="centeredHeader"> List of all requests </h1> </b>

                <!-- Table for all requests -->
                <table>
                    <tr>
                        <th>Request ID</th> <th>Booking ID</th> <th>Description</th>
                    </tr>
                    <tr v-for="request in requestsList.filter((p) => p.isFulfilled === false)"
                        v-bind:key="request.requestId">
                        <td>{{ request.requestId }}</td>
                        <td>{{ request.booking.bookingId }}</td>
                        <td>{{ request.description }}</td>
                    </tr>
                </table>
                <b> <h1 class="centeredHeader"> List of requests assigned to {{ currentEmployeeEmail }} </h1> </b>

                <!-- Table for all requests assigned to employee -->
                <table>
                    <tr>
                        <th>Assignment ID </th> <th>Request ID</th> <th>Booking</th> <th>Description</th>
                    </tr>
                    <tr v-for = "employeeRequest in employeeRequestList">
                        <td>{{ employeeRequest.assignmentId }}</td> <td>{{employeeRequest.reqest.requestId}}</td> <td>{{employeeRequest.bookingId}}</td> <td>{{employeeRequest.description}}</td>
                    </tr>
                </table>

                <!-- Selecting request -->
                <div class="centered">
                    <h1 class="centeredHeader"> Selecting request </h1>
                    <input type="text" placeholder="request id" v-model="requestId">
                    <br>
                    <button @click="selectRequest(requestId)">Select request</button>
                    <button @click="fulfillRequest(requestId)">Fulfill request</button>
                    <br>
                    <span class="errorDisplay">{{ errorMessageDisplaySelect }}</span>
                </div>
                <b> <h1 class="centeredHeader"> All bookings </h1> </b>

                <!-- Table for all bookings -->
                <table>
                    <tr>
                        <th>Booking ID</th>
                        <th>Room ID</th>
                        <th>Customer email</th>
                    </tr>
                    <tr v-for="booking in bookingsList" v-bind:key="booking.bookingId">
                        <td>{{ booking.bookingId }}</td>
                        <td>{{ booking.room.roomId }}</td>
                        <td>{{ booking.generalUser.email }}</td>
                    </tr>
                </table>

                <!-- Creating request -->
                <div class="centered">
                    <h1 class="centeredHeader"> Create a request </h1>
                    <input type="text" placeholder="booking id" v-model="bookingId">
                    <input type="text" placeholder="description" v-model="descriptionRequest">
                    <br>
                    <button @click="createRequest(bookingId, descriptionRequest)">Create request</button>
                    <br>
                    <span class="errorDisplay">{{ errorMessageDisplayRequest }}</span>
                </div>
            </div>

        </div>
    </main>
</template>

<script lang="ts">
import axios from 'axios';

const backendUrl = import.meta.env.VITE_BACKEND;

// HELPER METHODS

// Get all employees
async function getEmployees() {
    let employees: any[] = await axios.get(backendUrl + "/employees")
        .then(response => response.data)
        .catch(error => console.log(error));
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
    console.log(shifts);
    return shifts;
}

// Get all requests
async function getAllRequests() {
    let requests: any[] = await axios.get(backendUrl + "/requests")
        .then(response => response.data)
        .catch(error => console.log(error));
    console.log(requests);
    return requests;
}

// Get all requests assigned to employee
async function getEmployeeAssignments(email: string) {
    let employeeRequests: any[] = [];
    let assignments: any[] = await axios.get(backendUrl + "/assignments/all")
    .then((response) => {
        console.log(response.data instanceof Array);
        return response.data}).then((response => response.filter((assignment: any) => assignment.employee.email === email)))
    .catch(error => console.log(error));
    // for (let assignment of assignments) {
    //     if (assignment.employee.email === email) {
    //         employeeRequests.push(assignment);
    //     }
    // }
    console.log("get employee requests");
    console.log(employeeRequests);
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
    console.log(bookings);
    return bookings;
}

// Create assignment
async function createAssignment(requestId: string, employeeEmail: string) {
    let assignment: any = await axios.post(backendUrl + "/assignment/create", {
        employeeId: employeeEmail,
        requestId: requestId
    })
        .then(response => response.data)
    console.log(assignment);
    return assignment;
}

// Fulfill request
async function fulfillChange(requestId: string) {
    let request: any = await axios.get(backendUrl + "/requests/" + requestId)
    let requestUpdate = await axios.put(backendUrl + "/requests/update/" + requestId, {
        description: request.description,
        bookingId: request.bookingId,
        isFulfilled: true
    })
    
    console.log(requestUpdate);
    return requestUpdate;
}

// MAIN EXPORT
let employees: any[] = await getEmployees();
let requests: any[] = await getAllRequests();
let bookings: any[] = await getBookings();
let shifts: any[] = [];
let assignments: any[] = [];
console.log(employees);
export default {
    components: {
    },
    data() {
        return {
            employeesList: employees,
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
            currentEmployeeEmail: "",
            requestId: "",
            errorMessageDisplaySelect: "",
            requestsList: requests
        }
    },

    methods: {
        // Login method
        login: async function(email: string, password: string) {
            this.checkPassword(email, password);
            this.getShiftsList(this.text);
            this.currentEmployeeEmail = this.text;
            console.log(this.currentEmployeeEmail);
            this.getAssignments(this.currentEmployeeEmail);
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
            console.log("shifts")
            console.log(shifts);
        },
        // Get all requests assigned to employee
        getAssignments: async function(email: string) {
            let employeeRequests: any[] = await getEmployeeAssignments(email);
            this.employeeRequestList = employeeRequests;
            console.log(employeeRequests);
        },
        // Create request
        createRequest: async function(bookingId: string, description: string) {
            try {
                let request: any = await createRequest(bookingId, description);
                console.log(request);
                alert("Request created");
                this.errorMessageDisplayRequest = "";
                this.requestsList.push(request);
                this.login(this.currentEmployeeEmail, this.password);
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
                console.log(assignment);
                alert("Request selected");
                this.errorMessageDisplaySelect = "";
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
        fulfillRequest: async function(requestId: string) {
            try {
                let request: any = await fulfillChange(requestId);
                console.log(request);
                alert("Request fulfilled");
                this.errorMessageDisplaySelect = "";
                // } catch (error:AxiosError) {
            } catch (error: any) {

                if (error.response?.status === 404) {
                    this.errorMessageDisplaySelect = "Request not found";
                }
                else {
                    this.errorMessageDisplaySelect = error.message;
                }
            }
        }
    }
}
</script>

<style scoped>
table {
    font-family: arial, sans-serif;
    border-color: black;
    border-width: thin;
    width: 80%;
    text-align: center;
    margin: auto;
}

button {
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
    margin: 10px;
    border: 1px solid #ccc;
}

.centered {
    text-align: center;
    margin: auto;
}

.centeredHeader {
    font: sans-serif;
    text-align: center;
    margin: auto;
    margin-top: 50px;
}
</style>