import { Component, OnInit, Input } from '@angular/core';
import { Ticket } from '@app/_models/Ticket';
import { AuthenticationService } from '@app/_services/authentication.service';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  @Input()
  ticket: Ticket;
  travelerName: string;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.setTravelerName();
  }

  setTravelerName() {
    this.authenticationService.currentUser.subscribe(currentUser => {
      if (currentUser) {
        this.travelerName = `${currentUser.firstName} ${currentUser.lastName}`;
      }

    });

  }

}
