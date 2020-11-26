<template>
  <div class="body-deviceInfo">
    <TopNavBar/>
    <LeftNavBar/>
    <section>
      <div class="container py-5 px-5 ">
        <div class="row">
          <div class="col-12 col-md-12 col-lg-12 anim1">
            <h2> Device | name it</h2>
          </div><!-- End Of col-->
        </div><!-- End Of row-->

        <div class="row justify-content-center align-items-center py-3">
          <div
              class="anim2 col-12 col-md-4 col-lg-6 d-flex justify-content-center align-items-center flex-column right__content-device">
            <div class="container-device-right d-flex justify-content-center align-items-center flex-column">
              <div class="container-image">
                <img alt="" src="@/assets/img/Group 66.png">
              </div>
              <p class="nameDevice">Device Name {{ $route.params.Device_id }}</p>
              <p v-if="false" class="isActive"> Active </p>
              <p v-if="true" class="isNotActive"> Not Active </p>
            </div>
          </div><!-- End Of col-->
          <div class="anim3 col-12 col-md-8 col-lg-6 left__content-device">
            <div class="container-device-info-left">
              <div>
                <p>Number Device : {{ $route.params.Device_id }}</p>
              </div>
              <div class="container-device-info d-flex flex-column">
                <div class="d-flex justify-content-start align-items-start">
                  <p class="labelLocation">Location |</p>
                  <p class="nameLabelLocation">living rome </p>
                </div>
                <div class=" d-flex justify-content-start align-items-start">
                  <p class="labelLocation">Type |</p>
                  <p class="nameLabelLocation">TV</p>
                </div>
                <div class="form-check form-switch isActiveInput justify-content-end align-items-center">
                  <input id="flexSwitchCheckDefault" class="form-check-input" type="checkbox">
                  <label class="form-check-label  pl-2" for="flexSwitchCheckDefault">Active</label>
                </div>
              </div>

            </div>
          </div><!-- End Of col-->
        </div><!-- End Of row-->
      </div><!-- End Of container-->
      <!-- body section -->
    </section>
  </div>
</template>

<script>
// import axios from "axios";

import TopNavBar from "@/components/MainPage/Nav/TopNav";
import LeftNavBar from "@/components/MainPage/Nav/LeftNav";
import {mapGetters} from "vuex";
import {TimelineLite} from "gsap";

export default {
  name: "main",
  mounted() {
    const anim1 = document.getElementsByClassName('anim1');
    const anim2 = document.getElementsByClassName('anim2');
    const anim3 = document.getElementsByClassName('anim3');

    const timeline = new TimelineLite()
    timeline.from(anim1, {opacity: 0, duration: 1, y: -50})
        .from(anim2, {opacity: 0, duration: 1, y: 50}, '-=0.8')
        .from(anim3, {opacity: 0, duration: 1, y: 50}, '-=0.7')

  },
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
@import "../../assets/css/MainPageStyle/_DeviceInfoStyle.css";
</style>
