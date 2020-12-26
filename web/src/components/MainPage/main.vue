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
            <router-link to="/mainPage/add-device">
              <section>
                <img alt="" class="addimage" src="@/assets/img/Group 70.png">
                <p>Add Device</p>
              </section>
            </router-link>
          </div>
          <Device v-for="list in lists" :key="list.id" :list="list"/>

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
import Device from "@/components/MainPage/CardDevice";
import {mapGetters} from "vuex";
import {TimelineLite} from "gsap";
import axios from "axios";

export default {
  name: "main",
  components: {
    TopNavBar,
    LeftNavBar,
    Device,
  },
  data() {
    return {
      lists: [
        {id: 1, name: 'device 1', status: 0},
        {id: 2, name: 'device 2', status: 1},
        {id: 3, name: 'device 3', status: 0},
        {id: 4, name: 'device 4', status: 1},
      ]
    }
  },
  mounted() {
    const addDevice = document.getElementsByClassName('addDevice');
    const d1 = document.getElementsByClassName('1');
    const timeline = new TimelineLite()
    timeline.from(addDevice, {opacity: 0, duration: 1, y: -50})
        .from(d1, {opacity: 0, duration: 1, y: -50}, '-=0.7')

  },
  async beforeMount() {
    const {user} = (await axios.get('/api/v1/users/me')).data;
    await this.$store.dispatch('user', user);
  },
  computed: {
    ...mapGetters(['user'])
  }

}
</script>

<style scoped>
@import "../../assets/css/MainPageStyle/_mainStyle.css";
</style>
