<template>
  <div class="content__body-c">
    <NavBar/>
    <div class="content__body-contactUs">
      <div>
        <h2>Contact Us</h2>
        <form action="#" @submit.prevent="checkForm">
          <div class="Group1">
            <div class="input-group ">
              <label for="name">Name</label>
              <input v-if="!submitStatus" v-model.trim="$v.name.$model" :class="{ 'rounded-pill':true }" name="name"
                     placeholder="Enter Your Name" type="text">
              <input v-else-if="submitStatus" id="name"
                     v-model.trim="$v.name.$model"
                     :class="{ 'inputError':$v.name.$error ,'inputSuccess':!$v.name.$invalid }"
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
              <label for="email">E-Mail</label>
              <input v-if="!submitStatus" v-model.trim="$v.email.$model" :class="{ 'rounded-pill':true }" name="email"
                     placeholder="Enter Your E-Mail" type="email">
              <input v-else-if="submitStatus" id="email" v-model.trim="$v.email.$model"
                     :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid }" name="email"
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
              <label for="contactsMessage">Message</label>
              <textarea v-if="!submitStatus" v-model.trim="$v.contactsMessage.$model" :class="{ 'rounded-3':true }"
                        cols="35" name="contactsMessage" placeholder="Enter Your Message ..." rows="9"></textarea>
              <textarea v-else-if="submitStatus"
                        id="contactsMessage"
                        v-model.trim="$v.contactsMessage.$model"
                        :class="{ 'inputError':$v.contactsMessage.$error ,'inputSuccess':!$v.contactsMessage.$invalid }"
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
import NavBar from "@/components/TopBar/NavBar";

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
@import "../assets/css/_contactUsStyle.css";
</style>
