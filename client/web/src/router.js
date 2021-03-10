import Vue from 'vue';
import Router from 'vue-router'
import WelcomePage from "@/Pages/Site/WelcomePage";
import store from "@/vuex";
import PageNotFound from "@/Pages/ErrorsPages/PageNotFound";
import newLoginPage from "@/Pages/Site/newLoginPage";
import newRegisterPage from "@/Pages/Site/newRegisterPage";
import AdminCreateProcessorPage from "@/Pages/Admin/AdminCreateProcessorPage";
import AdminCreateControllerPage from "@/Pages/Admin/AdminCreateControllerPage";
import AdminAddTypeControllerPage from "@/Pages/Admin/AdminAddTypeControllerPage";
import AdminAddLocationControllerPage from "@/Pages/Admin/AdminAddLocationControllerPage";
import AdminMessagesPage from "@/Pages/Admin/AdminMessagesPage";
import MainLayout from "@/layout/Main_Layout/MainLayout";
import DashboardMain from "@/components/Dir_Main/DashboardMain";
import ProfileUserMain from "@/components/Dir_Main/ProfileUserMain";
import AddDevice from "@/components/Dir_Main/AddDevice";
import AddController from "@/components/Dir_Main/AddController";
import AllController from "@/components/Dir_Main/AllController";
import ShowControllerInfo from "@/components/Dir_Main/ShowControllerInfo";

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {path: '/', name: "WelcomePage", component: WelcomePage},
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
            name: "MainLayout",
            component: MainLayout,
            children: [
                {
                    path: '/',
                    name: "DashboardMain",
                    component: DashboardMain,
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
                    name: "ProfileUserMain",
                    component: ProfileUserMain,
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
                    name: "AddDevice",
                    component: AddDevice,
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
                    name: "AddController",
                    component: AddController,
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
                    path: '/v2/main/device/all/controller/:processor_id',
                    name: "AllController",
                    component: AllController,
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
                    path: '/v2/main/device/processor/:processor_id/controller/:controller_id',
                    name: "ShowControllerInfo",
                    component: ShowControllerInfo,
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
            ],
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
