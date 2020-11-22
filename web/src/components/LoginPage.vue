<template>
  <div class="content__body-L">
    <NavBar/>
    <div class="body-login">
      <div class="content__body-login">
        <div class="main-body-content">
          <h2>Login</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">
              <div class="input-group">
                <label for="email">E-Mail</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.email.$model" type="email" placeholder="Enter Your E-Mail" name="email"  class="email">
                <input v-else-if="submitStatus" v-model.trim="$v.email.$model" :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid }" type="email" placeholder="Enter Your E-Mail" name="email" id="email" class="email">
                <div>
                  <span class="ErrorText" v-if="!$v.email.minLength"> E-mail must have at least {{$v.email.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.email.maxLength"> E-mail must have at most {{$v.email.$params.maxLength.min}} letters</span>
                </div>
              </div>
              <div class="input-group ">
                <label for="Password">Password</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.password.$model"   type="password" placeholder="Enter Your Password" name="Password" class="Password">
                <input v-else-if="submitStatus" v-model.trim="$v.password.$model" :class="{ 'inputError':$v.password.$error ,'inputSuccess':!$v.password.$invalid }"  type="password" placeholder="Enter Your Password" name="Password" id="Password" class="Password">
                <div>
                  <span class="ErrorText" v-if="!$v.password.minLength"> password must have at least {{$v.password.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password.maxLength"> password must have at most {{$v.password.$params.maxLength.min}} letters</span>
                </div>
              </div>
            </div>

            <div v-if="submitStatus" >
              <p class="ErrorText" v-if="submitStatus === 'ERROR'">Please fill the form correctly.</p>
              <p class="PENDINGText" v-if="submitStatus === 'PENDING'">Sending...</p>
              <p class="SuccessText" v-if="submitStatus === 'OK' && !error">Thanks for your submission!</p>
              <div v-if="error">
                <p class="ErrorText"> {{error}}</p>
              </div>
            </div>

            <scale-loader v-show="Loader" :loading="loading" :color="color" :height="height" :width="width"></scale-loader>

            <div v-show="!Loader" class="Group2">
              <div class="btn-group">
                <button type="submit">Login</button>
              </div>
              <div class="input-group">
                <label for="Password">Forget Password?<router-link to="/forgetPassword"> Reset Password </router-link></label>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { required, minLength,maxLength} from 'vuelidate/lib/validators'
import axios from "axios";
import NavBar from "@/components/TopBar/NavBar";
import ScaleLoader from 'vue-spinner/src/ScaleLoader.vue'
export default {
  name: "LoginPage",
  components:{
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
      email: '',
      password: '',
      submitStatus: null,
      error:null,
      Loader:false,
    }
  },
  validations:{
    email:{required, minLength:minLength(4), maxLength:maxLength(70)},
    password:{required, minLength:minLength(6), maxLength:maxLength(60)},
  },
  methods:{

    async checkForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        this.Loader = true ;
        setTimeout(() => {
          this.Loader = false ;
        }, 3000);
        this.submitStatus = 'ERROR';
        setTimeout(() => {
          this.submitStatus = null
        }, 3000);
      } else {
        // do your submit logic here
        this.submitStatus = 'PENDING'
        setTimeout(() => {
          this.submitStatus = 'OK'
        }, 200);
        setTimeout(() => {
          this.submitStatus = null
        }, 3000);
        this.Loader = true ;
        setTimeout(() => {
          this.Loader = false ;
        }, 3500);

        try {
          let response  = await axios.post('login-api',{
            email     : this.email,
            password  : this.password
          });
          localStorage.setItem('token' , response.data.token);
          localStorage.setItem('user' , response.data.user);
          await this.$store.dispatch('user', response.data);
          this.$router.push('/mainPage');
          this.Loader = false ;

        }catch (e) {
            this.error = 'Invalid Email/Password'
            this.Loader = true ;
            setTimeout(() => {
              this.Loader = false ;
            }, 3500);
        }


      }
    },

  }
}
</script>

<style scoped>
  @import "../assets/css/_loginStyle.css";
</style>


