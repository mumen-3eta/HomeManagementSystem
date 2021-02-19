<template>
  <div>
    <div class="container__headerSc">
      <carousel :autoplay="true" :autoplaySpeed="2000" :dots="true" :items="1" :nav="false"
                class="container__headerSc-CarouselGroup">
        <div class="container__headerSc-group">
          <div class="headerSc-group_1">
            <h2 class="headerSc-group_1-title">Home Management Experience You really want</h2>
            <p class="headerSc-group_1-body">Now you can confidently choose Home Management System , because we are the
              best at what we do.</p>
            <div>
              <button class="headerSc-group_1-btn" type="button">
                <router-link :to="{path:'/v2/register'}" class="headerSc-group_1-link-btn">Get Start Now!</router-link>
              </button>
            </div>
          </div>
          <div class="headerSc-group_2">
            <img alt="image-1" src="@/assets/img/welcomePage/undraw_Home_settings_re_pkya.svg">
          </div>
        </div>
        <div class="container__headerSc-group">
          <div class="headerSc-group_1">
            <h2 class="headerSc-group_1-title">Home Management Experience You really want</h2>
            <p class="headerSc-group_1-body">Now you can confidently choose Home Management System , because we are the
              best at what we do.</p>
            <div>
              <button class="headerSc-group_1-btn" type="button">
                <router-link :to="{path:'/v2/register'}" class="headerSc-group_1-link-btn">Get Start Now!</router-link>
              </button>
            </div>
          </div>
          <div class="headerSc-group_2">
            <img alt="image-1" src="@/assets/img/welcomePage/undraw_home_cinema_l7yl.svg">
          </div>
        </div>
        <div class="container__headerSc-group">
          <div class="headerSc-group_1">
            <h2 class="headerSc-group_1-title">Home Management Experience You really want</h2>
            <p class="headerSc-group_1-body">Now you can confidently choose Home Management System , because we are the
              best at what we do.</p>
            <div>
              <button class="headerSc-group_1-btn" type="button">
                <router-link :to="{path:'/v2/register'}" class="headerSc-group_1-link-btn">Get Start Now!</router-link>
              </button>
            </div>
          </div>
          <div class="headerSc-group_2">
            <img alt="image-1" src="@/assets/img/welcomePage/undraw_build_your__home_csh6.svg">
          </div>
        </div>
        <div class="container__headerSc-group">
          <div class="headerSc-group_1">
            <h2 class="headerSc-group_1-title">Home Management Experience You really want</h2>
            <p class="headerSc-group_1-body">Now you can confidently choose Home Management System , because we are the
              best at what we do.</p>
            <div>
              <button class="headerSc-group_1-btn" type="button">
                <router-link :to="{path:'/v2/register'}" class="headerSc-group_1-link-btn">Get Start Now!</router-link>
              </button>
            </div>
          </div>
          <div class="headerSc-group_2">
            <img alt="image-1" src="@/assets/img/welcomePage/undraw_software_engineer_lvl5.svg">
          </div>
        </div>
        <div class="container__headerSc-group">
          <div class="headerSc-group_1">
            <h2 class="headerSc-group_1-title">Home Management Experience You really want</h2>
            <p class="headerSc-group_1-body">Now you can confidently choose Home Management System , because we are the
              best at what we do.</p>
            <div>
              <button class="headerSc-group_1-btn" type="button">
                <router-link :to="{path:'/v2/register'}" class="headerSc-group_1-link-btn">Get Start Now!</router-link>
              </button>
            </div>
          </div>
          <div class="headerSc-group_2">
            <img alt="image-1" src="@/assets/img/welcomePage/undraw_select_house_qbag.svg">
          </div>
        </div>
      </carousel>
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
