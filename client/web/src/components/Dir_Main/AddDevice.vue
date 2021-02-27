<template>
  <div>
    <!--   section number 2     -->
    <section class="section section__mTop">
      <header class="section__header">
        <h2 class="section__title">Processor</h2>
        <div class="section__control-s">
          <button class="section__button section__button--painted focus--box-shadow" type="button"
                  @click.prevent="OpenProcessorId">
            <i class="fas fa-plus"></i>
          </button>
        </div>
      </header>

      <div class="project my-3 mx-auto p-2 position-relative">
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
              <router-link :to="{path:'/v2/main/device/create/controller/' + props.row.Processor_Id}">
                <button class="btn_AddController"><i class="fas fa-plus"></i> Add Controller</button>
              </router-link>
              <button class="btn_deleted" @click.prevent="deleteProcessorID(props.row.Processor_Id)"><i
                  class="fas fa-trash-alt"></i> Delete
              </button>
            </div>
            <span v-else>
                {{ props.formattedRow[props.column.field] }}
              </span>
          </template>
        </vue-good-table>
      </div>
    </section><!-- End SECTION -->

    <!--    modal Add Processor id  -->
    <modal :resizable="false" :width="650" height="auto" name="AddNewProcessorId" @closed="CloseProcessorId"
           @before-open="CloseProcessorId">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseProcessorId"></i>
      <div class="container__AddProcessorID">
        <h2>Add New Processor ID</h2>
        <div class="input-group__AddProcessorID">
          <label for="input_ProcessorID">Processor ID</label>
          <input id="input_ProcessorID" v-model.trim="Processor_ID" type="text">
          <p v-if="errorProcessorID" class="error_style pb-2">{{ errorProcessorID }}</p>
          <div class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="addProcessorID">Add Processor Id</button>
            <button class="btn btn-danger" @click.prevent="ClearProcessorID">Clear</button>
          </div>
        </div>
        <div class="otherWay_AddProcessorID">
          <div class="otherWay_btnAdd">
            <h4>Other Way To Add Processor ID</h4>
            <button class="btn btn-secondary" @click.prevent="openScan">
              {{ !isShowingCamera ? 'Scan BarCode' : 'Exit Scanning' }}
            </button>
          </div>
          <div id="handle__camera" class="handle__camera ScanOpen">

            <qrcode-stream v-if="isShowingCamera" @decode="onDecode" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">Wait For Open Camera...</p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">place camera, Pleas Check your web Came</p>
          </div>
        </div>
      </div>
    </modal>
    <!--   End modal Add Processor id  -->

    <!--    modal Search Processor id  -->
    <modal :resizable="false" :width="650" height="auto" name="SearchProcessorId" @closed="AfterCloseScanSearch"
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
  name: "AddDevice",
  data() {
    return {
      Processor_ID: null,
      errorProcessorID: null,
      isShowingCamera: false,
      isShowingWait: true,
      searchTerm: null,
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
          label: 'Processor ID',
          field: 'Processor_Id',
          type: 'string',
          sortable: false,
        },
        // {
        //   label: 'Created On',
        //   field: 'createdAt',
        //   type: 'date',
        //   dateInputFormat: 'yyyy-MM-dd',
        //   dateOutputFormat: 'MMM do yy',
        // },
        {
          label: 'Action',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.userAllProcessor,
    }
  },
  methods: {
    OpenProcessorId() {
      this.$modal.show('AddNewProcessorId')
      this.ClearProcessorID();
    },
    CloseProcessorId() {
      this.$modal.hide('AddNewProcessorId')
      this.ClearProcessorID();
      this.isShowingCamera = false;
      this.isShowingWait = true
    },
    OpenScanSearch() {
      this.$modal.show('SearchProcessorId')
      this.isShowingCamera = !this.isShowingCamera;
      setTimeout(() => {
        this.isShowingWait = false;
      }, 550);
    },
    CloseScanSearch() {
      this.$modal.hide('SearchProcessorId')
      this.isShowingCamera = false;
      this.isShowingWait = true
      this.searchTerm = null;
    },
    AfterCloseScanSearch() {
      this.$modal.hide('SearchProcessorId')
      this.isShowingCamera = false;
      this.isShowingWait = true
    },
    async addProcessorID() {
      let ProcessorID = this.Processor_ID;
      if (ProcessorID) {
        let ProcessorIDRegex = /^[a-zA-Z0-9]+$/;
        let CheckProcessorID = ProcessorIDRegex.test(ProcessorID);//true or false
        if (!CheckProcessorID) {
          this.errorProcessorID = "Sorry! User Processor Id Faild, ignored space";
          setTimeout(() => {
            this.errorProcessorID = null;
          }, 3000);
          return false;
        }
        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const {data: {data: ProcessorIdData}} = await axios.post('/api/v1/user/processor', {
          ProcessorId: this.Processor_ID,
        });
        await this.$store.dispatch('processorId', ProcessorIdData);
        const {data: {data: processorData}} = await axios.get('/api/v1/user/processor');
        await this.$store.dispatch('userProcessorIds', processorData);
        const userAllProcessors = processorData.map((item, i) => ({
          id: i,
          name: `processorID #${i}`,
          Processor_Id: item,
          btn_Action: ''
        }))
        await this.$store.dispatch('userAllProcessor', userAllProcessors);
        this.rows = this.$store.getters.userAllProcessor;
        this.$modal.hide('AddNewProcessorId')
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Add Processor ID, Successfully',
          toast: false,
          text: ProcessorIdData.ProcessorId,
          showConfirmButton: false,
          timer: 1500
        })
      } else {
        this.errorProcessorID = "Sorry! User Processor Id Faild!";
        setTimeout(() => {
          this.errorProcessorID = null;
        }, 3000);
      }

    },
    async ClearProcessorID() {
      this.Processor_ID = null;
    },
    openScan() {
      const handle__camera = document.getElementById('handle__camera');
      handle__camera.style.transition = 'all ease 0.3s ';
      handle__camera.classList.toggle('ScanOpen');
      this.isShowingCamera = !this.isShowingCamera;
      setTimeout(() => {
        this.isShowingWait = false;
      }, 550);


    },
    onDecode(decodedString) {
      this.Processor_ID = decodedString
      // console.log(decodedString)
      // ...
    },
    onDecodeSearch(decodedString) {
      this.searchTerm = decodedString
      setTimeout(() => {
        this.$modal.hide('SearchProcessorId')
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
    async deleteProcessorID(processor_Id) {
      this.$swal.fire({
        title: 'Are you sure?',
        html: `You won't Delete this ProcessorId <strong>${processor_Id}</strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.DeleteFunction(processor_Id);
          console.log(processor_Id)
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Delete Processor Id, Successfully',
            toast: false,
            text: "ProcessorID \"" + processor_Id + "\"",
            showConfirmButton: false,
            timer: 1500
          })
        }
      })

    },
    async DeleteFunction(processor_Id) {
      console.log(processor_Id)
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      // await axios.delete('/api/v1/user/processor', {data: {ProcessorId: processor_Id.ProcessorId}});
      await axios.delete('/api/v1/user/processor', {data: {ProcessorId: processor_Id}});
      const {data: {data: processorData}} = await axios.get('/api/v1/user/processor');
      await this.$store.dispatch('userProcessorIds', processorData);
      const userAllProcessors = processorData.map((item, i) => ({
        id: i,
        name: `processorID #${i}`,
        Processor_Id: item,
        btn_Action: ''
      }))
      await this.$store.dispatch('userAllProcessor', userAllProcessors);
      this.rows = this.$store.getters.userAllProcessor;
    },

  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const {data: {data: processorData}} = await axios.get('/api/v1/user/processor');
    await this.$store.dispatch('userProcessorIds', processorData);
    this.isShowingCamera = false;
    const userAllProcessors = processorData.map((item, i) => ({
      id: i,
      name: `processorID #${i}`,
      Processor_Id: item,
      btn_Action: ''
    }))
    await this.$store.dispatch('userAllProcessor', userAllProcessors);
    this.rows = this.$store.getters.userAllProcessor;

  },
  computed: {
    ...mapGetters(['processorId', 'userProcessorIds'])
  },
}

</script>

<style scoped>
@import "../../assets/css/_AddDeviceStyle.css";
</style>
