<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <div class="container_Admin_CreateDevice">
        <h2>Create Device</h2>
        <div class="btn-group_Generate">
          <button class="btn btn-secondary" @click.prevent="generateId">Generate Id Processor</button>
          <button class="btn btn-danger" @click.prevent="clearGenerateId">Clear</button>
        </div>
        <div class="container__DeviceId">
          <div v-if="processorId" class="container__processor_Id">
            <div class="svg_Generator">
              <VueBarcode id="svg_element" background="none" fontSize="14" v-bind:value="processorId" width="1">Sorry ,
                some thing error
              </VueBarcode>
            </div>
            <div class="btn-group_Generate-2">
              <button class="btn btn-secondary" @click.prevent="copyText">Copy Processor Id</button>
              <button id="DownloadsAsImage" class="btn btn-primary" @click.prevent="downloadsAsImage(processorId)">
                Downloads as image
              </button>
            </div>
          </div>
          <canvas id="canvas" hidden></canvas>
          <div hidden>
            <label for="ProcessorIDInput" hidden></label>
            <input id="ProcessorIDInput" disabled hidden v-bind:value="processorId"/>
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
import VueBarcode from 'vue-barcode';

export default {
  name: "AdminDashBoardPanelPage",
  components: {HeaderMain, AsideMain, NavMain, VueBarcode,},
  data() {
    return {
      processor_Id: null,
      options: {
        flat: false,
      }
    }
  },
  methods: {
    async clearGenerateId() {
      if (this.$store.getters.processorId) {
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
      await this.$store.dispatch('processorId', null);
    },
    async generateId() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const processorId = await axios.post('/api/v1/processor/create');
      await this.$store.dispatch('processorId', processorId.data.processorId);
      this.$swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Create Processor ID',
        toast: false,
        text: processorId.data.processorId,
        showConfirmButton: false,
        timer: 1500
      })
    },
    copyText() {
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
    downloadsAsImage(processorId) {
      let svg = document.querySelector('svg');
      document.querySelector('canvas');

      function triggerDownload(imgURI) {
        let evt = new MouseEvent('click', {
          view: window,
          bubbles: false,
          cancelable: true
        });
        let a = document.createElement('a');
        let nameImage = "ProcessorID_" + processorId;
        a.setAttribute('download', nameImage + '.png');
        a.setAttribute('href', imgURI);
        a.setAttribute('target', '_blank');
        a.dispatchEvent(evt);
      }

      let canvas = document.getElementById('canvas');
      let ctx = canvas.getContext('2d');
      let data = (new XMLSerializer()).serializeToString(svg);
      let DOMURL = window.URL || window.webkitURL || window;
      let img = new Image();
      let svgBlob = new Blob([data], {type: 'image/svg+xml;charset=utf-8'});
      let url = DOMURL.createObjectURL(svgBlob);
      img.onload = function () {
        ctx.drawImage(img, 0, 0);
        DOMURL.revokeObjectURL(url);

        let imgURI = canvas
            .toDataURL('image/png')
            .replace('image/png', 'image/octet-stream');
        triggerDownload(imgURI);
      };
      img.src = url;
    },
  },
  beforeMount() {
    this.processor_Id = this.$store.getters.processorId;
  },
  computed: {
    ...mapGetters(['processorId'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminDashBoardPanelPageStyle.css";
</style>
