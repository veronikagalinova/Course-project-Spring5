import { Component, OnInit, EventEmitter, Inject } from '@angular/core';
import { BusLine } from '../../../_models/BusLine';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BusLinesService } from 'src/app/_services/bus-lines.service';
import { Stop } from '@app/_models/Stop';
import { DayOfWeek } from '../../../_models/DayOfWeek';
import { Route } from '../../../_models/Route';

@Component({
  selector: 'app-create-bus-line',
  templateUrl: './create-bus-line.component.html',
  styleUrls: ['./create-bus-line.component.css']
})
export class CreateBusLineComponent {

  newLine: BusLine;

  stops: Stop[];

  form: FormGroup;

  workingDays = Object.keys(DayOfWeek);

  public event: EventEmitter<BusLine> = new EventEmitter();

  constructor(private fb: FormBuilder,
    public dialogRef: MatDialogRef<CreateBusLineComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log(data)
    this.stops = data;
    this.form = fb.group({
      startPoint: ['', Validators.required],
      endPoint: ['', Validators.required],
      distance: ['', [Validators.required, Validators.min(0)]],
      duration: ['', [Validators.required, Validators.min(0)]],
      price: ['', [Validators.required, Validators.min(0)]],
      seats: ['', [Validators.required, Validators.min(0)]],
      workingDays: ['', Validators.required],
      departureTime: ['', Validators.required]
    });
  }

  close() {
    this.dialogRef.close();
  }

  get f() { return this.form.controls; }

  save(): void {
    const route = new Route(this.f.startPoint.value, this.f.endPoint.value,
      this.f.duration.value, this.f.distance.value);
    this.newLine = new BusLine(route, this.f.price.value, this.f.seats.value, this.f.workingDays.value, this.f.departureTime.value);
    this.event.emit(this.newLine);
    this.dialogRef.close();
  }

}
