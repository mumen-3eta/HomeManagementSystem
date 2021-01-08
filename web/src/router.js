import Vue from 'vue';
import Router from 'vue-router'
import WelcomePage from "@/Pages/Site/WelcomePage";
import AboutUsPage from "@/components/AboutUsPage";
import ContactUsPage from "@/components/ContactUsPage";
import LoginPage from "@/components/LoginPage";
import SignUpPage from "@/components/SignUpPage";
import ForgetPassword from "@/components/ForgetPassword";
import main from "@/components/MainPage/main";
import store from "@/vuex";
import ChangePasswordPage from "@/components/ChangePasswordPage";
import ProfileUser from "@/components/MainPage/ProfileUser";
import AddDevices from "@/components/MainPage/AddDevices";
import PageNotFound from "@/Pages/ErrorsPages/PageNotFound";
import DeviceInfo from "@/components/MainPage/DeviceInfo";
import EditInfoDevice from "@/components/MainPage/EditInfoDevice";
import MainHomePage from "@/Pages/Main/MainHomePage";
import MainProfilePage from "@/Pages/Main/MainProfilePage";
import newLoginPage from "@/Pages/Site/newLoginPage";
import newRegisterPage from "@/Pages/Site/newRegisterPage";

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {path: '/', name: "WelcomePage", component: WelcomePage},
        {path: '/aboutUs', name: "AboutUsPage", component: AboutUsPage},// need delete it
        {path: '/contactUs', name: "ContactUsPage", component: ContactUsPage},// need delete it
        {
            path: '/login',
            name: "LoginPage",
            component: LoginPage,
            // already be login
            beforeEnter: (to, from, next) => {
                if (store.getters['TokenUser']) {
                    return next({
                        name: "WelcomePage"
                    })
                }
                next()
            }

        },// need delete it
        {
            path: '/register',
            name: "SignUpPage",
            component: SignUpPage,
            // already be login
            beforeEnter: (to, from, next) => {
                if (store.getters['TokenUser']) {
                    return next({
                        name: "WelcomePage"
                    })
                }
                next()
            }
        },// need delete it
        {
            path: '/forgetPassword',
            name: "ForgetPassword",
            component: ForgetPassword,
            // already be login
            beforeEnter: (to, from, next) => {
                if (store.getters['TokenUser']) {
                    return next({
                        name: "WelcomePage"
                    })
                }

                next()
            }
        },// need delete it
        {
            path: '/ChangePassword',
            name: "ChangePasswordPage",
            component: ChangePasswordPage,
            // already be login
            beforeEnter: (to, from, next) => {
                if (store.getters['TokenUser']) {
                    return next({
                        name: "WelcomePage"
                    })
                }

                next()
            }
        },// need delete it
        {
            path: '/mainPage',
            name: "mainPage",
            component: main,
            // must be login
            beforeEnter: (to, from, next) => {
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "LoginPage"
                    })
                }
                next()
            }
        },// need delete it
        {
            path: '/mainPage/Profile/user',
            name: "ProfileUser",
            component: ProfileUser,
            // must be login
            beforeEnter: (to, from, next) => {
                // if (localStorage.getItem('token') == null){
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "LoginPage"
                    })
                }
                // }

                next()
            }
        },// need delete it
        {
            path: '/mainPage/add-device',
            name: "AddDevices",
            component: AddDevices,
            // must be login
            beforeEnter: (to, from, next) => {
                // if (localStorage.getItem('token') == null){
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "LoginPage"
                    })
                }
                // }

                next()
            }
        },// need delete it
        {
            path: '/mainPage/DeviceInfo/:Device_id',
            name: "DeviceInfo",
            component: DeviceInfo,
            // must be login
            beforeEnter: (to, from, next) => {
                // if (localStorage.getItem('token') == null){
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "LoginPage"
                    })
                }
                // }

                next()
            }
        },// need delete it
        {
            path: '/mainPage/DeviceInfo/:Device_id/edit',
            name: "EditDeviceInfo",
            component: EditInfoDevice,
            // must be login
            beforeEnter: (to, from, next) => {
                // if (localStorage.getItem('token') == null){
                if (!store.getters['TokenUser']) {
                    return next({
                        name: "LoginPage"
                    })
                }
                // }
                next()
            }
        },// need delete it


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
