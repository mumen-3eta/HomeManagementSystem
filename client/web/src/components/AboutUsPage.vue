<template>
  <div class="content__body-a">
    <NavBar/>
    <div class="content__body-aboutUs">
      <div>
        <h2 class="title1">About Us</h2>
        <h4 class="title2">This application aims to make it easier for the user to control the house appliances</h4>
        <p class="title3">
          We are five graduate students and this is our graduation project and We selected this project because we think
          it will benefit and solve a problem for it’s users.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/Dir_Welcome/NavBar";
import {TimelineLite} from "gsap";

export default {
  name: "AboutUsPage",
  components: {
    NavBar,
  },
  mounted() {
    const title1 = document.getElementsByClassName('title1');
    const title2 = document.getElementsByClassName('title2');
    const title3 = document.getElementsByClassName('title3');
    const timeline = new TimelineLite()
    timeline.from(title1, {opacity: 0, duration: 1, y: -50})
        .from(title2, {opacity: 0, duration: 1, x: 50}, '-=0.5')
        .from(title3, {opacity: 0, duration: 1, x: -50}, '-=1.0')
  }

}
</script>

<style scoped>
@import "../assets/temp/Temp_Old_Style__needDelete/_aboutUsStyle.css";
</style>


