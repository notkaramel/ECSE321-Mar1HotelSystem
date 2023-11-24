<script lang="ts">
  
  import {
    FwbA,
    FwbTable,
    FwbTableBody,
    FwbTableCell,
    FwbTableHead,
    FwbTableHeadCell,
    FwbTableRow,
    FwbCheckbox,
    FwbButton,
  } from 'flowbite-vue'

  import { ref } from 'vue'
  
  
    // import { ref } from 'vue'
    // import { FwbSelect } from 'flowbite-vue'
    
    // const selected = ref('')
    // const Users_Type = [
    // { value: 'All Users', name: 'All Users' },
    // { value: 'employees', name: 'Employees' },
    // { value: 'Customer', name: 'Customer' },
    // ]
    
    
    export default {
    props: {
        email: String,
        firstName: String,
        lastName: String,
        phoneNumber: Number,
        userList: Array
    },
    components: {
    FwbA,
    FwbTable,
    FwbTableBody,
    FwbTableCell,
    FwbTableHead,
    FwbTableHeadCell,
    FwbTableRow,
    FwbCheckbox,
    }
}

let emailList = new Array()
// const emailList = new Array()
function listOfEmails(list:any[]) {
  for (let i = 0; i < list.length; i++) {
    if(list[i].keys == "email"){
        emailList[i] = list[i].value + "<br>";
    }
    
}
}
  </script>

  <script setup lang="ts">
  const deleteEmployee = ref("vue.js")
</script>

<template>
    <!-- <fwb-select
    v-model="selected"
    :options="Users_Type"
    label="Select User Type"
    underline
  /> -->


    <fwb-table hoverable>
      <fwb-table-head>
        <fwb-table-head-cell>Email</fwb-table-head-cell>
        <fwb-table-head-cell>First Name</fwb-table-head-cell>
        <fwb-table-head-cell>Last Name</fwb-table-head-cell>
        <fwb-table-head-cell>Phone Number</fwb-table-head-cell>
        <fwb-table-head-cell>
          <!-- <span class="sr-only">check</span> -->
        </fwb-table-head-cell>
      </fwb-table-head>
      <fwb-table-body>
        <!-- <fwb-table-row v-for="email in listOfEmails(userList)"> -->
        <fwb-table-row>
          <fwb-table-cell> {{email}}</fwb-table-cell>
          <fwb-table-cell>{{firstName}}</fwb-table-cell>
          <fwb-table-cell>{{lastName}}</fwb-table-cell>
          <fwb-table-cell>{{phoneNumber}}</fwb-table-cell>
          <fwb-table-cell>
            <!-- <fwb-checkbox v-model="check"/> -->
            <!-- <fwb-button @click="deleteEmployee" color="red">Delete</fwb-button> -->
          </fwb-table-cell>
        </fwb-table-row>
      </fwb-table-body>
    </fwb-table>
  </template>
  
  
  