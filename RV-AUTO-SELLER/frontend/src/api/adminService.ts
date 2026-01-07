import { api } from "@/api/http";
import type { AdminStatsResponse, AdminUserUpdateRequest, AdminRoleUpdateRequest } from "@/types/admin";
import type { User } from "@/types/User";
import type { ListingResponse } from "@/types/listing";
import type { Appointment } from "@/types/appointment";
import type { Feedback } from "@/types/feedback";

export const adminApi = {
    async getStats(): Promise<AdminStatsResponse> {
        const res = await api.get("/admin/stats");
        return res.data;
    },

    // users
    async getUsers(): Promise<User[]> {
        const res = await api.get("/admin/users");
        return res.data;
    },

    async updateUser(id: number, payload: AdminUserUpdateRequest): Promise<User> {
        const res = await api.put(`/admin/users/${id}`, payload);
        return res.data;
    },

    async updateUserRole(id: number, payload: AdminRoleUpdateRequest): Promise<User> {
        const res = await api.patch(`/admin/users/${id}/role`, payload);
        return res.data;
    },

    async deleteUser(id: number): Promise<void> {
        await api.delete(`/admin/users/${id}`);
    },

    // listings
    async getListings(): Promise<ListingResponse[]> {
        const res = await api.get("/admin/listings");
        return res.data;
    },

    async updateListing(id: number, payload: any): Promise<ListingResponse> {
        const res = await api.put(`/admin/listings/${id}`, payload);
        return res.data;
    },

    async markListingSold(id: number, sold: boolean): Promise<ListingResponse> {
        const res = await api.patch(`/admin/listings/${id}/sold`, null, { params: { sold } });
        return res.data;
    },

    async deleteListing(id: number): Promise<void> {
        await api.delete(`/admin/listings/${id}`);
    },

    // appointments
    async getAppointments(): Promise<Appointment[]> {
        const res = await api.get("/admin/appointments");
        return res.data;
    },

    async deleteAppointment(id: number): Promise<void> {
        await api.delete(`/admin/appointments/${id}`);
    },

    // feedbacks
    async getFeedbacks(): Promise<Feedback[]> {
        const res = await api.get("/admin/feedbacks");
        return res.data;
    },

    async deleteFeedback(id: number): Promise<void> {
        await api.delete(`/admin/feedbacks/${id}`);
    },
};
