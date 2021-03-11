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
  </div>
</template>

<script>
import NavMain from "@/layout/Main_Layout/NavMain";
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";

export default {
  name: "MainLayout",
  components: {HeaderMain, AsideMain, NavMain},
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
