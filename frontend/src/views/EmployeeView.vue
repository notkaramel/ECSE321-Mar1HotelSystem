<template>
    <main>
        <div>
            <table>
                <tr>
                    <th>First Name</th> <th>Last Name</th> <th>Email</th>
                </tr>
                <tr v-for = "e in employeesList">
                    <td>{{e.firstName}}</td> <td>{{e.lastName}}</td> <td>{{e.email}}</td>
                </tr>
            </table>
            <input type = "text" placeholder="email" v-model="text">
            <input type = "text" placeholder="password" v-model="password">
            <button @click="login(text, password)">Login</button>
            <span class="errorDisplay">{{errorMessageDisplay}}</span>
            <div v-if = canDisplay>
                <table>
                    <tr>
                        <th>Shift ID</th> <th>Shift date</th> <th>Shift start time</th> <th>Shift end time</th>
                    </tr>
                    <tr v-for = "s in shiftsList">
                        <td>{{s.shiftId}}</td> <td>{{s.date}}</td> <td>{{s.startTime}}</td> <td>{{s.endTime}}</td>
                    </tr>
                </table>
                <h1> List of assigned requests </h1>
                <table>
                    <tr>
                        <th>Request ID</th> <th>Booking</th> <th>Description</th> <th>Assignee</th>
                    </tr>
                    <tr v-for = "employeeRequest in employeeRequestList">
                        <td>{{employeeRequest.requestId}}</td> <td>{{employeeRequest.booking.bookingId}}</td> <td>{{employeeRequest.description}}</td> <td>{{employeeRequest.employee}} </td>
                    </tr>
                </table>
            </div>
        </div>
    </main>
</template>

<script setup lang="ts">

</script>

<script lang="ts">
import axios, { AxiosError } from 'axios';

const backendUrl = import.meta.env.VITE_BACKEND;

async function getEmployees() {
    let employees: any[] = await axios.get(backendUrl + "/employees")
    .then(response => response.data)
    .catch(error => console.log(error));
    return employees;
}

async function getEmployee(email: string) {
    let employee: any = await axios.get(backendUrl + "/employee/" + email)
    .then(response => response.data)
    return employee;
}

async function getShifts(email: string) {
    let shifts: any[] = await axios.get(backendUrl + "/employee/" + email + "/shifts")
    .then(response => response.data)
    .catch(error => console.log(error));
    console.log(shifts);
    return shifts;
}

async function getEmployeeAssignments(email: string) {
    let assignments: any[] = await axios.get(backendUrl + "assignments/all")
    .then(response => response.data)
    .catch(error => console.log(error));
    let employeeRequests: any[] = [];
    for (let assignment of assignments) {
        if (assignment.employee.email === email) {
            employeeRequests.push(assignment);
        }
    }
    return employeeRequests;
}

let employees: any[] = await getEmployees();
let shifts: any[] = [];
let assignments: any[] = [];
console.log(employees);
export default {
    components: {
    employees
},
    data() {
        return {
            employeesList: employees,
            text: "",
            shiftsList: shifts,
            password: "",
            errorMessageDisplay: "",
            canDisplay: false,
            employeeRequestList: assignments
        }
    },

    methods: {
        login: async function(email: string, password: string) {
            this.checkPassword(email, password);
            this.getShiftsList(this.text);
        },

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
            } catch (error: AxiosError) {
                if (error.response?.status === 404) {
                    this.errorMessageDisplay= "Employee not found";
                    this.canDisplay = false;
                }
                else {
                    this.errorMessageDisplay = error.message;
                    this.canDisplay = false;
                }
            }
            
        },
        getShiftsList: async function(email: string) {
            let shifts: any[] = await getShifts(email);
            this.shiftsList = shifts;
            console.log("shifts")
            console.log(shifts);
        },
        
        getAssignments: async function(email: string) {
            let employeeRequests: any[] = await getEmployeeAssignments(email);
            this.employeeRequestList = employeeRequests;
            console.log(employeeRequests);
        }
    }
}
</script>

<style scoped>
    table {
        font-family: arial, sans-serif;
        border-color: black;
        border-width: thin;
        width: 100%;
        text-align: center;
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
    }

    .errorDisplay {
        color: red;
    }
</style>