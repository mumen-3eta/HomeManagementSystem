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
      <div :class="mode=== 'dark' ? 'profile__Main-containerEditInfo_dark' : 'profile__Main-containerEditInfo_default'"
           class="profile__Main-containerEditInfo">
        <h2 class="profile__Main-EditInfoH2 mb-4">{{ $t('Dashboard.Profile.title') }}</h2>
        <div class="profile__Main-EditInfo">
          <div class="profile__Main-EditInfoImg">
            <div class="profile__image-outline">
              <img :alt="this.imageName" :src="imageLoading">
            </div>
            <p class="image__errors">{{ errors }}</p>
            <div class="profile__image-btn">
              <input id="input__file" ref="imageInput" accept="image/*" hidden type="file"
                     @change.prevent="previewImage">
              <button v-show="!imageLoading" class="Add-btn btn btn-info" @click.prevent="onPickImage">{{
                  $t('Dashboard.Profile.Image.chooseYourImage')
                }}
              </button>
              <button v-show="imageLoading" class="upload-btn btn btn-success" @click.prevent="onUploadImage">
                {{ $t('Dashboard.Profile.Image.upload') }}
              </button>
              <button v-show="imageLoading" class="btn btn-danger" @click.prevent="onCancelImage">
                {{ $t('Dashboard.Profile.Image.cancel') }}
              </button>
            </div>
          </div>
          <div
              :class=" lang==='ar' ? 'profile__Main-EditInfoImg direction-rtl' :'profile__Main-EditInfoImg direction-ltr'">
            <div class="profile__Main-EditInfoData__title">
              <ul class="profile__Main-EditInfoData__ul">
                <li class="profile__Main-EditInfoData__li"><a id="Base_Info" class="profile__Main-EditInfoData__active"
                                                              href="javascript:void(0);">{{
                    $t('Dashboard.Profile.UpdateForm.baseInfo')
                  }}</a></li>
                <li class="profile__Main-EditInfoData__li"><a id="Login_Info" href="javascript:void(0);">{{
                    $t('Dashboard.Profile.UpdateForm.loginInfo')
                  }}</a>
                </li>
              </ul>
            </div>
            <div class="profile__Main-EditInfoData__body">
              <div id="bodyBaseInfo" class="profile__Main-EditInfoData__bodyInfo">
                <form @submit.prevent="OnUpdateBodyInfo">
                  <div class="profile__bodyInfo-group">
                    <label id="firstNameInputLabel" class="profile__bodyInfo-groupLabel" for="firstNameInput">
                      {{ $t('Dashboard.Profile.baseInfo.firstName') }}
                    </label>
                    <input id="firstNameInput" v-model.trim="userData.profileInfo.firstName"
                           class="profile__bodyInfo-groupInput" type="text">
                    <p class="error_style">{{ userData.profileInfo.error.firstName }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="lastNameInputLabel" class="profile__bodyInfo-groupLabel" for="lastNameInput">
                      {{ $t('Dashboard.Profile.baseInfo.lastName') }}
                    </label>
                    <input id="lastNameInput" v-model.trim="userData.profileInfo.lastName"
                           class="profile__bodyInfo-groupInput" type="text">
                    <p class="error_style">{{ userData.profileInfo.error.lastName }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="mobileInputLabel" class="profile__bodyInfo-groupLabel" for="mobileInput">
                      {{ $t('Dashboard.Profile.baseInfo.mobile') }}
                    </label>
                    <input id="mobileInput" v-model.trim="userData.profileInfo.mobile"
                           class="profile__bodyInfo-groupInput"
                           type="text">
                    <p class="error_style">{{ userData.profileInfo.error.mobile }}</p>
                  </div>
                  <div class="profile__bodyInfo-groupBTN">
                    <button class="profile__bodyInfo-SubmitBTN" type="submit">
                      {{ $t('Dashboard.Profile.UpdateForm.btn_Update') }}
                    </button>
                  </div>
                </form>
              </div>
              <div id="bodyLoginInfo" class="profile__Main-EditInfoData__bodyInfo profile__bodyInfo-NotActive">
                <form @submit.prevent="OnUpdateBodyInfoLogin">
                  <div class="profile__bodyInfo-group">
                    <label id="userNameInputLabel" class="profile__bodyInfo-groupLabel"
                           for="userNameInput">
                      {{ $t('Dashboard.Profile.loginInfo.userName') }}
                      <sup class="badge bg-secondary">
                        {{ $t('Dashboard.Profile.loginInfo.new') }}
                      </sup>
                    </label>
                    <input id="userNameInput" v-model.trim="userData.basicInfo.userName"
                           class="profile__bodyInfo-groupInput"
                           type="text">
                    <p class="error_style">{{ userData.basicInfo.error.userName }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="newPasswordInputLabel" class="profile__bodyInfo-groupLabel" for="newPasswordInput">
                      {{ $t('Dashboard.Profile.loginInfo.newPassword') }}
                    </label>
                    <input id="newPasswordInput" v-model.trim="userData.basicInfo.newPassword"
                           class="profile__bodyInfo-groupInput" type="password">
                    <p class="error_style">{{ userData.basicInfo.error.newPassword }}</p>
                  </div>
                  <div class="profile__bodyInfo-group">
                    <label id="currentInputLabel" class="profile__bodyInfo-groupLabel" for="currentInput">
                      {{ $t('Dashboard.Profile.loginInfo.confirmPassword') }}
                    </label>
                    <input id="currentInput" v-model.trim="userData.basicInfo.currentPassword"
                           class="profile__bodyInfo-groupInput" type="password">
                    <p class="error_style">{{ userData.basicInfo.error.currentPassword }}</p>
                  </div>

                  <div class="profile__bodyInfo-groupBTN">
                    <button class="profile__bodyInfo-SubmitBTN" type="submit">
                      {{ $t('Dashboard.Profile.UpdateForm.btn_Update') }}
                    </button>
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
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      userData: {
        profileInfo: {
          mobile: this.$store.getters.userProfile.mobile ? this.$store.getters.userProfile.mobile : '',
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
        if (this.lang === 'en') {
          this.errors = `Your image size: ${this.pictureSizeKB} , image file size must be less than ${limit / 1000}KB`;
        } else {
          this.errors = ` حجم صورتك : ${this.pictureSizeKB} , يجب أن يكون حجم ملف الصورة أقل من ${limit / 1000}كيلو بايت `;
        }
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
        title: this.lang === 'en' ? 'Image is Upload now' : 'يتم تحميل الصورة الآن',
        html: this.lang === 'en' ? 'Wait,  I will close in <b></b> milliseconds.' : 'انتظر ، سأغلق خلال <b> </b> مللي ثانية.',
        timer: 3000,
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
        })
      })
      this.onCancelImage();
    },
    /*** store image method ***/
    async storeImageInDB(URL) {
      await axios.put('/api/v1/users/profile', {image: URL}).then(async () => {
        await axios.get('/api/v1/users/profile').then(async ({data: {profileData: userProfile}}) => {
          await this.$store.dispatch('userProfile', userProfile[0]);
        }).catch(() => {
          console.log('Get Profile faild!')
        });
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: this.lang === 'en' ? 'Thank you, Send it' : 'شكرا لك تم الإرسال',
          text: this.lang === 'en' ? 'Your information has been successfully updated' : 'تم التحديث معلوماتك بنجاح',
          showConfirmButton: false,
          timer: 1500
        })
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: this.lang === 'en' ? 'Failed to store your photo!' : 'فشل تخزين صورتك!',
          showConfirmButton: false,
          timer: 1500
        })
      });
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
            if (this.lang === 'en') {
              this.userData.profileInfo.error.mobile = 'Excuse me! It must be a phone number';
            } else {
              this.userData.profileInfo.error.mobile = 'عذرا! يجب أن يكون رقم هاتف';
            }
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
            mobile: Mobile ? Mobile : this.$store.getters.userProfile.mobile, /* 😪 Error Hear ☠️ 🆘 🔞 ' error: "mobile must be less than or equal to 15" not work correctly '*/
          }).then(async () => {
            await axios.get('/api/v1/users/profile').then(async ({data: {profileData: userProfile}}) => {
              await this.$store.dispatch('userProfile', userProfile[0]);
              FirstName = userProfile[0].first_name ? userProfile[0].first_name : ''
              LastName = userProfile[0].last_name ? userProfile[0].last_name : ''
              Mobile = userProfile[0].mobile ? userProfile[0].mobile : ''
            }).catch(() => {
              console.log('Get data profile faild')
            });
            this.$swal.fire({
              position: 'center',
              icon: 'success',
              title: this.lang === 'en' ? 'Thank you, Send it' : 'شكرا لك تم الإرسال',
              text: this.lang === 'en' ? 'Your information has been successfully updated' : 'تم تحديث معلوماتك بنجاح',
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(() => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: this.lang === 'en' ? 'Excuse me, 😥' : 'عفواً, 😥',
              text: this.lang === 'en' ? 'Failed, Update your information' : 'فشل تحديث المعلومات الخاصة بك',
              showConfirmButton: false,
              timer: 1500
            })
          });
        } else {
          if (FirstName) {
            await axios.put('/api/v1/users/profile', {
              firstName: FirstName ? FirstName : this.$store.getters.userProfile.first_name,
            }).then(async () => {
              await axios.get('/api/v1/users/profile').then(async ({data: {profileData: userProfile}}) => {
                await this.$store.dispatch('userProfile', userProfile[0]);
                FirstName = userProfile[0].first_name ? userProfile[0].first_name : ''
              }).catch(() => {
                console.log('Get data profile faild')
              });
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: this.lang === 'en' ? 'Thank you, Send it' : 'شكرا لك تم الإرسال',
                text: this.lang === 'en' ? 'Your first name has been successfully updated' : 'تم تحديث اسمك الأول بنجاح',
                showConfirmButton: false,
                timer: 1500
              })
            }).catch(() => {
              this.$swal.fire({
                position: 'center',
                icon: 'error',
                title: this.lang === 'en' ? 'Excuse me, 😥' : 'عفواً, 😥',
                text: this.lang === 'en' ? 'Failed, Update your First Name' : 'فشل تحديث الإسم الأول الخاصة بك',
                showConfirmButton: false,
                timer: 1500
              })
            });
          }
          if (LastName) {
            await axios.put('/api/v1/users/profile', {
              lastName: LastName ? LastName : this.$store.getters.userProfile.last_name,
            }).then(async () => {
              await axios.get('/api/v1/users/profile').then(async ({data: {profileData: userProfile}}) => {
                await this.$store.dispatch('userProfile', userProfile[0]);
                LastName = userProfile[0].last_name ? userProfile[0].last_name : ''
              }).catch(() => {
                console.log('Get data profile faild')
              });
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: this.lang === 'en' ? 'Thank you, Send it' : 'شكرا لك تم الإرسال',
                text: this.lang === 'en' ? 'Your Last name has been successfully updated' : 'تم تحديث الكنية بنجاح',
                showConfirmButton: false,
                timer: 1500
              })
            }).catch(() => {
              this.$swal.fire({
                position: 'center',
                icon: 'error',
                title: this.lang === 'en' ? 'Excuse me, 😥' : 'عفواً, 😥',
                text: this.lang === 'en' ? 'Failed, Update your last Name' : 'فشل تحديث الكنية الخاصة بك',
                showConfirmButton: false,
                timer: 1500
              })
            });
          } else {
            await axios.put('/api/v1/users/profile', {
              mobile: Mobile ? Mobile : this.$store.getters.userProfile.mobile, /* 😪 Error Hear ☠️ 🆘 🔞 ' error: "mobile must be less than or equal to 15" not work correctly '*/
            }).then(async () => {
              await axios.get('/api/v1/users/profile').then(async ({data: {profileData: userProfile}}) => {
                await this.$store.dispatch('userProfile', userProfile[0]);
                Mobile = userProfile[0].mobile ? userProfile[0].mobile : ''
              }).catch(() => {
                console.log('Get data profile faild')
              });
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: this.lang === 'en' ? 'Thank you, Send it' : 'شكرا لك تم الإرسال',
                text: this.lang === 'en' ? 'Your Mobile has been successfully updated' : 'تم تحديث رقم الهاتف بنجاح',
                showConfirmButton: false,
                timer: 1500
              })
            }).catch(() => {
              this.$swal.fire({
                position: 'center',
                icon: 'error',
                title: this.lang === 'en' ? 'Excuse me, 😥' : 'عفواً, 😥',
                text: this.lang === 'en' ? 'Failed, Update your Mobile' : 'فشل تحديث رقم الهاتف الخاصة بك',
                showConfirmButton: false,
                timer: 1500
              })
            });
          }
        }


      } else {
        if (this.lang === 'en') {
          this.userData.profileInfo.error.mobile = 'Excuse me! The mobile number field is required';
          this.userData.profileInfo.error.firstName = 'Excuse me! The first name field is required';
          this.userData.profileInfo.error.lastName = 'Excuse me! The Nickname field, is required';
        } else {
          this.userData.profileInfo.error.mobile = "عفوا ! حقل رقم الهاتف المحمول مطلوب";
          this.userData.profileInfo.error.firstName = 'عفوا ! حقل الإسم الأول ، مطلوب';
          this.userData.profileInfo.error.lastName = 'عفوا ! حقل الكنية ، مطلوب';
        }

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
            if (this.lang === 'en') {
              this.userData.basicInfo.error.userName = 'Excuse me! Username failed, it should be (a-z), (0-1) and space ignored';
            } else {
              this.userData.basicInfo.error.userName = 'عذرا! فشل اسم المستخدم ، يجب أن يكون (a-z) ، (0-1) وتجاهل المسافة';
            }
            setTimeout(() => {
              this.userData.basicInfo.error.userName = null;
            }, 3000);
            return false;
          }
          await axios.put('/api/v1/users/profile', {
            userName: UserName,
          }).then(async () => {
            await axios.get('/api/v1/users/profile').then(async ({data: {profileData: response}}) => {
              await this.$store.dispatch('userProfile', response[0]);
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: this.lang === 'en' ? `<strong>Updated</strong>` : `<strong>محدث</strong>`,
                text: this.lang === 'en' ? 'Your username has been successfully updated' : 'تم تحديث اسم المستخدم الخاص بك بنجاح',
                showConfirmButton: false,
                timer: 1500
              })
              this.EmptyFromInfoLogin();//empty form
            }).catch(() => {
              this.userData.basicInfo.userName = null;
            });
          }).catch(() => {
            if (this.lang === 'en') {
              this.userData.basicInfo.error.userName = 'Excuse me ! Username already exists 😥';
            } else {
              this.userData.basicInfo.error.userName = 'عفوا ! اسم المستخدم موجود مسبقا 😥';
            }
            setTimeout(() => {
              this.userData.basicInfo.error.userName = null;
            }, 3000);
          });
        }
        if (CurrentPassword && NewPassword) {
          await axios.put('/api/v1/users/profile', {
            password: NewPassword,
          }).then(async () => {
            await axios.get('/api/v1/users/profile').then(async ({data: {profileData: response}}) => {
              await this.$store.dispatch('userProfile', response[0]);
              this.$swal.fire({
                position: 'center',
                icon: 'success',
                title: this.lang === 'en' ? `<strong>Updated</strong>` : `<strong>محدث</strong>`,
                text: this.lang === 'en' ? 'Your Password has been successfully updated' : 'تم تحديث كلمةالمرورالخاص بك بنجاح',
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
        if (this.lang === 'en') {
          this.userData.basicInfo.error.userName = 'Excuse me! The user Name field is required';
          this.userData.basicInfo.error.newPassword = 'Excuse me! The Password field is required';
        } else {
          this.userData.basicInfo.error.userName = "عفوا ! حقل إسم المستخدم مطلوب مطلوب";
          this.userData.basicInfo.error.newPassword = 'عفوا ! حقل كلمة المرور مطلوب مطلوب';
        }
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
    // this.socket = io('http://localhost:3001');
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
