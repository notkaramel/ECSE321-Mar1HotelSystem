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
            <button @click="getShiftsList(text)">Get Employee</button>
            <button @click="checkPassword(text, password)">Check password</button>
            <table>
                <tr>
                    <th>Shift ID</th> <th>Shift date</th> <th>Shift start time</th> <th>Shift end time</th>
                </tr>
                <tr v-for = "s in shiftsList">
                    <td>{{s.shiftId}}</td> <td>{{s.date}}</td> <td>{{s.startTime}}</td> <td>{{s.endTime}}</td>
                </tr>
            </table>
        </div>
    </main>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const count = ref(0)
</script>

<script lang="ts">
import axios from 'axios';

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
    .catch(error => console.log(error));
    console.log(employee);
    return employee;
}

async function getShifts(email: string) {
    let shifts: any[] = await axios.get(backendUrl + "/employee/" + email + "/shifts")
    .then(response => response.data)
    .catch(error => console.log(error));
    console.log(shifts);
    return shifts;
}

let employees: any[] = await getEmployees();
let shifts: any[] = [];
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
            password: ""
        }
    },

    methods: {
        checkPassword: async function(email: string, password: string) {
            try {
                let employee: any = await getEmployee(email);
            if (employee.password === password) {
                console.log("Password is correct");
            } else {
                console.log("Password is incorrect");
            }
            } catch (error: any) {
                console.log(error.axios.message);
            }
            
        },
        getShiftsList: async function(email: string) {
            let shifts: any[] = await getShifts(email);
            this.shiftsList = shifts;
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
</style>