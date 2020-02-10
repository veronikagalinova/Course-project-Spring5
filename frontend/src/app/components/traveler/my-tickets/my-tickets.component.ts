import { Component, OnInit } from '@angular/core';
import { TicketsService } from '@app/_services/tickets.service';
import { Ticket } from '@app/_models/Ticket';

@Component({
  selector: 'app-my-tickets',
  templateUrl: './my-tickets.component.html',
  styleUrls: ['./my-tickets.component.css']
})
export class MyTicketsComponent implements OnInit {

  tickets: Ticket[];

  constructor(private ticketsService: TicketsService) { }

  ngOnInit() {
    this.getTicketsOfUser();
  }

  getTicketsOfUser() {
    this.ticketsService.getAll().subscribe(res => this.tickets = res);
  }

}
