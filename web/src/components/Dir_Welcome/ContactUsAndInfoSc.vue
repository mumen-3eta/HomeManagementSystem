<template>
  <div>
    <div class="container__contactUsAndInfoSc">
      <div class="container__infoBox-shadow"></div>
      <div v-animate-onscroll.repeat="'animated fadeIn'" class="contactUs-main__section ">
        <div v-animate-onscroll.repeat="'animated fadeInUp'" class="contactus__title">
          <h2 class="contactus__title-h">Contact the Pied Piper team</h2>
        </div>
        <div class="contactUs__info">
          <div v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="information__Section">
            <h3 class="information__Section-title">Let's talk about the future of the internet</h3>
            <p class="information__Section-p">We're here to answer your questions and discuss the decentralized future
              of the internet.</p>
            <ul class="container__contactInfo">
              <li class="container-contactUs__list">
                <i class="fas fa-map-marker-alt"></i>
                <p class="container-contactUs__p">Pied Piper HQ - 59 Silicon Av.</p>
              </li>
              <li class="container-contactUs__list">
                <i class="fas fa-phone-volume"></i>
                <p class="container-contactUs__p">555-333-555</p>
              </li>
              <li class="container-contactUs__list">
                <i class="fas fa-envelope"></i>
                <p class="container-contactUs__p">pay-the-piper@piedpiper.com</p>
              </li>
            </ul>

          </div>
          <div v-animate-onscroll.repeat="'animated fadeIn fadeInLeft'" class="contactMsg__Section">
            <form class="form__contactUs" @submit.prevent="checkSendMessage">
              <div class="form__contactUs-group">
                <label id="lab_EmailInputContact" class="labelInputContact "
                       for="EmailInputContact">Email</label>
                <input id="EmailInputContact" v-model.trim="Email" class="InputContact" type="email">
                <p class="error_style mTop__5">{{ errors.errorEmail }}</p>
              </div>
              <div class="form__contactUs-group">
                <label id="lab_SubjectInputContact" class="labelInputContact" for="SubjectInputContact">Subject</label>
                <input id="SubjectInputContact" v-model.trim="Subject" class="InputContact" type="text">
                <p class="error_style mTop__5">{{ errors.errorSubject }}</p>
              </div>
              <div class="form__contactUs-group">
                <label id="lab_MsgInputContact" class="labelInputContact" for="MsgInputContact">Message</label>
                <textarea id="MsgInputContact" v-model.trim="Message" class="InputContact" cols="30"
                          rows="10"></textarea>
                <p class="error_style">{{ errors.errorMessage }}</p>
              </div>
              <div class="form__contactUs-group">
                <button class="form__contactUs-btn" type="submit">Send</button>
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
      Email: null,
      Subject: null,
      Message: null,
      errors: {
        errorEmail: null,
        errorSubject: null,
        errorMessage: null,
      },
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
