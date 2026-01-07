import { createRouter, createWebHistory } from 'vue-router'
import Account from '@/views/Auth/Account.vue'
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/Auth/LoginView.vue";
import RegisterView from "@/views/Auth/RegisterView.vue";
import ListingForm from "@/components/listings/ListingForm.vue";
import ListingDetailsView from "@/views/ListingDetailsView.vue";
import ContactView from '../views/ContactView.vue'
import FaqView from '../views/FaqView.vue'
import PrivacyPolicyView from '../views/PrivacyPolicyView.vue'
import { requireAdmin } from "@/utils/auth";

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
        component: () => import('../views/VehiclesView.vue')
    },
    {
        path: '/listing/:id',
        name: 'listing-details',
        component: ListingDetailsView,
        props: true
    },
    {
        path: '/my-appointments',
        name: 'myAppointments',
        component: Account,
    },
    {
        path:'/contact',
        name: 'contact',
        component: ContactView
    },
    {
        path: '/faq',
        name: 'faq',
        component: FaqView
    },
    {
        path: '/privacy',
        name: 'privacy',
        component: PrivacyPolicyView
    },
    {
        path: "/admin",
        component: () => import("@/layouts/AdminLayout.vue"),
        meta: { requiresAdmin: true },
        children: [
            { path: "", name: "admin-dashboard", component: () => import("@/views/Admin/AdminDashboardView.vue") },
            { path: "users", name: "admin-users", component: () => import("@/views/Admin/AdminUsersView.vue") },
            { path: "listings", name: "admin-listings", component: () => import("@/views/Admin/AdminListingsView.vue") },
            { path: "appointments", name: "admin-appointments", component: () => import("@/views/Admin/AdminAppointmentsView.vue") },
            { path: "feedbacks", name: "admin-feedbacks", component: () => import("@/views/Admin/AdminFeedbacksView.vue") },
        ],
    },
    {
        path: "/403",
        name: "forbidden",
        component: () => import("@/views/ForbiddenView.vue"),
    },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach(async (to, _from, next) => {
    if (to.meta?.requiresAdmin) {
        const res = await requireAdmin();
        if (!res.ok) return next({ name: "forbidden" });
    }
    next();
});

export default router
