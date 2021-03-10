<template>
  <div>
    <div class="container__aboutUsSc">
      <div v-animate-onscroll="'animated fadeIn'" class="main__aboutUsSc">
        <h2 v-animate-onscroll.repeat="'animated fadeIn fadeInUp'" class="main__aboutUsSc-mainTitle">
          {{ AboutUsInfo.MainTitle }}
        </h2>
        <h4 v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="main__aboutUsSc-Title">
          {{ AboutUsInfo.SubTitle }}
        </h4>
        <p v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="main__aboutUsSc-body">
          {{ AboutUsInfo.body }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AboutUsSc",
  data() {
    return {
      AboutUsInfo: {
        MainTitle: 'About Us',
        SubTitle: 'This application aims to make it easier for the user to control the house appliances',
        body: 'We are five graduate students and this is our graduation project and We selected this project because we think it will benefit and solve a problem for it’s users.',
      }
    }
  },
}
</script>

<style scoped>
@import "../../assets/css/_aboutUsScStyle.css";
</style>
