<template>
  <div class="div__nav">
    <nav class="nav">
      <ul class="nav__list" role="menubar">
        <!--   For All     -->
        <li :aria-label="lang ==='en' ? ariaLabel_EN.mainHome : ariaLabel_AR.mainHome"
            :class="['nav__item',this.$route.path === '/v2/main/home' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/home'}" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-home nav__icon"></i>
          </router-link>
        </li>
        <li :aria-label="lang ==='en' ? ariaLabel_EN.profile : ariaLabel_AR.profile"
            :class="['nav__item',this.$route.path === '/v2/main/profile' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/profile'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="fas fa-user-tie nav__icon"></i>
          </router-link>
        </li>
        <!--  End  For All     -->

        <!--   For user     -->
        <li v-if="!user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.addMainDevice : ariaLabel_AR.addMainDevice"
            :class="['nav__item',this.$route.path === '/v2/main/device/add' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/device/add'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="far fa-desktop nav__icon"></i>
          </router-link>
        </li>
        <li v-if="!user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.allController : ariaLabel_AR.allController"
            :class="['nav__item',this.$route.path === '/v2/main/controller/connected' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/controller/connected'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="far fa-laptop nav__icon"></i>
          </router-link>
        </li>
        <!--  End  For user    -->
        <!--   For Admin     -->
        <li v-if="user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.createProcessor : ariaLabel_AR.createProcessor"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/processor' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/create/processor'}" aria-label="heart"
                       class="nav__link focus--box-shadow" role="menuitem">
            <i class="fas fa-laptop-medical nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.createController : ariaLabel_AR.createController"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/create/controller'}" aria-label="heart"
                       class="nav__link focus--box-shadow" role="menuitem">
            <i class="fas fa-laptop nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.addTypeController : ariaLabel_AR.addTypeController"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller/type' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/create/controller/type'}" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-cubes nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.addLocationController : ariaLabel_AR.addLocationController"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller/location' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/create/controller/location'}" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-map-marker-alt nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.allMessages : ariaLabel_AR.allMessages"
            :class="['nav__item',this.$route.path === '/v2/main/admin/all/messages' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/all/messages'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="far fa-comment-dots nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :aria-label="lang ==='en' ? ariaLabel_EN.allUser : ariaLabel_AR.allUser"
            :class="['nav__item',this.$route.path === '/v2/main/admin/all/Users' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/all/Users'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="far fa-users nav__icon"></i>
          </router-link>
        </li>
        <!--  End  For Admin    -->
      </ul>
    </nav><!-- End NAV -->
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "NavMain",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      ariaLabel_EN: {
        mainHome: 'Main Home',
        profile: 'Profile',
        addMainDevice: 'Add Main Device',
        allController: 'All Controllers',
        createProcessor: 'Create Processor',
        createController: 'Create Controller',
        addTypeController: 'Add Type Controller',
        addLocationController: 'Add Location Controller',
        allMessages: 'All Messages',
        allUser: 'All User',
      },
      ariaLabel_AR: {
        mainHome: 'الصفحة الرئيسية',
        profile: 'الملف الشخصي',
        addMainDevice: 'أضف الجهاز الرئيسي',
        allController: 'جميع وحدات التحكم',
        createProcessor: 'إنشاء المعالج',
        createController: 'إنشاء وحدة تحكم',
        addTypeController: 'إضافة نوع وحدة تحكم',
        addLocationController: 'أضف وحدة تحكم الموقع',
        allMessages: 'جميع الرسائل',
        allUser: 'جميع المستخدمين',
      }
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
}
</script>
<style lang="scss" scoped>
li {
  &:hover {
    position: relative;
  }
}

li[aria-label] {
  &:hover {
    &:after {
      content: attr(aria-label);
      padding: 4px 8px;
      position: absolute;
      left: calc(100% + 0.5rem);
      top: -25%;
      white-space: nowrap;
      z-index: 9999;
      background: #949494;
      border-radius: 5px;
      color: white;
    }
  }
}

@media (max-width: 1200px) {
  li[aria-label] {
    &:hover {
      &:after {
        left: 0;
        top: -75%;
      }
    }
  }
}

.nav {
  position: fixed;
  z-index: 999;
  /*margin: -2rem 0.8rem 0 0.8rem;*/
}

</style>

