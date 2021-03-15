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
            <!--            :to="{path:'/v2/main/device/create/controller/' + props.row.connection_id}-->
            <router-link
                :to="{path:'/v2/main/device/processor/' + $route.params.connection_id + '/controller/' + props.row.Controller_Id }">
              <button class="btn_Show">
                <i class="fas fa-eye"></i>
              </button>
            </router-link>
            <button class="btn_Edit">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn_deleted">
              <i class="fas fa-trash-alt"></i>
            </button>
            <div class="btn_Status">
              <div class="form-check form-switch">
                <label for="flexSwitchCheckChecked"></label>
                <input id="flexSwitchCheckChecked" :checked="Status ? 'checked' : '' " class="form-check-input"
                       type="checkbox">
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
          label: 'Controller Connection ID',
          field: 'Controller_Connection_Id',
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
          label: 'create At',
          field: 'create_at',
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
    /*** ---------------- Get All Controller Connected With Processor ---------------- ***/
    async GetAllControllerConnectedWithProcessor() {
      await axios.get('/api/v1/controller/connection', {data: {processorId: this.$route.params.connection_id}}).then(async (response) => {
        const userAllControllerConnectedWithProcessor = response.data.connectionData.map((item, i) => ({
          id: (++i).toString(),
          Controller_Connection_Id: item.id.toString(),
          Name: item.name.toString(),
          Status: item.status,
          Processor_Id: item.processor_id.toString(),
          Controller_id: item.controller_id.toString(),
          TypeId: item.typeid.toString(),
          LocationId: item.location_id.toString(),
          create_at: item.create_at.toString(),
          btn_Action: ''
        }))
        await this.$store.dispatch('userAllControllerConnectedWithProcessor', userAllControllerConnectedWithProcessor);
        this.rows = this.$store.getters.userAllControllerConnectedWithProcessor ? this.$store.getters.userAllControllerConnectedWithProcessor : [];
      }).catch(() => {
        console.log("faild get All controller connection")
      });
    },
    /*** ---------------- End Get All Controller Connected With Processor ---------------- ***/
  },
  async beforeMount() {
    // await this.GetAllControllerConnectedWithProcessor();
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
