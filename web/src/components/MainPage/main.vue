<template>
  <div class="body-main">
    <TopNavBar/>
    <LeftNavBar/>

    <section>
      <!-- body section -->
      <div class="body__content-main">
        <!-- section change just it  -->
        <div class="All-Drivers">
          <!-- add Device-->
          <div class="device addDevice">
            <a href="/mainPage/add-device">
              <section>
                <img alt="" class="addimage" src="@/assets/img/Group 70.png">
                <p>Add Device</p>
              </section>
            </a>
          </div>
          <!-- all device-->
          <div class="device">
            <router-link to="/mainPage/DeviceInfo/1">
              <section>
                <img alt="" src="@/assets/img/Group 66.png">
                <p>Device Name 1</p>
              </section>
            </router-link>
          </div>
          <div class="device">
            <router-link to="/mainPage/DeviceInfo/2">
              <section>
                <img alt="" src="@/assets/img/Group 66.png">
                <p>Device Name 2</p>
              </section>
            </router-link>
          </div>
          <div class="device">
            <router-link to="/mainPage/DeviceInfo/3">
              <section>
                <img alt="" src="@/assets/img/Group 66.png">
                <p>Device Name 3</p>
              </section>
            </router-link>
          </div>
          <div class="device">
            <router-link to="/mainPage/DeviceInfo/4">
              <section>
                <img alt="" src="@/assets/img/Group 66.png">
                <p>Device Name 4</p>
              </section>
            </router-link>
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
  components: {
    TopNavBar,
    LeftNavBar,
  },
  computed: {
    ...mapGetters(['user'])
  }

}
</script>

<style scoped>
@import "../../assets/css/MainPageStyle/_mainStyle.css";
</style>
