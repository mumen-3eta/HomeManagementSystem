<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div
        :class="lang==='ar' ? 'container_Admin_CreateController direction-rtl' :'container_Admin_CreateController direction-ltr'">
      <h2>{{ $t('AdminDashboard.createController.title') }}</h2>
      <div class="btn-group_Generate">
        <button class="btn btn-secondary" @click.prevent="generateControllerId">
          {{ $t('AdminDashboard.createController.btn_Generate') }}
        </button>
      </div>

      <div class="project my-3 mx-auto p-2 position-relative">
        <vue-good-table
            :columns="this.lang === 'en' ? columns_EN : columns_AR"
            :pagination-options="{
                    enabled: true,
                    mode: 'pages',
                    perPage: 5,
                    position: 'bottom',
                    perPageDropdown: [3, 5, 7, 9],
                    dropdownAllowAll: false,
                    setCurrentPage: 1,
                    nextLabel:  this.lang==='en' ? 'next' : 'التالي',
                    prevLabel: this.lang==='en' ? 'prev' : 'السابق',
                    rowsPerPageLabel: this.lang==='en' ? 'Rows per page' : 'عدد الصفوف في الصفحة',
                    ofLabel: this.lang==='en' ? 'of' :'من' ,
                    pageLabel: this.lang==='en' ? 'page' :'صفحة', // for 'pages' mode
                    allLabel:  this.lang==='en' ?  'All' : 'الجميع',
                  }"
            :rows="rows"
            :search-options=" {
                    enabled: true,
                    skipDiacritics: true,
                    placeholder: 'Search this table'
                  }"
            :sort-options="{
                enabled: true,
                initialSortBy: [
                        {field: 'Create_at', type: 'desc'},
                        {field: 'id', type: 'desc'},
                    ]
              }"
            :theme="mode==='dark' ? 'black-rhino' : ''">
          <div slot="emptystate" style="text-align: center">
            {{ $t('Dashboard.NoDataForTable') }}
          </div>
          <div slot="table-actions" class="btn_searchScan"></div>
          <template slot="table-row" slot-scope="props">
            <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">
              <button class="btn_AddController"
                      @click.prevent="showQRCodeModal(props.row.controllerID)"><i
                  class="fas fa-edit"></i>
                {{ $t('AdminDashboard.createController.table.btn_QRCode') }}
              </button>
            </div>
            <span v-else>
                {{ props.formattedRow[props.column.field] }}
              </span>
          </template>
        </vue-good-table>
      </div>
    </div>

    <!--    modal Add type for Controller  -->
    <modal :resizable="false" :width="650" height="auto" name="AllControllers"
           @closed="CloseAllControllerModal"
           @before-open="CloseAllControllerModal">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseAllControllerModal"></i>
      <div :class="lang==='ar' ? 'container__AddProcessorID direction-rtl' :'container__AddProcessorID direction-ltr'"
           :style="mode ==='dark' ? 'background-color: #323232' :'background-color:#fff'">
        <h2 style="text-align: center">
          {{ $t('AdminDashboard.createController.model.title') }}
          <br>
          " {{ controllerId }} "
        </h2>
        <div class="input-group__AddProcessorID">
          <div class="container__DeviceId">
            <div v-if="controllerId" class="container__processor_Id">
              <div class="svg_Generator">
                <div>
                  <qrcode-vue id="svg_element" :margin="2" :quality="1" :scale="7" v-bind:value="controllerId">
                    {{ $t('AdminDashboard.createController.model.error') }}
                  </qrcode-vue>

                  <div v-if="controllerId">
                    <label for="ControllerIDInput" hidden></label>
                    <input id="ControllerIDInput" class="InputForCopy" v-bind:value="controllerId"/>
                  </div>

                </div>
              </div>
              <div class="btn-group_Generate-2">
                <button class="btn btn-secondary" @click.prevent="copyTextControllerId">
                  {{ $t('AdminDashboard.createController.model.btn_Copy') }}
                </button>
                <button id="DownloadsAsImage" class="btn btn-primary"
                        @click.prevent="downloadsControllerIdAsImage(controllerId)">
                  {{ $t('AdminDashboard.createController.model.btn_Download') }}
                </button>
              </div>
            </div>
            <canvas id="canvas" hidden></canvas>
          </div>
        </div>
      </div>
    </modal>
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
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      columns_EN: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Controller ID',
          field: 'controllerID',
          type: 'string',
        },
        {
          label: 'Date added',
          field: 'Create_at',
          type: 'string',
        },
        {
          label: 'Settings',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      columns_AR: [
        {
          label: 'رقم الترتيب',
          field: 'id',
          type: 'string',
        },
        {
          label: 'معرف وحدة التحكم',
          field: 'controllerID',
          type: 'string',
        },
        {
          label: 'تاريخ الاضافة',
          field: 'Create_at',
          type: 'string',
        },
        {
          label: 'الاعدادات',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.All_Processor ? this.$store.getters.All_Processor : [],
    }
  },
  methods: {
    /*** open Modal ***/
    OpenAllControllerModal() {
      this.$modal.show('AllControllers')
    },
    async showQRCodeModal(QRCode) {
      this.$modal.show('AllControllers');
      await this.$store.dispatch('controllerId', QRCode.toString());
    },
    /*** Close Modal***/
    CloseAllControllerModal() {
      this.$modal.hide('AllControllers')
    },

    async generateControllerId() {
      await axios.post('/api/v1/controller/create').then(async ({data: {newController: response}}) => {
        await this.$store.dispatch('controllerId', response[0].id.toString());
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: this.lang === 'en' ? 'Create Controller ID' : 'إنشاء معرّف وحدة التحكم',
          toast: false,
          text: response[0].id.toString(),
          showConfirmButton: false,
          timer: 1500
        })
        this.OpenAllControllerModal();
        await this.GetAllControllers();
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: this.lang === 'en' ? 'Failed Create Controller ID' : 'فشل إنشاء معرّف وحدة التحكم',
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
      });
    },
    copyTextControllerId() {
      let copyText = document.getElementById("ControllerIDInput");
      copyText.select();
      document.execCommand("copy");
      this.$swal.fire({
        position: 'center',
        icon: 'success',
        title: copyText.value,
        toast: true,
        text: this.lang === 'en' ? 'Copied the Controller ID' : 'نسخ معرّف وحدة التحكم',
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
    /*** Get All Processors  ***/
    async GetAllControllers() {
      await axios.get('/api/v1/controller/all').then(async ({data: {productionData: response}}) => {
        const All_Controller = response.map((item, i) => ({
          id: (++i).toString(),
          controllerID: item.id.toString(),
          Create_at: this.FormatDate(item.create_at),
          btn_Action: ''
        }))
        await this.$store.dispatch('All_Controller', All_Controller);
        this.rows = this.$store.getters.All_Controller ? this.$store.getters.All_Controller : [];
      }).catch(() => {
        this.rows = this.$store.getters.All_Controller ? this.$store.getters.All_Controller : [];
        console.log("get Faild")
      });
    },
    FormatDate(data) {
      if (data) {
        let splitDate1 = data.split('T');
        let splitDate2 = splitDate1[0].split('-')
        return `${splitDate2[0]}/${splitDate2[1]}/${splitDate2[2]}`;
      } else {
        return ' ';
      }
    },
  },
  computed: {
    ...mapGetters(['controllerId', 'All_Controller'])
  },
  mounted() {
    this.GetAllControllers();
  }
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
