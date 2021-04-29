<template>

  <header class="header">
    <div class="header__wrapper">
      <div class="profile">
        <div class="nav_link-btn nav_lang custom-select" href="javascript:void;">
            <span class="icon_nav_lang">
             <i class="fas fa-globe-americas"></i>
            </span>
          <select v-model="lang" class="select_nav_lang " @change="changeLanguage($event)">
            <option value="en">English</option>
            <option value="ar">عربي</option>
          </select>
        </div>
      </div>
      <div v-on-clickaway="CloseDropMenu" class="profile">
        <button id="btn_Profile" class="profile__button" type="button"
                @click.prevent="OpenDropMenu">
          <span v-if="userProfile.user_name" class="profile__name">
            <i class="fas fa-caret-down"></i>{{ userProfile.user_name }}</span>
          <img :src="userProfile.image ? userProfile.image : 'https://img.icons8.com/metro/500/000000/user-male.png' "
               alt="profile image"
               class="profile__img">
        </button>
        <div id="profile__menu" class="profile__menu">
          <ul :class=" lang==='ar' ? 'profile__menu-ul direction-rtl' :'profile__menu-ul direction-ltr'">
            <li class="profile__menu-li">
              <router-link class="profile__menu-link" to="/v2/main/profile">
                <i class="fas fa-user-alt profile__menu-icon"></i>
                <p class="profile__menu-nameTitle"> {{ $t('Dashboard.Header_nav.profile') }} </p>
              </router-link>
            </li>
            <li v-if="user.is_admin" class="profile__menu-li">
              <router-link :to="{path:'/v2/main/admin/create/processor'}" class="profile__menu-link">
                <i class="fas fa-laptop-medical profile__menu-icon"></i>
                <p class="profile__menu-nameTitle">{{ $t('Dashboard.Header_nav.createProcessor') }}</p>
              </router-link>
            </li>
            <li v-if="user.is_admin" class="profile__menu-li">
              <router-link :to="{path:'/v2/main/admin/create/controller'}" class="profile__menu-link">
                <i class="fas fa-desktop profile__menu-icon"></i>
                <p class="profile__menu-nameTitle">{{ $t('Dashboard.Header_nav.createController') }}</p>
              </router-link>
            </li>
            <li v-if="user.is_admin" class="profile__menu-li">
              <router-link :to="{path:'/v2/main/admin/create/controller/type'}" class="profile__menu-link">
                <i class="fas fa-cubes profile__menu-icon"></i>
                <p class="profile__menu-nameTitle">{{ $t('Dashboard.Header_nav.addTypeController') }}</p>
              </router-link>
            </li>
            <li v-if="user.is_admin" class="profile__menu-li">
              <router-link :to="{path:'/v2/main/admin/create/controller/location'}" class="profile__menu-link">
                <i class="fas fa-map-marker-alt profile__menu-icon"></i>
                <p class="profile__menu-nameTitle">{{ $t('Dashboard.Header_nav.addLocationController') }}</p>
              </router-link>
            </li>
            <li v-if="!user.is_admin" class="profile__menu-li">
              <router-link :to="{path:'/v2/main/device/add'}" class="profile__menu-link">
                <i class="fas fa-desktop profile__menu-icon"></i>
                <p class="profile__menu-nameTitle">{{ $t('Dashboard.Header_nav.myDevice') }}</p>
              </router-link>
            </li>
            <li class="profile__menu-li" @click.prevent="handleClick">
              <a class="profile__menu-link" href="javascript:(void(0));">
                <i class="fas fa-sign-out-alt profile__menu-icon"></i>
                <p class="profile__menu-nameTitle">{{ $t('Dashboard.Header_nav.logOut') }}</p>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </header><!-- End HEADER -->

</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";
import {mixin as clickAway} from "vue-clickaway";

export default {
  name: "HeaderMain",
  mixins: [clickAway],
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
    }
  },
  methods: {
    async handleClick() {
      await axios.get('/api/v1/users/logout').then(async () => {
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: this.lang === 'en' ? 'logout Successfully' : 'تم تسجيل الخروج بنجاح',
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
          title: this.lang === 'en' ? 'logout Faild' : 'فشل تسجيل الخروج',
          toast: true,
          showConfirmButton: false,
          timer: 1500
        })
        console.log("logout Faild")
      });

    },
    OpenDropMenu() {
      document.getElementById("btn_Profile").classList.toggle("profile__menu-open");
      document.getElementById("profile__menu").classList.toggle("profile__menu-open");
    },
    CloseDropMenu() {
      document.getElementById("btn_Profile").classList.remove("profile__menu-open");
      document.getElementById("profile__menu").classList.remove("profile__menu-open");
    },
    changeLanguage(event) {
      window.location.reload();
      localStorage.setItem('lang', event.target.value);
    },
  },
  async beforeMount() {

  },
  computed: {
    ...mapGetters(['user', 'userProfile'])
  },
}
</script>

<style scoped>
.profile__menu-open {
  background-color: white;
  border-radius: 10px 10px 0 0;
  box-shadow: 1px 1px 4px #9d9c9c;
  transition: all 0.3s ease;
}

.profile__menu {
  position: absolute;
  left: -8.5rem;
  width: calc(100% + 8.5rem);
  max-width: calc(100% + 8.5rem);
  min-width: calc(100% + 8.5rem);
  height: auto;
  background-color: white;
  z-index: 999;
  box-shadow: 1px 1px 4px #9d9c9c;
  border-radius: 10px 0 10px 10px;
  display: none;
  transition: all 0.3s ease;
}

.profile__menu-open {
  display: flex;
  transition: all 0.3s ease;
}

@media (max-width: 769px) {
  .profile__menu {
    width: 13.65rem;
    max-width: 20.65rem;
    min-width: 20.65rem;
    top: 3.71rem;
    left: -16rem;
    border-radius: 10px 0 10px 10px;
  }
}

i.fa-caret-down {
  padding-right: 0.5rem;
}

.profile__menu-ul {
  width: 100%;
  display: flex;
  flex-direction: column;
  text-align: center;
  align-items: center;
  justify-content: start;
}

.profile__menu-li {
  width: 100%;
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: start;
  padding: 0.5rem;
}

.profile__menu-li:not(:last-child) {
  border-bottom: 2px solid #cfcfcf;
}

.profile__menu-li:last-child {
  background-color: #cb5959;
  border-radius: 0 0 10px 10px;
  color: white;
  transition: all 0.3s ease;
}

.profile__menu-li:last-child .profile__menu-link {
  color: white;
}

@media (hover: hover) {
  .profile__menu-li:last-child:hover {
    background-color: #a93e3e;
  }

  .profile__menu-li:last-child:hover .profile__menu-link {
    color: #fff490;
  }
}

.profile__menu-link {
  width: 100%;
  display: flex;
  text-align: center;
  align-items: center;
  justify-content: start;
}

.profile__menu-icon {
  padding: 0.125rem 1rem;
  font-size: 0.9rem;
}

.profile__menu-nameTitle {
  padding: 0.125rem 1rem;
  font-size: 1.25rem;
  font-family: Ubuntu, Arial, Helvetica, sans-serif;
  text-transform: capitalize;
  cursor: pointer;
}

</style>

