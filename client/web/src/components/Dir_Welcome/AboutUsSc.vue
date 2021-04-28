<template>
  <div>
    <div class="container__aboutUsSc">
      <div v-animate-onscroll="'animated fadeIn'" class="main__aboutUsSc">
        <h2 v-animate-onscroll.repeat="'animated fadeIn fadeInUp'" class="main__aboutUsSc-mainTitle">
          {{ lang === 'en' ? AboutUsInfo_EN.MainTitle : AboutUsInfo_AR.MainTitle }}
        </h2>
        <h4 v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="main__aboutUsSc-Title">
          {{ lang === 'en' ? AboutUsInfo_EN.SubTitle : AboutUsInfo_AR.SubTitle }}
        </h4>
        <p v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="main__aboutUsSc-body">
          {{ lang === 'en' ? AboutUsInfo_EN.body : AboutUsInfo_AR.body }}
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
      lang: localStorage.getItem('lang') || 'en',
      AboutUsInfo_EN: {
        MainTitle: 'About Us',
        SubTitle: 'We are students at the Islamic University. We are working on this project to graduate from the College of Engineering, Computer Department This application aims to facilitate the user\'s control of home appliances',
        body: 'We are five graduate students and this is our graduation project and We selected this project because we think it will benefit and solve a problem for it’s users.',
      },
      AboutUsInfo_AR: {
        MainTitle: 'معلومات عنا',
        SubTitle: '.نحن طلاب في الجامعة الاسلامية ، نعمل على هذا المشروع لنتخرج من كلية الهندسة قسم حاسوب ، يهدف هذا التطبيق إلى تسهيل تحكم المستخدم في الأجهزة المنزلية',
        body: 'نحن خمسة طلاب دراسات عليا وهذا هو مشروع التخرج الخاص بنا وقد اخترنا هذا المشروع لأننا نعتقد أنه سيفيد ويحل مشكلة لمستخدميه',
      }
    }
  },
}
</script>

<style scoped>
@import "../../assets/css/_aboutUsScStyle.css";
</style>
