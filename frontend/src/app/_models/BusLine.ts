import { Route } from "./Route";

export class BusLine {
    id?: string;
    route: Route;
    seats: number;
    workingDays: string[];
    departureTime: string;

    constructor(route: Route, seats: number, workingDays: string[], departureTime) {
        this.route = route;
        this.seats = seats;
        this.workingDays = workingDays;
        this.departureTime = departureTime;
    }
}