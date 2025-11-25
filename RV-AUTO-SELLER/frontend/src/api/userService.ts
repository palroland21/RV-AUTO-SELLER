import axios from "axios";

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
