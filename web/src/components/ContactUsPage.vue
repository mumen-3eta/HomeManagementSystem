<template>
  <div class="content__body-c">
    <NavBar/>
    <div class="content__body-contactUs">
      <div>
        <h2 class="title">Contact Us</h2>
        <form action="#" @submit.prevent="checkForm">
          <div class="Group1">
            <div class="input-group ">
              <label class="label1" for="name">Name</label>
              <input v-if="!submitStatus" v-model.trim="$v.name.$model" :class="{ 'rounded-pill':true ,'input1':true }"
                     name="name"
                     placeholder="Enter Your Name" type="text">
              <input v-else-if="submitStatus" id="name"
                     v-model.trim="$v.name.$model"
                     :class="{ 'inputError':$v.name.$error ,'inputSuccess':!$v.name.$invalid , 'rounded-pill':true,'input1':true }"
                     name="name" placeholder="Enter Your Name" type="text">
              <div>
                <span v-if="!$v.name.minLength"
                      class="ErrorText"> Name must have at least {{ $v.name.$params.minLength.min }} letters</span>
                <!--                <span class="ErrorText" v-if="!$v.name.required"> Name is required </span>-->
                <span v-if="!$v.name.maxLength"
                      class="ErrorText"> Name must have at most {{ $v.name.$params.maxLength.min }} letters</span>
              </div>
            </div>
            <div class="input-group">
              <label class="label2" for="email">E-Mail</label>
              <input v-if="!submitStatus" v-model.trim="$v.email.$model" :class="{ 'rounded-pill':true ,'input2':true }"
                     name="email"
                     placeholder="Enter Your E-Mail" type="email">
              <input v-else-if="submitStatus" id="email" v-model.trim="$v.email.$model"
                     :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid , 'rounded-pill':true ,'input2':true  }"
                     name="email"
                     placeholder="Enter Your E-Mail"
                     type="email">
              <div>
                <!--                <span class="ErrorText" v-if="!$v.email.required"> email is required </span>-->
                <span v-if="!$v.email.minLength"
                      class="ErrorText"> email must have at least {{ $v.email.$params.minLength.min }} letters</span>
                <span v-if="!$v.email.maxLength"
                      class="ErrorText"> email must have at most {{ $v.email.$params.maxLength.min }} letters</span>
              </div>
            </div>
          </div>
          <div class="Group2">
            <div class="input-group">
              <label class="label3" for="contactsMessage">Message</label>
              <textarea v-if="!submitStatus" v-model.trim="$v.contactsMessage.$model"
                        :class="{ 'rounded-3':true ,'input3':true}"
                        cols="35" name="contactsMessage" placeholder="Enter Your Message ..." rows="9"></textarea>
              <textarea v-else-if="submitStatus"
                        id="contactsMessage"
                        v-model.trim="$v.contactsMessage.$model"
                        :class="{ 'inputError':$v.contactsMessage.$error ,'inputSuccess':!$v.contactsMessage.$invalid , 'rounded-3':true ,'input3':true }"
                        cols="35" name="contactsMessage"
                        placeholder="Enter Your Message ..." rows="9"></textarea>
              <div>
                <!--                <span class="ErrorText" v-if="!$v.contactsMessage.required"> Message is required </span>-->
                <span v-if="!$v.contactsMessage.minLength"
                      class="ErrorText"> Message must have at least {{ $v.contactsMessage.$params.minLength.min }} letters</span>
                <span v-if="!$v.contactsMessage.maxLength"
                      class="ErrorText"> Message must have at most {{ $v.contactsMessage.$params.maxLength.min }} letters</span>
              </div>
            </div>

            <div v-if="submitStatus">
              <p v-if="submitStatus === 'OK'" id="Ok" class="SuccessText">Thanks for your submission!</p>
              <p v-if="submitStatus === 'ERROR'" class="ErrorText">Please fill the form correctly.</p>
              <p v-if="submitStatus === 'PENDING'" class="PENDINGText">Sending...</p>
            </div>

            <div class="btn-group">
              <button type="submit">Send</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>


<script>
import {maxLength, minLength, required} from 'vuelidate/lib/validators'
import NavBar from "@/components/Dir_Welcome/NavBar";
import {TimelineLite} from "gsap";

export default {
  name: "ContactUsPage",
  components: {
    NavBar,
  },
  data() {
    return {
      name: '',
      email: '',
      contactsMessage: '',
      submitStatus: null
    }
  },
  mounted() {
    const title = document.getElementsByClassName('title');
    const input1 = document.getElementsByClassName('input1');
    const input2 = document.getElementsByClassName('input2');
    const input3 = document.getElementsByClassName('input3');
    const label1 = document.getElementsByClassName('label1');
    const label2 = document.getElementsByClassName('label2');
    const label3 = document.getElementsByClassName('label3');
    const btnGroup = document.getElementsByClassName('btn-group');
    const timeline = new TimelineLite()
    timeline.from(title, {opacity: 0, duration: 1})
        .from(label1, {opacity: 0, duration: 1, x: -100}, '-=1.5')
        .from(input1, {opacity: 0, duration: 1, x: -50}, '-=0.8')
        .from(label2, {opacity: 0, duration: 1, x: 50}, '-=1.5')
        .from(input2, {opacity: 0, duration: 1, x: 50}, '-=0.8')
        .from(label3, {opacity: 0, duration: 1, y: 50}, '-=1.5')
        .from(input3, {opacity: 0, duration: 1, y: 50}, '-=0.8')
        .from(btnGroup, {opacity: 0, duration: 1, y: 50}, '-=0.8')
  },
  validations: {
    name: {required, minLength: minLength(3), maxLength: maxLength(50)},
    email: {required, minLength: minLength(4), maxLength: maxLength(70)},
    contactsMessage: {required, minLength: minLength(5), maxLength: maxLength(255)}
  },
  methods: {
    checkForm() {
      this.$v.$touch()
      if (this.$v.$invalid) {
        this.submitStatus = 'ERROR';
        setTimeout(() => {
          this.submitStatus = null
        }, 3000);
      } else {
        // do your submit logic here
        this.submitStatus = 'PENDING';
        setTimeout(() => {
          this.submitStatus = 'OK'
        }, 500);
        setTimeout(() => {
          this.submitStatus = null
        }, 3000);

      }
    },

  }

}
</script>


<style scoped>
@import "../assets/temp/Temp_Old_Style__needDelete/_contactUsStyle.css";
</style>
