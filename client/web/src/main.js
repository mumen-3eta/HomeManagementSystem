import Vue from 'vue'
import App from './App.vue'
import router from "@/router";
import Vuelidate from 'vuelidate';
import './axios'
import store from "@/vuex";
import firebase from 'firebase/app';
import 'firebase/storage';
import '@/animateCss'
import '@/sweetalert2'
import vModal from 'vue-js-modal'

Vue.use(vModal)
Vue.use(Vuelidate)

// Initialize Firebase
firebase.initializeApp({
    apiKey: "AIzaSyDhJ6gHaXmYf6tYi4rxftU561gIQooLS3s",
    authDomain: "hmsy-test.firebaseapp.com",
    databaseURL: "https://hmsy-test.firebaseio.com",
    projectId: "hmsy-test",
    storageBucket: "hmsy-test.appspot.com",
    messagingSenderId: "855951027335",
    appId: "1:855951027335:web:677119ddb49d67ba701c22",
    measurementId: "G-6DC38MY0D7"
});


Vue.config.productionTip = false
new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')
