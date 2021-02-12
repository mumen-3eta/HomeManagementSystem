<template>
  <div>
    <div class="container__footerSc">
      <div class="footer__title">
        <p>All rights reserved<sup>&#169;</sup> {{ this.NowYear }}, HMSy</p>
      </div>
      <div class="footer__ul">
        <ul class="footer__list">
          <li class="footer__item"><a class="footer__link" href=""><i class="fab fa-facebook"></i></a></li>
          <li class="footer__item"><a class="footer__link" href=""><i class="fab fa-twitter"></i></a></li>
          <li class="footer__item"><a class="footer__link" href=""><i class="fab fa-instagram"></i></a></li>
          <li class="footer__item"><a class="footer__link" href=""><i class="fab fa-github"></i></a></li>
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
      NowYear: null
    }
  },
  mounted() {
    let today = new Date();
    this.NowYear = today.getFullYear();
  }
}
</script>

<style scoped>
@import "../../assets/css/_footerScStyle.css";
</style>
