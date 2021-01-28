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
        <h2 class="profile__Main-EditInfoH2">Update Your Profile</h2>
        <div class="profile__Main-EditInfo">
          <div class="profile__image-outline">
            <img :alt="this.imageName" :src="imageLoading">
          </div>
          <p class="image__errors">{{ errors }}</p>
          <div class="profile__image-btn">
            <input id="input__file" ref="imageInput" accept="image/*" hidden type="file" @change.prevent="previewImage">
            <button v-show="!imageLoading" class="Add-btn btn btn-info" @click.prevent="onPickImage">Choose Image
            </button>
            <button v-show="imageLoading" class="upload-btn btn btn-success" @click.prevent="onUploadImage">upload
              Image
            </button>
            <button v-show="imageLoading" class="btn btn-danger" @click.prevent="onCancelImage">Cancel</button>
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
    onUploadImage() {
      this.picture = null;
      const D = new Date();
      const Year = D.getFullYear();
      const Month = D.getMonth();
      const Time = D.getTime();
      const FullName = 'User/Profile/' + `${Year}_${Month}_${Time}`;
      const storageRef = firebase.storage().ref(FullName + `${this.imageName}`).put(this.imageData);
      storageRef.on(`state_change`, snapshot => {
        this.uploadValue = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
      }, error => {
        console.log(error.message)
      }, () => {
        this.uploadValue = 100;
        storageRef.snapshot.ref.getDownloadURL().then((url) => {
          this.picture = url;
          // API code For update in dataBase
          // this.picture = url in firebase
        })
      })
      this.onCancelImage();
    },
  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const {user} = (await axios.get('/api/v1/users/me')).data;
    await this.$store.dispatch('user', user);
  },
  computed: {
    ...mapGetters(['user', 'TokenUser'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_ProfileUserMainStyle.css";
</style>
