import { Route } from "./Route";

export class BusLine {
    id?: string;
    route: Route;
    seats: number;
    workingDays: string[];
}