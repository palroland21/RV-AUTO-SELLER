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
    }

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
