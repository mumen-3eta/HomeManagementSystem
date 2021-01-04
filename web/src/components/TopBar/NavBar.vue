<template>
  <div>
    <div class="main__nav">
      <nav class="main__nav_content">
        <div class="nav__logo">
          <div class="nav__items">
            <img alt="logo" src="@/assets/img/Logo.svg">
          </div>
        </div>
        <div class="ul-list-min">
          <ul class="nav__element">
            <li class="nav__element-list"><a class="nav__link-list" href="">Home</a></li>
            <li class="nav__element-list"><a class="nav__link-list" href="">About us</a></li>
            <li class="nav__element-list"><a class="nav__link-list" href="">contact us</a></li>
            <li class="nav__element-list"><a class="nav__link-list" href="">our Team</a></li>
          </ul>
        </div>
        <div class="nav_btn_items">
          <button class="nav__items-btn" type="button"><a class="nav_link-btn" href="">Login</a></button>
          <button class="nav__items-btn" type="button"><a class="nav_link-btn" href="">SigUp</a></button>
        </div>
        <div class="fas__menu-btn">
          <i class="fas fa-ellipsis-v menu-btn"></i>
        </div>
      </nav>
      <!--    <nav class="nav__top-welcome">-->
      <!--      <div title="logo Home Manegment System">-->
      <!--        <img alt="" src="@/assets/img/Logo.svg">-->
      <!--      </div>-->
      <!--      <div class="nav-items">-->
      <!--        <ul class="Nav-List-1">-->
      <!--          <li>-->
      <!--            <router-link to="/">Home</router-link>-->
      <!--          </li>-->
      <!--          <li>-->
      <!--            <router-link to="/aboutUs">About</router-link>-->
      <!--          </li>-->
      <!--          <li>-->
      <!--            <router-link to="/contactUs">Contacts</router-link>-->
      <!--          </li>-->
      <!--        </ul>-->

      <!--        <ul v-if="TokenUser">-->
      <!--          <li class="siginUp"><a href="javascript:void(0)" @click.prevent="handleClick">Log Out</a></li>-->
      <!--          <li>-->
      <!--            <router-link to="/mainPage">Dashboard</router-link>-->
      <!--          </li>-->
      <!--        </ul>-->
      <!--        <ul v-if="!TokenUser">-->
      <!--          <li>-->
      <!--            <router-link to="/login">Login</router-link>-->
      <!--          </li>-->
      <!--          <li class="siginUp">-->
      <!--            <router-link to="/register">Sign Up</router-link>-->
      <!--          </li>-->
      <!--        </ul>-->
      <!--      </div>-->
      <!--      <div id="btn-bargar" :aria-expanded="show ? 'true' : 'false'" class="bargar-item" title="Nav button"-->
      <!--           @click="show = !show">-->
      <!--        <svg class="bargar" fill="none" height="35" viewBox="0 0 35 35" width="35">-->
      <!--          <path-->
      <!--              d="M32.7564 6.28202H2.24362C1.0042 6.28202 0 5.27782 0 4.03847C0 2.79911 1.0042 1.79485 2.24362 1.79485H32.7564C33.9958 1.79485 35 2.79904 35 4.03847C35 5.27789 33.9958 6.28202 32.7564 6.28202Z"-->
      <!--              fill="#F2F2F2"/>-->
      <!--          <path-->
      <!--              d="M20.1923 19.7436H2.24362C1.0042 19.7436 0 18.7394 0 17.5C0 16.2606 1.0042 15.2564 2.24362 15.2564H20.1923C21.4317 15.2564 22.436 16.2606 22.436 17.5C22.436 18.7394 21.4317 19.7436 20.1923 19.7436Z"-->
      <!--              fill="#F2F2F2"/>-->
      <!--          <path-->
      <!--              d="M32.7562 19.7436H29.1665C27.9271 19.7436 26.9229 18.7394 26.9229 17.5C26.9229 16.2606 27.9271 15.2564 29.1665 15.2564H32.7562C33.9956 15.2564 34.9998 16.2606 34.9998 17.5C34.9998 18.7394 33.9956 19.7436 32.7562 19.7436Z"-->
      <!--              fill="#2D9CDB"/>-->
      <!--          <path-->
      <!--              d="M32.7564 33.2052H2.24362C1.0042 33.2052 0 32.2009 0 30.9615C0 29.7222 1.0042 28.7179 2.24362 28.7179H32.7564C33.9958 28.7179 35 29.7221 35 30.9615C35 32.2009 33.9958 33.2052 32.7564 33.2052Z"-->
      <!--              fill="#F2F2F2"/>-->
      <!--        </svg>-->

      <!--      </div>-->
      <!--    </nav>-->
      <!--    <div v-if="show" id="nav-items" class="min-nav-items">-->
      <!--      <div>-->
      <!--        <ul>-->
      <!--          <li>-->
      <!--            <router-link to="/">Home</router-link>-->
      <!--          </li>-->
      <!--          <li>-->
      <!--            <router-link to="/aboutUs">About</router-link>-->
      <!--          </li>-->
      <!--          <li>-->
      <!--            <router-link to="/contactUs">Contacts</router-link>-->
      <!--          </li>-->
      <!--        </ul>-->
      <!--        <ul v-if="TokenUser">-->
      <!--          <li class="siginUp"><a href="javascript:void(0)" @click.prevent="handleClick">Log Out</a></li>-->
      <!--          <li>-->
      <!--            <router-link to="/mainPage">Dashboard</router-link>-->
      <!--          </li>-->
      <!--        </ul>-->
      <!--        <ul v-if="!TokenUser ">-->
      <!--          <li>-->
      <!--            <router-link to="/login">Login</router-link>-->
      <!--          </li>-->
      <!--          <li class="siginUp">-->
      <!--            <router-link to="/register">Sign Up</router-link>-->
      <!--          </li>-->
      <!--        </ul>-->
      <!--      </div>-->
      <!--    </div>-->
    </div>
    <div class="menu__responsive">

    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "Nav-Bar",
  data() {
    return {
      show: false,
      // token :false
    }
  },
  methods: {
    async handleClick() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      await axios.put('/api/users/logout');
      localStorage.removeItem('csrfToken');
      this.$store.dispatch('user', null);
      this.$store.dispatch('TokenUser', null);
      this.$store.dispatch('deviceInfoAdd', null);
      this.$router.push('/login');
    }
  },
  computed: {
    ...mapGetters(['user', 'deviceInfoAdd', 'TokenUser'])
  }
}
</script>

<style scoped>
@import "../../assets/css/_navBarStyle.css";
</style>







