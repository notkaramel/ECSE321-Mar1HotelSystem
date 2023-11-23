<script lang="ts">
import UserTable from '@/components/UserTable.vue';

import axios from 'axios'
const backendUrl = import.meta.env.VITE_BACKEND;

console.log(backendUrl)

async function getEmployees() {
    let listOfEmployees: any[] = await axios.get(backendUrl + "/employees")
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    return listOfEmployees;
}

let EmployeeList:any[] = await getEmployees();
console.log(EmployeeList[0]);
export default {
    components: {
        UserTable
        //EmployeeList
    },
    data() {
        return {
            employeeList: EmployeeList
        }
    }
}
</script>

<template>
    <main class="flex flex-row items-center">
        <div v-for="employee in employeeList" :key="employee.email">
            <UserTable :email="employee.email" :firstName="employee.firstName" :lastName="employee.lastName" :phoneNumber="employee.phoneNumber"/>
        </div>
    </main>
</template>
 <!-- :firstName="employee.firstName" :lastName="employee.lastName" :phoneNumber="employee.phoneNumber" -->
<!-- <style scoped></style> -->