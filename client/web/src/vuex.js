import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const state = {
    /* used it */
    user: null,
    userProfile: null,
    TokenUser: null,
    userAllControllerConnectedWithProcessor: null,
    userAllControllerConnected: null,
    AllControllerConnectedForThisUser: null,
    userProcessorIds: null,
    allMessages: null,
    TypeControllerData: null,
    LocationControllerData: null,
    userAllProcessor: [],
    allTypeController: null,
    allLocationController: null,
    All_Controller: null,
    All_Processor: null,
    Get_All_Users: null,
    /* End  used it */

    /*** Check it, if i not used delete them with (getters,action,mutations)***/
    deviceInfoAdd: [],
    processorId: null,
    controllerId: null,
    NewTypeController: null,
    NewLocationController: null,
    NewControllerData: null,

};

const store = new Vuex.Store({
    state,
    plugins: [new VuexPersistence().plugin],
    getters: {
        user: (state) => {
            return state.user;
        },
        userProfile: (state) => {
            return state.userProfile;
        },
        GetUser: (state) => {
            return state.user;
        },
        userAllControllerConnectedWithProcessor: (state) => {
            return state.userAllControllerConnectedWithProcessor;
        },
        userAllControllerConnected: (state) => {
            return state.userAllControllerConnected;
        },
        AllControllerConnectedForThisUser: (state) => {
            return state.AllControllerConnectedForThisUser;
        },
        All_Controller: (state) => {
            return state.All_Controller;
        },
        All_Processor: (state) => {
            return state.All_Processor;
        },
        Get_All_Users: (state) => {
            return state.Get_All_Users;
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
        allMessages: (state) => {
            return state.allMessages;
        },
    },
    actions: {
        user(context, user) {
            context.commit('user', user);
        },
        userProfile(context, userProfile) {
            context.commit('userProfile', userProfile);
        },
        TokenUser(context, TokenUser) {
            context.commit('TokenUser', TokenUser);
        },
        userAllControllerConnectedWithProcessor(context, userAllControllerConnectedWithProcessor) {
            context.commit('userAllControllerConnectedWithProcessor', userAllControllerConnectedWithProcessor);
        },
        userAllControllerConnected(context, userAllControllerConnected) {
            context.commit('userAllControllerConnected', userAllControllerConnected);
        },
        AllControllerConnectedForThisUser(context, AllControllerConnectedForThisUser) {
            context.commit('AllControllerConnectedForThisUser', AllControllerConnectedForThisUser);
        },
        All_Controller(context, All_Controller) {
            context.commit('All_Controller', All_Controller);
        },
        All_Processor(context, All_Processor) {
            context.commit('All_Processor', All_Processor);
        },
        Get_All_Users(context, Get_All_Users) {
            context.commit('Get_All_Users', Get_All_Users);
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
        allMessages(context, allMessages) {
            context.commit('allMessages', allMessages);
        },
    },
    mutations: {
        user(state, user) {
            state.user = user;
        },
        userProfile(state, userProfile) {
            state.userProfile = userProfile;
        },
        TokenUser(state, TokenUser) {
            state.TokenUser = TokenUser;
        },
        userAllControllerConnectedWithProcessor(state, userAllControllerConnectedWithProcessor) {
            state.userAllControllerConnectedWithProcessor = userAllControllerConnectedWithProcessor;
        },
        userAllControllerConnected(state, userAllControllerConnected) {
            state.userAllControllerConnected = userAllControllerConnected;
        },
        AllControllerConnectedForThisUser(state, AllControllerConnectedForThisUser) {
            state.AllControllerConnectedForThisUser = AllControllerConnectedForThisUser;
        },
        userAllProcessor(state, userAllProcessor) {
            state.userAllProcessor = userAllProcessor;
        },
        All_Controller(state, All_Controller) {
            state.All_Controller = All_Controller;
        },
        All_Processor(state, All_Processor) {
            state.All_Processor = All_Processor;
        },
        Get_All_Users(state, Get_All_Users) {
            state.Get_All_Users = Get_All_Users;
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
        allMessages(state, allMessages) {
            state.allMessages = allMessages;
        },
    }

});

export default store;
