<template>
  <aside :class="mode==='dark' ? 'aside_dark' : 'aside'">
    <section class="section">
      <div class="aside__control">
        <router-link :aria-label="lang ==='en' ? GoToWelcomePage.EN : GoToWelcomePage.AR"
                     :to="{path:'/'}">
          <button :class="mode==='dark' ? 'btn_dark' : ''" class="aside__button focus--box-shadow" type="button">
            <i class="fas fa-chevron-left"></i>
          </button>
        </router-link>
        <div
            :aria-label="lang === 'en' ? (mode === 'dark' ? 'Disable dark' : 'Enable dark') : (mode === 'dark' ? 'إلغاء تفعيل الوضع الساكن' : ' تفعيل الوضع الداكن')"
            class="form-check form-switch">
          <input id="flexSwitchCheckDefault"
                 :checked="mode === 'dark' ? 'checked' : '' "
                 class="form-check-input" type="checkbox" @change="ChangeDarkMode">
          <label class="form-check-label" for="flexSwitchCheckDefault" style="margin-left: 0.5rem">
            {{
              lang === 'en' ? (mode === 'dark' ? 'Dark Mode' : 'Default Mode') : (mode === 'dark' ? 'الوضع الداكن' : 'الوضع الطبيعي')
            }}
          </label>
        </div>
      </div>
      <div class="profile-main">
        <div class="profile-main__setting focus--box-shadow">
          <img
              :src="userProfile.image ? userProfile.image : 'https://img.icons8.com/metro/500/000000/user-male.png'"
              alt="profile image user"
              class="profile-main__photo">
        </div>
        <h1 v-show="userProfile.user_name" class="profile-main__name" v-text="userProfile.user_name"></h1>
        <h6 v-show="userProfile.email" class="profile-main__name" v-text="userProfile.email"></h6>
        <h6 v-if="user.is_admin" class="profile-main__name text-secondary">{{
            $t('Dashboard.Aside_Nav.text_Admin')
          }}</h6>
      </div>
      <div v-if="!user.is_admin" class="banner">
        <h3 :class="mode === 'dark' ? 'dark_text' : ''" class="banner__title"
            v-text="lang ==='en' ? BannerData_EN.bannerTitle : BannerData_AR.bannerTitle"></h3>
        <p :class="mode === 'dark' ? 'dark_text' : ''" class="banner__description"
           v-text="lang ==='en' ? BannerData_EN.bannerDescription : BannerData_AR.bannerDescription"></p>
        <button :class="mode==='dark' ? 'btn_dark' : ''" class="banner__button" type="button"
                v-text="lang ==='en' ? BannerData_EN.bannerButtonText : BannerData_AR.bannerButtonText"></button>
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
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      BannerData_EN: {
        bannerTitle: 'Premium access',
        bannerDescription: 'Create Teams without limit',
        bannerButtonText: 'Get Premium Access',
      },
      BannerData_AR: {
        bannerTitle: 'الوصول المتميز',
        bannerDescription: 'إنشاء فرق بلا حدود',
        bannerButtonText: 'احصل على وصول بريميوم',
      },
      GoToWelcomePage: {
        EN: 'Go To Welcome Page',
        AR: 'انتقل إلى صفحة الترحيب',
      }
    }
  },
  methods: {
    ChangeDarkMode() {
      window.location.reload();
      if (this.mode === 'dark') {
        localStorage.setItem('mode', 'default');
      } else {
        localStorage.setItem('mode', 'dark');
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
<style lang="scss" scoped>
.dark_text {
  color: black;
}

a {
  &:hover {
    position: relative;
  }
}

a[aria-label] {
  &:hover {
    &:after {
      content: attr(aria-label);
      padding: 4px 8px;
      position: absolute;
      left: calc(100% + 0.5rem);
      top: -25%;
      white-space: nowrap;
      z-index: 20;
      background: #949494;
      border-radius: 5px;
      color: white;
    }
  }
}

div {
  &:hover {
    position: relative;
  }
}

div[aria-label] {
  &:hover {
    &:after {
      content: attr(aria-label);
      padding: 4px 8px;
      position: absolute;
      right: calc(100% + 0.5rem);
      top: -25%;
      white-space: nowrap;
      z-index: 20;
      background: #949494;
      border-radius: 5px;
      color: white;
    }
  }
}
</style>

