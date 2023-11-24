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
async function getManagers() {
    let listOfManagers: any[] = await axios.get(backendUrl + "/managers")
    .then(response => response.data)
        .then(data => data["managerList"])
        .catch(err => {
            console.log(err)
        });
    return listOfManagers;
}

async function getCustomers() {
    let listOfCustomers: any[] = await axios.get(backendUrl + "/customers")
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    return listOfCustomers;
}

async function getGeneralUsers() {
    let listOfGeneralUsers: any[] = await axios.get(backendUrl + "/generalUsers")
        .then(response => response.data)
        .then(data => data["generalUserList"])
        .catch(err => {
            console.log(err)
        });
    return listOfGeneralUsers;
}

let EmployeeList:any[] = await getEmployees();
let ManagerList:any[] = await getManagers();
let CustomerList:any[] = await getCustomers();
let GeneralUserList:any[] = await getGeneralUsers();
// let DeletedEmployee = getDeletedEmployee();
console.log(EmployeeList);
console.log(ManagerList);
console.log(CustomerList);
console.log(GeneralUserList);
export default {
    components: {
       UserTable,
    //    DeleteUser
        //EmployeeList
    },
    data() {
        return {
            employeeList: EmployeeList,
            managerList: ManagerList,
            customerList: CustomerList,
            generalUserList: GeneralUserList,
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

async function deleteManager(emailDelete: string) {
    let deletedManager = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedManager);
    return deletedManager;
}

async function deleteCustomer(emailDelete: string) {
    let deletedCustomer = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedCustomer);
    return deletedCustomer;
}

async function deleteGeneralUser(emailDelete: string) {
    let deletedGeneralUser = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedGeneralUser);
    return deletedGeneralUser;
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

async function createManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
    let createdManager = await axios.post(backendUrl + "/managers/create/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdManager);
    return createdManager;
}

async function createCustomer(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
    let createdCustomer = await axios.post(backendUrl + "/customer/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdCustomer);
    return createdCustomer;
}

async function createGeneralUser(firstName: string, lastName: string, email: string, phoneNumber: number) {
    let createdGeneralUser = await axios.post(backendUrl + "/generalUsers/create", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdGeneralUser);
    return createdGeneralUser;
}

// async function deleteEmployeeSpecific(){
//     deleteEmployee(message)
// }



</script>

<script setup lang="ts">
  import { ref } from 'vue'
  import { FwbTextarea } from 'flowbite-vue'
  import { FwbButton } from 'flowbite-vue'
  import { FwbBadge } from 'flowbite-vue'

  const messageEmployee = ref('')
  const messageManager = ref('')
  const messageCustomer = ref('')
  const messageGeneralUser = ref('')

  const employeeFirstName = ref('')
  const employeeLastName = ref('')
  const employeeEmail = ref('')
  const employeePhoneNumber = ref('')
  const employeePassword = ref('')
  const employeeHoursWorked = ref('')

  const managerFirstName = ref('')
  const managerLastName = ref('')
  const managerEmail = ref('')
  const managerPhoneNumber = ref('')
  const managerPassword = ref('')

  const customerFirstName = ref('')
  const customerLastName = ref('')
  const customerEmail = ref('')
  const customerPhoneNumber = ref('')
  const customerPassword = ref('')
 
  
//   function delete(){

//   }

//   export default {
//     props: { 
//     message: String
//   }
// }
  </script>

<!-- <style scoped lang="postcss">
        #deleteEmployee {
            @apply content-center;
        }
</style> -->

<template>
    <main class="flex flex-row items-center-top">
        <fwb-badge type="default">View Employees</fwb-badge>
        <div v-for="employee in employeeList" :key="employee.email">
            <UserTable :email="employee.email" :firstName="employee.firstName" :lastName="employee.lastName" :phoneNumber="employee.phoneNumber" :userList="employee"/>
            <fwb-textarea
            v-model="messageEmployee"
            :rows="2"
            label="Delete Employee"
            placeholder="Input employee email of employee you want to delete..."
            />
            <fwb-button @click="deleteEmployee(messageEmployee)" color="red">Delete</fwb-button>
        </div>
        
        <div class="CreatingEmployee">
            <fwb-badge type="green">Create Employee</fwb-badge>
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
     
     <main class="flex flex-row items-center-top">
        <fwb-badge type="default">View Managers</fwb-badge>
        <div v-for="manager in managerList" :key="manager.email">
                <UserTable :email="manager.email" :firstName="manager.firstName" :lastName="manager.lastName" :phoneNumber="manager.phoneNumber"/>
                <fwb-textarea
                v-model="messageManager"
                :rows="2"
                label="Delete Manager"
                placeholder="Input manager email of manager you want to delete..."
                />
                <fwb-button @click="deleteManager(messageManager)" color="red">Delete</fwb-button>
            </div>

            <div class="CreatingManager">
                <fwb-badge type="green">Create Manager</fwb-badge>
    <fwb-textarea
        v-model="managerFirstName"
        :rows="2"
        label="Enter Manager First Name"
        placeholder="Input manager first name..."
        />
        <fwb-textarea
        v-model="managerLastName"
        :rows="2"
        label="Enter Manager Last Name"
        placeholder="Input manager last name..."
        />
        <fwb-textarea
        v-model="managerEmail"
        :rows="2"
        label="Enter Manager Email"
        placeholder="Input manager email..."
        />

        <fwb-textarea
        v-model="managerPhoneNumber"
        :rows="2"
        label="Enter Manager Phone Number"
        placeholder="Input manager phone Number..."
        />

        <fwb-textarea
        v-model="managerPassword"
        :rows="2"
        label="Enter Manager Password"
        placeholder="Input manager password..."
        />
        
        <fwb-button @click="createManager(managerFirstName, managerLastName, managerEmail, parseInt(managerPhoneNumber),
        managerPassword)" color="green">Create Manager</fwb-button>
    </div>
     </main>

     <main class="flex flex-row items-center-top">
        <fwb-badge type="default">View Customers</fwb-badge>
        <div v-for="customer in customerList" :key="customer.email">
            <UserTable :email="customer.email" :firstName="customer.firstName" :lastName="customer.lastName" :phoneNumber="customer.phoneNumber"/>
            <fwb-textarea
            v-model="messageCustomer"
            :rows="2"
            label="Delete Customer"
            placeholder="Input customer email of customer you want to delete..."
            />
            <fwb-button @click="deleteCustomer(messageCustomer)" color="red">Delete</fwb-button>
        </div>
        <div class="CreatingCustomer">
        <fwb-badge type="green">Create Customer</fwb-badge>
    <fwb-textarea
        v-model="customerFirstName"
        :rows="2"
        label="Enter Customer First Name"
        placeholder="Input customer first name..."
        />
        <fwb-textarea
        v-model="customerLastName"
        :rows="2"
        label="Enter Customer Last Name"
        placeholder="Input customer last name..."
        />
        <fwb-textarea
        v-model="customerEmail"
        :rows="2"
        label="Enter Customer Email"
        placeholder="Input customer email..."
        />

        <fwb-textarea
        v-model="customerPhoneNumber"
        :rows="2"
        label="Enter Customer Phone Number"
        placeholder="Input customer phone Number..."
        />

        <fwb-textarea
        v-model="customerPassword"
        :rows="2"
        label="Enter Customer Password"
        placeholder="Input customer password..."
        />

        <fwb-button @click="createCustomer(customerFirstName, customerLastName, customerEmail, parseInt(customerPhoneNumber),
        customerPassword)" color="green">Create Customer</fwb-button>
    </div>
     </main>

     <main class="flex flex-row items-center-top">
        <div v-for="generalUser in generalUserList" :key="generalUser.email">
            <fwb-badge type="default">View All Users</fwb-badge>
            <UserTable :email="generalUser.email" :firstName="generalUser.firstName" :lastName="generalUser.lastName" :phoneNumber="generalUser.phoneNumber" :userList="generalUser"/>
            <fwb-textarea
            v-model="messageGeneralUser"
            :rows="2"
            label="Delete General User"
            placeholder="Input generalUser email of generalUser you want to delete..."
            />
            <fwb-button @click="deleteGeneralUser(messageGeneralUser)" color="red">Delete</fwb-button>
        </div>
     </main>


     

</template>
