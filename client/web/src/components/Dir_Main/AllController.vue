<template>
  <div :class="lang==='ar' ? 'direction-rtl' :'direction-ltr'">
    <h2>{{ $t('Dashboard.allControllerForProcessor.title') }} {{ $route.params.connection_id }}</h2>
    <div class="SubMain__Section-AllController my-3 mx-auto p-2 position-relative">
      <div :class="lang==='ar' ? 'search_group direction-ltr' :'search_group '">
        <i class="fa fa-search search_group-icon"></i>
        <label for="searchId"></label>
        <input id="searchId" v-model="searchTerm" class="search_group-input" type="search">
        <button class="btn btn-secondary mx-2 search_group-btn" @click.prevent="OpenScanSearch">
          {{ $t('Dashboard.allControllerForProcessor.btn_Text') }}
        </button>
      </div>
      <vue-good-table
          :columns="this.lang === 'en' ? columns_EN : columns_AR"
          :pagination-options="{
                    enabled: true,
                    mode: 'pages',
                    perPage: 5,
                    position: 'bottom',
                    perPageDropdown: [5, 7, 9],
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
                    enabled: false,
                    skipDiacritics: true,
                    placeholder: 'Search this table',
                    externalQuery: searchTerm
                  }"
          :theme="mode==='dark' ? 'black-rhino' : ''">
        <div slot="emptystate" style="text-align: center">
          {{ $t('Dashboard.NoDataForTable') }}
        </div>
        <div slot="table-actions" class="btn_searchScan"></div>
        <template slot="table-row" slot-scope="props">
          <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">

            <button class="btn_deleted"
                    @click.prevent="DeleteControllerConnection(props.row.Controller_id ,props.row.Processor_Id)">
              <i class="fas fa-trash-alt"></i>
            </button>
            <div class="btn_Status">
              <div class="form-check form-switch">
                <label for="flexSwitchCheckChecked"></label>
                <input id="flexSwitchCheckChecked"
                       :checked="props.row.ActiveStatus === 'ON' ? 'checked' : '' || props.row.ActiveStatus === 'شغال' ? 'checked' : ''  "
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
    <modal :class=" lang==='ar' ? 'direction-ltr' :''" :resizable="false" :width="650" height="auto"
           name="ScanSearchControllerId" @closed="AfterCloseScanSearch"
           @before-open="AfterCloseScanSearch">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseScanSearch"></i>
      <div class="container__AddProcessorID">
        <h3>
          {{ $t('Dashboard.allControllerForProcessor.webCame.title') }}
        </h3>
        <div class="otherWay_AddProcessorID">
          <div class="handle__camera">
            <qrcode-stream v-if="isShowingCamera" @decode="onDecodeSearch" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">
                {{ $t('Dashboard.allControllerForProcessor.webCame.messageWait') }}
              </p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">
              {{ $t('Dashboard.allControllerForProcessor.webCame.error') }}
            </p>
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
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      ProcessorID: this.$route.params.connection_id ? this.$route.params.connection_id : '',
      ControllerID: '',
      NameControllerUser: '',
      typeController_id: '',
      locationController_id: '',
      isShowingCamera: false,
      isShowingWait: true,
      searchTerm: '',
      Status: false,
      columns_EN: [
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
          label: 'Date added',
          field: 'create_at',
          type: 'string',
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
          label: 'الاسم',
          field: 'Name',
          type: 'string',
        },
        {
          label: 'معرف وحدة التحكم',
          field: 'Controller_id',
          type: 'string',
          sortable: false,
        },
        {
          label: 'معرف المعالج',
          field: 'Processor_Id',
          type: 'string',
          sortable: false,
        },
        {
          label: 'الحالة',
          field: 'ActiveStatus',
          type: 'string',
          sortable: false,
        },
        {
          label: 'تاريخ الاضافة',
          field: 'create_at',
          type: 'string',
        },
        {
          label: 'الاعدادات',
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
        title: this.lang === 'en' ? 'Are you sure?' : 'هل أنت متأكد؟',
        html: this.lang === 'en' ? `Do you want to delete this controller ID? <strong>${controller_Id}</strong>` : `هل تريد أن تحذف معرف وحدة التحكم هذه <strong>${controller_Id}</strong>`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#35997c',
        cancelButtonColor: '#cb4848',
        cancelButtonText: this.lang === 'en' ? 'Cancel' : 'إلغاء',
        confirmButtonText: this.lang === 'en' ? 'Yes, delete it!' : 'نعم , إحذف وحدة التحكم'
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
              title: this.lang === 'en' ? 'This connection has been successfully deleted' : 'تم حذف هذا الاتصال بنجاح',
              toast: false,
              showConfirmButton: false,
              timer: 1500
            })
          }).catch(async () => {
            this.$swal.fire({
              position: 'center',
              icon: 'error',
              title: this.lang === 'en' ? `<strong>Failed to delete processor ID</strong>` : `<strong>فشل حذف معرف المعالج</strong>`,
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
            title: this.lang === 'en' ? 'This Controller has been successfully activated' : 'تم تنشيط وحدة التحكم هذه بنجاح',
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        } else {
          this.$swal.fire({
            position: 'center',
            icon: 'warning',
            title: this.lang === 'en' ? 'This controller has been successfully deactivated' : 'تم إلغاء تنشيط وحدة التحكم هذه ، بنجاح',
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
          title: this.lang === 'en' ? 'Failed Change Status' : 'فشل تغيير الحالة',
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
        let ON = this.lang === 'en' ? 'ON' : 'شغال';
        let OFF = this.lang === 'en' ? 'OFF' : 'لا يعمل';
        const userAllControllerConnectedWithProcessor = response.map((item, i) => ({
          id: (++i).toString(),
          Controller_Connection_Id: item.id.toString(),
          Name: item.name.toString(),
          Status: item.status,
          ActiveStatus: item.status === false ? OFF : ON,
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
