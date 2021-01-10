<template>
  <div>
    <div class="container__askedQuestionSc">
      <div class="askedQuestion-section">
        <div class="askedQuestionSc-title">
          <h2 class="asked-h">Frequently Asked Questions</h2>
        </div>
        <div class="askedQuestionSc-body">
          <div class="container__body-asked">
            <div ref="btnDown11" class="container__question" @click.prevent="down1">
              <p class="text-question">How is a decentralized network different from the traditional web?</p>
              <button id="btn_Down-01" ref="btnDown01" class="text-question__btn" @click.prevent="down11">
                <i class="fas fa-angle-down"></i>
              </button>
              <button id="btn_Up-01" class="text-question__btn Change_btn">
                <i class="fas fa-angle-up"></i>
              </button>

            </div>
            <div id="content_answer-01" class="container__answer ">
              <p class="text-answer">
                Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to
                connect to a single server containing that data. This method of storing data makes it easy for third
                parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple
                devices in the network, which makes it harder to hack and interfere with your data.
              </p>
            </div>
          </div><!--duplicated -->
          <div class="container__body-asked">
            <div ref="btnDown22" class="container__question" @click.prevent="down2">
              <p class="text-question">How is a decentralized network different from the traditional web?</p>
              <button id="btn_Down-02" ref="btnDown02" class="text-question__btn" @click.prevent="down22">
                <i class="fas fa-angle-down "></i>
              </button>
              <button id="btn_Up-02" class="text-question__btn Change_btn">
                <i class="fas fa-angle-up"></i>
              </button>

            </div>
            <div id="content_answer-02" class="container__answer ">
              <p class="text-answer">
                Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to
                connect to a single server containing that data. This method of storing data makes it easy for third
                parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple
                devices in the network, which makes it harder to hack and interfere with your data.
              </p>
            </div>
          </div><!--duplicated -->
          <div class="container__body-asked">
            <div ref="btnDown33" class="container__question" @click.prevent="down3">
              <p class="text-question">How is a decentralized network different from the traditional web?</p>
              <button id="btn_Down-03" ref="btnDown03" class="text-question__btn" @click.prevent="down33">
                <i class="fas fa-angle-down "></i>
              </button>
              <button id="btn_Up-03" class="text-question__btn Change_btn">
                <i class="fas fa-angle-up"></i>
              </button>

            </div>
            <div id="content_answer-03" class="container__answer ">
              <p class="text-answer">
                Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to
                connect to a single server containing that data. This method of storing data makes it easy for third
                parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple
                devices in the network, which makes it harder to hack and interfere with your data.
              </p>
            </div>
          </div><!--duplicated -->
          <div class="container__body-asked">
            <div ref="btnDown44" class="container__question" @click.prevent="down4">
              <p class="text-question">How is a decentralized network different from the traditional web?</p>
              <button id="btn_Down-04" ref="btnDown04" class="text-question__btn" @click.prevent="down44">
                <i class="fas fa-angle-down "></i>
              </button>
              <button id="btn_Up-04" class="text-question__btn Change_btn">
                <i class="fas fa-angle-up"></i>
              </button>

            </div>
            <div id="content_answer-04" class="container__answer ">
              <p class="text-answer">
                Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to
                connect to a single server containing that data. This method of storing data makes it easy for third
                parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple
                devices in the network, which makes it harder to hack and interfere with your data.
              </p>
            </div>
          </div><!--duplicated -->
          <div class="container__body-asked">
            <div ref="btnDown55" class="container__question" @click.prevent="down5">
              <p class="text-question">How is a decentralized network different from the traditional web?</p>
              <button id="btn_Down-05" ref="btnDown05" class="text-question__btn" @click.prevent="down55">
                <i class="fas fa-angle-down "></i>
              </button>
              <button id="btn_Up-05" class="text-question__btn Change_btn">
                <i class="fas fa-angle-up"></i>
              </button>

            </div>
            <div id="content_answer-05" class="container__answer ">
              <p class="text-answer">
                Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to
                connect to a single server containing that data. This method of storing data makes it easy for third
                parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple
                devices in the network, which makes it harder to hack and interfere with your data.
              </p>
            </div>
          </div><!--duplicated -->
          <div class="container__body-asked">
            <div ref="btnDown66" class="container__question" @click.prevent="down6">
              <p class="text-question">How is a decentralized network different from the traditional web?</p>
              <button id="btn_Down-06" ref="btnDown06" class="text-question__btn" @click.prevent="down66">
                <i class="fas fa-angle-down "></i>
              </button>
              <button id="btn_Up-06" class="text-question__btn Change_btn">
                <i class="fas fa-angle-up"></i>
              </button>

            </div>
            <div id="content_answer-06" class="container__answer ">
              <p class="text-answer">
                Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to
                connect to a single server containing that data. This method of storing data makes it easy for third
                parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple
                devices in the network, which makes it harder to hack and interfere with your data.
              </p>
            </div>
          </div><!--duplicated -->
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AskedQuestionSc",
  methods: {
    down1() {
      this.$refs.btnDown01.click()
    },
    down11() {
      this.$refs.btnDown11.click()
    },
    down2() {
      this.$refs.btnDown02.click()
    },
    down22() {
      this.$refs.btnDown22.click()
    },
    down3() {
      this.$refs.btnDown03.click()
    },
    down33() {
      this.$refs.btnDown33.click()
    },
    down4() {
      this.$refs.btnDown04.click()
    },
    down44() {
      this.$refs.btnDown44.click()
    },
    down5() {
      this.$refs.btnDown05.click()
    },
    down55() {
      this.$refs.btnDown55.click()
    },
    down6() {
      this.$refs.btnDown06.click()
    },
    down66() {
      this.$refs.btnDown66.click()
    },
  },
  mounted() {
    // bt01
    document.getElementById("btn_Down-01").addEventListener("click", function () {
      document.getElementById("content_answer-01").classList.toggle("hidden_answer");
      document.getElementById("btn_Down-01").classList.toggle("Change_btn");
      document.getElementById("btn_Up-01").classList.toggle("Change_btn");
    })
    // bt02
    document.getElementById("btn_Down-02").addEventListener("click", function () {
      document.getElementById("content_answer-02").classList.toggle("hidden_answer");
      document.getElementById("btn_Down-02").classList.toggle("Change_btn");
      document.getElementById("btn_Up-02").classList.toggle("Change_btn");
    })
    // bt03
    document.getElementById("btn_Down-03").addEventListener("click", function () {
      document.getElementById("content_answer-03").classList.toggle("hidden_answer");
      document.getElementById("btn_Down-03").classList.toggle("Change_btn");
      document.getElementById("btn_Up-03").classList.toggle("Change_btn");
    })
    // bt04
    document.getElementById("btn_Down-04").addEventListener("click", function () {
      document.getElementById("content_answer-04").classList.toggle("hidden_answer");
      document.getElementById("btn_Down-04").classList.toggle("Change_btn");
      document.getElementById("btn_Up-04").classList.toggle("Change_btn");
    })
    // bt05
    document.getElementById("btn_Down-05").addEventListener("click", function () {
      document.getElementById("content_answer-05").classList.toggle("hidden_answer");
      document.getElementById("btn_Down-05").classList.toggle("Change_btn");
      document.getElementById("btn_Up-05").classList.toggle("Change_btn");
    })
    // bt06
    document.getElementById("btn_Down-06").addEventListener("click", function () {
      document.getElementById("content_answer-06").classList.toggle("hidden_answer");
      document.getElementById("btn_Down-06").classList.toggle("Change_btn");
      document.getElementById("btn_Up-06").classList.toggle("Change_btn");
    })
  }
}
</script>

<style scoped>
@import "../../assets/css/_askedQuestionScStyle.css";
</style>
