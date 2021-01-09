<template>
  <div>
    <div class="container__contactUsAndInfoSc">
      <div class="container__infoBox-shadow"></div>
      <div class="contactUs-main__section">
        <div class="contactus__title">
          <h2 class="contactus__title-h">Contact the Pied Piper team</h2>
        </div>
        <div class="contactUs__info">
          <div class="information__Section">
            <h3 class="information__Section-title">Let's talk about the future of the internet</h3>
            <p class="information__Section-p">We're here to answer your questions and discuss the decentralized future
              of the internet.</p>
            <ul class="container__contactInfo">
              <li class="container-contactUs__list">
                <i class="fas fa-map-marker-alt"></i>
                <p class="container-contactUs__p">Pied Piper HQ - 59 Silicon Av.</p>
              </li>
              <li class="container-contactUs__list">
                <i class="fas fa-phone-volume"></i>
                <p class="container-contactUs__p">555-333-555</p>
              </li>
              <li class="container-contactUs__list">
                <i class="fas fa-envelope"></i>
                <p class="container-contactUs__p">pay-the-piper@piedpiper.com</p>
              </li>
            </ul>

          </div>
          <div class="contactMsg__Section">
            <form action="#" class="form__contactUs">
              <div class="form__contactUs-group">
                <label id="lab_EmailInputContact" class="labelInputContact "
                       for="EmailInputContact">Email</label>
                <input id="EmailInputContact" class="InputContact" type="email">
              </div>
              <div class="form__contactUs-group">
                <label id="lab_SubjectInputContact" class="labelInputContact" for="SubjectInputContact">Subject</label>
                <input id="SubjectInputContact" class="InputContact" type="text">
              </div>
              <div class="form__contactUs-group">
                <label id="lab_MsgInputContact" class="labelInputContact" for="MsgInputContact">Message</label>
                <textarea id="MsgInputContact" class="InputContact" cols="30" rows="10"></textarea>
              </div>
              <div class="form__contactUs-group">
                <button class="form__contactUs-btn">Send</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ContactUsAndInfoSc",
  mounted() {
    const input1 = document.getElementById("EmailInputContact");
    const labelInput1 = document.getElementById("lab_EmailInputContact");
    const input2 = document.getElementById("SubjectInputContact");
    const labelInput2 = document.getElementById("lab_SubjectInputContact");
    const input3 = document.getElementById("MsgInputContact");
    const labelInput3 = document.getElementById("lab_MsgInputContact");


    input1.addEventListener("focusin", function () {
      SlidUp(labelInput1, "AddMoved");
      SlidUp(input1, "AddBorder");

    })
    input2.addEventListener("focusin", function () {
      SlidUp(labelInput2, "AddMoved");
      SlidUp(input2, "AddBorder");
    })
    input3.addEventListener("focusin", function () {
      console.log("fefe")
      SlidUp(labelInput3, "AddMoved");
      SlidUp(input3, "AddBorder");
    })


    input1.addEventListener("focusout", function () {
      if (!input1.value) {
        SlidDown(labelInput1, "AddMoved");
        SlidDown(input1, "AddBorder");
      }
    })
    input2.addEventListener("focusout", function () {
      if (!input2.value) {
        SlidDown(labelInput2, "AddMoved");
        SlidDown(input2, "AddBorder");
      }
    })
    input3.addEventListener("focusout", function () {
      if (!input3.value) {
        SlidDown(labelInput3, "AddMoved");
        SlidDown(input3, "AddBorder");
      }
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
@import "../../assets/css/_contactUsAndInfoScStyle.css";
</style>
