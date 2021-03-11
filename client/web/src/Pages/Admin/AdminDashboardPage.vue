<template>
  <div>
    <!--   section number 1     -->
    <section class="section">
      <header class="section__header">
        <!--        v-if="TokenUser && user.basicInfo.isAdmin"-->
        <!--         {{ user.basicInfo.userName }}-->
        <h2 class="section__title">Admin DashBoard | admin</h2>
      </header>
      <!--   for Admin user     -->
      <ul class="team">
        <li v-for="(DashboardInfoADMIN,i) in DashboardInfoCardForADMIN" :key="i" class="team__item">
          <router-link :to="{path:DashboardInfoADMIN.Path}" class="team__link focus--box-shadow">
            <div class="team__header">
              <ul class="photo">
                <li :class="DashboardInfoADMIN.ClassColorBorder">
                  <span class="photo__substrate">
                       <svg :height="DashboardInfoADMIN.SVG.height" :style="DashboardInfoADMIN.SVG.Style"
                            :viewBox="DashboardInfoADMIN.SVG.viewBox"
                            :width="DashboardInfoADMIN.SVG.width" :x="DashboardInfoADMIN.SVG.x"
                            :xmlns="DashboardInfoADMIN.SVG.xmlns"
                            :y="DashboardInfoADMIN.SVG.y">
                         <g :fill="DashboardInfoADMIN.SVG.Fill_1" :fill-rule="DashboardInfoADMIN.SVG.fill_rule"
                            :font-family="DashboardInfoADMIN.SVG.font_family"
                            :font-size="DashboardInfoADMIN.SVG.font_size"
                            :font-weight="DashboardInfoADMIN.SVG.font_weight" :stroke="DashboardInfoADMIN.SVG.stroke"
                            :stroke-dasharray="DashboardInfoADMIN.SVG.stroke_dasharray"
                            :stroke-dashoffset="DashboardInfoADMIN.SVG.stroke_dashoffset"
                            :stroke-linecap="DashboardInfoADMIN.SVG.stroke_linecap"
                            :stroke-linejoin="DashboardInfoADMIN.SVG.stroke_linejoin"
                            :stroke-miterlimit="DashboardInfoADMIN.SVG.stroke_miterlimit"
                            :stroke-width="DashboardInfoADMIN.SVG.stroke_width"
                            :style="DashboardInfoADMIN.SVG.Style_mix" :text-anchor="DashboardInfoADMIN.SVG.text_anchor">
                           <path :d="DashboardInfoADMIN.SVG.d_1" :fill="DashboardInfoADMIN.SVG.Fill_2">
                           </path>
                           <g :fill="DashboardInfoADMIN.SVG.Fill_3">
                             <path
                                 :d="DashboardInfoADMIN.SVG.d_2"></path>
                           </g>
                         </g>
                       </svg>
                  </span>
                </li>
              </ul>
              <button class="setting setting--absolute foucs--box-shadow" type="button">
                <i class="fa fa-ellipsis-v"></i>
              </button>
            </div>
            <div class="team__inform">
              <p class="team__name">{{ DashboardInfoADMIN.TitleCard }}</p>
              <p v-if="TokenUser && userProcessorIds" class="team__name">{{ DashboardInfoADMIN.Number }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </section><!-- End SECTION -->

    <!--   section number 2     -->
    <!--  For Admin   -->
    <section class="section section__mTop">
      <header class="section__header">
        <h2 class="section__title">Un Know </h2>
        <div class="section__control">
          <button class="section__button  focus--box-shadow" type="button">
            <i class="fas fa-cog"></i>
          </button>
          <button class="section__button section__button--painted focus--box-shadow" type="button">
            <i class="fas fa-plus"></i>
          </button>
        </div>
      </header>
      <!--    Start hear  -->

      <!--   End hear   -->
    </section><!-- End SECTION -->
  </div>
</template>

<script>
import axios from "axios";
import io from "socket.io-client";
import {mapGetters} from "vuex";

export default {
  name: "AdminDashboardPage",
  data() {
    return {
      socket: '',
      DashboardInfoCardForADMIN: [
        {
          Path: '/v2/main/admin/create/controller/type',
          ClassColorBorder: 'photo__item photo__item-bg1Color',
          TitleCard: 'Number Type',
          Number: this.$store.getters.allTypeController ? this.$store.getters.allTypeController.length : '0',
          SVG: {
            height: '30',
            Style: 'fill:#000000;',
            viewBox: '0 0 172 172',
            width: '30',
            x: '0px',
            xmlns: 'http://www.w3.org/2000/svg',
            y: '0px',
            Fill_1: 'none',
            fill_rule: 'nonzero',
            font_family: 'none',
            font_size: 'none',
            font_weight: 'none',
            stroke: 'none',
            stroke_dasharray: '',
            stroke_dashoffset: '0',
            stroke_linecap: 'butt',
            stroke_linejoin: 'miter',
            stroke_miterlimit: '10',
            stroke_width: '1',
            Style_mix: 'mix-blend-mode: normal',
            text_anchor: 'none',
            d_1: 'M0,172v-172h172v172z',
            Fill_2: 'none',
            Fill_3: '#ffffff',
            d_2: 'M27.52,13.76c-7.60562,0 -13.76,6.15438 -13.76,13.76v30.96h6.88v-30.96c0,-4.09844 2.78156,-6.88 6.88,-6.88h116.96c4.09844,0 6.88,2.78156 6.88,6.88v51.6h6.88v-51.6c0,-7.60562 -6.15437,-13.76 -13.76,-13.76zM30.315,27.52c-1.63937,0.30906 -2.82187,1.76031 -2.795,3.44v27.52h6.88v-24.08h103.2v44.72h6.88v-48.16c0,-1.89469 -1.54531,-3.44 -3.44,-3.44h-110.08c-0.1075,0 -0.215,0 -0.3225,0c-0.1075,0 -0.215,0 -0.3225,0zM6.88,61.92c-1.80062,0 -3.64156,0.63156 -4.945,1.935c-1.30344,1.30344 -1.935,3.14438 -1.935,4.945v82.56c0,1.80063 0.63156,3.64156 1.935,4.945c1.30344,1.30344 3.14438,1.935 4.945,1.935h65.36c1.80063,0 3.64156,-0.63156 4.945,-1.935c1.30344,-1.30344 1.935,-3.14437 1.935,-4.945v-82.56c0,-1.80062 -0.63156,-3.64156 -1.935,-4.945c-1.30344,-1.30344 -3.14437,-1.935 -4.945,-1.935zM6.88,68.8h65.36v82.56h-65.36zM13.115,72.24c-1.63937,0.30906 -2.82187,1.76031 -2.795,3.44v61.92c0,1.89469 1.54531,3.44 3.44,3.44h51.6c1.89469,0 3.44,-1.54531 3.44,-3.44v-61.92c0,-1.89469 -1.54531,-3.44 -3.44,-3.44h-51.6c-0.1075,0 -0.215,0 -0.3225,0c-0.1075,0 -0.215,0 -0.3225,0zM17.2,79.12h44.72v55.04h-44.72zM127.28,82.56c-1.80062,0 -3.64156,0.63156 -4.945,1.935c-1.30344,1.30344 -1.935,3.14438 -1.935,4.945v61.92c0,1.80063 0.63156,3.64156 1.935,4.945c1.30344,1.30344 3.14438,1.935 4.945,1.935h37.84c1.80063,0 3.64156,-0.63156 4.945,-1.935c1.30344,-1.30344 1.935,-3.14437 1.935,-4.945v-61.92c0,-1.80062 -0.63156,-3.64156 -1.935,-4.945c-1.30344,-1.30344 -3.14437,-1.935 -4.945,-1.935zM127.28,89.44h37.84v61.92h-37.84zM133.515,92.88c-1.63937,0.30906 -2.82187,1.76031 -2.795,3.44v41.28c0,1.89469 1.54531,3.44 3.44,3.44h24.08c1.89469,0 3.44,-1.54531 3.44,-3.44v-41.28c0,-1.89469 -1.54531,-3.44 -3.44,-3.44h-24.08c-0.1075,0 -0.215,0 -0.3225,0c-0.1075,0 -0.215,0 -0.3225,0zM137.6,99.76h17.2v34.4h-17.2zM82.56,110.08v6.88h34.4v-6.88zM99.76,120.4v17.2h17.2v-6.88h-10.32v-3.44h10.32v-6.88zM82.56,141.04v6.88h34.4v-6.88zM39.56,142.76c-1.89469,0 -3.44,1.54531 -3.44,3.44c0,1.89469 1.54531,3.44 3.44,3.44c1.89469,0 3.44,-1.54531 3.44,-3.44c0,-1.89469 -1.54531,-3.44 -3.44,-3.44zM146.2,142.76c-1.89469,0 -3.44,1.54531 -3.44,3.44c0,1.89469 1.54531,3.44 3.44,3.44c1.89469,0 3.44,-1.54531 3.44,-3.44c0,-1.89469 -1.54531,-3.44 -3.44,-3.44z',
          },
        },
        {
          Path: '/v2/main/admin/create/controller/location',
          ClassColorBorder: 'photo__item photo__item-bg2Color',
          TitleCard: 'Number Location',
          Number: this.$store.getters.allLocationController ? this.$store.getters.allLocationController.length : '0',
          SVG: {
            height: '30',
            Style: 'fill:#000000;',
            viewBox: '0 0 172 172',
            width: '30',
            x: '0px',
            xmlns: 'http://www.w3.org/2000/svg',
            y: '0px',
            Fill_1: 'none',
            fill_rule: 'nonzero',
            font_family: 'none',
            font_size: 'none',
            font_weight: 'none',
            stroke: 'none',
            stroke_dasharray: '',
            stroke_dashoffset: '0',
            stroke_linecap: 'butt',
            stroke_linejoin: 'miter',
            stroke_miterlimit: '10',
            stroke_width: '1',
            Style_mix: 'mix-blend-mode: normal',
            text_anchor: 'none',
            d_1: 'M0,172v-172h172v172z',
            Fill_2: 'none',
            Fill_3: '#ffffff',
            d_2: 'M18.8125,1.34375c-9.675,0 -17.46875,7.79375 -17.46875,17.46875v39.77447c0,9.1375 6.85417,17.06668 15.8573,17.87293c6.04688,0.5375 11.5552,-2.0172 15.04895,-6.3172c0.80625,0.94062 1.74688,1.88177 2.6875,2.55365v46.8974c0,2.28437 1.74688,4.03125 4.03125,4.03125h31.9823c2.55312,0 4.43438,-2.28437 4.03125,-4.70312c-3.225,-16.52813 1.61145,-34.2651 14.37708,-47.1651c0.67188,-0.67187 1.34375,-1.2099 2.01563,-1.88178c1.6125,-1.34375 1.7474,-3.7625 0.53803,-5.375c-1.075,-1.47812 -1.74793,-3.35937 -1.74793,-5.375v-33.32605c0,-2.15 -1.61093,-4.16458 -3.76093,-4.29895c-2.28437,-0.13437 -4.30157,1.74687 -4.30157,4.03125v33.32605c0,4.8375 -3.49322,9.00208 -8.19635,9.67395c-5.77813,0.67188 -10.75,-3.7625 -10.75,-9.40625v-33.32605c0,-2.15 -1.61355,-4.16458 -3.76355,-4.29895c-2.28438,-0.13437 -4.29895,1.74687 -4.29895,4.03125v33.32605c0,4.8375 -3.49322,9.00208 -8.19635,9.67395c-5.64375,0.67188 -10.61615,-3.7625 -10.61615,-9.40625v-13.1698c0,-2.15 -1.61355,-4.16458 -3.76355,-4.29895c-2.28438,-0.13437 -4.29895,1.74687 -4.29895,4.03125v13.1698c0,4.8375 -3.49322,9.00208 -8.19635,9.67395c-5.64375,0.67188 -10.61615,-3.7625 -10.61615,-9.40625v-40.3125c0,-5.24062 4.16563,-9.40625 9.40625,-9.40625h134.375c5.24062,0 9.40625,4.16563 9.40625,9.40625v40.3125c0,2.01563 -0.67292,3.89635 -1.74792,5.50885c-1.20937,1.74688 -1.07448,4.03125 0.53802,5.375c1.74687,1.6125 4.4349,1.34323 5.91302,-0.53802c2.15,-2.95625 3.49323,-6.5828 3.49323,-10.47968v-40.17865c-0.13438,-9.675 -7.9276,-17.46875 -17.6026,-17.46875zM32.25,21.5c-2.2264,0 -4.03125,1.80485 -4.03125,4.03125c0,2.2264 1.80485,4.03125 4.03125,4.03125c2.2264,0 4.03125,-1.80485 4.03125,-4.03125c0,-2.2264 -1.80485,-4.03125 -4.03125,-4.03125zM112.6073,21.5c-2.15,0.13438 -3.76355,2.01458 -3.76355,4.29895v28.35522c0,2.55313 2.41822,4.4328 4.83697,3.76093c1.88125,-0.40313 3.22553,-2.01615 3.22553,-3.8974v-28.48645c0,-2.28438 -1.8802,-4.16562 -4.29895,-4.03125zM140.0177,21.5c-2.28438,-0.13437 -4.29895,1.74687 -4.29895,4.03125v6.71875v21.7677c0,1.88125 1.34427,3.49427 3.22552,3.8974c2.55312,0.5375 4.83698,-1.34427 4.83698,-3.8974v-21.7677v-6.45105c0,-2.15 -1.61355,-4.16458 -3.76355,-4.29895zM126.24426,64.53412c-11.35469,0 -22.70885,4.33202 -31.37604,12.99921c-17.2,17.2 -17.20052,45.42085 0.13385,62.62085l28.62292,28.75415c0.80625,0.80625 1.74635,1.2099 2.82135,1.2099c1.075,0 2.14948,-0.40365 2.82135,-1.2099l28.35522,-28.6203c17.33438,-17.33437 17.33438,-45.42032 0,-62.7547c-8.66719,-8.66719 -20.02397,-12.99921 -31.37866,-12.99921zM19.0802,81.96875c-2.28438,-0.13438 -4.29895,1.74688 -4.29895,4.03125v56.4375h-9.13855c-2.15,0 -4.16458,1.61355 -4.29895,3.76355c-0.13437,2.28438 1.74687,4.29895 4.03125,4.29895h79.01355c3.49375,0 5.23958,-4.16563 2.9552,-6.71875l-0.13385,-0.13385c-0.80625,-0.80625 -1.74845,-1.2099 -2.95782,-1.2099h-61.40833v-56.1698c0,-2.28438 -1.61355,-4.16458 -3.76355,-4.29895zM126.3125,94.73438c7.39063,0 13.4375,6.04688 13.4375,13.4375c0,7.39063 -6.04687,13.4375 -13.4375,13.4375c-7.39062,0 -13.4375,-6.04687 -13.4375,-13.4375c0,-7.39062 6.04688,-13.4375 13.4375,-13.4375zM166.625,142.4375c-2.2264,0 -4.03125,1.80485 -4.03125,4.03125c0,2.2264 1.80485,4.03125 4.03125,4.03125c2.2264,0 4.03125,-1.80485 4.03125,-4.03125c0,-2.2264 -1.80485,-4.03125 -4.03125,-4.03125zM32.25,162.59375c-2.28438,0 -4.03125,1.74687 -4.03125,4.03125c0,2.28438 1.74687,4.03125 4.03125,4.03125h67.1875c2.28437,0 4.03125,-1.74687 4.03125,-4.03125c0,-2.28438 -1.74688,-4.03125 -4.03125,-4.03125zM153.1875,162.59375c-2.28438,0 -4.03125,1.74687 -4.03125,4.03125c0,2.28438 1.74687,4.03125 4.03125,4.03125h13.4375c2.28438,0 4.03125,-1.74687 4.03125,-4.03125c0,-2.28438 -1.74687,-4.03125 -4.03125,-4.03125z',
          },
        },
        {
          Path: '#',
          ClassColorBorder: 'photo__item photo__item-bg3Color',
          TitleCard: 'Number User',
          Number: '20',
          SVG: {
            height: '30',
            Style: 'fill:#000000;',
            viewBox: '0 0 172 172',
            width: '30',
            x: '0px',
            xmlns: 'http://www.w3.org/2000/svg',
            y: '0px',
            Fill_1: 'none',
            fill_rule: 'nonzero',
            font_family: 'none',
            font_size: 'none',
            font_weight: 'none',
            stroke: 'none',
            stroke_dasharray: '',
            stroke_dashoffset: '0',
            stroke_linecap: 'butt',
            stroke_linejoin: 'miter',
            stroke_miterlimit: '10',
            stroke_width: '1',
            Style_mix: 'mix-blend-mode: normal',
            text_anchor: 'none',
            d_1: 'M0,172v-172h172v172z',
            Fill_2: 'none',
            Fill_3: '#ffffff',
            d_2: 'M54.13073,17.2c-31.2524,0.53893 -34.44622,22.21004 -28.17396,47.09844c-1.21547,0.69947 -3.21443,2.91979 -2.7099,6.86432c0.94027,7.35587 4.12925,9.21786 6.15885,9.37266c0.774,6.86853 8.1476,15.65272 10.7276,16.93125v11.46667c-5.73333,17.2 -40.13333,0 -40.13333,45.86667h68.8c0,-20.34187 11.40611,-29.10285 20.91771,-34.14245c-8.93253,-3.1304 -18.18291,-3.51982 -20.91771,-11.72422v-11.46667c2.58,-1.27853 9.94787,-10.05725 10.72761,-16.92005c0.45867,-1.62253 0.81745,-7.42717 0.81745,-8.73437c0,-7.16093 -0.10508,-21.96189 4.37839,-33.54896c-1.5652,-7.78587 -5.65665,-14.35224 -14.42292,-15.21797c-2.34493,-3.88147 -8.35526,-5.84531 -16.16979,-5.84531zM126.8388,22.93333c-6.44427,0 -11.42188,5.73333 -11.42188,5.73333c-36.93413,0 -16.43066,62.79917 -27.27812,71.82344c0,0 5.76746,8.54401 26.52786,8.54401v7.31224c-5.73333,15.52013 -34.4,9.78698 -34.4,38.45364h91.73333c0,-28.66667 -28.66667,-22.93351 -34.4,-38.45364v-7.37943c20.8636,0 25.69922,-8.26406 25.69922,-8.26406c-11.6788,-9.58613 8.69532,-77.76953 -36.46042,-77.76953z',
          },
        },
      ],
    }
  },
  methods: {
    async GetAllType() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const {data: {data: allTypeController}} = await axios.get('/api/v1/controller/type');
      const All_Type_Controller = allTypeController.map((item, i) => ({
        id: i,
        nameType: item.label,
        labelId: item.labelId,
        btn_Action: ''
      }))
      this.socket.emit("all_Type_Controller", All_Type_Controller);
      this.socket.on("all_Type_Controller_server", (data) => {
        this.$store.dispatch('allTypeController', data);

      });
      if (this.$store.getters.allLocationController) {
        this.DashboardInfoCardForADMIN[0].Number = this.$store.getters.allTypeController.length;
      } else {
        this.DashboardInfoCardForADMIN[0].Number = '0';
      }

    },
    async GetAllLocation() {
      axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');
      const {data: {data: allLocationController}} = await axios.get('/api/v1/controller/location');
      const All_Location_Controller = allLocationController.map((item, i) => ({
        id: i,
        nameLocation: item.label,
        locationId: item.locationId,
        btn_Action: ''
      }))
      this.socket.emit("all_Location_Controller", All_Location_Controller);
      this.socket.on("all_Location_Controller_server", (data) => {
        this.$store.dispatch('allLocationController', data);
      });
      if (this.$store.getters.allLocationController) {
        this.DashboardInfoCardForADMIN[1].Number = this.$store.getters.allLocationController.length;
      } else {
        this.DashboardInfoCardForADMIN[1].Number = '0';
      }
      // await this.$store.dispatch('allLocationController', All_Location_Controller);
    },
  },
  async beforeMount() {
    await this.GetAllType();
    await this.GetAllLocation();
  },
  created() {
    this.socket = io('http://localhost:3001');
    this.GetAllType();
    this.GetAllLocation();
  },
  computed: {
    ...mapGetters(['user', 'TokenUser', 'allTypeController', 'allLocationController']),
  },
}
</script>

<style scoped>

</style>
