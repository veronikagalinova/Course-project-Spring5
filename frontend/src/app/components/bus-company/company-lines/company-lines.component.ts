import { Component, OnInit, OnChanges, ViewChild } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { BusLine } from '../../../_models/BusLine';
import { CreateBusLineComponent } from '../create-bus-line/create-bus-line.component';
import { MatTableDataSource, MatDialog, MatSnackBar, MatPaginator } from '@angular/material';
import { Stop } from '../../../_models/Stop';

@Component({
  selector: 'app-company-lines',
  templateUrl: './company-lines.component.html',
  styleUrls: ['./company-lines.component.css']
})
export class CompanyLinesComponent implements OnInit {
  companyLines: BusLine[];

  stops: Stop[];

  dataSource = new MatTableDataSource<BusLine>();

  displayedColumns: string[] = ['startPoint', 'endPoint', 'duration',
    'departureTime', 'price', 'distance', 'workingDays'];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private busLinesService: BusLinesService,
    public snackBar: MatSnackBar,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.getLines();
    this.getStops();
    this.dataSource.paginator = this.paginator;
  }


  getLines() {
    this.busLinesService.getAll().subscribe(res => {
      this.companyLines = res;
      this.dataSource.data = this.companyLines;
    });
  }

  getStops() {
    this.busLinesService.getStops().subscribe(res => {
      this.stops = res;
      this.stops.sort();
    });
  }

  openDialog() {
    let dialogRef = this.dialog.open(CreateBusLineComponent, {
      width: '600px',
      data: this.stops
    });

    dialogRef.componentInstance.event.subscribe((newLine) => {
      this.busLinesService.addBusLine(newLine).subscribe(res => {
        this.getLines();
        this.showSuccessMsg();
      });

    });
  }

  showSuccessMsg() {
    const successfullCreationMsg = "New bus line is created successfully!";

    this.snackBar.open(successfullCreationMsg, '', {
      duration: 5000,
      panelClass: ['success-snack-bar']
    });
  }

}


