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
    NewTypeController: null,
    allTypeController: null,
    NewLocationController: null,
    allLocationController: null,
    TypeControllerData: null,
    LocationControllerData: null,
    NewControllerData: null,

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
        NewTypeController: (state) => {
            return state.NewTypeController;
        },
        allTypeController: (state) => {
            return state.allTypeController;
        },
        NewLocationController: (state) => {
            return state.NewLocationController;
        },
        allLocationController: (state) => {
            return state.allLocationController;
        },
        TypeControllerData: (state) => {
            return state.TypeControllerData;
        },
        LocationControllerData: (state) => {
            return state.LocationControllerData;
        },
        NewControllerData: (state) => {
            return state.NewControllerData;
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
        NewTypeController(context, NewTypeController) {
            context.commit('NewTypeController', NewTypeController);
        },
        allTypeController(context, allTypeController) {
            context.commit('allTypeController', allTypeController);
        },
        NewLocationController(context, NewLocationController) {
            context.commit('NewLocationController', NewLocationController);
        },
        allLocationController(context, allLocationController) {
            context.commit('allLocationController', allLocationController);
        },
        TypeControllerData(context, TypeControllerData) {
            context.commit('TypeControllerData', TypeControllerData);
        },
        LocationControllerData(context, LocationControllerData) {
            context.commit('LocationControllerData', LocationControllerData);
        },
        NewControllerData(context, NewControllerData) {
            context.commit('NewControllerData', NewControllerData);
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
        },

        NewTypeController(state, NewTypeController) {
            state.NewTypeController = NewTypeController;
        },
        allTypeController(state, allTypeController) {
            state.allTypeController = allTypeController;
        },
        NewLocationController(state, NewLocationController) {
            state.NewLocationController = NewLocationController;
        },
        allLocationController(state, allLocationController) {
            state.allLocationController = allLocationController;
        },
        TypeControllerData(state, TypeControllerData) {
            state.TypeControllerData = TypeControllerData;
        },
        LocationControllerData(state, LocationControllerData) {
            state.LocationControllerData = LocationControllerData;
        },
        NewControllerData(state, NewControllerData) {
            state.NewControllerData = NewControllerData;
        },
    }

});

export default store;
