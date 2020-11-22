<template>
  <div id="app" class="body-app">
    <div>
      <router-view/>
    </div>
  </div>

</template>



<script>


export default {
  name: 'App',
}
</script>

<style>
.body-app {
  padding: 0px;
  margin:  0px;
  position: relative;
  background: linear-gradient(103.72deg, #45A0F4 0%, #68DFA6 105.3%);
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: white;
  /*min-height: 100vh;*/
}
</style>




