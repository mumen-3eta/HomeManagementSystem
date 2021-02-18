<template>
  <div>
    <div class="container__ourTeamSc">
      <h2 v-animate-onscroll="'animated fadeIn'" class="container__ourTeamSc-title">Our Team</h2>
      <div class="main__ourTeamSc">
        <carousel :autoplay="true" :dots="true" :nav="false"
                  :responsive="{0:{items:1},600:{items:2},1200:{items:3},1800:{items:4}}">
          <!--      member 1 - osama      -->
          <div v-animate-onscroll="'animated fadeIn fadeInUp'" class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/osama.jpg">
            </div>
            <h3 class="Member__Team-name">Osama Younis</h3>
            <p class="Member__Team-job">BackEnd Developer</p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://www.facebook.com/Osama.w.you/" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-twitter"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-linkedin-in"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://github.com/Osama-you" target="_blank">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <!--      member 2 - Abo obada     -->
          <div v-animate-onscroll="'animated fadeIn fadeInUp'" class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/AboObada.jpg">
            </div>
            <h3 class="Member__Team-name">Ahmed Iqtifan</h3>
            <p class="Member__Team-job">HardWar Developer</p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://www.facebook.com/profile.php?id=100003143624009"
                     target="_blank">
                    <i class="fab fa-facebook-f"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-twitter"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-linkedin-in"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <!--      member 3 - Ahmed qeshta     -->
          <div v-animate-onscroll="'animated fadeIn fadeInUp'" class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/A7medQeshta.jpg">
            </div>
            <h3 class="Member__Team-name">Ahmed Qeshta</h3>
            <p class="Member__Team-job">FrontEnd Developer</p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://www.facebook.com/A7medQeshta" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-twitter"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-linkedin-in"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://github.com/AhmedQeshta" target="_blank">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <!--      member 4 - Alaa    -->
          <div v-animate-onscroll="'animated fadeIn fadeInUp'" class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/Alaa.png">
            </div>
            <h3 class="Member__Team-name">Alaa Shurrab</h3>
            <p class="Member__Team-job">BackEnd Developer</p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://www.facebook.com/alaa.shurrab.39" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-twitter"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-linkedin-in"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://github.com/AlaaShurrab" target="_blank">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <!--  -->
          <!--   member 5  - Mumen A. Abu Aita    -->
          <div v-animate-onscroll="'animated fadeIn fadeInUp'" class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/mumen.jpg">
            </div>
            <h3 class="Member__Team-name">Mumen Abu Aita</h3>
            <p class="Member__Team-job">Android Developer</p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://www.facebook.com/Mumen.Aita" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-twitter"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
                    <i class="fab fa-linkedin-in"></i>
                  </a>
                </li>
                <li class="Social_media-list">
                  <a class="Social_media-link" href="https://github.com/mumen-3eta" target="_blank">
                    <i class="fab fa-github"></i>
                  </a>
                </li>
              </ul>
            </div>
          </div>

        </carousel>
      </div>
    </div>
  </div>
</template>

<script>
import carousel from "vue-owl-carousel";

export default {
  name: "OurTeamSc",
  components: {carousel},
}
</script>

<style scoped>
@import "../../assets/css/_ourTeamScStyle.css";
</style>
