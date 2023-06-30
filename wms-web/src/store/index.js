import Vue from 'vue'
import Vuex from 'vuex'
import router,{resetRouter} from "@/router";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

function addNewRoute(menuList) {
    console.log(menuList)
    let routes = router.options.routes
    console.log(routes)
    routes.forEach(routeItem=>{
        if(routeItem.path==="/Index"){
            menuList.forEach(menu=>{
                let childRoute =  {
                    path:'/'+menu.menuClick,
                    name:menu.menuName,
                    meta:{
                        title:menu.menuName
                    },
                    component:()=>import('../components/'+menu.menuComponent)
                }

                routeItem.children.push(childRoute)
            })
        }
    })

    resetRouter()
    router.addRoutes (routes)
}
export default new Vuex.Store({
    state: {
        menu: [],
        token: '',
        userInfo: JSON.parse(sessionStorage.getItem("userInfo"))
    },
    mutations: {
        setMenu(state,menuList) {
            state.menu = menuList

            addNewRoute(menuList)
        },
        setRouter(state,menuList) {
            addNewRoute(menuList)
        },
        //set
        SET_TOKEN: (state,token) => {
            state.token = token
            localStorage.setItem("token",token)
        },
        SET_USERINFO: (state,userInfo) => {
            state.userInfo = userInfo
            //session只能存字符串，所以需要将userInfo序列化
            sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
        },
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem("token",'')
            sessionStorage.setItem("userInfo",JSON.stringify(''))
        }
    },
    getters: {
        getMenu(state) {
            return state.menu
        },
        //get
        getUser: (state) => {
            return state.userInfo
        }
    },
    plugins:[createPersistedState()]
})