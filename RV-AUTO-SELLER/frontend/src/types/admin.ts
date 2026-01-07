export type AdminStatsResponse = {
    totalUsers: number;
    totalListings: number;
    totalAppointments: number;
    totalFeedbacks: number;

    listingsByBrand?: Record<string, number>;
    listingsByType?: Record<string, number>;
    listingsByFuel?: Record<string, number>;
};

export type AdminUserUpdateRequest = {
    firstName?: string | null;
    lastName?: string | null;
    username?: string | null;
    telephone?: string | null;
    email?: string | null;
    newPassword?: string | null;
    role?: "USER" | "ADMIN" | null;
};

export type AdminRoleUpdateRequest = {
    role: "USER" | "ADMIN";
};
