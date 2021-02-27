<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <div class="container_Admin_CreateDevice">
        <h2>Create Processor</h2>
        <div class="btn-group_Generate">
          <button class="btn btn-secondary" @click.prevent="generateId">Generate Id Processor</button>
          <button class="btn btn-danger" @click.prevent="clearGenerateId">Clear</button>
        </div>
        <div class="container__DeviceId">
          <div v-if="processorId" class="container__processor_Id">
            <div class="svg_Generator">
              <div>
                <qrcode-vue id="svg_element" :margin="2" :quality="1" :scale="7" v-bind:value="processorId">Sorry , some
                  thing error
                </qrcode-vue>

                <div v-if="processorId">
                  <label for="ProcessorIDInput" hidden></label>
                  <input id="ProcessorIDInput" class="InputForCopy" v-bind:value="processorId"/>
                </div>

              </div>
            </div>
            <div class="btn-group_Generate-2">
              <button class="btn btn-secondary" @click.prevent="copyText">Copy Processor Id</button>
              <button id="DownloadsAsImage" class="btn btn-primary" @click.prevent="downloadsAsImage(processorId)">
                Downloads as image
              </button>
            </div>
          </div>
          <canvas id="canvas" hidden></canvas>
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
  name: "AdminCreateProcessorPage",
  components: {
    HeaderMain, AsideMain, NavMain,
    QrcodeVue,
  },
  data() {
    return {
      processor_Id: null,
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
    this.processor_Id = this.$store.getters.processorId;
  },
  computed: {
    ...mapGetters(['processorId'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminCreateProcessorPageStyle.css";

.InputForCopy {
  width: 100%;
  text-align: center;
  color: black;
  background: none;
  border: none;
  margin-top: 0.5rem
}
</style>
