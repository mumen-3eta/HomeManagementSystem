<template>
  <div class="body-AddDevice">
    <TopNavBar/><!--  end top nav bar    -->
    <LeftNavBar/><!--  end Left nav bar    -->

    <section>
      <div class="container py-5 px-5 ">
        <div class="row mb-4">
          <div class="col-12 col-md-12 col-lg-12 anim1">
            <h2> Add New Device</h2>
          </div><!-- End Of col-->
        </div><!-- End Of row-->
        <form class="row g-4">
          <div class="col-md-6">
            <label class="form-label" for="inputEmail4">Email</label>
            <input id="inputEmail4" class="form-control" type="email">
          </div>
          <div class="col-md-6">
            <label class="form-label" for="inputPassword4">Password</label>
            <input id="inputPassword4" class="form-control" type="password">
          </div>
          <div class="col-12">
            <label class="form-label" for="inputAddress">Address</label>
            <input id="inputAddress" class="form-control" placeholder="1234 Main St" type="text">
          </div>
          <div class="col-12">
            <label class="form-label" for="inputAddress2">Address 2</label>
            <input id="inputAddress2" class="form-control" placeholder="Apartment, studio, or floor" type="text">
          </div>
          <div class="col-md-6">
            <label class="form-label" for="inputCity">City</label>
            <input id="inputCity" class="form-control" type="text">
          </div>
          <div class="col-md-4">
            <label class="form-label" for="inputState">State</label>
            <select id="inputState" class="form-select">
              <option selected>Choose...</option>
              <option>...</option>
            </select>
          </div>
          <div class="col-md-2">
            <label class="form-label" for="inputZip">Zip</label>
            <input id="inputZip" class="form-control" type="text">
          </div>
          <div class="col-12">
            <button class="btn btn-info text-light" type="submit">ADD Device</button>
          </div>
        </form>
        <!--        <form action="">-->
        <!--          <div class="row">-->
        <!--            <div class="col-12 col-md-6 ">-->
        <!--              <div class="form-group my-2">-->
        <!--                <label class="my-2" for="exampleInputEmail1">Email address<span-->
        <!--                    class="text-danger px-1">*</span></label>-->
        <!--                <input id="exampleInputEmail1" v-model.trim="email" aria-describedby="emailHelp"-->
        <!--                       class="form-control"-->
        <!--                       placeholder="example@example.example" type="email">-->
        <!--                <small id="emailHelp" class="form-text text-muted"></small>-->
        <!--              </div>-->
        <!--            </div>-->
        <!--            <div class="col-12 col-md-6 ">-->
        <!--              <div class="form-group my-2">-->
        <!--                <label class="my-2" for="exampleInputEmail1">Email address<span-->
        <!--                    class="text-danger px-1">*</span></label>-->
        <!--                <input id="exampleInputEmail1" v-model.trim="email" aria-describedby="emailHelp"-->
        <!--                       class="form-control"-->
        <!--                       placeholder="example@example.example" type="email">-->
        <!--                <small id="emailHelp" class="form-text text-muted"></small>-->
        <!--              </div>-->
        <!--            </div>-->
        <!--            <div class="col-12 col-md-6 ">-->
        <!--              <div class="form-group my-2">-->
        <!--                <label class="my-2" for="exampleInputEmail1">Email address<span-->
        <!--                    class="text-danger px-1">*</span></label>-->
        <!--                <input id="exampleInputEmail1" v-model.trim="email" aria-describedby="emailHelp"-->
        <!--                       class="form-control"-->
        <!--                       placeholder="example@example.example" type="email">-->
        <!--                <small id="emailHelp" class="form-text text-muted"></small>-->
        <!--              </div>-->
        <!--            </div>-->
        <!--          </div>&lt;!&ndash; End Of row&ndash;&gt;-->
        <!--        </form>-->
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
