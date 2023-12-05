<script lang="ts">

import ManageBooking from '@/components/ManageBooking.vue';
import HotelSchedule from '@/components/HotelScheduleComp.vue';

import axios from 'axios'
const backendUrl = import.meta.env.VITE_BACKEND;

// console.log(backendUrl)

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

async function getRooms() {
  let listOfRooms: any[] = await axios.get(backendUrl + "/rooms")
    .then(response => response.data["roomList"])
    .catch(err => {
      console.log(err)
    });
  return listOfRooms;
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

let EmployeeList: any[] = await getEmployees();
let ManagerList: any[] = await getManagers();
let CustomerList: any[] = await getCustomers();
let GeneralUserList: any[] = await getGeneralUsers();
let RequestList: any[] = await getRequests();
let AssignmentList: any[] = await getAssignments();
let BookingList: any[] = await getBookings();
let ShiftList: any[] = await getShifts();
let RoomList: any[] = await getRooms();
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
      roomList: RoomList,
      requestList: RequestList,
      assignmentList: AssignmentList,
      bookingList: BookingList,
      shiftList: ShiftList,
    }
  },
  methods: {
    // rerender the page
    async rerender() {
      this.employeeList = await getEmployees();
      this.managerList = await getManagers();
      this.customerList = await getCustomers();
      this.generalUserList = await getGeneralUsers();
      this.roomList = await getRooms();
      this.requestList = await getRequests();
      this.assignmentList = await getAssignments();
      this.bookingList = await getBookings();
      this.shiftList = await getShifts();
    },
    // Functions for endpoints used
    async deleteEmployee(emailDelete: string) {
      let deletedEmployee = await axios.delete(backendUrl + "/employee/" + emailDelete)
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedEmployee;
    },
    async deleteManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
      let deletedManager = await axios.post(backendUrl + "/managers/delete", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedManager;
    },
    async deleteRoom(roomId: number) {
      let deletedRoom = await axios.delete(backendUrl + "/room/delete/" + roomId)
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      this.rerender();
      return deletedRoom;
    },

    async updateManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string, passwordUpdate: String) {
      let updatedManager = await axios.put(backendUrl + "/managers/update/" + passwordUpdate, { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return updatedManager;
    },

    async deleteCustomer(emailDelete: string) {
      let deletedCustomer = await axios.delete(backendUrl + "/customer/" + emailDelete)
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedCustomer;
    },

    async deleteGeneralUser(firstName: string, lastName: string, email: string, phoneNumber: number) {
      let deletedGeneralUser = await axios.post(backendUrl + "/generalUsers/delete", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedGeneralUser;
    },
    async deleteRequest(requestId: number) {
      let deletedRequest = await axios.delete(backendUrl + "/requests/delete/" + requestId)
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedRequest;
    },
    async deleteAssignment(assignmentId: number) {
      let deletedAssignment = await axios.delete(backendUrl + "/assignments/delete/" + assignmentId)
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedAssignment;
    },
    async deleteEmployeeShift(shiftId: number) {
      let deletedEmployeeShift = await axios.delete(backendUrl + "/employee/shift/" + shiftId)
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return deletedEmployeeShift;
    },
    async createEmployee(firstName: string, lastName: string, email: string, phoneNumber: number, password: string, hoursWorked: number) {
      let createdEmployee = await axios.post(backendUrl + "/employee/", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password, "hoursWorked": hoursWorked })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdEmployee;
    },
    async createManager(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
      let createdManager = await axios.post(backendUrl + "/managers/create/", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdManager;
    },
    async createCustomer(firstName: string, lastName: string, email: string, phoneNumber: number, password: string) {
      let createdCustomer = await axios.post(backendUrl + "/customer/", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber, "password": password })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdCustomer;
    },
    async createGeneralUser(firstName: string, lastName: string, email: string, phoneNumber: number) {
      let createdGeneralUser = await axios.post(backendUrl + "/generalUsers/create", { "email": email, "firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdGeneralUser;
    },
    async createRoom(roomType: String, bedType: String, roomMaxCapacity: Number, roomPricePerNight: Number) {
      let createdRoom = await axios.post(backendUrl + "/room/create", { "roomType": roomType, "bedType": bedType, "maxCapacity": roomMaxCapacity, "pricePerNight": roomPricePerNight, "isAvailable": 'true' })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdRoom;
    },
    async createRequest(description: String, bookingId: Number, isFulfilled: String) {
      var setTrue = (isFulfilled === "true");
      let createdRequest = await axios.post(backendUrl + "/request/create", { "description": description, "bookingId": bookingId, "isFulfilled": setTrue })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdRequest;
    },
    async createAssignment(employeeId: String, requestId: Number) {
      let createdAssignment = await axios.post(backendUrl + "/assignment/create", { "employeeId": employeeId, "requestId": requestId })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdAssignment;
    },
    async createEmployeeShift(date: String, startTime: Number, endTime: Number,
      email: String) {
      let createdEmployeeShift = await axios.post(backendUrl + "/employee/" + email + "/shift", { "date": date + "T00:00:00.000+00:00", "startTime": startTime, "endTime": endTime })
        .then(response => response.data)
        .catch(err => {
          console.log(err)
          alert(err.response["data"])
        });
      // window.location.reload();
      this.rerender();
      return createdEmployeeShift;
    }
  }
};

</script>

<script setup lang="ts">
// Imports and messages of textboxes
import { ref } from 'vue'
import { FwbInput } from 'flowbite-vue'
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

const roomId = ref('')
const roomType = ref('')
const bedType = ref('')
const roomMaxCapacity = ref('')
const roomPricePerNight = ref('')

const shiftDate = ref('')
const shiftStartTime = ref('')
const shiftEndTime = ref('')
const shiftEmail = ref('')
const shiftIdDelete = ref('')

const messageRequestDescription = ref('')
const messageRequestBookingId = ref('')
const messageRequestIsFufilled = ref('')
const messageAssignmentEmployeeId = ref('')
const messageAssignmentRequestId = ref('')

</script>

<style scoped lang="postcss">
.manager {
  @apply block bg-white rounded-lg border border-gray-200 shadow-md;
  @apply p-8 m-3;
}

.accordion-content {
  @apply flex flex-row items-start justify-evenly;
}
</style>

<template>
  <!-- Accordion view so each larger functionality is split, uses flow bite for components -->
  <div class="manager">
    <fwb-accordion :open-first-item="false">
      <!-- Employee Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Employees</fwb-accordion-header>
        <fwb-accordion-content>
          <div class="accordion-content">
            <div id="ViewingEmployee">
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
                  <fwb-table-row v-for="employee in employeeList" :key="employee.email">
                    <fwb-table-cell> {{ employee.email }}</fwb-table-cell>
                    <fwb-table-cell>{{ employee.firstName }}</fwb-table-cell>
                    <fwb-table-cell>{{ employee.lastName }}</fwb-table-cell>
                    <fwb-table-cell>{{ employee.phoneNumber }}</fwb-table-cell>
                    <fwb-table-cell>{{ employee.hoursWorked }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>

            <div id="CreatingEmployee">
              <fwb-badge type="green">Create Employee</fwb-badge>
              <fwb-input v-model="employeeFirstName" size="md" label="Enter Employee First Name" placeholder="John" />
              <fwb-input v-model="employeeLastName" size="md" label="Enter Employee Last Name" placeholder="Doe" />
              <fwb-input v-model="employeeEmail" size="md" label="Enter Employee Email" placeholder="john@doe.com" />

              <fwb-input v-model="employeePhoneNumber" size="md" label="Enter Employee Phone Number"
                placeholder="438 123 4567" />

              <fwb-input v-model="employeePassword" size="md" label="Enter Employee Password" placeholder="********" />

              <fwb-input v-model="employeeHoursWorked" size="md" label="Enter Employee hours worked" placeholder="14" />

              <fwb-button @click="createEmployee(employeeFirstName, employeeLastName, employeeEmail, parseInt(employeePhoneNumber),
                employeePassword, parseInt(employeeHoursWorked))" color="green">Create
                Employee</fwb-button>
            </div>

            <div>
              <fwb-badge type="red">Delete Employee</fwb-badge>
              <fwb-input v-model="messageEmployee" size="md" label="Employee Email To Delete"
                placeholder="john@doe.com" />
              <fwb-button @click="deleteEmployee(messageEmployee)" color="red">Delete</fwb-button>
            </div>
          </div>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Manager Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Managers</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
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

                  <fwb-table-row v-for="manager in managerList" :key="manager.email">
                    <fwb-table-cell> {{ manager.email }}</fwb-table-cell>
                    <fwb-table-cell>{{ manager.firstName }}</fwb-table-cell>
                    <fwb-table-cell>{{ manager.lastName }}</fwb-table-cell>
                    <fwb-table-cell>{{ manager.phoneNumber }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>

            <div class="CreatingManager">
              <fwb-badge type="green">Create Manager</fwb-badge>
              <fwb-input v-model="managerFirstName" size="md" label="Enter Manager First Name" placeholder="Marwan" />
              <fwb-input v-model="managerLastName" size="md" label="Enter Manager Last Name" placeholder="Kanaan" />
              <fwb-input v-model="managerEmail" size="md" label="Enter Manager Email"
                placeholder="marwan@mar1hotel.com" />

              <fwb-input v-model="managerPhoneNumber" size="md" label="Enter Manager Phone Number"
                placeholder="438 123 4567" />

              <fwb-input v-model="managerPassword" size="md" label="Enter Manager Password" placeholder="**********" />

              <fwb-button @click="createManager(managerFirstName, managerLastName, managerEmail, parseInt(managerPhoneNumber),
                managerPassword)" color="green">Create Manager</fwb-button>
            </div>

            <div>
              <fwb-badge type="yellow">Update Manager</fwb-badge>
              <fwb-input v-model="managerUpdateFirstName" size="md" label="Enter Manager First Name"
                placeholder="Marwan" />
              <fwb-input v-model="managerUpdateLastName" size="md" label="Enter Manager Last Name" placeholder="Kanaan" />
              <fwb-input v-model="managerUpdateEmail" size="md" label="Enter Manager Email"
                placeholder="marwan@mar1hotel.com" />

              <fwb-input v-model="managerUpdatePhoneNumber" size="md" label="Enter Manager Phone Number"
                placeholder="438 123 4567" />

              <fwb-input v-model="managerUdpatePassword" size="md" label="Enter Manager Password"
                placeholder="**********" />

              <fwb-input v-model="managerNewPassword" size="md" label="Enter Manager New Password"
                placeholder="**********" />

              <fwb-button @click="updateManager(managerUpdateFirstName, managerUpdateLastName, managerUpdateEmail, parseInt(managerUpdatePhoneNumber),
                managerUdpatePassword, managerNewPassword)" color="green">Update Manager</fwb-button>
            </div>

            <div>
              <fwb-badge type="red">Delete Manager</fwb-badge>
              <fwb-input v-model="managerDeleteFirstName" size="md" label="Enter Manager First Name"
                placeholder="Marwan" />
              <fwb-input v-model="managerDeleteLastName" size="md" label="Enter Manager Last Name" placeholder="Kanaan" />
              <fwb-input v-model="managerDeleteEmail" size="md" label="Enter Manager Email"
                placeholder="marwan@mar1hotel.com" />

              <fwb-input v-model="managerDeletePhoneNumber" size="md" label="Enter Manager Phone Number"
                placeholder="438 123 4567" />

              <fwb-input v-model="managerDeletePassword" size="md" label="Enter Manager Password"
                placeholder="**********" />
              <fwb-button @click="deleteManager(managerDeleteFirstName, managerDeleteLastName, managerDeleteEmail, parseInt(managerDeletePhoneNumber),
                managerDeletePassword)" color="red">Delete</fwb-button>
            </div>
          </main>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Customer Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Customers</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
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
                  <fwb-table-row v-for="customer in customerList" :key="customer.email">
                    <fwb-table-cell> {{ customer.email }}</fwb-table-cell>
                    <fwb-table-cell>{{ customer.firstName }}</fwb-table-cell>
                    <fwb-table-cell>{{ customer.lastName }}</fwb-table-cell>
                    <fwb-table-cell>{{ customer.phoneNumber }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>
            <div class="CreatingCustomer">
              <fwb-badge type="green">Create Customer</fwb-badge>
              <fwb-input v-model="customerFirstName" size="md" label="Enter Customer First Name" placeholder="John" />
              <fwb-input v-model="customerLastName" size="md" label="Enter Customer Last Name" placeholder="Doe" />
              <fwb-input v-model="customerEmail" size="md" label="Enter Customer Email" placeholder="john.doe@mail.com" />
              <fwb-input v-model="customerPhoneNumber" size="md" label="Enter Customer Phone Number"
                placeholder="438 123 4567" />
              <fwb-input v-model="customerPassword" size="md" label="Enter Customer Password" placeholder="**********" />

              <fwb-button @click="createCustomer(customerFirstName, customerLastName, customerEmail, parseInt(customerPhoneNumber),
                customerPassword)" color="green">Create Customer</fwb-button>
            </div>
            <div>
              <fwb-badge type="red">Delete Customer</fwb-badge>
              <fwb-input v-model="messageCustomer" size="md" label="Customer Email To Be Deleted"
                placeholder="john.doe@mail.com" />
              <fwb-button @click="deleteCustomer(messageCustomer)" color="red">Delete</fwb-button>
            </div>
          </main>

        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- General User Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>All Users</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
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
                  <fwb-table-row v-for="generalUser in generalUserList" :key="generalUser.email">
                    <fwb-table-cell> {{ generalUser.email }}</fwb-table-cell>
                    <fwb-table-cell>{{ generalUser.firstName }}</fwb-table-cell>
                    <fwb-table-cell>{{ generalUser.lastName }}</fwb-table-cell>
                    <fwb-table-cell>{{ generalUser.phoneNumber }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>
            <div class="CreatingGeneralUser">
              <fwb-badge type="green">Create Guest User</fwb-badge>
              <fwb-input v-model="generalUserFirstName" size="md" label="Enter GeneralUser First Name"
                placeholder="John" />
              <fwb-input v-model="generalUserLastName" size="md" label="Enter GeneralUser Last Name" placeholder="Doe" />
              <fwb-input v-model="generalUserEmail" size="md" label="Enter GeneralUser Email"
                placeholder="john.doe@mail.com" />

              <fwb-input v-model="generalUserPhoneNumber" size="md" label="Enter GeneralUser Phone Number"
                placeholder="438 123 4567" />

              <fwb-button
                @click="createGeneralUser(generalUserFirstName, generalUserLastName, generalUserEmail, parseInt(generalUserPhoneNumber))"
                color="green">Create GeneralUser</fwb-button>
            </div>
            <div>
              <fwb-badge type="red">Delete Guest User</fwb-badge>
              <fwb-input v-model="generalUserDeleteFirstName" size="md" label="Enter GeneralUser First Name"
                placeholder="John" />
              <fwb-input v-model="generalUserDeleteLastName" size="md" label="Enter GeneralUser Last Name"
                placeholder="Doe" />
              <fwb-input v-model="generalUserDeleteEmail" size="md" label="Enter GeneralUser Email"
                placeholder="john.doe@mail.com" />

              <fwb-input v-model="generalUserDeletePhoneNumber" size="md" label="Enter GeneralUser Phone Number"
                placeholder="438 123 4567" />
              <fwb-button
                @click="deleteGeneralUser(generalUserDeleteFirstName, generalUserDeleteLastName, generalUserDeleteEmail, parseInt(generalUserDeletePhoneNumber))"
                color="red">Delete</fwb-button>
            </div>
          </main>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Room Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Rooms</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
            <div>
              <fwb-badge type="default">View All Rooms</fwb-badge>
              <fwb-table hoverable>
                <fwb-table-head>
                  <fwb-table-head-cell>Room ID</fwb-table-head-cell>
                  <fwb-table-head-cell>Room Type</fwb-table-head-cell>
                  <fwb-table-head-cell>Bed Type</fwb-table-head-cell>
                  <fwb-table-head-cell>Capacity</fwb-table-head-cell>
                  <fwb-table-head-cell>Price/Night</fwb-table-head-cell>
                  <fwb-table-head-cell>
                  </fwb-table-head-cell>
                </fwb-table-head>
                <fwb-table-body>
                  <fwb-table-row v-for="room in roomList" :key="room.roomId">
                    <fwb-table-cell> {{ room.roomId }}</fwb-table-cell>
                    <fwb-table-cell>{{ room.roomType }}</fwb-table-cell>
                    <fwb-table-cell>{{ room.bedType }}</fwb-table-cell>
                    <fwb-table-cell>{{ room.maxCapacity }}</fwb-table-cell>
                    <fwb-table-cell>{{ room.pricePerNight }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>
            <div class="CreatingRoom">
              <fwb-badge type="green">Create Room</fwb-badge>
              <fwb-input v-model="roomType" size="md" label="Enter Room Type: 'Regular', 'Suite', or 'Deluxe'"
                placeholder="Regular" />
              <fwb-input v-model="bedType" size="md" label="Enter Bed Type: 'Single', 'Doubles', 'Queen', or 'King'"
                placeholder="Queen" />
              <fwb-input v-model="roomMaxCapacity" size="md" label="Enter Room Capacity" placeholder="4" />
              <fwb-input v-model="roomPricePerNight" size="md" label="Enter Room Price Per Night (CAD$)"
                placeholder="120" />

              <fwb-button @click="createRoom(roomType, bedType, parseInt(roomMaxCapacity), parseInt(roomPricePerNight))"
                color="green">Create Room</fwb-button>
            </div>
            <div>
              <fwb-badge type="red">Delete Room</fwb-badge>
              <fwb-input v-model="roomId" size="md" label="Enter Room ID to be deleted"
                placeholder="14" />
              <fwb-button
                @click="deleteRoom(parseInt(roomId))"
                color="red">Delete Room</fwb-button>
            </div>
          </main>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Request Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Requests</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
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
                  <fwb-table-row v-for="request in requestList" :key="request.requestId">
                    <fwb-table-cell> {{ request.requestId }}</fwb-table-cell>
                    <fwb-table-cell>{{ request.description }}</fwb-table-cell>
                    <fwb-table-cell>{{ request.isFulfilled }}</fwb-table-cell>
                    <fwb-table-cell>{{ request["booking"].bookingId }}</fwb-table-cell>
                    <fwb-table-cell>{{ request["booking"]["room"].roomId }}</fwb-table-cell>
                    <fwb-table-cell>{{ request["booking"]["generalUser"].email }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>

            <div>
              <fwb-badge type="green">Create Requests</fwb-badge>
              <fwb-input v-model="messageRequestDescription" size="md" label="Request Description"
                placeholder="I need a hair dryer please!" />
              <fwb-input v-model="messageRequestBookingId" size="md" label="Booking Id" placeholder="214" />
              <fwb-input v-model="messageRequestIsFufilled" size="md" label="Request Status"
                placeholder="'true' or 'false'" />
              <fwb-button
                @click="createRequest(messageRequestDescription, parseInt(messageRequestBookingId), messageRequestIsFufilled)"
                color="green">Create Request</fwb-button>
            </div>

            <div>
              <fwb-badge type="red">Delete Request</fwb-badge>
              <fwb-input v-model="messageRequest" size="md" label="Request ID to be deleted" placeholder="132" />
              <fwb-button @click="deleteRequest(parseInt(messageRequest))" color="red">Delete</fwb-button>
            </div>
          </main>

        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Assignment Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Assignments</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
            <div>
              <fwb-badge type="default">View Assignments</fwb-badge>
              <fwb-table hoverable>
                <fwb-table-head>
                  <fwb-table-head-cell>Assignment Id</fwb-table-head-cell>
                  <fwb-table-head-cell>Request Id</fwb-table-head-cell>
                  <fwb-table-head-cell>Description</fwb-table-head-cell>
                  <fwb-table-head-cell>Is Fufilled</fwb-table-head-cell>
                  <fwb-table-head-cell>Booking Id</fwb-table-head-cell>
                  <!-- <fwb-table-head-cell>Room Id</fwb-table-head-cell> -->
                  <!-- <fwb-table-head-cell>Customer Email</fwb-table-head-cell> -->
                  <fwb-table-head-cell>Employee Email</fwb-table-head-cell>
                  <fwb-table-head-cell>
                  </fwb-table-head-cell>
                </fwb-table-head>
                <fwb-table-body>
                  <fwb-table-row v-for="assignment in assignmentList" :key="assignment.assignmentId">
                    <fwb-table-cell>{{ assignment.assignmentId }}</fwb-table-cell>
                    <fwb-table-cell> {{ assignment["request"].requestId }}</fwb-table-cell>
                    <fwb-table-cell>{{ assignment["request"].description }}</fwb-table-cell>
                    <fwb-table-cell>{{ assignment["request"].isFulfilled }}</fwb-table-cell>
                    <fwb-table-cell>{{ assignment["request"]["booking"].bookingId }}</fwb-table-cell>
                    <!-- <fwb-table-cell>{{ assignment["request"]["booking"]["room"].roomId }}</fwb-table-cell> -->
                    <!-- <fwb-table-cell>{{ assignment["request"]["booking"]["generalUser"].email }}</fwb-table-cell> -->
                    <fwb-table-cell>{{ assignment["employee"].email }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>

            <div>
              <fwb-badge type="green">Create Assignment</fwb-badge>
              <fwb-input v-model="messageAssignmentEmployeeId" size="md" label="Employee's Email"
                placeholder="lucas@mar1hotel.com" />
              <fwb-input v-model="messageAssignmentRequestId" size="md" label="Request ID" placeholder="42" />
              <fwb-button @click="createAssignment(messageAssignmentEmployeeId, parseInt(messageAssignmentEmployeeId))"
                color="green">Create Assignment</fwb-button>
            </div>

            <div>
              <fwb-badge type="red">Delete Assignment</fwb-badge>
              <fwb-input v-model="messageAssignment" size="md" label="Assignment ID to be deleted" placeholder="38" />
              <fwb-button @click="deleteAssignment(parseInt(messageAssignment))" color="red">Delete</fwb-button>
            </div>
          </main>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Booking Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Manage Booking</fwb-accordion-header>
        <fwb-accordion-content>
          <div class="accordion-content">
            <ManageBooking />
          </div>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Hotel Schedule Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Hotel Schedule</fwb-accordion-header>
        <fwb-accordion-content>
          <div>
            <HotelSchedule />
          </div>
        </fwb-accordion-content>
      </fwb-accordion-panel>

      <!-- Shift Panel -->
      <fwb-accordion-panel>
        <fwb-accordion-header>Shifts</fwb-accordion-header>
        <fwb-accordion-content>
          <main class="accordion-content">
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
                  <fwb-table-row v-for="shift in shiftList" :key="shift.shiftId">
                    <fwb-table-cell> {{ shift.shiftId }}</fwb-table-cell>
                    <fwb-table-cell> {{ shift.date }}</fwb-table-cell>
                    <fwb-table-cell>{{ shift.startTime }}</fwb-table-cell>
                    <fwb-table-cell>{{ shift.endTime }}</fwb-table-cell>
                    <fwb-table-cell>{{ shift["employee"].email }}</fwb-table-cell>
                    <fwb-table-cell>{{ shift["employee"].hoursWorked }}</fwb-table-cell>
                    <fwb-table-cell>
                    </fwb-table-cell>
                  </fwb-table-row>
                </fwb-table-body>
              </fwb-table>
            </div>
            <div>
              <fwb-badge type="red">Delete Shift</fwb-badge>
              <fwb-input v-model="shiftIdDelete" size="md" label="Enter Shift Id to Delete Specific Shift"
                placeholder="Input Shift Id to Delete Specific Shift..." />
              <fwb-button @click="deleteEmployeeShift(parseInt(shiftIdDelete))" color="red">Delete Employee
                Shift</fwb-button>
            </div>
            <div>
              <fwb-badge type="green">Create Employee Shift</fwb-badge>
              <fwb-input v-model="shiftDate" size="md" label="Enter Employee Shift Date YYYY-MM-DD"
                placeholder="Input Employee Shift Date YYYY-MM-DD..." />
              <fwb-input v-model="shiftStartTime" size="md" label="Enter Employee Shift Start Time"
                placeholder="Input Employee Shift Start Time..." />

              <fwb-input v-model="shiftEndTime" size="md" label="Enter Employee Shift End Time"
                placeholder="Input Employee Shift End Time..." />
              <fwb-input v-model="shiftEmail" size="md" label="Enter Employee Email"
                placeholder="Input employee email..." />
              <fwb-button @click="createEmployeeShift(shiftDate, parseInt(shiftStartTime),
                parseInt(shiftEndTime), shiftEmail)" color="green">Create Employee Shift</fwb-button>
            </div>
          </main>
        </fwb-accordion-content>
      </fwb-accordion-panel>
    </fwb-accordion>
  </div>
</template>
