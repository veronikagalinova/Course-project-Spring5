import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket, BusLineSearchResult } from '@app/_models';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(private http: HttpClient) { }

  buyTicket(route: BusLineSearchResult, travelDate: string) {
    const postData = {
      route: route, travelDate: travelDate
    };
    return this.http.post<Ticket>(`${environment.apiUrl}/api/tickets`, postData);
  }
}
