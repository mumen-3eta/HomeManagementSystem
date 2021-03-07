<template>
  <div>
    <div class="container__footerSc">
      <div class="footer__title">
        <p>
          {{ SiteFooterInfo.FirstLeftText }}
          <sup>&#169;</sup>
          {{ SiteFooterInfo.NowYear }}
          {{ SiteFooterInfo.SecondLeftText }}
        </p>
      </div>
      <div class="footer__ul">
        <ul class="footer__list">
          <li v-for="(SocialMediaItem,index) in SiteFooterInfo.SocialMediaList" :key="index" class="footer__item">
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
      SiteFooterInfo: {
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
    }
  },
  mounted() {
    let today = new Date();
    this.SiteFooterInfo.NowYear = today.getFullYear();
  }
}
</script>

<style scoped>
@import "../../assets/css/_footerScStyle.css";
</style>
