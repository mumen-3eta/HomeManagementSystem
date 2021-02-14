import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const state = {
    user: {
        profileInfo: {},
        basicInfo: {},
    },
    TokenUser: null,
    deviceInfoAdd: [],
    processorId: null,
    // Image: null,
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
        TokenUser: (state) => {
            return state.TokenUser;
        },
        // Image: (state) => {
        //     return state.Image;
        // },
        deviceInfoAdd: (state) => {
            return state.deviceInfoAdd;
        },
        processorId: (state) => {
            return state.processorId;
        },
    },
    actions: {
        user(context, user) {
            context.commit('user', user);
        },
        TokenUser(context, TokenUser) {
            context.commit('TokenUser', TokenUser);
        },
        // Image(context, Image) {
        //     context.commit('Image', Image);
        // },
        deviceInfoAdd(context, deviceInfoAdd) {
            context.commit('deviceInfoAdd', deviceInfoAdd);
        },
        processorId(context, processorId) {
            context.commit('processorId', processorId);
        },
    },
    mutations: {
        user(state, user) {
            state.user = user;
        },
        TokenUser(state, TokenUser) {
            state.TokenUser = TokenUser;
        },
        // Image(state, Image) {
        //     state.Image = Image;
        // },
        deviceInfoAdd(state, deviceInfoAdd) {
            state.deviceInfoAdd = deviceInfoAdd;
        },
        processorId(state, processorId) {
            state.processorId = processorId;
        }
    }

});

export default store;
