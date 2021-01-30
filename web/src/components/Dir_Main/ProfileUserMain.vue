<template>
  <div>
    <div class="profile__Main-container">
      <div class="profile__Main-containerInfo">
        <div class="profile__Main-Info">
          <img v-show="user.profileInfo.image" :src="user.profileInfo.image" alt="profile image" class="p__Main-image">
          <h3 v-show="user.basicInfo.userName">{{ user.basicInfo.userName }}</h3>
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
                    <input id="firstNameInput" v-model.trim="userData.profileInfo.email"
                           class="profile__bodyInfo-groupInput" type="text">
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="lastNameInputLabel" class="profile__bodyInfo-groupLabel" for="lastNameInput">Last
                      Name</label>
                    <input id="lastNameInput" v-model.trim="userData.profileInfo.firstName"
                           class="profile__bodyInfo-groupInput" type="text">
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="emailInputLabel" class="profile__bodyInfo-groupLabel" for="emailInput">Last Name</label>
                    <input id="emailInput" v-model.trim="userData.profileInfo.lastName"
                           class="profile__bodyInfo-groupInput"
                           type="email">
                  </div>
                  <div class="profile__bodyInfo-groupBTN">
                    <button class="profile__bodyInfo-SubmitBTN" type="submit">Update</button>
                  </div>
                </form>
              </div>
              <div id="bodyLoginInfo" class="profile__Main-EditInfoData__bodyInfo profile__bodyInfo-NotActive">
                bodyLoginInfo
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
          email: this.$store.getters.user.basicInfo.email,
          firstName: null,
          lastName: null,
        },
        basicInfo: {},
        error: null,
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
    onUploadImage: function () {
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
          //  Delete old Image
          /*
          * new api and store url in api and use it like (localStorage)
          * for user one url unique
          * */
          let FromURL = localStorage.getItem('url_img');
          if (FromURL) {
            let oldRefFromURL = firebase.storage().refFromURL(FromURL);
            if (oldRefFromURL) {
              this.OnDeleteImage(oldRefFromURL); // to delete old
            }
          }
          localStorage.setItem('url_img', url);
        })
      })
      this.onCancelImage();
    },
    OnDeleteImage(deletedImage) {
      deletedImage.delete().then(() => {
        // File deleted successfully
        console.log("File deleted successfully");
      }).catch((error) => {
        console.log(error);
      });
    },

    //  OnUpdateBodyInfo
    OnUpdateBodyInfo() {

    }
  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const {user} = (await axios.get('/api/v1/users/me')).data;
    await this.$store.dispatch('user', user);

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
    const input3 = document.getElementById("emailInput");
    const labelInput3 = document.getElementById("emailInputLabel");

    if (this.$store.getters.user.basicInfo.email) {
      SlidUp(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
    } else {
      SlidDown(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
    }

    if (this.$store.getters.user.basicInfo.firstName) {
      SlidUp(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
    } else {
      SlidDown(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
    }
    if (this.$store.getters.user.basicInfo.lastName) {
      SlidUp(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
    } else {
      SlidDown(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
    }

    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
      SlidUp(input1, "AddBorder");

    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
      SlidUp(input2, "AddBorder");
    })
    input3.addEventListener("focusin", function () {
      SlidUp(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
      SlidUp(input3, "AddBorder");
    })


    input1.addEventListener("focusout", function () {
      if (!input1.value) {
        SlidDown(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
        SlidDown(input1, "AddBorder");

      }
    })
    input2.addEventListener("focusout", function () {
      if (!input2.value) {
        SlidDown(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
        SlidDown(input2, "AddBorder");

      }
    })
    input3.addEventListener("focusout", function () {
      if (!input3.value) {
        SlidDown(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
        SlidDown(input3, "AddBorder");

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
