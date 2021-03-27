<template>
  <div>
    <!--   section number 1     -->
    <section class="section">
      <header v-if="userProfile.user_name" class="section__header">
        <h2 class="section__title">DashBoard | {{ userProfile.user_name }}</h2>
      </header>
      <!--   for default user     -->
      <ul class="team">
        <li v-for="(DashboardInfo,index) in DashboardInfoCard" :key="index" class="team__item">
          <router-link :to="{path:DashboardInfo.Path}" class="team__link focus--box-shadow">
            <div class="team__header">
              <ul class="photo">
                <li :class="DashboardInfo.ClassColorBorder">
                    <span class="photo__substrate">
                         <svg :height="DashboardInfo.SVG.height" :style="DashboardInfo.SVG.Style"
                              :viewBox="DashboardInfo.SVG.viewBox"
                              :width="DashboardInfo.SVG.width" :x="DashboardInfo.SVG.x"
                              :xmlns="DashboardInfo.SVG.xmlns"
                              :y="DashboardInfo.SVG.y">
                           <g :fill="DashboardInfo.SVG.Fill_1" :fill-rule="DashboardInfo.SVG.fill_rule"
                              :font-family="DashboardInfo.SVG.font_family"
                              :font-size="DashboardInfo.SVG.font_size"
                              :font-weight="DashboardInfo.SVG.font_weight" :stroke="DashboardInfo.SVG.stroke"
                              :stroke-dasharray="DashboardInfo.SVG.stroke_dasharray"
                              :stroke-dashoffset="DashboardInfo.SVG.stroke_dashoffset"
                              :stroke-linecap="DashboardInfo.SVG.stroke_linecap"
                              :stroke-linejoin="DashboardInfo.SVG.stroke_linejoin"
                              :stroke-miterlimit="DashboardInfo.SVG.stroke_miterlimit"
                              :stroke-width="DashboardInfo.SVG.stroke_width"
                              :style="DashboardInfo.SVG.Style_mix" :text-anchor="DashboardInfo.SVG.text_anchor">
                             <path :d="DashboardInfo.SVG.d_1" :fill="DashboardInfo.SVG.Fill_2">
                             </path>
                             <g :fill="DashboardInfo.SVG.Fill_3">
                               <path
                                   :d="DashboardInfo.SVG.d_2"></path>
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
              <p class="team__name">{{ DashboardInfo.TitleCard }}</p>
              <p v-if="index === 0 " class="team__name">
                {{ userProcessorIds ? userProcessorIds.length : '0' }}</p>
              <p v-if="index === 1 " class="team__name">
                {{ userAllControllerConnected ? userAllControllerConnected.length : '0' }}</p>
              <p v-if="index === 2 " class="team__name">0</p>
            </div>
          </router-link>
        </li>
      </ul>
    </section><!-- End SECTION -->

    <!--   section number 2     -->
    <!--  For user  -->
    <section class="section section__mTop">
      <header class="section__header">
        <h2 class="section__title">All Controller</h2>
        <div class="section__control-search">
          <div class="searchInput">
            <label for="SearchID"></label>
            <input id="SearchID" v-model="search" placeholder="Search by name Or Type" type="search">
          </div>
          <button class="section__button section__button--painted focus--box-shadow" type="button">
            <i class="fa fa-search"></i>
          </button>
        </div>
      </header>
      <div class="Main__Card_FavoriteController">
        <div v-for="(Controller , index) in filteredList" :key="Controller.id"
             class="Card_container">
          <ul class="card_ListData">
            <!--            <router-link :to="{path : Controller.Path}">-->
            <li class="card_item">
              <i :class="[ Controller.Status  ? 'desktop_isActive' : '' , 'fas fa-desktop' ]"></i>
            </li>
            <li class="card_item">{{ Controller.ControllerName }}</li>
            <li class="card_item">{{ Controller.CreateAt | moment("DD MMM YYYY") }}</li>
            <!--            </router-link>-->
            <li class="card_item card_itemBTN">
              <button :class="[ Controller.IsActive  ? 'btn_Star-Favorite' : 'btn_Star' ]"
                      @click.prevent="ChangeFavorite(index)">
                <i :class="[Controller.IsActive ? 'fas fa-star' : 'far fa-star' ]"></i>
              </button>
              <div class="btn_Status">
                <div class="form-check form-switch">
                  <label for="flexSwitchCheckChecked"></label>
                  <input id="flexSwitchCheckChecked" :checked="Controller.Status ? 'checked' : '' "
                         class="form-check-input"
                         type="checkbox"
                         @change.prevent="ChangeActivation(Controller.id)">
                </div>
              </div>
            </li>
          </ul>

        </div>
      </div>
    </section><!-- End SECTION -->


  </div>
</template>

<script>
import io from "socket.io-client";
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "UserDashboard",
  data() {
    return {
      socket: '',
      search: '',
      search2: '',
      DashboardInfoCard: [
        {
          Path: '/v2/main/device/add',
          ClassColorBorder: 'photo__item photo__item-bg1Color',
          TitleCard: 'Number Processor',
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
            d_2: 'M45.6875,10.75c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-13.4375c-7.41162,0 -13.4375,6.02588 -13.4375,13.4375v8.0625h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v10.75h-8.0625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h8.0625v8.0625c0,7.41162 6.02588,13.4375 13.4375,13.4375h13.4375v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h10.75v8.0625c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-8.0625h13.4375c7.41162,0 13.4375,-6.02588 13.4375,-13.4375v-8.0625h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-10.75h8.0625c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875h-8.0625v-8.0625c0,-7.41162 -6.02588,-13.4375 -13.4375,-13.4375h-13.4375v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v8.0625h-10.75v-8.0625c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM29.5625,26.875h112.875c4.44067,0 8.0625,3.62183 8.0625,8.0625v102.125c0,4.44067 -3.62183,8.0625 -8.0625,8.0625h-112.875c-4.44067,0 -8.0625,-3.62183 -8.0625,-8.0625v-102.125c0,-4.44067 3.62183,-8.0625 8.0625,-8.0625zM37.625,37.625c-2.96045,0 -5.375,2.41455 -5.375,5.375v86c0,2.96045 2.41455,5.375 5.375,5.375h96.75c2.96045,0 5.375,-2.41455 5.375,-5.375v-86c0,-2.96045 -2.41455,-5.375 -5.375,-5.375zM37.625,43h96.75v86h-96.75zM56.4375,53.75c-2.06811,0 -4.12573,0.78735 -5.70044,2.36206c-3.14941,3.14941 -3.14941,8.25147 0,11.40088c1.57471,1.57471 3.63233,2.36206 5.70044,2.36206c2.06811,0 4.12573,-0.78735 5.70044,-2.36206c3.14941,-3.14941 3.14941,-8.25147 0,-11.40088c-1.57471,-1.57471 -3.63233,-2.36206 -5.70044,-2.36206zM56.4375,59.125c0.69287,0 1.37524,0.26245 1.90015,0.78735c1.0498,1.0498 1.0498,2.75049 0,3.80029c-1.0498,1.0498 -2.75049,1.0498 -3.80029,0c-1.0498,-1.0498 -1.0498,-2.75049 0,-3.80029c0.5249,-0.5249 1.20728,-0.78735 1.90015,-0.78735zM67.1875,80.625c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h43c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM67.1875,91.375c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h13.4375c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM91.375,91.375c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875c0,1.49072 1.19678,2.6875 2.6875,2.6875h5.375c1.49072,0 2.6875,-1.19678 2.6875,-2.6875c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM45.6875,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM59.125,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM72.5625,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM86,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM99.4375,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM112.875,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875zM126.3125,112.875c-1.49072,0 -2.6875,1.19678 -2.6875,2.6875v5.375c0,1.49072 1.19678,2.6875 2.6875,2.6875c1.49072,0 2.6875,-1.19678 2.6875,-2.6875v-5.375c0,-1.49072 -1.19678,-2.6875 -2.6875,-2.6875z',
          },
        },
        {
          Path: '/v2/main/controller/connected',
          ClassColorBorder: 'photo__item photo__item-bg2Color',
          TitleCard: 'Number Controller',
          Number: this.$store.getters.userAllControllerConnected ? this.$store.getters.userAllControllerConnected.length : '0',
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
            d_2: 'M27.95,23.65c-4.72832,0 -8.6,3.87168 -8.6,8.6v73.1c0,1.57051 0.46191,3.03184 1.20938,4.3h-9.80937v6.45c0,5.9125 4.8375,10.75 10.75,10.75h23.65v21.5c0,3.53574 2.91426,6.45 6.45,6.45h30.1c3.53574,0 6.45,-2.91426 6.45,-6.45v-4.3h45.15c3.53574,0 6.45,-2.91426 6.45,-6.45v-10.75h10.75c5.9125,0 10.75,-4.8375 10.75,-10.75v-6.45h-9.80938c0.74746,-1.26816 1.20938,-2.72949 1.20938,-4.3v-73.1c0,-4.72832 -3.87168,-8.6 -8.6,-8.6zM27.95,27.95h116.1c2.40195,0 4.3,1.89805 4.3,4.3v73.1c0,2.40195 -1.89805,4.3 -4.3,4.3h-4.3v-43c0,-3.53574 -2.91426,-6.45 -6.45,-6.45h-51.6c-3.53574,0 -6.45,2.91426 -6.45,6.45v21.5h-23.65c-3.53574,0 -6.45,2.91426 -6.45,6.45v15.05h-17.2c-2.40195,0 -4.3,-1.89805 -4.3,-4.3v-73.1c0,-2.40195 1.89805,-4.3 4.3,-4.3zM34.4,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM43,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM51.6,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM60.2,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM68.8,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM77.4,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM86,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM94.6,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM103.2,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM111.8,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM120.4,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM129,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM137.6,36.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM34.4,45.15c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM137.6,45.15c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM34.4,53.75c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM137.6,53.75c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM34.4,62.35c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM81.7,64.5h51.6c1.21777,0 2.15,0.93223 2.15,2.15v70.95c0,1.21777 -0.93223,2.15 -2.15,2.15h-45.15v-45.15c0,-3.53574 -2.91426,-6.45 -6.45,-6.45h-2.15v-21.5c0,-1.21777 0.93223,-2.15 2.15,-2.15zM34.4,70.95c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM34.4,79.55c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM34.4,88.15c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM51.6,92.45h30.1c1.21777,0 2.15,0.93223 2.15,2.15v53.75c0,1.21777 -0.93223,2.15 -2.15,2.15h-30.1c-1.21777,0 -2.15,-0.93223 -2.15,-2.15v-53.75c0,-1.21777 0.93223,-2.15 2.15,-2.15zM34.4,96.75c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM15.05,113.95h30.1v8.6h-23.65c-3.58613,0 -6.45,-2.86387 -6.45,-6.45zM139.75,113.95h17.2v2.15c0,3.58613 -2.86387,6.45 -6.45,6.45h-10.75zM107.5,131.15c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15zM66.65,141.9c-1.18418,0 -2.15,0.96582 -2.15,2.15c0,1.18418 0.96582,2.15 2.15,2.15c1.18418,0 2.15,-0.96582 2.15,-2.15c0,-1.18418 -0.96582,-2.15 -2.15,-2.15z',
          },
        },
        {
          Path: '#',
          ClassColorBorder: 'photo__item photo__item-bg3Color',
          TitleCard: 'Favorite Controller',
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
            d_2: 'M24.08,13.76c-5.6587,0 -10.32,4.6613 -10.32,10.32v13.45765c-0.12882,0.62781 -3.44,16.81185 -3.44,26.10235c0,4.74256 0.91525,9.28297 3.42656,12.90672c0.00458,0.00661 0.00885,0.01356 0.01344,0.02015v71.35313c0,5.68976 4.63024,10.32 10.32,10.32h82.62047c-2.42176,-2.07432 -4.77999,-4.36192 -6.94719,-6.88h-75.67328c-1.89544,0 -3.44,-1.54112 -3.44,-3.44v-66.22672c1.58576,0.55025 3.3132,0.86672 5.16,0.86672c4.59416,0 8.77956,-1.69639 12.04,-4.42765c3.26044,2.73126 7.44584,4.42765 12.04,4.42765c4.59416,0 8.77956,-1.69639 12.04,-4.42765c3.26044,2.73126 7.44584,4.42765 12.04,4.42765c4.72586,0 8.74254,-2.10804 12.04,-4.9786c3.29746,2.87056 7.31414,4.9786 12.04,4.9786c4.72586,0 8.74266,-2.10697 12.04,-4.9786c3.29734,2.87163 7.31414,4.9786 12.04,4.9786c4.59123,0 8.78079,-1.69224 12.04,-4.42094c3.25921,2.7287 7.44877,4.42094 12.04,4.42094c1.8468,0 3.57424,-0.31647 5.16,-0.86672v14.71406c2.37704,0.1376 4.68872,0.52186 6.88,1.18922v-21.02969c0.00459,-0.0066 0.00886,-0.01355 0.01344,-0.02015c2.51132,-3.62374 3.42656,-8.16416 3.42656,-12.90672c0,-9.2905 -3.31118,-25.47454 -3.44,-26.10235v-13.45765c0,-5.6587 -4.6613,-10.32 -10.32,-10.32zM24.08,20.64h123.84c1.9437,0 3.44,1.4963 3.44,3.44v10.32h-40.70219c-0.37149,-0.0614 -0.75054,-0.0614 -1.12203,0h-22.95797c-0.37149,-0.0614 -0.75054,-0.0614 -1.12203,0h-23.005c-0.19315,-0.03228 -0.3887,-0.04802 -0.58453,-0.04703c-0.16665,0.00361 -0.33282,0.01933 -0.49719,0.04703h-40.72906v-10.32c0,-1.9437 1.4963,-3.44 3.44,-3.44zM27.52,24.08c-1.89986,0 -3.44,1.54014 -3.44,3.44c0,1.89986 1.54014,3.44 3.44,3.44c1.89986,0 3.44,-1.54014 3.44,-3.44c0,-1.89986 -1.54014,-3.44 -3.44,-3.44zM37.84,24.08c-1.89986,0 -3.44,1.54014 -3.44,3.44c0,1.89986 1.54014,3.44 3.44,3.44c1.89986,0 3.44,-1.54014 3.44,-3.44c0,-1.89986 -1.54014,-3.44 -3.44,-3.44zM48.16,24.08c-1.89986,0 -3.44,1.54014 -3.44,3.44c0,1.89986 1.54014,3.44 3.44,3.44c1.89986,0 3.44,-1.54014 3.44,-3.44c0,-1.89986 -1.54014,-3.44 -3.44,-3.44zM20.20328,41.28h14.19672v30.59047c-2.19826,2.31776 -5.17671,3.80953 -8.6,3.80953c-3.34441,0 -5.02466,-1.07788 -6.39625,-3.05703c-1.37159,-1.97915 -2.20375,-5.17712 -2.20375,-8.98297c0,-6.78558 2.41479,-19.22465 3.00328,-22.36zM41.28,41.28h17.2v30.59047c-2.19826,2.31776 -5.17671,3.80953 -8.6,3.80953c-3.42329,0 -6.40174,-1.49178 -8.6,-3.80953zM65.36,41.28h17.2v30.44265c-2.20371,2.35713 -5.14372,3.95735 -8.6,3.95735c-3.45628,0 -6.39629,-1.60022 -8.6,-3.95735zM89.44,41.28h17.2v30.44265c-2.20055,2.3552 -5.14372,3.95735 -8.6,3.95735c-3.45628,0 -6.39629,-1.60022 -8.6,-3.95735zM113.52,41.28h17.2v30.44265c-2.20055,2.3552 -5.14372,3.95735 -8.6,3.95735c-3.45628,0 -6.39945,-1.60215 -8.6,-3.95735zM137.6,41.28h14.19672c0.58849,3.13535 3.00328,15.57442 3.00328,22.36c0,3.80584 -0.83216,7.00382 -2.20375,8.98297c-1.37159,1.97916 -3.05184,3.05703 -6.39625,3.05703c-3.42329,0 -6.40455,-1.49337 -8.6,-3.80953zM118.68,103.2c-12.384,0 -22.36,9.97735 -22.36,22.01735c0,18.92 17.19731,30.27334 28.20531,37.49734c3.096,2.064 5.50937,3.44 7.22937,5.16l2.06266,1.72l2.06265,-1.72c1.72,-1.376 4.47065,-3.096 7.22265,-5.16c11.696,-7.568 28.89735,-18.57734 28.89735,-37.49734c0,-12.04 -9.976,-22.01735 -22.36,-22.01735c-5.848,0 -11.352,2.41069 -15.48,6.19469c-4.128,-3.784 -9.632,-6.19469 -15.48,-6.19469zM118.68,110.08c5.16,0 9.62931,2.408 12.72531,6.88l2.75469,4.12531l2.75469,-4.12531c2.752,-4.128 7.56531,-6.88 12.72531,-6.88c8.6,0 15.48,6.88135 15.48,15.13735c0,15.136 -14.45069,24.76531 -25.11469,31.64531c-2.408,1.376 -4.12531,2.75066 -5.84531,3.78266c-1.72,-1.376 -3.43731,-2.40666 -5.84531,-3.78266c-10.664,-6.88 -25.11469,-16.50931 -25.11469,-31.64531c0,-8.256 6.88,-15.13735 15.48,-15.13735z',
          },
        },
      ],
      ListCardController: [
        {
          ControllerName: 'Controller Name #1',
          TypeController: 'Type Controller #111',
          Path: '/v2/main/device/processor/6029b5e176c08c314041f39f/controller/185w5158d6wa185w6418fe15fe',
          Status: false,
          IsActive: false
        },
        {
          ControllerName: 'Controller Name #2',
          TypeController: 'Type Controller #222',
          Path: '/v2/main/device/processor/6029b5e176c08c314041f39f/controller/185w5158d6wa185w6418fe15fe',
          Status: true,
          IsActive: true
        },
        {
          ControllerName: 'Controller Name #2',
          TypeController: 'Type Controller #2',
          Path: '/v2/main/device/processor/6029b5e176c08c314041f39f/controller/185w5158d6wa185w6418fe15fe',
          Status: false,
          IsActive: true
        },
        {
          ControllerName: 'Controller Name #3',
          TypeController: 'Type Controller #3',
          Path: '/v2/main/device/processor/6029b5e176c08c314041f39f/controller/185w5158d6wa185w6418fe15fe',
          Status: false,
          IsActive: false
        },
        {
          ControllerName: 'Controller Name #4',
          TypeController: 'Type Controller #4',
          Path: '/v2/main/device/processor/6029b5e176c08c314041f39f/controller/185w5158d6wa185w6418fe15fe',
          Status: true,
          IsActive: true
        },
        {
          ControllerName: 'Controller Name #5',
          TypeController: 'Type Controller #5',
          Path: '/v2/main/device/processor/6029b5e176c08c314041f39f/controller/185w5158d6wa185w6418fe15fe',
          Status: true,
          IsActive: false
        },
      ],
    }
  },
  methods: {
    ChangeFavorite(id) {
      this.$store.getters.userAllControllerConnected[id].IsActive = !this.$store.getters.userAllControllerConnected[id].IsActive;
    },
    async ChangeActivation(id) {
      await axios.post('/api/v1/controller/change', {
        newStatus: '',
        controllerId: id
      }).then(async ({data: {controllerStatus: status}}) => {
        if (status[0].status) {
          this.$swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Active this Controller, Successfully',
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        } else {
          this.$swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Not Active this Controller, Successfully',
            toast: false,
            showConfirmButton: false,
            timer: 1500
          })
        }
        await this.GetAllControllerConnected();
      }).catch(async () => {
        this.$swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Change Status, Faild',
          toast: false,
          showConfirmButton: false,
          timer: 1500
        })
        await this.GetAllControllerConnected();
      });
      // this.$store.getters.userAllControllerConnected[id].Status = !this.$store.getters.userAllControllerConnected[id].Status;
    },
    /*** ---------------- Get All Controller Connected With Processor ---------------- ***/
    async GetAllControllerConnected() {
      await axios.get('/api/v1/controller/connection/all').then(async ({data: {connectionData: response}}) => {
        const userAllControllerConnected = response.map((item) => ({
          id: item.controller_id.toString(),
          ControllerName: item.name.toString(),
          CreateAt: item.create_at.toString(),
          Status: item.status,
          IsActive: true
        }))
        await this.$store.dispatch('userAllControllerConnected', userAllControllerConnected);
      }).catch(async () => {
        console.log("faild get All Dashboard")
        await this.$store.dispatch('userAllControllerConnected', null);
      });
    },
    /*** ---------------- End Get All Controller Connected With Processor ---------------- ***/
    /*** ------ Get All Connection For processors to this user -------- ***/
    async GetAllProcessorUser() {
      await axios.get('/api/v1/processor/connection').then(async ({data: {connectionData: response}}) => {
        await this.$store.dispatch('userProcessorIds', response);
      }).catch(async () => {
        console.log("faild get All connection")
        await this.$store.dispatch('userProcessorIds', null);
      });
    }
    /*** -------- End  Get All Connection For processors to this user ------ ***/
  },
  async beforeMount() {
    await this.GetAllProcessorUser();
    await this.GetAllControllerConnected();
  },
  created() {
    this.socket = io('http://localhost:3001');
    // this.GetAllProcessorUser();
  },
  computed: {
    ...mapGetters(['user', 'userProcessorIds', 'userProfile', 'userAllControllerConnected']),
    filteredList() {
      if (this.$store.getters.userAllControllerConnected) {
        return this.$store.getters.userAllControllerConnected.filter(post => {
          return post.ControllerName.toLowerCase().includes(this.search.toLowerCase()) || post.TypeController.toLowerCase().includes(this.search.toLowerCase())
        })
      } else {
        return [];
      }


    },
    // filteredList2() {
    //   return this.ListCardController.filter(post => {
    //     return post.ControllerName.toLowerCase().includes(this.search2.toLowerCase()) || post.TypeController.toLowerCase().includes(this.search2.toLowerCase())
    //   })
    // },
  },
}
</script>

<style lang="scss" scoped>
a {
  &:hover {
    color: black;
  }
}

section {
  .section__header {
    width: 100%;

    .section__title {
      width: 100%;
    }

    .section__control-search {
      display: flex;
      width: 100%;

      .section__title {
        width: 100%;
      }

      .searchInput {
        width: 100%;

        input {
          padding: 0.85rem 0.6rem;
          font-size: 1.25rem;
          border: none;
          width: 100%;
          border-radius: 10px 0 0 10px;

          &:focus {
            outline: none;

          }
        }
      }

      .section__button {
        margin: 0;
        border-radius: 0 10px 10px 0;
        color: white;
      }
    }
  }

  .Main__Card_FavoriteController {
    width: 100%;
    height: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: stretch;
    padding: 0.25rem 0;
    margin: 0 auto;
    font-family: Ubuntu, Arial, Helvetica, sans-serif;


    .Card_container {
      width: 340px;
      height: auto;
      display: flex;
      //flex-wrap: wrap;
      //flex-grow: 1;
      flex-direction: column;
      padding: 1.9rem 0;
      margin: 0.4rem;
      background-color: white;
      border-radius: 15px;
      box-shadow: 2px 2px 2px 2px #dbcbbe;
      position: relative;

      .card_ListData {
        .card_item {
          font-size: 1.2rem;
          margin: 0.25rem auto;
          padding: 0.25rem 0;
          display: flex;
          justify-content: center;
          align-items: center;
          justify-items: center;
          text-align: center;

          &.card_itemBTN {
            display: flex;
            justify-content: space-between;
            justify-items: center;
            width: 95%;
            position: absolute;
            top: 10px;
            z-index: 99;
          }

          .btn_Star {
            margin-left: 1rem;
            background-color: #c6c6c6;
            color: white;
            border-radius: 5px;
            font-size: 1.2rem;
          }

          .fas {
            &.fa-desktop {
              font-size: 10rem;
              color: #556764;
            }

            &.desktop_isActive {
              font-size: 10rem;
              color: #359f84;
            }
          }


          .btn_Star-Favorite {
            margin-left: 1rem;
            background-color: #f1f1f1;
            color: white;
            border-radius: 5px;
            font-size: 1.2rem;

            .fas {
              &.fa-star {
                color: #efbf15;
              }
            }
          }
        }

      }
    }
  }
}

button {
  padding: 0.6rem;
  border: 0;
  cursor: pointer;
  transition: color 0.3s ease, background-color 0.3s ease;
}

@media screen and (max-width: 410px) {
  section {
    .section__header {
      width: 100%;

      .section__title {
        width: 100%;
        font-size: 1.1rem;
      }

      .section__control-search {
        display: flex;
        width: 85%;

        .section__title {
          width: 100%;
        }

        .searchInput {
          width: 100%;

          input {
            padding: 0.5rem 0.6rem;
            font-size: 1.25rem;
            border: none;
            width: 100%;

            &:focus {
              outline: none;
            }
          }
        }

        .section__button {
          padding: 0.5rem 0.6rem;
        }
      }
    }
  }
}
</style>
