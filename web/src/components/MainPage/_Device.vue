<template>
  <div :class="{'device':true , '1':true}">
    <router-link :to="{path:'/mainPage/DeviceInfo/' + list.id }">
      <section>
        <img alt="" src="@/assets/img/Group 66.png">
        <p>{{ list.name }}</p>
      </section>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "_Device",
  props: {
    list: Object,
  },

}
</script>

<style scoped>

</style>
