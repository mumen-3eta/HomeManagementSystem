<template>
  <header class="Main__Header">
    <main class="main__login">
      <div class="login__photo"></div>
      <div class="login__inform">
        <h2 class="login__inform-title">Welcome back</h2>
        <p class="login__inform-minTitle">Sign to continue using Tipe</p>
        <form action="" class="login__form">
          <div class="form__group">
            <label id="emailUsername_LabelInput" class="form__input-label" for="EmailUsername_input">username or
              E-mail</label>
            <input id="EmailUsername_input" class="form__input-input" type="text">
            <hr id="line1" class="style_line">
          </div>
          <div class="form__group">
            <label id="password_LabelInput" class="form__input-label" for="password_input">Password</label>
            <input id="password_input" class="form__input-input" type="password">
            <hr id="line2" class="style_line">
          </div>
          <div class="form__group">
            <p class="form__forgot">
              <a class="form__forgot-link" href="">Forgot your password?</a>
            </p>
          </div>
          <div class="form__group">
            <button class="form__button-submit" type="submit">Login</button>
          </div>
        </form>
        <div class="go__register">
          <p class="register__label">
            Don't have an account?
            <a class="register__label-link" href="">Register Here</a>
          </p>
        </div>
      </div>
    </main>
  </header>
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
    const input1 = document.getElementById("EmailUsername_input");
    const labelInput1 = document.getElementById("emailUsername_LabelInput");
    const input2 = document.getElementById("password_input");
    const labelInput2 = document.getElementById("password_LabelInput");
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
    // let value = input1.value
    // if (value) {
    input1.addEventListener("focusout", function () {
      SlidDown(labelInput1, "move");
      SlidDown(line1, "line-dis");
    })
    input2.addEventListener("focusout", function () {
      SlidDown(labelInput2, "move");
      SlidDown(line2, "line-dis");
    })

    // }


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
@import "../../assets/css/_NEWloginStyle.css";
@import "../../assets/css/ClearStyle/normalize.css";
</style>
