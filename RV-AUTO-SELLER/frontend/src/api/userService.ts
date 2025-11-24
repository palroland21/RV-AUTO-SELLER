import http from "./https";
import type { User } from "@/types/User";

export const getCurrentUser = async (): Promise<User> => {
    const response = await http.get<User>("/user/me");
    return response.data;
};
