<script lang="ts">
import UserTable from '@/components/UserTable.vue';
// import DeleteUser from '@/components/DeleteUser.vue';

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
// let DeletedEmployee = getDeletedEmployee();
console.log(EmployeeList);
export default {
    components: {
       UserTable,
    //    DeleteUser
        //EmployeeList
    },
    data() {
        return {
            employeeList: EmployeeList,
            // emailDelete: ''
            // deletedEmployee
        }
    }
}


// async function getDeletedEmployee() {
//     message
// }

async function deleteEmployee(emailDelete: string) {
    let deletedEmployee = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedEmployee);
    return deletedEmployee;
}


async function createEmployee(firstName: string, lastName: string, email: string, phoneNumber: number, password: string, hoursWorked: number) {
    let createdEmployee = await axios.post(backendUrl + "/employee/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password, "hoursWorked": hoursWorked})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdEmployee);
    return createdEmployee;
}

// async function deleteEmployeeSpecific(){
//     deleteEmployee(message)
// }

</script>

<script setup lang="ts">
  import { ref } from 'vue'
  import { FwbTextarea } from 'flowbite-vue'
  import { FwbButton } from 'flowbite-vue'
  const message = ref('')

  const employeeFirstName = ref('')
  const employeeLastName = ref('')
  const employeeEmail = ref('')
  const employeePhoneNumber = ref('')
  const employeePassword = ref('')
  const employeeHoursWorked = ref('')
  
//   function delete(){

//   }

//   export default {
//     props: { 
//     message: String
//   }
// }
  </script>
<template>
    
    <main class="flex flex-row items-center">
        <div v-for="employee in employeeList" :key="employee.email">
            <UserTable :email="employee.email" :firstName="employee.firstName" :lastName="employee.lastName" :phoneNumber="employee.phoneNumber"/>
        </div>
        <div>
        <fwb-textarea
            v-model="message"
            :rows="2"
            label="Delete Employee"
            placeholder="Input employee email of employee you want to delete..."
            />
            <fwb-button @click="deleteEmployee(message)" color="red">Delete</fwb-button>
        </div>

        <div>
        <fwb-textarea
            v-model="employeeFirstName"
            :rows="2"
            label="Enter Employee First Name"
            placeholder="Input employee first name..."
            />
            <fwb-textarea
            v-model="employeeLastName"
            :rows="2"
            label="Enter Employee Last Name"
            placeholder="Input employee last name..."
            />
            <fwb-textarea
            v-model="employeeEmail"
            :rows="2"
            label="Enter Employee Email"
            placeholder="Input employee email..."
            />

            <fwb-textarea
            v-model="employeePhoneNumber"
            :rows="2"
            label="Enter Employee Phone Number"
            placeholder="Input employee phone Number..."
            />

            <fwb-textarea
            v-model="employeePassword"
            :rows="2"
            label="Enter Employee Password"
            placeholder="Input employee password..."
            />

            <fwb-textarea
            v-model="employeeHoursWorked"
            :rows="2"
            label="Enter Employee hours worked"
            placeholder="Input employee hours worked..."
            />
            
            <fwb-button @click="createEmployee(employeeFirstName, employeeLastName, employeeEmail, parseInt(employeePhoneNumber),
            employeePassword, parseInt(employeeHoursWorked))" color="green">Create Employee</fwb-button>
        </div>
     </main>
</template>
