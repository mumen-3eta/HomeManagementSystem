<template>
  <div>
    <div class="container__askedQuestionSc">
      <div v-animate-onscroll="'animated fadeInLeft'" class="askedQuestion-section">
        <div class="askedQuestionSc-title">
          <h2 class="asked-h">Frequently Asked Questions</h2>
        </div>
        <div class="askedQuestionSc-body">
          <div v-for="(askedQuestion , index) in ListQuestions" :key="index" class="container__body-asked">
            <div class="container__question" @click.prevent="ChangeStatus(index)">
              <p class="text-question">{{ askedQuestion.question }}</p>
              <button v-show="!askedQuestion.show" class="text-question__btn">
                <i class="fas fa-angle-down"></i>
              </button>
              <button v-show="askedQuestion.show" class="text-question__btn">
                <i class="fas fa-angle-up"></i>
              </button>
            </div>
            <div v-show="askedQuestion.show" v-animate-css="'animated fadeIn'" class="container__answerShowed">
              <p class="text-answer">
                {{ askedQuestion.answer }}
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
  data() {
    return {
      ListQuestions: [
        {
          question: "what is HMSy ?",
          answer: "this is a project created by Student in Islamic University  form Gaza , To complete Graduation Project",
          show: false,
        },
        {
          question: "How is a decentralized network different from the traditional web?",
          answer: "Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to connect to a single server containing that data. This method of storing data makes it easy for third parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple devices in the network, which makes it harder to hack and interfere with your data.",
          show: false,
        },
        {
          question: "How is a decentralized network different from the traditional web?",
          answer: "Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to connect to a single server containing that data. This method of storing data makes it easy for third parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple devices in the network, which makes it harder to hack and interfere with your data.",
          show: false,
        },
        {
          question: "How is a decentralized network different from the traditional web?",
          answer: "Decentralization is a key aspect of PiperNet. Traditionally, to access data on a website, you needed to connect to a single server containing that data. This method of storing data makes it easy for third parties to get access to it, corrupt it or even delete it. In PiperNet it is securely stored on multiple devices in the network, which makes it harder to hack and interfere with your data.",
          show: false,
        },
      ]
    }
  },
  methods: {
    // to show and hid content
    ChangeStatus(numberQuestion) {
      let ListQuestions = this.$data.ListQuestions;
      ListQuestions[numberQuestion].show = !ListQuestions[numberQuestion].show;
    },
  },
}
</script>

<style scoped>
@import "../../assets/css/_askedQuestionScStyle.css";
</style>
