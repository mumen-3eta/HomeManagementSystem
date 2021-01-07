<template>
  <div>
    <div class="container__headerSc">
      <div class="headerSc__section">
        <h2 class="headerSc__title">
          Home Management Experience You really want
        </h2>
        <div class="headerSc__carousel">
          <carousel :autoplay="true" :dots="false" :items="1" :nav="false">
            <div class="headerSc__carousel-container">
              <div class="headerSc__carousel-img">
                <img alt="image-1"
                     src="@/assets/img/welcomePage/undraw_Home_settings_re_pkya.svg">
              </div>
              <p class="headerSc__carousel-p">Now you can confidently choose Home Management System , because we are the
                best at what we do.</p>
            </div>
            <div class="headerSc__carousel-container">
              <div class="headerSc__carousel-img">
                <img alt="image-1" class="headerSc__carousel-img"
                     src="@/assets/img/welcomePage/undraw_home_cinema_l7yl.svg">
              </div>
              <p class="headerSc__carousel-p">Now you can confidently choose Home Management System , because we are the
                best at what we do.</p>
            </div>
            <div class="headerSc__carousel-container">
              <div class="headerSc__carousel-img">
                <img alt="image-1" class="headerSc__carousel-img"
                     src="@/assets/img/welcomePage/undraw_build_your__home_csh6.svg">
              </div>
              <p class="headerSc__carousel-p">Now you can confidently choose Home Management System , because we are the
                best at what we do.</p>
            </div>
            <div class="headerSc__carousel-container">
              <div class="headerSc__carousel-img">
                <img alt="image-1" class="headerSc__carousel-img"
                     src="@/assets/img/welcomePage/undraw_software_engineer_lvl5.svg">
              </div>
              <p class="headerSc__carousel-p">Now you can confidently choose Home Management System , because we are the
                best at what we do.</p>
            </div>
            <div class="headerSc__carousel-container">
              <div class="headerSc__carousel-img-v2">
                <img alt="image-1" class="headerSc__carousel-img"
                     src="@/assets/img/welcomePage/undraw_select_house_qbag.svg">
              </div>
              <p class="headerSc__carousel-p">Now you can confidently choose Home Management System , because we are the
                best at what we do.</p>
            </div>
          </carousel>
        </div>
      </div>
      <div class="container__box-shadow-v1"></div>
      <div class="container__box-shadow"></div>
    </div>


  </div>

</template>

<script>
import carousel from 'vue-owl-carousel'

export default {
  name: "HeaderSc",
  components: {carousel},

}
</script>

<style scoped>
@import "../../assets/css/_headerScStyle.css";
</style>
