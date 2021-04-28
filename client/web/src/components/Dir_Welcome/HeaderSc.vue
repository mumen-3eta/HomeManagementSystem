<template>
  <div>
    <div class="container__headerSc">
      <carousel :autoplay="true"
                :autoplaySpeed="2000"
                :dots="true"
                :items="1"
                :loop="true"
                :nav="false"
                class="container__headerSc-CarouselGroup">
        <div v-for="(header , index) in lang ==='en' ? ListHeaderSc_EN : ListHeaderSc_AR" :key="index"
             :class=" lang==='ar' ? 'container__headerSc-group direction-rtl' :'container__headerSc-group direction-ltr'">
          <div class="headerSc-group_1">
            <h2 class="headerSc-group_1-title">{{ header.title }}</h2>
            <p class="headerSc-group_1-body">{{ header.body }}</p>
            <div>
              <router-link :to="{path : header.LinkButtonTo}" class="headerSc-group_1-link-btn">
                <button class="headerSc-group_1-btn" type="button">
                  {{ header.TextInButton }}
                </button>
              </router-link>
            </div>
          </div>
          <div class="headerSc-group_2">
            <img :src="header.ImagePath" alt="image-1">
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
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      ListHeaderSc_EN: [
        {
          title: 'Home Management Experience You really want ',
          body: 'Now you can confidently choose Home Management System , because we are the best at what we do.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_Home_settings_re_pkya.svg`),
          TextInButton: 'Get Start Now!',
          LinkButtonTo: '/v2/register',
        },
        {
          title: 'Home Management Experience You really want',
          body: 'Now you can confidently choose Home Management System , because we are the best at what we do.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_home_cinema_l7yl.svg`),
          TextInButton: 'Get Start Now!',
          LinkButtonTo: '/v2/register',
        },
        {
          title: 'Home Management Experience You really want',
          body: 'Now you can confidently choose Home Management System , because we are the best at what we do.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_build_your__home_csh6.svg`),
          TextInButton: 'Get Start Now!',
          LinkButtonTo: '/v2/register',
        },
        {
          title: 'Home Management Experience You really want',
          body: 'Now you can confidently choose Home Management System , because we are the best at what we do.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_software_engineer_lvl5.svg`),
          TextInButton: 'Get Start Now!',
          LinkButtonTo: '/v2/register',
        },
      ],
      ListHeaderSc_AR: [
        {
          title: 'تجربة إدارة المنزل الذي تريده حقًا',
          body: 'الآن يمكنك بثقة اختيار نظام إدارة المنزل ، لأننا الأفضل في ما نقوم به.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_Home_settings_re_pkya.svg`),
          TextInButton: 'ابدأ الآن!',
          LinkButtonTo: '/v2/register',
        },
        {
          title: 'تجربة إدارة المنزل الذي تريده حقًا',
          body: 'الآن يمكنك بثقة اختيار نظام إدارة المنزل ، لأننا الأفضل في ما نقوم به.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_home_cinema_l7yl.svg`),
          TextInButton: 'ابدأ الآن!',
          LinkButtonTo: '/v2/register',
        },
        {
          title: 'تجربة إدارة المنزل الذي تريده حقًا',
          body: 'الآن يمكنك بثقة اختيار نظام إدارة المنزل ، لأننا الأفضل في ما نقوم به.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_build_your__home_csh6.svg`),
          TextInButton: 'ابدأ الآن!',
          LinkButtonTo: '/v2/register',
        },
        {
          title: 'تجربة إدارة المنزل الذي تريده حقًا',
          body: 'الآن يمكنك بثقة اختيار نظام إدارة المنزل ، لأننا الأفضل في ما نقوم به.',
          ImagePath: require(`@/assets/img/welcomePage/undraw_software_engineer_lvl5.svg`),
          TextInButton: 'ابدأ الآن!',
          LinkButtonTo: '/v2/register',
        },
      ]
    }
  },

}
</script>

<style scoped>
@import "../../assets/css/_headerScStyle.css";
</style>
