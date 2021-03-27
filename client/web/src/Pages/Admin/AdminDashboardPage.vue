<template>
  <div>
    <!--   section number 1     -->
    <section class="section">
      <header class="section__header">
        <h2 class="section__title">Admin DashBoard | {{ user.user_name }}</h2>
      </header>
      <!--   for Admin user     -->
      <ul class="team">
        <li v-for="(DashboardInfoADMIN,i) in DashboardInfoCardSection1ForADMIN" :key="i" class="team__item">
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
              <p v-if="i===0 " class="team__name">{{ allTypeController ? allTypeController.length : '0' }}</p>
              <p v-if="i===1 " class="team__name">{{ allLocationController ? allLocationController.length : '0' }}</p>
              <p v-if="i===2" class="team__name">{{ Get_All_Users ? Get_All_Users.length : '0' }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </section><!-- End SECTION -->

    <!--   section number 2     -->
    <!--  For Admin   -->
    <section class="section section__mTop">
      <!--    Start hear  -->
      <ul class="team">
        <li v-for="(DashboardInfoADMIN,i) in DashboardInfoCardSection2ForADMIN" :key="i" class="team__item">
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
              <p v-if="i===0 " class="team__name">{{ All_Processor ? All_Processor.length : '0' }}</p>
              <p v-if="i===1 " class="team__name">{{ All_Controller ? All_Controller.length : '0' }}</p>
              <p v-if="i===2" class="team__name">0</p>
            </div>
          </router-link>
        </li>
      </ul>
      <!--   End hear   -->
    </section><!-- End SECTION -->
  </div>
</template>

<script>
import io from "socket.io-client";
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "AdminDashboardPage",
  data() {
    return {
      socket: '',
      DashboardInfoCardSection1ForADMIN: [
        {
          Path: '/v2/main/admin/create/controller/type',
          ClassColorBorder: 'photo__item photo__item-bg1Color',
          TitleCard: 'Number Type',
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
          Path: '/v2/main/admin/all/Users',
          ClassColorBorder: 'photo__item photo__item-bg3Color',
          TitleCard: 'Number User',
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
      DashboardInfoCardSection2ForADMIN: [
        {
          Path: '/v2/main/admin/create/processor',
          ClassColorBorder: 'photo__item photo__item-bg1Color',
          TitleCard: 'Number Processors',
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
            d_2: ' M45.6875,10.75c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-13.4375c-7.41162,0 -13.4375,6.02588 -13.4375,13.4375v8.0625h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v8.0625c0,7.41162 6.02588,13.4375 13.4375,13.4375h13.4375v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h13.4375c7.41162,0 13.4375,-6.02588 13.4375,-13.4375v-8.0625h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-8.0625c0,-7.41162 -6.02588,-13.4375 -13.4375,-13.4375h-13.4375v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM29.5625,26.875h112.875c4.44067,0 8.0625,3.62183 8.0625,8.0625v102.125c0,4.44067 -3.62183,8.0625 -8.0625,8.0625h-112.875c-4.44067,0 -8.0625,-3.62183 -8.0625,-8.0625v-102.125c0,-4.44067 3.62183,-8.0625 8.0625,-8.0625zM37.625,37.625c-2.96045,0 -5.375,2.41455 -5.375,5.375v86c0,2.96045 2.41455,5.375 5.375,5.375h96.75c2.96045,0 5.375,-2.41455 5.375,-5.375v-86c0,-2.96045 -2.41455,-5.375 -5.375,-5.375zM37.625,43h96.75v86h-96.75zM56.4375,53.75c-2.06811,0 -4.12573,0.78735 -5.70044,2.36206c-3.14941,3.14941 -3.14941,8.25147 0,11.40088c1.57471,1.57471 3.63233,2.36206 5.70044,2.36206c2.06811,0 4.12573,-0.78735 5.70044,-2.36206c3.14941,-3.14941 3.14941,-8.25147 0,-11.40088c-1.57471,-1.57471 -3.63233,-2.36206 -5.70044,-2.36206zM56.4375,59.125c0.69287,0 1.37524,0.26245 1.90015,0.78735c1.0498,1.0498 1.0498,2.75049 0,3.80029c-1.0498,1.0498 -2.75049,1.0498 -3.80029,0c-1.0498,-1.0498 -1.0498,-2.75049 0,-3.80029c0.5249,-0.5249 1.20728,-0.78735 1.90015,-0.78735zM67.1875,80.625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h43c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM67.1875,91.375c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h13.4375c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM91.375,91.375c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h5.375c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM45.6875,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM59.125,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM72.5625,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM86,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM99.4375,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM112.875,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM126.3125,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875z',
          },
        },
        {
          Path: '/v2/main/admin/create/controller',
          ClassColorBorder: 'photo__item photo__item-bg2Color',
          TitleCard: 'Number Controllers',
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
            d_2: 'M24.1875,21.5c-4.43017,0 -8.0625,3.63233 -8.0625,8.0625v18.8125h-2.6875c-7.42212,0 -13.4375,6.01538 -13.4375,13.4375v80.625c0,7.42212 6.01538,13.4375 13.4375,13.4375h32.25c7.42212,0 13.4375,-6.01538 13.4375,-13.4375v-13.4375h20.57617l-6.4563,21.5h-6.05737c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875c0,1.48022 1.19678,2.6875 2.6875,2.6875h59.125c1.49072,0 2.6875,-1.20728 2.6875,-2.6875c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875h-6.05737l-6.4563,-21.5h50.13867c4.43018,0 8.0625,-3.63232 8.0625,-8.0625v-91.375c0,-4.43017 -3.63232,-8.0625 -8.0625,-8.0625zM24.1875,26.875h139.75c1.48022,0 2.6875,1.20728 2.6875,2.6875v2.6875h-145.125v-2.6875c0,-1.48022 1.20728,-2.6875 2.6875,-2.6875zM21.5,37.625h145.125v32.25c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875v26.875h-56.21704c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875c0,1.48022 1.19678,2.6875 2.6875,2.6875h56.21704v8.0625c0,1.48022 -1.20728,2.6875 -2.6875,2.6875h-104.8125v-10.75h24.39746c1.51172,0 2.6875,-1.20728 2.6875,-2.6875c0,-1.48022 -1.17578,-2.6875 -2.6875,-2.6875h-24.39746v-26.875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875v-8.0625c0,-7.42212 -6.01538,-13.4375 -13.4375,-13.4375h-24.1875zM13.4375,53.75h32.25c4.43018,0 8.0625,3.63233 8.0625,8.0625v2.6875h-48.375v-2.6875c0,-4.43017 3.63233,-8.0625 8.0625,-8.0625zM26.875,56.4375c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875c0,1.48022 1.19678,2.6875 2.6875,2.6875h2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM5.375,69.875h48.375v59.125h-48.375zM72.5625,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM86,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM99.4375,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM112.875,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM126.3125,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM139.75,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM153.1875,69.875c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875v5.375c0,1.48022 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.20728 2.6875,-2.6875v-5.375c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM94.28296,107.5c-1.49072,0 -2.6875,1.20728 -2.6875,2.6875c0,1.48022 1.19678,2.6875 2.6875,2.6875h5.15454c1.49072,0 2.6875,-1.20728 2.6875,-2.6875c0,-1.48022 -1.19678,-2.6875 -2.6875,-2.6875zM85.31763,129h22.86474l6.4563,21.5h-35.77734zM5.375,134.375h48.375v8.0625c0,4.43018 -3.63232,8.0625 -8.0625,8.0625h-32.25c-4.43017,0 -8.0625,-3.63232 -8.0625,-8.0625zM29.5625,137.0625c-2.97095,0 -5.375,2.40405 -5.375,5.375c0,2.97095 2.40405,5.375 5.375,5.375c2.97095,0 5.375,-2.40405 5.375,-5.375c0,-2.97095 -2.40405,-5.375 -5.375,-5.375z',
          },
        },
        {
          Path: '/v2/main/admin/all/messages',
          ClassColorBorder: 'photo__item photo__item-bg3Color',
          TitleCard: 'Number Messages',
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
            d_2: 'M21.5,21.5v86h21.5v21.5h21.5v27.30542l34.13965,-27.30542h51.86035v-5.375v-80.625h-21.5v-21.5zM32.25,32.25h86v10.75h-75.25v53.75h-10.75zM53.75,53.75h86v64.5h-44.88965l-19.61035,15.68408v-15.68408h-21.5zM69.875,69.875v10.75h53.75v-10.75zM69.875,91.375v10.75h32.25v-10.75z',
          },
        },
      ],
    }
  },
  methods: {
    /* get All Controller Location */
    async GetAllLocations() {
      await axios.get('/api/v1/controller/location').then(async ({data: {locationLabels: response}}) => {
        const All_Location_Controller = response.map((item, i) => ({
          id: (++i).toString(),
          nameLocation: item.label,
          locationId: item.id,
          btn_Action: ''
        }))
        await this.$store.dispatch('allLocationController', All_Location_Controller);
        // this.rows = this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [];
      }).catch(() => {
        console.log("get Faild")
        // this.rows = this.$store.getters.allLocationController ? this.$store.getters.allLocationController : [];
      });
    },

    /*** Get All Type Controller  ***/
    async GetAllTypes() {
      await axios.get('/api/v1/controller/type').then(async ({data: {typeLabels: response}}) => {
        const All_Type_Controller = response.map((item, i) => ({
          id: (++i).toString(),
          nameType: item.label,
          labelId: item.id,
          btn_Action: ''
        }))
        await this.$store.dispatch('allTypeController', All_Type_Controller);
        // this.rows = this.$store.getters.allTypeController ? this.$store.getters.allTypeController : [];
      }).catch(() => {
        console.log("get Faild")
        // this.rows = this.$store.getters.allTypeController ? this.$store.getters.allTypeController : [];
      });
    },

    /* get All Controller  */
    async GetAllController() {
      await axios.get('/api/v1/controller/all').then(async ({data: {productionData: response}}) => {
        const All_Controller = response.map((item, i) => ({
          id: (++i).toString(),
          controllerID: item.id,
          Create_at: item.create_at,
          btn_Action: ''
        }))
        await this.$store.dispatch('All_Controller', All_Controller);
      }).catch(() => {
        console.log("get Faild")
      });
    },

    /*** Get All Processors  ***/
    async GetAllProcessors() {
      await axios.get('/api/v1/processor/all').then(async ({data: {productionData: response}}) => {
        const All_Processor = response.map((item, i) => ({
          id: (++i).toString(),
          processorID: item.id.toString(),
          Create_at: this.FormatDate(item.create_at),
          btn_Action: ''
        }))
        await this.$store.dispatch('All_Processor', All_Processor);
      }).catch(() => {
        console.log("get Faild")
      });
    },

    /*** Get All Processors  ***/
    async GetAllUsers() {
      await axios.get('/api/v1/users/profile/all').then(async ({data: {productionData: response}}) => {
        const Get_All_Users = response.map((item, i) => ({
          id: (++i).toString(),
          user_id: item.id ? item.id.toString() : item.id,
          userName: item.user_name ? item.user_name.toString() : item.user_name,
          Email: item.email ? item.email.toString() : item.email,
          firstName: item.first_name ? item.first_name.toString() : item.first_name,
          lastName: item.last_name ? item.last_name.toString() : item.last_name,
          Image: item.image ? item.image.toString() : item.image,
          Mobile: item.mobile ? item.mobile.toString() : item.mobile,
          IsAdmin: item.is_admin,
          Create_at: this.FormatDate(item.create_at),
          btn_Action: ''
        }))
        await this.$store.dispatch('Get_All_Users', Get_All_Users);
      }).catch(() => {
        console.log("get Get All Users Faild")
      });
    },

    FormatDate(data) {
      if (data) {
        let splitDate1 = data.split('T');
        let splitDate2 = splitDate1[0].split('-')
        return `${splitDate2[0]}/${splitDate2[1]}/${splitDate2[2]}`;
      } else {
        return ' ';
      }
    },

  },
  async beforeMount() {
    await this.GetAllLocations();
    await this.GetAllTypes();
    await this.GetAllController();
    await this.GetAllProcessors();
    await this.GetAllUsers();
  },
  created() {
    this.socket = io('http://localhost:3001');
  },
  computed: {
    ...mapGetters(['user', 'allLocationController', 'allTypeController', 'All_Controller', 'All_Processor', 'Get_All_Users']),
  },
}
</script>

<style scoped>

</style>
