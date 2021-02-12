<template>
  <div>

  </div>
  <!--  <div class="body-profileUser">-->
  <!--    <TopNavBar/>-->
  <!--    <LeftNavBar/>-->

  <!--    <section>-->
  <!--      <div class="container py-5 px-5 ">-->
  <!--        <div class="row anim-title">-->
  <!--          <div class="col-12 col-md-12 col-lg-12">-->
  <!--            <h2 v-if="user.basicInfo.userName"> Profile | {{ user.basicInfo.userName }}</h2>-->
  <!--          </div>&lt;!&ndash; End Of col&ndash;&gt;-->
  <!--        </div>&lt;!&ndash; End Of row&ndash;&gt;-->

  <!--        <div class="row justify-content-center align-items-center py-3">-->
  <!--          <div-->
  <!--              class="anim-left col-12 col-md-4 col-lg-6 d-flex justify-content-center align-items-center flex-column right__content">-->
  <!--            <img v-show="user.profileInfo.image" :src="user.profileInfo.image" alt="User Image"-->
  <!--                 class="user__profile-image"-->
  <!--                 title="User Image">-->

  <!--            <div class="user__profile-info py-4 text-center">-->
  <!--              <h3 v-if="user.basicInfo.FullName">{{ user.basicInfo.FullName }}</h3>-->
  <!--              <h6 v-if="user.basicInfo.userName">{{ user.basicInfo.userName }}</h6>-->
  <!--              <p>{{ user.basicInfo.email }}</p>-->
  <!--            </div>-->
  <!--            <form @submit.prevent="checkImage">-->
  <!--              <div class="d-flex flex-column">-->
  <!--                <button v-if="uploadValue.toFixed() != 100" class="btn btn-danger my-3" @click.prevent="onPickImage">-->
  <!--                  Choose Image-->
  <!--                </button>-->
  <!--                <input ref="imageInput" accept="image/*" style="display: none" type="file"-->
  <!--                       @change="previewImage" @change.prevent="onUpload">-->
  <!--                <button v-if="uploadValue.toFixed() == 100" class="btn btn-success my-3" type="submit">Update</button>-->
  <!--                <div v-if="uploadValue.toFixed() != 100">-->
  <!--                  <p> Progress : {{ uploadValue.toFixed() + "%" }}-->
  <!--                    <progress :value="uploadValue" max="100"></progress>-->
  <!--                  </p>-->
  <!--                </div>-->
  <!--              </div>-->
  <!--            </form>-->
  <!--          </div>&lt;!&ndash; End Of col&ndash;&gt;-->
  <!--          <div class="anim-right col-12 col-md-8 col-lg-6 left__content">-->
  <!--            <div class="py-4">-->
  <!--              <h4 style="text-align: center">Update Profile</h4>-->
  <!--              <hr class="user__profile-hr mx-auto">-->
  <!--            </div>-->

  <!--            <ul id="myTab" class="nav nav-tabs" role="tablist">-->
  <!--              <li class="nav-item" role="presentation">-->
  <!--                <a id="info-tab" aria-controls="info" aria-selected="true" class="nav-link active" data-toggle="tab"-->
  <!--                   href="#info"-->
  <!--                   role="tab">Base Info</a>-->
  <!--              </li>-->
  <!--              <li class="nav-item" role="presentation">-->
  <!--                <a id="password-tab" aria-controls="password" aria-selected="false" class="nav-link" data-toggle="tab"-->
  <!--                   href="#password" role="tab">Login info</a>-->
  <!--              </li>-->
  <!--            </ul>-->
  <!--            <div id="myTabContent" class="tab-content">-->
  <!--              <div id="info" aria-labelledby="info-tab" class="tab-pane fade show active" role="tabpanel">-->
  <!--                <form class="py-3" @submit.prevent="checkForm">-->
  <!--                  <div class="form-group my-2">-->
  <!--                    <label for="exampleInputEmail1">First Name<span class="text-danger px-1">*</span></label>-->
  <!--                    <input id="exampleInputEmail1" v-model.trim="email" aria-describedby="emailHelp"-->
  <!--                           class="form-control"-->
  <!--                           placeholder="example@example.example" type="email">-->
  <!--                    <small id="emailHelp" class="form-text text-muted"></small>-->
  <!--                  </div>-->
  <!--                  <div class="form-group my-3">-->
  <!--                    <label for="exampleInputName">last Name<span class="text-danger px-1">*</span></label>-->
  <!--                    <input id="exampleInputName" v-model="name" aria-describedby="nameHelp" class="form-control"-->
  <!--                           placeholder="example example" type="text">-->
  <!--                    <small id="nameHelp" class="form-text text-muted"></small>-->
  <!--                  </div>-->
  <!--                  <div class="form-group my-3">-->
  <!--                    <label for="exampleInputURLImage">URL Image<span-->
  <!--                        class="text-danger font-weight-bold text-danger px-1">(optional)</span></label>-->
  <!--                    <input id="exampleInputURLImage" v-model="inputURLImage" aria-describedby="URLImageHelp"-->
  <!--                           class="form-control"-->
  <!--                           placeholder="https://example.com/image.example" type="url">-->
  <!--                    <small id="URLImageHelp" class="form-text text-muted"></small>-->
  <!--                  </div>-->
  <!--                  <div class="my-3">-->
  <!--                    <button class="btn btn-info text-light" type="submit">Update</button>-->
  <!--                  </div>-->
  <!--                </form>-->
  <!--              </div>-->
  <!--              <div id="password" aria-labelledby="password-tab" class="tab-pane fade" role="tabpanel">-->
  <!--                <form class="py-3">-->
  <!--                  <div class="form-group my-2">-->
  <!--                    <label for="exampleInputCurrentPassword">E-mail<span-->
  <!--                        class="text-danger px-1">*</span></label>-->
  <!--                    <input id="exampleInputCurrentPassword" aria-describedby="currentPasswordHelp" class="form-control"-->
  <!--                           placeholder="test@test.com" type="email">-->
  <!--                    <small id="currentPasswordHelp" class="form-text text-muted"></small>-->
  <!--                  </div>-->
  <!--                  <div class="form-group my-3">-->
  <!--                    <label for="exampleInputPassword1">User Name<span class="text-danger px-1">*</span></label>-->
  <!--                    <input id="exampleInputPassword1" aria-describedby="passwordHelp" class="form-control"-->
  <!--                           placeholder="your User Name" type="text">-->
  <!--                    <small id="passwordHelp" class="form-text text-muted"></small>-->
  <!--                  </div>-->
  <!--                  <div class="form-group my-3">-->
  <!--                    <label for="exampleInputConfirmPassword">Password<span-->
  <!--                        class="text-danger px-1">*</span></label>-->
  <!--                    <input id="exampleInputConfirmPassword" aria-describedby="passwordHelp" class="form-control"-->
  <!--                           placeholder="**********" type="password">-->
  <!--                    <small id="confirmPasswordHelp" class="form-text text-muted"></small>-->
  <!--                  </div>-->
  <!--                  <div class="my-3">-->
  <!--                    <button class="btn btn-info text-light" type="submit">Update</button>-->
  <!--                  </div>-->
  <!--                </form>-->
  <!--              </div>-->
  <!--            </div>-->
  <!--          </div>&lt;!&ndash; End Of col&ndash;&gt;-->
  <!--        </div>&lt;!&ndash; End Of row&ndash;&gt;-->
  <!--      </div>&lt;!&ndash; End Of container&ndash;&gt;-->
  <!--      &lt;!&ndash; body section &ndash;&gt;-->
  <!--    </section>-->
  <!--  </div>-->

</template>

<script>
// import TopNavBar from "@/components/MainPage/Nav/TopNav";
// import LeftNavBar from "@/components/MainPage/Nav/LeftNav";
// import {mapGetters} from "vuex";
// import axios from "axios";
// import firebase from 'firebase/app';
// import 'firebase/storage';
// import {TimelineLite} from "gsap";

export default {
  name: "ProfileUser",
  // components: {
  //   TopNavBar,
  //   LeftNavBar,
  // },
  // mounted() {
  //   const animTitle = document.getElementsByClassName('anim-title');
  //   const animLeft = document.getElementsByClassName('anim-left');
  //   const animRight = document.getElementsByClassName('anim-right');
  //   const timeline = new TimelineLite()
  //   timeline.from(animTitle, {opacity: 0, duration: 1, y: -50})
  //       .from(animLeft, {opacity: 0, duration: 1, y: -50}, '-=0.8')
  //       .from(animRight, {opacity: 0, duration: 1, y: -50}, '-=0.6')
  //
  // },
  // data() {
  //   return {
  //     name: this.$store.getters.GetUser.name,
  //     email: this.$store.getters.GetUser.email,
  //     imageUrlRef: this.$store.getters.GetUser.image,
  //     imageData: null,
  //     picture: null,
  //     uploadValue: 0,
  //     inputURLImage: null,
  //   }
  // },
  // methods: {
  //   async checkForm() {
  //     try {
  //       let ImagePath;
  //       if (this.inputURLImage) {
  //         ImagePath = this.inputURLImage;
  //       } else {
  //         ImagePath = this.$store.getters.GetUser.image;
  //       }
  //
  //       let response = await axios.patch('update', {
  //         name: this.name,
  //         email: this.email,
  //         image: ImagePath,
  //       });
  //       await this.$store.dispatch('user', response.data);
  //     } catch (e) {
  //       this.error = 'Error'
  //     }
  //
  //   },
  //   async checkImage() {
  //     try {
  //       let ImagePath;
  //       if (this.picture) {
  //         ImagePath = this.picture;
  //       } else {
  //         ImagePath = this.$store.getters.GetUser.image;
  //       }
  //
  //       let response = await axios.patch('updateImage', {
  //         image: ImagePath,
  //       });
  //       await this.$store.dispatch('user', response.data);
  //       this.uploadValue = 0;
  //     } catch (e) {
  //       this.error = 'Error'
  //       this.uploadValue = 0;
  //     }
  //   },
  //   onPickImage() {
  //     this.$refs.imageInput.click()
  //   },
  //
  //   previewImage(event) {
  //     this.uploadValue = 0;
  //     this.picture = null;
  //     this.imageData = event.target.files[0];
  //   },
  //   onUpload() {
  //     this.picture = null;
  //     const D = new Date();
  //     const Year = D.getFullYear();
  //     const Month = D.getMonth();
  //     const Time = D.getTime();
  //     const FullName = 'User/Profile/' + `${Year}_${Month}_${Time}`;
  //     const storageRef = firebase.storage().ref(FullName + `${this.imageData.name}`).put(this.imageData);
  //     storageRef.on(`state_change`, snapshot => {
  //       this.uploadValue = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
  //     }, error => {
  //       console.log(error.message)
  //     }, () => {
  //       this.uploadValue = 100;
  //       storageRef.snapshot.ref.getDownloadURL().then((url) => {
  //         this.picture = url;
  //       })
  //     })
  //   },
  //
  // },
  // computed: {
  //   ...mapGetters(['GetUser', 'user'])
  //
  //
  // },
  // beforeMount() {
  //   axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
  // }
}
</script>

<!--<style scoped>-->
<!--@import "../../assets/temp/Temp_Old_Style__needDelete/MainPageStyle/_profileUserStyle.css";-->
<!--</style>-->
