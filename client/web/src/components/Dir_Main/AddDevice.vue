<template>
  <div>
    <!--   section number 2     -->
    <section class="section section__mTop">
      <header class="section__header">
        <h2 class="section__title">Processor</h2>
        <div class="section__control">
          <button class="section__button  focus--box-shadow" type="button">
            <i class="fas fa-cog"></i>
          </button>
          <button class="section__button section__button--painted focus--box-shadow" type="button"
                  @click.prevent="OpenProcessorId">
            <i class="fas fa-plus"></i>
          </button>
        </div>
      </header>
      <ul class="project">
        <li v-for="(ProcessorId,index) in userProcessorIds" v-bind:key="index"
            class="project__item">
          <a class="project__link focus--box-shadow" href="">
            <div class="project__wrapper">
              <div class="project__element project__icon">
                <div class="icon icon--viking">
                  <i class="fas fa-desktop"></i>
                </div>
              </div>
              <div class="project__element project__inform">
                <span class="project__inform-name">Processor # {{ ++index }}</span>
              </div>
              <div class="project__element project__inform">
                <span class="project__inform-name font__sizeXl">{{ ProcessorId }}</span>
              </div>

              <div class="project__element project__setting">
                <button class="setting focus--box-shadow" type="button" @click.prevent="deleteProcessorID(processorId)">
                  <i class="fas fa-trash-alt"></i>
                </button>
              </div>
            </div>
          </a>
        </li>
      </ul>
    </section><!-- End SECTION -->

    <!--    modal Add Processor id  -->
    <modal :resizable="false" :width="650" height="auto" name="AddNewProcessorId">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseProcessorId"></i>
      <div class="container__AddProcessorID">
        <h2>Add New Processor ID</h2>
        <div class="input-group__AddProcessorID">
          <label for="input_ProcessorID">Processor ID</label>
          <input id="input_ProcessorID" v-model.trim="Processor_ID" type="text">
          <p v-if="errorProcessorID" class="error_style pb-2">{{ errorProcessorID }}</p>
          <div class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="addProcessorID">Add Processor Id</button>
            <button class="btn btn-danger" @click.prevent="ClearProcessorID">Clear</button>
          </div>
        </div>
        <div class="otherWay_AddProcessorID">
          <div class="otherWay_btnAdd">
            <h4>Other Way To Add Processor ID</h4>
            <button class="btn btn-secondary" @click.prevent="openScan">
              {{ !isShowingCamera ? 'Scan BarCode' : 'Exit Scanning' }}
            </button>
          </div>
          <div id="handle__camera" class="handle__camera ScanOpen">

            <qrcode-stream v-if="isShowingCamera" @decode="onDecode" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">Wait For Open Camera...</p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">place camera, Pleas Check your web Came</p>
          </div>
        </div>
      </div>
    </modal>
    <!--   End modal Add Processor id  -->
  </div>
</template>

<script>

import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "AddDevice",
  data() {
    return {
      Processor_ID: null,
      errorProcessorID: null,
      isShowingCamera: false,
      isShowingWait: true,
    }
  },
  methods: {
    OpenProcessorId() {
      this.$modal.show('AddNewProcessorId')
    },
    CloseProcessorId() {
      this.$modal.hide('AddNewProcessorId')
    },
    async addProcessorID() {
      let ProcessorID = this.Processor_ID;
      if (ProcessorID) {
        let ProcessorIDRegex = /^[a-zA-Z0-9]+$/;
        let CheckProcessorID = ProcessorIDRegex.test(ProcessorID);//true or false
        if (!CheckProcessorID) {
          this.errorProcessorID = "Sorry! User Processor Id Faild, ignored space";
          setTimeout(() => {
            this.errorProcessorID = null;
          }, 3000);
          return false;
        }
        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const responseProcessor = await axios.post('/api/v1/user/processor', {
          ProcessorId: this.Processor_ID,
        });
        await this.$store.dispatch('processorId', responseProcessor.data.data);
        const ProcessorIds = await axios.get('/api/v1/user/processor');
        await this.$store.dispatch('userProcessorIds', ProcessorIds.data.data);
        this.$modal.hide('AddNewProcessorId')
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Add Processor ID, Successfully',
          toast: false,
          text: responseProcessor.data.data.ProcessorId,
          showConfirmButton: false,
          timer: 1500
        })
      } else {
        this.errorProcessorID = "Sorry! User Processor Id Faild!";
        setTimeout(() => {
          this.errorProcessorID = null;
        }, 3000);
      }

    },
    async ClearProcessorID() {
      this.Processor_ID = null;
    },
    openScan() {
      const handle__camera = document.getElementById('handle__camera');
      handle__camera.style.transition = 'all ease 0.3s ';
      handle__camera.classList.toggle('ScanOpen');
      this.isShowingCamera = !this.isShowingCamera;
      setTimeout(() => {
        this.isShowingWait = false;
      }, 550);


    },
    onDecode(decodedString) {
      this.Processor_ID = decodedString
      // console.log(decodedString)
      // ...
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
    async deleteProcessorID(processor_Id) {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const responseProcessor =
          await axios.delete('/api/v1/user/processor');
      // await this.$store.dispatch('userProcessorIds', responseProcessor.data.data);
      console.log(processor_Id)
      console.log(responseProcessor)

    },
  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const responseProcessor = await axios.get('/api/v1/user/processor');
    await this.$store.dispatch('userProcessorIds', responseProcessor.data.data);
  },
  computed: {
    ...mapGetters(['processorId', 'userProcessorIds'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AddDeviceStyle.css";
</style>
