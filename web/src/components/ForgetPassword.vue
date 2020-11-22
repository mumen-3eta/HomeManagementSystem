<template>
  <div class="content__body-F">
  <NavBar/>
    <div class="body-forgetPassword">
      <div class="content__body-forgetPassword">
        <div class="main-body-content">
          <h2>Reset Password</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">
              <div class="input-group">
                <p>
                  submit a registered email so that we can confirm your identity and send you an email to reset the password
                </p>
              </div>
              <div class="input-group">
                <label>E-Mail</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.email.$model" type="email" placeholder="Enter Your E-Mail" name="email">
                <input v-else v-model.trim="$v.email.$model" :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid }" type="email" placeholder="Enter Your E-Mail" name="email">
                <div>
                  <span class="ErrorText" v-if="!$v.email.minLength"> E-mail must have at least {{$v.email.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.email.maxLength"> E-mail must have at most {{$v.email.$params.maxLength.min}} letters</span>
                </div>
              </div>
            </div>

            <div v-if="submitStatus" >
              <p class="ErrorText" v-if="submitStatus === 'ERROR'">Please fill the form correctly.</p>
              <p class="PENDINGText" v-if="submitStatus === 'PENDING'">Sending...</p>
              <p class="SuccessText" v-if="submitStatus === 'OK' && !error">Check your mail, retype your password</p>
              <div v-if="error">
                <p class="ErrorText"> {{error}}</p>
              </div>
            </div>

            <div class="Group2">
              <div class="btn-group">
                <button type="submit">submit</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/TopBar/NavBar";
import {maxLength, minLength, required} from "vuelidate/lib/validators";
// import axios from "axios";
export default {
  name: "ForgetPassword",
  components:{
    NavBar,
  },
  data() {
    return {
      email: '',
      submitStatus: null,
      error:null
    }
  },
  validations:{
    email:{required, minLength:minLength(4), maxLength:maxLength(70)},
  },
  methods:{
    async checkForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
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

        try {
          // const response  = await axios.post('login-api',{
          //   email     : this.email,
          //   password  : this.password
          // });
          //
          // localStorage.setItem('token' , response.data.token);
          // await this.$store.dispatch('user', response.data);
          this.$router.push('/ChangePassword');

        }catch (e) {
          this.error = 'Invalid Email/Password'
        }


      }
    },

  }
}
</script>


<style scoped>
  @import "../assets/css/_forgetPasswordStyle.css";
</style>
