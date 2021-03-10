<template>
  <header class="Main__Header">
    <main class="main__login">
      <div class="login__photo"></div>
      <div class="login__inform">
        <div class="close__to-home">
          <router-link class="close__to-home_link" to="/"><i class="far fa-times-circle"></i></router-link>
        </div>
        <h2 class="login__inform-title">Welcome back</h2>
        <p class="login__inform-minTitle">Sign to continue using HMSy</p>
        <form action="#" class="login__form" @submit.prevent="checkForm">
          <div class="form__group">
            <label id="emailUsername_LabelInput" class="form__input-label" for="EmailUsername_input">username or
              E-mail</label>
            <input id="EmailUsername_input" v-model.trim="userNameOrEmail" class="form__input-input" type="text">
            <hr id="line1" class="style_line">
          </div>
          <div class="form__group">
            <label id="password_LabelInput" class="form__input-label" for="password_input">Password</label>
            <input id="password_input" v-model.trim="Password" class="form__input-input" type="password">
            <hr id="line2" class="style_line">

          </div>
          <p class="error_style">{{ this.errors.errorUserNameOrEmail }}</p>
          <p class="error_style">{{ this.errors.errorPassword }}</p>
          <div class="form__group">
            <p class="form__forgot">
              <a class="form__forgot-link" href="">Forgot your password?</a>
            </p>
          </div>
          <div class="form__group">
            <button class="form__button-submit" type="submit">Login</button>
          </div>
        </form>
        <div class="go__register">
          <p class="register__label">
            Don't have an account?
            <router-link class="register__label-link" to="/v2/register">Register Here</router-link>
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
      if (this.userNameOrEmail) {
        let userNameOrEmail = this.userNameOrEmail;
        let usernameRegex = /^[a-zA-Z0-9]+$/;
        let EmailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        let CheckUserName = usernameRegex.test(userNameOrEmail);//true or false
        let CheckEmail = EmailRegex.test(userNameOrEmail);//true or false
        if (!CheckEmail && !CheckUserName) {
          this.errors.errorUserNameOrEmail = "Sorry! Email Faild, must be (@) and (.) and ignored space";
          setTimeout(() => {
            this.errors.errorUserNameOrEmail = null;
          }, 3000);
          return false;
        } else {
          if (!CheckUserName && !CheckEmail) {
            this.errors.errorUserNameOrEmail = "Sorry! User Name Faild, must be (a-z) and (0-1) and ignored space";
            setTimeout(() => {
              this.errors.errorUserNameOrEmail = null;
            }, 3000);
            return false;
          }
        }
      }

      try {
        let response = await axios.post('/api/v1/users/login', {
          email: this.userNameOrEmail,
          password: this.Password
        });
        localStorage.setItem('csrfToken', response.data.csrfToken);
        await this.$store.dispatch('TokenUser', response.data);
        await this.$router.push('/v2/main/home');
      } catch (e) {
        this.errors.errorUserNameOrEmail = 'Invalid Email/Password' || e.msg || e.message;
        return false;
      }


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
