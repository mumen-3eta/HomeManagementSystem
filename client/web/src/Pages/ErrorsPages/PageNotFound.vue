<template>
  <div class="content__404">
    <img alt="" class="content__body-404  " src="https://media.giphy.com/media/UoeaPqYrimha6rdTFV/giphy.gif">
    <h2 class="text-center  content__body-404 p-5"> {{ $t('404.NotFound') }} | <a href="javascript:void(0)"
                                                                                  @click.prevent="$router.go(-1)">
      {{ $t('404.goBack') }} </a></h2>
  </div>
</template>

<script>
export default {
  name: "PageNotFound",
}
</script>


<style scoped>
.content__404 {
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
  min-height: 100vh;
  margin: 0 auto;
  background: linear-gradient(103.72deg, #f1f1f1 0%, #f5f5f5 105.3%) no-repeat;
  width: 100%;
}

.content__body-404 {
  position: relative;
  text-align: center;
  width: 50%;
  margin: 10px auto;
  color: #21a7a4;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border-radius: 10px;
}

a {
  color: #21a7a4;
  transition: all 0.3s ease;
}

a:hover {
  color: #578557;
}


@media only screen and (max-width: 800px) {
  .content__404 {
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
    background: linear-gradient(103.72deg, #f1f1f1 0%, #f5f5f5 105.3%) no-repeat;
  }

  .content__body-404 {
    position: relative;
    text-align: center;
    width: 50%;
    margin: 10px auto;
    box-sizing: border-box;
    border-radius: 10px;
    padding: 0;
  }

  .content__404 h2 {
    font-size: 19px;
    width: 90%;
  }

  .content__404 img {
    width: 90%;
    height: auto;
  }
}
</style>
