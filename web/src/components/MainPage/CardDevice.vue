<template>
  <div :class="{'device':true , '1':true}">
    <router-link :to="{path:'/mainPage/DeviceInfo/' + list.id }">
      <section>
        <img alt="" src="@/assets/img/Group 66.png">
        <div class="d-flex justify-content-center align-items-center mt-2">
          <p>{{ list.name }}</p>
          <button class="btn btn-danger text-light ml-2 mr-1 my-auto" @click.prevent="DeleteDevice(list)"><i
              class="fa fa-trash-alt"></i></button>
          <button class="btn btn-info text-light mr-2 my-auto" @click.prevent="editDevice(list)"><i
              class="fa fa-edit"></i></button>
        </div>
      </section>

    </router-link>

  </div>
</template>

<script>
export default {
  name: "CardDevice",
  props: {
    list: Object,
  },
  methods: {
    async DeleteDevice(list) {
      await console.log(list);
      // this.$store.dispatch() // to delete
    },
    editDevice(list) {
      console.log(list.id);
      let ID = list.id;
      this.$router.push(`/mainPage/DeviceInfo/${ID}/edit`);
    }
  },

}
</script>

<style scoped>

</style>
