<template>
  <div>
    <div class="profile__Main-container">
      <div class="profile__Main-containerInfo">
        <div class="profile__Main-Info">
          <div>
            <img :src="userProfile.image ? userProfile.image : 'https://img.icons8.com/metro/500/000000/user-male.png'"
                 alt="profile image"
                 class="p__Main-image">
          </div>

          <h3>
            {{ userProfile.first_name ? userProfile.first_name : '' }}
            {{ userProfile.last_name ? userProfile.last_name : '' }}
          </h3>

          <div v-if="userProfile.user_name" class="profile__Main-InfoSpan">
            <i class="fa fa-quote-left"></i>
            <p v-text="userProfile.user_name"></p>
            <i class="fa fa-quote-right"></i>
          </div>
          <h6 v-if="userProfile.email" v-text="userProfile.email"></h6>
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
                    <p class="error_style">{{ userData.profileInfo.error.firstName }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="lastNameInputLabel" class="profile__bodyInfo-groupLabel" for="lastNameInput">Last
                      Name</label>
                    <input id="lastNameInput" v-model.trim="userData.profileInfo.lastName"
                           class="profile__bodyInfo-groupInput" type="text">
                    <p class="error_style">{{ userData.profileInfo.error.lastName }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="mobileInputLabel" class="profile__bodyInfo-groupLabel" for="mobileInput">mobile</label>
                    <input id="mobileInput" v-model.trim="userData.profileInfo.mobile"
                           class="profile__bodyInfo-groupInput"
                           type="text">
                    <p class="error_style">{{ userData.profileInfo.error.mobile }}</p>
                  </div>
                  <div class="profile__bodyInfo-groupBTN">
                    <button class="profile__bodyInfo-SubmitBTN" type="submit">Update</button>
                  </div>
                </form>
              </div>
              <div id="bodyLoginInfo" class="profile__Main-EditInfoData__bodyInfo profile__bodyInfo-NotActive">
                <form @submit.prevent="OnUpdateBodyInfoLogin">
                  <div class="profile__bodyInfo-group">
                    <label id="userNameInputLabel" class="profile__bodyInfo-groupLabel"
                           for="userNameInput">User Name <sup class="badge bg-secondary">New</sup></label>
                    <input id="userNameInput" v-model.trim="userData.basicInfo.userName"
                           class="profile__bodyInfo-groupInput"
                           type="text">
                    <p class="error_style">{{ userData.basicInfo.error.userName }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="currentInputLabel" class="profile__bodyInfo-groupLabel" for="currentInput">Current
                      Password</label>
                    <input id="currentInput" v-model.trim="userData.basicInfo.currentPassword"
                           class="profile__bodyInfo-groupInput" type="password">
                    <p class="error_style">{{ userData.basicInfo.error.currentPassword }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="newPasswordInputLabel" class="profile__bodyInfo-groupLabel" for="newPasswordInput">New
                      Password</label>
                    <input id="newPasswordInput" v-model.trim="userData.basicInfo.newPassword"
                           class="profile__bodyInfo-groupInput" type="password">
                    <p class="error_style">{{ userData.basicInfo.error.newPassword }}</p>
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
import io from "socket.io-client";

export default {
  name: "ProfileUserMain",
  data() {
    return {
      userData: {
        profileInfo: {
          mobile: '',
          firstName: this.$store.getters.userProfile.first_name ? this.$store.getters.userProfile.first_name : '',
          lastName: this.$store.getters.userProfile.last_name ? this.$store.getters.userProfile.last_name : '',
          image: '',
          error: {
            mobile: '',
            firstName: '',
            lastName: '',
            image: '',
          }
        },
        basicInfo: {
          currentPassword: '',
          newPassword: '',
          userName: '',
          error: {
            currentPassword: '',
            newPassword: '',
            userName: '',
          }
        },
      },
      picture: '',
      imageLoading: '',
      imageName: '',
      pictureSize: '',
      pictureSizeKB: '',
      pictureSizeUnit: '',
      imageData: '',
      uploadValue: 0,
      errors: '',
      socket: '',

    }
  },
  methods: {
    /*** Refs for input file ***/
    onPickImage() {
      this.$refs.imageInput.click()
    },
    /*** Image Preview ***/
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

    /*** up load image to FireBase Storage ***/
    async onUploadImage() {
      /*** Alert Swal.fire ***/
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
          let UserImage = this.$store.getters.userProfile.image;
          if (UserImage) {
            let isUserImage = UserImage.split("?");
            if (isUserImage[0].toString() !== "https://ui-avatars.com/api/") {
              let FromURL = UserImage;
              if (FromURL) {
                let oldRefFromURL = firebase.storage().refFromURL(FromURL);
                if (oldRefFromURL) {
                  this.OnDeleteImage(oldRefFromURL); // to delete old
                }
              }
            }
          }
          /*** End DELETE ***/
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
    /*** store image method ***/
    async storeImageInDB(URL) {
      await axios.put('/api/v1/users/profile', {image: URL});
      const {data: {profileData: userProfile}} = await axios.get('/api/v1/users/profile');
      await this.$store.dispatch('userProfile', userProfile[0]);
    },
    OnDeleteImage(deletedImage) {
      deletedImage.delete().then(() => {
        // File deleted successfully
      }).catch((error) => {
        console.log(error);
      });
    },


    //  OnUpdateBodyInfo
    async OnUpdateBodyInfo() {
      let FirstName = this.userData.profileInfo.firstName;
      let LastName = this.userData.profileInfo.lastName;
      let Mobile = this.userData.profileInfo.mobile;
      if (FirstName || LastName || Mobile) {
        if (Mobile) {
          let mobileRegex = /^[0-9]+$/;
          let CheckMobile = mobileRegex.test(Mobile);//true or false
          if (!CheckMobile) {
            this.userData.profileInfo.error.mobile = "Sorry! User mobile Faild, must be number";
            setTimeout(() => {
              this.userData.profileInfo.error.mobile = null;
            }, 3000);
            return false;
          }
        }

        if (FirstName && LastName && Mobile) {
          await axios.put('/api/v1/users/profile', {
            firstName: FirstName ? FirstName : this.$store.getters.userProfile.first_name,
            lastName: LastName ? LastName : this.$store.getters.userProfile.last_name,
            // mobile: Mobile ? Mobile : this.$store.getters.userProfile.mobile, /* 😪 Error Hear ☠️ 🆘 🔞 ' error: "mobile must be less than or equal to 15" not work correctly '*/
          }).then(async () => {
            const {data: {profileData: userProfile}} = await axios.get('/api/v1/users/profile');
            await this.$store.dispatch('userProfile', userProfile[0]);
            FirstName = userProfile[0].first_name ? userProfile[0].first_name : ''
            LastName = userProfile[0].last_name ? userProfile[0].last_name : ''
            Mobile = userProfile[0].mobile ? userProfile[0].mobile : ''
            this.$swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Thank you, Send it',
              text: "Update your information, Successfully",
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(() => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Sorry, 😥',
              text: "Update your information, Faild",
              showConfirmButton: false,
              timer: 1500
            })
          });
        } else if (FirstName) {
          await axios.put('/api/v1/users/profile', {
            firstName: FirstName ? FirstName : this.$store.getters.userProfile.first_name,
          }).then(async () => {
            const {data: {profileData: userProfile}} = await axios.get('/api/v1/users/profile');
            await this.$store.dispatch('userProfile', userProfile[0]);
            FirstName = userProfile[0].first_name ? userProfile[0].first_name : ''
            this.$swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Thank you, Send it',
              text: "Update your First Name, Successfully",
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(() => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Sorry, 😥',
              text: "Update your First Name, Faild",
              showConfirmButton: false,
              timer: 1500
            })
          });
        } else if (LastName) {
          await axios.put('/api/v1/users/profile', {
            lastName: LastName ? LastName : this.$store.getters.userProfile.last_name,
          }).then(async () => {
            const {data: {profileData: userProfile}} = await axios.get('/api/v1/users/profile');
            await this.$store.dispatch('userProfile', userProfile[0]);
            LastName = userProfile[0].last_name ? userProfile[0].last_name : ''
            this.$swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Thank you, Send it',
              text: "Update your Last Name, Successfully",
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(() => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Sorry, 😥',
              text: "Update your lastName, Faild",
              showConfirmButton: false,
              timer: 1500
            })
          });
        } else {
          await axios.put('/api/v1/users/profile', {
            // mobile: Mobile ? Mobile : this.$store.getters.userProfile.mobile, /* 😪 Error Hear ☠️ 🆘 🔞 ' error: "mobile must be less than or equal to 15" not work correctly '*/
          }).then(async () => {
            const {data: {profileData: userProfile}} = await axios.get('/api/v1/users/profile');
            await this.$store.dispatch('userProfile', userProfile[0]);
            Mobile = userProfile[0].mobile ? userProfile[0].mobile : ''
            this.$swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Thank you, Send it',
              text: "Update your Mobile, Successfully",
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(() => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: 'Sorry, 😥',
              text: "Update your Mobile, Faild",
              showConfirmButton: false,
              timer: 1500
            })
          });
        }


        // const user = await axios.post('/api/v1/users/profile', {
        //   profileInfo: {
        //     firstName: this.userData.profileInfo.firstName,
        //     lastName: this.userData.profileInfo.lastName,
        //     mobile: this.userData.profileInfo.mobile,
        //     image: this.$store.getters.user.profileInfo.image
        //   }
        // });
        // this.socket.emit("user_profileData", user.data.data);
        // this.socket.on("user_profileData_server", (data) => {
        //   this.$store.dispatch('user', data);
        //   this.mobile = this.$store.getters.user.profileInfo.mobile;
        //   this.firstName = this.$store.getters.user.profileInfo.firstName;
        //   this.lastName = this.$store.getters.user.profileInfo.lastName;
        //   this.image = this.$store.getters.user.profileInfo.image;
        // });
        // await this.$store.dispatch('user', user.data.data);

      } else {
        this.userData.profileInfo.error.mobile = "Sorry! User mobile Faild, is Required";
        this.userData.profileInfo.error.firstName = "Sorry! User first Name Faild, is Required";
        this.userData.profileInfo.error.lastName = "Sorry! User last Name Faild, is Required";
        setTimeout(() => {
          this.userData.profileInfo.error.mobile = '';
          this.userData.profileInfo.error.firstName = '';
          this.userData.profileInfo.error.lastName = '';
        }, 3000);
      }
    },
    //  OnUpdateBodyInfoLogin
    async OnUpdateBodyInfoLogin() {
      let UserName = this.userData.basicInfo.userName;
      let CurrentPassword = this.userData.basicInfo.currentPassword;
      let NewPassword = this.userData.basicInfo.newPassword;
      if (CurrentPassword || NewPassword || UserName) {

        if (UserName) {
          let usernameRegex = /^[a-zA-Z0-9]+$/;
          let CheckUserName = usernameRegex.test(UserName);//true or false
          if (!CheckUserName) {
            this.userData.basicInfo.error.userName = "Sorry! User Name Faild, must be (a-z) and (0-1) and ignored space";
            setTimeout(() => {
              this.userData.basicInfo.error.userName = null;
            }, 3000);
            return false;
          }
          await axios.put('/api/v1/users/profile', {
            userName: UserName,
          }).then(async () => {
            await axios.get('/api/v1/users/profile').then(async (response) => {
              await this.$store.dispatch('userProfile', response.data.profileData[0]);
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: `<strong>Updated</strong>`,
                text: "Update your UserName, Successfully",
                showConfirmButton: false,
                timer: 1500
              })
              this.EmptyFromInfoLogin();//empty form
            }).catch(() => {
              this.userData.basicInfo.userName = null;
            });
          }).catch(() => {
            this.userData.basicInfo.error.userName = "Sorry! UserName is already 😥";
            setTimeout(() => {
              this.userData.basicInfo.error.userName = null;
            }, 3000);
          });
        }
        if (CurrentPassword && NewPassword) {
          await axios.put('/api/v1/users/profile', {
            password: NewPassword,
          }).then(async () => {
            await axios.get('/api/v1/users/profile').then(async (response) => {
              await this.$store.dispatch('userProfile', response.data.profileData[0]);
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: `<strong>Updated</strong>`,
                text: "Update your Password, Successfully",
                showConfirmButton: false,
                timer: 1500
              })
              this.EmptyFromInfoLogin();//empty form
            }).catch((e) => {
              console.log(e)
            });
          });
        }

      } else {
        this.userData.basicInfo.error.userName = "Sorry! User user Name Faild, is Required";
        this.userData.basicInfo.error.newPassword = "Sorry! User Password Faild, is Required";
        setTimeout(() => {
          this.userData.basicInfo.error.userName = '';
          this.userData.basicInfo.error.newPassword = '';
        }, 3000);
      }
    },


    /*** Btn Cancel Image To Delete image from Preview ***/
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
    /*** Empty Form ***/
    EmptyFromInfoLogin() {
      this.userData.basicInfo.currentPassword = null;
      this.userData.basicInfo.userName = null;
      this.userData.basicInfo.newPassword = null;
      this.userData.basicInfo.error.currentPassword = null;
      this.userData.basicInfo.error.newPassword = null;
      this.userData.basicInfo.error.userName = null;
      document.getElementById("userNameInput").style.borderBottom = "";
      document.getElementById("currentInput").style.borderBottom = "";
      document.getElementById("newPasswordInput").style.borderBottom = "";
      document.getElementById("userNameInputLabel").classList.remove("profile__bodyInfo-groupLabelAddMoved");
      document.getElementById("currentInputLabel").classList.remove("profile__bodyInfo-groupLabelAddMoved");
      document.getElementById("newPasswordInputLabel").classList.remove("profile__bodyInfo-groupLabelAddMoved");
    },
  },
  created() {
    this.socket = io('http://localhost:3001');
  },
  mounted() {
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
    // const input4 = document.getElementById("userNameInput");
    // const labelInput4 = document.getElementById("userNameInputLabel");

    if (this.$store.getters.userProfile.last_name) {
      SlidUp(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
      input1.style.borderBottom = "2px solid #219D9D";
    } else {
      SlidDown(labelInput1, "profile__bodyInfo-groupLabelAddMoved");
      input1.style.borderBottom = "";
    }
    if (this.$store.getters.userProfile.first_name) {
      SlidUp(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
      input2.style.borderBottom = "2px solid #219D9D";
    } else {
      SlidDown(labelInput2, "profile__bodyInfo-groupLabelAddMoved");
      input2.style.borderBottom = "";
    }
    if (this.$store.getters.userProfile.mobile) {
      SlidUp(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
      input3.style.borderBottom = "2px solid #219D9D";
    } else {
      SlidDown(labelInput3, "profile__bodyInfo-groupLabelAddMoved");
      input3.style.borderBottom = "";
    }
    // if (this.$store.getters.userProfile.user_name) {
    //   SlidUp(labelInput4, "profile__bodyInfo-groupLabelAddMoved");
    //   input4.style.borderBottom = "2px solid #219D9D";
    // } else {
    //   SlidDown(labelInput4, "profile__bodyInfo-groupLabelAddMoved");
    //   input4.style.borderBottom = "";
    // }

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
    const userNameInput = document.getElementById("userNameInput");
    const userNameInputLabel = document.getElementById("userNameInputLabel");


    currentInput.addEventListener("focusin", function () {
      SlidUp(currentInputLabel, "profile__bodyInfo-groupLabelAddMoved");
      currentInput.style.borderBottom = "2px solid #219D9D";
    })
    newPasswordInput.addEventListener("focusin", function () {
      SlidUp(newPasswordInputLabel, "profile__bodyInfo-groupLabelAddMoved");
      newPasswordInput.style.borderBottom = "2px solid #219D9D";
    })
    userNameInput.addEventListener("focusin", function () {
      SlidUp(userNameInputLabel, "profile__bodyInfo-groupLabelAddMoved");
      userNameInput.style.borderBottom = "2px solid #219D9D";
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
    userNameInput.addEventListener("focusout", function () {
      if (!userNameInput.value) {
        SlidDown(userNameInputLabel, "profile__bodyInfo-groupLabelAddMoved");
        userNameInput.style.borderBottom = "";
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
    ...mapGetters(['user', 'userProfile'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_ProfileUserMainStyle.css";
</style>
