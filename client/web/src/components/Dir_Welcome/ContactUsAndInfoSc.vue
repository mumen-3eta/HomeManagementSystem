<template>
  <div>
    <div class="container__contactUsAndInfoSc">
      <div class="container__infoBox-shadow"></div>
      <div v-animate-onscroll.repeat="'animated fadeIn'" class="contactUs-main__section ">
        <div v-animate-onscroll.repeat="'animated fadeInUp'" class="contactus__title">
          <h2 class="contactus__title-h">
            {{ lang === 'en' ? SiteContactUsInfo_EN.MainTitle : SiteContactUsInfo_AR.MainTitle }}</h2>
        </div>
        <div :class=" lang==='ar' ? 'contactUs__info direction-rtl' :'contactUs__info direction-ltr'">
          <div v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="information__Section">
            <h3 class="information__Section-title">
              {{ lang === 'en' ? SiteContactUsInfo_EN.SectionTitle : SiteContactUsInfo_AR.SectionTitle }}</h3>
            <p class="information__Section-p">
              {{ lang === 'en' ? SiteContactUsInfo_EN.SectionBody : SiteContactUsInfo_AR.SectionBody }}</p>
            <ul class="container__contactInfo">
              <li v-for="(content , index) in lang === 'en' ? SiteContactUsInfo_EN.ContactUsList : SiteContactUsInfo_AR.ContactUsList"
                  :key="index"
                  class="container-contactUs__list">
                <i :class="content.Type " style="margin-left: 0.5rem"></i>
                <p class="container-contactUs__p">{{ content.BodyContent }}</p>
              </li>
            </ul>

          </div>
          <!--          :class=" lang==='ar' ? 'labelInputContact_AR' :'labelInputContact'"-->
          <div v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="contactMsg__Section">
            <form class="form__contactUs" @submit.prevent="checkSendMessage">
              <div class="form__contactUs-group">
                <label id="lab_EmailInputContact" :class=" lang==='ar' ? 'labelInputContact_AR' :'labelInputContact'"
                       for="EmailInputContact">{{ $t('contactUs.email') }}</label>
                <input id="EmailInputContact" v-model.trim="Email" class="InputContact" type="email">
                <p class="error_style mTop__5">{{ errors.errorEmail }}</p>
              </div>
              <div class="form__contactUs-group">
                <label id="lab_SubjectInputContact" :class=" lang==='ar' ? 'labelInputContact_AR' :'labelInputContact'"
                       for="SubjectInputContact">{{ $t('contactUs.subject') }}</label>
                <input id="SubjectInputContact" v-model.trim="Subject" class="InputContact" type="text">
                <p class="error_style mTop__5">{{ errors.errorSubject }}</p>
              </div>
              <div class="form__contactUs-group">
                <label id="lab_MsgInputContact" :class=" lang==='ar' ? 'labelInputContact_AR' :'labelInputContact'"
                       for="MsgInputContact">{{ $t('contactUs.message') }}</label>
                <textarea id="MsgInputContact" v-model.trim="Message" class="InputContact" cols="30"
                          rows="10"></textarea>
                <p class="error_style">{{ errors.errorMessage }}</p>
              </div>
              <div class="form__contactUs-group">
                <button class="form__contactUs-btn" type="submit">{{ $t('contactUs.send') }}</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: "ContactUsAndInfoSc",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      socket: null,
      Email: null,
      Subject: null,
      Message: null,
      errors: {
        errorEmail: null,
        errorSubject: null,
        errorMessage: null,
      },
      SiteContactUsInfo_EN: {
        MainTitle: 'Contact the HMSy team',
        SectionTitle: 'Let\'s talk about the future of the internet',
        SectionBody: 'We\'re here to answer your questions and discuss the decentralized future of the internet.',
        ContactUsList: [
          {Type: 'fas fa-map-marker-alt', BodyContent: 'Palestine - Gaza Strip',},
          {Type: 'fas fa-phone-volume', BodyContent: '+970-XXX-XXX-XXX',},
          {Type: 'fas fa-envelope', BodyContent: 'info_HMSy@HMSy.com',},
        ],
      },
      SiteContactUsInfo_AR: {
        MainTitle: 'HMSy اتصل بفريق ',
        SectionTitle: 'لنتحدث عن مستقبل الإنترنت',
        SectionBody: 'نحن هنا للإجابة على أسئلتك ومناقشة المستقبل اللامركزي للإنترنت.',
        ContactUsList: [
          {Type: 'fas fa-map-marker-alt', BodyContent: 'فلسطين - قطاع غزة',},
          {Type: 'fas fa-phone-volume', BodyContent: '+970-XXX-XXX-XXX',},
          {Type: 'fas fa-envelope', BodyContent: 'info_HMSy@HMSy.com',},
        ],
      }
    }
  },
  methods: {
    checkSendMessage() {
      if (this.Email) {
        let email = this.Email;
        let EmailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let CheckEmail = EmailRegex.test(email);//true or false
        if (!CheckEmail) {
          this.errors.errorEmail = "Sorry! Email Faild, must be (@) and (.) and ignored space";
          setTimeout(() => {
            this.errors.errorEmail = null;
          }, 3000);
          return false;
        }
      }
      if (this.Subject) {
        let numberWord = this.Subject.match(/\w+/g).length;
        let numberCharacter = this.Subject.length;
        if (numberCharacter >= 60 || numberCharacter <= 0) {
          this.errors.errorSubject = "Sorry! Characters must be less than 60 character";
          setTimeout(() => {
            this.errors.errorSubject = null;
          }, 3000);
          return false;
        }
        if (numberWord >= 20 || numberWord <= 0) {
          this.errors.errorSubject = "Sorry! Words must be less than 10 Word";
          setTimeout(() => {
            this.errors.errorSubject = null;
          }, 3000);
          return false;
        }
      }
      if (this.Message) {
        let numberWordMessage = this.Message.match(/\w+/g).length;
        let numberCharacterMessage = this.Message.length;
        if (numberCharacterMessage >= 1000 || numberCharacterMessage <= 0) {
          this.errors.errorMessage = "Sorry! Characters must be less than 1000 character";
          setTimeout(() => {
            this.errors.errorMessage = null;
          }, 3000);
          return false;
        }
        if (numberWordMessage >= 250 || numberWordMessage <= 0) {
          this.errors.errorMessage = "Sorry! Words must be less than 250 Word";
          setTimeout(() => {
            this.errors.errorMessage = null;
          }, 3000);
          return false;
        }
      }
      if (this.Email || this.Subject || this.Message) {
        /* Email , Subject , Message*/
        this.socket.emit("all_messages", {email: this.Email, subject: this.Subject, message: this.Message});
        // section Api for send message
        // try {
        //    code..
        // }catch (e) {
        //    catch errors..
        // }
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Thank you, Send it',
          text: "The message has been sent, you will be contacted in the coming hours on the same email!",
          showConfirmButton: false,
          timer: 1500
        })
        this.EmptyFrom();//empty form
      }

    },
    EmptyFrom() {
      this.Email = null;
      this.Subject = null;
      this.Message = null;
      document.getElementById("lab_EmailInputContact").style.backgroundColor = "";
      document.getElementById("lab_EmailInputContact").classList.remove("AddMoved");
      document.getElementById("lab_SubjectInputContact").style.backgroundColor = "";
      document.getElementById("lab_SubjectInputContact").classList.remove("AddMoved");
      document.getElementById("lab_MsgInputContact").style.backgroundColor = "";
      document.getElementById("lab_MsgInputContact").classList.remove("AddMoved");
    },
  },
  mounted() {
    const input1 = document.getElementById("EmailInputContact");
    const labelInput1 = document.getElementById("lab_EmailInputContact");
    const input2 = document.getElementById("SubjectInputContact");
    const labelInput2 = document.getElementById("lab_SubjectInputContact");
    const input3 = document.getElementById("MsgInputContact");
    const labelInput3 = document.getElementById("lab_MsgInputContact");


    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "AddMoved");
      labelInput1.style.backgroundColor = "#fff";
      SlidUp(input1, "AddBorder");

    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "AddMoved");
      labelInput2.style.backgroundColor = "#fff";
      SlidUp(input2, "AddBorder");
    })
    input3.addEventListener("focusin", function () {
      SlidUp(labelInput3, "AddMoved");
      labelInput3.style.backgroundColor = "#fff";
      SlidUp(input3, "AddBorder");
    })


    input1.addEventListener("focusout", function () {
      if (!input1.value) {
        SlidDown(labelInput1, "AddMoved");
        SlidDown(input1, "AddBorder");
        labelInput1.style.backgroundColor = "";
      }
    })
    input2.addEventListener("focusout", function () {
      if (!input2.value) {
        SlidDown(labelInput2, "AddMoved");
        SlidDown(input2, "AddBorder");
        labelInput2.style.backgroundColor = "";
      }
    })
    input3.addEventListener("focusout", function () {
      if (!input3.value) {
        SlidDown(labelInput3, "AddMoved");
        SlidDown(input3, "AddBorder");
        labelInput3.style.backgroundColor = "";
      }
    })


    function SlidUp(input, nameClass) {
      input.classList.add(nameClass);
    }

    function SlidDown(input, nameClass) {
      input.classList.remove(nameClass);
    }

  },
}
</script>

<style scoped>
@import "../../assets/css/_contactUsAndInfoScStyle.css";

.mTop__5 {
  margin-top: -0.8rem;
  margin-left: 0.5rem;
  text-transform: capitalize;
}
</style>
