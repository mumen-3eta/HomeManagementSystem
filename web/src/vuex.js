import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const state = {
    user: null,
    deviceInfoAdd: []
};

const store = new Vuex.Store({
    state,
    plugins: [new VuexPersistence().plugin],
    getters: {
        user: (state) => {
            return state.user;
        },
        GetUser: (state) => {
            return state.user;
        },
        deviceInfoAdd: (state) => {
            return state.deviceInfoAdd;
        }
    },
    actions: {
        user(context, user) {
            context.commit('user', user);
        },
        deviceInfoAdd(context, deviceInfoAdd) {
            context.commit('deviceInfoAdd', deviceInfoAdd);
        }
    },
    mutations: {
        user(state, user) {
            state.user = user;
        },
        deviceInfoAdd(state, deviceInfoAdd) {
            state.deviceInfoAdd = deviceInfoAdd;
        }
    }

});

export default store;
