import { Component, OnInit, OnChanges } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { BusLine } from '../../../_models/BusLine';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-company-lines',
  templateUrl: './company-lines.component.html',
  styleUrls: ['./company-lines.component.css']
})
export class CompanyLinesComponent implements OnInit, OnChanges {
  companyLines: BusLine[];
  dataSource = new MatTableDataSource<BusLine>();
  displayedColumns: string[] = ['startPoint', 'endPoint', 'duration', 'departureTime', 'price', 'distance', 'workingDays'];
  constructor(private busLinesService: BusLinesService) { }

  ngOnInit() {
    this.getLines();
  }

  ngOnChanges() {


  }

  getLines() {
    this.busLinesService.getAll().subscribe(res => {
      this.companyLines = res;
      this.dataSource.data = this.companyLines;
      console.log(this.dataSource)
    });
  }

}
