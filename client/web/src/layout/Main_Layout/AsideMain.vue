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
        <div v-show="user.profileInfo.image" class="profile-main__setting focus--box-shadow" type="button">
          <img :src="user.profileInfo.image" alt="profile image user" class="profile-main__photo">
        </div>
        <h1 v-show="user.basicInfo.userName" class="profile-main__name">{{ user.basicInfo.userName }}</h1>
        <h6 v-show="user.basicInfo.email" class="profile-main__name">{{ user.basicInfo.email }}</h6>
      </div>
      <ul class="statistics">
        <li class="statistics__entry">
          <a class="statistics__entry-description" href="">Team</a>
          <span class="statistics__entry-quantity">3</span>
        </li>
        <li class="statistics__entry">
          <a class="statistics__entry-description" href="">Projects</a>
          <span class="statistics__entry-quantity">6</span>
        </li>
        <li class="statistics__entry">
          <a class="statistics__entry-description" href="">Feedback</a>
          <span class="statistics__entry-quantity">68</span>
        </li>
      </ul>
      <div class="banner">
        <h3 class="banner__title">Premium access</h3>
        <p class="banner__description">Create Teams without limit</p>
        <button class="banner__button" type="button">Get Premium Access</button>
      </div>
    </section>
  </aside><!-- End ASIDE -->
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "AsideMain",
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    // const {user} = (await axios.get('/api/v1/users/me')).data;
    const user = await axios.get('/api/v1/users/profile');
    await this.$store.dispatch('user', user.data.data);
  },
  computed: {
    ...mapGetters(['user', 'deviceInfoAdd', 'TokenUser'])
  },
}
</script>

