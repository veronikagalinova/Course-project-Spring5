import { Component } from '@angular/core';
import { User } from './_models/User';
import { Router } from '@angular/router';
import { AuthenticationService } from './_services/authentication.service';
import { Role } from './_models/Role';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = "Travel By Bus";
    currentUser: User;
    isAuthenticated: boolean;
    isBusCompany: boolean;
    isTraveler: boolean;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.authenticationService.currentUser.subscribe(x => {
            this.currentUser = x;
            this.isAuthenticated = this.currentUser != null;
            this.isBusCompany = this.currentUser && this.currentUser.roles.includes(Role.BUS_COMPANY);
            this.isTraveler = this.currentUser && this.currentUser.roles.includes(Role.TRAVELER);
        });


    }


    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
}
