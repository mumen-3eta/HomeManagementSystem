<template>
  <div class="content__body-loginNew">
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
              <input id="input1" v-model.trim="email" class="input-style" name="input1" type="text"
                     @change.prevent="testEmail">
              <hr id="line1" class="style_line">
            </div>
            <div class="inputForm">
              <label id="labelInput2" class="text-label" for="input2">Password</label>
              <input id="input2" v-model.trim="password" class="input-style" type="password"
                     @change.prevent="testPassword">
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


export default {
  name: "newLoginPage",
  components: {},
  data() {
    return {
      email: null,
      password: null,

    }
  },
  methods: {
    testEmail() {
      this.valiided = this.email || '';
      console.log(this.email)
    },
    testPassword() {
      this.valiided = this.password || '';
      console.log(this.password)
    },
  },
  mounted() {
    const input1 = document.getElementById("input1");
    const labelInput1 = document.getElementById("labelInput1");
    const input2 = document.getElementById("input2");
    const labelInput2 = document.getElementById("labelInput2");
    const line1 = document.getElementById("line1");
    const line2 = document.getElementById("line2");

    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "move");
      SlidUp(line1, "line-dis");
    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "move");
      SlidUp(line2, "line-dis");
    })


    input1.addEventListener("focusout", function () {
      SlidDown(labelInput1, "move");
      SlidDown(line1, "line-dis");
    })
    input2.addEventListener("focusout", function () {
      SlidDown(labelInput2, "move");
      SlidDown(line2, "line-dis");
    })


    function SlidUp(input, nameClass) {
      input.classList.add(nameClass);
    }

    function SlidDown(input, nameClass) {
      input.classList.remove(nameClass);
    }

  },

}
</script>

<style scoped>
@import "../assets/css/_NEWloginStyle.css";
</style>
