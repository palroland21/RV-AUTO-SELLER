import { createRouter, createWebHistory } from 'vue-router'
import Account from '@/views/Auth/Account.vue'
import HomeView from "@/views/HomeView.vue";
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
