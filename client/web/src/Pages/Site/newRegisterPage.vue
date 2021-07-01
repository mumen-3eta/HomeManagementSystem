<template>
  <header class="Main__Header-R">
    <main class="main__register">
      <div class="register__photo">
        <a class="nav_link-btn nav_lang custom-select" href="javascript:void;">
            <span class="icon_nav_lang">
             <i class="fas fa-globe-americas"></i>
            </span>
          <select v-model="lang" class="select_nav_lang" @change="changeLanguage($event)">
            <option value="en">English</option>
            <option value="ar">عربي</option>
          </select>
        </a>
      </div>
      <div :class=" lang==='ar' ? 'register__inform direction-rtl' :'register__inform direction-ltr'">
        <div class="close__to-home">
          <router-link :class=" lang==='ar' ? 'close__to-home_link_AR' :'close__to-home_link'" to="/"><i
              class="far fa-times-circle"></i></router-link>
        </div>
        <h2 class="register__inform-title">{{ $t('signUp.title') }}</h2>
        <p class="register__inform-minTitle">{{ $t('signUp.body') }}</p>
        <form action="#" class="register__form" @submit.prevent="checkForm">
          <div class="form__group-R">
            <label id="username_LabelInput" :class=" lang==='ar' ? 'form__input-label_AR' :'R-form__input-label'"
                   for="username_input">{{
                $t('signUp.username')
              }}</label>
            <input id="username_input" v-model.trim="userName" class="R-form__input-input" type="text">
            <hr id="line1_U" class="R-style_line">
          </div>
          <div class="form__group-R">
            <label id="email_LabelInput" :class=" lang==='ar' ? 'form__input-label_AR' :'R-form__input-label'"
                   for="email_input">{{ $t('signUp.email') }}</label>
            <input id="email_input" v-model.trim="Email" class="R-form__input-input" type="text">
            <hr id="line1_E" class="R-style_line">
          </div>
          <div class="form__group-R">
            <label id="password_LabelInput" :class=" lang==='ar' ? 'form__input-label_AR' :'R-form__input-label'"
                   for="password_input">{{
                $t('signUp.password')
              }}</label>
            <input id="password_input" v-model.trim="Password" class="R-form__input-input" type="password">
            <hr id="line2" class="R-style_line">
          </div>
          <div class="form__group-R">
            <p class="R-form__forgot">
              <a class="R-form__forgot-link" href="">{{ $t('signUp.forgotPassword') }}</a>
            </p>
          </div>

          <p class="error_style mt-3 ">{{ this.errors.ErrorEmailOrUserName }}</p>

          <div class="form__group-R">
            <button class="form__button-submit" type="submit">{{ $t('signUp.btn_text') }}</button>
          </div>
        </form>
        <div class="go__login">
          <p class="login__label">
            {{ $t('signUp.goToLogin_text') }}
            <router-link class="login__label-link" to="/v2/login">{{ $t('signUp.goToLogin_link') }}</router-link>
          </p>
        </div>
      </div>
    </main>
  </header>
</template>

<script>


import axios from "axios";

export default {
  name: "newRegisterPage",
  components: {},
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      userName: '',
      Email: '',
      Password: '',
      errors: {
        ErrorEmailOrUserName: '',
      },
    }
  },
  methods: {
    async checkForm() {
      if (this.userName && this.Email && this.Password) {
        if (this.userName) {
          let userName = this.userName;
          let usernameRegex = /^[a-zA-Z0-9]+$/;
          let CheckUserName = usernameRegex.test(userName);//true or false
          if (!CheckUserName) {
            if (this.lang === 'en') {
              this.errors.errorUserName = "Excuse me! Username failed, must be (a-z), (0-1) and space ignored";
            } else {
              this.errors.errorUserName = "إعذرني! فشل اسم المستخدم ، يجب أن يكون (a-z) ، (0-1) وتجاهل المسافة";
            }
            setTimeout(() => {
              this.errors.errorUserName = null;
            }, 3000);
            return false;
          }
        }
        if (this.Email) {
          let email = this.Email;
          let EmailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          let CheckEmail = EmailRegex.test(email);//true or false
          if (!CheckEmail) {
            if (this.lang === 'en') {
              this.errors.errorEmail = "Excuse me ! Email failed, must be (@), (.) And a space ignored";
            } else {
              this.errors.errorEmail = "عفواً 👽 فشل البريد الإلكتروني ، يجب أن يكون (@) و (.) ومسافة متجاهلة";
            }
            setTimeout(() => {
              this.errors.errorEmail = null;
            }, 3000);
            return false;
          }
        }

        await axios.post('/api/v1/users/register', {
          userName: this.userName,
          email: this.Email,
          password: this.Password,
        }).then(async ({data: response}) => {
          await this.ClearError();
          await this.$store.dispatch('TokenUser', response);
          this.LoadingActivation();
          await this.GetUserData();
        }).catch(() => {
          if (this.lang === 'en') {
            this.errors.ErrorEmailOrUserName = "Email or username is already taken Or Invalid Email Or Password";
          } else {
            this.errors.ErrorEmailOrUserName = "البريد الإلكتروني أو اسم المستخدم مأخوذ بالفعل أو البريد الإلكتروني أو كلمة السر خاطئة";
          }
          return false;
        });
      } else {
        /* if not Input userName And Email  👽  */
        if (this.lang === 'en') {
          this.errors.ErrorEmailOrUserName = "Excuse me 👽 Username failed or password is required";
        } else {
          this.errors.ErrorEmailOrUserName = "عفواً 👽 فشل الإشتراك اسم المستخدم و كلمةالمرور و الإيميل مطلوب";
        }
        setTimeout(() => {
          this.errors.ErrorEmailOrUserName = null;

        }, 3000);
        return false;
      }

    },
    async GetUserData() {
      await axios.get('/api/v1/users/me').then(async ({data: {user: userInfo}}) => {
        await this.$store.dispatch('user', userInfo);
      }).catch(() => {
        console.log('Failed response me')
      });
      await axios.get('/api/v1/users/profile').then(async ({data: {profileData: userProfile}}) => {
        await this.$store.dispatch('userProfile', userProfile[0]);
      }).catch(() => {
        console.log('Failed response profile')
      });
      await this.$router.push('/v2/main/home');
    },
    ClearError() {
      this.errors.errorUserName = null;
      this.errors.errorEmail = null;
      this.errors.errorPassword = null;
    },
    LoadingActivation() {
      document.getElementById("loadingScreen").style.display = "flex";
      setTimeout(function () {
        document.getElementById("loadingScreen").style.display = "none";
      }, 950);
    },
    changeLanguage(event) {
      window.location.reload();
      localStorage.setItem('lang', event.target.value);
    },
  },

  mounted() {
    const input1 = document.getElementById("username_input");
    const labelInput1 = document.getElementById("username_LabelInput");
    const input2 = document.getElementById("email_input");
    const labelInput2 = document.getElementById("email_LabelInput");
    const input3 = document.getElementById("password_input");
    const labelInput3 = document.getElementById("password_LabelInput");
    const line1_U = document.getElementById("line1_U");
    const line1_E = document.getElementById("line1_E");
    const line2 = document.getElementById("line2");

    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "move_R");
      SlidUp(line1_U, "line-dis_R");

    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "move_R");
      SlidUp(line1_E, "line-dis_R");

    })
    input3.addEventListener("focusin", function () {
      SlidUp(labelInput3, "move_R");
      SlidUp(line2, "line-dis_R");

    })
    // let value = input1.value
    // if (value) {
    input1.addEventListener("focusout", function () {
      if (!input1.value) {
        SlidDown(labelInput1, "move_R");
        SlidDown(line1_U, "line-dis_R");
      }
    })
    input2.addEventListener("focusout", function () {
      if (!input2.value) {
        SlidDown(labelInput2, "move_R");
        SlidDown(line1_E, "line-dis_R");
      }
    })
    input3.addEventListener("focusout", function () {
      if (!input3.value) {
        SlidDown(labelInput3, "move_R");
        SlidDown(line2, "line-dis_R");
      }
    })

    // }


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
@import "../../assets/css/_NEWRegisterStyle.css";
</style>
