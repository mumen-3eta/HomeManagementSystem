<template>
  <div>
    <div class="profile__Main-container">
      <div class="profile__Main-containerInfo">
        <div class="profile__Main-Info">
          <div>
            <img v-show="user.profileInfo.image" :src="user.profileInfo.image" alt="profile image"
                 class="p__Main-image">
          </div>
          <h3 v-show="user.profileInfo.firstName && user.profileInfo.lastName">{{ user.profileInfo.firstName }}
            {{ user.profileInfo.lastName }}</h3>
          <div v-show="user.basicInfo.userName" class="profile__Main-InfoSpan">
            <i class="fa fa-quote-left"></i>
            <p> {{ user.basicInfo.userName }}</p>
            <i class="fa fa-quote-right"></i>
          </div>
          <h6 v-show="user.basicInfo.email">{{ user.basicInfo.email }}</h6>
        </div>
      </div>
      <div class="profile__Main-containerEditInfo">
        <h2 class="profile__Main-EditInfoH2 mb-4">Update Your Profile</h2>
        <div class="profile__Main-EditInfo">
          <div class="profile__Main-EditInfoImg">
            <div class="profile__image-outline">
              <img :alt="this.imageName" :src="imageLoading">
            </div>
            <p class="image__errors">{{ errors }}</p>
            <div class="profile__image-btn">
              <input id="input__file" ref="imageInput" accept="image/*" hidden type="file"
                     @change.prevent="previewImage">
              <button v-show="!imageLoading" class="Add-btn btn btn-info" @click.prevent="onPickImage">Choose Your Image
              </button>
              <button v-show="imageLoading" class="upload-btn btn btn-success" @click.prevent="onUploadImage">upload
              </button>
              <button v-show="imageLoading" class="btn btn-danger" @click.prevent="onCancelImage">Cancel</button>
            </div>
          </div>
          <div class="profile__Main-EditInfoImg">
            <div class="profile__Main-EditInfoData__title">
              <ul class="profile__Main-EditInfoData__ul">
                <li class="profile__Main-EditInfoData__li"><a id="Base_Info" class="profile__Main-EditInfoData__active"
                                                              href="javascript:void(0);">Base Info</a></li>
                <li class="profile__Main-EditInfoData__li"><a id="Login_Info" href="javascript:void(0);">Login info</a>
                </li>
              </ul>
            </div>
            <div class="profile__Main-EditInfoData__body">
              <div id="bodyBaseInfo" class="profile__Main-EditInfoData__bodyInfo">
                <form @submit.prevent="OnUpdateBodyInfo">
                  <div class="profile__bodyInfo-group">
                    <label id="firstNameInputLabel" class="profile__bodyInfo-groupLabel" for="firstNameInput">First
                      Name</label>
                    <input id="firstNameInput" v-model.trim="userData.profileInfo.firstName"
                           class="profile__bodyInfo-groupInput" type="text">
                    <p class="error_style">{{ userData.profileInfo.error }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="lastNameInputLabel" class="profile__bodyInfo-groupLabel" for="lastNameInput">Last
                      Name</label>
                    <input id="lastNameInput" v-model.trim="userData.profileInfo.lastName"
                           class="profile__bodyInfo-groupInput" type="text">
                    <p class="error_style">{{ userData.profileInfo.error }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="mobileInputLabel" class="profile__bodyInfo-groupLabel" for="mobileInput">mobile</label>
                    <input id="mobileInput" v-model.trim="userData.profileInfo.mobile"
                           class="profile__bodyInfo-groupInput"
                           type="text">
                    <p class="error_style">{{ userData.profileInfo.error }}</p>
                  </div>
                  <div class="profile__bodyInfo-groupBTN">
                    <button class="profile__bodyInfo-SubmitBTN" type="submit">Update</button>
                  </div>
                </form>
              </div>
              <div id="bodyLoginInfo" class="profile__Main-EditInfoData__bodyInfo profile__bodyInfo-NotActive">
                <form @submit.prevent="OnUpdateBodyInfoLogin">
                  <div class="profile__bodyInfo-group">
                    <label id="currentInputLabel" class="profile__bodyInfo-groupLabel" for="currentInput">Current
                      Password</label>
                    <input id="currentInput" v-model.trim="userData.basicInfo.currentPassword"
                           class="profile__bodyInfo-groupInput" type="password">
                    <p class="error_style">{{ userData.basicInfo.error }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="newPasswordInputLabel" class="profile__bodyInfo-groupLabel" for="newPasswordInput">New
                      Password</label>
                    <input id="newPasswordInput" v-model.trim="userData.basicInfo.newPassword"
                           class="profile__bodyInfo-groupInput" type="password">
                    <p class="error_style">{{ userData.basicInfo.error }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="confirmationPasswordInputLabel" class="profile__bodyInfo-groupLabel"
                           for="confirmationPasswordInput">Password Confirmation</label>
                    <input id="confirmationPasswordInput" v-model.trim="userData.basicInfo.passwordConfirmation"
                           class="profile__bodyInfo-groupInput"
                           type="password">
                    <p class="error_style">{{ userData.basicInfo.error }}</p>
                  </div>
                  <div class="profile__bodyInfo-groupBTN">
                    <button class="profile__bodyInfo-SubmitBTN" type="submit">Update</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";
import firebase from 'firebase/app';
import 'firebase/storage';

export default {
  name: "ProfileUserMain",
  data() {
    return {
      userData: {
        profileInfo: {
          mobile: this.$store.getters.user.profileInfo.mobile,
          firstName: this.$store.getters.user.profileInfo.firstName,
          lastName: this.$store.getters.user.profileInfo.lastName,
          image: this.$store.getters.user.profileInfo.image,
          error: null,
        },
        basicInfo: {
          currentPassword: null,
          newPassword: null,
          passwordConfirmation: null,
          error: null
        },
      },
      picture: null,
      imageLoading: null,
      imageName: null,
      pictureSize: null,
      pictureSizeKB: null,
      pictureSizeUnit: null,
      imageData: null,
      uploadValue: 0,
      errors: null,

    }
  },
  methods: {
    onPickImage() {
      this.$refs.imageInput.click()
    },
    onCancelImage() {
      const input = this.$refs.imageInput;
      const files = input.files;
      if (files && files[0]) {
        const reader = new FileReader;
        reader.onload = () => {
          this.imageLoading = null;
          this.imageName = null
          this.pictureSize = null;
          this.pictureSizeKB = null;
          this.pictureSizeUnit = null;
          this.imageData = null;
          this.picture = null;
          this.uploadValue = 0;
          this.errors = null;
        };
        reader.readAsDataURL(files[0]);
        this.$emit('input', files[0])
      }
    },
    previewImage(event) {
      this.uploadValue = 0;
      this.pictureSize = (event.target.files[0].size);
      this.pictureSizeUnit = "KB";
      this.pictureSizeKB = (this.pictureSize / 1000) + this.pictureSizeUnit;
      this.imageName = event.target.files[0].name;
      const limit = 2000 * 1000; // 2000 KB = 2MB
      if (this.pictureSize >= limit) {
        this.errors = `Your image size: ${this.pictureSizeKB} , image file size must be less than ${limit / 1000}KB`;
        setTimeout(() => {
          this.errors = null;
        }, 5000);
        return false;
      } else {
        const input = this.$refs.imageInput;
        const files = input.files;
        if (files && files[0]) {
          const reader = new FileReader;
          reader.onload = e => {
            this.imageLoading = e.target.result;
            this.imageName = event.target.files[0].name;
            this.imageData = event.target.files[0];
          };
          reader.readAsDataURL(files[0]);
          this.$emit('input', files[0])
        }
      }
    },
    async onUploadImage() {
      let timerInterval
      this.$swal.fire({
        title: 'Image is Upload now',
        html: 'Wait,  I will close in <b></b> milliseconds.',
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
          this.$swal.showLoading()
          timerInterval = setInterval(() => {
            const content = this.$swal.getContent()
            if (content) {
              const b = content.querySelector('b')
              if (b) {
                b.textContent = this.$swal.getTimerLeft()
              }
            }
          }, 100)
        },
        willClose: () => {
          clearInterval(timerInterval)
        }
      }).then((result) => {
        if (result.dismiss === this.$swal.DismissReason) {
          console.log('I was closed by the timer')
        }
      })
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const getUser = await axios.get('/api/v1/users/profile');
      this.picture = null;
      const D = new Date();
      const Year = D.getFullYear();
      const Month = D.getMonth();
      const Time = D.getTime();
      const FullName = 'User/Profile/' + `${Year}_${Month}_${Time}` + `${this.imageName}`;
      const ImagePath = firebase.storage().ref(FullName);
      const storageRef = ImagePath.put(this.imageData);
      storageRef.on(`state_change`, snapshot => {
        this.uploadValue = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
      }, error => {
        console.log(error.message)
      }, () => {
        this.uploadValue = 100;
        storageRef.snapshot.ref.getDownloadURL().then((url) => {
          this.picture = url;
          // Delete old Image
          let isUserImage = getUser.data.data.profileInfo.image.split("?");
          if (isUserImage[0].toString() !== "https://ui-avatars.com/api/") {
            let FromURL = getUser.data.data.profileInfo.image;
            // console.log(firebase.storage().refFromURL(FromURL))
            if (FromURL) {
              let oldRefFromURL = firebase.storage().refFromURL(FromURL);
              if (oldRefFromURL) {
                this.OnDeleteImage(oldRefFromURL); // to delete old
              }
            }
          }
          //End test
          this.storeImageInDB(url);
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Thank you, Send it',
            text: "Update your information, Successfully",
            showConfirmButton: false,
            timer: 1500
          })
        })
      })
      this.onCancelImage();
    },
    async storeImageInDB(URL) {
      const user = await axios.post('/api/v1/users/profile', {
        profileInfo: {
          firstName: this.$store.getters.user.profileInfo.firstName,
          lastName: this.$store.getters.user.profileInfo.lastName,
          mobile: this.$store.getters.user.profileInfo.mobile,
          image: URL
        }
      });
      await this.$store.dispatch('user', user.data.data);
    },
    OnDeleteImage(deletedImage) {
      deletedImage.delete().then(() => {
        // File deleted successfully
        // console.log("File deleted successfully");
      }).catch((error) => {
        console.log(error);
      });
    },

    //  OnUpdateBodyInfo
    async OnUpdateBodyInfo() {
      if (this.userData.profileInfo.mobile
          && this.userData.profileInfo.firstName
          && this.userData.profileInfo.lastName) {
        // section Api for send message

        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const user = await axios.post('/api/v1/users/profile', {
          profileInfo: {
            firstName: this.userData.profileInfo.firstName,
            lastName: this.userData.profileInfo.lastName,
            mobile: this.userData.profileInfo.mobile,
            image: this.$store.getters.user.profileInfo.image
          }
        });
        await this.$store.dispatch('user', user.data.data);
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Thank you, Send it',
          text: "Update your information, Successfully",
          showConfirmButton: false,
          timer: 1500
        })
      }
    },
    //  OnUpdateBodyInfoLogin
    OnUpdateBodyInfoLogin() {
      if (this.userData.basicInfo.currentPassword
          && this.userData.basicInfo.newPassword
          && this.userData.basicInfo.passwordConfirmation) {
        // section Api for send message
        // try {
        //    code..
        // }catch (e) {
        //    catch errors..
        // }
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Thank you, Send it',
          text: "Update your password",
          showConfirmButton: false,
          timer: 1500
        })
        this.EmptyFromInfoLogin();//empty form
      }
    },
    EmptyFromInfoLogin() {
      this.userData.basicInfo.currentPassword = null;
      this.userData.basicInfo.newPassword = null;
      this.userData.basicInfo.passwordConfirmation = null;
      this.userData.basicInfo.error = null;
      document.getElementById("currentInput").style.borderBottom = "";
      document.getElementById("newPasswordInput").style.borderBottom = "";
      document.getElementById("confirmationPasswordInput").style.borderBottom = "";
      document.getElementById("currentInputLabel").classList.remove("profile__bodyInfo-groupLabelAddMoved");
      document.getElementById("newPasswordInputLabel").classList.remove("profile__bodyInfo-groupLabelAddMoved");
      document.getElementById("confirmationPasswordInputLabel").classList.remove("profile__bodyInfo-groupLabelAddMoved");
    },
  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const user = await axios.get('/api/v1/users/profile');
    await this.$store.dispatch('user', user.data.data);
    const Base_Info = document.getElementById('Base_Info');
    const Login_Info = document.getElementById('Login_Info');
    const bodyBaseInfo = document.getElementById('bodyBaseInfo');
    const bodyLoginInfo = document.getElementById('bodyLoginInfo');
    Base_Info.addEventListener("click", function () {
      Base_Info.classList.toggle('profile__Main-EditInfoData__active');
      Login_Info.classList.remove('profile__Main-EditInfoData__active');

      bodyLoginInfo.classList.add('profile__bodyInfo-NotActive');
      bodyBaseInfo.classList.remove('profile__bodyInfo-NotActive');

    });
    Login_Info.addEventListener("click", function () {
      Login_Info.classList.toggle('profile__Main-EditInfoData__active');
      Base_Info.classList.remove('profile__Main-EditInfoData__active');

      bodyLoginInfo.classList.remove('profile__bodyInfo-NotActive');
      bodyBaseInfo.classList.add('profile__bodyInfo-NotActive');
    });


    const input1 = document.getElementById("firstNameInput");
    const labelInput1 = document.getElementById("firstNameInputLabel");
    const input2 = document.getElementById("lastNameInput");
    const labelInput2 = document.getElementById("lastNameInputLabel");
    const input3 = document.getElementById("mobileInput");
    const labelInput3 = document.getElementById("mobileInputLabel");

    if (this.$store.getters.user.profileInfo.lastName) {
      SlidUp(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
      input1.style.borderBottom = "2px solid #219D9D";
    } else {
      SlidDown(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
      input1.style.borderBottom = "";
    }
    if (this.$store.getters.user.profileInfo.firstName) {
      SlidUp(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
      input2.style.borderBottom = "2px solid #219D9D";
    } else {
      SlidDown(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
      input2.style.borderBottom = "";
    }
    if (this.$store.getters.user.profileInfo.mobile) {
      SlidUp(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
      input3.style.borderBottom = "2px solid #219D9D";
    } else {
      SlidDown(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
      input3.style.borderBottom = "";
    }

    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
      input1.style.borderBottom = "2px solid #219D9D";
    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
      input2.style.borderBottom = "2px solid #219D9D";
    })
    input3.addEventListener("focusin", function () {
      SlidUp(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
      input3.style.borderBottom = "2px solid #219D9D";
    })


    input1.addEventListener("focusout", function () {
      if (!input1.value) {
        SlidDown(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
        input1.style.borderBottom = "";
      }
    })
    input2.addEventListener("focusout", function () {
      if (!input2.value) {
        SlidDown(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
        input2.style.borderBottom = "";
      }
    })
    input3.addEventListener("focusout", function () {
      if (!input3.value) {
        SlidDown(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
        input3.style.borderBottom = "";
      }
    })


    const currentInput = document.getElementById("currentInput");
    const currentInputLabel = document.getElementById("currentInputLabel");
    const newPasswordInput = document.getElementById("newPasswordInput");
    const newPasswordInputLabel = document.getElementById("newPasswordInputLabel");
    const confirmationPasswordInput = document.getElementById("confirmationPasswordInput");
    const confirmationPasswordInputLabel = document.getElementById("confirmationPasswordInputLabel");


    currentInput.addEventListener("focusin", function () {
      SlidUp(currentInputLabel, "profile__bodyInfo-groupLabelAddMoved");
      currentInput.style.borderBottom = "2px solid #219D9D";
    })
    newPasswordInput.addEventListener("focusin", function () {
      SlidUp(newPasswordInputLabel, "profile__bodyInfo-groupLabelAddMoved");
      newPasswordInput.style.borderBottom = "2px solid #219D9D";
    })
    confirmationPasswordInput.addEventListener("focusin", function () {
      SlidUp(confirmationPasswordInputLabel, "profile__bodyInfo-groupLabelAddMoved");
      confirmationPasswordInput.style.borderBottom = "2px solid #219D9D";
    })


    currentInput.addEventListener("focusout", function () {
      if (!currentInput.value) {
        SlidDown(currentInputLabel, "profile__bodyInfo-groupLabelAddMoved");
        currentInput.style.borderBottom = "";
      }
    })
    newPasswordInput.addEventListener("focusout", function () {
      if (!newPasswordInput.value) {
        SlidDown(newPasswordInputLabel, "profile__bodyInfo-groupLabelAddMoved");
        newPasswordInput.style.borderBottom = "";
      }
    })
    confirmationPasswordInput.addEventListener("focusout", function () {
      if (!confirmationPasswordInput.value) {
        SlidDown(confirmationPasswordInputLabel, "profile__bodyInfo-groupLabelAddMoved");
        confirmationPasswordInput.style.borderBottom = "";
      }
    })


    function SlidUp(input, nameClass) {
      input.classList.add(nameClass);
    }

    function SlidDown(input, nameClass) {
      input.classList.remove(nameClass);
    }

  },
  computed: {
    ...mapGetters(['user', 'TokenUser'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_ProfileUserMainStyle.css";
</style>
