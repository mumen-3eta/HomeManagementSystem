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
                <img class="addimage" src="@/assets/img/Group 70.png" alt="">
                <p>Add Device</p>
              </section>
            </a>
          </div>

          <!-- all device-->
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
          </div>
          <div class="device">
            <a href="">
              <section>
                <img src="@/assets/img/Group 66.png" alt="">
                <p>Device Name 1</p>
              </section>
            </a>
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
