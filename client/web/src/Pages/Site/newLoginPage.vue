<template>
  <header class="Main__Header">
    <main class="main__login">
      <div class="login__photo">
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
      <div :class=" lang==='ar' ? 'login__inform direction-rtl' :'login__inform direction-ltr'">
        <div class="close__to-home">
          <router-link :class=" lang==='ar' ? 'close__to-home_link_AR' :'close__to-home_link'"
                       to="/"><i class="far fa-times-circle"></i></router-link>
        </div>
        <h2 class="login__inform-title">{{ $t('login.title') }}</h2>
        <p class="login__inform-minTitle">{{ $t('login.body') }}</p>
        <form action="#" class="login__form" @submit.prevent="checkForm">
          <div class="form__group">
            <label id="emailUsername_LabelInput" :class=" lang==='ar' ? 'form__input-label_AR' :'form__input-label'"
                   for="EmailUsername_input">{{ $t('login.usernameOrEmail') }}</label>
            <input id="EmailUsername_input" v-model.trim="userNameOrEmail" class="form__input-input" type="text">
            <hr id="line1" class="style_line">
          </div>
          <div class="form__group">
            <label id="password_LabelInput" :class=" lang==='ar' ? 'form__input-label_AR' :'form__input-label'"
                   for="password_input">{{ $t('login.password') }}</label>
            <input id="password_input" v-model.trim="Password" class="form__input-input" type="password">
            <hr id="line2" class="style_line">

          </div>
          <p class="error_style">{{ this.errors.errorUserNameOrEmail }}</p>
          <p class="error_style">{{ this.errors.errorPassword }}</p>
          <div class="form__group">
            <p class="form__forgot">
              <a class="form__forgot-link" href="">{{ $t('login.forgotPassword') }}</a>
            </p>
          </div>
          <div class="form__group">
            <button class="form__button-submit" type="submit">{{ $t('login.btn_text') }}</button>
          </div>
        </form>
        <div class="go__register">
          <p class="register__label">
            {{ $t('login.newAccount') }}
            <router-link class="register__label-link" to="/v2/register">{{ $t('login.registerHere') }}</router-link>
          </p>
        </div>
      </div>
    </main>
  </header>
</template>

<script>

import axios from "axios";

export default {
  name: "newLoginPage",
  components: {},
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      userNameOrEmail: '',
      Password: '',
      errors: {
        errorUserNameOrEmail: '',
        errorPassword: '',
      },
    }
  },
  methods: {
    async checkForm() {
      let UserNameOrEmail = this.userNameOrEmail;
      /* Check input userName or Email*/
      if (UserNameOrEmail) {
        let usernameRegex = /^[a-zA-Z0-9]+$/;
        let EmailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let CheckUserName = usernameRegex.test(UserNameOrEmail);//true or false
        let CheckEmail = EmailRegex.test(UserNameOrEmail);//true or false
        /* this condition to Check if username Or Email not input correctly 👺  */
        if (!CheckEmail && !CheckUserName) {
          if (this.lang === 'en') {
            this.errors.errorUserNameOrEmail = "Excuse me ! Email failed, must be (@), (.) And a space ignored";
          } else {
            this.errors.errorUserNameOrEmail = "عفواً 👽 فشل البريد الإلكتروني ، يجب أن يكون (@) و (.) ومسافة متجاهلة";
          }
          setTimeout(() => {
            this.errors.errorUserNameOrEmail = null;
          }, 3000);
          return false;
        }

        if (CheckEmail) {
          await axios.post('/api/v1/users/login', {
            email: UserNameOrEmail,
            password: this.Password
          }).then(async ({data: response}) => {
            await this.$store.dispatch('TokenUser', response);
            this.LoadingActivation();
            await this.GetUserData();
          }).catch(() => {
            if (this.lang === 'en') {
              this.errors.errorUserNameOrEmail = 'Invalid Email Or Password';
            } else {
              this.errors.errorUserNameOrEmail = 'البريد الإلكتروني أو كلمة السر خاطئة';
            }
            return false;
          });
        }
        if (CheckUserName) {
          await axios.post('/api/v1/users/login', {
            userName: UserNameOrEmail,
            password: this.Password
          }).then(async ({data: response}) => {
            await this.$store.dispatch('TokenUser', response);
            this.LoadingActivation();
            await this.GetUserData();
          }).catch(() => {
            if (this.lang === 'en') {
              this.errors.errorUserNameOrEmail = 'Invalid Email Or Password';
            } else {
              this.errors.errorUserNameOrEmail = 'البريد الإلكتروني أو كلمة السر خاطئة';
            }
            return false;
          });
        }

      } else {
        /* if not Input userName And Email  👽  */
        if (this.lang === 'en') {
          this.errors.errorUserNameOrEmail = "Excuse me 👽 Username failed or password is required";
        } else {
          this.errors.errorUserNameOrEmail = "عفواً 👽 فشل اسم المستخدم أو مطلوب كلمة المرور";
        }
        setTimeout(() => {
          this.errors.errorUserNameOrEmail = null;

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
    const input1 = document.getElementById("EmailUsername_input");
    const labelInput1 = document.getElementById("emailUsername_LabelInput");
    const input2 = document.getElementById("password_input");
    const labelInput2 = document.getElementById("password_LabelInput");
    const line1 = document.getElementById("line1");
    const line2 = document.getElementById("line2");

    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "move");
      SlidUp(line1, "line-dis");
    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "move");
      SlidUp(line2, "line-dis");
    })

    // if (value) {
    input1.addEventListener("focusout", function () {
      if (!input1.value) {
        SlidDown(labelInput1, "move");
        SlidDown(line1, "line-dis");
      }
    })
    input2.addEventListener("focusout", function () {
      if (!input2.value) {
        SlidDown(labelInput2, "move");
        SlidDown(line2, "line-dis");
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
@import "../../assets/css/_NEWloginStyle.css";
@import "../../assets/css/ClearStyle/normalize.css";
</style>
