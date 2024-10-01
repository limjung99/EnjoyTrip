<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { defineComponent } from 'vue';
import { Carousel, Navigation, Pagination, Slide } from 'vue3-carousel';
import { searchHotplaceApi } from "@/api/TripApi";
import 'vue3-carousel/dist/carousel.css';
defineComponent({
  name: 'Autoplay',
  components: {
    Carousel,
    Slide,
    Navigation,
    Pagination,
  },
})
const hotplaceImages = ref([])
onMounted(() => {
  searchHotplaceApi(
    (data) => hotplaceImages.value = data.data

  )
})

</script>
<template>
  <div class="col-6 d-flex flex-column justify-content-center align-items-center vh-100"
    style="width:100; padding: 5%;">
    <h4>실시간 여행지</h4>
    <div class="wrapper">
      <Carousel :autoplay="300" :transition="2000" :wrap-around="true" >
        <Slide v-for="slide in hotplaceImages" :key="slide">
          <div class="carousel__item">
            <img class="slideImg" :src="slide.img" />
          </div>
        </Slide>

        <template #addons>
          <Navigation style="color:orangered;" />
          <Pagination />
        </template>
      </Carousel>
    </div>
  </div>
</template>

<style scoped>
.carousel__item {
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 10px;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 5px solid white;
}

.slideImg {
  max-height: 400px;
  max-width: 100%;
  border-radius: 20%;
}

.wrapper {
  margin-top: 3vh;
}
</style>