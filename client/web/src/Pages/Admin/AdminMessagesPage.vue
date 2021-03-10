<template>
  <div class="wrapper">
    <!--   ********** body content can change it here *****************-->
    <div class="container_Admin_AddLocationController">
      <header class="section__header">
        <h2 class="section__title">Messages</h2>
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
              <button class="btn_deleted"><i class="fas fa-trash-alt"></i> Delete</button>
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
import io from "socket.io-client";

import {mapGetters} from "vuex";

export default {
  name: "AdminMessagesPage",
  data() {
    return {
      socket: '',
      NowMessages: [],
      columns: [
        {
          label: 'Email',
          field: 'email',
          type: 'string',
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
          label: 'Action',
          field: 'btn_Action',
          type: 'string',
          sortable: false,
        },
      ],
      rows: this.$store.getters.allMessages,
    }
  },
  created() {
    this.socket = io('http://localhost:3001');
  },
  mounted() {
    // reserve event that sent from server
    this.socket.on("all_messages_server", (data) => {
      /*
      * create route in api (create/message)
      * take (email,subject,message)
      * create route in api (Get/message)
      * return [
      *    {email: "dataInDB", subject: "dataInDB", message: "dataInDB"},
      *    {...},
      *     ...
      * ]
      * */
      this.NowMessages.push(data);//api and database use (convert NowMessages to api)
      this.$store.dispatch('allMessages', this.NowMessages);
      this.rows = this.$store.getters.allMessages;
    });
  },
  computed: {
    ...mapGetters(['allMessages'])
  },
}
</script>

<style scoped>

</style>
