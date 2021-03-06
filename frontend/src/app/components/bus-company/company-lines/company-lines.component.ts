import { Component, OnInit, ViewChild } from '@angular/core';
import { BusLinesService } from '../../../_services/bus-lines.service';
import { BusLine } from '../../../_models/BusLine';
import { MatTableDataSource, MatDialog, MatSnackBar, MatPaginator } from '@angular/material';
import { Stop } from '../../../_models/Stop';
import { BusLineDialogComponent } from '../bus-line-dialog/bus-line-dialog.component';

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
    'departureTime', 'price', 'distance', 'workingDays', 'action'];

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

  openDialog(action: string, busLine: BusLine) {
    let dialogRef = this.dialog.open(BusLineDialogComponent, {
      width: '600px',
      data: {
        stops: this.stops,
        action: action,
        busLine: busLine
      }
    });

    dialogRef.componentInstance.event.subscribe((result) => {
      if (result.event == 'Add') {
        this.addNewLine(result.busLine);
      } else if (result.event == 'Update') {
        this.updateLine(result.busLine);
      } else if (result.event == 'Delete') {
        this.deleteLine(result.busLine);
      }
    });
  }

  addNewLine(newLine: BusLine) {
    this.busLinesService.addBusLine(newLine).subscribe(res => {
      this.getLines();
      this.showSuccessMsg('created');
    });
  }

  updateLine(busLine: BusLine) {
    this.busLinesService.updateBusLine(busLine).subscribe(res => {
      this.getLines();
      this.showSuccessMsg('updated');
    });
  }

  deleteLine(busLine: BusLine) {
    this.busLinesService.deleteBusLine(busLine.id).subscribe(res => {
      this.getLines();
      this.showSuccessMsg('deleted');
    });
  }

  showSuccessMsg(action: string) {
    const successfullCreationMsg = `Bus line is ${action} successfully!`;

    this.snackBar.open(successfullCreationMsg, '', {
      duration: 5000,
      panelClass: ['success-snack-bar']
    });
  }

}


