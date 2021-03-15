<template>
  <div class="div__nav">
    <nav class="nav">
      <ul class="nav__list" role="menubar">
        <!--   For All     -->
        <li :class="['nav__item',this.$route.path === '/v2/main/home' ? 'nav__item--isActive':'']"
            aria-label="Main Home">
          <router-link :to="{path:'/v2/main/home'}" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-home nav__icon"></i>
          </router-link>
        </li>
        <li :class="['nav__item',this.$route.path === '/v2/main/profile' ? 'nav__item--isActive':'']"
            aria-label="Profile">
          <router-link :to="{path:'/v2/main/profile'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="fas fa-user-tie nav__icon"></i>
          </router-link>
        </li>
        <!--  End  For All     -->

        <!--   For user     -->
        <li v-if="!user.is_admin"
            :class="['nav__item',this.$route.path === '/v2/main/device/add' ? 'nav__item--isActive':'']"
            aria-label="Add Main Device">
          <router-link :to="{path:'/v2/main/device/add'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="far fa-desktop nav__icon"></i>
          </router-link>
        </li>
        <!--  End  For user    -->
        <!--   For Admin     -->
        <li v-if="user.is_admin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/processor' ? 'nav__item--isActive':'']"
            aria-label="Add Processor">
          <router-link :to="{path:'/v2/main/admin/create/processor'}" aria-label="heart"
                       class="nav__link focus--box-shadow" role="menuitem">
            <i class="fas fa-laptop-medical nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller' ? 'nav__item--isActive':'']"
            aria-label="Add Controller">
          <router-link :to="{path:'/v2/main/admin/create/controller'}" aria-label="heart"
                       class="nav__link focus--box-shadow" role="menuitem">
            <i class="fas fa-laptop nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller/type' ? 'nav__item--isActive':'']"
            aria-label="Add Type Controller">
          <router-link :to="{path:'/v2/main/admin/create/controller/type'}" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-cubes nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/create/controller/location' ? 'nav__item--isActive':'']"
            aria-label="Add Location Controller">
          <router-link :to="{path:'/v2/main/admin/create/controller/location'}" class="nav__link focus--box-shadow"
                       role="menuitem">
            <i class="fas fa-map-marker-alt nav__icon"></i>
          </router-link>
        </li>
        <li v-if="user.is_admin"
            :class="['nav__item',this.$route.path === '/v2/main/admin/all/messages' ? 'nav__item--isActive':'']"
            aria-label="All Messages">
          <router-link :to="{path:'/v2/main/admin/all/messages'}" class="nav__link focus--box-shadow" role="menuitem">
            <i class="far fa-comment-dots nav__icon"></i>
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
      z-index: 20;
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
  z-index: 9;
  /*margin: -2rem 0.8rem 0 0.8rem;*/
}

</style>

