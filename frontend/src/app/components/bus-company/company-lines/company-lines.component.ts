import { Component, OnInit, OnChanges } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { BusLine } from '../../../_models/BusLine';
import { CreateBusLineComponent } from '../create-bus-line/create-bus-line.component';
import { MatTableDataSource, MatDialog } from '@angular/material';

@Component({
  selector: 'app-company-lines',
  templateUrl: './company-lines.component.html',
  styleUrls: ['./company-lines.component.css']
})
export class CompanyLinesComponent implements OnInit {

  companyLines: BusLine[];

  dataSource = new MatTableDataSource<BusLine>();

  displayedColumns: string[] = ['startPoint', 'endPoint', 'duration',
    'departureTime', 'price', 'distance', 'workingDays'];

  constructor(private busLinesService: BusLinesService,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.getLines();
  }


  getLines() {
    this.busLinesService.getAll().subscribe(res => {
      this.companyLines = res;
      this.dataSource.data = this.companyLines;
    });
  }

  openDialog() {
    let dialogRef = this.dialog.open(CreateBusLineComponent, {
      width: '600px',
      data: 'Add new line'
    });
    dialogRef.componentInstance.event.subscribe((result) => {
      // this.dataService.addPost(result.newLine);
      // this.dataSource = new PostDataSource(this.dataService);
    });
  }
}


