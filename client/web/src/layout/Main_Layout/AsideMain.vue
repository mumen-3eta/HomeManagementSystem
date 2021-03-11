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
        <!--        v-show="user.profileInfo.image"-->
        <div class="profile-main__setting focus--box-shadow" type="button">
          <!--          :src="user.profileInfo.image" -->
          <img alt="profile image user" class="profile-main__photo" src="">
        </div>
        <!--        v-show="user.basicInfo.userName"-->
        <!--        {{ user.basicInfo.userName }}-->
        <h1 class="profile-main__name">test</h1>
        <!--        v-show="user.basicInfo.email"-->
        <!--        {{ user.basicInfo.email }}-->
        <h6 class="profile-main__name"></h6>
      </div>
      <!--      v-if="!user.basicInfo.isAdmin"-->
      <div class="banner">
        <h3 class="banner__title">Premium access</h3>
        <p class="banner__description">Create Teams without limit</p>
        <button class="banner__button" type="button">Get Premium Access</button>
      </div>
    </section>
  </aside><!-- End ASIDE -->
</template>

<script>
// import axios from "axios";
import {mapGetters} from "vuex";
import io from "socket.io-client";

export default {
  name: "AsideMain",
  methods: {

    async GetProfileUser() {
      // const {data: {data: user_profileData}} = await axios.get('/api/v1/users/profile');
      // await this.$store.dispatch('user', user_profileData);
      // this.socket.emit("user_profileData", user_profileData);
      // this.socket.on("user_profileData_server", (data) => {
      //   this.$store.dispatch('user', data);
      // });
    },
  },
  beforeMount() {
    this.GetProfileUser();
  },
  created() {
    this.socket = io('http://localhost:3001');
    this.GetProfileUser();
  },
  computed: {
    ...mapGetters(['user', 'deviceInfoAdd', 'TokenUser', 'userProcessorIds'])
  },
}
</script>

