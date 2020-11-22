<template>
  <div class="body-profileUser">
    <TopNavBar/>
    <LeftNavBar/>

    <section>
      <div class="container py-5 px-5 ">
        <div class="row">
          <div class="col-12 col-md-12 col-lg-12">
            <h2> Profile | {{GetUser.name}}</h2>
          </div><!-- End Of col-->
        </div><!-- End Of row-->

        <div class="row justify-content-center align-items-center py-3">
            <div class="col-12 col-md-4 col-lg-6 d-flex justify-content-center align-items-center flex-column right__content">
              <img class="user__profile-image"  v-show="!GetUser.image" src="@/assets/img/man.svg" title="User Image" alt="User Image">
              <img class="user__profile-image"  v-show="GetUser.image" :src="GetUser.image" title="User Image" alt="User Image">
              <div class="user__profile-info py-4 text-center">
                  <h3>{{GetUser.name}}</h3>
                  <p>{{GetUser.email}}</p>
              </div>
              <form @submit.prevent="checkImage">
                  <div class="d-flex flex-column">
                  <button v-if="uploadValue.toFixed() != 100"  class="btn btn-danger my-3" @click.prevent="onPickImage">Choose Image</button>
                    <input type="file" accept="image/*" @change.prevent="previewImage" @change="onUpload" style="display: none" ref="imageInput">
                  <button v-if="uploadValue.toFixed() == 100" class="btn btn-success my-3" type="submit">Update</button>
                  <div v-if="uploadValue.toFixed() != 100">
                    <p> Progress : {{uploadValue.toFixed() + "%" }}
                      <progress :value="uploadValue" max="100"></progress>
                    </p>
                  </div>
                </div>
              </form>
            </div><!-- End Of col-->
            <div class="col-12 col-md-8 col-lg-6 left__content">
              <div class="py-4">
                <h4 style="text-align: center">Update Profile</h4>
                <hr class="user__profile-hr">
              </div>

            <ul class="nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item" role="presentation">
                <a class="nav-link active" id="info-tab" data-toggle="tab" href="#info" role="tab" aria-controls="info" aria-selected="true">Info</a>
              </li>
              <li class="nav-item" role="presentation">
                <a class="nav-link" id="password-tab" data-toggle="tab" href="#password" role="tab" aria-controls="password" aria-selected="false">Password</a>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="info" role="tabpanel" aria-labelledby="info-tab">
                <form @submit.prevent="checkForm" class="py-3">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Email address<span class="text-danger px-1">*</span></label>
                    <input type="email" v-model.trim="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="example@example.example">
                    <small id="emailHelp" class="form-text text-muted"></small>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputName">Your Name<span class="text-danger px-1">*</span></label>
                    <input type="text" v-model="name" class="form-control" id="exampleInputName" aria-describedby="nameHelp" placeholder="example example">
                    <small id="nameHelp" class="form-text text-muted"></small>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputURLImage">URL Image<span class="text-danger font-weight-bold text-danger px-1">(optional)</span></label>
                    <input type="url" v-model="inputURLImage" class="form-control" id="exampleInputURLImage" aria-describedby="URLImageHelp" placeholder="https://example.com/image.example">
                    <small id="URLImageHelp" class="form-text text-muted"></small>
                  </div>
                  <div>
                    <button type="submit" class="btn btn-info">Update</button>
                  </div>
                </form>
              </div>
              <div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
                <form class="py-3">
                  <div class="form-group">
                    <label for="exampleInputCurrentPassword">Current Password<span class="text-danger px-1">*</span></label>
                    <input type="password" class="form-control" id="exampleInputCurrentPassword" aria-describedby="currentPasswordHelp" placeholder="**********">
                    <small id="currentPasswordHelp" class="form-text text-muted"></small>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">New Password<span class="text-danger px-1">*</span></label>
                    <input type="password" class="form-control" id="exampleInputPassword1" aria-describedby="passwordHelp" placeholder="**********">
                    <small id="passwordHelp" class="form-text text-muted"></small>
                  </div>
                  <div class="form-group">
                    <label for="exampleInputConfirmPassword">Confirm Password<span class="text-danger px-1">*</span></label>
                    <input type="password" class="form-control" id="exampleInputConfirmPassword" aria-describedby="passwordHelp" placeholder="**********">
                    <small id="confirmPasswordHelp" class="form-text text-muted"></small>
                  </div>
                  <div>
                    <button type="submit" class="btn btn-info">Update</button>
                  </div>
                </form>
              </div>
            </div>
          </div><!-- End Of col-->
        </div><!-- End Of row-->
      </div><!-- End Of container-->
      <!-- body section -->
    </section>
  </div>

</template>

<script>
import TopNavBar from "@/components/MainPage/Nav/TopNav";
import LeftNavBar from "@/components/MainPage/Nav/LeftNav";
import {mapGetters} from "vuex";
import axios from "axios";
import firebase from 'firebase/app';
import 'firebase/storage';
export default {
  name: "ProfileUser",
  components:{
    TopNavBar,
    LeftNavBar,
  },
  data(){
    return{
      name: this.$store.getters.GetUser.name ,
      email: this.$store.getters.GetUser.email ,
      imageUrlRef: this.$store.getters.GetUser.image ,
      imageData:null,
      picture:null,
      uploadValue:0,
      inputURLImage:null,
    }
  },
  methods:{
    async checkForm () {
      try {
        let ImagePath;
        if (this.picture){
          ImagePath = this.$store.getters.GetUser.image;
        }
        else if(this.inputURLImage){
          ImagePath = this.inputURLImage;
        }
        else{
          ImagePath = this.$store.getters.GetUser.image;
        }

        let response  = await axios.patch('update',{
          name      : this.name,
          email     : this.email,
          image     : ImagePath,
        });
        await this.$store.dispatch('user', response.data);
      }catch (e) {
        this.error = 'Error'
      }

    },
    async checkImage(){
      try {
        let ImagePath;
        if (this.picture){
          ImagePath = this.picture;
        }else{
          ImagePath = this.$store.getters.GetUser.image;
        }

        let response  = await axios.patch('updateImage',{
          image     : ImagePath,
        });
        await this.$store.dispatch('user', response.data);
        this.uploadValue = 0;
      }catch (e) {
        this.error = 'Error'
        this.uploadValue = 0;
      }
    },
    onPickImage(){
        this.$refs.imageInput.click()
    },

    previewImage(event){
      this.uploadValue = 0;
      this.picture = null;
      this.imageData = event.target.files[0];
    },
    onUpload(){
      this.picture = null;
      const D = new Date();
      const Year = D.getFullYear();
      const Month = D.getMonth();
      const Time = D.getTime();
      const FullName = 'User/Profile/'+ `${Year}_${Month}_${Time}` ;
      const storageRef = firebase.storage().ref(FullName + `${this.imageData.name}`).put(this.imageData);
      storageRef.on(`state_change` , snapshot=>{
        this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
      },error=>{console.log(error.message)},()=>{this.uploadValue=100;
        storageRef.snapshot.ref.getDownloadURL().then((url)=>{
          this.picture = url;
        })
      })
    },

  },
  computed:{
    ...mapGetters(['GetUser']),
    ...mapGetters(['user']),

  },
}
</script>

<style scoped>
    @import "../../assets/css/MainPageStyle/_profileUserStyle.css";
</style>
