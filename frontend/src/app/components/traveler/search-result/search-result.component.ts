import { Component, OnInit, Input, ViewChild, Output, EventEmitter } from '@angular/core';
import { BusLineSearchResult } from '../../../_models/BusLineSearchResult';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  @Input()
  searchResult: BusLineSearchResult[];
  @Output()
  newTicketRequest = new EventEmitter<BusLineSearchResult>();

  dataSource = new MatTableDataSource<BusLineSearchResult>();

  displayedColumns: string[] = ['startPoint', 'endPoint', 'company',
    'departureTime', 'price', 'duration', 'action'];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;


  constructor() { }

  ngOnInit() {
    this.dataSource.data = this.searchResult;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }


  buyTicket(element: BusLineSearchResult) {
    this.newTicketRequest.emit(element);
  }

}
