export interface User {
    id: number;
    firstName: string;
    lastName: string;
    username: string;
    telephone: string;
    email: string;
    role: "USER" | "ADMIN";
}
