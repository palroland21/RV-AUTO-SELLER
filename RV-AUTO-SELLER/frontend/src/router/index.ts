import { createRouter, createWebHistory } from 'vue-router'
import Account from '@/views/Auth/Account.vue'
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/Auth/LoginView.vue";
import RegisterView from "@/views/Auth/RegisterView.vue";
import ListingForm from "@/components/listings/ListingForm.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeView
    },
    {
        path: '/account',
        name: 'Account',
        component: Account
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginView
    },
    {
        path: '/register',
        name: 'Register',
        component: RegisterView
    },
    {
        path:'/sell-form',
        name: 'SellForm',
        component: ListingForm
    },
    {
        path: '/listings',
        name: 'listings',
        component: () => import('../views/HomeView.vue') // Temporar o trimitem tot la Home până faci pagina de Listings
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
