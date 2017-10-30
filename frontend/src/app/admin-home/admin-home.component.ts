import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  userIsAdmin(): boolean{
    return this.userService.isAdmin();
  }  
}
