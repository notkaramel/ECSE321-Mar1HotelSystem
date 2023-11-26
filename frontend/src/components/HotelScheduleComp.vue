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

  import { ref } from 'vue'
  import { FwbTextarea } from 'flowbite-vue'
  import { FwbButton } from 'flowbite-vue'
  import { FwbBadge } from 'flowbite-vue'

  const messageDayOfWeek = ref('')
  const messageOpeningHour = ref('')
  const messageClosingHour  = ref('')
  const messageDate = ref('')
  const messageCustomOpeningHour = ref('')
  const messageCustomClosingHour  = ref('')
  const messageYear = ref('')
  const messageOperatingHour = ref('')
  const messageCustomHour  = ref('')
  const messageDeleteOperatingHours = ref('')
  const messageDeleteCustomHours = ref('')
  const messageDeleteOperatingHoursId = ref('')
  const messageDeleteCustomHoursId = ref('')

  const messageUpdateDayOfWeek = ref('')
  const messageUpdateOpeningHour = ref('')
  const messageUpdateClosingHour  = ref('')
  const messageUpdateDate = ref('')
  const messageUpdateCustomOpeningHour = ref('')
  const messageUpdateCustomClosingHour  = ref('')

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
        },
        async ceateOperatingHours(dayOfWeek:String, openingHour: Number, closingHour: Number){
            const backend = import.meta.env.VITE_BACKEND;
            let operatingHoursInfo = await axios.post(backend + '/operatingHours/create', { "dayOfWeek": dayOfWeek, "openingHour": openingHour,
                "closingHour": closingHour
                        })
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const operatingHoursList = data["operatingHoursList"];
                    console.log(operatingHoursList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.operatingHours = operatingHoursList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(operatingHoursInfo);
            return {
                operatingHoursInfo
            }
        },

        async ceateCustomHours(date:String, openingHour: Number, closingHour: Number){
            const backend = import.meta.env.VITE_BACKEND;
            let customHoursInfo = await axios.post(backend + '/customHours/create', { "date": date, "openingHour": openingHour,
                "closingHour": closingHour
                        })
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const customHoursList = data["customHoursList"];
                    console.log(customHoursList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.customHours = customHoursList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(customHoursInfo);
            return {
                customHoursInfo
            }
        },

        async ceateHotelSchedule(year: Number, openingHoursId: Number, closingHoursId: Number){
            const backend = import.meta.env.VITE_BACKEND;
            let openingHoursList = new Array()
            openingHoursList[0] = openingHoursId;
            let closingHoursList = new Array()
            closingHoursList[0] = closingHoursId;
            let hotelScheduleInfo = await axios.post(backend + '/customHours/create', { "year": year, "openingHoursList": openingHoursList,
                "closingHoursList": closingHoursList
                        })
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const hotelScheduleList = data["allHoteSchedule"];
                    console.log(hotelScheduleList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.hotelSchedule = hotelScheduleList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(hotelScheduleInfo);
            return {
                hotelScheduleInfo
            }
            
        },

        async deleteOperatingHours(dayOfWeek:String){
            const backend = import.meta.env.VITE_BACKEND;
            let operatingHoursInfo = await axios.delete(backend + '/operatingHours/delete/'+ dayOfWeek)
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const operatingHoursList = data["operatingHoursList"];
                    console.log(operatingHoursList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.operatingHours = operatingHoursList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(operatingHoursInfo);
            return {
                operatingHoursInfo
            }
        },
        async deleteCustomHours(date:String){
            const backend = import.meta.env.VITE_BACKEND;
            let customHoursInfo = await axios.post(backend + '/customHours/delete'+ date)
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const customHoursList = data["customHoursList"];
                    console.log(customHoursList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.customHours = customHoursList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(customHoursInfo);
            return {
                customHoursInfo
            }
        },

        async updateOperatingHours(dayOfWeek:String, openingHour: Number, closingHour: Number){
            const backend = import.meta.env.VITE_BACKEND;
            let operatingHoursInfo = await axios.post(backend + '/operatingHours/update/', {"dayOfWeek": dayOfWeek, "openingHour": openingHour,
                "closingHour": closingHour})
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const operatingHoursList = data["operatingHoursList"];
                    console.log(operatingHoursList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.operatingHours = operatingHoursList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(operatingHoursInfo);
            return {
                operatingHoursInfo
            }
        },

        async updateCustomHours(date:String, openingHour: Number, closingHour: Number){

            const backend = import.meta.env.VITE_BACKEND;
            let customHoursInfo = await axios.post(backend + '/customHours/udpate', {"date": date, "openingHour": openingHour,
                "closingHour": closingHour})
                .then(response => {
                    // console.log(response);
                    return response.data;
                })
                .then(data => {
                    const customHoursList = data["customHoursList"];
                    console.log(customHoursList);
                   // this.operatingHours.push(operatingHoursInfo)
                    this.customHours = customHoursList;
                    return data;
                })
                .catch(error => {
                    console.log(error);
                });
                console.log(customHoursInfo);
            return {
                customHoursInfo
            }
        },
    },
    data() {
        // Declear data object schemas (DTOs)
        const OperatingHoursDTO = {
            operatingHoursId: Number,
            dayOfWeek: String,
            openingHour: Number,
            closingHour: Number,
        };
        const allOperatingHoursDTO = [OperatingHoursDTO];
        const CustomHoursDTO = {
            customHoursId: Number,
            date: String,
            openingHour: Number,
            closingHour: Number,
        };
        const allCustomHoursDTO = [CustomHoursDTO];
        const HotelScheduleDTO = {
            year: Number,
  operatingHoursList: [
    {
      operatingHoursId: Number,
      openingHour: Number,
      closingHour: Number,
      dayOfWeek: String
    }
  ],
  customHoursList: [
    {
      customHoursId: Number,
      date: String,
      openingHour: Number,
      closingHour: Number
    }
  ]
        };

        const allHotelScheduleDTO = [HotelScheduleDTO];
        

        return {
            operatingHours: allOperatingHoursDTO,
            customHours: allCustomHoursDTO,
            hotelSchedule: allHotelScheduleDTO,
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
    <fwb-badge type="default">View Operating Hours</fwb-badge>
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
  <fwb-textarea
            v-model="messageDeleteOperatingHours"
            :rows="2"
            label="Delete operating hours"
            placeholder="Input operating hours day of wekk of operating hours of day you want to delete..."
            />
            <fwb-button @click="deleteOperatingHours(messageDeleteOperatingHours)" color="red">Delete</fwb-button>
</div>
<div>
    <fwb-badge type="green">Create Operating Hours</fwb-badge>
    <fwb-textarea
        v-model="messageDayOfWeek"
        :rows="2"
        label="Enter day of week"
        placeholder="Input day of week..."
        />

        <fwb-textarea
        v-model="messageOpeningHour"
        :rows="2"
        label="Enter opening hour"
        placeholder="Input opening hour..."
        />

        <fwb-textarea
        v-model="messageClosingHour"
        :rows="2"
        label="Enter closing hour"
        placeholder="Input closing hour..."
        />
        
        <fwb-button @click="ceateOperatingHours(messageDayOfWeek, parseInt(messageOpeningHour), parseInt(messageClosingHour))" color="green">Create Operating Hours</fwb-button>

        <fwb-badge type="green">Update Operating Hours</fwb-badge>
        <fwb-textarea
        v-model="messageUpdateDayOfWeek"
        :rows="2"
        label="Enter day of week"
        placeholder="Input day of week..."
        />

        <fwb-textarea
        v-model="messageUpdateOpeningHour"
        :rows="2"
        label="Enter opening hour"
        placeholder="Input opening hour..."
        />

        <fwb-textarea
        v-model="messageUpdateClosingHour"
        :rows="2"
        label="Enter closing hour"
        placeholder="Input closing hour..."
        />
        
        <fwb-button @click="ceateOperatingHours(messageUpdateDayOfWeek, parseInt(messageUpdateOpeningHour), parseInt(messageUpdateClosingHour))" color="green">Udpate Operating Hours</fwb-button>
</div>
</main>

<main class="flex flex-row items-center-top">
    <div>
    <fwb-badge type="default">View Custom Hours</fwb-badge>
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
  <fwb-textarea
            v-model="messageDeleteCustomHours"
            :rows="2"
            label="Delete operating hours"
            placeholder="Input operating hours day of wekk of operating hours of day you want to delete..."
            />
            <fwb-button @click="deleteCustomHours(messageDeleteCustomHours)" color="red">Delete</fwb-button>
</div>
<div>
    <fwb-badge type="green">Create Custom Hours</fwb-badge>
    <fwb-textarea
        v-model="messageDate"
        :rows="2"
        label="Enter date YYYY-MM-DD"
        placeholder="Input day of week..."
        />

        <fwb-textarea
        v-model="messageCustomOpeningHour"
        :rows="2"
        label="Enter opening hour"
        placeholder="Input opening hour..."
        />

        <fwb-textarea
        v-model="messageCustomClosingHour"
        :rows="2"
        label="Enter closing hour"
        placeholder="Input closing hour..."
        />
        
        <fwb-button @click="ceateCustomHours(messageDate, parseInt(messageCustomOpeningHour), parseInt(messageCustomClosingHour))" color="green">Create Custom Hours</fwb-button>

        <fwb-badge type="green">Update Custom Hours</fwb-badge>
        <fwb-textarea
        v-model="messageUpdateDate"
        :rows="2"
        label="Enter date YYYY-MM-DD"
        placeholder="Input day of week..."
        />

        <fwb-textarea
        v-model="messageUpdateCustomOpeningHour"
        :rows="2"
        label="Enter opening hour"
        placeholder="Input opening hour..."
        />

        <fwb-textarea
        v-model="messageUpdateCustomClosingHour"
        :rows="2"
        label="Enter closing hour"
        placeholder="Input closing hour..."
        />
        
        <fwb-button @click="updateCustomHours(messageUpdateDate, parseInt(messageUpdateCustomOpeningHour), parseInt(messageUpdateCustomClosingHour))" color="green">Update Custom Hours</fwb-button>
</div>
</main>

<main class="flex flex-row items-center-top">
    <div>
    <fwb-badge type="default">View Hotel Schedule</fwb-badge>
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
        <!-- <fwb-table-cell v-for="operating in hotelSchedule["operatingHoursList"]">{{hotel["operatingHoursList"]}}</fwb-table-cell> -->
        <!-- <fwb-table-cell v-for="operating in hotel["operatingHoursList"]">{{operating.dayOfWeek}}</fwb-table-cell>
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
<main class="flex flex-row items-center-top">
    <div>
    <fwb-badge type="green">Create Hotel Schedule</fwb-badge>
    <fwb-textarea
        v-model="messageYear"
        :rows="2"
        label="Enter Year"
        placeholder="Input year..."
        />

        <fwb-textarea
        v-model="messageOperatingHour"
        :rows="2"
        label="Enter operating hours id"
        placeholder="Input opening hour..."
        />

        <fwb-textarea
        v-model="messageCustomHour"
        :rows="2"
        label="Enter custom hours id"
        placeholder="Input closing hour..."
        />
        
        <fwb-button @click="ceateHotelSchedule(parseInt(messageYear), parseInt(messageOperatingHour), parseInt(messageCustomHour))" color="green">Create Hotel Schedule</fwb-button>
</div>
</main>

</template>