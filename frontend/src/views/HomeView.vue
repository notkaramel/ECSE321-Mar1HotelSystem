<template>
  <main>
    <!--Welcome banner-->
    <div class="flex flex-col items-center py-16">
      <br><br>
      <p class="text-5xl font-bold mb-4">{{ welcomeMsg }}</p>
      <fwb-rating :rating="4">
        <template #besideText>
          <p class="ml-2 text-lg font-medium text-gray-500 dark:text-gray-400">
            4 out of 5
          </p>
        </template>
      </fwb-rating>
      <br><br>
    </div>

    <!--Image carousel-->
    <fwb-carousel :pictures="pictures" />
    <br><br> 

    <!--Spa&Amenities-->
    <section class="alternate-section">
      <div class="section-content">
        <br><br>
        <h2 class="section-header">Spa & Amenities</h2>
        <p class="section-description">Elevate your stay with our Spa & Amenities package, offering personalized spa treatments and exclusive access to state-of-the-art facilities, ensuring a relaxing experience. Indulge in a range of services, from soothing massages from expert massage therapist Bilar Mokhtari to revitalizing skincare from ethetician Lucas Pacicco. We are dedicated to your well-being.</p>
      </div>
      <div class="section-image">
        <img src="src/assets/spa.png" alt="Spa Image" class="image" />
      </div>
    </section>

    <!--Dining-->
    <section>
      <div class="section-image">
        <img src="src/assets/restaurant.png" alt="Dining Image" class="image" />
      </div>
      <div class="section-content">
        <br><br>
        <h2 class="section-header">Dining</h2>
        <p class="section-description">Experience culinary excellence with our Dining services at 3 Michelin Star Chef Antoine Phan and Sous Chef ZiXu Liu, where every meal is a journey of flavors. Enjoy a diverse menu featuring locally sourced ingredients, ensuring a fresh and delightful dining experience. Whether it's a gourmet dinner or a casual brunch, our commitment to quality and taste will make every meal memorable.</p>
      </div>
    </section>

    <!--Local Area-->
    <section class="alternate-section">
      <div class="section-content">
        <br>
        <h2 class="section-header">Local Area</h2>
        <p class="section-description">Immerse yourself in the vibrant culture of Montreal by consulting with our expert consierge Adam Corbier, who can curate tours and provide insider recommendations to explore the city's hidden gems. From iconic landmarks to trendy neighborhoods, our concierge service ensures you discover the best of Montreal, providing an authentic and tailored experience to your preferences.</p>
      </div>
      <div class="section-image">
        <img src="src/assets/local-area.png" alt="Local Area Image" class="image" />
      </div>
    </section>

    <!--Schedule-->
    <section>
      <div class="section-image">
        <img src="src/assets/contact us.png" alt="Schedule Image" class="image" />
      </div>
      <div class="section-content">
        <br><br>
        <h2 class="section-header">Schedule</h2>
        <p class="section-description">Check out our daily schedule for front desk and consierge opening hours.<br>
          <br>
        Weekdays: 8am-8pm<br>
        Weekends: 9am-8pm<br>
        Honidays: 9am-4pm<br>
    </p>
      </div>
    </section>

  </main>
</template>

<script setup lang="ts">
// import rating component
import { FwbRating } from 'flowbite-vue'

// import carousel component
import { FwbCarousel } from 'flowbite-vue'

// import carousel component's images
const pictures = [
  {src: 'src/assets/hotel-1.png', alt: 'Image 1'},
  {src: 'src/assets/hotel-2.png', alt: 'Image 2'},
  {src: 'src/assets/hotel-3.png', alt: 'Image 3'},
  {src: 'src/assets/hotel-4.png', alt: 'Image 4'},
  {src: 'src/assets/hotel-5.png', alt: 'Image 5'},
  {src: 'src/assets/hotel-6.png', alt: 'Image 6'},
  {src: 'src/assets/hotel-7.png', alt: 'Image 7'},
  {src: 'src/assets/hotel-8.png', alt: 'Image 8'},
  {src: 'src/assets/hotel-9.png', alt: 'Image 9'}]

</script>

<script lang="ts">
  import axios from 'axios'

  const backendUrl = import.meta.env.VITE_BACKEND;

  let welcomeMsg = await axios.get(backendUrl).then((response) => response.data);

  export default {
    data() {
      return {
        welcomeMsg: welcomeMsg,
      };
    }
  }
</script>

<style scoped>
/* Global Style */
body {
  margin: 0;
  font-family: 'Arial', sans-serif;
}

/* Header Style */
header {
  text-align: center;
  margin-bottom: 20px;
}
.header-title {
  font-size: 2em;
}

/* Section Style */
section {
  display: flex;
  margin-bottom: 40px;
}

.section-content,
.section-image {
  flex: 1;
  margin: 0 20px;
  justify-content: flex-start; 
  align-items: flex-start;
}
.section-content {
  text-align: left;
}
.section-header {
  font-size: 1.8em; 
  margin-bottom: 10px;
  color: #333; 
}
.section-description {
  font-size: 1.4em; 
  color: #333; 
}

/* Image Style */
.image {
  max-width: 100%;
  height: auto;
  margin-top: 20px;
}
</style>

