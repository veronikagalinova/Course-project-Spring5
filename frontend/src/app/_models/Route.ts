import { Stop } from "./Stop";

export class Route {
    id?: string;
    startPoint: Stop;
    endPoint: Stop;
    duration: number;
    distance: number;

    constructor(startPoint: Stop, endPoint: Stop, duration: number, distance: number) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.duration = duration;
        this.distance = distance;
    }
}