import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { BusLine } from '../_models/BusLine';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BusLinesService {

  constructor(private http: HttpClient) { }

  getAll() {
    // return this.http.get<BusLine[]>(`${environment.apiUrl}/api/busLines`);
    return this.http.get<BusLine[]>('http://localhost:4200/assets/lines.json');

  }
}
