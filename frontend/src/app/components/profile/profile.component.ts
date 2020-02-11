import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/_services/authentication.service';
import { User } from '@app/_models/User';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MustMatch } from '@app/MustMatchValidatior';
import { UserService } from '@app/_services/user.service';
import { AppConstants } from '@app/app.constants';
import { NotificationService } from '@app/_services/error/notification.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: User;
  form: FormGroup;
  submitted: boolean;

  constructor(private autenticationService: AuthenticationService,
    private notificationService: NotificationService,
    private usersService: UserService,
    private fb: FormBuilder) { }

  ngOnInit() {
    this.getUserInfo();
  }

  getUserInfo() {
    this.autenticationService.currentUser.subscribe(x => {
      this.currentUser = x;
      if (this.currentUser) { this.initializeForm(); }
    });
  }

  initializeForm() {
    this.form = this.fb.group({
      username: [this.currentUser.username, Validators.required],
      firstName: [this.currentUser.firstName, Validators.required],
      lastName: [this.currentUser.lastName, Validators.required],
      password: ['', [Validators.pattern("(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")]],
      confirmPassword: [''],
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    const userUpdated = new User();
    userUpdated.username = this.f.username.value;
    userUpdated.firstName = this.f.firstName.value;
    userUpdated.lastName = this.f.lastName.value;
    if (this.f.password.value !== "") {
      userUpdated.password = this.f.password.value;
    }

    this.usersService.updateProfile(this.currentUser.id, userUpdated).subscribe(res => {
      this.currentUser = res;
      this.autenticationService.updateCurrentUserValue(res);
      this.submitted = false;
      this.notificationService.showSuccess(AppConstants.PROFILE_UPDATE_SUCCESS_MSG);
    });

  }


}
