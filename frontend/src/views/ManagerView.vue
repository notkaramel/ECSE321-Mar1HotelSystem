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

async function getRequests() {
    let listOfRequests: any[] = await axios.get(backendUrl + "/requests")
        .then(response => response.data)
        // .then(response => response.data["room"])
        // .then(response => response.data["generalUser"])
        // .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    return listOfRequests;
}

async function getAssignments() {
    let listOfAssignments: any[] = await axios.get(backendUrl + "/assignments/all")
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    return listOfAssignments;
}
const emailS= '';
let EmployeeList:any[] = await getEmployees();
let ManagerList:any[] = await getManagers();
let CustomerList:any[] = await getCustomers();
let GeneralUserList:any[] = await getGeneralUsers();
let RequestList:any[] = await getRequests();
let AssignmentList:any[] = await getAssignments();
//let SearchedEmployee:any[] = await searchEmployee();
// let DeletedEmployee = getDeletedEmployee();
console.log(EmployeeList);
console.log(ManagerList);
console.log(CustomerList);
console.log(GeneralUserList);
console.log(RequestList);
console.log(AssignmentList);
// console.log(SearchedEmployee);
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
            requestList: RequestList,
            assignmentList: AssignmentList,
            searchedEmployeeRes: ''
            
            // emailDelete: ''
            // deletedEmployee
        }
    }
}

async function searchEmployee(emailSearch: string, event: Event){
    // const buttonValue = event.target?.addEventListener;
    let searchedEmployee= await axios.get(backendUrl + "/employee/"+emailSearch)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(searchedEmployee);
    window.location.reload();
    //event.target?.addEventListener
    
  
    return searchedEmployee;
}

// async function searchedEmployee(event: Event) {
//     let searchedEmployeeResults = event.target;

//     searchEmployee();
// }

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
    window.location.reload();
    return deletedEmployee;
}

async function deleteManager(emailDelete: string) {
    let deletedManager = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedManager);
    window.location.reload();
    return deletedManager;
}

async function deleteCustomer(emailDelete: string) {
    let deletedCustomer = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedCustomer);
    window.location.reload();
    return deletedCustomer;
}

async function deleteGeneralUser(emailDelete: string) {
    let deletedGeneralUser = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(deletedGeneralUser);
    window.location.reload();
    return deletedGeneralUser;
}


async function createEmployee(firstName: string, lastName: string, email: string, phoneNumber: number, password: string, hoursWorked: number) {
    let createdEmployee = await axios.post(backendUrl + "/employee/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password, "hoursWorked": hoursWorked})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdEmployee);
    window.location.reload();
    return createdEmployee;
}

async function createManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
    let createdManager = await axios.post(backendUrl + "/managers/create/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdManager);
    window.location.reload();
    return createdManager;
}

async function createCustomer(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
    let createdCustomer = await axios.post(backendUrl + "/customer/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdCustomer);
    window.location.reload();
    return createdCustomer;
}

async function createGeneralUser(firstName: string, lastName: string, email: string, phoneNumber: number) {
    let createdGeneralUser = await axios.post(backendUrl + "/generalUsers/create", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    console.log(createdGeneralUser);
    window.location.reload();
    return createdGeneralUser;
}

// async function searchEmployee(emailSearch: string) {
//     let searchedEmployee= await axios.get(backendUrl + "/employee/"+emailSearch)
//         .then(response => response.data)
//         .catch(err => {
//             console.log(err)
//         });
//     console.log(searchedEmployee);
//     window.location.reload();
//     return searchedEmployee;
// }

// async function searchedEmployee() {
//     searchEmployee(messageSearchEmployee);
// }

// async function deleteEmployeeSpecific(){
//     deleteEmployee(message)
// }



</script>

<script setup lang="ts">
  import { ref } from 'vue'
  import { FwbTextarea } from 'flowbite-vue'
  import { FwbButton } from 'flowbite-vue'
  import { FwbBadge } from 'flowbite-vue'
  import {
    FwbA,
    FwbTable,
    FwbTableBody,
    FwbTableCell,
    FwbTableHead,
    FwbTableHeadCell,
    FwbTableRow,
    FwbCheckbox,
    FwbAccordion,
  FwbAccordionContent,
  FwbAccordionHeader,
  FwbAccordionPanel,
  } from 'flowbite-vue'

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

  const generalUserFirstName = ref('')
  const generalUserLastName = ref('')
  const generalUserEmail = ref('')
  const generalUserPhoneNumber = ref('')

  const messageSearchEmployee = ref('')
  const messageSearchEmployeeResult = ref('')
 
  
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
    <title>
        Manager
    </title>
    <fwb-accordion :open-first-item="false">
    <fwb-accordion-panel>
      <fwb-accordion-header>Employees</fwb-accordion-header>
      <fwb-accordion-content>
    <main class="flex flex-row items-center-top">
        <div>
            <fwb-badge type="default">View Employees</fwb-badge>
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Email</fwb-table-head-cell>
        <fwb-table-head-cell>First Name</fwb-table-head-cell>
        <fwb-table-head-cell>Last Name</fwb-table-head-cell>
        <fwb-table-head-cell>Phone Number</fwb-table-head-cell>
        <fwb-table-head-cell>Hours Worked</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="employee in employeeList">
          <fwb-table-cell> {{employee.email}}</fwb-table-cell>
          <fwb-table-cell>{{employee.firstName}}</fwb-table-cell>
          <fwb-table-cell>{{employee.lastName}}</fwb-table-cell>
          <fwb-table-cell>{{employee.phoneNumber}}</fwb-table-cell>
          <fwb-table-cell>{{employee.hoursWorked}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
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
        <div>
        <fwb-badge type="default">Search Employee</fwb-badge>
        <fwb-textarea
            v-model="messageSearchEmployee"
            :rows="2"
            label="Search Employee"
            placeholder="Input employee email of employee you want to search..."
            />
            <fwb-button @click=" searchEmployee(messageSearchEmployee, $event)" color="green">Search</fwb-button>
            
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Email</fwb-table-head-cell>
        <fwb-table-head-cell>First Name</fwb-table-head-cell>
        <fwb-table-head-cell>Last Name</fwb-table-head-cell>
        <fwb-table-head-cell>Phone Number</fwb-table-head-cell>
        <fwb-table-head-cell>Hours Worked</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="searchedEmployee in searchedEmployeeRes">
          <fwb-table-cell>  searchedEmployee </fwb-table-cell>
          <!-- <fwb-table-cell>{{employee}}</fwb-table-cell>
          <fwb-table-cell>{{employee}}</fwb-table-cell>
          <fwb-table-cell>{{employee}}</fwb-table-cell>
          <fwb-table-cell>{{employee}}</fwb-table-cell> -->
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
    </div>
     </main>
    </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
    <fwb-accordion-header>Managers</fwb-accordion-header>
    <fwb-accordion-content>
    
     <main class="flex flex-row items-center-top">
        <div>
                <fwb-badge type="default">View Managers</fwb-badge>
                <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Email</fwb-table-head-cell>
        <fwb-table-head-cell>First Name</fwb-table-head-cell>
        <fwb-table-head-cell>Last Name</fwb-table-head-cell>
        <fwb-table-head-cell>Phone Number</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="manager in managerList">
          <fwb-table-cell> {{manager.email}}</fwb-table-cell>
          <fwb-table-cell>{{manager.firstName}}</fwb-table-cell>
          <fwb-table-cell>{{manager.lastName}}</fwb-table-cell>
          <fwb-table-cell>{{manager.phoneNumber}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
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

    </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
      <fwb-accordion-header>Customers</fwb-accordion-header>
      <fwb-accordion-content>

     <main class="flex flex-row items-center-top">
        <div>
            <fwb-badge type="default">View Customers</fwb-badge>
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Email</fwb-table-head-cell>
        <fwb-table-head-cell>First Name</fwb-table-head-cell>
        <fwb-table-head-cell>Last Name</fwb-table-head-cell>
        <fwb-table-head-cell>Phone Number</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="customer in customerList">
          <fwb-table-cell> {{customer.email}}</fwb-table-cell>
          <fwb-table-cell>{{customer.firstName}}</fwb-table-cell>
          <fwb-table-cell>{{customer.lastName}}</fwb-table-cell>
          <fwb-table-cell>{{customer.phoneNumber}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
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

    </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
      <fwb-accordion-header>All Users</fwb-accordion-header>
      <fwb-accordion-content>
     <main class="flex flex-row items-center-top">
        <div>
            <fwb-badge type="default">View All Users</fwb-badge>
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Email</fwb-table-head-cell>
        <fwb-table-head-cell>First Name</fwb-table-head-cell>
        <fwb-table-head-cell>Last Name</fwb-table-head-cell>
        <fwb-table-head-cell>Phone Number</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="generalUser in generalUserList">
          <fwb-table-cell> {{generalUser.email}}</fwb-table-cell>
          <fwb-table-cell>{{generalUser.firstName}}</fwb-table-cell>
          <fwb-table-cell>{{generalUser.lastName}}</fwb-table-cell>
          <fwb-table-cell>{{generalUser.phoneNumber}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
            <fwb-textarea
            v-model="messageGeneralUser"
            :rows="2"
            label="Delete General User"
            placeholder="Input generalUser email of generalUser you want to delete..."
            />
            <fwb-button @click="deleteGeneralUser(messageGeneralUser)" color="red">Delete</fwb-button>
        </div>
        <div class="CreatingGeneralUser">
    <fwb-textarea
        v-model="generalUserFirstName"
        :rows="2"
        label="Enter GeneralUser First Name"
        placeholder="Input general user first name..."
        />
        <fwb-textarea
        v-model="generalUserLastName"
        :rows="2"
        label="Enter GeneralUser Last Name"
        placeholder="Input general user last name..."
        />
        <fwb-textarea
        v-model="generalUserEmail"
        :rows="2"
        label="Enter GeneralUser Email"
        placeholder="Input general user email..."
        />

        <fwb-textarea
        v-model="generalUserPhoneNumber"
        :rows="2"
        label="Enter GeneralUser Phone Number"
        placeholder="Input general user phone Number..."
        />
        
        <fwb-button @click="createGeneralUser(generalUserFirstName, generalUserLastName, generalUserEmail, parseInt(generalUserPhoneNumber))" color="green">Create GeneralUser</fwb-button>
    </div>
     </main>

    </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
      <fwb-accordion-header>Requests</fwb-accordion-header>
      <fwb-accordion-content>
        <main class="flex flex-row items-center-top">
        <div>
            <fwb-badge type="default">View All Users</fwb-badge>
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Request Id</fwb-table-head-cell>
        <fwb-table-head-cell>Description</fwb-table-head-cell>
        <fwb-table-head-cell>Is Fufilled</fwb-table-head-cell>
        <fwb-table-head-cell>Booking Id</fwb-table-head-cell>
        <fwb-table-head-cell>Room Id</fwb-table-head-cell>
        <fwb-table-head-cell>Customer Email</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="request in requestList">
          <fwb-table-cell> {{request.requestId}}</fwb-table-cell>
          <fwb-table-cell>{{request.description}}</fwb-table-cell>
          <fwb-table-cell>{{request.isFulfilled}}</fwb-table-cell>
          <fwb-table-cell>{{request["booking"].bookingId}}</fwb-table-cell>
          <fwb-table-cell>{{request["booking"]["room"].roomId}}</fwb-table-cell>
          <fwb-table-cell>{{request["booking"]["generalUser"].email}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
            <!-- <fwb-textarea
            v-model="messageGeneralUser"
            :rows="2"
            label="Delete General User"
            placeholder="Input generalUser email of generalUser you want to delete..."
            />
            <fwb-button @click="deleteGeneralUser(messageGeneralUser)" color="red">Delete</fwb-button> -->
        </div>
     </main>
    
      </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
      <fwb-accordion-header>Assignments</fwb-accordion-header>
      <fwb-accordion-content>

      </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
      <fwb-accordion-header>Booking</fwb-accordion-header>
      <fwb-accordion-content>


    </fwb-accordion-content>
    </fwb-accordion-panel>
</fwb-accordion>


     

</template>
