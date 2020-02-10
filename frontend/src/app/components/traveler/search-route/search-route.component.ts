import { Component, OnInit } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { Stop } from '../../../_models/Stop';
import { FormControl } from '@angular/forms';
import * as moment from 'moment';
import { BusLineSearchResult } from '../../../_models/BusLineSearchResult';
import { AppConstants } from '../../../app.constants';

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

  constructor(private busLinesService: BusLinesService) { }

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
      const dateFormatted = moment(this.travelDate.value).format('YYYY-MM-DD');
      this.busLinesService.searchRoute(this.startPoint, this.endPoint, dateFormatted)
        .subscribe(res => {
        this.searchResults = res;
          console.log(this.searchResults)
        });
    }
  }

}
