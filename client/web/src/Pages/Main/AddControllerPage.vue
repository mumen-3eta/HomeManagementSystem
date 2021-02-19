<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <AddController/>

    </main><!-- End Main -->
    <!--    aside here -->
    <AsideMain/>
  </div>
</template>

<script>
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";
import NavMain from "@/layout/Main_Layout/NavMain";

import AddController from "@/components/Dir_Main/AddController";

export default {
  name: "AddControllerPage",
  components: {AddController, HeaderMain, AsideMain, NavMain},
}
</script>

<style scoped>
@import "../../assets/css/Pages_Style/MainHomePage.css";
</style>
