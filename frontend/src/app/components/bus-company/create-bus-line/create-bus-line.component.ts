import { Component, OnInit, EventEmitter, Inject } from '@angular/core';
import { BusLine } from '../../../_models/BusLine';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-bus-line',
  templateUrl: './create-bus-line.component.html',
  styleUrls: ['./create-bus-line.component.css']
})
export class CreateBusLineComponent {

  newLine: BusLine;

  form: FormGroup;

  public event: EventEmitter<BusLine> = new EventEmitter();

  constructor(private fb: FormBuilder,
    public dialogRef: MatDialogRef<CreateBusLineComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.form = fb.group({
      startPoint: ['', Validators.required],
      endPoint: ['', Validators.required],
      distance: ['', Validators.required],
      duration: ['', Validators.required],
      price: ['', Validators.required],
      seats: ['', Validators.required],
      workingDays: ['', Validators.required],
      departureTime: ['', Validators.required]
    });
  }

  close() {
    this.dialogRef.close();
  }

  save(): void {
    // this.blogPost.position = this.dataService.dataLength();
    this.event.emit(this.newLine);
    this.dialogRef.close();
  }

}
