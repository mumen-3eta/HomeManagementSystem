<template>
  <div :class="{'device':true , '1':true}">
    <router-link :to="{path:'/mainPage/DeviceInfo/' + list.id }">
      <section>
        <img alt="" src="@/assets/img/Group 66.png">
        <div class="d-flex justify-content-center align-items-center mt-2">
          <p>{{ list.name }}</p>
          <button class="btn btn-danger text-light mx-3 my-auto" @click.prevent="DeleteDevice(list)"> Delete</button>
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
    DeleteDevice(list) {
      console.log(list);
      // this.$store.dispatch() // to delete
    }
  },

}
</script>

<style scoped>

</style>
