<script setup lang="ts">
import {
  FwbA,
  FwbTable,
  FwbTableBody,
  FwbTableCell,
  FwbTableHead,
  FwbTableHeadCell,
  FwbTableRow,
} from 'flowbite-vue'
</script>

<script lang="ts">
import { useRoute } from 'vue-router';

import axios from 'axios'
export default {
    name: 'ManagerView',
    props: {
//         dayOfWeek: String,
//   openingHour: Number,
//   closingHour: Number,
//   year: Number,
//   operatingHoursIdList: [
//     Number
//   ],
//   customHoursIdList: [
//     Number
//   ],
//   date: Date,
    },
    methods: {
        async setup_hotelSchedule() {
        const backend = import.meta.env.VITE_BACKEND;
        let hotelScheduleInfo = await fetch(backend + '/hotelSchedule')
                .then(response => {
                    // console.log(response);
                    return response.json();
                })
                .then(json => {
                    const hotelScheduleList = json["allHotelSchedule"];
                    console.log(hotelScheduleList);
                    this.hotelSchedule = hotelScheduleList;
                    return json;
                })
                .catch(error => {
                    console.log(error);
                });

            console.log(hotelScheduleInfo);
            return {
                hotelScheduleInfo 
            }
        },

        async setup_customHours() {
        const backend = import.meta.env.VITE_BACKEND;
        let customHoursInfo = await fetch(backend + '/customHours')
                .then(response => {
                    // console.log(response);
                    return response.json();
                })
                .then(json => {
                    const customHoursList = json["allCustomHours"];
                    console.log(customHoursList);
                    this.customHours = customHoursList;
                    return json;
                })
                .catch(error => {
                    console.log(error);
                });

            console.log(customHoursInfo);
            return {
                customHoursInfo 
            }
        },
        
        async setup_operatingHours() {
            const backend = import.meta.env.VITE_BACKEND;
            let operatingHoursInfo = await fetch(backend + '/operatingHours')
                .then(response => {
                    // console.log(response);
                    return response.json();
                })
                .then(json => {
                    // console.log(json);
                    // console.log(json["bookingId"]);
                    const operatingHoursList = json["operatingHoursList"];
                    // const operatingHoursId = json["operatingHoursList"].operatingHoursId;
                    // const dayOfWeek = json["operatingHoursList"].dayOfweek;
                    // const openingHour = json["operatingHoursList"].openingHour;
                    // const closingHour = json["operatingHoursList"].closingHour;
                    console.log(operatingHoursList);
                    // console.log(operatingHoursId);
                    // console.log(dayOfWeek);
                    // console.log(openingHour);
                    // console.log(closingHour);

                    this.operatingHours = operatingHoursList;
                    // this.operatingHoursId = operatingHoursId ;
                    // this.dayOfWeek = dayOfWeek;
                    // this.openingHour = openingHour;
                    // this.closingHour = closingHour;
                    return json;

                })
                .catch(error => {
                    console.log(error);
                });

            console.log(operatingHoursInfo);
            return {
                operatingHoursInfo
            }
        }
    },
    data() {
        // Declear data object schemas (DTOs)
        const OperatingHoursDTO = {
            "operatingHoursId": "",
            "dayOfWeek": "",
            "openingHour": "",
            "closingHour": "",
        };

        const CustomHoursDTO = {
            "customHoursId": "",
            "date": "",
            "openingHour": "",
            "closingHour": "",
        };

        const HotelScheduleDTO = {
            "year": "",
  "operatingHoursList": [
    {
      "operatingHoursId": "",
      "openingHour": "",
      "closingHour": "",
      "dayOfWeek": ""
    }
  ],
  "customHoursList": [
    {
      "customHoursId": "",
      "date": "",
      "openingHour": "",
      "closingHour": ""
    }
  ]
        };

        return {
            operatingHours: OperatingHoursDTO,
            customHours: CustomHoursDTO,
            hotelSchedule: HotelScheduleDTO,
        }
    },
    beforeMount() {
        this.setup_operatingHours();
        this.setup_customHours();
        this.setup_hotelSchedule();
    }
    
}
</script>

<template>
<main class="flex flex-row items-center-top">
    <div>
    <fwb-table>
    <fwb-table-head>
      <fwb-table-head-cell>Operating Hours Id</fwb-table-head-cell>
      <fwb-table-head-cell>Day Of Week</fwb-table-head-cell>
      <fwb-table-head-cell>Opening Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Closing Hour</fwb-table-head-cell>
    </fwb-table-head>
    <fwb-table-body>
      <fwb-table-row v-for="operating in operatingHours">
        <fwb-table-cell>{{operating.operatingHoursId}}</fwb-table-cell>
        <fwb-table-cell>{{operating.dayOfWeek}}</fwb-table-cell>
        <fwb-table-cell>{{operating.openingHour}}</fwb-table-cell>
        <fwb-table-cell>{{operating.closingHour}}</fwb-table-cell>   
      </fwb-table-row>
    </fwb-table-body>
  </fwb-table>
</div>

</main>

<main class="flex flex-row items-center-top">
    <div>
    <fwb-table>
    <fwb-table-head>
      <fwb-table-head-cell>Custom Hours Id</fwb-table-head-cell>
      <fwb-table-head-cell>Date</fwb-table-head-cell>
      <fwb-table-head-cell>Opening Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Closing Hour</fwb-table-head-cell>
    </fwb-table-head>
    <fwb-table-body>
      <fwb-table-row v-for="custom in customHours">
        <fwb-table-cell>{{custom.customHoursId}}</fwb-table-cell>
        <fwb-table-cell>{{custom.date}}</fwb-table-cell>
        <fwb-table-cell>{{custom.openingHour}}</fwb-table-cell>
        <fwb-table-cell>{{custom.closingHour}}</fwb-table-cell>   
      </fwb-table-row>
    </fwb-table-body>
  </fwb-table>
</div>

</main>

<main class="flex flex-row items-center-top">
    <div>
    <fwb-table>
    <fwb-table-head>
      <fwb-table-head-cell>Year</fwb-table-head-cell>
      <fwb-table-head-cell>Operating Hours Id</fwb-table-head-cell>
      <fwb-table-head-cell>Day Of Week</fwb-table-head-cell>
      <fwb-table-head-cell>Operating Opening Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Operating Closing Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Custom Hours Id</fwb-table-head-cell>
      <fwb-table-head-cell>Operating Hours Date</fwb-table-head-cell>
      <fwb-table-head-cell>Custom Opening Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Custom Closing Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Custom Hours Id</fwb-table-head-cell>
      <fwb-table-head-cell>Custom Hours Date</fwb-table-head-cell>
      <fwb-table-head-cell>Opening Hour</fwb-table-head-cell>
      <fwb-table-head-cell>Closing Hour</fwb-table-head-cell>
    </fwb-table-head>
    <fwb-table-body>
      <fwb-table-row v-for="hotel in hotelSchedule">
        <fwb-table-cell>{{hotel.year}}</fwb-table-cell>
        <!-- <fwb-table-cell v-for="operating in hotel["operatingHoursList"]">{{operating.operatingHoursId}}</fwb-table-cell>
        <fwb-table-cell v-for="operating in hotel["operatingHoursList"]">{{operating.dayOfWeek}}</fwb-table-cell>
        <fwb-table-cell v-for="operating in hotel["operatingHoursList"]">{{operating.openingHour}}</fwb-table-cell>
        <fwb-table-cell v-for="operating in hotel["operatingHoursList"]">{{operating.closingHour}}</fwb-table-cell>  
        <fwb-table-cell v-for="custom in hotel["customHoursList"]">{{custom.customHoursId}}</fwb-table-cell>
        <fwb-table-cell v-for="custom in hotel["customHoursList"]">{{custom.date}}</fwb-table-cell>
        <fwb-table-cell v-for="custom in hotel["customHoursList"]">{{custom.openingHour}}</fwb-table-cell>
        <fwb-table-cell v-for="custom in hotel["customHoursList"]">{{custom.closingHour}}</fwb-table-cell>    -->
      </fwb-table-row>
    </fwb-table-body>
  </fwb-table>
</div>

</main>

</template>