<template>
  <div class="body-app">
    <div id="loadingScreen" :class="mode ==='dark'?'loadingScreen_dark':'loadingScreen'">
      <div :class="mode ==='dark'?'mainLoader_dark':'mainLoader'">
        <div class="loader">
          <div :class="mode ==='dark'?'loader__bar_dark':'loader__bar'"></div>
          <div :class="mode ==='dark'?'loader__bar_dark':'loader__bar'"></div>
          <div :class="mode ==='dark'?'loader__bar_dark':'loader__bar'"></div>
          <div :class="mode ==='dark'?'loader__bar_dark':'loader__bar'"></div>
          <div :class="mode ==='dark'?'loader__bar_dark':'loader__bar'"></div>
          <div :class="mode ==='dark'?'loader__ball_dark':'loader__ball'"></div>
        </div>
        <div :class=" lang ==='ar' ? 'direction-rtl' :' direction-ltr'">
          {{ $t('loading_Text') }}<span class="refresh">...</span></div>
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
  data() {
    return {
      lang: localStorage.getItem('lang') || 'en',
      mode: localStorage.getItem('mode') || 'dark',
    }
  },
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

.loadingScreen {
  position: fixed;
  width: 100%;
  height: 100vh;
  /*background: #fff url('https://media.giphy.com/media/feN0YJbVs0fwA/giphy.gif') no-repeat center;*/
  background: #fff no-repeat center;
  z-index: calc(99 * 999);
  display: flex;
  justify-content: center;
}

.mainLoader {
  font-size: 1.25rem;
  color: #3f9d8e;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.loader {
  position: relative;
  width: 75px;
  height: 100px;
}

.refresh {
  animation: Opacity 1.3s infinite;
}

@keyframes Opacity {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0.2;
  }
  100% {
    opacity: 1;
  }
}

.loader__bar {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 1%;
  background: #3f9d8e;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2)
}

.loader__bar:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #3f9d8e;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2)
}

.loader__bar:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #3f9d8e;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2)
}

.loader__bar:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #3f9d8e;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2)
}

.loader__bar:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #3f9d8e;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2)
}

.loader__bar:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #3f9d8e;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(0, 0, 0, 0.2)
}

.loader__bar:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__ball {
  position: absolute;
  bottom: 10px;
  left: 0;
  width: 10px;
  height: 10px;
  background: #32ab97;
  border-radius: 50%;
  -webkit-animation: ball 4s infinite;
  animation: ball 4s infinite
}

@-webkit-keyframes ball {
  0% {
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0)
  }
  5% {
    -webkit-transform: translate(8px, -14px);
    transform: translate(8px, -14px)
  }
  10% {
    -webkit-transform: translate(15px, -10px);
    transform: translate(15px, -10px)
  }
  17% {
    -webkit-transform: translate(23px, -24px);
    transform: translate(23px, -24px)
  }
  20% {
    -webkit-transform: translate(30px, -20px);
    transform: translate(30px, -20px)
  }
  27% {
    -webkit-transform: translate(38px, -34px);
    transform: translate(38px, -34px)
  }
  30% {
    -webkit-transform: translate(45px, -30px);
    transform: translate(45px, -30px)
  }
  37% {
    -webkit-transform: translate(53px, -44px);
    transform: translate(53px, -44px)
  }
  40% {
    -webkit-transform: translate(60px, -40px);
    transform: translate(60px, -40px)
  }
  50% {
    -webkit-transform: translate(60px, 0);
    transform: translate(60px, 0)
  }
  57% {
    -webkit-transform: translate(53px, -14px);
    transform: translate(53px, -14px)
  }
  60% {
    -webkit-transform: translate(45px, -10px);
    transform: translate(45px, -10px)
  }
  67% {
    -webkit-transform: translate(37px, -24px);
    transform: translate(37px, -24px)
  }
  70% {
    -webkit-transform: translate(30px, -20px);
    transform: translate(30px, -20px)
  }
  77% {
    -webkit-transform: translate(22px, -34px);
    transform: translate(22px, -34px)
  }
  80% {
    -webkit-transform: translate(15px, -30px);
    transform: translate(15px, -30px)
  }
  87% {
    -webkit-transform: translate(7px, -44px);
    transform: translate(7px, -44px)
  }
  90% {
    -webkit-transform: translate(0, -40px);
    transform: translate(0, -40px)
  }
  100% {
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0)
  }
}

@keyframes ball {
  0% {
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0)
  }
  5% {
    -webkit-transform: translate(8px, -14px);
    transform: translate(8px, -14px)
  }
  10% {
    -webkit-transform: translate(15px, -10px);
    transform: translate(15px, -10px)
  }
  17% {
    -webkit-transform: translate(23px, -24px);
    transform: translate(23px, -24px)
  }
  20% {
    -webkit-transform: translate(30px, -20px);
    transform: translate(30px, -20px)
  }
  27% {
    -webkit-transform: translate(38px, -34px);
    transform: translate(38px, -34px)
  }
  30% {
    -webkit-transform: translate(45px, -30px);
    transform: translate(45px, -30px)
  }
  37% {
    -webkit-transform: translate(53px, -44px);
    transform: translate(53px, -44px)
  }
  40% {
    -webkit-transform: translate(60px, -40px);
    transform: translate(60px, -40px)
  }
  50% {
    -webkit-transform: translate(60px, 0);
    transform: translate(60px, 0)
  }
  57% {
    -webkit-transform: translate(53px, -14px);
    transform: translate(53px, -14px)
  }
  60% {
    -webkit-transform: translate(45px, -10px);
    transform: translate(45px, -10px)
  }
  67% {
    -webkit-transform: translate(37px, -24px);
    transform: translate(37px, -24px)
  }
  70% {
    -webkit-transform: translate(30px, -20px);
    transform: translate(30px, -20px)
  }
  77% {
    -webkit-transform: translate(22px, -34px);
    transform: translate(22px, -34px)
  }
  80% {
    -webkit-transform: translate(15px, -30px);
    transform: translate(15px, -30px)
  }
  87% {
    -webkit-transform: translate(7px, -44px);
    transform: translate(7px, -44px)
  }
  90% {
    -webkit-transform: translate(0, -40px);
    transform: translate(0, -40px)
  }
  100% {
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0)
  }
}

@-webkit-keyframes barUp1 {
  0% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  40% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  50% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  90% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  100% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
}

@keyframes barUp1 {
  0% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  40% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  50% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  90% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  100% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
}

@-webkit-keyframes barUp2 {
  0% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  40% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  50% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  90% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  100% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
}

@keyframes barUp2 {
  0% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  40% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  50% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  90% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  100% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
}

@-webkit-keyframes barUp3 {
  0% {
    -webkit-transform: scale(1, 0.6);
    transform: scale(1, 0.6)
  }
  100% {
    -webkit-transform: scale(1, 0.6);
    transform: scale(1, 0.6)
  }
}

@keyframes barUp3 {
  0% {
    -webkit-transform: scale(1, 0.6);
    transform: scale(1, 0.6)
  }
  100% {
    -webkit-transform: scale(1, 0.6);
    transform: scale(1, 0.6)
  }
}

@-webkit-keyframes barUp4 {
  0% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  40% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  50% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  90% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  100% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
}

@keyframes barUp4 {
  0% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  40% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
  50% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  90% {
    -webkit-transform: scale(1, 0.4);
    transform: scale(1, 0.4)
  }
  100% {
    -webkit-transform: scale(1, 0.8);
    transform: scale(1, 0.8)
  }
}

@-webkit-keyframes barUp5 {
  0% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  40% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  50% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  90% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  100% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
}

@keyframes barUp5 {
  0% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  40% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
  50% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  90% {
    -webkit-transform: scale(1, 0.2);
    transform: scale(1, 0.2)
  }
  100% {
    -webkit-transform: scale(1, 1);
    transform: scale(1, 1)
  }
}


/* Dark Mode Theme*/

.loadingScreen_dark {
  position: fixed;
  width: 100%;
  height: 100vh;
  /*background: #fff url('https://media.giphy.com/media/feN0YJbVs0fwA/giphy.gif') no-repeat center;*/
  background: rgb(19, 19, 19) no-repeat center;
  z-index: calc(99 * 999);
  display: flex;
  justify-content: center;
}

.mainLoader_dark {
  font-size: 1.25rem;
  color: #c5dbd7;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.loader__bar_dark {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 1%;
  background: #c5dbd7;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(255, 255, 255, 0.2)
}

.loader__bar_dark:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar_dark:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar_dark:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar_dark:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar_dark:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar_dark {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #c5dbd7;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(255, 255, 255, 0.2)
}

.loader__bar_dark:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar_dark:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar_dark:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar_dark:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar_dark:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar_dark {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #c5dbd7;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(255, 255, 255, 0.2)
}

.loader__bar_dark:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar_dark:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar_dark:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar_dark:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar_dark:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar_dark {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #c5dbd7;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(255, 255, 255, 0.2)
}

.loader__bar_dark:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar_dark:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar_dark:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar_dark:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar_dark:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar_dark {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #c5dbd7;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(255, 255, 255, 0.2)
}

.loader__bar_dark:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar_dark:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar_dark:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar_dark:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar_dark:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__bar_dark {
  position: absolute;
  bottom: 0;
  width: 10px;
  height: 50%;
  background: #c5dbd7;
  -webkit-transform-origin: center bottom;
  -ms-transform-origin: center bottom;
  transform-origin: center bottom;
  box-shadow: 1px 1px 0 rgba(255, 255, 255, 0.2)
}

.loader__bar_dark:nth-child(1) {
  left: 0px;
  -webkit-transform: scale(1, .2);
  -ms-transform: scale(1, .2);
  transform: scale(1, .2);
  -webkit-animation: barUp1 4s infinite;
  animation: barUp1 4s infinite
}

.loader__bar_dark:nth-child(2) {
  left: 15px;
  -webkit-transform: scale(1, .4);
  -ms-transform: scale(1, .4);
  transform: scale(1, .4);
  -webkit-animation: barUp2 4s infinite;
  animation: barUp2 4s infinite
}

.loader__bar_dark:nth-child(3) {
  left: 30px;
  -webkit-transform: scale(1, .6);
  -ms-transform: scale(1, .6);
  transform: scale(1, .6);
  -webkit-animation: barUp3 4s infinite;
  animation: barUp3 4s infinite
}

.loader__bar_dark:nth-child(4) {
  left: 45px;
  -webkit-transform: scale(1, .8);
  -ms-transform: scale(1, .8);
  transform: scale(1, .8);
  -webkit-animation: barUp4 4s infinite;
  animation: barUp4 4s infinite
}

.loader__bar_dark:nth-child(5) {
  left: 60px;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1);
  -webkit-animation: barUp5 4s infinite;
  animation: barUp5 4s infinite
}

.loader__ball_dark {
  position: absolute;
  bottom: 10px;
  left: 0;
  width: 10px;
  height: 10px;
  background: #c5dbd7;
  border-radius: 50%;
  -webkit-animation: ball 4s infinite;
  animation: ball 4s infinite
}
</style>




