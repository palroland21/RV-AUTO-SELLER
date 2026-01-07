export type Appointment = {
    id: number;
    location: string;
    date: string; // LocalDateTime vine ca string JSON
    details: string;
    client: number; // id
    seller: number; // id
};
