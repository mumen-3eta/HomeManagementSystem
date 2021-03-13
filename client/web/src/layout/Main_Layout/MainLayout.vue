<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main margin-left_5">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <div>
        <router-view></router-view>
      </div>

    </main><!-- End Main -->
    <!--    aside here -->
    <AsideMain/>
    <!--    btn up -->
    <UpButton/>
  </div>
</template>

<script>
import NavMain from "@/layout/Main_Layout/NavMain";
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";
import UpButton from "@/components/Dir_UpButton/UpButton";

export default {
  name: "MainLayout",
  components: {UpButton, HeaderMain, AsideMain, NavMain},
  mounted() {
    window.onscroll = function () {
      scrollFunction();
    };

    function scrollFunction() {
      if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
        document.getElementById("up_Button").style.display = "block";
      } else {
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
@import "../../assets/css/ClearStyle/normalize.css";
@import "../../assets/css/Pages_Style/MainHomePage.css";

.margin-left_5 {
  margin-left: 7rem;
}

@media (max-width: 1200px) {
  .margin-left_5 {
    margin-left: 1.5rem;
  }
}
</style>
