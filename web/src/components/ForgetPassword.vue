<template>
  <div class="content__body-F">
    <NavBar/>
    <div class="body-forgetPassword">
      <div class="content__body-forgetPassword">
        <div class="main-body-content">
          <h2 class="anim1">Reset Password</h2>
          <form action="#" @submit.prevent="checkForm">
            <div class="Group1">
              <div class="input-group anim2">
                <p>
                  submit a registered email so that we can confirm your identity and send you an email to reset the
                  password
                </p>
              </div>
              <div class="input-group anim3">
                <label>E-Mail</label>
                <input v-if="!submitStatus" v-model.trim="$v.email.$model" :class="{ 'rounded-pill':true }" name="email"
                       placeholder="Enter Your E-Mail" type="email">
                <input v-else v-model.trim="$v.email.$model"
                       :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid , 'rounded-pill':true}"
                       name="email"
                       placeholder="Enter Your E-Mail" type="email">
                <div>
                  <span v-if="!$v.email.minLength"
                        class="ErrorText"> E-mail must have at least {{ $v.email.$params.minLength.min }} letters</span>
                  <span v-if="!$v.email.maxLength"
                        class="ErrorText"> E-mail must have at most {{ $v.email.$params.maxLength.min }} letters</span>
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
              <div class="btn-group anim4">
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
import {maxLength, minLength, required} from "vuelidate/lib/validators";
import {TimelineLite} from "gsap";
// import axios from "axios";
export default {
  name: "ForgetPassword",
  components: {
    NavBar,
  },
  data() {
    return {
      email: '',
      submitStatus: null,
      error: null
    }
  },
  mounted() {
    const anim1 = document.getElementsByClassName('anim1');
    const anim2 = document.getElementsByClassName('anim2');
    const anim3 = document.getElementsByClassName('anim3');
    const anim4 = document.getElementsByClassName('anim4');

    const timeline = new TimelineLite()
    timeline.from(anim1, {opacity: 0, duration: 1, y: -25})
        .from(anim2, {opacity: 0, duration: 1, y: -50}, '-=0.9')
        .from(anim3, {opacity: 0, duration: 1, y: -50}, '-=0.85')
        .from(anim4, {opacity: 0, duration: 1, y: -50}, '-=0.8')
  },
  validations: {
    email: {required, minLength: minLength(4), maxLength: maxLength(70)},
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

        } catch (e) {
          this.error = 'Invalid Email/Password'
        }


      }
    },

  }
}
</script>


<style scoped>
@import "../assets/temp/Temp_Old_Style__needDelete/_forgetPasswordStyle.css";
</style>
