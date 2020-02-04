import { Component, OnInit } from '@angular/core';
import { Role } from '../_models/Role';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  roles = [Role.Traveler, Role.BUS_COMPANY];

  constructor() { }

  ngOnInit() {
  }

}
