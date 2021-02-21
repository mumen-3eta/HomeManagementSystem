<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <div class="container_Admin_AddLocationController">
        <h2>Add Location Controller</h2>

      </div>
    </main><!-- End Main -->
    <!--    aside here -->
    <AsideMain/>

  </div>
</template>

<script>
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";
import NavMain from "@/layout/Main_Layout/NavMain";

export default {
  name: "AdminAddLocationControllerPage",
  components: {
    HeaderMain, AsideMain, NavMain,
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminAddLocationContorllerPageStyle.css";
</style>
