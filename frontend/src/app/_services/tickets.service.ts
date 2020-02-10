import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from '@app/_models/Ticket';
import { BusLineSearchResult } from '@app/_models/BusLineSearchResult';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${environment.apiUrl}/api/tickets`);
  }

  buyTicket(route: BusLineSearchResult, travelDate: string): Observable<Ticket> {
    const postData = {
      route: route, travelDate: travelDate
    };
    return this.http.post<Ticket>(`${environment.apiUrl}/api/tickets`, postData);
  }

}
