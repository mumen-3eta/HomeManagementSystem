<template>
  <div class="wrapper">
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
                some thing error
              </qrcode-vue>
              <div v-if="controllerId">
                <label for="ControllerIDInput" hidden></label>
                <input id="ControllerIDInput" class="InputForCopy" v-bind:value="controllerId"/>
              </div>
            </div>
          </div>
          <div class="btn-group_Generate-2">
            <button class="btn btn-secondary" @click.prevent="copyTextControllerId">Copy Controller Id</button>
            <button id="DownloadsAsImage" class="btn btn-primary"
                    @click.prevent="downloadsControllerIdAsImage(controllerId)">Downloads as image
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
  name: "AdminCreateControllerPage",
  components: {
    QrcodeVue
  },
  methods: {
    async generateControllerId() {
      await axios.post('/api/v1/controller/create').then(async (response) => {
        await this.$store.dispatch('controllerId', response.data.newController[0].id.toString());
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Create Controller ID',
          toast: false,
          text: response.data.newController[0].id.toString(),
          showConfirmButton: false,
          timer: 1500
        })
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Create Controller Faild',
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
      });
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
      let copyText = document.getElementById("ControllerIDInput");
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
    downloadsControllerIdAsImage(controllerId) {
      const image = document.getElementById('svg_element').src;
      let imageSplit_1 = image.split(';')[0];
      let imageExtension = imageSplit_1.split('/')[1];
      let a = document.createElement('a');
      a.href = image;
      a.download = `ControllerID_${controllerId}.${imageExtension}`;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    },
  },
  computed: {
    ...mapGetters(['controllerId'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminCreateContorllerPageStyle.css";

.InputForCopy {
  width: 100%;
  text-align: center;
  color: black;
  background: none;
  border: none;
  margin-top: 0.5rem
}
</style>
