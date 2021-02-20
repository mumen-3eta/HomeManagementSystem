<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <div class="container_Admin_CreateController">
        <h2>Create Controller</h2>
        <div class="btn-group_Generate">
          <button class="btn btn-secondary" @click.prevent="generateControllerId">Generate Id Controller</button>
          <button class="btn btn-danger" @click.prevent="clearGenerateControllerId">Clear</button>
        </div>
        <div class="container__DeviceId">
          <div v-if="controllerId" class="container__processor_Id">
            <div class="svg_Generator">
              <div>
                <qrcode-vue id="svg_element" :margin="2" :quality="1" :scale="7" v-bind:value="controllerId">Sorry ,
                  some
                  thing error
                </qrcode-vue>
                <p v-if="controllerId">{{ controllerId }}</p>
              </div>
            </div>
            <div class="btn-group_Generate-2">
              <button class="btn btn-secondary" @click.prevent="copyTextControllerId">Copy Processor Id</button>
              <button id="DownloadsAsImage" class="btn btn-primary"
                      @click.prevent="downloadsControllerIdAsImage(controllerId)">
                Downloads as image
              </button>
            </div>
          </div>
          <canvas id="canvas" hidden></canvas>
          <div hidden>
            <label for="ProcessorIDInput" hidden></label>
            <input id="ProcessorIDInput" disabled hidden v-bind:value="controllerId"/>
          </div>
        </div>
      </div>
    </main><!-- End Main -->
    <!--    aside here -->
    <AsideMain/>

  </div>
</template>

<script>
import HeaderMain from "@/layout/Main_Layout/HeaderMain";
import AsideMain from "@/layout/Main_Layout/AsideMain";
import NavMain from "@/layout/Main_Layout/NavMain";
import axios from "axios";
import {mapGetters} from "vuex";
import QrcodeVue from 'vue-qrcode';

export default {
  name: "AdminCreateControllerPage",
  data() {
    return {
      processor_Id: null,
    }
  },
  components: {
    HeaderMain, AsideMain, NavMain, QrcodeVue
  },
  methods: {
    async generateControllerId() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const processorId = await axios.post('/api/v1/controller');
      await this.$store.dispatch('controllerId', processorId.data.controllerId);
      this.$swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Create Controller ID',
        toast: false,
        text: processorId.data.controllerId,
        showConfirmButton: false,
        timer: 1500
      })
    },
    async clearGenerateControllerId() {
      if (this.$store.getters.controllerId) {
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Cleared Successfully',
          toast: false,
          text: null,
          showConfirmButton: false,
          timer: 1500
        })
      }
      await this.$store.dispatch('controllerId', null);
    },
    copyTextControllerId() {
      let copyText = document.getElementById("ProcessorIDInput");
      copyText.select();
      document.execCommand("copy");
      this.$swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Copied the text',
        toast: true,
        text: copyText.value,
        showConfirmButton: false,
        timer: 1500
      })
    },
    downloadsControllerIdAsImage(processorId) {
      const image = document.getElementById('svg_element').src;
      let imageSplit_1 = image.split(';')[0];
      let imageExtension = imageSplit_1.split('/')[1];
      let a = document.createElement('a');
      a.href = image;
      a.download = `ProcessorID_${processorId}.${imageExtension}`;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    },
  },
  beforeMount() {
    this.processor_Id = this.$store.getters.controllerId;
  },
  computed: {
    ...mapGetters(['controllerId'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminCreateContorllerPageStyle.css";
</style>
