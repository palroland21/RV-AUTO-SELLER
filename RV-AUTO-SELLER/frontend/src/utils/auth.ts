import { getCurrentUser } from "@/api/userService";

let cachedUser: any = null;

export async function requireAdmin() {
    const token = localStorage.getItem("token");
    if (!token) return { ok: false, reason: "no_token" };

    try {
        if (!cachedUser) cachedUser = await getCurrentUser();
        const role = cachedUser?.role;
        if (role !== "ADMIN") return { ok: false, reason: "not_admin" };
        return { ok: true, user: cachedUser };
    } catch {
        cachedUser = null;
        return { ok: false, reason: "invalid_token" };
    }
}

export function clearAuthCache() {
    cachedUser = null;
}
