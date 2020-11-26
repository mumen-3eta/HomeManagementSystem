<template>
  <div class="content__body-w">
    <NavBar/>
    <div class="content__body-welcome">
      <div class="imageTitle">
        <img alt="undraw_software_engineer_lvl5" class="svg-background"
             src="@/assets/img/undraw_software_engineer_lvl5 1.svg">
      </div>

      <div>
        <h2 class="title1">The Home Management Experience You really want</h2>
        <p class="title2">
          Now you can confidently choose Home Management System without worrying that no one will be able to use the
          site.
        </p>
        <router-link class="title3" title="Go to Login Page" to="/login"> Get Started</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/TopBar/NavBar";
import {TimelineLite} from "gsap";

export default {
  name: "WelcomePage",
  components: {
    NavBar,
  },
  mounted() {
    const title1 = document.getElementsByClassName('title1');
    const title2 = document.getElementsByClassName('title2');
    const title3 = document.getElementsByClassName('title3');
    const imageTitle = document.getElementsByClassName('imageTitle');
    const timeline = new TimelineLite()
    timeline.from(title1, {opacity: 0, duration: 1, y: -100})
        .from(title2, {opacity: 0, duration: 1, x: -100}, '-=0.9')
        .from(title3, {opacity: 0, duration: 1, x: -100}, '-=0.8')
        .from(imageTitle, {opacity: 0, duration: 1, x: 100}, '-=0.7')
  }
}
</script>

<style scoped>
@import "../assets/css/_welcomStyle.css";
</style>



