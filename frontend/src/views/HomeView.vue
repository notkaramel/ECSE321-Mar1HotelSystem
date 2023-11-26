<template>
  <main>
    <!--Welcome banner-->
    <div class="flex flex-col items-center py-16">
      <br>
      <p class="text-5xl font-bold mb-4"> Welcome to Mar-1 Hotel! </p>
      <fwb-rating :rating="5">
        <template #besideText>
          <p class="ml-2 text-lg font-medium text-gray-500 dark:text-gray-400">
            4.7 out of 5
          </p>
        </template>
      </fwb-rating>
      <!--Image carousel-->
    </div>
    <br><br>
    <fwb-carousel :pictures="pictures" :slideInterval="2000" :animation="false" slide/>
    <br>
    <!--Spa&Amenities-->
    <div class="alternate-section my-7">
      <div class="section-content">
        <h2 class="section-header">Spa & Amenities</h2>
        <p class="section-description">Elevate your stay with our Spa & Amenities package, offering personalized spa
          treatments and exclusive access to state-of-the-art facilities, ensuring a relaxing experience. Indulge in a
          range of services, from soothing massages from expert massage therapist Bilar Mokhtari to revitalizing skincare
          from ethetician Lucas Pacicco. We are dedicated to your well-being.</p>
      </div>
      <div class="section-image">
        <img src="@/assets/spa.png" alt="Spa Image" class="image" />
      </div>
    </div>

    <!--Dining-->
    <div class="alternate-section">
      <div class="section-image">
        <img src="@/assets/restaurant.png" alt="Dining Image" class="image" />
      </div>
      <div class="section-content">
        <h2 class="section-header">Dining</h2>
        <p class="section-description">Experience culinary excellence with our Dining services at 3 Michelin Star Chef
          Antoine Phan and Sous Chef ZiXu Liu, where every meal is a journey of flavors. Enjoy a diverse menu featuring
          locally sourced ingredients, ensuring a fresh and delightful dining experience. Whether it's a gourmet dinner or
          a casual brunch, our commitment to quality and taste will make every meal memorable.</p>
      </div>
    </div>

    <!--Local Area-->
    <div class="alternate-section">
      <div class="section-content">
        <h2 class="section-header">Local Area</h2>
        <p class="section-description">Immerse yourself in the vibrant culture of Montreal by consulting with our expert
          consierge Adam Corbier, who can curate tours and provide insider recommendations to explore the city's hidden
          gems. From iconic landmarks to trendy neighborhoods, our concierge service ensures you discover the best of
          Montreal, providing an authentic and tailored experience to your preferences.</p>
      </div>
      <div class="section-image">
        <img src="@/assets/local-area.png" alt="Local Area Image" class="image" />
      </div>
    </div>

    <!--Schedule-->
    <div class="alternate-section">
      <div class="section-image">
        <img src="@/assets/contact us.png" alt="Schedule Image" class="image" />
      </div>
      <div class="section-content">
        <h2 class="section-header">Opening Schedule</h2>
        <p class="section-description">Check out our daily schedule for front desk and consierge opening hours.
          <br>
          Weekdays: 8am-8pm<br>
          Weekends: 9am-8pm<br>
          Honidays: 9am-4pm<br>
        </p>
      </div>
    </div>

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

<style scoped lang="postcss">
/* Global Style */
body {
  scroll-behavior: smooth;
  font-family: 'Arial', sans-serif;
}

/* Section Style */
.alternate-section {
  @apply flex flex-row items-center py-6 mx-10 gap-12 align-baseline;
}

.section-content, .section-image {
  @apply flex-1 text-justify;
}
.section-header {
  @apply text-3xl font-bold mb-2;
}

.section-description {
  @apply text-xl text-justify;
}

/* Image Style */
.image {
  max-width: 100%;
}
</style>

