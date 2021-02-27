<template>
  <div class="wrapper">
    <!--    nav here -->
    <NavMain/>
    <main class="main">
      <!--      header hear -->
      <HeaderMain/>
      <!--   ********** body content can change it here *****************-->
      <div class="container_Admin_AddLocationController">
        <section class="container_Admin_btnSectionGroup">
          <header class="section__header">
            <h2 class="section__title">Add Location Controller</h2>
            <div class="section__control-s">
              <button class="section__button section__button--painted focus--box-shadow" type="button"
                      @click.prevent="OpenLocationControllerModal">
                <i class="fas fa-plus"></i>
              </button>
            </div>
          </header>
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
                  }">
              <div slot="table-actions" class="btn_searchScan"></div>
              <template slot="table-row" slot-scope="props">
                <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">
                  <button class="btn_AddController"
                          @click.prevent="editLocationController(props.row.locationId,props.row.nameLocation)"><i
                      class="fas fa-edit"></i> Edit
                  </button>
                  <button class="btn_deleted"
                          @click.prevent="deleteTypeController(props.row.locationId,props.row.nameLocation)"><i
                      class="fas fa-trash-alt"></i> Delete
                  </button>
                </div>
                <span v-else>
                {{ props.formattedRow[props.column.field] }}
              </span>
              </template>
            </vue-good-table>
          </div>
        </section>
      </div>
      <!--    modal Add type for Controller  -->
      <modal :resizable="false" :width="650" height="auto" name="AddNewLocationController"
             @closed="CloseLocationControllerModal"
             @before-open="CloseLocationControllerModal">
        <i class="fas-closeBTN fas fa-times" @click.prevent="CloseLocationControllerModal"></i>
        <div class="container__AddProcessorID">
          <h2>{{ !edited ? "Add New Location for Controller" : `Edit Location name: "${EditInfo.Name_Location}"` }}</h2>
          <div class="input-group__AddProcessorID">
            <label for="input_ProcessorID">Location Name</label>
            <input id="input_ProcessorID" v-model.trim="NameLocationController" required type="text">
            <p v-if="errorNameLocationController" class="error_style pb-2">{{ errorNameLocationController }}</p>
            <div v-if="!edited" class="btn-group__AddProcessorID">
              <button class="btn btn-secondary" @click.prevent="addLocationController">Add Location Controller</button>
              <button class="btn btn-danger" @click.prevent="ClearLocationController">Clear</button>
            </div>
            <div v-if="edited" class="btn-group__AddProcessorID">
              <button class="btn btn-secondary" @click.prevent="SaveChangeLocation">Save Change</button>
            </div>
          </div>
        </div>
      </modal>
      <!--   End modal  Add type for Controller   -->
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

export default {
  name: "AdminAddLocationControllerPage",
  components: {
    HeaderMain, AsideMain, NavMain,
  },
  data() {
    return {
      NameLocationController: null,
      errorNameLocationController: null,
      edited: false,
      EditInfo: {
        Location_id: null,
        Name_Location: null,
      },
      columns: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Name Location',
          field: 'nameLocation',
          type: 'string',
        },
        {
          label: 'Location Id',
          field: 'locationId',
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
      rows: this.$store.getters.allLocationController,
    }
  },
  methods: {
    /*** open Modal ***/
    OpenLocationControllerModal() {
      this.$modal.show('AddNewLocationController')
    },
    /*** Close Modal***/
    CloseLocationControllerModal() {
      this.$modal.hide('AddNewLocationController')
      this.errorNameLocationController = null
      this.NameLocationController = null;
      this.edited = false;
      this.EditInfo.Location_id = null;
      this.EditInfo.Name_Location = null;
    },
    /*** Clear BTN ***/
    ClearLocationController() {
      this.errorNameLocationController = null
      this.NameLocationController = null;
      this.EditInfo.Location_id = null;
      this.EditInfo.Name_Location = null;
    },
    /*** Add new location Controller ***/
    async addLocationController() {
      let NameLocationController = this.NameLocationController;
      if (NameLocationController) {
        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const {data: {data: controllerLocation}} = await axios.post('/api/v1/controller/location', {
          label: this.NameLocationController,
        });
        await this.$store.dispatch('NewLocationController', controllerLocation);

        const {data: {data: allLocationController}} = await axios.get('/api/v1/controller/location');
        const All_Location_Controller = allLocationController.map((item, i) => ({
          id: i,
          nameLocation: item.label,
          locationId: item.locationId,
          btn_Action: ''
        }))
        await this.$store.dispatch('allLocationController', All_Location_Controller);

        this.rows = this.$store.getters.allLocationController;
        this.CloseLocationControllerModal();
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: `Add ${controllerLocation.label}, Successfully`,
          toast: false,
          text: `ID this Location : " ${controllerLocation._id} "`,
          showConfirmButton: false,
          timer: 1500
        })
      } else {
        this.errorNameLocationController = "Sorry! Add controller Location Faild!";
        setTimeout(() => {
          this.errorNameLocationController = null;
        }, 3000);
      }

    },
    /*** Edit name location Controller ***/
    editLocationController(locationController_id, Name_Location) {
      this.$modal.show('AddNewLocationController')
      this.edited = true;
      this.NameLocationController = Name_Location;
      this.EditInfo.Location_id = locationController_id;
      this.EditInfo.Name_Location = Name_Location;

    },
    async SaveChangeLocation() {
      let NameLocationController = this.NameLocationController;
      if (NameLocationController) {
        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const {data: {data: controllerLocation}} = await axios.put('/api/v1/controller/location', {
          locationId: this.EditInfo.Location_id,
          label: this.NameLocationController,
        });
        await this.$store.dispatch('NewLocationController', controllerLocation);

        const {data: {data: allLocationController}} = await axios.get('/api/v1/controller/location');
        const All_Location_Controller = allLocationController.map((item, i) => ({
          id: i,
          nameLocation: item.label,
          locationId: item.locationId,
          btn_Action: ''
        }))
        await this.$store.dispatch('allLocationController', All_Location_Controller);

        this.rows = this.$store.getters.allLocationController;
        this.CloseLocationControllerModal();
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: `Add ${controllerLocation.label}, Successfully`,
          toast: false,
          text: `ID this Location : " ${controllerLocation._id} "`,
          showConfirmButton: false,
          timer: 1500
        })
      } else {
        this.errorNameLocationController = "Sorry! Add controller Location Faild!";
        setTimeout(() => {
          this.errorNameLocationController = null;
        }, 3000);
      }

    },

    /*** Delete name and id location Controller ***/
    async deleteTypeController(locationController_id, Name_Location) {
      this.$swal.fire({
        title: 'Are you sure?',
        html: `You won't Delete this Location <strong>"${Name_Location}"</strong> <br> <strong>${locationController_id}</strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.DeleteTypeControllerFunction(locationController_id);
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Delete Location Controller, Successfully',
            toast: false,
            text: "LocationID \"" + locationController_id + "\"",
            showConfirmButton: false,
            timer: 1500
          })
        }
      })
    },
    async DeleteTypeControllerFunction(locationController_id) {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      await axios.delete('/api/v1/controller/location', {data: {locationId: locationController_id}});
      const {data: {data: allLocationController}} = await axios.get('/api/v1/controller/location');
      const All_Location_Controller = allLocationController.map((item, i) => ({
        id: i,
        nameLocation: item.label,
        locationId: item.locationId,
        btn_Action: ''
      }))
      await this.$store.dispatch('allLocationController', All_Location_Controller);

      this.rows = this.$store.getters.allLocationController;
    },
  },
  computed: {
    ...mapGetters(['NewLocationController', 'allLocationController'])
  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const {data: {data: allLocationController}} = await axios.get('/api/v1/controller/location');
    const All_Location_Controller = allLocationController.map((item, i) => ({
      id: i,
      nameLocation: item.label,
      locationId: item.locationId,
      btn_Action: ''
    }))
    await this.$store.dispatch('allLocationController', All_Location_Controller);
    this.rows = this.$store.getters.allLocationController;
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminAddLocationContorllerPageStyle.css";
</style>
