<template>
  <div :class="lang==='ar' ? 'direction-rtl' :'direction-ltr'">
    <h2>{{ $t('Dashboard.ControllersConnected.allControllerConnected') }}</h2>
    <div class="SubMain__Section-AllController my-3 mx-auto p-2 position-relative">
      <div :class="lang==='ar' ? 'search_group direction-ltr' :'search_group '">
        <i class="fa fa-search search_group-icon"></i>
        <label for="searchId"></label>
        <input id="searchId" v-model="searchTerm" class="search_group-input" type="search">
        <button class="btn btn-secondary mx-2 search_group-btn" @click.prevent="OpenScanSearch">
          {{ $t('Dashboard.ControllersConnected.btn_Text') }}
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
          :rtl="this.lang!=='en'"
          :search-options=" {
                    enabled: false,
                    skipDiacritics: true,
                    placeholder: 'Search this table',
                    externalQuery: searchTerm
                  }">
        <div slot="table-actions" class="btn_searchScan"></div>
        <template slot="table-row" slot-scope="props">
          <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">
            <button class="btn_deleted"
                    @click.prevent="DeleteControllerConnection(props.row.ControllerId ,props.row.ProcessorId)">
              <i class="fas fa-trash-alt"></i>
            </button>
            <div class="btn_Status">
              <div class="form-check form-switch">
                <label for="flexSwitchCheckChecked"></label>
                <input id="flexSwitchCheckChecked"
                       :checked="props.row.Status === 'ON' ? 'checked' : '' || props.row.Status === 'شغال' ? 'checked' : ''  "
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
    <modal :class=" lang==='ar' ? 'direction-ltr' :''" :resizable="false" :width="650" height="auto"
           name="ScanSearchControllerId" @closed="AfterCloseScanSearch"
           @before-open="AfterCloseScanSearch">
      <i class="fas-closeBTN fas fa-times" @click.prevent="CloseScanSearch"></i>
      <div class="container__AddProcessorID">
        <h3>{{ $t('Dashboard.ControllersConnected.webCame.title') }}</h3>
        <div class="otherWay_AddProcessorID">
          <div class="handle__camera">
            <qrcode-stream v-if="isShowingCamera" @decode="onDecodeSearch" @init="onInit">
              <p v-if="isShowingCamera && isShowingWait" class="wait_class-cam">
                {{ $t('Dashboard.ControllersConnected.webCame.messageWait') }}</p>
            </qrcode-stream>
            <p v-if="!isShowingCamera">{{ $t('Dashboard.ControllersConnected.webCame.error') }}</p>
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
      lang: localStorage.getItem('lang') || 'en',
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
          label: 'Date added',
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
          label: 'Settings',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      columns_AR: [
        {
          label: 'الرقم',
          field: 'id',
          type: 'string',
        },
        {
          label: 'الاسم',
          field: 'ControllerName',
          type: 'string',
          sortable: false,
        },
        {
          label: 'معرف وحدة التحكم',
          field: 'ControllerId',
          type: 'string',
          sortable: false,
        },
        {
          label: 'معرف المعالج',
          field: 'ProcessorId',
          type: 'string',
          sortable: false,
        },
        {
          label: 'تاريخ الاضافة',
          field: 'CreateAt',
          type: 'string',
        },
        {
          label: 'الحالة',
          field: 'Status',
          type: 'string',
          sortable: false,
        },
        {
          label: 'إعدادات',
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
            title: this.lang === 'en' ? 'This console has been successfully activated' : 'تم تنشيط وحدة التحكم هذه بنجاح',
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
        await this.GetAllControllerConnectedForThisUser();
      }).catch(async () => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: this.lang === 'en' ? 'Failed Change Status' : 'فشل تغيير الحالة',
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
        let ON = this.lang === 'en' ? 'ON' : 'شغال';
        let OFF = this.lang === 'en' ? 'OFF' : 'لا يعمل';
        const AllControllerConnectedForThisUser = response.map((item) => ({
          id: (item.id).toString(),
          ControllerName: item.name.toString(),
          ControllerId: item.controller_id.toString(),
          ProcessorId: item.processor_id.toString(),
          CreateAt: this.FormatDate(item.create_at.toString()),
          Status: item.status === false ? OFF : ON,
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
