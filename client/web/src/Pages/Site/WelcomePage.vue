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
  mounted() {
    window.onscroll = function () {
      scrollFunction();
    };

    function scrollFunction() {
      if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
        document.getElementById("main__nav").style.backgroundColor = "rgba(7,6,6,0.9)";
        document.getElementById("main__nav").style.boxShadow = "0 0 0 3px #70cbcb";
        document.getElementById("image_logo").style.width = "75%";
        document.getElementById("up_Button").style.display = "block";
      } else {
        document.getElementById("main__nav").style.backgroundColor = "";
        document.getElementById("image_logo").style.width = "";
        document.getElementById("main__nav").style.boxShadow = "";
        document.getElementById("up_Button").style.display = "none";
      }
    }

    document.getElementById("up_Button").addEventListener("click", function () {
      document.body.scrollTop = 0; // For Safari
      document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    });
  }
}
</script>

<style scoped>
@import "../../assets/css/_welcomStyle.css";
</style>




