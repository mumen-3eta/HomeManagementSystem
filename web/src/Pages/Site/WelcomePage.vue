<template>
  <div>
    <!--   Nav bar    -->
    <NavBar/>

    <!--   header   -->
    <HeaderSc/>


    <!-- Footer -->
    <FooterSc/>
  </div>
</template>

<script>
import NavBar from "@/components/Dir_Welcome/NavBar";
import HeaderSc from "@/components/Dir_Welcome/HeaderSc";
import FooterSc from "@/components/Dir_Welcome/FooterSc";

export default {
  name: "WelcomePage",
  components: {
    FooterSc,
    HeaderSc,
    NavBar,
  },

}
</script>

<style scoped>
@import "../../assets/css/_welcomStyle.css";
</style>




