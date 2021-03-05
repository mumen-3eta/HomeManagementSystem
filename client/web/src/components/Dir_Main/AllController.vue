<template>
  <div>
    <h2>All Controller : {{ $route.params.processor_id }}</h2>
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
            <!--            :to="{path:'/v2/main/device/create/controller/' + props.row.Processor_Id}-->
            <router-link
                :to="{path:'/v2/main/device/processor/' + $route.params.processor_id + '/controller/' + props.row.Controller_Id }">
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
export default {
  name: "AllController",
  data() {
    return {
      ProcessorID: this.$route.params.processor_id,
      ControllerID: null,
      NameControllerUser: null,
      typeController_id: null,
      locationController_id: null,
      isShowingCamera: false,
      isShowingWait: true,
      searchTerm: null,
      Status: false,
      columns: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Name',
          field: 'name',
          type: 'string',
        },
        {
          label: 'Controller ID',
          field: 'Controller_Id',
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
      rows: [
        {
          id: "1",
          name: "Controller #1",
          Controller_Id: "185w5158d6wa185w6418fe15fe",
          btn_Action: "",
        },
        {
          id: "2",
          name: "Controller #2",
          Controller_Id: "185185dw6d35181d86418fe15fe",
          btn_Action: "",
        },
        {
          id: "3",
          name: "Controller #3",
          Controller_Id: "185w51d418wdwa3w6418fe15fe",
          btn_Action: "",
        },
      ],
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
  },
}
</script>

<style scoped>

</style>
