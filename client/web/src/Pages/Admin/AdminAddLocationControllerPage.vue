<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div
        :class="lang ==='ar' ? 'container_Admin_AddLocationController direction-rtl' :'container_Admin_AddLocationController direction-ltr'">
      <section class="container_Admin_btnSectionGroup">
        <header class="section__header">
          <h2 class="section__title">{{ $t('AdminDashboard.addLocationController.title') }}</h2>
          <div class="section__control-s">
            <button class="section__button section__button--painted focus--box-shadow" type="button"
                    @click.prevent="OpenLocationControllerModal">
              <i class="fas fa-plus"></i>
            </button>
          </div>
        </header>
        <div class="project my-3 mx-auto p-2 position-relative">
          <vue-good-table
              :columns="this.lang === 'en' ? columns_EN : columns_AR"
              :pagination-options="{
                    enabled: true,
                    mode: 'pages',
                    perPage: 5,
                    position: 'bottom',
                    perPageDropdown: [3, 5, 7, 9,15,25,50],
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
              :theme="mode==='dark' ? 'black-rhino' : ''">
            <div slot="emptystate" style="text-align: center">
              {{ $t('Dashboard.NoDataForTable') }}
            </div>
            <div slot="table-actions" class="btn_searchScan"></div>
            <template slot="table-row" slot-scope="props">
              <div v-if="props.column.field === 'btn_Action'"
                   :class="lang==='en' ? 'btn_actionGroup' :'btn_actionGroup direction-ltr'">
                <button class="btn_AddController"
                        @click.prevent="editLocationController(props.row.locationId,props.row.nameLocation)"><i
                    class="fas fa-edit"></i> {{ $t('AdminDashboard.addLocationController.table.btn_Edit') }}
                </button>
                <button class="btn_deleted"
                        @click.prevent="deleteTypeController(props.row.locationId,props.row.nameLocation)"><i
                    class="fas fa-trash-alt"></i> {{ $t('AdminDashboard.addLocationController.table.btn_Delete') }}
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
      <div :class="lang==='ar' ? 'container__AddProcessorID direction-rtl' :'container__AddProcessorID direction-ltr'">
        <h2>
          {{
            !edited
                ?
                (lang === 'en' ? 'Add New Location for Controller' : 'إضافة موقع جديد لوحدة التحكم')
                :
                (lang === 'en' ? `Edit Location name: "${EditInfo.Name_Location}"` : `تحرير اسم الموقع: "${EditInfo.Name_Location}"`)
          }}
        </h2>
        <div class="input-group__AddProcessorID">
          <label v-if="!edited" for="input_ProcessorID_Add">
            {{ $t('AdminDashboard.addLocationController.model.nameType') }}
          </label>
          <input v-if="!edited" id="input_ProcessorID_Add" v-model.trim="NameLocationController"
                 required type="text" @keypress.enter="addLocationController"
                 @keypress.esc="CloseLocationControllerModal">
          <label v-if="edited" for="input_ProcessorID_Edit">
            {{ $t('AdminDashboard.addLocationController.model.nameType') }}
          </label>
          <input v-if="edited" id="input_ProcessorID_Edit" v-model.trim="NameLocationController"
                 required type="text" @keypress.enter="SaveChangeLocation" @keypress.esc="CloseLocationControllerModal">
          <p v-if="errorNameLocationController" class="error_style pb-2">{{ errorNameLocationController }}</p>
          <div v-if="!edited" class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="addLocationController">
              {{ $t('AdminDashboard.addLocationController.model.addTypeController') }}
            </button>
            <button class="btn btn-danger" @click.prevent="ClearLocationController">
              {{ $t('AdminDashboard.addLocationController.model.clear') }}
            </button>
          </div>
          <div v-if="edited" class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="SaveChangeLocation">
              {{ $t('AdminDashboard.addLocationController.model.saveChange') }}
            </button>
          </div>
        </div>
      </div>
    </modal>

  </div>
</template>

<script>
import axios from "axios";
import {mapGetters} from "vuex";

export default {
  name: "AdminAddLocationControllerPage",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      socket: '',
      NameLocationController: '',
      errorNameLocationController: '',
      edited: false,
      EditInfo: {
        Location_id: '',
        Name_Location: '',
      },
      columns_EN: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Controller Location name',
          field: 'nameLocation',
          type: 'string',
        },
        {
          label: 'Controller Location Id',
          field: 'locationId',
          type: 'string',
          sortable: false,
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
          label: 'اسم موقع وحدة التحكم',
          field: 'nameLocation',
          type: 'string',
        },
        {
          label: 'معرف موقع وحدة التحكم',
          field: 'locationId',
          type: 'string',
          sortable: false,
        },
        {
          label: 'الاعدادات',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [],
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
        await axios.post('/api/v1/controller/location', {
          label: NameLocationController,
        }).then(async ({data: {data: response}}) => {
          await axios.get('/api/v1/controller/location').then(async ({data: {locationLabels: response}}) => {
            const All_Location_Controller = response.map((item, i) => ({
              id: (++i).toString(),
              nameLocation: item.label,
              locationId: item.id,
              btn_Action: ''
            }))
            await this.$store.dispatch('allLocationController', All_Location_Controller);
            this.rows = this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [];
          }).catch(() => {
            console.log("get Faild")
            this.rows = this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [];
          });
          this.$modal.hide('AddNewLocationController')
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: this.lang === 'en' ? `Add ${response[0].label}, Successfully` : `أضيف ${response[0].label}, بنجاح`,
            toast: false,
            text: this.lang === 'en' ? `ID this Location : " ${response[0].id} "` : `معرف موقع وحدة التحكم : " ${response[0].id} "`,
            showConfirmButton: false,
            timer: 1500
          })
        }).catch(() => {
          this.$modal.hide('AddNewLocationController')
          this.$swal.fire({
            position: 'center',
            icon: 'error',
            title: this.lang === 'en' ? `<strong>Failed, Add New Location</strong>` : `<strong>فشل إضافة موقع جديد</strong>`,
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        });
      } else {
        if (this.lang === 'en') {
          this.errorNameLocationController = "Excuse me ! Failed to add controller Location";
        } else {
          this.errorNameLocationController = "عذرا ! فشل إضافة موقع لوحدة تحكم";
        }
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
        await axios.put('/api/v1/controller/location', {
          locationId: this.EditInfo.Location_id,
          label: NameLocationController,
        }).then(async ({data: {data: response}}) => {
          await this.GetAllLocations();
          this.$modal.hide('AddNewLocationController')
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: this.lang === 'en' ? `Updated ${response[0].label}, Successfully` : `تم تحديث ${response[0].label}, بنجاح`,
            toast: false,
            text: this.lang === 'en' ? `ID this Location : " ${response[0].id} "` : `معرف هذا الموقع : " ${response[0].id} "`,
            showConfirmButton: false,
            timer: 1500
          })
        }).catch(() => {
          this.$modal.hide('AddNewLocationController')
          this.$swal.fire({
            position: 'center',
            icon: 'error',
            title: this.lang === 'en' ? `<strong>Failed Update this Location</strong>` : `<strong>فشل تحديث هذا الموقع</strong>`,
            toast: false,
            text: this.lang === 'en' ? ' the Location name is Already Exist 😥' : 'اسم الموقع موجود بالفعل 😥',
            showConfirmButton: false,
            timer: 1500
          })
        });
      } else {
        if (this.lang === 'en') {
          this.errorNameLocationController = "Excuse me ! Failed to update controller Location";
        } else {
          this.errorNameLocationController = "عذرا ! فشل تحديث موقع وحدة التحكم";
        }
        setTimeout(() => {
          this.errorNameLocationController = null;
        }, 3000);
      }

    },

    /*** Delete name and id location Controller ***/
    async deleteTypeController(locationController_id, Name_Location) {
      this.$swal.fire({
        title: this.lang === 'en' ? 'Are you sure?' : 'هل أنت واثق؟',
        html: this.lang === 'en' ? `Do you want to delete this Location? <br> <strong>"${Name_Location}"</strong> <br> <strong>'id : ' ${locationController_id}</strong>` : `  هل تريد أن تحذف هذا الموقع <br> <strong> <strong>"${Name_Location}" <br> </strong> ' رقم المعرف ' ${locationController_id}  </strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        cancelButtonText: this.lang === 'en' ? 'Cancel' : 'إلغاء',
        confirmButtonText: this.lang === 'en' ? 'Yes, delete it!' : 'نعم , إحذف'
      }).then((result) => {
        if (result.isConfirmed) {
          this.DeleteTypeControllerFunction(locationController_id);
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: this.lang === 'en' ? 'This Controller Location has been successfully deleted' : 'تم حذف هذا الموقع بنجاح',
            toast: false,
            text: this.lang === 'en' ? "LocationID \"" + locationController_id + "\"" : "معرف الموقع \"" + locationController_id + "\"",
            showConfirmButton: false,
            timer: 1500
          })
        }
      })
    },
    async DeleteTypeControllerFunction(locationController_id) {
      await axios.delete('/api/v1/controller/location', {data: {locationId: locationController_id}}).then(async () => {
        await this.GetAllLocations();
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: this.lang === 'en' ? `<strong>Failed, Delete this Location</strong>` : `<strong>فشل حذف هذا الموقع</strong>`,
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
      });

    },

    /* get All Controller Location */
    async GetAllLocations() {
      await axios.get('/api/v1/controller/location').then(async ({data: {locationLabels: response}}) => {
        const All_Location_Controller = response.map((item, i) => ({
          id: (++i).toString(),
          nameLocation: item.label,
          locationId: item.id,
          btn_Action: ''
        }))
        await this.$store.dispatch('allLocationController', All_Location_Controller);
        this.rows = this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [];
      }).catch(() => {
        console.log("get Faild")
        this.rows = this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [];
      });
    }
  },
  computed: {
    ...mapGetters(['allLocationController'])
  },
  async beforeMount() {
    await this.GetAllLocations();
  },
  created() {
    // this.socket = io('http://localhost:3001');
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminAddLocationContorllerPageStyle.css";
</style>
