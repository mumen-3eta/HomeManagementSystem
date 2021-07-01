<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div
        :class="lang ==='ar' ? 'container_Admin_AddLocationController direction-rtl' :'container_Admin_AddLocationController direction-ltr'">
      <header class="section__header">
        <h2 class="section__title">{{ $t('AdminDashboard.Messages.title') }}</h2>
      </header>
      <div class="project my-3 mx-auto p-2 position-relative">
        <vue-good-table
            :columns="this.lang === 'en' ? columns_EN : columns_AR"
            :pagination-options="{
                    enabled: true,
                    mode: 'pages',
                    perPage: 5,
                    position: 'bottom',
                    perPageDropdown: [5,7,9,12,15,25,50],
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
            :rtl="this.lang !== 'en'"
            :search-options=" {
                    enabled: true,
                    skipDiacritics: true,
                    placeholder: 'Search this table'
                  }"
            :theme="mode==='dark' ? 'black-rhino' : ''">
          <!--         -->
          <div slot="emptystate" style="text-align: center">
            {{ $t('Dashboard.NoDataForTable') }}
          </div>
          <div slot="table-actions" class="btn_searchScan"></div>
          <template slot="table-row" slot-scope="props">
            <div v-if="props.column.field === 'btn_Action'" class="btn_actionGroup">
              <button class="btn_deleted"><i
                  class="fas fa-trash-alt"></i>
                {{ $t('AdminDashboard.Messages.Delete') }}
              </button>
              <button class="btn_read">
                <div class="btn_Status">
                  <div class="form-check form-switch">
                    <label for="flexSwitchCheckChecked2">{{ $t('AdminDashboard.Messages.Read') }}</label>
                    <input id="flexSwitchCheckChecked2" :checked=" '' "
                           class="form-check-input"
                           type="checkbox">
                  </div>
                </div>
              </button>
            </div>
            <span v-else>
                {{ props.formattedRow[props.column.field] }}
              </span>
          </template>
        </vue-good-table>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "AdminMessagesPage",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      socket: '',
      NowMessages: [],
      columns_EN: [
        {
          label: 'Email',
          field: 'email',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Subject',
          field: 'subject',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Message',
          field: 'message',
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
          label: 'البريد الإلكتروني',
          field: 'email',
          type: 'string',
          sortable: false,
        },
        {
          label: 'الموضوع',
          field: 'subject',
          type: 'string',
          sortable: false,
        },
        {
          label: 'الرسالة',
          field: 'message',
          type: 'string',
          sortable: false,
        },
        {
          label: 'الإعدادات',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.allMessages ? this.$store.getters.allMessages : [],
    }
  },
  created() {
    // this.socket = io('http://localhost:3001');
  },
  methods: {
    DeleteMessage(id) {
      console.log(id)
    }
  },
  mounted() {
    /***
     * // reserve event that sent from server
     */
    // this.socket.on("all_messages_server", (data) => {
    //   /*
    //   * create route in api (create/message)
    //   * take (email,subject,message)
    //   * create route in api (Get/message)
    //   * return [
    //   *    {email: "dataInDB", subject: "dataInDB", message: "dataInDB"},
    //   *    {...},
    //   *     ...
    //   * ]
    //   * */
    //   this.NowMessages.push(data);//api and database use (convert NowMessages to api)
    //   this.$store.dispatch('allMessages', this.NowMessages);
    //   this.rows = this.$store.getters.allMessages ? this.$store.getters.allMessages : [];
    // });
  },
  computed: {
    ...mapGetters(['allMessages'])
  },
}
</script>

<style lang="scss" scoped>
.form-switch .form-check-input {
  transform: rotate(0) scale(1.2);
  width: 2.5em;
  margin-left: -2.5em;
  margin-right: 1rem;
  border-radius: 2em;
}

.btn_read {
  display: flex;
  justify-content: center;
  justify-items: center;
  justify-self: center;
  padding: 0.15rem;
  margin: 0 0.25rem;
  border-radius: 10px;

}
</style>
