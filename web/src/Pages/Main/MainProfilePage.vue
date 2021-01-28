<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <ProfileUserMain/>

    </main><!-- End Main -->
    <!--    aside here -->
    <!--    <AsideMain/>-->

  </div>
</template>

<script>
import NavMain from "@/layout/Main_Layout/NavMain";
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import ProfileUserMain from "@/components/Dir_Main/ProfileUserMain";

export default {
  name: "MainProfilePage",
  components: {ProfileUserMain, HeaderMain, NavMain},
}
</script>

<style scoped>
@import "../../assets/css/ClearStyle/normalize.css";
@import "../../assets/css/Pages_Style/MainHomePage.css";
</style>
