<template>
  <header class="Main__Header-R">
    <main class="main__register">
      <div class="register__photo"></div>
      <div class="register__inform">
        <div class="close__to-home">
          <router-link class="close__to-home_link" to="/"><i class="far fa-times-circle"></i></router-link>
        </div>
        <h2 class="register__inform-title">Sign Up</h2>
        <p class="register__inform-minTitle">Signup to continue using HMSy</p>
        <form action="#" class="register__form" @submit.prevent="checkForm">
          <div class="form__group-R">
            <label id="username_LabelInput" class="R-form__input-label" for="username_input">username</label>
            <input id="username_input" v-model.trim="userName" class="R-form__input-input" type="text">
            <hr id="line1_U" class="R-style_line">
            <p class="error_style">{{ this.errors.errorUserName }}</p>
          </div>
          <div class="form__group-R">
            <label id="email_LabelInput" class="R-form__input-label" for="email_input">E-mail</label>
            <input id="email_input" v-model.trim="Email" class="R-form__input-input" type="text">
            <hr id="line1_E" class="R-style_line">
            <p class="error_style">{{ this.errors.errorEmail }}</p>
          </div>
          <div class="form__group-R">
            <label id="password_LabelInput" class="R-form__input-label" for="password_input">Password</label>
            <input id="password_input" v-model.trim="Password" class="R-form__input-input" type="password">
            <hr id="line2" class="R-style_line">
            <p class="error_style">{{ this.errors.errorPassword }}</p>
          </div>
          <div class="form__group-R">
            <p class="R-form__forgot">
              <a class="R-form__forgot-link" href="">Forgot your password?</a>
            </p>
          </div>
          <div class="form__group-R">
            <button class="form__button-submit" type="submit">Login</button>
          </div>
        </form>
        <div class="go__login">
          <p class="login__label">
            You have an account?
            <router-link class="login__label-link" to="/v2/login">Login Here</router-link>
          </p>
        </div>
      </div>
    </main>
  </header>
</template>

<script>


import axios from "axios";
import {boomify} from "@/utils";

export default {
  name: "newRegisterPage",
  components: {},
  data() {
    return {
      userName: null,
      Email: null,
      Password: null,
      errors: {
        errorUserName: null,
        errorEmail: null,
        errorPassword: null,
      },
    }
  },
  methods: {
    async checkForm() {
      if (this.userName) {
        let userName = this.userName;
        let usernameRegex = /^[a-zA-Z0-9]+$/;
        let CheckUserName = usernameRegex.test(userName);//true or false
        if (!CheckUserName) {
          this.errors.errorUserName = "Sorry! User Name Faild, must be (a-z) and (0-1) and ignored space";
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
          this.errors.errorEmail = "Sorry! Email Faild, must be (@) and (.) and ignored space";
          setTimeout(() => {
            this.errors.errorEmail = null;
          }, 3000);
          return false;
        }
      }
      try {
        const re = await axios.post('/api/v1/users/register', {
          userName: this.userName,
          email: this.Email,
          password: this.Password,
        });
        if (re.data.errors) {
          // Error handling from api
          boomify(400, "Email is already taken");
        }
        this.errors.errorUserName = null;
        this.errors.errorEmail = null;
        this.errors.errorPassword = null;
        localStorage.setItem('csrfToken', re.data.csrfToken);
        await this.$store.dispatch('TokenUser', re.data);
        await this.$router.push('/v2/main/home');
      } catch (e) {
        this.errors.errorEmail = e.msg || e.message;
        return false;
      }


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
