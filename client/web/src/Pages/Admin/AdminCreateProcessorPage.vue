<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div class="container_Admin_CreateDevice">
      <h2>Create Processor</h2>
      <div class="btn-group_Generate">
        <button class="btn btn-secondary" @click.prevent="generateId">Generate Id Processor</button>
      </div>
      <div class="project my-3 mx-auto p-2 position-relative">
        <vue-good-table
            :columns="columns"
            :pagination-options="{
                    enabled: true,
                    mode: 'records',
                    perPage: 5,
                    position: 'bottom',
                    perPageDropdown: [5, 7, 9],
                    dropdownAllowAll: false,
                    setCurrentPage: 1,
                    nextLabel: 'next',
                    prevLabel: 'prev',
                    rowsPerPageLabel: 'Rows per page',
                    ofLabel: 'of',
                    pageLabel: 'page', // for 'pages' mode
                    allLabel: 'All',
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
              }">
          <div slot="table-actions" class="btn_searchScan"></div>
          <template slot="table-row" slot-scope="props">
            <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">
              <button class="btn_AddController"
                      @click.prevent="showQRCodeModal(props.row.processorID)"><i
                  class="fas fa-edit"></i> QRCode
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
    <modal :resizable="false" :width="650" height="auto" name="AllProcessors"
           @closed="CloseAllProcessorModal"
           @before-open="CloseAllProcessorModal">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseAllProcessorModal"></i>
      <div class="container__AddProcessorID">
        <h2>QRCode For Processors</h2>
        <div class="input-group__AddProcessorID">
          <div class="container__DeviceId">
            <div v-if="processorId" class="container__processor_Id">
              <div class="svg_Generator">
                <div>
                  <qrcode-vue id="svg_element" :margin="2" :quality="1" :scale="7" v-bind:value="processorId">Sorry ,
                    some
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
    </modal>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import QrcodeVue from 'vue-qrcode';
import axios from "axios";

export default {
  name: "AdminCreateProcessorPage",
  data() {
    return {
      columns: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'processor ID',
          field: 'processorID',
          type: 'string',
        },
        {
          label: 'Create At',
          field: 'Create_at',
          type: 'string',
        },
        {
          label: 'Action',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.All_Processor ? this.$store.getters.All_Processor : [],
    }
  },
  components: {
    QrcodeVue,
  },
  methods: {
    /*** open Modal ***/
    OpenAllProcessorModal() {
      this.$modal.show('AllProcessors')
    },
    async showQRCodeModal(QRCode) {
      this.$modal.show('AllProcessors');
      await this.$store.dispatch('processorId', QRCode.toString());
    },
    /*** Close Modal***/
    CloseAllProcessorModal() {
      this.$modal.hide('AllProcessors')
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
          timer: 500
        });
        this.OpenAllProcessorModal();
        await this.GetAllProcessors();
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
    /*** Get All Processors  ***/
    async GetAllProcessors() {
      await axios.get('/api/v1/processor/all').then(async ({data: {productionData: response}}) => {
        const All_Processor = response.map((item, i) => ({
          id: (++i).toString(),
          processorID: item.id.toString(),
          Create_at: this.FormatDate(item.create_at),
          btn_Action: ''
        }))
        await this.$store.dispatch('All_Processor', All_Processor);
        this.rows = this.$store.getters.All_Processor ? this.$store.getters.All_Processor : [];
      }).catch(() => {
        this.rows = this.$store.getters.All_Processor ? this.$store.getters.All_Processor : [];
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
    ...mapGetters(['processorId', 'All_Processor'])
  },
  mounted() {
    this.GetAllProcessors();
  }
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
