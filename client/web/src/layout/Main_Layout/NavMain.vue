<template>
  <div class="div__nav">
    <nav class="nav">
      <ul class="nav__list" role="menubar">
        <li :class="['nav__item',this.$route.path === '/v2/main/home' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/home'}" aria-label="Home" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-home nav__icon"></i>
          </router-link>
        </li>
        <li v-if="!user.basicInfo.isAdmin"
            :class="['nav__item',this.$route.path === '/v2/main/device/add' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/device/add'}" aria-label="heart" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="far fa-desktop nav__icon"></i>

          </router-link>
        </li>
        <li v-if="user.basicInfo.isAdmin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/processor' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/create/processor'}" aria-label="heart"
                       class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-laptop-medical nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.basicInfo.isAdmin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/admin/create/controller'}" aria-label="heart"
                       class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-laptop nav__icon"></i>
          </router-link>
        </li>
        <li class="nav__item">
          <a aria-label="heart" class="nav__link focus--box-shadow" href="#" role="menuitem">
            <i class="fas fa-rss nav__icon"></i>
          </a>
        </li>
        <li class="nav__item">
          <a aria-label="heart" class="nav__link focus--box-shadow" href="#" role="menuitem">
            <i class="fas fa-print nav__icon"></i>
          </a>
        </li>
        <li :class="['nav__item',this.$route.path === '/v2/main/profile' ? 'nav__item--isActive':'']">
          <router-link :to="{path:'/v2/main/profile'}" aria-label="heart" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-user-tie nav__icon"></i>
          </router-link>
        </li>
      </ul>
    </nav><!-- End NAV -->
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "NavMain",
  computed: {
    ...mapGetters(['user', 'TokenUser'])
  },
}
</script>

