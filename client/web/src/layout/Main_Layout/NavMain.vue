<template>
  <div class="div__nav">
    <nav class="nav">
      <ul class="nav__list" role="menubar">
        <li class="nav__item nav__item--isActive">
          <router-link aria-label="Home" class="nav__link focus--box-shadow" role="menuitem" to="/v2/main/home">
            <i class="fas fa-home nav__icon"></i>
          </router-link>
        </li>
        <li class="nav__item">
          <a aria-label="heart" class="nav__link focus--box-shadow" href="#" role="menuitem">
            <i class="far fa-heart nav__icon"></i>
          </a>
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
        <li class="nav__item">
          <a aria-label="heart" class="nav__link focus--box-shadow" href="#" role="menuitem">
            <i class="fas fa-user-tie nav__icon"></i>
          </a>
        </li>
      </ul>
    </nav><!-- End NAV -->
  </div>
</template>

<script>
export default {
  name: "NavMain"
}
</script>

