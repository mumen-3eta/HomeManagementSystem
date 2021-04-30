<template>
  <div>
    <div v-if="user.is_admin">
      <AdminDashboardPage v-if="user.is_admin"/>
    </div>

    <div v-if="!user.is_admin">
      <UserDashboard v-if="!user.is_admin"/>
    </div>

  </div>
</template>

<script>
import {mapGetters} from "vuex";
import AdminDashboardPage from "@/Pages/Admin/AdminDashboardPage";
import UserDashboard from "@/components/Dir_Main/UserDashboard";

export default {
  name: "DashboardMain",
  components: {
    UserDashboard,
    AdminDashboardPage
  },
  computed: {
    ...mapGetters(['user']),
  },
}
</script>
