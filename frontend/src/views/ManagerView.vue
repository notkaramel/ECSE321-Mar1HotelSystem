<script lang="ts">

import ManageBooking from '@/components/ManageBooking.vue';
import HotelSchedule from '@/components/HotelScheduleComp.vue';

import axios from 'axios'
import { isBooleanAttr } from '@vue/shared'
const backendUrl = import.meta.env.VITE_BACKEND;

console.log(backendUrl)

// Get the data
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

async function getBookings() {
    let listOfBookings: any[] = await axios.get(backendUrl + "/booking/all")
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    return listOfBookings;
}

async function getShifts() {
    let listOfShifts: any[] = await axios.get(backendUrl + "/employee/shifts")
        .then(response => response.data)
        .catch(err => {
            console.log(err)
        });
    return listOfShifts;
}

// Put data got in list

let EmployeeList:any[] = await getEmployees();
let ManagerList:any[] = await getManagers();
let CustomerList:any[] = await getCustomers();
let GeneralUserList:any[] = await getGeneralUsers();
let RequestList:any[] = await getRequests();
let AssignmentList:any[] = await getAssignments();
let BookingList:any[] = await getBookings();
let ShiftList:any[] = await getShifts();
console.log(EmployeeList);
console.log(ManagerList);
console.log(CustomerList);
console.log(GeneralUserList);
console.log(RequestList);
console.log(AssignmentList);
console.log(BookingList);
console.log(ShiftList);
export default {
    components: {
    ManageBooking,
    HotelSchedule,
    },
    data() {
        return {
            employeeList: EmployeeList,
            managerList: ManagerList,
            customerList: CustomerList,
            generalUserList: GeneralUserList,
            requestList: RequestList,
            assignmentList: AssignmentList,
            bookingList: BookingList,
            shiftList: ShiftList,
        }
    },
}

// Functions for endpoints used
async function deleteEmployee(emailDelete: string) {
    let deletedEmployee = await axios.delete(backendUrl + "/employee/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedEmployee);
    window.location.reload();
    
    return deletedEmployee;
}


async function deleteManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
    let deletedManager = await axios.post(backendUrl + "/managers/delete", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedManager);
    window.location.reload();
    return deletedManager;
    
}

async function updateManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string, passwordUpdate: String) {
    let updatedManager = await axios.put(backendUrl + "/managers/update/"+passwordUpdate, {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(updatedManager);
    window.location.reload();
    return updatedManager;
    
}

async function deleteCustomer(emailDelete: string) {
    let deletedCustomer = await axios.delete(backendUrl + "/customer/"+emailDelete)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedCustomer);
    window.location.reload();
    return deletedCustomer;
}

async function deleteGeneralUser(firstName: string, lastName: string, email: string, phoneNumber: number) {
    let deletedGeneralUser = await axios.post(backendUrl + "/generalUsers/delete", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedGeneralUser);
    window.location.reload();
    return deletedGeneralUser;
}

async function deleteRequest(requestId: number) {
    let deletedRequest = await axios.delete(backendUrl + "/requests/delete/"+requestId)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedRequest);
    window.location.reload();
    return deletedRequest;
}

async function deleteAssignment(assignmentId: number) {
    let deletedAssignment = await axios.delete(backendUrl + "/assignments/delete/"+assignmentId)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedAssignment);
    window.location.reload();
    return deletedAssignment;
}


async function deleteEmployeeShift(shiftId: number) {
    let deletedEmployeeShift = await axios.delete(backendUrl + "/employee/shift/"+shiftId)
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(deletedEmployeeShift);
    window.location.reload();
    return deletedEmployeeShift;
}


async function createEmployee(firstName: string, lastName: string, email: string, phoneNumber: number, password: string, hoursWorked: number) {
    let createdEmployee = await axios.post(backendUrl + "/employee/", {"email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password, "hoursWorked": hoursWorked})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
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
            alert(err.response["data"])
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
            alert(err.response["data"])
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
            alert(err.response["data"])
        });
    console.log(createdGeneralUser);
    window.location.reload();
    return createdGeneralUser;
}

async function createRequest(description: String, bookingId: Number, isFulfilled: Boolean) {
    let createdRequest = await axios.post(backendUrl + "/request/create", {"description": description, "bookingId": bookingId, "isFulfilled": isFulfilled})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(createdRequest);
    window.location.reload();
    return createdRequest;
}

async function createAssignment(employeeId: String, requestId: Number) {
    let createdAssignment = await axios.post(backendUrl + "/assignment/create", {"employeeId": employeeId,"requestId": requestId})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(createdAssignment);
    //window.location.reload();
    return createdAssignment;
}

async function createEmployeeShift(shiftId: Number, date: String, startTime: Number, endTime: Number,
  email: String, firstName: String, lastName: String, phoneNumber: Number, password: String, hoursWorked: Number) {
    let createdEmployeeShift = await axios.post(backendUrl + "/employee/"+email+"shift", {"shiftId": shiftId, "date": date, "startTime": startTime, "endTime": endTime, "employee": { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password, "hoursWorked": hoursWorked}})
        .then(response => response.data)
        .catch(err => {
            console.log(err)
            alert(err.response["data"])
        });
    console.log(createdEmployeeShift);
    window.location.reload();
    return createdEmployeeShift;
}


</script>


<script setup lang="ts">
// Imports and messages of textboxes
  import { ref } from 'vue'
  import { FwbTextarea } from 'flowbite-vue'
  import { FwbButton } from 'flowbite-vue'
  import { FwbBadge } from 'flowbite-vue'
  import {
    FwbTable,
    FwbTableBody,
    FwbTableCell,
    FwbTableHead,
    FwbTableHeadCell,
    FwbTableRow,
    FwbAccordion,
  FwbAccordionContent,
  FwbAccordionHeader,
  FwbAccordionPanel,
  } from 'flowbite-vue'

  const messageEmployee = ref('')
  const messageCustomer = ref('')
  const messageRequest = ref('')
  const messageAssignment = ref('')

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

  const generalUserDeleteFirstName = ref('')
  const generalUserDeleteLastName = ref('')
  const generalUserDeleteEmail = ref('')
  const generalUserDeletePhoneNumber = ref('')
 
  const managerDeleteFirstName = ref('')
  const managerDeleteLastName = ref('')
  const managerDeleteEmail = ref('')
  const managerDeletePhoneNumber = ref('')
  const managerDeletePassword = ref('')

  const managerUpdateFirstName = ref('')
  const managerUpdateLastName = ref('')
  const managerUpdateEmail = ref('')
  const managerUpdatePhoneNumber = ref('')
  const managerUdpatePassword = ref('')
  const managerNewPassword = ref('')

  const shiftId = ref('')
  const shiftDate = ref('')
  const shiftStartTime = ref('')
  const shiftEndTime = ref('')
  const shiftFirstName= ref('')
  const shiftLastName = ref('')
  const shiftEmail = ref('')
  const shiftPhoneNumber = ref('')
  const shiftPassword = ref('')
  const shiftHoursWorked = ref('')
  const shiftIdDelete = ref('')

  const messageRequestDescription = ref('')
  const messageRequestBookingId = ref('')
  const messageRequestIsFufilled = ref('')
  const messageAssignmentEmployeeId = ref('')
  const messageAssignmentRequestId = ref('')

  </script>

<template>
    <title>
        Manager
    </title>

    <!-- Accordion view so each larger functionality is split, uses flow bite for components -->

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
        
        <fwb-button @click=" createEmployee(employeeFirstName, employeeLastName, employeeEmail, parseInt(employeePhoneNumber),
        employeePassword, parseInt(employeeHoursWorked))" color="green">Create Employee</fwb-button>
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
        v-model="managerDeleteFirstName"
        :rows="2"
        label="Enter Manager First Name"
        placeholder="Input manager first name..."
        />
        <fwb-textarea
        v-model="managerDeleteLastName"
        :rows="2"
        label="Enter Manager Last Name"
        placeholder="Input manager last name..."
        />
        <fwb-textarea
        v-model="managerDeleteEmail"
        :rows="2"
        label="Enter Manager Email"
        placeholder="Input manager email..."
        />

        <fwb-textarea
        v-model="managerDeletePhoneNumber"
        :rows="2"
        label="Enter Manager Phone Number"
        placeholder="Input manager phone Number..."
        />

        <fwb-textarea
        v-model="managerDeletePassword"
        :rows="2"
        label="Enter Manager Password"
        placeholder="Input manager password..."
        />
                <fwb-button @click="deleteManager(managerDeleteFirstName, managerDeleteLastName, managerDeleteEmail, parseInt(managerDeletePhoneNumber),
        managerDeletePassword)" color="red">Delete</fwb-button>
                <fwb-badge type="green">Update Manager</fwb-badge>
                <fwb-textarea
        v-model="managerUpdateFirstName"
        :rows="2"
        label="Enter Manager First Name"
        placeholder="Input manager first name..."
        />
        <fwb-textarea
        v-model="managerUpdateLastName"
        :rows="2"
        label="Enter Manager Last Name"
        placeholder="Input manager last name..."
        />
        <fwb-textarea
        v-model="managerUpdateEmail"
        :rows="2"
        label="Enter Manager Email"
        placeholder="Input manager email..."
        />

        <fwb-textarea
        v-model="managerUpdatePhoneNumber"
        :rows="2"
        label="Enter Manager Phone Number"
        placeholder="Input manager phone Number..."
        />

        <fwb-textarea
        v-model="managerUdpatePassword"
        :rows="2"
        label="Enter Manager Password"
        placeholder="Input manager password..."
        />

        <fwb-textarea
        v-model="managerNewPassword"
        :rows="2"
        label="Enter Manager New Password"
        placeholder="Input manager new password..."
        />
        
        <fwb-button @click="updateManager(managerUpdateFirstName, managerUpdateLastName, managerUpdateEmail, parseInt(managerUpdatePhoneNumber),
        managerUdpatePassword, managerNewPassword)" color="green">Update Manager</fwb-button>
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
        v-model="generalUserDeleteFirstName"
        :rows="2"
        label="Enter GeneralUser First Name"
        placeholder="Input general user first name..."
        />
        <fwb-textarea
        v-model="generalUserDeleteLastName"
        :rows="2"
        label="Enter GeneralUser Last Name"
        placeholder="Input general user last name..."
        />
        <fwb-textarea
        v-model="generalUserDeleteEmail"
        :rows="2"
        label="Enter GeneralUser Email"
        placeholder="Input general user email..."
        />

        <fwb-textarea
        v-model="generalUserDeletePhoneNumber"
        :rows="2"
        label="Enter GeneralUser Phone Number"
        placeholder="Input general user phone Number..."
        />
            <fwb-button @click="deleteGeneralUser(generalUserDeleteFirstName, generalUserDeleteLastName, generalUserDeleteEmail, parseInt(generalUserDeletePhoneNumber))" color="red">Delete</fwb-button>
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
            <fwb-badge type="default">View Requests</fwb-badge>
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
            <fwb-textarea
            v-model="messageRequest"
            :rows="2"
            label="Delete Request"
            placeholder="Input request id of request you want to delete..."
            />
            <fwb-button @click="deleteRequest(parseInt(messageRequest))" color="red">Delete</fwb-button>
        </div>
        <div>
            <fwb-badge type="green">Create Requests</fwb-badge>
            <fwb-textarea
            v-model="messageRequestDescription"
            :rows="2"
            label="Request Description"
            placeholder="Input Request Description..."
            />
            <fwb-textarea
            v-model="messageRequestBookingId"
            :rows="2"
            label="Booking Id of Booking related to Request"
            placeholder="Input Booking Id of Booking related to Request..."
            />
            <fwb-textarea
            v-model="messageRequestIsFufilled"
            :rows="2"
            label="Request Status as boolean either true of false (all lower caser)"
            placeholder="Input Request Status as boolean either true of false (all lower caser)"
            />
            <fwb-button @click="createRequest(messageRequestDescription, parseInt(messageRequestBookingId), isBooleanAttr(messageRequestIsFufilled))" color="green">Create Request</fwb-button>
        </div>
     </main>
    
      </fwb-accordion-content>
    </fwb-accordion-panel>
    <fwb-accordion-panel>
      <fwb-accordion-header>Assignments</fwb-accordion-header>
      <fwb-accordion-content>
        <main class="flex flex-row items-center-top">
        <div>
            <fwb-badge type="default">View Assignments</fwb-badge>
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Request Id</fwb-table-head-cell>
        <fwb-table-head-cell>Description</fwb-table-head-cell>
        <fwb-table-head-cell>Is Fufilled</fwb-table-head-cell>
        <fwb-table-head-cell>Booking Id</fwb-table-head-cell>
        <fwb-table-head-cell>Room Id</fwb-table-head-cell>
        <fwb-table-head-cell>Customer Email</fwb-table-head-cell>
        <fwb-table-head-cell>Employee Email</fwb-table-head-cell>
        <fwb-table-head-cell>Assignment Id</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="assignment in assignmentList">
          <fwb-table-cell> {{assignment["request"].requestId}}</fwb-table-cell>
          <fwb-table-cell>{{assignment["request"].description}}</fwb-table-cell>
          <fwb-table-cell>{{assignment["request"].isFulfilled}}</fwb-table-cell>
          <fwb-table-cell>{{assignment["request"]["booking"].bookingId}}</fwb-table-cell>
          <fwb-table-cell>{{assignment["request"]["booking"]["room"].roomId}}</fwb-table-cell>
          <fwb-table-cell>{{assignment["request"]["booking"]["generalUser"].email}}</fwb-table-cell>
          <fwb-table-cell>{{assignment["employee"].email}}</fwb-table-cell>
          <fwb-table-cell>{{assignment.assignmentId}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
            <fwb-textarea
            v-model="messageAssignment"
            :rows="2"
            label="Delete Assignment"
            placeholder="Input assignment id of assignment you want to delete..."
            />
            <fwb-button @click="deleteAssignment(parseInt(messageAssignment))" color="red">Delete</fwb-button>
        </div>
        <div>
            <fwb-badge type="green">Create Assignment</fwb-badge>
            <fwb-textarea
            v-model="messageAssignmentEmployeeId"
            :rows="2"
            label="Employee Id (which is the email) of employee in charge of Assignment"
            placeholder="Input Employee Id (which is the email) of employee in charge of Assignment..."
            />
            <fwb-textarea
            v-model="messageAssignmentRequestId"
            :rows="2"
            label="Request Id of request in related of Assignment"
            placeholder="Input Request Id of request in related of Assignment..."
            />
            <fwb-button @click="createAssignment(messageAssignmentEmployeeId, parseInt(messageAssignmentEmployeeId))" color="green">Create Assignment</fwb-button>
        </div>
     </main>
      </fwb-accordion-content>
    </fwb-accordion-panel>

<fwb-accordion-panel>
 <fwb-accordion-header>Manage Booking</fwb-accordion-header>
 <fwb-accordion-content>
   <div>
     <ManageBooking/>
   </div>
 </fwb-accordion-content>
</fwb-accordion-panel>
<fwb-accordion-panel>
 <fwb-accordion-header>Hotel Schedule</fwb-accordion-header>
 <fwb-accordion-content>
   <div>
     <HotelSchedule/>
   </div>
 </fwb-accordion-content>
</fwb-accordion-panel>
<fwb-accordion-panel>
 <fwb-accordion-header>Shifts</fwb-accordion-header>
 <fwb-accordion-content>
    <main class="flex flex-row items-center-top">
   <div>
    <fwb-badge type="default">View Shifts</fwb-badge>
            <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Shift Id</fwb-table-head-cell>
        <fwb-table-head-cell>Date</fwb-table-head-cell>
        <fwb-table-head-cell>Start Time</fwb-table-head-cell>
        <fwb-table-head-cell>End Time</fwb-table-head-cell>
        <fwb-table-head-cell>Employee Email</fwb-table-head-cell>
        <fwb-table-head-cell>Hours Worked</fwb-table-head-cell>
        <fwb-table-head-cell>
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <fwb-table-row v-for="shift in shiftList">
          <fwb-table-cell> {{shift.shiftId}}</fwb-table-cell>
          <fwb-table-cell> {{shift.date}}</fwb-table-cell>
          <fwb-table-cell>{{shift.startTime}}</fwb-table-cell>
          <fwb-table-cell>{{shift.endTime}}</fwb-table-cell>
          <fwb-table-cell>{{shift["employee"].email}}</fwb-table-cell>
          <fwb-table-cell>{{shift["employee"].hoursWorked}}</fwb-table-cell>
          <fwb-table-cell>
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>

    <fwb-textarea
        v-model="shiftIdDelete"
        :rows="2"
        label="Enter Shift Id to Delete Specific Shift"
        placeholder="Input Shift Id to Delete Specific Shift..."
        />
                <fwb-button @click="deleteEmployeeShift(parseInt(shiftIdDelete))" color="red">Delete Employee Shift</fwb-button>                
    </div>
     <div>
    <fwb-badge type="green">Create Employee Shift</fwb-badge>
    <fwb-textarea
        v-model="shiftId"
        :rows="2"
        label="Enter Employee Shift Id (You make a unique Employee Shift Id)"
        placeholder="Input Employee Shift Id..."
        />
        <fwb-textarea
        v-model="shiftDate"
        :rows="2"
        label="Enter Employee Shift Date YYYY-MM-DD"
        placeholder="Input Employee Shift Date YYYY-MM-DD.."
        />
        <fwb-textarea
        v-model="shiftStartTime"
        :rows="2"
        label="Enter Employee Shift Start Time"
        placeholder="Input Employee Shift Start Time..."
        />

        <fwb-textarea
        v-model="shiftEndTime"
        :rows="2"
        label="Enter Employee Shift End Time"
        placeholder="Input Employee Shift End Time..."
        />
    <fwb-textarea
        v-model="shiftFirstName"
        :rows="2"
        label="Enter Employee First Name"
        placeholder="Input employee first name..."
        />
        <fwb-textarea
        v-model="shiftLastName"
        :rows="2"
        label="Enter Employee Last Name"
        placeholder="Input employee last name..."
        />
        <fwb-textarea
        v-model="shiftEmail"
        :rows="2"
        label="Enter Employee Email"
        placeholder="Input employee email..."
        />

        <fwb-textarea
        v-model="shiftPhoneNumber"
        :rows="2"
        label="Enter Employee Phone Number"
        placeholder="Input employee phone Number..."
        />

        <fwb-textarea
        v-model="shiftPassword"
        :rows="2"
        label="Enter Employee Password"
        placeholder="Input employee password..."
        />

        <fwb-textarea
        v-model="shiftHoursWorked"
        :rows="2"
        label="Enter Employee hours worked"
        placeholder="Input employee hours worked..."
        />
                <fwb-button @click="createEmployeeShift(parseInt(shiftId), shiftDate, parseInt(shiftStartTime),
                parseInt(shiftEndTime) ,shiftEmail, shiftFirstName, shiftLastName, parseInt(shiftPhoneNumber), shiftPassword, parseInt(shiftHoursWorked))" color="green">Create Employee Shift</fwb-button>
   </div>
</main>
 </fwb-accordion-content>
</fwb-accordion-panel>
</fwb-accordion>


     

</template>
