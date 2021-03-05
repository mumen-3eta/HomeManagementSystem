<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <ShowControllerInfo/>

    </main><!-- End Main -->
    <!--    aside here -->
    <AsideMain/>
  </div>
</template>

<script>
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";
import NavMain from "@/layout/Main_Layout/NavMain";
import ShowControllerInfo from "@/components/Dir_Main/ShowControllerInfo";

export default {
  name: "ControllerForProcessorPage",
  components: {ShowControllerInfo, HeaderMain, AsideMain, NavMain},
}
</script>

<style scoped>
@import "../../assets/css/Pages_Style/MainHomePage.css";
</style>
