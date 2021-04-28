<template>
  <section
      :class="lang==='ar' ? 'Main__Section-AddControllerUser direction-rtl' :' Main__Section-AddControllerUser direction-ltr'">
    <h2>{{ $t('Dashboard.AddControllerForProcessor.title') }}</h2>
    <div class="SubMain__Section-AddControllerUser">
      <div class="input-group__AddControllerUser">
        <label for="NameControllerUser">{{ $t('Dashboard.AddControllerForProcessor.Form.name') }}</label>
        <input id="NameControllerUser" v-model.trim="NameControllerUser"
               :placeholder="$t('Dashboard.AddControllerForProcessor.Form.placeholderName')"
               type="text">
      </div>
      <div class="input-group__AddControllerUser">
        <label for="TypeController">
          {{ $t('Dashboard.AddControllerForProcessor.Form.typeController') }}
          {{ typeController_id ? typeController_id.label : '' }}
        </label>
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
        <label for="ControllerID">{{ $t('Dashboard.AddControllerForProcessor.Form.controllerID') }} </label>
        <div
            :class=" lang==='en' ? 'input__BTN-group__AddControllerUser direction-ltr' :'input__BTN-group__AddControllerUser '"
            :style="lang==='ar' ?'margin-right: -0.5rem' : ''">
          <input id="ControllerID" v-model.trim="ControllerID"
                 :placeholder="$t('Dashboard.AddControllerForProcessor.Form.placeholderScan')"
                 :style="lang==='ar' ?'margin-left: 1.5rem' : ''"
                 type="text">
          <button class="btn btn-secondary" @click.prevent="OpenScanModalControllerID">
            {{ $t('Dashboard.AddControllerForProcessor.Form.btn_Scan') }}
          </button>
        </div>
      </div>
      <div class="input-group__AddControllerUser">
        <label for="LocationController">{{ $t('Dashboard.AddControllerForProcessor.Form.locationController') }}
          {{ locationController_id ? locationController_id.label : '' }}</label>
        <div @click.prevent="getAllLocationController">
          <v-select id="LocationController" v-model.trim="locationController_id"
                    :options="$store.getters.LocationControllerData ? $store.getters.LocationControllerData : ''"
                    :value="$store.getters.LocationControllerData ? $store.getters.LocationControllerData : ''"></v-select>
        </div>

      </div>
    </div>
    <div>
      <button :style="lang==='ar' ? 'margin-left: 0.5rem' :''" class="btn btn-info"
              @click.prevent="CreateControllerForUser">{{ $t('Dashboard.AddControllerForProcessor.Form.buttonCreate') }}
      </button>
      <button class="btn btn-danger"
              @click.prevent="ClearAllDataController">{{ $t('Dashboard.AddControllerForProcessor.Form.buttonClear') }}
      </button>
    </div>
    <!--    modal Search Processor id  -->
    <modal :class=" lang==='ar' ? 'direction-ltr' :''" :resizable="false" :width="650" height="auto"
           name="ScanControllerId" @closed="CloseScanModalControllerID"
           @before-open="CloseScanModalControllerID">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseScanModalControllerID"></i>
      <div class="container__AddProcessorID">
        <h3> {{ $t('Dashboard.AddControllerForProcessor.webCame.title') }}</h3>
        <div class="otherWay_AddProcessorID">
          <div class="handle__camera">
            <qrcode-stream v-if="isShowingCamera" @decode="onDecodeControllerID" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">
                {{ $t('Dashboard.AddControllerForProcessor.webCame.messageWait') }}</p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">{{ $t('Dashboard.AddControllerForProcessor.webCame.error') }}</p>
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

export default {
  name: "AddController",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
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
            title: this.lang === 'en' ? `Add Controller ${response[0].name} , Successfully` : `إضافة وحدة التحكم  ${response[0].name} , بنجاح `,
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
              title: this.lang === 'en' ? `<strong>this Controller Already Exist</strong>` : `<strong>وحدة التحكم هذه موجودة بالفعل</strong>`,
              toast: false,
              text: this.lang === 'en' ? 'Failed Add Controller' : 'فشل إضافة وحدة تحكم',
              showConfirmButton: false,
              timer: 1500
            })
          } else {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: this.lang === 'en' ? `<strong>Failed Add Controller</strong>` : `<strong>فشل إضافة وحدة تحكم</strong>`,
              toast: false,
              showConfirmButton: false,
              timer: 1500
            })
          }
        });

      } else {
        if (this.lang === 'en') {
          this.error = 'All Input Required';
        } else {
          this.error = 'كل المدخلات مطلوبة';
        }
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
    // this.socket = io('http://localhost:3001');
  },
}
</script>

<style scoped>
@import "../../assets/css/_AddControllerUserStyle.css";
</style>
