<template>
    <div class="text-box-container">
      <input type="text" id="exampleText" v-model="exampleText" placeholder="Type something...">
    </div>
  </template>
  
  <script lang="ts">
  export default {
    data() {
      return {
        exampleText: '', 
      };
    },
  };
  </script>
  
  <style scoped>
  .text-box-container input[type="text"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  </style>
  