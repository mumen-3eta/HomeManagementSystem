<template>
  <div id="app" class="body-app">
    <div id="loadingScreen">
      <div class="loader">
        <div> loading...</div>
      </div>
    </div>
    <div>
      <router-view/>
    </div>
  </div>

</template>


<script>
export default {
  name: 'App',
  mounted() {
    window.onload = function () {
      setTimeout(function () {
        document.getElementById("loadingScreen").style.display = "none"
      }, 500); // 0.5 sec
    };
  }
}
</script>

<style scoped>

#loadingScreen {
  position: fixed;
  width: 100%;
  height: 100vh;
  background: #fff url('https://media.giphy.com/media/feN0YJbVs0fwA/giphy.gif') no-repeat center;
  z-index: calc(99 * 999);
  display: flex;
  justify-content: center;
  align-items: first;
}

.loader {
  margin-top: 9rem;
  font-size: 1.25rem;
  color: #3f9d8e;
  animation: Opacity 2.5s infinite;
}

@keyframes Opacity {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    opacity: 1;
  }
}
</style>




