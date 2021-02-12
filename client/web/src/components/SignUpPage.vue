<template>
  <div class="content__body-R">
    <NavBar/>
    <div class="body-register">
      <div class="content__body-register">
        <div class="main-body-content">
          <h2 class="anim1">Sign Up</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">
              <div class="input-group anim2">
                <label for="name">User Name</label>
                <input v-if="!submitStatus" v-model.trim="$v.name.$model" :class="{ 'rounded-pill':true }" name="name"
                       placeholder="Enter Your user Name" type="text">
                <input v-else-if="submitStatus" id="name"
                       v-model.trim="$v.name.$model"
                       :class="{ 'inputError':$v.name.$error ,'inputSuccess':!$v.name.$invalid , 'rounded-pill':true }"
                       name="name" placeholder="Enter Your Name" type="text">
                <div>
                  <span v-if="!$v.name.minLength"
                        class="ErrorText"> Name must have at least {{ $v.name.$params.minLength.min }} letters</span>
                  <span v-if="!$v.name.maxLength"
                        class="ErrorText"> Name must have at most {{ $v.name.$params.maxLength.min }} letters</span>
                </div>
              </div>
              <div class="input-group anim3">
                <label for="email">E-Mail</label>
                <input v-if="!submitStatus" v-model.trim="$v.email.$model" :class="{ 'rounded-pill':true }" name="email"
                       placeholder="Enter Your E-Mail" type="email">
                <input v-else-if="submitStatus" id="email"
                       v-model.trim="$v.email.$model"
                       :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid , 'rounded-pill':true}"
                       name="email" placeholder="Enter Your E-Mail" type="email">
                <div>
                  <span v-if="!$v.email.minLength"
                        class="ErrorText"> Name must have at least {{ $v.email.$params.minLength.min }} letters</span>
                  <span v-if="!$v.email.maxLength"
                        class="ErrorText"> Name must have at most {{ $v.email.$params.maxLength.min }} letters</span>

                </div>
              </div>
              <div class="input-group anim4">
                <label for="Password">Password</label>
                <input v-if="!submitStatus" v-model.trim="$v.password.$model" :class="{ 'rounded-pill':true }"
                       name="Password" placeholder="Enter Your Password" type="password">
                <input v-else-if="submitStatus" id="Password"
                       v-model.trim="$v.password.$model"
                       :class="{ 'inputError':$v.password.$error ,'inputSuccess':!$v.password.$invalid , 'rounded-pill':true}"
                       name="Password" placeholder="Enter Your Password" type="password">
                <div>
                  <span v-if="!$v.password.minLength"
                        class="ErrorText"> password must have at least {{
                      $v.password.$params.minLength.min
                    }} letters</span>
                  <span v-if="!$v.password.maxLength"
                        class="ErrorText"> password must have at most {{
                      $v.password.$params.maxLength.min
                    }} letters</span>
                </div>
              </div>
              <div class="input-group anim5">
                <label for="password_confirmation">Confirm Password</label>
                <input v-if="!submitStatus" v-model.trim="$v.password_confirmation.$model"
                       :class="{ 'rounded-pill':true }" name="password_confirmation" placeholder="Confirm password"
                       type="password">
                <input v-else-if="submitStatus" id="password_confirmation"
                       v-model.trim="$v.password_confirmation.$model"
                       :class="{ 'inputError':$v.password_confirmation.$error ,'inputSuccess':!$v.password_confirmation.$invalid  , 'rounded-pill':true }"
                       name="password_confirmation" placeholder="Confirm password"
                       type="password">
                <div>
                  <span v-if="!$v.password_confirmation.minLength"
                        class="ErrorText"> password must have at least {{
                      $v.password_confirmation.$params.minLength.min
                    }} letters</span>
                  <span v-if="!$v.password_confirmation.maxLength"
                        class="ErrorText"> password must have at most {{
                      $v.password_confirmation.$params.maxLength.min
                    }} letters</span>
                  <span v-if="!$v.password_confirmation.sameAsPassword"
                        class="ErrorText">Passwords must be identical</span>
                </div>
              </div>
            </div>

            <div v-if="submitStatus">
              <p v-if="submitStatus === 'OK'&& !error" class="SuccessText">Thanks for your submission!</p>
              <p v-if="submitStatus === 'ERROR'" class="ErrorText">Please fill the form correctly.</p>
              <p v-if="submitStatus === 'PENDING'" class="PENDINGText">Sending...</p>
            </div>
            <div v-if="error">
              <span class="ErrorText"> {{ error }}</span>
            </div>
            <scale-loader v-show="Loader" :color="color" :height="height" :loading="loading"
                          :width="width"></scale-loader>

            <div v-show="!Loader" class="Group2">
              <div class="btn-group anim6">
                <button type="submit">Regester</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {maxLength, minLength, required, sameAs} from 'vuelidate/lib/validators'
import axios from 'axios'
import NavBar from "@/components/Dir_Welcome/NavBar";
import ScaleLoader from 'vue-spinner/src/ScaleLoader.vue';
import {TimelineLite} from "gsap";
import {boomify} from "@/utils";


export default {
  name: "SignUpPage",
  components: {
    NavBar,
    ScaleLoader,
  },
  props: {
    loading: {
      type: Boolean,
      default: true
    },
    color: {
      type: String,
      default: '#d8e9ea'
    },
    height: {
      type: String,
      default: '35px'
    },
    width: {
      type: String,
      default: '4px'
    },
    margin: {
      type: String,
      default: '2px'
    },
    radius: {
      type: String,
      default: '2px'
    }
  },
  data() {
    return {
      name: '',
      email: '',
      password: '',
      password_confirmation: '',
      submitStatus: null,
      error: null,
      Loader: false,
    }
  },
  mounted() {
    const anim1 = document.getElementsByClassName('anim1');
    const anim2 = document.getElementsByClassName('anim2');
    const anim3 = document.getElementsByClassName('anim3');
    const anim4 = document.getElementsByClassName('anim4');
    const anim5 = document.getElementsByClassName('anim5');
    const anim6 = document.getElementsByClassName('anim6');

    const timeline = new TimelineLite()
    timeline.from(anim1, {opacity: 0, duration: 1, y: -25})
        .from(anim2, {opacity: 0, duration: 1, y: -50}, '-=0.9')
        .from(anim3, {opacity: 0, duration: 1, y: -50}, '-=0.85')
        .from(anim4, {opacity: 0, duration: 1, y: -50}, '-=0.8')
        .from(anim5, {opacity: 0, duration: 1, y: -50}, '-=0.75')
        .from(anim6, {opacity: 0, duration: 1, y: -50}, '-=0.7')
  },
  validations: {
    name: {required, minLength: minLength(3), maxLength: maxLength(25)},
    email: {required, minLength: minLength(4), maxLength: maxLength(50)},
    password: {required, minLength: minLength(6), maxLength: maxLength(50)},
    password_confirmation: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(50),
      sameAsPassword: sameAs('password')
    },

  },
  methods: {
    async checkForm() {
      this.$v.$touch()
      if (this.$v.name.$model) {
        let userName = this.$v.name.$model;
        let usernameRegex = /^[a-zA-Z0-9]+$/;
        let CheckUserName = usernameRegex.test(userName);//true or false
        if (!CheckUserName) {
          this.error = "user name Faild";
          setTimeout(() => {
            this.error = null;
          }, 3000);
          return false;
        }
      }
      if (this.$v.$invalid) {
        this.Loader = true;
        setTimeout(() => {
          this.Loader = false;
        }, 3000);
        this.submitStatus = 'ERROR';
        setTimeout(() => {
          this.submitStatus = null
        }, 3000);


      } else {
        this.Loader = true;
        // do your submit logic here
        this.submitStatus = 'PENDING'
        setTimeout(() => {
          this.submitStatus = 'OK'
        }, 250);
        setTimeout(() => {
          this.submitStatus = null
        }, 2000);
        setTimeout(() => {
          this.Loader = false;
        }, 3500);
        try {
          const re = await axios.post('/api/v1/users/register', {
            userName: this.name,
            email: this.email,
            password: this.password,
          });
          if (re.data.errors) {
            // Error handling from api
            boomify(400, "Email is already taken");
          }
          this.error = null;
          localStorage.setItem('csrfToken', re.data.csrfToken);
          await this.$store.dispatch('TokenUser', re.data);
          await this.$router.push('/mainPage');
          this.Loader = false;
        } catch (e) {
          this.error = e.msg || e.message;
          return false;
        }


      }
    },
  }
}
</script>


<style scoped>
@import "../assets/temp/Temp_Old_Style__needDelete/_registerStyle.css";
</style>
