<template>
  <button id="up_Button" v-animate-onscroll="'animated fadeInRight'" class="up_Btn">
    <i class="fas fa-angle-up"></i>
  </button>
</template>

<script>
export default {
  name: "UpButton",
  mounted() {
    window.onscroll = function () {
      scrollFunction();
    };

    function scrollFunction() {
      if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
        document.getElementById("main__nav").style.backgroundColor = "rgba(7,6,6,0.9)";
        document.getElementById("main__nav").style.boxShadow = "0 0 0 3px #70cbcb";
        document.getElementById("image_logo").style.width = "75%";
        document.getElementById("up_Button").style.display = "block";
      } else {
        document.getElementById("main__nav").style.backgroundColor = "";
        document.getElementById("image_logo").style.width = "";
        document.getElementById("main__nav").style.boxShadow = "";
        document.getElementById("up_Button").style.display = "none";
      }
    }

    document.getElementById("up_Button").addEventListener("click", function () {
      document.body.scrollTop = 0; // For Safari
      document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    });
  }
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
