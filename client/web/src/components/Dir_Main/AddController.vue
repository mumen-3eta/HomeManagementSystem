<template>
  <!--  <div>-->
  <!--    <p>Add controller {{ ProcessorID }}</p>-->
  <!--  </div>-->
  <section class="Main__Section-AddControllerUser">
    <h2>Add Controller</h2>
    <div class="SubMain__Section-AddControllerUser">
      <div class="input-group__AddControllerUser">
        <label for="NameControllerUser">Name</label>
        <input id="NameControllerUser" v-model.trim="NameControllerUser" placeholder="Enter Name Controller"
               type="text">
      </div>
      <div class="input-group__AddControllerUser">
        <label for="TypeController">Type Controller : {{ typeController_id ? typeController_id.label : '' }}</label>
        <div @click.prevent="getAllTypeController">
          <!--          $store.state.TypeControllerData-->
          <v-select id="TypeController" v-model.trim="typeController_id"
                    :options="$store.getters.TypeControllerData ? $store.getters.TypeControllerData : ''"
                    :value="$store.getters.TypeControllerData ? $store.getters.TypeControllerData : ''"></v-select>
        </div>
      </div>
    </div>
    <div class="SubMain__Section-AddControllerUser">
      <div class="input-group__AddControllerUser">
        <label for="ControllerID">Controller ID</label>
        <div class="input__BTN-group__AddControllerUser">
          <input id="ControllerID" v-model.trim="ControllerID" placeholder="Enter Controller id Or Scan QRCode"
                 type="text">
          <button class="btn btn-secondary" @click.prevent="OpenScanModalControllerID">Scan</button>
        </div>
      </div>
      <div class="input-group__AddControllerUser">
        <label for="LocationController">Location Controller :
          {{ locationController_id ? locationController_id.label : '' }}</label>
        <div @click.prevent="getAllLocationController">
          <v-select id="LocationController" v-model.trim="locationController_id"
                    :options="$store.getters.LocationControllerData ? $store.getters.LocationControllerData : ''"
                    :value="$store.getters.LocationControllerData ? $store.getters.LocationControllerData : ''"></v-select>
        </div>

      </div>
    </div>
    <div>
      <button class="btn btn-info" @click.prevent="CreateControllerForUser">Create</button>
      <button class="btn btn-danger" @click.prevent="ClearAllDataController">Clear</button>
    </div>
    <!--    modal Search Processor id  -->
    <modal :resizable="false" :width="650" height="auto" name="ScanControllerId" @closed="CloseScanModalControllerID"
           @before-open="CloseScanModalControllerID">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseScanModalControllerID"></i>
      <div class="container__AddProcessorID">
        <h3>Scan Controller QRCode</h3>
        <div class="otherWay_AddProcessorID">
          <div class="handle__camera">
            <qrcode-stream v-if="isShowingCamera" @decode="onDecodeControllerID" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">Wait For Open Camera...</p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">place camera, Pleas Check your web Came</p>
          </div>
        </div>
      </div>
    </modal>
    <!--   End modal Search Processor id  -->

  </section>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";
import io from "socket.io-client";

export default {
  name: "AddController",
  data() {
    return {
      ProcessorID: this.$route.params.processor_id ? this.$route.params.processor_id : '',
      ControllerID: '',
      NameControllerUser: '',
      typeController_id: '',
      locationController_id: '',
      isShowingCamera: false,
      isShowingWait: true,
      searchTerm: '',
      Status: false,
      error: '',
    }
  },
  methods: {
    /*** Create Controller For User ***/
    async CreateControllerForUser() {
      if (this.ProcessorID && this.ControllerID && this.NameControllerUser && this.typeController_id && this.locationController_id) {

        await axios.post('/api/v1/controller/connection', {
          processorId: this.ProcessorID ? this.ProcessorID : this.$route.params.processor_id,
          name: this.NameControllerUser,
          typeId: this.typeController_id.id,
          locationId: this.locationController_id.id,
          controllerId: this.ControllerID
        }).then(async ({data: {connectionData: response}}) => {
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: `Add Controller ${response[0].name} , Successfully`,
            toast: false,
            text: response[0].id,
            showConfirmButton: false,
            timer: 1500
          })
          await this.$router.push({path: '/v2/main/device/all/controller/' + this.$route.params.processor_id});
        }).catch((e) => {
          let errorArr = e.message.split(' ');
          let ResultFilter = errorArr.filter(code => code.toString().toLowerCase() === '409')
          if (ResultFilter[0] === '409') {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: `<strong>this Controller Already Exist</strong>`,
              toast: false,
              text: 'Add Controller, Faild',
              showConfirmButton: false,
              timer: 1500
            })
          } else {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: `<strong>Add Controller, Faild</strong>`,
              toast: false,
              showConfirmButton: false,
              timer: 1500
            })
          }
        });

      } else {
        this.error = 'All Input Required';
        setTimeout(() => {
          this.error = false;
        }, 2000);
      }

    },

    /*** Open Scan Modal Controller ID ***/
    OpenScanModalControllerID() {
      this.$modal.show('ScanControllerId')
      this.isShowingCamera = !this.isShowingCamera;
      setTimeout(() => {
        this.isShowingWait = false;
      }, 550);
    },

    /*** Close Scan Modal Controller ID ***/
    CloseScanModalControllerID() {
      this.$modal.hide('ScanControllerId')
      this.isShowingCamera = false;
      this.isShowingWait = true
    },

    /*** Decode Scan QRCode ***/
    onDecodeControllerID(decodedString) {
      this.ControllerID = decodedString
      setTimeout(() => {
        this.$modal.hide('ScanControllerId')
        this.isShowingCamera = false;
        this.isShowingWait = true
      }, 50);
    },
    async onInit(promise) {
      try {
        await promise;
        console.log("init", promise);
      } catch (e) {
        console.log(e);
      } finally {
        //  ....
      }
    },

    /*** Clear All Data Controller ***/
    ClearAllDataController() {
      this.ControllerID = null;
      this.NameControllerUser = null;
      this.typeController_id = null;
      this.locationController_id = null;
      this.isShowingCamera = false;
      this.isShowingWait = true;
      this.getAllTypeController();
      this.getAllLocationController();
    },

    /* get All Controller Location */
    async getAllLocationController() {
      await axios.get('/api/v1/controller/location').then(async ({data: {locationLabels: response}}) => {
        await this.$store.dispatch('LocationControllerData', response);
      }).catch(() => {
        console.log("get Faild LocationControllerData")
      });
    },

    /*** Get All Type Controller  ***/
    async getAllTypeController() {
      await axios.get('/api/v1/controller/type').then(async ({data: {typeLabels: response}}) => {
        await this.$store.dispatch('TypeControllerData', response);
      }).catch(() => {
        console.log("get Faild TypeControllerData")
      });
    }


  },
  beforeMount() {
    this.getAllTypeController();
    this.getAllLocationController();
  },
  computed: {
    ...mapGetters(['TypeControllerData', 'LocationControllerData'])
  },
  created() {
    this.socket = io('http://localhost:3001');
  },
}
</script>

<style scoped>
@import "../../assets/css/_AddControllerUserStyle.css";
</style>
