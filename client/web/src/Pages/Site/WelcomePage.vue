<template>
  <div>
    <!--   Nav bar    -->
    <NavBar/>

    <!--   header   -->
    <HeaderSc id="HomeSection"/>

    <!--  about us  -->
    <AboutUsSc id="AboutUs"/>

    <!--   our Team   -->
    <OurTeamSc id="ourTeamSection"/>

    <!--  Asked Questions    -->
    <AskedQuestionSc/>

    <!--   Contact us And information contact   -->
    <ContactUsAndInfoSc id="contactUsSection"/>
    <!-- Footer -->
    <FooterSc/>

    <!--    btn up -->
    <UpButton/>
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
import UpButton from "@/components/Dir_UpButton/UpButton";

export default {
  name: "WelcomePage",
  components: {
    UpButton,
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




