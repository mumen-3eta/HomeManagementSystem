<template>
  <div class="Left-NavBar">
    <div>
      <p>DB</p>
    </div>
    <ul>
      <li>
        <router-link to="/mainPage">Home</router-link>
      </li>
      <li>
        <router-link style="font-size: 15px" to="/mainPage/add-device">Add Device</router-link>
      </li>
      <!--      <li><router-link to="/mainPage/Profile/user" >Profile</router-link></li>-->
      <li><a href="/mainPage/Profile/user">Profile</a></li>

      <li><a class="logout" href="javascript:void(0)" title="Log Out" @click.prevent="handleClick"><img
          alt="" src="@/assets/img/exit.png"></a></li>
    </ul>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "LeftNav",
  methods: {
    handleClick() {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      this.$store.dispatch('user', null);
      this.$store.dispatch('deviceInfoAdd', null);
      this.$router.push('/login');
    }
  },
  computed: {
    ...mapGetters(['user'])
  }
}
</script>

<style scoped>
@import "../../../assets/css/MainPageStyle/_leftNavStyle.css";
</style>
