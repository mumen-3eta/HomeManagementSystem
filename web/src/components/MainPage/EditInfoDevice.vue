<template>
  <div class="body-AddDevice">
    <TopNavBar/><!--  end top nav bar    -->
    <LeftNavBar/><!--  end Left nav bar    -->

    <section>
      <div class="container py-5 px-5 ">
        <div class="row mb-4">
          <div class="col-12 col-md-12 col-lg-12 anim1">
            <h2> Edit Device</h2>
          </div><!-- End Of col-->
        </div><!-- End Of row-->
        <form class="row g-4" @submit.prevent="AddDeviceForm">
          <div class="col-md-6 anim2">
            <label class="form-label" for="inputNameDevice">Name Device<span class="text-danger px-1">*</span></label>
            <input id="inputNameDevice" v-model.trim="deviceInfo.nameDevice" class="form-control"
                   placeholder="EX: Your name Device" required type="text">
          </div>
          <div class="col-md-6 anim3">
            <label class="form-label" for="inputLocation">Location<span class="text-danger px-1">*</span></label>
            <input id="inputLocation" v-model.trim="deviceInfo.nameLocation" class="form-control"
                   placeholder="EX: Living Room"
                   required type="text">
          </div>
          <div class="col-md-6 anim4">
            <label class="form-label" for="inputTypeDevice">Type Device<span class="text-danger px-1">*</span></label>
            <input id="inputTypeDevice" v-model.trim="deviceInfo.nameType" class="form-control" placeholder="EX: TV"
                   required type="text">
          </div>
          <!--          <div class="col-md-6 anim5">-->
          <!--            <label class="form-label" for="inputStatus">Status<span class="text-danger px-1">*</span></label>-->
          <!--            <select id="inputStatus" v-model.trim="deviceInfo.status" class="form-select" required>-->
          <!--              &lt;!&ndash;              <option value="-1">Choose One...</option>&ndash;&gt;-->
          <!--              <option selected value="0">Not Active</option>-->
          <!--              <option value="1">Active</option>-->
          <!--            </select>-->
          <!--          </div>-->
          <div class="col-12 anim6">
            <button class="btn btn-info text-light" type="submit">Update Device</button>
          </div>
        </form>

        <!--        <div v-if="this.deviceInfoAdd" class="container py-5">-->
        <!--          <div class="row">-->
        <!--            <div class="col">-->
        <!--              <p>{{ deviceInfoAdd.nameDevice }}</p>-->
        <!--              <p>{{ deviceInfoAdd.nameLocation }}</p>-->
        <!--              <p>{{ deviceInfoAdd.nameType }}</p>-->
        <!--              <p>{{ deviceInfoAdd.status | Status }} </p>-->
        <!--            </div>&lt;!&ndash; End Of col&ndash;&gt;-->
        <!--          </div>&lt;!&ndash; End Of row&ndash;&gt;-->
        <!--        </div>&lt;!&ndash; End Of container&ndash;&gt;-->

      </div><!-- End Of container-->
    </section>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import TopNavBar from "@/components/MainPage/Nav/TopNav";
import LeftNavBar from "@/components/MainPage/Nav/LeftNav";
import {TimelineLite} from "gsap";

export default {
  name: "EditInfoDevice",
  components: {
    TopNavBar,
    LeftNavBar,
  },
  data() {
    return {
      deviceInfo: {
        nameDevice: null,
        nameLocation: null,
        nameType: null,
        status: 'Not Active',
      },
    }
  },
  methods: {
    AddDeviceForm() {
      // Add function and hidden for any user (save for one user كل يوزر له أجهزته الخاصة)
      this.$store.dispatch('deviceInfoAdd', this.deviceInfo)
      // set default input (Empty)
      this.deviceInfo = {nameDevice: null, nameLocation: null, nameType: null, status: 'Not Active',}

    }
  },
  mounted() {
    const anim1 = document.getElementsByClassName('anim1');
    const anim2 = document.getElementsByClassName('anim2');
    const anim3 = document.getElementsByClassName('anim3');
    const anim4 = document.getElementsByClassName('anim4');
    // const anim5 = document.getElementsByClassName('anim5');
    const anim6 = document.getElementsByClassName('anim6');

    const timeline = new TimelineLite()
    timeline.from(anim1, {opacity: 0, duration: 1, y: -50})
        .from(anim2, {opacity: 0, duration: 1, y: 50}, '-=0.8')
        .from(anim3, {opacity: 0, duration: 1, y: 50}, '-=0.8')
        .from(anim4, {opacity: 0, duration: 1, y: 50}, '-=0.8')
        // .from(anim5, {opacity: 0, duration: 1, y: 50}, '-=0.8')
        .from(anim6, {opacity: 0, duration: 1, y: 50}, '-=0.8')

  },
  // filters: {
  //   Status: function (value) {
  //     if (value == '-1') return 'Choose nothing';
  //     return value == 0 ? 'Not Active' : 'Active';
  //   }
  // },
  computed: {
    ...mapGetters(['GetUser', 'deviceInfoAdd', 'user']),
  },
}
</script>

<style scoped>
@import "../../assets/css/MainPageStyle/_addDeviceStyle.css";
</style>
