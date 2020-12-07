<template>
  <div :class="{'device':true , 'isNotActive':list.status === 0 ,'isActive':list.status === 1, '1':true}">
    <section>
      <router-link :to="{path:'/mainPage/DeviceInfo/' + list.id }">
        <img alt="" src="@/assets/img/Group 66.png">
        <div class="d-flex justify-content-center align-items-center mt-2">
          <p>{{ list.name }}</p>
        </div>
      </router-link>
      <div class="d-flex justify-content-center align-items-center mt-2">
        <button class="btn btn-danger text-light ml-2 mr-1 my-auto" @click.prevent="DeleteDevice(list)"><i
            class="fa fa-trash-alt"></i></button>
        <button class="btn btn-info text-light mr-2  my-auto" @click.prevent="editDevice(list)"><i
            class="fa fa-edit"></i></button>
        <!--       if  need last input active active it (checkbox active)         -->
        <div class="form-check form-switch isActiveInput justify-content-end align-items-center">
          <input v-if="list.status !== 0" id="flexSwitchCheckDefault1" checked class="form-check-input" type="checkbox">
          <input v-if="list.status !== 1" id="flexSwitchCheckDefault2" class="form-check-input" type="checkbox">
          <label for="flexSwitchCheckDefault1" hidden></label><label for="flexSwitchCheckDefault2" hidden></label>
        </div>
      </div>
    </section>


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

.btn .fa-trash-alt {
  padding: 5px 3px;
}

.btn .fa-edit {
  padding: 4px 2px;
}

.isActive {
  border: 2px dashed #13bf2a;
  border-radius: 15px;
}

.isNotActive {
  border: 2px dashed #929292;
  border-radius: 15px;
}

</style>
