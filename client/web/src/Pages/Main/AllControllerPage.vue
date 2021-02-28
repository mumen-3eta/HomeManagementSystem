<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <AllController/>

    </main><!-- End Main -->
    <!--    aside here -->
    <AsideMain/>
  </div>
</template>

<script>
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";
import NavMain from "@/layout/Main_Layout/NavMain";
import AllController from "@/components/Dir_Main/AllController";

export default {
  name: "AllControllerPage",
  components: {AllController, HeaderMain, AsideMain, NavMain},
}
</script>

<style scoped>

</style>
