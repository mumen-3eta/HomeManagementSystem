<template>
  <div>
    <div class="container__ourTeamSc">
      <h2 class="container__ourTeamSc-title">Our Team</h2>
      <div class="main__ourTeamSc">
        <carousel :autoplay="true" :dots="true" :nav="false"
                  :responsive="{0:{items:1},600:{items:2},1200:{items:3},1800:{items:4}}">
          <!--      member 1      -->
          <div class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/teamMember1.jpg">
            </div>
            <h3 class="Member__Team-name">Test name</h3>
            <p class="Member__Team-job">Test job</p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
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

          <!--      member 2      -->
          <div class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/teamMember2.jpeg">
            </div>
            <h3 class="Member__Team-name">Test name 2 </h3>
            <p class="Member__Team-job">Test job 2 </p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
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

          <!--      member 3      -->
          <div class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/teamMember3.jpg">
            </div>
            <h3 class="Member__Team-name">Test name 3 </h3>
            <p class="Member__Team-job">Test job 3 </p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
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

          <!--      member 4      -->
          <div class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/teamMember4.jpg">
            </div>
            <h3 class="Member__Team-name">Test name 4 </h3>
            <p class="Member__Team-job">Test job 4 </p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
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

          <!--      member 5      -->
          <div class="Member__Team">
            <div class="Member__Team-img">
              <img alt="image team member" class="Member__img"
                   src="@/assets/img/welcomePage/imgTeam/teamMember5.jpg">
            </div>
            <h3 class="Member__Team-name">Test name 5 </h3>
            <p class="Member__Team-job">Test job 5 </p>
            <div class="Social_media-main">
              <ul class="Social_media-ul">
                <li class="Social_media-list">
                  <a class="Social_media-link" href="#">
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
