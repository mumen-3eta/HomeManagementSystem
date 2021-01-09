<template>
  <div>
    <!--   Nav bar    -->
    <NavBar/>

    <!--   header   -->
    <HeaderSc id="HomeSection"/>

    <!--  about us  -->
    <AboutUsSc/>

    <!--   our Team   -->
    <OurTeamSc/>

    <!--  Asked Questions    -->
    <AskedQuestionSc/>

    <!--   Contact us And information contact   -->
    <ContactUsAndInfoSc id="contactUsSection"/>
    <!-- Footer -->
    <FooterSc/>
  </div>
</template>

<script>
import NavBar from "@/components/Dir_Welcome/NavBar";
import HeaderSc from "@/components/Dir_Welcome/HeaderSc";
import FooterSc from "@/components/Dir_Welcome/FooterSc";
import AboutUsSc from "@/components/Dir_Welcome/AboutUsSc";
import AskedQuestionSc from "@/components/Dir_Welcome/AskedQuestionSc";
import ContactUsAndInfoSc from "@/components/Dir_Welcome/ContactUsAndInfoSc";
import OurTeamSc from "@/components/Dir_Welcome/OurTeamSc";

export default {
  name: "WelcomePage",
  components: {
    OurTeamSc,
    ContactUsAndInfoSc,
    AskedQuestionSc,
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




