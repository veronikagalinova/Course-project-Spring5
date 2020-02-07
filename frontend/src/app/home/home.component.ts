import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '@app/_models/User';
import { UserService } from '@app/_services/user.service';
import { AuthenticationService } from '@app/_services/authentication.service';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
    loading = false;
    currentUser: User;
    userFromApi: User;

    constructor(
        private userService: UserService,
        private authenticationService: AuthenticationService
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
    }

    ngOnInit() {
        this.loading = true;
        this.userService.getById(this.currentUser.id).pipe(first()).subscribe(user => {
            this.loading = false;
            this.userFromApi = user;
        });
    }
}