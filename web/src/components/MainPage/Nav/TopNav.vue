<template>
  <!-- top nav Start -->
  <nav class="nav__top-mainly">
    <div>
      <label>DashBord</label>
    </div>
    <div>
          <span>
            <img v-show="user.profileInfo.image" :src="user.profileInfo.image" alt="User Image" title="User Image">
            <label title="User Name">{{ user.basicInfo.userName }}</label>
          </span>
    </div>
  </nav>
  <!-- Top Nav End -->
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "TopNav",
  computed: {
    ...mapGetters(['GetUser']),
    ...mapGetters(['user'])
  },
}
</script>

<style scoped>
@import "../../../assets/css/MainPageStyle/_topNavStyle.css";
</style>
