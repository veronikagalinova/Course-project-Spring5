import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { BusLine } from '../_models/BusLine';
import { Observable, of } from 'rxjs';
import { Stop } from '../_models/Stop';
import { BusLineSearchResult } from '../_models/BusLineSearchResult';

@Injectable({
  providedIn: 'root'
})
export class BusLinesService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<BusLine[]>(`${environment.apiUrl}/api/busLines`);
  }

  getStops(): Observable<Stop[]> {
    return this.http.get<Stop[]>('http://localhost:4200/assets/stops.json');
  }

  addBusLine(newLine: BusLine): Observable<BusLine> {
    return this.http.post<BusLine>(`${environment.apiUrl}/api/busLines`, newLine);
  }

  updateBusLine(busLine: BusLine): Observable<BusLine> {
    return this.http.put<BusLine>(`${environment.apiUrl}/api/busLines/${busLine.id}`, busLine);
  }

  deleteBusLine(busLineId: string): Observable<BusLine> {
    return this.http.delete<BusLine>(`${environment.apiUrl}/api/busLines/${busLineId}`);
  }

  searchRoute(startPoint: string, endPoint: string, travelDate: string) {
    return this.http.get<BusLineSearchResult[]>(`${environment.apiUrl}/api/busLines/${startPoint}/${endPoint}/${travelDate}`);
  }
}
