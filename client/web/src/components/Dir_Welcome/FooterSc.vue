<template>
  <div>
    <div :class=" lang==='ar' ? 'container__footerSc direction-rtl' :'container__footerSc direction-ltr'">
      <div class="footer__title">
        <p>
          {{ lang === 'en' ? SiteFooterInfo_EN.FirstLeftText : SiteFooterInfo_AR.FirstLeftText }}
          <sup>&#169;</sup>
          {{ lang === 'en' ? SiteFooterInfo_EN.NowYear : SiteFooterInfo_AR.NowYear }}
          {{ lang === 'en' ? SiteFooterInfo_EN.SecondLeftText : SiteFooterInfo_AR.SecondLeftText }}
        </p>
      </div>
      <div class="footer__ul">
        <ul class="footer__list">
          <li v-for="(SocialMediaItem,index) in lang === 'en' ? SiteFooterInfo_EN.SocialMediaList : SiteFooterInfo_AR.SocialMediaList  "
              :key="index" class="footer__item">
            <a :href="SocialMediaItem.Link" class="footer__link" target="_blank">
              <i :class="SocialMediaItem.Type"></i>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterSc",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      SiteFooterInfo_EN: {
        FirstLeftText: 'All rights reserved',
        SecondLeftText: ', HMSy',
        NowYear: null,
        SocialMediaList: [
          {Type: 'fab fa-facebook', Link: '#',},
          {Type: 'fab fa-twitter', Link: '#',},
          {Type: 'fab fa-instagram', Link: '#',},
          {Type: 'fab fa-github', Link: '#',},
        ],
      },
      SiteFooterInfo_AR: {
        FirstLeftText: 'كل الحقوق محفوظة',
        SecondLeftText: '، HMSy',
        NowYear: null,
        SocialMediaList: [
          {Type: 'fab fa-facebook', Link: '#',},
          {Type: 'fab fa-twitter', Link: '#',},
          {Type: 'fab fa-instagram', Link: '#',},
          {Type: 'fab fa-github', Link: '#',},
        ],
      },
    }
  },
  mounted() {
    let today = new Date();
    if (this.lang === 'en') {
      this.SiteFooterInfo_EN.NowYear = today.getFullYear();
    } else {
      this.SiteFooterInfo_AR.NowYear = today.getFullYear();
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/_footerScStyle.css";
</style>
