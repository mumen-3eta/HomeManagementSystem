<template>
  <div class="Left-NavBar">
    <div>
      <p>DB</p>
    </div>
    <ul>
      <li><router-link to="/mainPage">Main</router-link></li>
<!--      <li><router-link to="/mainPage/Profile/user" >Profile</router-link></li>-->
      <li><a href="/mainPage/Profile/user" >Profile</a></li>

      <li><a title="Log Out" href="javascript:void(0)" @click.prevent="handleClick" class="logout"><img src="@/assets/img/exit.png" alt=""></a></li>
    </ul>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "LeftNav",
  methods:{
    handleClick(){
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      this.$store.dispatch('user' , null);
      this.$router.push('/login');
    }
  },
  computed:{
    ...mapGetters(['user'])
  }
}
</script>

<style scoped>
  @import "../../../assets/css/MainPageStyle/_leftNavStyle.css";
</style>
