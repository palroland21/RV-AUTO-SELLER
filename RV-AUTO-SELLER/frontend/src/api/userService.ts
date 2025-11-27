import axios from "axios";
import {User} from "@/types/User";

export async function getCurrentUser() {
    const token = localStorage.getItem("token");

    if (!token) throw new Error("No token received");

    const response = await axios.get("http://localhost:9090/auth/me", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
}

export const updateUser = async (userData: User) => {
    const token = localStorage.getItem("token");

    const response = await axios.put("http://localhost:9090/auth/update", userData, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });

    return response.data;
}
