<template>
  <div>
    <!--   Nav bar    -->
    <NavBar/>

    <!--   header   -->
    <HeaderSc/>

    <!--  about us  -->
    <AboutUsSc/>

    <!-- Footer -->
    <FooterSc/>
  </div>
</template>

<script>
import NavBar from "@/components/Dir_Welcome/NavBar";
import HeaderSc from "@/components/Dir_Welcome/HeaderSc";
import FooterSc from "@/components/Dir_Welcome/FooterSc";
import AboutUsSc from "@/components/Dir_Welcome/AboutUsSc";

export default {
  name: "WelcomePage",
  components: {
    AboutUsSc,
    FooterSc,
    HeaderSc,
    NavBar,
  },

}
</script>

<style scoped>
@import "../../assets/css/_welcomStyle.css";
</style>




