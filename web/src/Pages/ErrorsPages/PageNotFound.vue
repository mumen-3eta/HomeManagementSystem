<template>
    <div class="content__404">
      <img class="content__body-404  " src="https://media.giphy.com/media/UoeaPqYrimha6rdTFV/giphy.gif" alt="">
      <h2 class="text-center  content__body-404 p-5"> Not Found | <a href="javascript:void(0)" @click.prevent="$router.go(-1)"> Go back </a></h2>
    </div>
</template>

<script>
export default {
name: "PageNotFound"
}
</script>


<style scoped>
.content__404{
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  flex-direction: column;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: center;
  align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  text-align: center;
  width: 80%;
  min-height: 100vh;
  margin: 0 auto;
  /*padding-top: 100px;*/
}
.content__body-404 {
  position: relative;
  text-align: center;
  width: 50%;
  margin: 10px auto;
  background: linear-gradient(103.72deg, #45A0F4 0%, #68DFA6 105.3%) no-repeat/cover center;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-box-shadow: 1px 1px 8px 1px #abeecd;
  box-shadow: 1px 1px 8px 1px #abeecd;
  border-radius: 10px;
}

/*.content__404 h2{*/
/*  font-size: 10px;*/
/*}*/

@media only screen and (max-width: 800px) {
  .content__404{
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    flex-direction: column;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: center;
    align-items: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    text-align: center;
    width: 100%;
    min-height: 100vh;
    margin: 0 auto;
    /*padding-top: 100px;*/
  }
  .content__body-404 {
    position: relative;
    text-align: center;
    width: 50%;
    margin: 10px auto;
    background: linear-gradient(103.72deg, #45A0F4 0%, #68DFA6 105.3%) no-repeat/cover center;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-box-shadow: 1px 1px 8px 1px #abeecd;
    box-shadow: 1px 1px 8px 1px #abeecd;
    border-radius: 10px;
    padding: 0;
  }
  .content__404 h2{
    font-size: 19px;
    width: 90%;
  }
  .content__404 img{
    width: 90%;
    height: auto;
  }
}
</style>
