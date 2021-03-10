<template>
  <div class="container__Show-Controller">
    <h2>show Controller</h2>
    <p>
      {{ $route.params.processor_id }}
      {{ $route.params.controller_id }}
    </p>
  </div>
</template>

<script>
export default {
  name: "ShowControllerInfo",
  data() {
    return {}
  },
  methods: {},
}
</script>

<style lang="scss" scoped>
@import "../../assets/sass/ShowControllerInfoStyle";
</style>
