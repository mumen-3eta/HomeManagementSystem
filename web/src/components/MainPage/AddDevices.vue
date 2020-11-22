<template>
  <div class="body-AddDevice">
    <TopNavBar/><!--  end top nav bar    -->
    <LeftNavBar/><!--  end Left nav bar    -->

    <section>
      <div class="container py-3">
        <div class="row">
          <div class="text-center col-12 col-md-12">
            <h2> Add New Device </h2>
          </div><!-- End Of col-->
        </div><!-- End Of row-->
      </div><!-- End Of container-->
    </section>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import TopNavBar from "@/components/MainPage/Nav/TopNav";
import LeftNavBar from "@/components/MainPage/Nav/LeftNav";

export default {
  name: "AddDevices",
  components: {
    TopNavBar,
    LeftNavBar,
  },
  computed: {
    ...mapGetters(['GetUser']),
    ...mapGetters(['user']),

  },
}
</script>

<style scoped>
@import "../../assets/css/MainPageStyle/_addDeviceStyle.css";
</style>
