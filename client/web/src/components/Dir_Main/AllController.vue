<template>
  <div>
    <h2>All Controller for Processor : {{ $route.params.connection_id }}</h2>
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
                    @click.prevent="DeleteControllerConnection(props.row.Controller_id ,props.row.Processor_Id)">
              <i class="fas fa-trash-alt"></i>
            </button>
            <div class="btn_Status">
              <div class="form-check form-switch">
                <label for="flexSwitchCheckChecked"></label>
                <input id="flexSwitchCheckChecked"
                       :checked="props.row.ActiveStatus === 'ON'  ? 'checked' : '' "
                       class="form-check-input"
                       type="checkbox"
                       @change.prevent="ChangeActivation(props.row.Controller_id)">
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

export default {
  name: "AllController",
  data() {
    return {
      ProcessorID: this.$route.params.connection_id ? this.$route.params.connection_id : '',
      ControllerID: '',
      NameControllerUser: '',
      typeController_id: '',
      locationController_id: '',
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
          field: 'Name',
          type: 'string',
        },
        {
          label: 'Controller ID',
          field: 'Controller_id',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Processor ID',
          field: 'Processor_Id',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Status',
          field: 'ActiveStatus',
          type: 'string',
          sortable: false,
        },
        {
          label: 'create At',
          field: 'create_at',
          type: 'string',
        },
        {
          label: 'Action',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.userAllControllerConnectedWithProcessor ? this.$store.getters.userAllControllerConnectedWithProcessor : []

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
          await this.GetAllControllerConnectedWithProcessor();
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
        await this.GetAllControllerConnectedWithProcessor();
      }).catch(async () => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Change Status, Faild',
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
        await this.GetAllControllerConnectedWithProcessor();
      });
    },

    /*** ---------------- Get All Controller Connected With Processor ---------------- ***/
    async GetAllControllerConnectedWithProcessor() {
      await axios.post('/api/v1/controller/connection/processor', {
        processorId: this.$route.params.connection_id,
      }).then(async ({data: {connectionData: response}}) => {
        const userAllControllerConnectedWithProcessor = response.map((item, i) => ({
          id: (++i).toString(),
          Controller_Connection_Id: item.id.toString(),
          Name: item.name.toString(),
          Status: item.status,
          ActiveStatus: item.status === false ? 'OFF' : 'ON',
          Processor_Id: item.processor_id.toString(),
          Controller_id: item.controller_id.toString(),
          TypeId: item.typeid.toString(),
          LocationId: item.location_id.toString(),
          create_at: this.FormatDate(item.create_at.toString()),
          btn_Action: ''
        }))
        await this.$store.dispatch('userAllControllerConnectedWithProcessor', userAllControllerConnectedWithProcessor);
        this.rows = this.$store.getters.userAllControllerConnectedWithProcessor ? this.$store.getters.userAllControllerConnectedWithProcessor : [];
      }).catch(async () => {
        console.log("faild get All controller connection")
        await this.$store.dispatch('userAllControllerConnectedWithProcessor', null);
        this.rows = this.$store.getters.userAllControllerConnectedWithProcessor ? this.$store.getters.userAllControllerConnectedWithProcessor : [];
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
    await this.GetAllControllerConnectedWithProcessor();
  }
}
</script>

<style lang="scss" scoped>
.form-switch .form-check-input {
  transform: rotate(-90deg) scale(1.2);

  width: 2.5em;
  /* margin-left: -2.5em; */
  border-radius: 2em;
}
</style>
