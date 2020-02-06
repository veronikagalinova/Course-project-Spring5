import { Stop } from "./Stop";

export class Route {
    id?: string;
    startPoint: Stop;
    endPoint: Stop;
    duration: number;
    distance: number;
}