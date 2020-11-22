<template>
  <!-- top nav Start -->
  <nav class="nav__top-mainly">
    <div>
      <label>DashBord</label>
    </div>
    <div>
          <span>
              <img v-show="!GetUser.image" src="@/assets/img/man.svg" title="User Image" alt="User Image">
              <img v-show="GetUser.image" :src="GetUser.image" title="User Image" alt="User Image">
              <label title="User Name">{{GetUser.name}}</label>
          </span>
    </div>
  </nav>
  <!-- Top Nav End -->
</template>

<script>
import {mapGetters} from "vuex";
export default {
  name: "TopNav",
  computed:{
    ...mapGetters(['GetUser']),
    ...mapGetters(['user'])
  },
}
</script>

<style scoped>
    @import "../../../assets/css/MainPageStyle/_topNavStyle.css";
</style>
