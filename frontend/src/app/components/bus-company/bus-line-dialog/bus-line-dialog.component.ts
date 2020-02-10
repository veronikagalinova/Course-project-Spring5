import { Component, Inject, Optional, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { BusLine } from '@app/_models/BusLine';
import { Stop } from '@app/_models/Stop';
import { DayOfWeek } from '@app/_models/DayOfWeek';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Route } from '@app/_models/Route';

@Component({
  selector: 'app-bus-line-dialog',
  templateUrl: './bus-line-dialog.component.html',
  styleUrls: ['./bus-line-dialog.component.css']
})
export class BusLineDialogComponent {
  title = '';
  WARNING_DELETE_MSG = '';
  action: string;
  busLine: BusLine;
  stops: Stop[];
  form: FormGroup;
  workingDays = Object.keys(DayOfWeek);
  public event: EventEmitter<any> = new EventEmitter();

  constructor(private fb: FormBuilder,
    public dialogRef: MatDialogRef<BusLineDialogComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any) {

    this.title = `${data.action} bus line`;
    this.busLine = data.busLine;
    this.action = data.action;

    if (this.action === 'Add' || this.action == 'Update') {
      this.stops = data.stops;
      this.form = fb.group({
        startPoint: [this.busLine ? this.busLine.route.startPoint.location : '', Validators.required],
        endPoint: [this.busLine ? this.busLine.route.endPoint.location : '', Validators.required],
        distance: [this.busLine ? this.busLine.route.distance : '', [Validators.required, Validators.min(0)]],
        duration: [this.busLine ? this.busLine.route.duration : '', [Validators.required, Validators.min(0)]],
        price: [this.busLine ? this.busLine.price : '', [Validators.required, Validators.min(0)]],
        seats: [this.busLine ? this.busLine.seats : '', [Validators.required, Validators.min(0)]],
        workingDays: [this.busLine ? this.busLine.workingDays : '', Validators.required],
        departureTime: [this.busLine ? this.busLine.departureTime : '', Validators.required]
      });
    } else {
      this.WARNING_DELETE_MSG = `Are you sure you want to delete line ${this.busLine.route.startPoint.location}-${this.busLine.route.endPoint.location}?`;
    }

  }


  close() {
    this.dialogRef.close({ event: 'Cancel' });
  }

  get f() { return this.form.controls; }

  doAction(): void {

    if (this.action === 'Add' || this.action == 'Update') {

      const route = new Route(this.f.startPoint.value, this.f.endPoint.value,
        this.f.duration.value, this.f.distance.value);

      const id = this.busLine && this.busLine.id;
      this.busLine = new BusLine(route, this.f.price.value, this.f.seats.value,
        this.f.workingDays.value, this.f.departureTime.value);
      if (id) this.busLine.id = id; // when creating new line there is no id, when update bus line keep line id
    }

    this.event.emit({ event: this.action, busLine: this.busLine });
    this.dialogRef.close();
  }

}
