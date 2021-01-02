<template>
  <header class="header">
    <div class="header__wrapper">
      <form action="" class="search">
        <button class="search__button focus--box-shadow" type="submit">
          <i class="fa fa-search"></i>
        </button>
        <input class="search__input focus--box-shadow" placeholder="Search ..." type="search">
      </form>
      <div class="profile">
        <button class="profile__button" type="button">
          <span class="profile__name">Team Project</span>
          <img alt="profile image" class="profile__img" src="@/assets/img/MainHomePage/user.jpg">
        </button>
      </div>
    </div>
  </header><!-- End HEADER -->
</template>

<script>
export default {
  name: "HeaderMain"
}
</script>

