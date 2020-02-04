import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from 'src/app/_models/User';
import { UserService} from 'src/app/_services/user.service';
import { AuthenticationService } from 'src/app/_services/authentication.service';

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
        console.log(this.currentUser);
    }

    ngOnInit() {
        this.loading = true;
        this.userService.getById(this.currentUser.id).pipe(first()).subscribe(user => {
            this.loading = false;
            this.userFromApi = user;
        });
    }
}