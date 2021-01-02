<template>
  <div class="wrapper">
    <nav class="nav">
      <ul class="nav__list" role="menubar">
        <li class="nav__item nav__item--isActive">
          <a aria-label="Home" class="nav__link focus--box-shadow" href="#" role="menuitem">
            <i class="fas fa-home nav__icon"></i>
            <!-- <img class="nav__icon" src="img/home.svg" alt=""> -->
          </a>
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

    <main class="main">
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
      <!--   ********** body content can change it here *****************-->
      <!--   section number 1     -->
      <section class="section">
        <header class="section__header">
          <h2 class="section__title">Teams</h2>
          <a class="section__link" href="">View All</a>
        </header>
        <ul class="team">
          <li class="team__item">
            <a class="team__link focus--box-shadow" href="">
              <div class="team__header">
                <ul class="photo">
                  <li class="photo__item">
                    <span class="photo__substrate">+2</span>
                  </li>
                  <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                  </li>
                  <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                  </li>
                </ul>
                <button class="setting setting--absolute foucs--box-shadow" type="button">
                  <i class="fa fa-ellipsis-v"></i>
                </button>
              </div>
              <div class="team__inform">
                <p class="team__name">Product presentation</p>
                <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
              </div>
            </a>
          </li>
          <li class="team__item">
            <a class="team__link focus--box-shadow" href="">
              <div class="team__header">
                <ul class="photo">
                  <li class="photo__item">
                    <span class="photo__substrate">+2</span>
                  </li>
                  <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                  </li>
                  <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                  </li>
                </ul>
                <button class="setting setting--absolute foucs--box-shadow" type="button">
                  <i class="fa fa-ellipsis-v"></i>
                </button>
              </div>
              <div class="team__inform">
                <p class="team__name">Product presentation</p>
                <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
              </div>
            </a>
          </li>
          <li class="team__item">
            <a class="team__link focus--box-shadow" href="">
              <div class="team__header">
                <ul class="photo">
                  <li class="photo__item">
                    <span class="photo__substrate">+2</span>
                  </li>
                  <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                  </li>
                  <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                  </li>
                </ul>
                <button class="setting setting--absolute foucs--box-shadow" type="button">
                  <i class="fa fa-ellipsis-v"></i>
                </button>
              </div>
              <div class="team__inform">
                <p class="team__name">Product presentation</p>
                <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
              </div>
            </a>
          </li>
        </ul>
      </section><!-- End SECTION -->
      <!--   section number 2     -->
      <section class="section section__mTop">
        <header class="section__header">
          <h2 class="section__title">Project</h2>
          <div class="section__control">
            <button class="section__button  focus--box-shadow" type="button">
              <i class="fas fa-cog"></i>
            </button>
            <button class="section__button section__button--painted focus--box-shadow" type="button">
              <i class="fas fa-plus"></i>
            </button>
          </div>
        </header>
        <ul class="project">
          <li class="project__item">
            <a class="project__link focus--box-shadow" href="">
              <div class="project__wrapper">
                <div class="project__element project__icon">
                  <div class="icon icon--viking">
                    <i class="far fa-handshake"></i>
                  </div>
                </div>
                <div class="project__element project__inform">
                  <span class="project__inform-name">Product presentation</span>
                </div>
                <div class="project__element project__photo">
                  <ul class="photo">
                    <li class="photo__item">
                      <span class="photo__substrate">+2</span>
                    </li>
                    <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                    </li>
                    <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                    </li>
                  </ul>
                </div>
                <div class="project__element project__date">
                  <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
                </div>
                <div class="project__element project__status">
                  <span class="status status--published">Published</span>
                </div>
                <div class="project__element project__setting">
                  <button class="setting setting--rotate focus--box-shadow" type="button">
                    <i class="fas fa-ellipsis-v"></i>
                  </button>
                </div>
              </div>
            </a>
          </li>
          <li class="project__item">
            <a class="project__link focus--box-shadow" href="">
              <div class="project__wrapper">
                <div class="project__element project__icon">
                  <div class="icon icon--rajah">
                    <i class="far fa-handshake"></i>
                  </div>
                </div>
                <div class="project__element project__inform">
                  <span class="project__inform-name">Product presentation</span>
                </div>
                <div class="project__element project__photo">
                  <ul class="photo">
                    <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                    </li>
                  </ul>
                </div>
                <div class="project__element project__date">
                  <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
                </div>
                <div class="project__element project__status">
                  <span class="status status--published">Published</span>
                </div>
                <div class="project__element project__setting">
                  <button class="setting setting--rotate focus--box-shadow" type="button">
                    <i class="fas fa-ellipsis-v"></i>
                  </button>
                </div>
              </div>
            </a>
          </li>
          <li class="project__item">
            <a class="project__link focus--box-shadow" href="">
              <div class="project__wrapper">
                <div class="project__element project__icon">
                  <div class="icon icon--viking">
                    <i class="far fa-handshake"></i>
                  </div>
                </div>
                <div class="project__element project__inform">
                  <span class="project__inform-name">Product presentation</span>
                </div>
                <div class="project__element project__photo">
                  <ul class="photo">
                    <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                    </li>
                  </ul>
                </div>
                <div class="project__element project__date">
                  <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
                </div>
                <div class="project__element project__status">
                  <span class="status status--in-work">in Work</span>
                </div>
                <div class="project__element project__setting">
                  <button class="setting setting--rotate focus--box-shadow" type="button">
                    <i class="fas fa-ellipsis-v"></i>
                  </button>
                </div>
              </div>
            </a>
          </li>
          <li class="project__item">
            <a class="project__link focus--box-shadow" href="">
              <div class="project__wrapper">
                <div class="project__element project__icon">
                  <div class="icon icon--rajah">
                    <i class="far fa-handshake"></i>
                  </div>
                </div>
                <div class="project__element project__inform">
                  <span class="project__inform-name">Product presentation</span>
                </div>
                <div class="project__element project__photo">
                  <ul class="photo">
                    <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                    </li>
                  </ul>
                </div>
                <div class="project__element project__date">
                  <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
                </div>
                <div class="project__element project__status">
                  <span class="status status--published">Published</span>
                </div>
                <div class="project__element project__setting">
                  <button class="setting setting--rotate focus--box-shadow" type="button">
                    <i class="fas fa-ellipsis-v"></i>
                  </button>
                </div>
              </div>
            </a>
          </li>
          <li class="project__item">
            <a class="project__link focus--box-shadow" href="">
              <div class="project__wrapper">
                <div class="project__element project__icon">
                  <div class="icon icon--viking">
                    <i class="far fa-handshake"></i>
                  </div>
                </div>
                <div class="project__element project__inform">
                  <span class="project__inform-name">Product presentation</span>
                </div>
                <div class="project__element project__photo">
                  <ul class="photo">
                    <li class="photo__item">
                                                <span class="photo__substrate">
                                                    <img alt="team photo" src="@/assets/img/MainHomePage/user.jpg">
                                                </span>
                    </li>
                  </ul>
                </div>
                <div class="project__element project__date">
                  <time class="date" datetime="2020-05-05T10:00:00">05 May, 2020</time>
                </div>
                <div class="project__element project__status">
                  <span class="status status--published">Published</span>
                </div>
                <div class="project__element project__setting">
                  <button class="setting setting--rotate focus--box-shadow" type="button">
                    <i class="fas fa-ellipsis-v"></i>
                  </button>
                </div>
              </div>
            </a>
          </li>
        </ul>
      </section><!-- End SECTION -->

    </main><!-- End Main -->

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
          <button class="profile-main__setting focus--box-shadow" type="button">
            <img alt="profile image user" class="profile-main__photo" src="@/assets/img/MainHomePage/user.jpg">
          </button>
          <h1 class="profile-main__name">Team Project</h1>
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
  </div>
</template>

<script>
export default {
  name: "MainHomePage"
}
</script>

<style scoped>
@import "../assets/css/ClearStyle/normalize.css";
@import "../assets/css/Pages_Style/MainHomePage.css";
</style>
