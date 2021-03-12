<template>
  <aside class="aside">
    <section class="section">
      <div class="aside__control">
        <button class="aside__button focus--box-shadow" type="button">
          <i class="fas fa-chevron-left"></i>
        </button>
        <button aria-label="You hav new feedback"
                class="aside__button aside__button--notification focus--box-shadow " type="button">
          <i class="fas fa-bell"></i>
        </button>
      </div>
      <div class="profile-main">
        <div class="profile-main__setting focus--box-shadow" type="button">
          <img
              :src="userProfile.image ? userProfile.image : 'https://img.icons8.com/metro/500/000000/user-male.png'"
              alt="profile image user"
              class="profile-main__photo">
        </div>
        <h1 v-show="userProfile.user_name" class="profile-main__name" v-text="userProfile.user_name"></h1>
        <h6 v-show="userProfile.email" class="profile-main__name" v-text="userProfile.email"></h6>
        <h6 v-if="user.isAdmin" class="profile-main__name text-secondary">Admin</h6>
      </div>
      <div v-if="!user.isAdmin" class="banner">
        <h3 class="banner__title" v-text="BannerData.bannerTitle"></h3>
        <p class="banner__description" v-text="BannerData.bannerDescription"></p>
        <button class="banner__button" type="button" v-text="BannerData.bannerButtonText"></button>
      </div>
    </section>
  </aside><!-- End ASIDE -->
</template>

<script>
// import axios from "axios";
import {mapGetters} from "vuex";
// import io from "socket.io-client";

export default {
  name: "AsideMain",
  data() {
    return {
      BannerData: {
        bannerTitle: 'Premium access',
        bannerDescription: 'Create Teams without limit',
        bannerButtonText: 'Get Premium Access',
      }
    }
  },
  // created() {
  //   this.socket = io('http://localhost:3001');
  // },
  computed: {
    ...mapGetters(['user', 'userProfile'])
  },
}
</script>

