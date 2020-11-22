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
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.name.$model"  type="text" placeholder="Enter Your Name" name="name" >
                <input v-else-if="submitStatus" v-model.trim="$v.name.$model" :class="{ 'inputError':$v.name.$error ,'inputSuccess':!$v.name.$invalid }" type="text" placeholder="Enter Your Name" name="name" id="name">
                <div >
                  <span class="ErrorText" v-if="!$v.name.minLength"> Name must have at least {{$v.name.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.name.maxLength"> Name must have at most {{$v.name.$params.maxLength.min}} letters</span>
                </div>
              </div>
              <div class="input-group">
                <label for="email">E-Mail</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.email.$model"  type="email" placeholder="Enter Your E-Mail" name="email"  >
                <input v-else-if="submitStatus" v-model.trim="$v.email.$model" :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid }" type="email" placeholder="Enter Your E-Mail" name="email" id="email" >
                <div>
                  <span class="ErrorText" v-if="!$v.email.minLength"> Name must have at least {{$v.email.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.email.maxLength"> Name must have at most {{$v.email.$params.maxLength.min}} letters</span>
                  <div v-if="error">
                    <span class="ErrorText"> {{error}}</span>
                  </div>
                </div>
              </div>
              <div class="input-group ">
                <label for="Password">Password</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.password.$model"  type="password" placeholder="Enter Your Password" name="Password"  >
                <input v-else-if="submitStatus" v-model.trim="$v.password.$model" :class="{ 'inputError':$v.password.$error ,'inputSuccess':!$v.password.$invalid }" type="password" placeholder="Enter Your Password" name="Password" id="Password" >
                <div>
                  <span class="ErrorText" v-if="!$v.password.minLength"> password must have at least {{$v.password.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password.maxLength"> password must have at most {{$v.password.$params.maxLength.min}} letters</span>
                </div>
              </div>
              <div class="input-group">
                <label for="password_confirmation">Confirm Password</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.password_confirmation.$model"  type="password" placeholder="Confirm password" name="password_confirmation" >
                <input v-else-if="submitStatus" v-model.trim="$v.password_confirmation.$model" :class="{ 'inputError':$v.password_confirmation.$error ,'inputSuccess':!$v.password_confirmation.$invalid }" type="password" placeholder="Confirm password" name="password_confirmation" id="password_confirmation" >
                <div>
                  <span class="ErrorText" v-if="!$v.password_confirmation.minLength"> password must have at least {{$v.password_confirmation.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password_confirmation.maxLength"> password must have at most {{$v.password_confirmation.$params.maxLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password_confirmation.sameAsPassword">Passwords must be identical</span>
                </div>
              </div>
            </div>

            <div v-if="submitStatus" >
              <p class="SuccessText" v-if="submitStatus === 'OK'&& !error">Thanks for your submission!</p>
              <p class="ErrorText" v-if="submitStatus === 'ERROR'">Please fill the form correctly.</p>
              <p class="PENDINGText" v-if="submitStatus === 'PENDING'">Sending...</p>
            </div>
            <scale-loader v-show="Loader" :loading="loading" :color="color" :height="height" :width="width"></scale-loader>

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
import { required, minLength,maxLength,sameAs} from 'vuelidate/lib/validators'
import axios from 'axios'
import NavBar from "@/components/TopBar/NavBar";
import ScaleLoader from 'vue-spinner/src/ScaleLoader.vue'
export default {
  name: "SignUpPage",
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
      name: '',
      email: '',
      password: '',
      password_confirmation: '',
      submitStatus: null,
      error:null,
      Loader:false,
    }
  },
  validations:{
    name:{required, minLength:minLength(3), maxLength:maxLength(50)},
    email:{required, minLength:minLength(4), maxLength:maxLength(70)},
    password:{required, minLength:minLength(6), maxLength:maxLength(60)},
    password_confirmation:{required, minLength:minLength(6), maxLength:maxLength(60),sameAsPassword: sameAs('password')},

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
        this.Loader = true ;
        // do your submit logic here
        this.submitStatus = 'PENDING'
        setTimeout(() => {
          this.submitStatus = 'OK'
        }, 250);
        setTimeout(() => {
          this.submitStatus = null
        }, 2000);
        setTimeout(() => {
          this.Loader = false ;
        }, 3500);
        try {
          const re = await axios.post('register-api',{
            name  : this.name,
            email     : this.email,
            password  : this.password,
            password_confirmation : this.password_confirmation
          });
          if (re.data.errors == true){
            this.error = re.data.messages.email[0];
          }else {
            this.error =null;

            localStorage.setItem('token' , re.data.token);
            localStorage.setItem('user' , re.data.user);
            await this.$store.dispatch('user', re.data);
            this.$router.push('/mainPage');
            this.Loader =false;
          }
        }catch (e) {
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
