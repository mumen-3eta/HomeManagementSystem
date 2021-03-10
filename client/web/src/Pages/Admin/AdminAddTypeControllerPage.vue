<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div class="container_Admin_AddTypeController">
      <section class="container_Admin_btnSectionGroup">
        <header class="section__header">
          <h2 class="section__title">Add Type Controller</h2>
          <div class="section__control-s">
            <button class="section__button section__button--painted focus--box-shadow" type="button"
                    @click.prevent="OpenTypeControllerModal">
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
                        @click.prevent="editTypeController(props.row.labelId,props.row.nameType)"><i
                    class="fas fa-edit"></i> Edit
                </button>
                <button class="btn_deleted"
                        @click.prevent="deleteTypeController(props.row.labelId,props.row.nameType)"><i
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
    <modal :resizable="false" :width="650" height="auto" name="AddNewTypeController"
           @closed="CloseTypeControllerModal"
           @before-open="CloseTypeControllerModal">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseTypeControllerModal"></i>
      <div class="container__AddProcessorID">
        <h2>{{ !edited ? "Add New Type for Controller" : `Edit type name: "${EditInfo.Name_Type}"` }}</h2>
        <div class="input-group__AddProcessorID">
          <label for="input_ProcessorID">Type Name</label>
          <input id="input_ProcessorID" v-model.trim="NameTypeController" required type="text">
          <p v-if="errorLabel" class="error_style pb-2">{{ errorLabel }}</p>
          <div v-if="!edited" class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="addTypeController">Add Type Controller</button>
            <button class="btn btn-danger" @click.prevent="ClearTypeController">Clear</button>
          </div>
          <div v-if="edited" class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="SaveChangeEdited">Save Change</button>
          </div>
        </div>
      </div>
    </modal>
    <!--   End modal  Add type for Controller   -->

  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";
import io from "socket.io-client";

export default {
  name: "AdminAddTypeControllerPage",
  data() {
    return {
      socket: '',
      NameTypeController: '',
      errorLabel: '',
      edited: false,
      EditInfo: {
        Type_id: '',
        Name_Type: '',
      },
      columns: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Name Type',
          field: 'nameType',
          type: 'string',
        },
        {
          label: 'Type Id',
          field: 'labelId',
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
      rows: this.$store.getters.allTypeController,
    }
  },
  methods: {
    //open modal
    OpenTypeControllerModal() {
      this.$modal.show('AddNewTypeController')
    },
    //close modal
    CloseTypeControllerModal() {
      this.$modal.hide('AddNewTypeController')
      this.errorLabel = null
      this.NameTypeController = null;
      this.edited = false;
      this.EditInfo.Type_id = null;
      this.EditInfo.Name_Type = null;
    },
    //End open modal
    /*** Add Type ***/
    async addTypeController() {
      let NameTypeController = this.NameTypeController;
      if (NameTypeController) {
        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const {data: {data: controllerType}} = await axios.post('/api/v1/controller/type', {
          label: this.NameTypeController,
        });
        await this.$store.dispatch('NewTypeController', controllerType);

        const {data: {data: allTypeController}} = await axios.get('/api/v1/controller/type');
        const All_Type_Controller = allTypeController.map((item, i) => ({
          id: i,
          nameType: item.label,
          labelId: item.labelId,
          btn_Action: ''
        }))
        this.socket.emit("all_Type_Controller", All_Type_Controller);
        this.socket.on("all_Type_Controller_server", (data) => {
          this.$store.dispatch('allTypeController', data);
          this.rows = this.$store.getters.allTypeController;
        });
        // await this.$store.dispatch('allTypeController', All_Type_Controller);

        // this.rows = this.$store.getters.allTypeController;
        this.$modal.hide('AddNewTypeController')
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: `Add ${controllerType.label}, Successfully`,
          toast: false,
          text: `ID this Type : " ${controllerType._id} "`,
          showConfirmButton: false,
          timer: 1500
        })
      } else {
        this.errorLabel = "Sorry! Add controller Type Faild!";
        setTimeout(() => {
          this.errorLabel = null;
        }, 3000);
      }

    },
    /*** Delete Type ***/
    async deleteTypeController(typeController_id, Name_Type) {
      this.$swal.fire({
        title: 'Are you sure?',
        html: `You won't Delete this Type <strong>"${Name_Type}"</strong> <br> <strong>${typeController_id}</strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.DeleteTypeControllerFunction(typeController_id);
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Delete Type Controller, Successfully',
            toast: false,
            text: "TypeID \"" + typeController_id + "\"",
            showConfirmButton: false,
            timer: 1500
          })
        }
      })
    },
    async DeleteTypeControllerFunction(typeController_id) {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      await axios.delete('/api/v1/controller/type', {data: {labelId: typeController_id}});
      const {data: {data: allTypeController}} = await axios.get('/api/v1/controller/type');
      const All_Type_Controller = allTypeController.map((item, i) => ({
        id: i,
        nameType: item.label,
        labelId: item.labelId,
        btn_Action: ''
      }))
      this.socket.emit("all_Type_Controller", All_Type_Controller);
      this.socket.on("all_Type_Controller_server", (data) => {
        this.$store.dispatch('allTypeController', data);
        this.rows = this.$store.getters.allTypeController;
      });
      // await this.$store.dispatch('allTypeController', All_Type_Controller);
      // this.rows = this.$store.getters.allTypeController;
    },
    /*** btn Clear Type ***/
    ClearTypeController() {
      this.label = null;
      this.errorLabel = null
    },
    /*** Edit Type ***/
    editTypeController(typeController_id, Name_Type) {
      this.$modal.show('AddNewTypeController')
      this.edited = true;
      this.NameTypeController = Name_Type;
      this.EditInfo.Type_id = typeController_id;
      this.EditInfo.Name_Type = Name_Type;

    },
    async SaveChangeEdited() {
      let NameTypeController = this.NameTypeController;
      if (NameTypeController) {
        axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
        const {data: {data: controllerType}} = await axios.put('/api/v1/controller/type', {
          labelId: this.EditInfo.Type_id,
          label: this.NameTypeController,
        });
        await this.$store.dispatch('NewTypeController', controllerType);

        const {data: {data: allTypeController}} = await axios.get('/api/v1/controller/type');
        const All_Type_Controller = allTypeController.map((item, i) => ({
          id: i,
          nameType: item.label,
          labelId: item.labelId,
          btn_Action: ''
        }))
        this.socket.emit("all_Type_Controller", All_Type_Controller);
        this.socket.on("all_Type_Controller_server", (data) => {
          this.$store.dispatch('allTypeController', data);
          this.rows = this.$store.getters.allTypeController;
        });
        // await this.$store.dispatch('allTypeController', All_Type_Controller);
        //
        // this.rows = this.$store.getters.allTypeController;
        this.$modal.hide('AddNewTypeController')
        this.$swal.fire({
          position: 'center',
          icon: 'success',
          title: `Update type " ${controllerType.label} " , Successfully`,
          toast: false,
          text: `ID this Type : " ${controllerType._id} "`,
          showConfirmButton: false,
          timer: 1500
        })
      } else {
        this.errorLabel = "Sorry! update controller Type Faild!";
        setTimeout(() => {
          this.errorLabel = null;
        }, 3000);
      }

    },

  },
  created() {
    this.socket = io('http://localhost:3001');
  },
  async beforeMount() {
    axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
    const {data: {data: allTypeController}} = await axios.get('/api/v1/controller/type');
    const All_Type_Controller = allTypeController.map((item, i) => ({
      id: i,
      nameType: item.label,
      labelId: item.labelId,
      btn_Action: ''
    }))
    this.socket.emit("all_Type_Controller", All_Type_Controller);
    this.socket.on("all_Type_Controller_server", (data) => {
      this.$store.dispatch('allTypeController', data);
      this.rows = this.$store.getters.allTypeController;
    });
    // await this.$store.dispatch('allTypeController', All_Type_Controller);
    // this.rows = this.$store.getters.allTypeController;
  },
  computed: {
    ...mapGetters(['NewTypeController', 'allTypeController'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminAddTypeContorllerPageStyle.css";
</style>
