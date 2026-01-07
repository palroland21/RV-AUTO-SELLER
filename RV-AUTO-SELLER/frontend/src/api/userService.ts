import { api } from "@/api/http";
import { User } from "@/types/User";

export async function getCurrentUser() {
    const response = await api.get("/auth/me");
    return response.data;
}

export const updateUser = async (userData: User) => {
    const response = await api.put("/auth/update", userData);
    return response.data;
};
