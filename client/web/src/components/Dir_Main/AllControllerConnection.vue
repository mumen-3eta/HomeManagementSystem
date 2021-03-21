<template>
  <div>
    <h2>All Controller Connected</h2>
    <div class="SubMain__Section-AllController my-3 mx-auto p-2 position-relative">
      <div class="search_group">
        <i class="fa fa-search search_group-icon"></i>
        <label for="searchId"></label>
        <input id="searchId" v-model="searchTerm" class="search_group-input" type="search">
        <button class="btn btn-secondary mx-2 search_group-btn" @click.prevent="OpenScanSearch">Scan To Search
        </button>
      </div>
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
                    enabled: false,
                    skipDiacritics: true,
                    placeholder: 'Search this table',
                    externalQuery: searchTerm
                  }">
        <div slot="table-actions" class="btn_searchScan"></div>
        <template slot="table-row" slot-scope="props">
          <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">
            <!--            <router-link-->
            <!--                :to="{path:'/v2/main/device/processor/' + $route.params.connection_id + '/controller/' + props.row.Controller_Id }">-->
            <!--              <button class="btn_Show">-->
            <!--                <i class="fas fa-eye"></i>-->
            <!--              </button>-->
            <!--            </router-link>-->
            <button class="btn_deleted"
                    @click.prevent="DeleteControllerConnection(props.row.ControllerId ,props.row.ProcessorId)">
              <i class="fas fa-trash-alt"></i>
            </button>
            <div class="btn_Status">
              <div class="form-check form-switch">
                <label for="flexSwitchCheckChecked"></label>
                <input id="flexSwitchCheckChecked"
                       :checked="props.row.Status === 'ON' ? 'checked' : '' "
                       class="form-check-input"
                       type="checkbox"
                       @change.prevent="ChangeActivation(props.row.ControllerId)">
              </div>
            </div>
          </div>
          <span v-else>
                {{ props.formattedRow[props.column.field] }}
              </span>
        </template>
      </vue-good-table>
    </div>

    <!--    modal Search Processor id  -->
    <modal :resizable="false" :width="650" height="auto" name="ScanSearchControllerId" @closed="AfterCloseScanSearch"
           @before-open="AfterCloseScanSearch">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseScanSearch"></i>
      <div class="container__AddProcessorID">
        <h3>Scan To Search</h3>
        <div class="otherWay_AddProcessorID">
          <div class="handle__camera">
            <qrcode-stream v-if="isShowingCamera" @decode="onDecodeSearch" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">Wait For Open Camera...</p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">place camera, Pleas Check your web Came</p>
          </div>
        </div>
      </div>
    </modal>
    <!--   End modal Search Processor id  -->
  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "AllControllerConnection",
  data() {
    return {
      // ControllerID: '',
      // NameControllerUser: '',
      // typeController_id: '',
      // locationController_id: '',
      isShowingCamera: false,
      isShowingWait: true,
      searchTerm: '',
      Status: false,
      columns: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Name',
          field: 'ControllerName',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Controller ID',
          field: 'ControllerId',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Processor ID',
          field: 'ProcessorId',
          type: 'string',
          sortable: false,
        },
        {
          label: 'create At',
          field: 'CreateAt',
          type: 'string',
        },
        {
          label: 'Status',
          field: 'Status',
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
      rows: this.$store.getters.AllControllerConnectedForThisUser ? this.$store.getters.AllControllerConnectedForThisUser : [],

    }
  },
  methods: {
    /*** Open Scan Search ***/
    OpenScanSearch() {
      this.$modal.show('ScanSearchControllerId')
      this.isShowingCamera = !this.isShowingCamera;
      setTimeout(() => {
        this.isShowingWait = false;
      }, 550);
    },
    /*** After Close Scan Search ***/
    AfterCloseScanSearch() {
      this.$modal.hide('ScanSearchControllerId')
      this.isShowingCamera = false;
      this.isShowingWait = true
    },
    CloseScanSearch() {
      this.$modal.hide('ScanSearchControllerId')
      this.isShowingCamera = false;
      this.isShowingWait = true
      this.searchTerm = null;
    },

    /*** on Decode Search ***/
    onDecodeSearch(decodedString) {
      this.searchTerm = decodedString
      setTimeout(() => {
        this.$modal.hide('ScanSearchControllerId')
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

    /* Delete Controller Connection */
    async DeleteControllerConnection(controller_Id, processor_Id) {
      this.$swal.fire({
        title: 'Are you sure?',
        html: `You won't Delete this controller Id <strong>${controller_Id}</strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        confirmButtonText: 'Yes, delete it!'
      }).then(async (result) => {
        if (result.isConfirmed) {
          await axios.delete('/api/v1/controller/connection', {
            data: {
              controllerId: controller_Id,
              processorId: processor_Id
            }
          }).then(() => {
            this.$swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Delete this Connection, Successfully',
              toast: false,
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(async () => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: `<strong>Delete Processor ID, Faild</strong>`,
              toast: false,
              showConfirmButton: false,
              timer: 1500
            })
          });
          await this.GetAllControllerConnectedForThisUser();
        }
      })

    },

    /*** Change Activation ***/
    async ChangeActivation(id) {

      await axios.post('/api/v1/controller/change', {
        newStatus: '',
        controllerId: id
      }).then(async ({data: {controllerStatus: status}}) => {
        if (status[0].status) {
          this.$swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Active this Controller, Successfully',
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        } else {
          this.$swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Not Active this Controller, Successfully',
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        }
        await this.GetAllControllerConnectedForThisUser();
      }).catch(async () => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Change Status, Faild',
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
        await this.GetAllControllerConnectedForThisUser();
      });
    },

    /*** ---------------- Get All Controller Connected With Processor ---------------- ***/
    async GetAllControllerConnectedForThisUser() {
      await axios.get('/api/v1/controller/connection/all').then(async ({data: {connectionData: response}}) => {
        const AllControllerConnectedForThisUser = response.map((item) => ({
          id: (item.id).toString(),
          ControllerName: item.name.toString(),
          ControllerId: item.controller_id.toString(),
          ProcessorId: item.processor_id.toString(),
          CreateAt: this.FormatDate(item.create_at.toString()),
          Status: item.status === false ? 'OFF' : 'ON',
          ActiveStatus: item.status,
          btn_Action: ''
        }))
        await this.$store.dispatch('AllControllerConnectedForThisUser', AllControllerConnectedForThisUser);
        this.rows = this.$store.getters.AllControllerConnectedForThisUser ? this.$store.getters.AllControllerConnectedForThisUser : [];
      }).catch(async () => {
        console.log("Faild controller connection all")
        await this.$store.dispatch('AllControllerConnectedForThisUser', null);
        this.rows = this.$store.getters.AllControllerConnectedForThisUser ? this.$store.getters.AllControllerConnectedForThisUser : [];
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
    /*** ---------------- End Get All Controller Connected With Processor ---------------- ***/
  },
  async beforeMount() {
    await this.GetAllControllerConnectedForThisUser();
  },
  computed: {
    ...mapGetters(['AllControllerConnectedForThisUser'])
  },
}
</script>

<style scoped>

</style>
