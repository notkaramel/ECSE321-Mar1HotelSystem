<template>
    <main>
        <div>
            <table>
                <tr>
                    <th>Employee Shift</th> <th>First Name</th> <th>Last Name</th> <th>Employee ID</th>
                </tr>
                <tr v-for = "e in employeesList">
                    <td>{{e.shift}}</td> <td>{{e.firstName}}</td> <td>{{e.lastName}}</td> <td>{{e.email}}</td>
                </tr>

            </table>
            <button id="test"> Add Employee </button>
        </div>
    </main>
</template>

<script lang="ts">
import axios from 'axios';
const backendUrl = import.meta.env.VITE_BACKEND;

async function getEmployees() {
    let employees: any[] = await axios.get(backendUrl + "/employees")
    .then(response => response.data)
    .catch(error => console.log(error));
    return employees;
}

let employees: any[] = await getEmployees();
console.log(employees);
export default {
    components: {
        employees
    },
    data() {
        return {
            employeesList: employees
        }
    }
}

async function getEmployee(email: string) {
    let employee: any = await axios.get(backendUrl + "/employees/" + email)
    .then(response => response.data)
    .catch(error => console.log(error));
    return employee;
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