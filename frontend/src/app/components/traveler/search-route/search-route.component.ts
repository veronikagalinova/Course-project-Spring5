import { Component, OnInit } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { Stop } from '../../../_models/Stop';
import { FormControl } from '@angular/forms';
import * as moment from 'moment';
import { BusLineSearchResult } from '../../../_models/BusLineSearchResult';
import { AppConstants } from '../../../app.constants';
import { TicketsService } from '../../../_services/tickets.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-search-route',
  templateUrl: './search-route.component.html',
  styleUrls: ['./search-route.component.css']
})
export class SearchRouteComponent implements OnInit {

  stops: Stop[];
  startPoint: string;
  endPoint: string;
  travelDate = new FormControl(new Date());
  minTravelDate = new Date(Date.now());
  searchResults: BusLineSearchResult[];
  noResultsMsg = AppConstants.SEARCH_ROUTE_NO_RESULT;

  constructor(private busLinesService: BusLinesService,
    public snackBar: MatSnackBar,
    private ticketsService: TicketsService) { }

  ngOnInit() {
    this.getStops();
  }

  getStops() {
    this.busLinesService.getStops().subscribe(res => {
      this.stops = res;
      this.stops.sort();
    });
  }

  search() {
    console.log(this.startPoint);
    console.log(this.endPoint);
    console.log(this.travelDate.value);
    if (!this.startPoint || !this.endPoint || !this.travelDate.value) {
      // TO DO - SHOW ERROR MSG
      console.log('***********')
    } else {
      const dateFormatted = this.formatDate();
      this.busLinesService.searchRoute(this.startPoint, this.endPoint, dateFormatted)
        .subscribe(res => {
          this.searchResults = res;
        });
    }
  }

  buyTicket(event) {
    const dateFormatted = this.formatDate();
    this.ticketsService.buyTicket(event, dateFormatted).subscribe(res => this.showSuccessMsg());
  }

  formatDate(): string {
    return moment(this.travelDate.value).format('YYYY-MM-DD');
  }

  showSuccessMsg() {
    const successfullCreationMsg = `Successfully bought ticket. See details in 'My tickets'.`;

    this.snackBar.open(successfullCreationMsg, '', {
      duration: 5000,
      panelClass: ['success-snack-bar']
    });
  }

}
