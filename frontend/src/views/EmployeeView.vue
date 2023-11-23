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
            <input placeholder="email" id="employeeEmail">
            <button @click="count++">Get Employee {{ count }}</button>
            <table>
                <tr>
                    <th>First Name</th> <th>Last Name</th> <th>Shift Time</th>
                </tr>
                <tr v-for = "e in employeesList">
                    <td>{{e.firstName}}</td> <td>{{e.lastName}}</td> <td>{{e.email}}</td>
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
    let employee: any = await axios.get(backendUrl + "/employees/" + email)
    .then(response => response.data)
    .catch(error => console.log(error));
    return employee;
}

let employees: any[] = await getEmployees();
console.log(employees);
export default {
    components: {
    employees
},
    data() {
        return {
            employeesList: employees,
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