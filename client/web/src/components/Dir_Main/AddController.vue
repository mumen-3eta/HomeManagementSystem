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
          <v-select id="TypeController" v-model.trim="typeController_id" :options="$store.state.TypeControllerData"
                    :value="$store.state.TypeControllerData"></v-select>
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
                    :options="$store.state.LocationControllerData"
                    :value="$store.state.LocationControllerData"></v-select>
        </div>

      </div>
    </div>
    <div>
      <button class="btn btn-info" @click.prevent="CreateControllerForUser">Create</button>
      <button class="btn btn-danger" @click.prevent="ClearAllDataController">Clear</button>
    </div>
    <AllController/>
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
import AllController from "@/components/Dir_Main/AllController";

export default {
  name: "AddController",
  components: {AllController},
  data() {
    return {
      ProcessorID: this.$route.params.processor_id,
      ControllerID: null,
      NameControllerUser: null,
      typeController_id: null,
      locationController_id: null,
      isShowingCamera: false,
      isShowingWait: true,
      searchTerm: null,
      Status: false,
      columns: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Name',
          field: 'name',
          type: 'string',
        },
        {
          label: 'Controller ID',
          field: 'Controller_Id',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Action',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: [
        {
          id: "1",
          name: "Controller #1",
          Controller_Id: "185w5158d6wa185w6418fe15fe",
          btn_Action: "",
        },
        {
          id: "2",
          name: "Controller #2",
          Controller_Id: "185185dw6d35181d86418fe15fe",
          btn_Action: "",
        },
        {
          id: "3",
          name: "Controller #3",
          Controller_Id: "185w51d418wdwa3w6418fe15fe",
          btn_Action: "",
        },
      ],
    }
  },
  methods: {
    /*** Create Controller For User ***/
    async CreateControllerForUser() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      if (this.ProcessorID && this.ControllerID && this.NameControllerUser && this.typeController_id && this.locationController_id) {
        const {data: {data: NewControllerData}} = await axios.post('/api/v1/user/controller', {
          name: this.NameControllerUser,
          ProcessorId: this.ProcessorID,
          typeId: this.typeController_id.labelId,
          locationId: this.locationController_id.locationId,
          controllerId: this.ControllerID
        });
        await this.$store.dispatch('NewControllerData', NewControllerData);
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: `Add Controller ${NewControllerData.name} , Successfully`,
          toast: false,
          text: NewControllerData._id,
          showConfirmButton: false,
          timer: 1500
        })
        await this.$router.push({path: '/v2/main/device/add'});
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

    /*** Get All Type Controller ***/
    async getAllTypeController() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const {data: {data: TypeControllerData}} = await axios.get('/api/v1/controller/type');
      this.socket.emit("user_TypeControllerData", TypeControllerData);
      this.socket.on("user_TypeControllerData_server", (data) => {
        this.$store.dispatch('TypeControllerData', data);
      });
      // await this.$store.dispatch('TypeControllerData', TypeControllerData);
    },

    /*** Get All Location Controller ***/
    async getAllLocationController() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const {data: {data: LocationControllerData}} = await axios.get('/api/v1/controller/location');
      this.socket.emit("user_LocationControllerData", LocationControllerData);
      this.socket.on("user_LocationControllerData_server", (data) => {
        this.$store.dispatch('LocationControllerData', data);
      });
      // await this.$store.dispatch('LocationControllerData', LocationControllerData);
    },


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
