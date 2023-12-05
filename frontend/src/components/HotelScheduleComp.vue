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
import { FwbInput } from 'flowbite-vue'
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
    async rerender() {
      this.setup_operatingHours();
      this.setup_customHours();
      this.setup_hotelSchedule();
    },
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
    async createOperatingHours(dayOfWeek: String, openingHour: Number, closingHour: Number) {
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
      // window.location.reload();
      this.rerender();
      return {
        operatingHoursInfo
      }
    },

    async createCustomHours(date: String, openingHour: Number, closingHour: Number) {
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
      // window.location.reload();
      this.rerender();

      return {
        customHoursInfo
      }
    },

    async createHotelSchedule(year: Number, openingHoursId: Number, closingHoursId: Number) {
      const backend = import.meta.env.VITE_BACKEND;
      let openingHoursList = new Array()
      openingHoursList[0] = openingHoursId;
      let closingHoursList = new Array()
      closingHoursList[0] = closingHoursId;
      let hotelScheduleInfo = await axios.post(backend + '/hotelSchedule/create', {
        "year": year,
        "openingHoursList": openingHoursList,
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
      // window.location.reload();
      this.rerender();
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
      // window.location.reload();
      this.rerender();

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
      // window.location.reload();
      this.rerender();

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
      // window.location.reload();
      this.rerender();

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
      // window.location.reload();
      this.rerender();

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
  @apply flex flex-row items-start justify-center gap-2 py-4;
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
      <fwb-input v-model="messageDeleteOperatingHours" size="md" label="Operating Hours ID:"
        placeholder="Input operating hours day of week of operating hours of day you want to delete..." />
      <fwb-button @click="deleteOperatingHours(messageDeleteOperatingHours)" color="red">Delete</fwb-button>
    </div>

    <div class="p-2">
      <fwb-badge type="green">Create Operating Hours</fwb-badge>
      <fwb-input v-model="messageDayOfWeek" size="md" label="Enter day of week" placeholder="Monday" />

      <fwb-input v-model="messageOpeningHour" size="md" label="Enter opening hour" placeholder="9" />

      <fwb-input v-model="messageClosingHour" size="md" label="Enter closing hour" placeholder="18" />

      <fwb-button
        @click="createOperatingHours(messageDayOfWeek, parseInt(messageOpeningHour), parseInt(messageClosingHour))"
        color="green">Create Operating Hours</fwb-button>

      <fwb-badge type="yellow">Update Operating Hours</fwb-badge>
      <fwb-input v-model="messageUpdateDayOfWeek" size="md" label="Enter day of week" placeholder="Monday" />

      <fwb-input v-model="messageUpdateOpeningHour" size="md" label="Enter opening hour" placeholder="8" />

      <fwb-input v-model="messageUpdateClosingHour" size="md" label="Enter closing hour" placeholder="20" />

      <fwb-button
        @click="createOperatingHours(messageUpdateDayOfWeek, parseInt(messageUpdateOpeningHour), parseInt(messageUpdateClosingHour))"
        color="yellow">Update Operating Hours</fwb-button>
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
      <fwb-input v-model="messageDeleteCustomHours" size="md" label="Custom Hours ID:"
        placeholder="YYYY-MM-DDTHH:MMM (can copy date from table)" />
      <fwb-button @click="deleteCustomHours(messageDeleteCustomHours)" color="red">Delete</fwb-button>
    </div>
    <div class="p-2">
      <fwb-badge type="green">Create Custom Hours</fwb-badge>
      <fwb-input v-model="messageDate" size="md" label="Enter date YYYY-MM-DD" placeholder="2023-12-24" />

      <fwb-input v-model="messageCustomOpeningHour" size="md" label="Enter opening hour" placeholder="8" />

      <fwb-input v-model="messageCustomClosingHour" size="md" label="Enter closing hour" placeholder="14" />

      <fwb-button
        @click="createCustomHours(messageDate, parseInt(messageCustomOpeningHour), parseInt(messageCustomClosingHour))"
        color="green">Create Custom Hours</fwb-button>

      <fwb-badge type="yellow">Update Custom Hours</fwb-badge>
      <fwb-input v-model="messageUpdateDate" size="md"
        label="Enter date YYYY-MM-DDTHH:MM:... (copy the date of custom hour)"
        placeholder="2023-12-24" />

      <fwb-input v-model="messageUpdateCustomOpeningHour" size="md" label="Enter opening hour"
        placeholder="8" />

      <fwb-input v-model="messageUpdateCustomClosingHour" size="md" label="Enter closing hour"
        placeholder="14" />

      <fwb-button
        @click="updateCustomHours(messageUpdateDate, parseInt(messageUpdateCustomOpeningHour), parseInt(messageUpdateCustomClosingHour))"
        color="yellow">Update Custom Hours</fwb-button>
    </div>
  </main>

  <main class="accordion-content gap-12">
    <div>
      <fwb-badge type="default">View Hotel Schedule</fwb-badge>
      <fwb-table>
        <fwb-table-head>
          <fwb-table-head-cell>Year</fwb-table-head-cell>
          <fwb-table-head-cell>OH IDs</fwb-table-head-cell>
          <fwb-table-head-cell>OH Day Of Week</fwb-table-head-cell>
          <fwb-table-head-cell>CH IDs</fwb-table-head-cell>
          <fwb-table-head-cell>CH Date</fwb-table-head-cell>
        </fwb-table-head>
        <fwb-table-body>
          <fwb-table-row v-for="hs in hotelSchedule" :key="hs.year">
            <!-- <fwb-table-cell>{{ hs.year }}</fwb-table-cell> -->
            <fwb-table-cell> 2023 </fwb-table-cell>
            <fwb-table-cell>
              <li v-for="operating in hs.operatingHoursList" :key="operating.operatingHoursId"> {{
                operating.operatingHoursId }}
              </li>
            </fwb-table-cell>
            <fwb-table-cell>
              <li v-for="operating in hs.operatingHoursList" :key="operating.operatingHoursId">
                {{
                  operating.dayOfWeek }}
              </li>
            </fwb-table-cell>
            <!-- <fwb-table-cell>
              <li v-for="operating in hs.operatingHoursList" :key="operating.operatingHoursId">
                {{ operating.openingHour }}
              </li>
            </fwb-table-cell> -->
            <!-- <fwb-table-cell>
              <li v-for="operating in hs.operatingHoursList" :key="operating.operatingHoursId">
              {{ operating.closingHour }}
              </li>
            </fwb-table-cell> -->
            <fwb-table-cell>
              <li v-for="custom in hs.customHoursList" :key="custom.customHoursId">
                {{ custom.customHoursId }}
              </li>
            </fwb-table-cell>
            <fwb-table-cell>
              <li v-for="custom in hs.customHoursList" :key="custom.customHoursId">
                {{ custom.date }}
              </li>
            </fwb-table-cell>
          </fwb-table-row>
        </fwb-table-body>
      </fwb-table>
    </div>

    <div>
      <fwb-badge type="green">Create Hotel Schedule</fwb-badge>
      <fwb-input v-model="messageYear" size="md" label="Enter Year" placeholder="2023" />

      <fwb-input v-model="messageOperatingHour" size="md" label="Enter operating hours id" placeholder="8" />

      <fwb-input v-model="messageCustomHour" size="md" label="Enter custom hours id" placeholder="15" />

      <fwb-button
        @click="createHotelSchedule(parseInt(messageYear), parseInt(messageOperatingHour), parseInt(messageCustomHour))"
        color="green">Create Hotel Schedule</fwb-button>
    </div>
  </main>
</template>