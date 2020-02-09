import { Component, OnInit } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { Stop } from '../../../_models/Stop';
import { FormControl } from '@angular/forms';

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

}
