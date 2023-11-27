<script setup lang="ts">
// Imports and message from textbox
import {
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
const messageClosingHour = ref('')
const messageDate = ref('')
const messageCustomOpeningHour = ref('')
const messageCustomClosingHour = ref('')
const messageYear = ref('')
const messageOperatingHour = ref('')
const messageCustomHour = ref('')
const messageDeleteOperatingHours = ref('')
const messageDeleteCustomHours = ref('')

const messageUpdateDayOfWeek = ref('')
const messageUpdateOpeningHour = ref('')
const messageUpdateClosingHour = ref('')
const messageUpdateDate = ref('')
const messageUpdateCustomOpeningHour = ref('')
const messageUpdateCustomClosingHour = ref('')

</script>

<script lang="ts">
import axios from 'axios'
export default {
  name: 'ManagerView',
  methods: {
    // Get Data
    async setup_hotelSchedule() {
      const backend = import.meta.env.VITE_BACKEND;
      let hotelScheduleInfo = await fetch(backend + '/hotelSchedule')
        .then(response => {
          return response.json();
        })
        .then(json => {
          const hotelScheduleList = json["allHotelSchedule"];
          this.hotelSchedule = hotelScheduleList;
          return json;
        })
        .catch(error => {
          console.log(error);
        });
      return {
        hotelScheduleInfo
      }
    },

    async setup_customHours() {
      const backend = import.meta.env.VITE_BACKEND;
      let customHoursInfo = await fetch(backend + '/customHours')
        .then(response => {
          return response.json();
        })
        .then(json => {
          const customHoursList = json["allCustomHours"];
          this.customHours = customHoursList;
          return json;
        })
        .catch(error => {
          console.log(error);
        });
      return {
        customHoursInfo
      }
    },

    async setup_operatingHours() {
      const backend = import.meta.env.VITE_BACKEND;
      let operatingHoursInfo = await fetch(backend + '/operatingHours')
        .then(response => {
          return response.json();
        })
        .then(json => {
          const operatingHoursList = json["operatingHoursList"];
          this.operatingHours = operatingHoursList;
          return json;

        })
        .catch(error => {
          console.log(error);
        });
      return {
        operatingHoursInfo
      }
    },

    // Functions for endpoints used
    async ceateOperatingHours(dayOfWeek: String, openingHour: Number, closingHour: Number) {
      const backend = import.meta.env.VITE_BACKEND;
      let operatingHoursInfo = await axios.post(backend + '/operatingHours/create', {
        "dayOfWeek": dayOfWeek, "openingHour": openingHour,
        "closingHour": closingHour
      })
        .then(response => {
          return response.data;
        })
        .then(data => {
          const operatingHoursList = data["operatingHoursList"];
          this.operatingHours = operatingHoursList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
      return {
        operatingHoursInfo
      }
    },

    async ceateCustomHours(date: String, openingHour: Number, closingHour: Number) {
      const backend = import.meta.env.VITE_BACKEND;
      let customHoursInfo = await axios.post(backend + '/customHours/create', {
        "date": date, "openingHour": openingHour,
        "closingHour": closingHour
      })
        .then(response => {
          return response.data;
        })
        .then(data => {
          const customHoursList = data["customHoursList"];
          this.customHours = customHoursList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
      return {
        customHoursInfo
      }
    },

    async ceateHotelSchedule(year: Number, openingHoursId: Number, closingHoursId: Number) {
      const backend = import.meta.env.VITE_BACKEND;
      let openingHoursList = new Array()
      openingHoursList[0] = openingHoursId;
      let closingHoursList = new Array()
      closingHoursList[0] = closingHoursId;
      let hotelScheduleInfo = await axios.post(backend + '/customHours/create', {
        "year": year, "openingHoursList": openingHoursList,
        "closingHoursList": closingHoursList
      })
        .then(response => {
          return response.data;
        })
        .then(data => {
          const hotelScheduleList = data["allHoteSchedule"];
          this.hotelSchedule = hotelScheduleList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
      return {
        hotelScheduleInfo
      }

    },

    async deleteOperatingHours(dayOfWeek: String) {
      const backend = import.meta.env.VITE_BACKEND;
      let operatingHoursInfo = await axios.delete(backend + '/operatingHours/delete/' + dayOfWeek)
        .then(response => {
          return response.data;
        })
        .then(data => {
          const operatingHoursList = data["operatingHoursList"];
          this.operatingHours = operatingHoursList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
      return {
        operatingHoursInfo
      }
    },
    async deleteCustomHours(date: String) {
      const backend = import.meta.env.VITE_BACKEND;
      let customHoursInfo = await axios.post(backend + '/customHours/delete/' + date)
        .then(response => {
          return response.data;
        })
        .then(data => {
          const customHoursList = data["customHoursList"];
          this.customHours = customHoursList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
      return {
        customHoursInfo
      }
    },

    async updateOperatingHours(dayOfWeek: String, openingHour: Number, closingHour: Number) {
      const backend = import.meta.env.VITE_BACKEND;
      let operatingHoursInfo = await axios.post(backend + '/operatingHours/update/', {
        "dayOfWeek": dayOfWeek, "openingHour": openingHour,
        "closingHour": closingHour
      })
        .then(response => {
          return response.data;
        })
        .then(data => {
          const operatingHoursList = data["operatingHoursList"];
          this.operatingHours = operatingHoursList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
      return {
        operatingHoursInfo
      }
    },

    async updateCustomHours(date: String, openingHour: Number, closingHour: Number) {

      const backend = import.meta.env.VITE_BACKEND;
      let customHoursInfo = await axios.post(backend + '/customHours/update', {
        "date": date, "openingHour": openingHour,
        "closingHour": closingHour
      })
        .then(response => {
          return response.data;
        })
        .then(data => {
          const customHoursList = data["customHoursList"];
          this.customHours = customHoursList;
          return data;
        })
        .catch(error => {
          console.log(error);
          alert(error.response["data"])
        });
      window.location.reload();
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

<style scoped lang="postcss">
.accordion-content {
  @apply flex flex-row items-start justify-center gap-12 py-4;
}
</style>

<template>
  <!-- Hotel Schedule view flowbite for components -->
  <main class="accordion-content">
    <div class="p-2">
      <fwb-badge type="default">View Operating Hours</fwb-badge>
      <fwb-table>
        <fwb-table-head>
          <fwb-table-head-cell>Operating Hours Id</fwb-table-head-cell>
          <fwb-table-head-cell>Day Of Week</fwb-table-head-cell>
          <fwb-table-head-cell>Opening Hour</fwb-table-head-cell>
          <fwb-table-head-cell>Closing Hour</fwb-table-head-cell>
        </fwb-table-head>
        <fwb-table-body>
          <fwb-table-row v-for="operating in operatingHours" :key="operating.operatingHoursId">
            <fwb-table-cell>{{ operating.operatingHoursId }} </fwb-table-cell>
            <fwb-table-cell>{{ operating.dayOfWeek }}</fwb-table-cell>
            <fwb-table-cell>{{ operating.openingHour }}</fwb-table-cell>
            <fwb-table-cell>{{ operating.closingHour }}</fwb-table-cell>
          </fwb-table-row>
        </fwb-table-body>
      </fwb-table>
      <fwb-badge type="red">Delete Operating Hours</fwb-badge>
      <fwb-textarea v-model="messageDeleteOperatingHours" :rows="2" label="Operating Hours ID:"
        placeholder="Input operating hours day of week of operating hours of day you want to delete..." />
      <fwb-button @click="deleteOperatingHours(messageDeleteOperatingHours)" color="red">Delete</fwb-button>
    </div>

    <div class="p-2">
      <fwb-badge type="green">Create Operating Hours</fwb-badge>
      <fwb-textarea v-model="messageDayOfWeek" :rows="2" label="Enter day of week" placeholder="Input day of week..." />

      <fwb-textarea v-model="messageOpeningHour" :rows="2" label="Enter opening hour"
        placeholder="Input opening hour..." />

      <fwb-textarea v-model="messageClosingHour" :rows="2" label="Enter closing hour"
        placeholder="Input closing hour..." />

      <fwb-button
        @click="ceateOperatingHours(messageDayOfWeek, parseInt(messageOpeningHour), parseInt(messageClosingHour))"
        color="green">Create Operating Hours</fwb-button>

      <fwb-badge type="green">Update Operating Hours</fwb-badge>
      <fwb-textarea v-model="messageUpdateDayOfWeek" :rows="2" label="Enter day of week"
        placeholder="Input day of week..." />

      <fwb-textarea v-model="messageUpdateOpeningHour" :rows="2" label="Enter opening hour"
        placeholder="Input opening hour..." />

      <fwb-textarea v-model="messageUpdateClosingHour" :rows="2" label="Enter closing hour"
        placeholder="Input closing hour..." />

      <fwb-button
        @click="ceateOperatingHours(messageUpdateDayOfWeek, parseInt(messageUpdateOpeningHour), parseInt(messageUpdateClosingHour))"
        color="green">Udpate Operating Hours</fwb-button>
    </div>
  </main>

  <main class="accordion-content">
    <div class="p-2">
      <fwb-badge type="default">View Custom Hours</fwb-badge>
      <fwb-table>
        <fwb-table-head>
          <fwb-table-head-cell>Custom Hours Id</fwb-table-head-cell>
          <fwb-table-head-cell>Date</fwb-table-head-cell>
          <fwb-table-head-cell>Opening Hour</fwb-table-head-cell>
          <fwb-table-head-cell>Closing Hour</fwb-table-head-cell>
        </fwb-table-head>
        <fwb-table-body>
          <fwb-table-row v-for="custom in customHours" :key="custom.customHoursId">
            <fwb-table-cell>{{ custom.customHoursId }}</fwb-table-cell>
            <fwb-table-cell>{{ custom.date }}</fwb-table-cell>
            <fwb-table-cell>{{ custom.openingHour }}</fwb-table-cell>
            <fwb-table-cell>{{ custom.closingHour }}</fwb-table-cell>
          </fwb-table-row>
        </fwb-table-body>
      </fwb-table>
      <fwb-badge type="red">Delete Custom Hours</fwb-badge>
      <fwb-textarea v-model="messageDeleteCustomHours" :rows="2" label="Custom Hours ID:"
        placeholder="Input custom hours data (YYYY-MM-DDTHH:MMM...can copy date from table) of custom hours of date you want to delete..." />
      <fwb-button @click="deleteCustomHours(messageDeleteCustomHours)" color="red">Delete</fwb-button>
    </div>
    <div class="p-2">
      <fwb-badge type="green">Create Custom Hours</fwb-badge>
      <fwb-textarea v-model="messageDate" :rows="2" label="Enter date YYYY-MM-DD" placeholder="Input day of week..." />

      <fwb-textarea v-model="messageCustomOpeningHour" :rows="2" label="Enter opening hour"
        placeholder="Input opening hour..." />

      <fwb-textarea v-model="messageCustomClosingHour" :rows="2" label="Enter closing hour"
        placeholder="Input closing hour..." />

      <fwb-button
        @click="ceateCustomHours(messageDate, parseInt(messageCustomOpeningHour), parseInt(messageCustomClosingHour))"
        color="green">Create Custom Hours</fwb-button>

      <fwb-badge type="green">Update Custom Hours</fwb-badge>
      <fwb-textarea v-model="messageUpdateDate" :rows="2"
        label="Enter date YYYY-MM-DDTHH:MM:... (you can copy the date of custom hour you want to change)"
        placeholder="Input day of week..." />

      <fwb-textarea v-model="messageUpdateCustomOpeningHour" :rows="2" label="Enter opening hour"
        placeholder="Input opening hour..." />

      <fwb-textarea v-model="messageUpdateCustomClosingHour" :rows="2" label="Enter closing hour"
        placeholder="Input closing hour..." />

      <fwb-button
        @click="updateCustomHours(messageUpdateDate, parseInt(messageUpdateCustomOpeningHour), parseInt(messageUpdateCustomClosingHour))"
        color="green">Update Custom Hours</fwb-button>
    </div>
  </main>

  <main class="accordion-content flex-col">
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
          <fwb-table-head-cell>Custom Hours Date</fwb-table-head-cell>
          <fwb-table-head-cell>Custom Opening Hour</fwb-table-head-cell>
          <fwb-table-head-cell>Closing Hour</fwb-table-head-cell>
        </fwb-table-head>
        <fwb-table-body>
          <fwb-table-row v-for="hotel in hotelSchedule" :key="hotel.year">
            <fwb-table-cell>{{ hotel.year }}</fwb-table-cell>
            <fwb-table-cell v-for="operating in hotel.operatingHoursList" :key="operating.operatingHoursId">{{ operating.operatingHoursId
            }}</fwb-table-cell>
            <fwb-table-cell v-for="operating in hotel.operatingHoursList" :key="operating.operatingHoursId">{{ operating.dayOfWeek }}</fwb-table-cell>
            <fwb-table-cell v-for="operating in hotel.operatingHoursList" :key="operating.operatingHoursId">{{ operating.openingHour }}</fwb-table-cell>
            <fwb-table-cell v-for="operating in hotel.operatingHoursList" :key="operating.operatingHoursId">{{ operating.closingHour }}</fwb-table-cell>
            <fwb-table-cell v-for="custom in hotel.customHoursList" :key="custom.customHoursId">{{ custom.customHoursId }}</fwb-table-cell>
            <fwb-table-cell v-for="custom in hotel.customHoursList" :key="custom.customHoursId">{{ custom.date }}</fwb-table-cell>
            <fwb-table-cell v-for="custom in hotel.customHoursList" :key="custom.customHoursId">{{ custom.openingHour }}</fwb-table-cell>
            <fwb-table-cell v-for="custom in hotel.customHoursList" :key="custom.customHoursId">{{ custom.closingHour }}</fwb-table-cell>
          </fwb-table-row>
        </fwb-table-body>
      </fwb-table>
    </div>

    <div>
      <fwb-badge type="green">Create Hotel Schedule</fwb-badge>
      <fwb-textarea v-model="messageYear" :rows="2" label="Enter Year" placeholder="Input year..." />

      <fwb-textarea v-model="messageOperatingHour" :rows="2" label="Enter operating hours id"
        placeholder="Input opening hour..." />

      <fwb-textarea v-model="messageCustomHour" :rows="2" label="Enter custom hours id"
        placeholder="Input closing hour..." />

      <fwb-button
        @click="ceateHotelSchedule(parseInt(messageYear), parseInt(messageOperatingHour), parseInt(messageCustomHour))"
        color="green">Create Hotel Schedule</fwb-button>
    </div>
  </main>
</template>