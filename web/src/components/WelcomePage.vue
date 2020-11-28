<template>
  <div class="content__body-w">
    <NavBar/>
    <div class="content__body-welcome">
      <div class="imageTitle">
        <!--        <img alt="undraw_software_engineer_lvl5" class="svg-background"-->
        <!--             src="@/assets/img/undraw_software_engineer_lvl5 1.svg">-->
        <ul id="scene" class="svg-background">
          <li class="layer" data-depth="-0.8"><img alt="image" class="img4" src="@/assets/img/polygons.png"></li>
          <li class="layer" data-depth="0.6"><img alt="image" class="img1" src="@/assets/img/Group 3.png"></li>
          <li class="layer" data-depth="0.69"><img alt="image" class="img2" src="@/assets/img/Group 3-1.png"></li>
          <li class="layer" data-depth="0.65"><img alt="image" class="img3" src="@/assets/img/Group 3-2.png"></li>
        </ul>
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
import Parallax from 'parallax-js'

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
    const scene = document.getElementById('scene');
    new Parallax(scene);

  }
}
</script>

<style scoped>
@import "../assets/css/_welcomStyle.css";
</style>




