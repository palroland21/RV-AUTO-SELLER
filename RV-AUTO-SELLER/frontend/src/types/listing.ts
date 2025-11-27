export interface ListingFormData {
    title: string;
    brand: string;
    model: string;
    type: string;
    yearOfManufacture: number | null; // null la inceput, cand e gol inputul
    horsePower: number | null;
    price: number | null;
    fuelType: string;
    transmissionType: string;
    vin: string;
    location: string;
    description: string;
    username: string;
}