<template>
  <div class="content__body-C">
    <NavBar/>
    <div class="body-changePassword">
      <div class="content__body-changePassword">
        <div class="main-body-content">
          <h2>Change Password</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">

              <div class="input-group">
                <p>
                  Enter New Password, And Can Login With new Password.
                </p>
              </div>
              <div class="input-group">
                <label>Password</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.password.$model" type="password" placeholder="Enter New password" name="password">
                <input v-else v-model.trim="$v.password.$model" :class="{ 'inputError':$v.password.$error ,'inputSuccess':!$v.password.$invalid }"  type="password" placeholder="Enter New password" name="password">
                <div>
                  <span class="ErrorText" v-if="!$v.password.minLength"> password must have at least {{$v.password.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password.maxLength"> password must have at most {{$v.password.$params.maxLength.min}} letters</span>
                </div>
              </div>
              <div class="input-group">
                <label>Confirm password</label>
                <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.password_confirmation.$model" type="password" placeholder="Confirm password" name="confirm_password">
                <input v-else v-model.trim="$v.password_confirmation.$model" :class="{ 'inputError':$v.password_confirmation.$error ,'inputSuccess':!$v.password_confirmation.$invalid }" type="password" placeholder="Confirm password" name="confirm_password">
                <div>
                  <span class="ErrorText" v-if="!$v.password_confirmation.minLength"> password must have at least {{$v.password_confirmation.$params.minLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password_confirmation.maxLength"> password must have at most {{$v.password_confirmation.$params.maxLength.min}} letters</span>
                  <span class="ErrorText" v-if="!$v.password_confirmation.sameAsPassword">Passwords must be identical</span>
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
import {maxLength, minLength, required, sameAs} from "vuelidate/lib/validators";
// import axios from "axios";
export default {
  name: "ChangePasswordPage",
  components:{
    NavBar,
  },
  data() {
    return {
      password: '',
      password_confirmation: '',
      submitStatus: null,
      error:null
    }
  },
  validations:{
    password:{required, minLength:minLength(6), maxLength:maxLength(60)},
    password_confirmation:{required, minLength:minLength(6), maxLength:maxLength(60),sameAsPassword: sameAs('password')}
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
        }, 250);
        setTimeout(() => {
          this.submitStatus = null
        }, 2000);

        try {
        // await axios.post('register-api',{
        //   name  : this.name,
        //   email     : this.email,
        //   password  : this.password,
        //   password_confirmation : this.password_confirmation
        // });
        //

        this.$router.push('/login');

        }catch (e) {
          this.error = 'Invalid Password'
        }
      }
    },
  }
}
</script>

<style scoped>
  @import "../assets/css/_changePasswordStyle.css";
</style>
