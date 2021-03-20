<template>
  <div class="wrapper">
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
  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";
import QrcodeVue from 'vue-qrcode';

export default {
  name: "AdminCreateProcessorPage",
  components: {
    QrcodeVue,
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
      await axios.post('/api/v1/processor/create').then(async ({data: {newProcessor: response}}) => {
        await this.$store.dispatch('processorId', response[0].id.toString());
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Create Processor ID',
          toast: false,
          text: response[0].id.toString(),
          showConfirmButton: false,
          timer: 1500
        })
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Create Processor Faild',
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
      });
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
