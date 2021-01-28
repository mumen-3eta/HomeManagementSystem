<template>
  <div>
    <div class="profile__Main-container">
      <div class="profile__Main-containerInfo">
        <div class="profile__Main-Info">
          <img v-show="user.profileInfo.image" :src="user.profileInfo.image" alt="profile image" class="p__Main-image">
          <h3 v-show="user.basicInfo.userName">{{ user.basicInfo.userName }}</h3>
          <h6 v-show="user.basicInfo.email">{{ user.basicInfo.email }}</h6>
        </div>
      </div>
      <div class="profile__Main-containerEditInfo">
        <h2 class="profile__Main-EditInfoH2">Update Your Profile</h2>
        <div class="profile__Main-EditInfo">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "ProfileUserMain",
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const {user} = (await axios.get('/api/v1/users/me')).data;
    await this.$store.dispatch('user', user);
  },
  computed: {
    ...mapGetters(['user', 'TokenUser'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_ProfileUserMainStyle.css";
</style>
