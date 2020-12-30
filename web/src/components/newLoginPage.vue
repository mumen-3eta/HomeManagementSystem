<template>
  <div>
    <!--    <NavBar/>-->
    <div class="main-card">
      <div class="img-card">
        <img alt="background-image" class="img-borderRadius" src="@/assets/img/bg-image.jpg">
      </div>
      <div class="login-Card">
        <div class="header_loginCard">
          <h1 class="titleHeader"> Welcome back </h1>
          <p class="text-gray">Sign to continue using Tipe</p>
        </div>
        <form>
          <div>
            <div class="inputForm">
              <label id="labelInput1" class="text-label" for="input1">UserName or Email</label>
              <input id="input1" class="input-style" name="input1" type="text">
              <hr id="line1" class="style_line">
            </div>
            <div class="inputForm">
              <label id="labelInput2" class="text-label" for="input2">Password</label>
              <input id="input2" class="input-style" type="password">
              <hr id="line2" class="style_line">
            </div>
            <div class="card-check">
              <label class="container_RememberMe forRes">Remember Me
                <input type="checkbox">
                <span class="checkMark"></span>
              </label>
              <p><a class="link-forgot forRes" href="#">Forgot your password?</a></p>
            </div>
            <div>
              <button class="btn-submitForm" type="submit">Login</button>
            </div>
          </div>
        </form>
        <div>
          <p class="forRes">Don't have an account?<a class="link-forgot" href="#"> Register Here</a></p>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
// import NavBar from "@/components/TopBar/NavBar";

export default {
  name: "newLoginPage",
  components: {
    // NavBar,
  },

}
</script>

<style scoped>
@import "../assets/css/_NEWloginStyle.css";
</style>
