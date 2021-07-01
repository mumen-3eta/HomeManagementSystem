<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div
        :class="lang ==='ar' ? 'container_Admin_AddLocationController direction-rtl' :'container_Admin_AddLocationController direction-ltr'">
      <section class="container_Admin_btnSectionGroup">
        <header class="section__header">
          <h2 class="section__title">{{ $t('AdminDashboard.allUser.title') }}</h2>
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
              :search-options=" {
                    enabled: true,
                    skipDiacritics: true,
                    placeholder: 'Search this table'
                  }"
              :sort-options="{
                enabled: true,
                initialSortBy: [
                        {field: 'Create_at', type: 'desc'},
                        {field: 'id', type: 'desc'},
                    ]
              }"
              :theme="mode==='dark' ? 'black-rhino' : ''">
            <div slot="emptystate" style="text-align: center">
              {{ $t('Dashboard.NoDataForTable') }}
            </div>
            <div slot="table-actions" class="btn_searchScan"></div>
            <template slot="table-row" slot-scope="props">
              <div v-if="props.column.field === 'Image'" class="Image_styleTable">
                <img :alt="props.row.userName ? props.row.userName : 'user photo'"
                     :src="props.row.Image ? props.row.Image : 'https://img.icons8.com/metro/500/000000/user-male.png'">
              </div>
              <span v-else>
                {{ props.formattedRow[props.column.field] }}
              </span>
            </template>

          </vue-good-table>
        </div>
      </section>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AdminAllUsersPage",
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'default',//default
      columns_EN: [
        {
          label: 'ID',
          field: 'id',
          type: 'string',
        },
        {
          label: 'User Name',
          field: 'userName',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Email',
          field: 'Email',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Administration',
          field: 'IsAdmin',
          type: 'string',
          sortable: false,
        },
        {
          label: 'Date added',
          field: 'Create_at',
          type: 'string',
        },
        {
          label: 'Image',
          field: 'Image',
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
          label: 'إسم المستخدم',
          field: 'userName',
          type: 'string',
          sortable: false,
        },
        {
          label: 'البريد الإلكتروني',
          field: 'Email',
          type: 'string',
          sortable: false,
        },
        {
          label: 'الادارة',
          field: 'IsAdmin',
          type: 'string',
          sortable: false,
        },
        {
          label: 'تاريخ الاضافة',
          field: 'Create_at',
          type: 'string',
        },
        {
          label: 'الصورة',
          field: 'Image',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.Get_All_Users ? this.$store.getters.Get_All_Users : [],
    }
  },
  methods: {
    /*** Get All Processors  ***/
    async GetAllUsers() {
      await axios.get('/api/v1/users/profile/all').then(async ({data: {productionData: response}}) => {
        let Admin = this.lang === 'en' ? 'Admin' : 'المسؤول';
        let User = this.lang === 'en' ? 'User' : 'مستخدم';
        const Get_All_Users = response.map((item, i) => ({
          id: (++i).toString(),
          user_id: item.id ? item.id.toString() : item.id,
          userName: item.user_name ? item.user_name.toString() : item.user_name,
          Email: item.email ? item.email.toString() : item.email,
          firstName: item.first_name ? item.first_name.toString() : item.first_name,
          lastName: item.last_name ? item.last_name.toString() : item.last_name,
          Image: item.image ? item.image.toString() : item.image,
          Mobile: item.mobile ? item.mobile.toString() : item.mobile,
          IsAdmin: item.is_admin ? Admin : User,
          Create_at: this.FormatDate(item.create_at),
        }))
        await this.$store.dispatch('Get_All_Users', Get_All_Users);
        this.rows = this.$store.getters.Get_All_Users ? this.$store.getters.Get_All_Users : [];
      }).catch(() => {
        console.log("get Get All Users Faild")
        this.rows = this.$store.getters.Get_All_Users ? this.$store.getters.Get_All_Users : [];
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
  },
  async beforeMount() {
    await this.GetAllUsers();
  },
}
</script>

<style lang="scss" scoped>
.Image_styleTable {
  display: flex;
  justify-content: center;
  justify-items: center;
  text-align: center;
  flex-direction: column;
  margin: 0 auto;
  width: 40%;
  height: 100%;

  img {
    width: 40%;
    margin: 0 auto;
    border-radius: 50%;
  }
}

</style>
