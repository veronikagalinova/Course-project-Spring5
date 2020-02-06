import { Route } from "./Route";

export class BusLine {
    id?: string;
    route: Route;
    price: number;
    seats: number;
    workingDays: string[];
    departureTime: string;

    constructor(route: Route, price: number, seats: number, workingDays: string[], departureTime) {
        this.route = route;
        this.price = price;
        this.seats = seats;
        this.workingDays = workingDays;
        this.departureTime = departureTime;
    }
}