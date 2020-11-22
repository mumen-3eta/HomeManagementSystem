<template>
  <div class="body-main">
      <TopNavBar/>
      <LeftNavBar/>

    <section>
      <!-- body section -->
      <div class="body__content-main">
        <!-- section change just it  -->
        <div class="All-Drivers">
          <!-- all device-->
          <div class="device">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name {{ $route.params.Device_id}}</p>
              </section>
          </div>
        </div>
      </div>
      <!-- End -->
    </section>
  </div>
</template>

<script>
// import axios from "axios";

import TopNavBar from "@/components/MainPage/Nav/TopNav";
import LeftNavBar from "@/components/MainPage/Nav/LeftNav";
import {mapGetters} from "vuex";
export default {
  name: "main",
  mounted() {
    console.log();
  },
  components:{
    TopNavBar,
    LeftNavBar,
  },
  computed:{
    ...mapGetters(['user'])
  }

}
</script>

<style scoped>
  @import "../../assets/css/MainPageStyle/_mainStyle.css";
</style>
