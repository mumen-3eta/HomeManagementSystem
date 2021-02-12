<template>
  <div></div>
  <!--  <div class="Left-NavBar">-->
  <!--    <div>-->
  <!--      <p>DB</p>-->
  <!--    </div>-->
  <!--    <ul>-->
  <!--      <li>-->
  <!--        <router-link to="/mainPage">Home</router-link>-->
  <!--      </li>-->
  <!--      <li>-->
  <!--        <router-link style="font-size: 15px" to="/mainPage/add-device">Add Device</router-link>-->
  <!--      </li>-->
  <!--      <li>-->
  <!--        <router-link to="/mainPage/Profile/user">Profile</router-link>-->
  <!--      </li>-->
  <!--      &lt;!&ndash;      <li><a href="/mainPage/Profile/user">Profile</a></li>&ndash;&gt;-->

  <!--      <li><a class="logout" href="javascript:void(0)" title="Log Out" @click.prevent="handleClick"><img-->
  <!--          alt="" src="@/assets/img/exit.png"></a></li>-->
  <!--    </ul>-->
  <!--  </div>-->
</template>

<script>
// import {mapGetters} from "vuex";
// import axios from "axios";

export default {
  name: "LeftNav",
  // methods: {
  //   async handleClick() {
  //
  //     await axios.put('/api/v1/users/logout');
  //     localStorage.removeItem('csrfToken');
  //     this.$store.dispatch('user', null);
  //     this.$store.dispatch('TokenUser', null);
  //     this.$store.dispatch('deviceInfoAdd', null);
  //     this.$router.push('/login');
  //   }
  // },
  // computed: {
  //   ...mapGetters(['user', 'TokenUser', 'deviceInfoAdd'])
  // }
  // , beforeMount() {
  //   axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
  // }
}
</script>

<!--<style scoped>-->
<!--@import "../../../assets/temp/Temp_Old_Style__needDelete/MainPageStyle/_leftNavStyle.css";-->
<!--</style>-->
