<template>
  <div>
    <div v-if="user.isAdmin">
      <AdminDashboardPage v-if="user.isAdmin"/>
    </div>

    <div v-if="!user.isAdmin">
      <UserDashboard v-if="!user.isAdmin"/>
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

<style lang="scss" scoped>
section {
  .section__header {
    width: 100%;

    .section__title {
      width: 100%;
    }

    .section__control-search {
      display: flex;
      width: 100%;

      .section__title {
        width: 100%;
      }

      .searchInput {
        width: 100%;

        input {
          padding: 0.85rem 0.6rem;
          font-size: 1.25rem;
          border: none;
          width: 100%;
          border-radius: 10px 0 0 10px;

          &:focus {
            outline: none;

          }
        }
      }

      .section__button {
        margin: 0;
        border-radius: 0 10px 10px 0;
        color: white;
      }
    }
  }

  .Main__Card_FavoriteController {
    width: 100%;
    height: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: stretch;
    padding: 0.25rem 0;
    margin: 0 auto;
    font-family: Ubuntu, Arial, Helvetica, sans-serif;


    .Card_container {
      width: 340px;
      height: auto;
      display: flex;
      //flex-wrap: wrap;
      //flex-grow: 1;
      flex-direction: column;
      padding: 1.9rem 0;
      margin: 0.4rem;
      background-color: white;
      border-radius: 15px;
      box-shadow: 2px 2px 2px 2px #dbcbbe;

      .card_ListData {
        .card_item {
          font-size: 1.2rem;
          margin: 0.25rem auto;
          padding: 0.25rem 0;
          display: flex;
          justify-content: center;
          align-items: center;
          justify-items: center;
          text-align: center;

          &:last-child {
            margin-top: 1.5rem;
          }

          .btn_Star {
            margin-left: 1rem;
            background-color: #c6c6c6;
            color: white;
            border-radius: 5px;
            font-size: 1.2rem;
          }

          .btn_Star-Favorite {
            margin-left: 1rem;
            background-color: #f1f1f1;
            color: white;
            border-radius: 5px;
            font-size: 1.2rem;

            .fas {
              &.fa-star {
                color: #efbf15;
              }
            }

          }
        }

      }
    }
  }
}

button {
  padding: 0.6rem;
  border: 0;
  cursor: pointer;
  transition: color 0.3s ease, background-color 0.3s ease;
}

@media screen and (max-width: 410px) {
  section {
    .section__header {
      width: 100%;

      .section__title {
        width: 100%;
        font-size: 1.1rem;
      }

      .section__control-search {
        display: flex;
        width: 85%;

        .section__title {
          width: 100%;
        }

        .searchInput {
          width: 100%;

          input {
            padding: 0.5rem 0.6rem;
            font-size: 1.25rem;
            border: none;
            width: 100%;

            &:focus {
              outline: none;
            }
          }
        }

        .section__button {
          padding: 0.5rem 0.6rem;
        }
      }
    }
  }
}
</style>
