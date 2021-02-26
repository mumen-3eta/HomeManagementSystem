import Vue from 'vue';
import Router from 'vue-router'
import WelcomePage from "@/Pages/Site/WelcomePage";
import store from "@/vuex";
import PageNotFound from "@/Pages/ErrorsPages/PageNotFound";
import MainHomePage from "@/Pages/Main/MainHomePage";
import MainProfilePage from "@/Pages/Main/MainProfilePage";
import newLoginPage from "@/Pages/Site/newLoginPage";
import newRegisterPage from "@/Pages/Site/newRegisterPage";
import AddDevicePage from "@/Pages/Main/AddDevicePage";
import AddControllerPage from "@/Pages/Main/AddControllerPage";
import AdminCreateProcessorPage from "@/Pages/Admin/AdminCreateProcessorPage";
import AdminCreateControllerPage from "@/Pages/Admin/AdminCreateControllerPage";
import AdminAddTypeControllerPage from "@/Pages/Admin/AdminAddTypeControllerPage";
import AdminAddLocationControllerPage from "@/Pages/Admin/AdminAddLocationControllerPage";
import testGame from "@/Pages/testSocketIo/testGame";
import AdminMessagesPage from "@/Pages/Admin/AdminMessagesPage";

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {path: '/', name: "WelcomePage", component: WelcomePage},
        {path: '/game', name: "testGame", component: testGame},
        // {
        //     path: '/mainPage/DeviceInfo/:Device_id',
        //     name: "DeviceInfo",
        //     component: DeviceInfo,
        //     // must be login
        //     beforeEnter: (to, from, next) => {
        //         // if (localStorage.getItem('token') == null){
        //         if (!store.getters['TokenUser']) {
        //             return next({
        //                 name: "LoginPage"
        //             })
        //         }
        //         // }
        //
        //         next()
        //     }
        // },// need delete it
        // {
        //     path: '/mainPage/DeviceInfo/:Device_id/edit',
        //     name: "EditDeviceInfo",
        //     component: EditInfoDevice,
        //     // must be login
        //     beforeEnter: (to, from, next) => {
        //         // if (localStorage.getItem('token') == null){
        //         if (!store.getters['TokenUser']) {
        //             return next({
        //                 name: "LoginPage"
        //             })
        //         }
        //         // }
        //         next()
        //     }
        // },// need delete it


        // new Code here
        {
            path: '/v2/login',
            name: "newLoginPage",
            component: newLoginPage,
            // already be login
            beforeEnter: (to, from, next) => {
                if (store.getters['TokenUser']) {
                    return next({
                        name: "WelcomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/register',
            name: "newRegisterPage",
            component: newRegisterPage,
            // already be login
            beforeEnter: (to, from, next) => {
                if (store.getters['TokenUser']) {
                    return next({
                        name: "WelcomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/home',
            name: "MainHomePage",
            component: MainHomePage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/profile',
            name: "MainProfilePage",
            component: MainProfilePage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/device/add',
            name: "AddDevicePage",
            component: AddDevicePage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/device/create/controller/:processor_id',
            name: "AddControllerPage",
            component: AddControllerPage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },

        /* Main Admin || email:mainadmin@hmsy.test || password : mainadmin@hmsy.test */
        {
            path: '/v2/main/admin/create/processor',
            name: "AdminCreateProcessorPage",
            component: AdminCreateProcessorPage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (!store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/admin/create/controller',
            name: "AdminCreateControllerPage",
            component: AdminCreateControllerPage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (!store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/admin/create/controller/type',
            name: "AdminAddTypeControllerPage",
            component: AdminAddTypeControllerPage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (!store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/admin/create/controller/location',
            name: "AdminAddLocationControllerPage",
            component: AdminAddLocationControllerPage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (!store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },
        {
            path: '/v2/main/admin/all/messages',
            name: "AdminMessagesPage",
            component: AdminMessagesPage,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "newRegisterPage"
                    })
                }
                if (!store.getters['user'].basicInfo.isAdmin) {
                    return next({
                        name: "MainHomePage"
                    })
                }
                next()
            }
        },

        /*
            Must Be Last
            error page 404
        */
        {
            path: '/*',
            name: 'PageNotFound',
            component: PageNotFound,

        },
    ]
})
