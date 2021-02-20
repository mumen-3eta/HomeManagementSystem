import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const state = {
    user: null,
    TokenUser: null,
    deviceInfoAdd: [],
    processorId: null,
    controllerId: null,
    userProcessorIds: null,
    userAllProcessor: [],
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
        userAllProcessor: (state) => {
            return state.userAllProcessor;
        },
        deviceInfoAdd: (state) => {
            return state.deviceInfoAdd;
        },
        processorId: (state) => {
            return state.processorId;
        },
        controllerId: (state) => {
            return state.controllerId;
        },
        userProcessorIds: (state) => {
            return state.userProcessorIds;
        },
    },
    actions: {
        user(context, user) {
            context.commit('user', user);
        },
        TokenUser(context, TokenUser) {
            context.commit('TokenUser', TokenUser);
        },
        userAllProcessor(context, userAllProcessor) {
            context.commit('userAllProcessor', userAllProcessor);
        },
        deviceInfoAdd(context, deviceInfoAdd) {
            context.commit('deviceInfoAdd', deviceInfoAdd);
        },
        processorId(context, processorId) {
            context.commit('processorId', processorId);
        },
        controllerId(context, controllerId) {
            context.commit('controllerId', controllerId);
        },

        userProcessorIds(context, userProcessorIds) {
            context.commit('userProcessorIds', userProcessorIds);
        },
    },
    mutations: {
        user(state, user) {
            state.user = user;
        },
        TokenUser(state, TokenUser) {
            state.TokenUser = TokenUser;
        },
        userAllProcessor(state, userAllProcessor) {
            state.userAllProcessor = userAllProcessor;
        },
        deviceInfoAdd(state, deviceInfoAdd) {
            state.deviceInfoAdd = deviceInfoAdd;
        },
        processorId(state, processorId) {
            state.processorId = processorId;
        },
        controllerId(state, controllerId) {
            state.controllerId = controllerId;
        },

        userProcessorIds(state, userProcessorIds) {
            state.userProcessorIds = userProcessorIds;
        }
    }

});

export default store;
