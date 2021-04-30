<template>
  <div class="wrapper">

    <!--   ********** body content can change it here *****************-->
    <div
        :class="lang==='ar' ? 'container_Admin_AddTypeController direction-rtl' :'container_Admin_AddTypeController direction-ltr'">
      <section class="container_Admin_btnSectionGroup">
        <header class="section__header">
          <h2 class="section__title">{{ $t('AdminDashboard.addTypeController.title') }}</h2>
          <div class="section__control-s">
            <button class="section__button section__button--painted focus--box-shadow" type="button"
                    @click.prevent="OpenTypeControllerModal">
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
                }"
          >
            <div slot="emptystate" style="text-align: center">
              {{ $t('Dashboard.NoDataForTable') }}
            </div>
            <div slot="table-actions" class="btn_searchScan"></div>
            <template slot="table-row" slot-scope="props">
              <div v-if="props.column.field === 'btn_Action'"
                   :class="lang==='en' ? 'btn_actionGroup' :'btn_actionGroup direction-ltr'">
                <button class="btn_AddController"
                        @click.prevent="editTypeController(props.row.labelId,props.row.nameType)"><i
                    class="fas fa-edit"></i>
                  {{ $t('AdminDashboard.addTypeController.table.btn_Edit') }}
                </button>
                <button class="btn_deleted"
                        @click.prevent="deleteTypeController(props.row.labelId,props.row.nameType)"><i
                    class="fas fa-trash-alt"></i>
                  {{ $t('AdminDashboard.addTypeController.table.btn_Delete') }}
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
      <div :class="lang==='ar' ? 'container__AddProcessorID direction-rtl' :'container__AddProcessorID direction-ltr'">
        <h2>
          {{
            !edited
                ?
                (lang === 'en' ? 'Add New Type for Controller' : 'إضافة نوع جديد لوحدة التحكم')
                :
                (lang === 'en' ? `Edit type name: "${EditInfo.Name_Type}"` : `تحرير اسم النوع: "${EditInfo.Name_Type}"`)
          }}
        </h2>
        <div class="input-group__AddProcessorID">
          <label v-if="!edited" for="input_ProcessorIDAdd">
            {{ $t('AdminDashboard.addTypeController.model.nameType') }}
          </label>
          <input v-if="!edited" id="input_ProcessorIDAdd" v-model.trim="NameTypeController" required type="text"
                 @keypress.enter="addTypeController" @keypress.esc="ClearTypeController">
          <label v-if="edited" for="input_ProcessorIDUpdate">
            {{ $t('AdminDashboard.addTypeController.model.nameType') }}
          </label>
          <input v-if="edited" id="input_ProcessorIDUpdate" v-model.trim="NameTypeController" required type="text"
                 @keypress.enter="SaveChangeEdited" @keypress.esc="ClearTypeController">
          <p v-if="errorLabel" class="error_style pb-2">{{ errorLabel }}</p>
          <div v-if="!edited" class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="addTypeController">
              {{ $t('AdminDashboard.addTypeController.model.addTypeController') }}
            </button>
            <button class="btn btn-danger" @click.prevent="ClearTypeController">
              {{ $t('AdminDashboard.addTypeController.model.clear') }}
            </button>
          </div>
          <div v-if="edited" class="btn-group__AddProcessorID">
            <button class="btn btn-secondary" @click.prevent="SaveChangeEdited">
              {{ $t('AdminDashboard.addTypeController.model.saveChange') }}
            </button>
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

export default {
  name: "AdminAddTypeControllerPage",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      socket: '',
      NameTypeController: '',
      errorLabel: '',
      edited: false,
      EditInfo: {
        Type_id: '',
        Name_Type: '',
      },
      columns_EN: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'Controller type name',
          field: 'nameType',
          type: 'string',
        },
        {
          label: 'Controller type Id',
          field: 'labelId',
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
          label: 'اسم نوع وحدة التحكم',
          field: 'nameType',
          type: 'string',
        },
        {
          label: 'معرف نوع وحدة التحكم',
          field: 'labelId',
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
      rows: this.$store.getters.allTypeController ? this.$store.getters.allTypeController : [],
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
        await axios.post('/api/v1/controller/type', {
          label: NameTypeController,
        }).then(async ({data: {data: response}}) => {
          await axios.get('/api/v1/controller/type').then(async ({data: {typeLabels: response}}) => {
            const All_Type_Controller = response.map((item, i) => ({
              id: (++i).toString(),
              nameType: item.label,
              labelId: item.id,
              btn_Action: ''
            }))
            await this.$store.dispatch('allTypeController', All_Type_Controller);
            this.rows = this.$store.getters.allTypeController;

          }).catch(() => {
            console.log("get Faild")
          });

          this.$modal.hide('AddNewTypeController')
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: this.lang === 'en' ? `Add ${response[0].label}, Successfully` : `أضيف ${response[0].label}, بنجاح`,
            toast: false,
            text: this.lang === 'en' ? `ID this Type : " ${response[0].id} "` : `معرف نوع وحدة التحكم : " ${response[0].id} "`,
            showConfirmButton: false,
            timer: 1500
          })
        }).catch(() => {
          this.$modal.hide('AddNewTypeController')
          this.$swal.fire({
            position: 'center',
            icon: 'error',
            title: this.lang === 'en' ? `<strong>Failed, Add New Type</strong>` : `<strong>فشل إضافة نوع جديد</strong>`,
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        });
      } else {
        if (this.lang === 'en') {
          this.errorLabel = "Excuse me ! Failed to add controller Type";
        } else {
          this.errorLabel = "عذرا ! فشل إضافة نوع  لوحدة تحكم";
        }
        setTimeout(() => {
          this.errorLabel = null;
        }, 3000);
      }

    },
    /*** Delete Type ***/
    async deleteTypeController(typeController_id, Name_Type) {
      this.$swal.fire({
        title: this.lang === 'en' ? 'Are you sure?' : 'هل أنت واثق؟',
        html: this.lang === 'en' ? `Do you want to delete this type? <br> <strong>"${Name_Type}"</strong> <br> <strong>'id : ' ${typeController_id}</strong>` : `  هل تريد أن تحذف هذا النوع <br> <strong> <strong>"${Name_Type}" <br> </strong> ' رقم المعرف ' ${typeController_id}  </strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        cancelButtonText: this.lang === 'en' ? 'Cancel' : 'إلغاء',
        confirmButtonText: this.lang === 'en' ? 'Yes, delete it!' : 'نعم , إحذف'
      }).then((result) => {
        if (result.isConfirmed) {
          this.DeleteTypeControllerFunction(typeController_id);
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: this.lang === 'en' ? 'This Controller Type has been successfully deleted' : 'تم حذف هذا النوع بنجاح',
            toast: false,
            text: this.lang === 'en' ? "TypeID \"" + typeController_id + "\"" : "معرف النوع \"" + typeController_id + "\"",
            showConfirmButton: false,
            timer: 1500
          })
        }
      })
    },
    async DeleteTypeControllerFunction(typeController_id) {
      await axios.delete('/api/v1/controller/type', {data: {typeId: typeController_id}}).then(async () => {
        await this.GetAllTypes();
      }).catch(() => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: this.lang === 'en' ? `<strong>Failed, Delete this Type</strong>` : `<strong>فشل حذف هذا النوع</strong>`,
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
      });
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
        await axios.put('/api/v1/controller/type', {
          typeId: this.EditInfo.Type_id,
          label: NameTypeController,
        }).then(async ({data: {data: response}}) => {
          await this.GetAllTypes();
          this.$modal.hide('AddNewTypeController')
          this.$swal.fire({
            position: 'center',
            icon: 'success',
            title: this.lang === 'en' ? `Updated ${response[0].label}, Successfully` : `تم تحديث ${response[0].label}, بنجاح`,
            toast: false,
            text: this.lang === 'en' ? `ID this Type : " ${response[0].id} "` : `معرف هذا النوع : " ${response[0].id} "`,
            showConfirmButton: false,
            timer: 1500
          })
        }).catch(() => {
          this.$modal.hide('AddNewTypeController')
          this.$swal.fire({
            position: 'center',
            icon: 'error',
            title: this.lang === 'en' ? `<strong>Failed Update this Type</strong>` : `<strong>فشل تحديث هذا النوع</strong>`,
            toast: false,
            text: this.lang === 'en' ? ' the type name is Already Exist 😥' : 'اسم النوع موجود بالفعل 😥',
            showConfirmButton: false,
            timer: 1500
          })
        });

      } else {
        if (this.lang === 'en') {
          this.errorLabel = "Excuse me ! Failed to update controller Type";
        } else {
          this.errorLabel = "عذرا ! فشل تحديث نوع وحدة التحكم";
        }
        setTimeout(() => {
          this.errorLabel = null;
        }, 3000);
      }

    },
    /*** Get All Type Controller  ***/
    async GetAllTypes() {
      await axios.get('/api/v1/controller/type').then(async ({data: {typeLabels: response}}) => {
        const All_Type_Controller = response.map((item, i) => ({
          id: (++i).toString(),
          nameType: item.label,
          labelId: item.id,
          btn_Action: ''
        }))
        await this.$store.dispatch('allTypeController', All_Type_Controller);
        this.rows = this.$store.getters.allTypeController ? this.$store.getters.allTypeController : [];
      }).catch(() => {
        console.log("get Faild")
        this.rows = this.$store.getters.allTypeController ? this.$store.getters.allTypeController : [];
      });
    }
  },
  created() {
    // this.socket = io('http://localhost:3001');
  },
  async beforeMount() {
    await this.GetAllTypes();
  },
  computed: {
    ...mapGetters(['allTypeController'])
  },
}
</script>

<style scoped>
@import "../../assets/css/_AdminAddTypeContorllerPageStyle.css";
</style>
