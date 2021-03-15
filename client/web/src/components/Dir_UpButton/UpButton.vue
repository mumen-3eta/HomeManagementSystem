<template>
  <button id="up_Button" v-animate-onscroll="'animated fadeInRight'" class="up_Btn">
    <i class="fas fa-angle-up"></i>
  </button>
</template>

<script>
export default {
  name: "UpButton",
}
</script>

<style scoped>
.up_Btn {
  display: none; /* Hidden by default */
  position: fixed; /* Fixed/sticky position */
  bottom: 5rem; /* Place the button at the bottom of the page */
  right: 2rem; /* Place the button 30px from the right */
  z-index: 99;
  border: none;
  outline: none;
  background-color: #0d7571;
  color: white;
  cursor: pointer;
  padding: 0.52rem 0.72rem;
  border-radius: 5px;
  font-size: 1.45rem;
}

.up_Btn:hover {
  background-color: #386462;
}
</style>
