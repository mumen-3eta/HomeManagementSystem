<template>
  <div>
    <!--   Nav bar    -->
    <NavBar/>

    <!--   header   -->
    <HeaderSc/>
    <HeaderSc/>

  </div>
</template>

<script>
import NavBar from "@/components/Dir_Welcome/NavBar";
import HeaderSc from "@/components/Dir_Welcome/HeaderSc";

export default {
  name: "WelcomePage",
  components: {
    HeaderSc,
    NavBar,
  },
  mounted() {
    // const title1 = document.getElementsByClassName('title1');
    // const title2 = document.getElementsByClassName('title2');
    // const title3 = document.getElementsByClassName('title3');
    // const imageTitle = document.getElementsByClassName('svg-background');
    // const timeline = new TimelineLite()
    // timeline.from(title1, {opacity: 0, duration: 1, y: -100})
    //     .from(title2, {opacity: 0, duration: 1, x: -100}, '-=0.9')
    //     .from(title3, {opacity: 0, duration: 1, x: -100}, '-=0.8')
    //     .from(imageTitle, {opacity: 0, duration: 1, x: 100}, '-=0.7')
    // const scene = document.getElementById('scene');
    // new Parallax(scene);

  }
}
</script>

<style scoped>
@import "../../assets/css/_welcomStyle.css";
</style>




