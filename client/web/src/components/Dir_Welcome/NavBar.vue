<template>
  <div class="nav--Z-index">
    <!--  nav max width~~>1000  -->
    <div id="main__nav" class="main__nav">
      <nav class="main__nav_content">
        <div class="nav__logo">
          <div class="nav__items">
            <img id="image_logo" alt="logo" src="@/assets/img/Logo.svg">
          </div>
        </div>
        <div class="ul-list-max">
          <ul class="nav__element">
            <li class="nav__element-list"><a class="nav__link-list" href="#HomeSection">Home</a></li>
            <li class="nav__element-list"><a class="nav__link-list" href="#AboutUs">About us</a></li>
            <li class="nav__element-list"><a class="nav__link-list" href="#contactUsSection">contact us</a></li>
            <li class="nav__element-list"><a class="nav__link-list" href="#ourTeamSection">our Team</a></li>
          </ul>
        </div>
        <div v-if="!TokenUser" class="nav_btn_items">
          <router-link class="nav_link-btn" to="/v2/login">
            <button class="nav__items-btn nav__items-btnFirst" type="button">
              Login
            </button>
          </router-link>
          <router-link class="nav_link-btn" to="/v2/register">
            <button class="nav__items-btn" type="button">
              SigUp
            </button>
          </router-link>
        </div>
        <div v-if="TokenUser" class="nav_btn_items">
          <a class="nav_link-btn">
            <button class="nav__items-btn nav__items-btnFirst" type="button" @click.prevent="handleClick">
              logout
            </button>
          </a>
          <router-link class="nav_link-btn" to="/v2/main/home">
            <button class="nav__items-btn" type="button">
              Dashboard
            </button>
          </router-link>
        </div>
        <div id="btn_menu_open" class="fas__menu-btn">
          <i class="fas fa-bars menu-btn"></i>
        </div>
      </nav>
    </div>
    <!--  nav max width<~~1000  -->
    <div class="menu__responsive">
      <div id="father-list-min" class="father-list-min">
        <button id="btn_menu_close" class="fas-close__btn">
          <i class="fas fa-times "></i>
        </button>
        <ul class="nav__element-min">
          <li class="nav__element-list-min"><a class="nav__link-list-min" href="#HomeSection">Home</a></li>
          <li class="nav__element-list-min"><a class="nav__link-list-min" href="#AboutUs">About us</a></li>
          <li class="nav__element-list-min"><a class="nav__link-list-min" href="#contactUsSection">contact us</a></li>
          <li class="nav__element-list-min"><a class="nav__link-list-min" href="#ourTeamSection">our Team</a></li>
        </ul>

        <div class="nav__element-min">
          <div v-if="!TokenUser">
            <router-link class="nav_link-btn nav__items-btnFirst" to="/v2/login">
              <button class="nav__items-btn" type="button">
                Login
              </button>
            </router-link>
            <router-link class="nav_link-btn" to="/v2/register">
              <button class="nav__items-btn" type="button">
                SigUp
              </button>
            </router-link>
          </div>
          <div v-if="TokenUser">
            <a class="nav_link-btn">
              <button class="nav__items-btn nav__items-btnFirst" type="button" @click.prevent="handleClick">
                logout
              </button>
            </a>

            <router-link class="nav_link-btn" to="/v2/main/home">
              <button class="nav__items-btn" type="button">
                Dashboard
              </button>
            </router-link>

          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "Nav-Bar",
  methods: {
    async handleClick() {
      await axios.get('/api/v1/users/logout').then(async () => {
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'logout Successfully',
          toast: true,
          showConfirmButton: false,
          timer: 1500
        })
        await this.$store.dispatch('user', null);
        await this.$store.dispatch('TokenUser', null);
        await this.$store.dispatch('userProfile', null);
        await this.$store.dispatch('deviceInfoAdd', null);
        await this.$store.dispatch('processorId', null);
        await this.$store.dispatch('controllerId', null);
        await this.$store.dispatch('userProcessorIds', null);
        await this.$store.dispatch('userAllProcessor', null);
        await this.$store.dispatch('NewTypeController', null);
        await this.$store.dispatch('allTypeController', null);
        await this.$store.dispatch('NewLocationController', null);
        await this.$store.dispatch('allLocationController', null);
        await this.$store.dispatch('TypeControllerData', null);
        await this.$store.dispatch('LocationControllerData', null);
        await this.$store.dispatch('NewControllerData', null);
        await this.$router.push('/v2/login');
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: 'logout Faild',
          toast: true,
          showConfirmButton: false,
          timer: 1500
        })
        console.log("logout Faild")
      });

    },
  },
  computed: {
    ...mapGetters(['user', 'deviceInfoAdd', 'TokenUser'])
  },
  async beforeCreate() {
    await axios.get('/api/v1/users/me').then(async ({data: {user: response}}) => {
      await this.$store.dispatch('TokenUser', response);
    }).catch(async () => {
      console.log("unauthenticated")
      await this.$store.dispatch('TokenUser', null);
    });
  },
  mounted() {
    // menu open and close
    const btn_menu_open = document.getElementById("btn_menu_open");
    const btn_menu_close = document.getElementById("btn_menu_close");
    const father_list_min = document.getElementById("father-list-min");
    btn_menu_open.addEventListener("click", function () {
      father_list_min.style.opacity = 1;
      father_list_min.classList.toggle("father-list-min");
    });
    btn_menu_close.addEventListener("click", function () {
      father_list_min.style.opacity = 0;
      father_list_min.classList.toggle("father-list-min");
    });
    window.onscroll = function () {
      scrollFunction();
    };

    function scrollFunction() {
      if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
        document.getElementById("main__nav").style.backgroundColor = "rgba(7,6,6,0.9)";
        document.getElementById("main__nav").style.boxShadow = "0 0 0 3px #70cbcb";
        document.getElementById("image_logo").style.width = "75%";
        document.getElementById("up_Button").style.display = "block";
      } else {
        document.getElementById("main__nav").style.backgroundColor = "";
        document.getElementById("image_logo").style.width = "";
        document.getElementById("main__nav").style.boxShadow = "";
        document.getElementById("up_Button").style.display = "none";
      }
    }

    document.getElementById("up_Button").addEventListener("click", function () {
      document.body.scrollTop = 0; // For Safari
      document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    });
  }
}
</script>

<style scoped>
@import "../../assets/css/_navBarStyle.css";
</style>







