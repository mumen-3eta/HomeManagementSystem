<template>
  <div class="content__body-C">
    <NavBar/>
    <div class="body-changePassword">
      <div class="content__body-changePassword">
        <div class="main-body-content">
          <h2 class="anim1">Change Password</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">

              <div class="input-group anim2">
                <p>
                  Enter New Password, And Can Login With new Password.
                </p>
              </div>
              <div class="input-group anim3">
                <label>Password</label>
                <input v-if="!submitStatus" v-model.trim="$v.password.$model" :class="{ 'rounded-pill':true }"
                       name="password" placeholder="Enter New password" type="password">
                <input v-else v-model.trim="$v.password.$model"
                       :class="{ 'inputError':$v.password.$error ,'inputSuccess':!$v.password.$invalid , 'rounded-pill':true}"
                       name="password" placeholder="Enter New password" type="password">
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
              <div class="input-group anim4">
                <label>Confirm password</label>
                <input v-if="!submitStatus" v-model.trim="$v.password_confirmation.$model"
                       :class="{ 'rounded-pill':true }" name="confirm_password" placeholder="Confirm password"
                       type="password">
                <input v-else v-model.trim="$v.password_confirmation.$model"
                       :class="{ 'inputError':$v.password_confirmation.$error ,'inputSuccess':!$v.password_confirmation.$invalid , 'rounded-pill':true}"
                       name="confirm_password" placeholder="Confirm password" type="password">
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
              <p v-if="submitStatus === 'ERROR'" class="ErrorText">Please fill the form correctly.</p>
              <p v-if="submitStatus === 'PENDING'" class="PENDINGText">Sending...</p>
              <p v-if="submitStatus === 'OK' && !error" class="SuccessText">Check your mail, retype your password</p>
              <div v-if="error">
                <p class="ErrorText"> {{ error }}</p>
              </div>
            </div>

            <div class="Group2">
              <div class="btn-group anim5">
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
import NavBar from "@/components/Dir_Welcome/NavBar";
import {maxLength, minLength, required, sameAs} from "vuelidate/lib/validators";
import {TimelineLite} from "gsap";
// import axios from "axios";
export default {
  name: "ChangePasswordPage",
  components: {
    NavBar,
  },
  data() {
    return {
      password: '',
      password_confirmation: '',
      submitStatus: null,
      error: null
    }
  },
  mounted() {
    const anim1 = document.getElementsByClassName('anim1');
    const anim2 = document.getElementsByClassName('anim2');
    const anim3 = document.getElementsByClassName('anim3');
    const anim4 = document.getElementsByClassName('anim4');
    const anim5 = document.getElementsByClassName('anim5');

    const timeline = new TimelineLite()
    timeline.from(anim1, {opacity: 0, duration: 1, y: -25})
        .from(anim2, {opacity: 0, duration: 1, y: -50}, '-=0.9')
        .from(anim3, {opacity: 0, duration: 1, y: -50}, '-=0.85')
        .from(anim4, {opacity: 0, duration: 1, y: -50}, '-=0.8')
        .from(anim5, {opacity: 0, duration: 1, y: -50}, '-=0.75')
  },
  validations: {
    password: {required, minLength: minLength(6), maxLength: maxLength(60)},
    password_confirmation: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(60),
      sameAsPassword: sameAs('password')
    }
  },
  methods: {
    async checkForm() {
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

        } catch (e) {
          this.error = 'Invalid Password'
        }
      }
    },
  }
}
</script>

<style scoped>
@import "../assets/temp/Temp_Old_Style__needDelete/_changePasswordStyle.css";
</style>
