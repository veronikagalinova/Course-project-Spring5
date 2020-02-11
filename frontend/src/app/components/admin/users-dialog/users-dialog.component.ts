import { Component, OnInit, EventEmitter, Inject, Optional } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { User } from '@app/_models/User';
import { Role } from '@app/_models/Role';
import { MustMatch } from '@app/MustMatchValidatior';

@Component({
  selector: 'app-users-dialog',
  templateUrl: './users-dialog.component.html',
  styleUrls: ['./users-dialog.component.css']
})
export class UsersDialogComponent implements OnInit {

  WARNING_DELETE_MSG: string;
  user: User;
  title: string;
  action: string;
  form: FormGroup;
  roles = [Role.ADMIN, Role.BUS_COMPANY, Role.TRAVELER];

  public event: EventEmitter<any> = new EventEmitter();

  constructor(private fb: FormBuilder,
    public dialogRef: MatDialogRef<UsersDialogComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any) {
    this.title = `${data.action} user`;
    this.action = data.action;
    this.user = data.user;

    if (this.action == 'Delete') {
      this.WARNING_DELETE_MSG = `Are you sure you want to delete user ${this.user.username}?`;
    } else if (this.action == 'Update') {
      this.form = fb.group({
        username: [this.user ? this.user.username : '', [Validators.required, Validators.minLength(3), Validators.max(30)]],
        firstName: [this.user ? this.user.firstName : '', [Validators.required, Validators.minLength(3), Validators.max(30)]],
        lastName: [this.user ? this.user.lastName : '', [Validators.required, Validators.minLength(3), Validators.max(30)]],
        roles: [this.user ? this.user.roles : '', Validators.required]
      });
    } else {
      this.form = fb.group({
        username: [this.user ? this.user.username : '', [Validators.required, Validators.minLength(3), Validators.max(30)]],
        firstName: [this.user ? this.user.firstName : '', [Validators.required, Validators.minLength(3), Validators.max(30)]],
        lastName: [this.user ? this.user.lastName : '', [Validators.required, Validators.minLength(3), Validators.max(30)]],
        roles: [this.user ? this.user.roles : '', Validators.required],
        password: ['', [Validators.required]],
        confirmPassword: [''],
      }, {
        validator: MustMatch('password', 'confirmPassword')
      });
    }
  }

  ngOnInit() {

  }

  doAction() {
    if (this.action === 'Add' || this.action == 'Update') {

      const id = this.user && this.user.id;
      this.user = new User();
      this.user.username = this.f.username.value;
      this.user.firstName = this.f.firstName.value;
      this.user.lastName = this.f.lastName.value;
      this.user.roles = this.f.roles.value;
      if (id) this.user.id = id;
      if (this.action === 'Add') {
        this.user.password = this.f.password.value;
      }
    }

    this.event.emit({ event: this.action, user: this.user });
    this.dialogRef.close();
  }

  close() {
    this.dialogRef.close({ event: 'Cancel' });
  }

  get f() { return this.form.controls; }

}
