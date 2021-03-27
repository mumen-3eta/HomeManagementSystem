<template>
  <div>
    <div class="container__ourTeamSc">
      <h2 v-animate-onscroll="'animated fadeIn'" class="container__ourTeamSc-title">Our Team</h2>
      <div class="main__ourTeamSc">
        <carousel :autoplay="true"
                  :autoplaySpeed="2000"
                  :dots="true"
                  :loop="true"
                  :nav="false"
                  :responsive="{0:{items:1},600:{items:2},1200:{items:3},1800:{items:4}}">
          <div v-for="(MemberTeam , index) in ListProjectTeams" :key="index"
               v-animate-onscroll="'animated fadeIn fadeInUp'" class="Member__Team">
            <div class="Member__Team-img">
              <img :alt="MemberTeam.ImagePath" :src="MemberTeam.ImagePath"
                   class="Member__img">
            </div>
            <div class="Member__Team--group">
              <h3 class="Member__Team-name">{{ MemberTeam.name }}</h3>
              <p class="Member__Team-job">{{ MemberTeam.job }}</p>
              <div class="Social_media-main">
                <ul class="Social_media-ul">
                  <li v-for="(SocialMedia,i) in MemberTeam.Social_media_list " :key="i" class="Social_media-list">
                    <a :href="SocialMedia.link" class="Social_media-link" target="_blank">
                      <i :class="SocialMedia.Type"></i>
                    </a>
                  </li>
                </ul>
              </div>
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
  data() {
    return {
      ListProjectTeams: [
        {
          ImagePath: require(`@/assets/img/welcomePage/imgTeam/osama.jpg`),
          name: "Osama Younis",
          job: "BackEnd Developer",
          Social_media_list: [
            {
              link: "https://www.facebook.com/Osama.w.you/",
              Type: "fab fa-facebook-f",
            },
            {
              link: "#",
              Type: "fab fa-twitter",
            },
            {
              link: "#",
              Type: "fab fa-linkedin-in",
            },
            {
              link: "https://github.com/Osama-you",
              Type: "fab fa-github",
            },
          ],
        },
        {
          ImagePath: require(`@/assets/img/welcomePage/imgTeam/AboObada.jpg`),
          name: "Ahmed Iqtifan",
          job: "HardWar Developer",
          Social_media_list: [
            {
              link: "https://www.facebook.com/profile.php?id=100003143624009",
              Type: "fab fa-facebook-f",
            },
            {
              link: "#",
              Type: "fab fa-twitter",
            },
            {
              link: "#",
              Type: "fab fa-linkedin-in",
            },
            {
              link: "#",
              Type: "fab fa-github",
            },
          ],
        },
        {
          ImagePath: require(`@/assets/img/welcomePage/imgTeam/A7medQeshta.jpg`),
          name: "Ahmed Qeshta",
          job: "FrontEnd Developer",
          Social_media_list: [
            {
              link: "https://www.facebook.com/A7medQeshta",
              Type: "fab fa-facebook-f",
            },
            {
              link: "#",
              Type: "fab fa-twitter",
            },
            {
              link: "#",
              Type: "fab fa-linkedin-in",
            },
            {
              link: "https://github.com/AhmedQeshta",
              Type: "fab fa-github",
            },
          ],
        },
        {
          ImagePath: require(`@/assets/img/welcomePage/imgTeam/Alaa.png`),
          name: "Alaa Shurrab",
          job: "BackEnd Developer",
          Social_media_list: [
            {
              link: "https://www.facebook.com/alaa.shurrab.39",
              Type: "fab fa-facebook-f",
            },
            {
              link: "#",
              Type: "fab fa-twitter",
            },
            {
              link: "#",
              Type: "fab fa-linkedin-in",
            },
            {
              link: "https://github.com/AlaaShurrab",
              Type: "fab fa-github",
            },
          ],
        },
        {
          ImagePath: require(`@/assets/img/welcomePage/imgTeam/mumen.jpg`),
          name: "Mumen Abu Aita",
          job: "Android Developer",
          Social_media_list: [
            {
              link: "https://www.facebook.com/Mumen.Aita",
              Type: "fab fa-facebook-f",
            },
            {
              link: "#",
              Type: "fab fa-twitter",
            },
            {
              link: "#",
              Type: "fab fa-linkedin-in",
            },
            {
              link: "https://github.com/mumen-3eta",
              Type: "fab fa-github",
            },
          ],
        },
      ],
    }
  },
}
</script>

<style scoped>
@import "../../assets/css/_ourTeamScStyle.css";
</style>
