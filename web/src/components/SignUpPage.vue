<template>
  <div class="content__body-R">
    <NavBar/>
    <div class="body-register">
      <div class="content__body-register">
        <div class="main-body-content">
          <h2>Sign Up</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">
              <div class="input-group ">
                <label for="name">Name</label>
                <input v-if="!submitStatus" v-model.trim="$v.name.$model" :class="{ 'rounded-pill':true }" name="name"
                       placeholder="Enter Your Name" type="text">
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
              <div class="input-group">
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
                  <div v-if="error">
                    <span class="ErrorText"> {{ error }}</span>
                  </div>
                </div>
              </div>
              <div class="input-group ">
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
              <div class="input-group">
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
            <scale-loader v-show="Loader" :color="color" :height="height" :loading="loading"
                          :width="width"></scale-loader>

            <div v-show="!Loader" class="Group2">
              <div class="btn-group">
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
import NavBar from "@/components/TopBar/NavBar";
import ScaleLoader from 'vue-spinner/src/ScaleLoader.vue'

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
  validations: {
    name: {required, minLength: minLength(3), maxLength: maxLength(50)},
    email: {required, minLength: minLength(4), maxLength: maxLength(70)},
    password: {required, minLength: minLength(6), maxLength: maxLength(60)},
    password_confirmation: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(60),
      sameAsPassword: sameAs('password')
    },

  },
  methods: {
    async checkForm() {
      this.$v.$touch()
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
          const re = await axios.post('register-api', {
            name: this.name,
            email: this.email,
            password: this.password,
            password_confirmation: this.password_confirmation
          });
          if (re.data.errors) {
            this.error = re.data.messages.email[0];
          } else {
            this.error = null;
            localStorage.setItem('token', re.data.token);
            localStorage.setItem('user', re.data.user);
            await this.$store.dispatch('user', re.data);
            await this.$router.push('/mainPage');
            this.Loader = false;
          }
        } catch (e) {
          this.error = 'Error'
        }


      }
    },


  }
}
</script>


<style scoped>
@import "../assets/css/_registerStyle.css";
</style>
