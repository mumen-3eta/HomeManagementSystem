<template>
  <div class="content__body-c">
    <NavBar/>
    <div class="content__body-contactUs">
                <div>
                  <h2>Contact Us</h2>
                  <form action="#" @submit.prevent="checkForm">
                    <div class="Group1">
                      <div class="input-group ">
                        <label  for="name">Name</label>
                        <input v-if="!submitStatus" v-model.trim="$v.name.$model" :class="{ }" type="text" placeholder="Enter Your Name" name="name">
                        <input v-else-if="submitStatus"  v-model.trim="$v.name.$model" :class="{ 'inputError':$v.name.$error ,'inputSuccess':!$v.name.$invalid }" type="text" placeholder="Enter Your Name" name="name" id="name">
                        <div >
                          <span class="ErrorText" v-if="!$v.name.minLength"> Name must have at least {{$v.name.$params.minLength.min}} letters</span>
                          <!--                <span class="ErrorText" v-if="!$v.name.required"> Name is required </span>-->
                          <span class="ErrorText" v-if="!$v.name.maxLength"> Name must have at most {{$v.name.$params.maxLength.min}} letters</span>
                        </div>
                      </div>
                      <div class="input-group">
                        <label for="email">E-Mail</label>
                        <input v-if="!submitStatus" :class="{ }" v-model.trim="$v.email.$model" type="email" placeholder="Enter Your E-Mail" name="email">
                        <input v-else-if="submitStatus" v-model.trim="$v.email.$model" type="email" placeholder="Enter Your E-Mail" name="email" id="email" :class="{ 'inputError':$v.email.$error ,'inputSuccess':!$v.email.$invalid }">
                        <div >
                          <!--                <span class="ErrorText" v-if="!$v.email.required"> email is required </span>-->
                          <span class="ErrorText" v-if="!$v.email.minLength"> email must have at least {{$v.email.$params.minLength.min}} letters</span>
                          <span class="ErrorText" v-if="!$v.email.maxLength"> email must have at most {{$v.email.$params.maxLength.min}} letters</span>
                        </div>
                      </div>
                    </div>
                    <div class="Group2">
                      <div class="input-group">
                        <label for="contactsMessage">Message</label>
                        <textarea v-if="!submitStatus" :class="{ }"  v-model.trim="$v.contactsMessage.$model"  name="contactsMessage"  cols="35" rows="9" placeholder="Enter Your Message ..."></textarea>
                        <textarea v-else-if="submitStatus" :class="{ 'inputError':$v.contactsMessage.$error ,'inputSuccess':!$v.contactsMessage.$invalid }"  v-model.trim="$v.contactsMessage.$model"  name="contactsMessage" id="contactsMessage" cols="35" rows="9" placeholder="Enter Your Message ..."></textarea>
                        <div >
                          <!--                <span class="ErrorText" v-if="!$v.contactsMessage.required"> Message is required </span>-->
                          <span class="ErrorText" v-if="!$v.contactsMessage.minLength"> Message must have at least {{$v.contactsMessage.$params.minLength.min}} letters</span>
                          <span class="ErrorText" v-if="!$v.contactsMessage.maxLength"> Message must have at most {{$v.contactsMessage.$params.maxLength.min}} letters</span>
                        </div>
                      </div>

                      <div v-if="submitStatus" >
                        <p id="Ok" class="SuccessText" v-if="submitStatus === 'OK'">Thanks for your submission!</p>
                        <p  class="ErrorText" v-if="submitStatus === 'ERROR'">Please fill the form correctly.</p>
                        <p class="PENDINGText" v-if="submitStatus === 'PENDING'">Sending...</p>
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
import { required, minLength,maxLength} from 'vuelidate/lib/validators'
import NavBar from "@/components/TopBar/NavBar";
export default {
  name: "ContactUsPage",
  components:{
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
  validations:{
    name:{required, minLength:minLength(3), maxLength:maxLength(50)},
    email:{required, minLength:minLength(4), maxLength:maxLength(70)},
    contactsMessage:{required, minLength:minLength(5), maxLength:maxLength(255)}
  },
  methods:{
    checkForm () {
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
