export interface ListingImage {
    id: number;
    url: string;
}

export interface ListingFormData {
    title: string;
    brand: string;
    model: string;
    type: string;
    yearOfManufacture: number | null;
    horsePower: number | null;
    price: number | null;
    fuelType: string;
    transmissionType: string;
    vin: string;
    location: string;
    description: string;
    username: string;
}

export interface ListingResponse {
    id: number;
    title: string;
    images: ListingImage[]; //list of images
    price: number;
    brand: string;
    model: string;
    type: string;
    yearOfManufacture: number;
    fuelType: string;
    horsePower: number;
    location: string;
    VIN: string;
    transmissionType: string;
    description: string;
    user: number;
}